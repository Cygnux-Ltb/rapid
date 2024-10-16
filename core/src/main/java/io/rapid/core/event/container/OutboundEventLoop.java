package io.rapid.core.event.container;

import io.rapid.core.event.OutboundEvent;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;

public abstract non-sealed class OutboundEventLoop extends EventLoop<OutboundEvent> {

    protected OutboundEventLoop() {
        this(EventLoop.builder());
    }

    protected OutboundEventLoop(Builder builder) {
        super(builder.name("outbound-eventbus"),
                OutboundEvent.EVENT_FACTORY);
    }

    public void publish(SubscribeMarketData in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void publish(NewOrder in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void publish(CancelOrder in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void publish(QueryOrder in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void publish(QueryPosition in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

    public void publish(QueryBalance in) {
        eventbus.publish((event, sequence) -> event.updateWith(in));
    }

}
