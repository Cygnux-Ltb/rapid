package io.rapid.core.adaptor;

import io.mercury.common.lang.exception.ComponentStartupException;
import io.rapid.core.account.Account;
import io.rapid.core.handler.OrderHandler;
import io.rapid.core.protocol.avro.request.CancelOrder;
import io.rapid.core.protocol.avro.request.NewOrder;
import io.rapid.core.protocol.avro.request.QueryBalance;
import io.rapid.core.protocol.avro.request.QueryOrder;
import io.rapid.core.protocol.avro.request.QueryPositions;

import javax.annotation.Nonnull;
import java.io.IOException;

public interface TraderAdaptor {

    /**
     * @return Account
     */
    @Nonnull
    Account account();

    /**
     * 发送新订单
     *
     * @param order NewOrder
     * @return boolean
     */
    boolean newOrder(@Nonnull NewOrder order);

    /**
     * 发送撤单请求
     *
     * @param order CancelOrder
     * @return boolean
     */
    boolean cancelOrder(@Nonnull CancelOrder order);

    /**
     * 查询订单
     *
     * @param query QueryOrder
     * @return boolean
     */
    boolean queryOrder(@Nonnull QueryOrder query);

    /**
     * 查询持仓
     *
     * @param query QueryPositions
     * @return boolean
     */
    boolean queryPositions(@Nonnull QueryPositions query);

    /**
     * 查询余额
     *
     * @param query QueryBalance
     * @return boolean
     */
    boolean queryBalance(@Nonnull QueryBalance query);


    /**
     * @return Adaptor ID
     */
    @Nonnull
    String getAdaptorId();

    /**
     * Adaptor 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, ComponentStartupException;

    /**
     * @param handler OrderHandler
     * @return TraderAdaptor
     */
    TraderAdaptor setOrderHandler(OrderHandler handler);

}
