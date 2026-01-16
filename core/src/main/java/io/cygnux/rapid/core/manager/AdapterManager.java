package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.adapter.Adapter;
import io.cygnux.rapid.core.adapter.AdapterStatus;
import io.cygnux.rapid.core.types.adapter.event.CancelOrder;
import io.cygnux.rapid.core.types.adapter.event.NewOrder;
import io.cygnux.rapid.core.types.adapter.event.QueryBalance;
import io.cygnux.rapid.core.types.adapter.event.QueryOrder;
import io.cygnux.rapid.core.types.adapter.event.QueryPosition;
import io.cygnux.rapid.core.types.adapter.event.SubscribeMarketData;
import io.cygnux.rapid.core.event.SharedEventHandler;
import io.cygnux.rapid.core.types.event.received.AdapterReport;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.io.Closeable;

@ThreadSafe
public interface AdapterManager extends Closeable, SharedEventHandler {

    void onAdapterReport(AdapterReport report);

    @Override
    default void fireAdapterReport(AdapterReport report) {
        onAdapterReport(report);
    }

    boolean isClosed();

    void putAdapter(@Nonnull Adapter... adapters);

    Adapter getAdapter(int accountId);

    Adapter getAdapter(String adapterId);

    default AdapterStatus getCurrentStatus(int accountId) {
        return getAdapter(accountId).currentStatus();
    }

    default AdapterStatus getCurrentStatus(String adapterId) {
        return getAdapter(adapterId).currentStatus();
    }

    /**
     * @param event AdaptorReport
     */
    void onAdapterEvent(AdapterReport event);

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
