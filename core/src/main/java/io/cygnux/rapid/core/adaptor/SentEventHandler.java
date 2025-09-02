package io.cygnux.rapid.core.adaptor;

import com.lmax.disruptor.EventHandler;
import io.mercury.common.log4j2.StaticLogger;
import io.cygnux.rapid.core.adaptor.event.CancelOrder;
import io.cygnux.rapid.core.adaptor.event.NewOrder;
import io.cygnux.rapid.core.adaptor.event.QueryBalance;
import io.cygnux.rapid.core.adaptor.event.QueryOrder;
import io.cygnux.rapid.core.adaptor.event.QueryPosition;
import io.cygnux.rapid.core.adaptor.event.SubscribeMarketData;

import java.io.Closeable;

/**
 * 处理出站信息接口
 *
 * @author yellow013
 */
public interface SentEventHandler extends EventHandler<SentEvent>,
        // 用于清理资源
        Closeable {

    @Override
    default void onEvent(SentEvent event, long sequence, boolean endOfBatch) throws Exception {
        switch (event.getType()) {
            case NEW_ORDER -> handleNewOrder(event.getNewOrder());
            case CANCEL_ORDER -> handleCancelOrder(event.getCancelOrder());
            case QUERY_ORDER -> handleQueryOrder(event.getQueryOrder());
            case QUERY_POSITIONS -> handleQueryPosition(event.getQueryPosition());
            case QUERY_BALANCE -> handleQueryBalance(event.getQueryBalance());
            case SUBSCRIBE_MARKET_DATA -> handleSubscribeMarketData(event.getSubscribeMarketData());
            case null, default ->
                    StaticLogger.error("NOTE OutboundHandler::onEvent, event -> {}, sequence==[{}], endOfBatch==[{}]",
                            event, sequence, endOfBatch);
        }
    }

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
