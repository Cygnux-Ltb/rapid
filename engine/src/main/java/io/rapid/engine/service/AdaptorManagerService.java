package io.rapid.engine.service;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.adaptor.AbstractAdaptorManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author yellow013
 */
@NotThreadSafe
@Service
public final class AdaptorManagerService extends AbstractAdaptorManager implements Serializable {

    @Serial
    private static final long serialVersionUID = -1199809125474119945L;

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(AdaptorManagerService.class);


    public AdaptorManagerService() {
    }

    @PostConstruct
    public void init() {
        log.info("AdaptorManagerService init...");
    }


}
