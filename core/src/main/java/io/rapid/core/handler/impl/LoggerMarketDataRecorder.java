package io.rapid.core.handler.impl;

import io.rapid.core.mkd.MarketDataFeed;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.FastMarketData;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * @author yellow013
 */
public class LoggerMarketDataRecorder extends BaseMarketDataRecorder {

    private final Logger log = getLogger(getClass());

    public LoggerMarketDataRecorder(MarketDataFeed feed, Instrument... instruments) {
        super(feed, instruments);
    }

    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        log.info("LoggerMarketDataRecorder written -> {}", marketData);
    }

    @Override
    public void close() throws IOException {
        log.info("LoggerMarketDataRecorder is closed");
    }

}

