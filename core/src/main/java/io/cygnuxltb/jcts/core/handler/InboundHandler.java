package io.cygnuxltb.jcts.core.trader.handler;

import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.handler.MarketDataHandler;
import io.cygnuxltb.jcts.core.trader.serialization.avro.receive.AvroAdaptorEvent;
import io.cygnuxltb.jcts.core.trader.serialization.avro.receive.AvroOrderEvent;
import io.horizon.trader.serialization.avro.receive.AvroAdaptorEvent;
import io.horizon.trader.serialization.avro.receive.AvroOrderEvent;
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
 * @param <M>
 * @author yellow013
 */
public interface InboundHandler<M extends MarketData> extends
        // 行情处理器
        MarketDataHandler<M>,
        // 订单回报处理器
        OrderEventHandler,
        // Adaptor事件处理器
        AdaptorEventHandler,
        // 用于清理资源
        Closeable {

    /**
     * @param <M>
     * @author yellow013
     */
    final class InboundSchedulerWrapper<M extends MarketData> implements InboundHandler<M> {

        private final MarketDataHandler<M> marketDataHandler;
        private final OrderEventHandler orderEventHandler;
        private final AdaptorEventHandler adaptorEventHandler;

        private final Logger logger;

        public InboundSchedulerWrapper(@Nullable MarketDataHandler<M> marketDataHandler,
                                       @Nullable OrderEventHandler orderEventHandler,
                                       @Nullable AdaptorEventHandler adaptorEventHandler,
                                       @Nullable Logger logger) {
            this.marketDataHandler = marketDataHandler;
            this.orderEventHandler = orderEventHandler;
            this.adaptorEventHandler = adaptorEventHandler;
            this.logger = logger;
        }

        @Override
        public void onMarketData(@Nonnull M marketData) {
            if (marketDataHandler != null) {
                marketDataHandler.onMarketData(marketData);
            }
        }

        @Override
        public void onOrderEvent(@Nonnull AvroOrderEvent event) {
            if (orderEventHandler != null)
                orderEventHandler.onOrderEvent(event);
        }

        @Override
        public void onAdaptorEvent(@Nonnull AvroAdaptorEvent event) {
            if (adaptorEventHandler != null)
                adaptorEventHandler.onAdaptorEvent(event);
        }

        @Override
        public void close() throws IOException {
            try {
                ResourceUtil.close(marketDataHandler);
            } catch (Exception e) {
                if (marketDataHandler != null) {
                    if (logger != null) {
                        logger.error("Close MarketDataHandler -> {} throw {}",
                                marketDataHandler.getClass().getSimpleName(),
                                e.getClass().getSimpleName(), e);
                    }
                }
            }
            if (orderEventHandler != null) {
                try {
                    ResourceUtil.close(orderEventHandler);
                } catch (Exception e) {
                    if (logger != null)
                        logger.error("Close OrderReportHandler -> {} throw {}",
                                orderEventHandler.getClass().getSimpleName(),
                                e.getClass().getSimpleName(), e);
                }
            }
            if (adaptorEventHandler != null) {
                try {
                    ResourceUtil.close(adaptorEventHandler);
                } catch (Exception e) {
                    if (logger != null)
                        logger.error("Close AdaptorReportHandler -> {} throw {}",
                                adaptorEventHandler.getClass().getSimpleName(),
                                e.getClass().getSimpleName(), e);
                }
            }
        }
    }

    /**
     * @param <M>
     * @author yellow013
     */
    final class InboundSchedulerLogger<M extends MarketData> implements InboundHandler<M> {

        private final Logger log;

        public InboundSchedulerLogger(Logger log) {
            this.log = log == null ? Log4j2LoggerFactory.getLogger(getClass()) : log;
        }

        @Override
        public void onMarketData(@Nonnull M marketData) {
            log.info("InboundSchedulerLogger record marketData -> {}", marketData);
        }

        @Override
        public void onOrderEvent(@Nonnull AvroOrderEvent event) {
            log.info("InboundSchedulerLogger record orderReport -> {}", event);
        }

        @Override
        public void onAdaptorEvent(@Nonnull AvroAdaptorEvent report) {
            log.info("InboundSchedulerLogger record adaptorReport -> {}", report);
        }

        @Override
        public void close() throws IOException {
            log.info("InboundSchedulerLogger has been closed");
        }

    }

}
