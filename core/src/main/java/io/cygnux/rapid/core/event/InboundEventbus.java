package io.cygnux.rapid.core.event;

import io.mercury.common.concurrent.disruptor.RingEventHandler;
import io.cygnux.rapid.core.event.inbound.AdaptorReport;
import io.cygnux.rapid.core.event.inbound.BalanceReport;
import io.cygnux.rapid.core.event.inbound.DepthMarketData;
import io.cygnux.rapid.core.event.inbound.InstrumentStatusReport;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import io.cygnux.rapid.core.event.inbound.FastMarketData;

public abstract class InboundEventbus extends RingEventHandler<InboundEvent> {

    /**
     * 默认使用单生产者
     */
    protected InboundEventbus() {
        this(RingEventHandler.singleProducer());
    }

    /**
     * 使用自定义的构建器
     *
     * @param builder Builder
     */
    protected InboundEventbus(Builder builder) {
        super(builder.name("inbound-eventbus"), InboundEvent.EVENT_FACTORY);
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

    public void put(AdaptorReport in) {
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