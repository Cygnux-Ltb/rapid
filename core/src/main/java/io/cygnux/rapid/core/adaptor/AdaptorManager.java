package io.cygnux.rapid.core.adaptor;

import io.cygnux.rapid.core.event.inbound.AdaptorReport;
import io.cygnux.rapid.core.event.outbound.CancelOrder;
import io.cygnux.rapid.core.event.outbound.NewOrder;
import io.cygnux.rapid.core.event.outbound.QueryBalance;
import io.cygnux.rapid.core.event.outbound.QueryOrder;
import io.cygnux.rapid.core.event.outbound.QueryPosition;
import io.cygnux.rapid.core.event.outbound.SubscribeMarketData;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.io.Closeable;

@ThreadSafe
public sealed interface AdaptorManager extends Closeable permits AbstractAdaptorManager {

    boolean isClosed();

    void putAdaptor(@Nonnull Adaptor... adaptors);

    Adaptor getAdaptor(int accountId);

    Adaptor getAdaptor(String adaptorId);

    default AdaptorStatus getCurrentStatus(int accountId) {
        return getAdaptor(accountId).currentStatus();
    }

    default AdaptorStatus getCurrentStatus(String adaptorId) {
        return getAdaptor(adaptorId).currentStatus();
    }

    /**
     * @param event AdaptorReport
     */
    void onAdaptorEvent(AdaptorReport event);

    /**
     * 提交行情订阅
     *
     * @param subscribeMarketData SubscribeMarketData
     */
    boolean commitSubscribeMarketData(SubscribeMarketData subscribeMarketData);

    /**
     * 提交订单请求
     *
     * @param order NewOrder
     */
    boolean commitNewOrder(NewOrder order);

    /**
     * 提交撤单请求
     *
     * @param order CancelOrder
     */
    boolean commitCancelOrder(CancelOrder order);

    /**
     * 提交查询订单请求
     *
     * @param query QueryOrder
     */
    boolean commitQueryOrder(QueryOrder query);

    /**
     * 提交查询持仓请求
     *
     * @param query QueryPositions
     */
    boolean commitQueryPositions(QueryPosition query);

    /**
     * 提交查询余额请求
     *
     * @param query QueryBalance
     */
    boolean commitQueryBalance(QueryBalance query);

}
