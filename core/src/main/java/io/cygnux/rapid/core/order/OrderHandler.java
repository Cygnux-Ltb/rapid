package io.cygnux.rapid.core.order;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 订单处理器
 */
@FunctionalInterface
public interface OrderHandler {

    void onOrder(@Nonnull final io.cygnux.rapid.core.types.order.Order order);

    /**
     * Logger implements OrderHandler
     *
     * @author yellow013
     */
    class OrderLogger implements OrderHandler {

        private final Logger log;

        public OrderLogger() {
            this(null);
        }

        public OrderLogger(@Nullable Logger log) {
            this.log = log == null ? Log4j2LoggerFactory.getLogger(getClass()) : log;
        }

        @Override
        public void onOrder(@Nonnull final io.cygnux.rapid.core.types.order.Order order) {
            log.info("OrderLogger record -> {}", order);
        }

    }

}
