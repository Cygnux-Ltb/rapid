package io.cygnux.rapid.core.handler.impl;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.core.types.event.received.AdapterReport;
import io.cygnux.rapid.core.handler.AdapterReportHandler;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * Logger implements AdaptorEventHandler
 *
 * @author yellow013
 */
public final class AdapterReportLogger implements AdapterReportHandler {

    private final Logger log;

    public AdapterReportLogger(Logger log) {
        this.log = log == null ? Log4j2LoggerFactory.getLogger(AdapterReportLogger.class) : log;
    }

    @Override
    public void onAdapterReport(@Nonnull final AdapterReport report) {
        log.info("[AdapterStatusReport] logging -> {}", report);
    }

}
