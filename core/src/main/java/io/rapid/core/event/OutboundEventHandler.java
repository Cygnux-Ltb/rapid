package io.rapid.core.event;

import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;

import java.io.Closeable;

/**
 * 处理出站信息接口
 *
 * @author yellow013
 */
public interface OutboundEventHandler extends
        // 用于清理资源
        Closeable {

    /**
     * [1].处理行情订阅
     *
     * @param event SubscribeMarketData
     */
    void handleSubscribeMarketData(SubscribeMarketData event);

    /**
     * [2].处理新订单
     *
     * @param event NewOrder
     */
    void handleNewOrder(NewOrder event);

    /**
     * [3].处理撤单
     *
     * @param event CancelOrder
     */
    void handleCancelOrder(CancelOrder event);

    /**
     * [4].处理订单查询
     *
     * @param event QueryOrder
     */
    void handleQueryOrder(QueryOrder event);

    /**
     * [5].处理仓位查询
     *
     * @param event QueryPosition
     */
    void handleQueryPosition(QueryPosition event);

    /**
     * [6].处理余额查询
     *
     * @param event QueryBalance
     */
    void handleQueryBalance(QueryBalance event);

}
