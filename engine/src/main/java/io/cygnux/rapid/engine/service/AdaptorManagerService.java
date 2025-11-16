package io.cygnux.rapid.engine.service;

import io.cygnux.rapid.core.adaptor.AbstractAdaptorManager;
import io.cygnux.rapid.core.shared.event.AdaptorReport;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author yellow013
 */
@NotThreadSafe
@Service
public final class AdaptorManagerService extends AbstractAdaptorManager  {

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(AdaptorManagerService.class);

    @PostConstruct
    public void init() {
        log.info("AdaptorManagerService init...");
    }


    @Override
    public void onAdaptorReport(AdaptorReport report) {

    }

}
