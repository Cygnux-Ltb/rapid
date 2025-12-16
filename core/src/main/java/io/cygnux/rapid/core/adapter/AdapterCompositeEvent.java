package io.cygnux.rapid.core.adapter;

import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.cygnux.rapid.core.adapter.event.QueryBalance;
import io.cygnux.rapid.core.adapter.event.QueryOrder;
import io.cygnux.rapid.core.adapter.event.QueryPosition;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;
import lombok.Getter;

/**
 * 由[Adaptor]端在统一处理函数中进行处理, 包含当前类型的[Adaptor]需要处理的接入事件和统一的输出事件
 *
 * @param <T>
 */
public abstract class AdapterCompositeEvent<T extends AdapterCompositeEvent<T>> {

    @Getter
    protected boolean isOutboundEvent;

    @Getter
    protected SentEvent sentEvent = new SentEvent();

    protected abstract T self();

    /**
     * @param newOrder NewOrder
     * @return SendEvent
     */
    public T updateWith(NewOrder newOrder) {
        this.isOutboundEvent = true;
        this.sentEvent.updateWith(newOrder);
        return self();
    }

    /**
     * @param cancelOrder CancelOrder
     * @return SendEvent
     */
    public T updateWith(CancelOrder cancelOrder) {
        this.isOutboundEvent = true;
        this.sentEvent.updateWith(cancelOrder);
        return self();
    }

    /**
     * @param queryOrder QueryOrder
     * @return SendEvent
     */
    public T updateWith(QueryOrder queryOrder) {
        this.isOutboundEvent = true;
        this.sentEvent.updateWith(queryOrder);
        return self();
    }

    /**
     * @param queryPosition QueryPositions
     * @return SendEvent
     */
    public T updateWith(QueryPosition queryPosition) {
        this.isOutboundEvent = true;
        this.sentEvent.updateWith(queryPosition);
        return self();
    }

    /**
     * @param queryBalance QueryBalance
     * @return SendEvent
     */
    public T updateWith(QueryBalance queryBalance) {
        this.isOutboundEvent = true;
        this.sentEvent.updateWith(queryBalance);
        return self();
    }

    /**
     * @param subscribeMarketData SubscribeMarketData
     * @return SendEvent
     */
    public T updateWith(SubscribeMarketData subscribeMarketData) {
        this.isOutboundEvent = true;
        this.sentEvent.updateWith(subscribeMarketData);
        return self();
    }

}
