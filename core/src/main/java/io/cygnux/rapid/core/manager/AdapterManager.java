package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.adapter.Adapter;
import io.cygnux.rapid.core.adapter.AdapterStatus;
import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.cygnux.rapid.core.adapter.event.QueryBalance;
import io.cygnux.rapid.core.adapter.event.QueryOrder;
import io.cygnux.rapid.core.adapter.event.QueryPosition;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;
import io.cygnux.rapid.core.event.SharedEventHandler;
import io.cygnux.rapid.core.event.received.AdapterStatusReport;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.io.Closeable;

@ThreadSafe
public interface AdapterManager extends Closeable, SharedEventHandler {

    void onAdapterReport(AdapterStatusReport report);

    @Override
    default void fireAdapterReport(AdapterStatusReport report) {
        onAdapterReport(report);
    }

    boolean isClosed();

    void putAdapter(@Nonnull Adapter... adaptors);

    Adapter getAdapter(int accountId);

    Adapter getAdapter(String adaptorId);

    default AdapterStatus getCurrentStatus(int accountId) {
        return getAdapter(accountId).currentStatus();
    }

    default AdapterStatus getCurrentStatus(String adaptorId) {
        return getAdapter(adaptorId).currentStatus();
    }

    /**
     * @param event AdaptorReport
     */
    void onAdapterEvent(AdapterStatusReport event);

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
