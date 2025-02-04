package io.rapid.engine.strategy.manager;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.mdata.SavedMarketData;
import io.rapid.core.order.impl.ChildOrder;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.engine.order.OrderKeeper;
import org.slf4j.Logger;

import static io.mercury.common.concurrent.queue.SingleConsumerQueueWithJCT.spscQueue;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class AsyncMultiStrategyManager extends MultiStrategyManager {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AsyncMultiStrategyManager.class);

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
                            ChildOrder order = OrderKeeper.handleOrderReport(event);
                            log.info(
                                    "Search Order OK. brokerSysId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
                                    event.getBrokerOrdSysId(), order.getStrategyId(),
                                    order.getInstrument().getInstrumentCode(), event.getOrdSysId());
                            strategyMap.get(order.getStrategyId()).onOrder(order);
                        }
                        case AdaptorEvent -> {
                            AdaptorReport adaptorReport = msg.getAdaptorEvent();
                            String adaptorId = adaptorReport.getAdaptorId();
                            log.info("Recv AdaptorEvent -> {}", adaptorReport);
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

        private io.rapid.core.event.inbound.OrderReport orderReport;

        private AdaptorReport adaptorReport;

        private QueueMsg(SavedMarketData marketData) {
            this.mark = MarketData;
            this.marketData = marketData;
        }

        private QueueMsg(io.rapid.core.event.inbound.OrderReport orderReport) {
            this.mark = OrderReport;
            this.orderReport = orderReport;
        }

        private QueueMsg(AdaptorReport adaptorReport) {
            this.mark = AdaptorEvent;
            this.adaptorReport = adaptorReport;
        }

        public int getMark() {
            return mark;
        }

        public SavedMarketData getMarketData() {
            return marketData;
        }

        public io.rapid.core.event.inbound.OrderReport getOrderEvent() {
            return orderReport;
        }

        public AdaptorReport getAdaptorEvent() {
            return adaptorReport;
        }

    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub

    }

}
