package io.rapid.engine.strategy.manager;

import io.rapid.core.event.inbound.FastMarketData;
import io.rapid.core.event.inbound.OrderEvent;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyException;
import io.rapid.engine.trader.OrderKeeper;
import io.rapid.core.order.ChildOrder;
import io.rapid.core.event.inbound.AdaptorEvent;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.concurrent.queue.SingleConsumerQueueWithJCT.spscQueue;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class AsyncMultiEventScheduler extends MultiEventScheduler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AsyncMultiEventScheduler.class);

    private final Queue<QueueMsg> queue;

    private static final int MarketData = 0;
    private static final int OrderReport = 1;
    private static final int AdaptorEvent = 2;

    public AsyncMultiEventScheduler(Capacity capacity) {
        this.queue = spscQueue("AsyncMultiStrategyScheduler-Queue")
                .capacity(capacity.size()).spinStrategy().process(msg -> {
                    switch (msg.getMark()) {
                        case MarketData -> {
                            FastMarketData marketData = msg.getMarketData();
                            subscribedMap.get(marketData.getInstrumentCode())
                                    .each(strategy -> {
                                        if (strategy.isEnabled())
                                            strategy.onMarketData(marketData);
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
                            AdaptorEvent adaptorReport = msg.getAdaptorEvent();
                            String adaptorId = adaptorReport.getAdaptorId();
                            log.info("Recv AdaptorEvent -> {}", adaptorReport);
                        }
                        default -> throw new IllegalStateException("scheduler mark illegal");
                    }
                });
    }

    // TODO add pools
    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        queue.enqueue(new QueueMsg(marketData));
    }

    // TODO add pools
    @Override
    public void onOrderEvent(@Nonnull OrderEvent event) {
        queue.enqueue(new QueueMsg(event));
    }

    // TODO add pools
    @Override
    public void onAdaptorEvent(@Nonnull io.rapid.core.event.inbound.AdaptorEvent report) {
        queue.enqueue(new QueueMsg(report));
    }

    @Override
    public ImmutableIntObjectMap<Strategy> getStrategies() {
        return null;
    }

    @Override
    public ImmutableIntObjectMap<Instrument> getInstruments() {
        return null;
    }

    @Override
    public void onEvent(StrategyEvent event) {

    }

    @Override
    public void onException(Exception exception) throws StrategyException {

    }

    private static class QueueMsg {

        private final int mark;

        private FastMarketData marketData;

        private OrderEvent orderEvent;

        private AdaptorEvent adaptorEvent;

        private QueueMsg(FastMarketData marketData) {
            this.mark = MarketData;
            this.marketData = marketData;
        }

        private QueueMsg(OrderEvent orderEvent) {
            this.mark = OrderReport;
            this.orderEvent = orderEvent;
        }

        private QueueMsg(AdaptorEvent adaptorEvent) {
            this.mark = AdaptorEvent;
            this.adaptorEvent = adaptorEvent;
        }

        public int getMark() {
            return mark;
        }

        public FastMarketData getMarketData() {
            return marketData;
        }

        public OrderEvent getOrderEvent() {
            return orderEvent;
        }

        public AdaptorEvent getAdaptorEvent() {
            return adaptorEvent;
        }

    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub

    }

}
