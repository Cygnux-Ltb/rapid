package io.rapid.core.handler;

import io.rapid.core.mkd.FastMarketData;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

@FunctionalInterface
public interface MarketDataHandler {

    void onMarketData(@Nonnull final FastMarketData marketData);

    /**
     * Logger implements MarketDataHandler
     *
     * @author yellow013
     */
    class MarketDataLogger implements MarketDataHandler {

        private final Logger log;

        public MarketDataLogger(Logger log) {
            this.log = log == null ? getLogger(getClass()) : log;
        }

        @Override
        public void onMarketData(@Nonnull final FastMarketData marketData) {
            log.info("Received MarketData -> {}", marketData);
        }

    }

}
