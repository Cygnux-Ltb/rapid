package io.cygnuxltb.jcts.core.handler.impl;

import io.cygnuxltb.jcts.core.adaptor.MarketDataFeed;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.FastMarketData;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * @author yellow013
 */
public class LoggerMarketDataRecorder extends BaseMarketDataRecorder {

    private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

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

