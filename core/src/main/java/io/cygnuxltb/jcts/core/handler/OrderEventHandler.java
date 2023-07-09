package io.cygnuxltb.jcts.core.handler;

import io.cygnuxltb.jcts.core.serialization.avro.receive.AvroOrderEvent;
import io.horizon.trader.serialization.avro.receive.AvroOrderEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface OrderEventHandler {

    void onOrderEvent(@Nonnull final AvroOrderEvent event);

    /**
     * Logger implements AdaptorEventHandler
     *
     * @author yellow013
     */
    class OrderEventLogger implements OrderEventHandler {

        private final Logger log;

        public OrderEventLogger(Logger log) {
            this.log = log == null ? Log4j2LoggerFactory.getLogger(getClass()) : log;
        }

        public void onOrderEvent(@Nonnull final AvroOrderEvent event) {
            log.info("OrderEventLogger -> {}", event);
        }

    }

}
