package io.rapid.core.mkd;

import io.rapid.core.adaptor.Adaptor;
import io.rapid.core.handler.OrderHandler;
import io.rapid.core.serializable.avro.request.CancelOrder;
import io.rapid.core.serializable.avro.request.NewOrder;
import io.rapid.core.serializable.avro.request.QueryBalance;
import io.rapid.core.serializable.avro.request.QueryOrder;
import io.rapid.core.serializable.avro.request.QueryPositions;

import javax.annotation.Nonnull;

public interface MarketDataFeed extends Adaptor {

    String getFeedId();

    /**
     * @return Adaptor ID
     */
    @Nonnull
    @Override
    default String getAdaptorId() {
        return getFeedId();
    }

    /**
     * 发送订单请求
     *
     * @param order NewOrder
     * @return boolean
     */
    @Override
    default boolean newOrder(@Nonnull NewOrder order) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> newOrder");
    }

    /**
     * 发送撤单请求
     *
     * @param order CancelOrder
     * @return boolean
     */
    @Override
    default boolean cancelOrder(@Nonnull CancelOrder order) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> cancelOrder");
    }

    /**
     * 查询订单
     *
     * @param query QueryOrder
     * @return boolean
     */
    @Override
    default boolean queryOrder(@Nonnull QueryOrder query) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> queryOrder");
    }

    /**
     * 查询持仓
     *
     * @param query QueryPositions
     * @return boolean
     */
    @Override
    default boolean queryPositions(@Nonnull QueryPositions query) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> queryPositions");
    }

    /**
     * 查询余额
     *
     * @param query QueryBalance
     * @return boolean
     */
    @Override
    default boolean queryBalance(@Nonnull QueryBalance query) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> queryBalance");
    }

    /**
     * 添加订单处理器
     *
     * @param handler OrderHandler
     * @return TraderAdaptor
     */
    @Override
    default MarketDataFeed addOrderHandler(OrderHandler handler) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> addOrderHandler");
    }
    
}
