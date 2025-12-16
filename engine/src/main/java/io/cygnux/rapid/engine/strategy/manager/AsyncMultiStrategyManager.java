package io.cygnux.rapid.engine.strategy.manager;

import io.cygnux.rapid.core.shared.event.OrderReport;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.queue.Queue;
import io.cygnux.rapid.core.shared.event.AdapterReport;
import io.cygnux.rapid.core.mdata.SavedMarketData;
import io.cygnux.rapid.core.order.Order;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import org.slf4j.Logger;

import static io.mercury.common.concurrent.queue.SingleConsumerQueueWithJCT.spscQueue;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class AsyncMultiStrategyManager extends MultiStrategyManager {

    private static final Logger log = getLogger(AsyncMultiStrategyManager.class);

    private final Queue<QueueMsg> queue;

    private static final int MarketData = 0;
    private static final int OrderReport = 1;
    private static final int AdaptorEvent = 2;

    public AsyncMultiStrategyManager(Capacity capacity) {
        this.queue = spscQueue("AsyncMultiStrategyScheduler-Queue")
                .capacity(capacity.size()).spinStrategy().process(msg -> {
                    switch (msg.getMark()) {
                        case MarketData -> {
                            SavedMarketData marketData = msg.getMarketData();
                            subscribedMap.get(marketData.instrumentCode())
                                    .each(strategy -> {
                                        if (strategy.isEnabled())
                                            strategy.acceptMarketData(marketData);
                                    });
                        }
                        case OrderReport -> {
                            var event = msg.getOrderEvent();
                            log.info("Handle OrderEvent, brokerUniqueId==[{}], ordSysId==[{}]", event.getBrokerOrdSysId(),
                                    event.getOrdSysId());
                            Order order = orderKeeper.onOrderReport(event);
                            log.info(
                                    "Search Order OK. brokerSysId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
                                    event.getBrokerOrdSysId(), order.getStrategyId(),
                                    order.getInstrument().getInstrumentCode(), event.getOrdSysId());
                            strategyMap.get(order.getStrategyId()).onOrder(order);
                        }
                        case AdaptorEvent -> {
                            AdapterReport adapterReport = msg.getAdaptorEvent();
                            String adaptorId = adapterReport.getAdaptorId();
                            log.info("Recv AdaptorEvent -> {}", adapterReport);
                        }
                        default -> throw new IllegalStateException("scheduler mark illegal");
                    }
                });
    }





    @Override
    public void onEvent(StrategyEvent event) {

    }

    private static class QueueMsg {

        private final int mark;

        private SavedMarketData marketData;

        private OrderReport orderReport;

        private AdapterReport adapterReport;

        private QueueMsg(SavedMarketData marketData) {
            this.mark = MarketData;
            this.marketData = marketData;
        }

        private QueueMsg(OrderReport orderReport) {
            this.mark = OrderReport;
            this.orderReport = orderReport;
        }

        private QueueMsg(AdapterReport adapterReport) {
            this.mark = AdaptorEvent;
            this.adapterReport = adapterReport;
        }

        public int getMark() {
            return mark;
        }

        public SavedMarketData getMarketData() {
            return marketData;
        }

        public OrderReport getOrderEvent() {
            return orderReport;
        }

        public AdapterReport getAdaptorEvent() {
            return adapterReport;
        }

    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub

    }

}
