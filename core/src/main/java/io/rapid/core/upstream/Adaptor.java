package io.rapid.core.adaptor;

import io.mercury.common.state.AvailableTime;
import io.mercury.common.state.Enableable;
import io.mercury.common.state.StartupException;
import io.rapid.core.account.Account;
import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.handler.OrderHandler;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.serializable.avro.request.CancelOrder;
import io.rapid.core.serializable.avro.request.NewOrder;
import io.rapid.core.serializable.avro.request.QueryBalance;
import io.rapid.core.serializable.avro.request.QueryOrder;
import io.rapid.core.serializable.avro.request.QueryPositions;

import javax.annotation.Nonnull;
import java.io.IOException;

public interface Adaptor extends AutoCloseable, Enableable  {

    // ############################## Adaptor状态相关 ############################## //

    /**
     * @return Adaptor ID
     */
    @Nonnull
    String getAdaptorId();

    /**
     * 返回Adaptor[可用时间]对象
     *
     * @return AvailableTime
     */
    AvailableTime getAvailableTime();

    /**
     * Adaptor 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, StartupException;


    // ############################## Adaptor行情相关 ############################## //

    /**
     * 订阅行情
     *
     * @param instruments Instrument[]
     */
    boolean subscribeMarketData(@Nonnull Instrument... instruments);

    /**
     * 添加行情处理器
     *
     * @param handler MarketDataHandler
     * @return Adaptor
     */
    Adaptor addMarketDataHandler(MarketDataHandler handler);


    // ############################## Adaptor交易相关 ############################## //

    /**
     * 获取Adaptor绑定交易账户
     *
     * @return Account
     */
    @Nonnull
    Account getBoundAccount();

    /**
     * 发送订单请求
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
     * 添加订单处理器
     *
     * @param handler OrderHandler
     * @return Adaptor
     */
    Adaptor addOrderHandler(OrderHandler handler);

}
