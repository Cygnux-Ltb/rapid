package io.rapid.core.handler.impl;

import io.rapid.core.handler.AdaptorEventHandler;
import io.rapid.core.protocol.avro.event.AdaptorEvent;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * Logger implements AdaptorEventHandler
 *
 * @author yellow013
 */
public class AdaptorEventLogger implements AdaptorEventHandler {

    private final Logger log;

    public AdaptorEventLogger(Logger log) {
        this.log = log == null ? getLogger(getClass()) : log;
    }

    @Override
    public void onAdaptorEvent(@Nonnull final AdaptorEvent report) {
        log.info("AdaptorEventLogger -> {}", report);
    }

}
