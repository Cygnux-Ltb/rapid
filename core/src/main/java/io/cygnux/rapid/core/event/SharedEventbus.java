package io.cygnux.rapid.core.event;

import io.cygnux.rapid.core.types.event.received.AdapterReport;
import io.cygnux.rapid.core.types.event.received.BalanceReport;
import io.cygnux.rapid.core.types.event.received.DepthMarketData;
import io.cygnux.rapid.core.types.event.received.FastMarketData;
import io.cygnux.rapid.core.types.event.received.InstrumentStatusReport;
import io.cygnux.rapid.core.types.event.received.OrderReport;
import io.cygnux.rapid.core.types.event.received.PositionsReport;
import io.mercury.common.concurrent.disruptor.RingEventHandler;

public abstract class SharedEventbus extends RingEventHandler<SharedEvent> {

    /**
     * 默认使用单生产者
     */
    protected SharedEventbus() {
        this(RingEventHandler.singleProducer());
    }

    /**
     * 使用自定义的构建器
     *
     * @param builder Builder
     */
    protected SharedEventbus(Builder builder) {
        super(builder.name("stream-eventbus"), SharedEvent.EVENT_FACTORY);
    }

    public void put(DepthMarketData in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void put(FastMarketData in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void put(InstrumentStatusReport in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void put(AdapterReport in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void put(OrderReport in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void put(PositionsReport in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void put(BalanceReport in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

}