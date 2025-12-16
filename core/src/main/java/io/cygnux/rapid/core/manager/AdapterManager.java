package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.adapter.Adapter;
import io.cygnux.rapid.core.adapter.AdapterStatus;
import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.cygnux.rapid.core.adapter.event.QueryBalance;
import io.cygnux.rapid.core.adapter.event.QueryOrder;
import io.cygnux.rapid.core.adapter.event.QueryPosition;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;
import io.cygnux.rapid.core.shared.SharedEventHandler;
import io.cygnux.rapid.core.shared.event.AdapterReport;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.io.Closeable;

@ThreadSafe
public interface AdapterManager extends Closeable, SharedEventHandler {

    void onAdaptorReport(AdapterReport report);

    @Override
    default void fireAdaptorReport(AdapterReport report) {
        onAdaptorReport(report);
    }

    boolean isClosed();

    void putAdaptor(@Nonnull Adapter... adaptors);

    Adapter getAdaptor(int accountId);

    Adapter getAdaptor(String adaptorId);

    default AdapterStatus getCurrentStatus(int accountId) {
        return getAdaptor(accountId).currentStatus();
    }

    default AdapterStatus getCurrentStatus(String adaptorId) {
        return getAdaptor(adaptorId).currentStatus();
    }

    /**
     * @param event AdaptorReport
     */
    void onAdaptorEvent(AdapterReport event);

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
