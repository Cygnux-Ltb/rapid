package io.rapid.core.event.container;

import io.rapid.core.event.InboundEvent;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.InstrumentStatusReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
import io.rapid.core.event.inbound.RawMarketData;

public abstract non-sealed class InboundEventLoop extends EventLoop<InboundEvent> {

    /**
     * 默认使用单生产者
     */
    protected InboundEventLoop() {
        this(EventLoop.singleProducer());
    }

    /**
     * 使用自定义的构建器
     *
     * @param builder Builder
     */
    protected InboundEventLoop(Builder builder) {
        super(builder.name("inbound-eventbus"), InboundEvent.EVENT_FACTORY);
    }

    public void put(DepthMarketData in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void put(RawMarketData in) {
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