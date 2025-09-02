package io.cygnux.rapid.core.handler.impl;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.core.stream.event.AdaptorReport;
import io.cygnux.rapid.core.handler.AdaptorReportHandler;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * Logger implements AdaptorEventHandler
 *
 * @author yellow013
 */
public final class AdaptorReportLogger implements AdaptorReportHandler {

    private final Logger log;

    public AdaptorReportLogger(Logger log) {
        this.log = log == null ? Log4j2LoggerFactory.getLogger(AdaptorReportLogger.class) : log;
    }

    @Override
    public void onAdaptorReport(@Nonnull final AdaptorReport report) {
        log.info("[AdaptorReport] logging -> {}", report);
    }

}
