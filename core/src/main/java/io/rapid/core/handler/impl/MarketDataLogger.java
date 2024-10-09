package io.rapid.core.handler.impl;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.handler.MarketDataHandler;
import org.slf4j.Logger;

/**
 * Logger implements MarketDataHandler
 *
 * @author yellow013
 */
public final class MarketDataLogger implements MarketDataHandler {

    private final Logger log;

    public MarketDataLogger(Logger log) {
        this.log = log == null ? Log4j2LoggerFactory.getLogger(MarketDataLogger.class) : log;
    }

    @Override
    public void onMarketData(final RawMarketData marketData) {
        log.info("[RawMarketData] logging -> {}", marketData);
    }

}
