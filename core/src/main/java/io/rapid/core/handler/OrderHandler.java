package io.rapid.core.handler;

import io.rapid.core.order.Order;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface OrderHandler {

    void onOrder(@Nonnull final Order order);

    /**
     * Logger implements OrderHandler
     *
     * @author yellow013
     */
    class OrderLogger implements OrderHandler {

        private final Logger log;

        public OrderLogger(Logger log) {
            this.log = log == null ? Log4j2LoggerFactory.getLogger(getClass()) : log;
        }

        @Override
        public void onOrder(@Nonnull final Order order) {
            log.info("OrderLogger record -> {}", order);
        }

    }

}
