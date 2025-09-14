package io.cygnux.rapid.core.stream;

import io.cygnux.rapid.core.stream.event.AdaptorReport;
import io.cygnux.rapid.core.stream.event.BalanceReport;
import io.cygnux.rapid.core.stream.event.DepthMarketData;
import io.cygnux.rapid.core.stream.event.FastMarketData;
import io.cygnux.rapid.core.stream.event.InstrumentStatusReport;
import io.cygnux.rapid.core.stream.event.OrderReport;
import io.cygnux.rapid.core.stream.event.PositionsReport;
import io.mercury.common.concurrent.disruptor.RingEventHandler;

public abstract class StreamEventbus extends RingEventHandler<SharedEvent> {

    /**
     * 默认使用单生产者
     */
    protected StreamEventbus() {
        this(RingEventHandler.singleProducer());
    }

    /**
     * 使用自定义的构建器
     *
     * @param builder Builder
     */
    protected StreamEventbus(Builder builder) {
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