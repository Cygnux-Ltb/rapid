package io.cygnux.rapid.core.adapter;

import io.mercury.common.concurrent.disruptor.RingEventHandler;
import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.cygnux.rapid.core.adapter.event.QueryBalance;
import io.cygnux.rapid.core.adapter.event.QueryOrder;
import io.cygnux.rapid.core.adapter.event.QueryPosition;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;

public abstract class SentEventbus extends RingEventHandler<SentEvent> {

    /**
     * 默认使用单生产者
     */
    protected SentEventbus() {
        this(RingEventHandler.singleProducer());
    }

    /**
     * 使用自定义的构建器
     *
     * @param builder Builder
     */
    protected SentEventbus(Builder builder) {
        super(builder.name("outbound-eventbus"), SentEvent.EVENT_FACTORY);
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
