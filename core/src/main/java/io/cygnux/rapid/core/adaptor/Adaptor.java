package io.cygnux.rapid.core.adaptor;

import io.mercury.common.state.Enableable;
import io.mercury.common.state.StartupException;
import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.shared.enums.AdaptorType;
import io.cygnux.rapid.core.adaptor.event.CancelOrder;
import io.cygnux.rapid.core.adaptor.event.NewOrder;
import io.cygnux.rapid.core.adaptor.event.QueryBalance;
import io.cygnux.rapid.core.adaptor.event.QueryOrder;
import io.cygnux.rapid.core.adaptor.event.QueryPosition;
import io.cygnux.rapid.core.adaptor.event.SubscribeMarketData;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.IOException;

public sealed interface Adaptor extends Closeable, Enableable
        permits AbstractAdaptor {

    /**
     * Adaptor 消息发布URI
     *
     * @return String
     */
    static String publishPath() {
        return "adaptor/pub";
    }

    /**
     * Adaptor 消息接收URI
     *
     * @return String
     */
    static String subscribePath() {
        return "adaptor/sub";
    }

    /**
     * Adaptor 接收消息使用的Topic, 调用[Account::getTopic]
     *
     * @return String
     */
    default String subscribeTopic() {
        var account = getBoundAccount();
        return account.getTopic();
    }

    // ############################## 状态相关 ############################## //

    String getAdaptorId();

    /**
     * 获取Adaptor绑定交易账户
     *
     * @return Account
     */
    @Nonnull
    Account getBoundAccount();


    /**
     * 更新Adaptor状态
     *
     * @param adaptorType AdaptorStatus
     */
    void updateStatus(AdaptorType adaptorType, boolean isEnabled);

    /**
     * 获取当前Adaptor状态
     *
     * @return AdaptorStatus
     */
    AdaptorStatus currentStatus();

    /**
     * Adaptor 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, StartupException;

    // ############################## 行情相关 ############################## //

    /**
     * 订阅行情
     *
     * @param subscribeMarketData SubscribeMarketData
     */
    boolean subscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData);

    default String getMarketDataTopic() {
        return getBoundAccount().getTopicByMd();
    }

    // ############################## 交易相关 ############################## //

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

    default boolean queryOrder() {
        return queryOrder(getBoundAccount().newQueryOrder());
    }

    /**
     * 查询持仓
     *
     * @param query QueryPositions
     * @return boolean
     */
    boolean queryPosition(@Nonnull QueryPosition query);

    default boolean queryPosition() {
        return queryPosition(getBoundAccount().newQueryPosition());
    }

    /**
     * 查询余额
     *
     * @param query QueryBalance
     * @return boolean
     */
    boolean queryBalance(@Nonnull QueryBalance query);

    default boolean queryBalance() {
        return queryBalance(getBoundAccount().newQueryBalance());
    }

    default String getTraderTopic() {
        return getBoundAccount().getTopicByTd();
    }

}
