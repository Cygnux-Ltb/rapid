package io.rapid.core.event;

import io.mercury.common.concurrent.disruptor.RingEventHandler;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;

public abstract class OutboundEventLoop extends RingEventHandler<OutboundEvent> {

    /**
     * 默认使用单生产者
     */
    protected OutboundEventLoop() {
        this(RingEventHandler.singleProducer());
    }

    /**
     * 使用自定义的构建器
     *
     * @param builder Builder
     */
    protected OutboundEventLoop(Builder builder) {
        super(builder.name("outbound-loop"), OutboundEvent.EVENT_FACTORY);
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
