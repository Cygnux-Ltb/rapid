package io.cygnuxltb.jcts.engine.manager;

import io.cygnuxltb.jcts.engine.trader.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.serialization.avro.receive.AvroAdaptorEvent;
import io.horizon.trader.serialization.avro.receive.AvroOrderEvent;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.concurrent.queue.ScQueueWithJCT.spscQueue;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class AsyncMultiStrategyManager<M extends MarketData> extends MultiStrategyManager<M> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AsyncMultiStrategyManager.class);

    private final Queue<QueueMsg> queue;

    private static final int MarketData = 0;
    private static final int OrderReport = 1;
    private static final int AdaptorEvent = 2;

    public AsyncMultiStrategyManager(Capacity capacity) {
        this.queue = spscQueue("AsyncMultiStrategyScheduler-Queue")
                .capacity(capacity.value()).spinStrategy().process(msg -> {
                    switch (msg.getMark()) {
                        case MarketData -> {
                            M marketData = msg.getMarketData();
                            MarketDataKeeper.onMarketDate(marketData);
                            subscribedMap.get(marketData.getInstrumentId()).each(strategy -> {
                                if (strategy.isEnabled()) {
                                    strategy.onMarketData(marketData);
                                }
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
                            AvroAdaptorEvent adaptorReport = msg.getAdaptorEvent();
                            String adaptorId = adaptorReport.getAdaptorId();
                            log.info("Recv AdaptorEvent -> {}", adaptorReport);
                        }
                        default -> throw new IllegalStateException("scheduler mark illegal");
                    }
                });
    }

    // TODO add pools
    @Override
    public void onMarketData(@Nonnull M marketData) {
        queue.enqueue(new QueueMsg(marketData));
    }

    // TODO add pools
    @Override
    public void onOrderEvent(@Nonnull AvroOrderEvent event) {
        queue.enqueue(new QueueMsg(event));
    }

    // TODO add pools
    @Override
    public void onAdaptorEvent(@Nonnull AvroAdaptorEvent report) {
        queue.enqueue(new QueueMsg(report));
    }

    private class QueueMsg {

        private final int mark;

        private M marketData;

        private AvroOrderEvent orderEvent;

        private AvroAdaptorEvent adaptorEvent;

        private QueueMsg(M marketData) {
            this.mark = MarketData;
            this.marketData = marketData;
        }

        private QueueMsg(AvroOrderEvent orderEvent) {
            this.mark = OrderReport;
            this.orderEvent = orderEvent;
        }

        private QueueMsg(AvroAdaptorEvent adaptorEvent) {
            this.mark = AdaptorEvent;
            this.adaptorEvent = adaptorEvent;
        }

        public int getMark() {
            return mark;
        }

        public M getMarketData() {
            return marketData;
        }

        public AvroOrderEvent getOrderEvent() {
            return orderEvent;
        }

        public AvroAdaptorEvent getAdaptorEvent() {
            return adaptorEvent;
        }

    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub

    }

}
