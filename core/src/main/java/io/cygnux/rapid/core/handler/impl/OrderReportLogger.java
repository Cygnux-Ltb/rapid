package io.cygnux.rapid.core.handler.impl;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.handler.OrderReportHandler;
import org.slf4j.Logger;

import javax.annotation.Nonnull;


/**
 * Logger implements AdaptorEventHandler
 *
 * @author yellow013
 */
public class OrderReportLogger implements OrderReportHandler {

    private final Logger log;

    public OrderReportLogger(Logger log) {
        this.log = log == null ? Log4j2LoggerFactory.getLogger(OrderReportLogger.class) : log;
    }

    public void onOrderReport(@Nonnull final OrderReport event) {
        log.info("[OrderReport] logging -> {}", event);
    }

}
