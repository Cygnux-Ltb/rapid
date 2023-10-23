package io.cygnuxltb.jcts.core.handler.impl;

import io.cygnuxltb.jcts.core.handler.OrderEventHandler;
import io.cygnuxltb.jcts.core.ser.event.OrderEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * Logger implements AdaptorEventHandler
 *
 * @author yellow013
 */
public class OrderEventLogger implements OrderEventHandler {

    private final Logger log;

    public OrderEventLogger(Logger log) {
        this.log = log == null ? Log4j2LoggerFactory.getLogger(getClass()) : log;
    }

    public void onOrderEvent(@Nonnull final OrderEvent event) {
        log.info("OrderEventLogger -> {}", event);
    }

}
