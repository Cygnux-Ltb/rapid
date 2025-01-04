package io.rapid.core.event;

import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import lombok.Getter;

/**
 * 由[Adaptor]端在统一处理函数中进行处理, 包含当前类型的[Adaptor]需要处理的接入事件和统一的输出事件
 *
 * @param <T>
 */
public abstract class OutboundCompositeEvent<T extends OutboundCompositeEvent<T>> {

    @Getter
    protected boolean isOutboundEvent;

    @Getter
    protected OutboundEvent outboundEvent = new OutboundEvent();

    protected abstract T self();

    /**
     * @param newOrder NewOrder
     * @return SendEvent
     */
    public T updateWith(NewOrder newOrder) {
        this.isOutboundEvent = true;
        this.outboundEvent.updateWith(newOrder);
        return self();
    }

    /**
     * @param cancelOrder CancelOrder
     * @return SendEvent
     */
    public T updateWith(CancelOrder cancelOrder) {
        this.isOutboundEvent = true;
        this.outboundEvent.updateWith(cancelOrder);
        return self();
    }

    /**
     * @param queryOrder QueryOrder
     * @return SendEvent
     */
    public T updateWith(QueryOrder queryOrder) {
        this.isOutboundEvent = true;
        this.outboundEvent.updateWith(queryOrder);
        return self();
    }

    /**
     * @param queryPosition QueryPositions
     * @return SendEvent
     */
    public T updateWith(QueryPosition queryPosition) {
        this.isOutboundEvent = true;
        this.outboundEvent.updateWith(queryPosition);
        return self();
    }

    /**
     * @param queryBalance QueryBalance
     * @return SendEvent
     */
    public T updateWith(QueryBalance queryBalance) {
        this.isOutboundEvent = true;
        this.outboundEvent.updateWith(queryBalance);
        return self();
    }

    /**
     * @param subscribeMarketData SubscribeMarketData
     * @return SendEvent
     */
    public T updateWith(SubscribeMarketData subscribeMarketData) {
        this.isOutboundEvent = true;
        this.outboundEvent.updateWith(subscribeMarketData);
        return self();
    }

}
