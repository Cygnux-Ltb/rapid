package io.cygnuxltb.jcts.core.adaptor;

import io.cygnuxltb.jcts.core.account.Account;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvCancelOrderRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvNewOrderRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvQueryBalanceRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvQueryOrderRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvQueryPositionsRequest;
import io.mercury.common.fsm.Enableable;
import io.mercury.common.lang.exception.ComponentStartupException;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.IOException;

public interface TraderAdaptor extends Closeable, Enableable {

    /**
     * @return Adaptor ID
     */
    @Nonnull
    String getAdaptorId();

    /**
     * @return Account
     */
    @Nonnull
    Account getAccount();

    /**
     * Adaptor 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, ComponentStartupException;

    /**
     * 发送新订单
     *
     * @param request AvNewOrderRequest
     * @return boolean
     */
    boolean newOrder(@Nonnull AvNewOrderRequest request);

    /**
     * 发送撤单请求
     *
     * @param request AvCancelOrderRequest
     * @return boolean
     */
    boolean cancelOrder(@Nonnull AvCancelOrderRequest request);

    /**
     * 查询订单
     *
     * @param request AvQueryOrderRequest
     * @return boolean
     */
    boolean queryOrder(@Nonnull AvQueryOrderRequest request);

    /**
     * 查询持仓
     *
     * @param request AvQueryPositionsRequest
     * @return boolean
     */
    boolean queryPositions(@Nonnull AvQueryPositionsRequest request);

    /**
     * 查询余额
     *
     * @param request AvQueryBalanceRequest
     * @return boolean
     */
    boolean queryBalance(@Nonnull AvQueryBalanceRequest request);

}
