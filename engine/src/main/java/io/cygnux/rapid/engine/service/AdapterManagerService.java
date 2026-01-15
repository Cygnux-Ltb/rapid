package io.cygnux.rapid.engine.service;

import io.cygnux.rapid.core.adapter.AbstractAdapterManager;
import io.cygnux.rapid.core.types.event.received.AdapterReport;
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
public final class AdapterManagerService extends AbstractAdapterManager {

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(AdapterManagerService.class);

    @PostConstruct
    public void init() {
        log.info("AdaptorManagerService init...");
    }


    @Override
    public void onAdapterReport(AdapterReport report) {

    }

}
