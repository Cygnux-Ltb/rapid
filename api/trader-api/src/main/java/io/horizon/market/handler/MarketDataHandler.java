package io.horizon.market.handler;

import io.horizon.market.data.MarketData;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@FunctionalInterface
public interface MarketDataHandler<M> {

    void onMarketData(@Nonnull final M m);

    /**
     * @param <M>
     * @author yellow013
     */
    @NotThreadSafe
    abstract class BaseMarketDataHandler<M> implements MarketDataHandler<M> {

        protected M curr;
        protected M prev;

        @Override
        public void onMarketData(@Nonnull M marketData) {
            this.curr = marketData;
            handleMarketData(marketData);
            this.prev = marketData;
        }

        protected abstract void handleMarketData(M marketData);

    }

    /**
     * Logger implements MarketDataHandler
     *
     * @param <M>
     * @author yellow013
     */
    class MarketDataLogger<M extends MarketData> implements MarketDataHandler<M> {

        private final Logger log;

        public MarketDataLogger(Logger log) {
            this.log = log == null ? Log4j2LoggerFactory.getLogger(getClass()) : log;
        }

        @Override
        public void onMarketData(@Nonnull final M marketData) {
            log.info("Received MarketData -> {}", marketData);
        }

    }

}
