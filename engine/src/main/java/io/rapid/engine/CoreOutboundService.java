package io.rapid.engine;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.adaptor.AdaptorManager;
import io.rapid.core.event.OutboundEvent;
import io.rapid.core.event.OutboundHandler;
import io.rapid.core.event.OutboundEventLoop;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CoreOutboundService implements OutboundHandler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CoreSchedulerService.class);

    @Resource
    private AdaptorManager adaptorManager;

    private final OutboundEventLoop eventLoop = new OutboundEventLoop() {

        @Override
        public void onEvent(OutboundEvent event, long sequence, boolean endOfBatch) {
            log.info("CoreOutboundService process [OutboundEvent] -> {}", event);
            switch (event.getType()) {
                case SUBSCRIBE_MARKET_DATA -> adaptorManager
                        .commitSubscribeMarketData(event.getSubscribeMarketData());
                case NEW_ORDER -> adaptorManager
                        .commitNewOrder(event.getNewOrder());
                case CANCEL_ORDER -> adaptorManager
                        .commitCancelOrder(event.getCancelOrder());
                case QUERY_ORDER -> adaptorManager
                        .commitQueryOrder(event.getQueryOrder());
                case QUERY_POSITIONS -> adaptorManager
                        .commitQueryPositions(event.getQueryPosition());
                case QUERY_BALANCE -> adaptorManager
                        .commitQueryBalance(event.getQueryBalance());
            }
        }
    };

    /**
     * [1].处理行情订阅
     *
     * @param event SubscribeMarketData
     */
    @Override
    public void handleSubscribeMarketData(SubscribeMarketData event) {
        eventLoop.publish(event);
    }

    /**
     * [2].处理新订单
     *
     * @param event NewOrder
     */
    @Override
    public void handleNewOrder(NewOrder event) {
        eventLoop.publish(event);
    }

    /**
     * [3].处理撤单
     *
     * @param event CancelOrder
     */
    @Override
    public void handleCancelOrder(CancelOrder event) {
        eventLoop.publish(event);
    }

    /**
     * [4].处理订单查询
     *
     * @param event QueryOrder
     */
    @Override
    public void handleQueryOrder(QueryOrder event) {
        eventLoop.publish(event);
    }

    /**
     * [5].处理仓位查询
     *
     * @param event QueryPosition
     */
    @Override
    public void handleQueryPosition(QueryPosition event) {
        eventLoop.publish(event);
    }

    /**
     * [6].处理余额查询
     *
     * @param event QueryBalance
     */
    @Override
    public void handleQueryBalance(QueryBalance event) {
        eventLoop.publish(event);
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
