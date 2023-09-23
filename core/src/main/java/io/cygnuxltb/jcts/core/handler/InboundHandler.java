package io.cygnuxltb.jcts.core.handler;

import io.cygnuxltb.jcts.core.mkd.FastMarketData;
import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;
import io.cygnuxltb.jcts.core.ser.event.OrderEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.ResourceUtil;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;

/**
 * 处理Adaptor的入站信息接口
 *
 * @author yellow013
 */
public interface InboundHandler extends
        // 行情处理器
        MarketDataHandler,
        // 订单回报处理器
        OrderEventHandler,
        // Adaptor事件处理器
        AdaptorEventHandler,
        // 用于清理资源
        Closeable {

    /**
     * @author yellow013
     */
    final class InboundSchedulerWrapper implements InboundHandler {

        private final MarketDataHandler marketDataHandler;
        private final OrderEventHandler orderEventHandler;
        private final AdaptorEventHandler adaptorEventHandler;

        public InboundSchedulerWrapper(@Nullable MarketDataHandler marketDataHandler,
                                       @Nullable OrderEventHandler orderEventHandler,
                                       @Nullable AdaptorEventHandler adaptorEventHandler) {
            this.marketDataHandler = marketDataHandler;
            this.orderEventHandler = orderEventHandler;
            this.adaptorEventHandler = adaptorEventHandler;
        }

        @Override
        public void onMarketData(@Nonnull FastMarketData marketData) {
            if (marketDataHandler != null) {
                marketDataHandler.onMarketData(marketData);
            }
        }

        @Override
        public void onOrderEvent(@Nonnull OrderEvent event) {
            if (orderEventHandler != null)
                orderEventHandler.onOrderEvent(event);
        }

        @Override
        public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
            if (adaptorEventHandler != null)
                adaptorEventHandler.onAdaptorEvent(event);
        }

        @Override
        public void close() throws IOException {
            ResourceUtil.closeIgnoreException(marketDataHandler,
                    orderEventHandler, adaptorEventHandler);
        }
    }

    /**
     * @author yellow013
     */
    final class InboundSchedulerLogger implements InboundHandler {

        private final Logger log;

        public InboundSchedulerLogger(Logger log) {
            this.log = log == null ? Log4j2LoggerFactory.getLogger(getClass()) : log;
        }

        @Override
        public void onMarketData(@Nonnull FastMarketData marketData) {
            log.info("InboundSchedulerLogger record MarketData -> {}", marketData);
        }

        @Override
        public void onOrderEvent(@Nonnull OrderEvent event) {
            log.info("InboundSchedulerLogger record OrderEvent -> {}", event);
        }

        @Override
        public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
            log.info("InboundSchedulerLogger record AdaptorEvent -> {}", event);
        }

        @Override
        public void close() throws IOException {
            log.info("InboundSchedulerLogger has been closed");
        }

    }

}
