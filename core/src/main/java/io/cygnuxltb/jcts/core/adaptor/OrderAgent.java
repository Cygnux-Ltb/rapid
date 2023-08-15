package io.cygnuxltb.jcts.core.adaptor;

import io.cygnuxltb.jcts.core.account.Account;
import io.cygnuxltb.jcts.core.ser.req.CancelOrder;
import io.cygnuxltb.jcts.core.ser.req.NewOrder;
import io.cygnuxltb.jcts.core.ser.req.QueryBalance;
import io.cygnuxltb.jcts.core.ser.req.QueryOrder;
import io.cygnuxltb.jcts.core.ser.req.QueryPositions;
import io.mercury.common.fsm.Enableable;

import javax.annotation.Nonnull;
import java.io.Closeable;

public interface TraderAgent extends Closeable, Enableable {

    /**
     * @return Account
     */
    @Nonnull
    Account account();

    /**
     * 发送新订单
     *
     * @param request AvNewOrderRequest
     * @return boolean
     */
    boolean newOrder(@Nonnull NewOrder request);

    /**
     * 发送撤单请求
     *
     * @param request AvCancelOrderRequest
     * @return boolean
     */
    boolean cancelOrder(@Nonnull CancelOrder request);

    /**
     * 查询订单
     *
     * @param request AvQueryOrderRequest
     * @return boolean
     */
    boolean queryOrder(@Nonnull QueryOrder request);

    /**
     * 查询持仓
     *
     * @param request AvQueryPositionsRequest
     * @return boolean
     */
    boolean queryPositions(@Nonnull QueryPositions request);

    /**
     * 查询余额
     *
     * @param request AvQueryBalanceRequest
     * @return boolean
     */
    boolean queryBalance(@Nonnull QueryBalance request);

}
