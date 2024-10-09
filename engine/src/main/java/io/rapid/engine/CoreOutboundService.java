package io.rapid.engine;

import io.rapid.core.adaptor.AdaptorManager;
import io.rapid.core.event.OutboundEventHandler;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OutboundService implements OutboundEventHandler {

    @Resource
    private AdaptorManager adaptorManager;

    /**
     * [1].处理行情订阅
     *
     * @param event SubscribeMarketData
     */
    @Override
    public void handleSubscribeMarketData(SubscribeMarketData event) {
        adaptorManager.commitSubscribeMarketData(event);
    }

    /**
     * [2].处理新订单
     *
     * @param event NewOrder
     */
    @Override
    public void handleNewOrder(NewOrder event) {
        adaptorManager.commitNewOrder(event);
    }

    /**
     * [3].处理撤单
     *
     * @param event CancelOrder
     */
    @Override
    public void handleCancelOrder(CancelOrder event) {
        adaptorManager.commitCancelOrder(event);
    }

    /**
     * [4].处理订单查询
     *
     * @param event QueryOrder
     */
    @Override
    public void handleQueryOrder(QueryOrder event) {
        adaptorManager.commitQueryOrder(event);
    }

    /**
     * [5].处理仓位查询
     *
     * @param event QueryPosition
     */
    @Override
    public void handleQueryPosition(QueryPosition event) {
        adaptorManager.commitQueryPositions(event);
    }

    /**
     * [6].处理余额查询
     *
     * @param event QueryBalance
     */
    @Override
    public void handleQueryBalance(QueryBalance event) {
        adaptorManager.commitQueryBalance(event);
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        // TODO 资源清理
    }

}
