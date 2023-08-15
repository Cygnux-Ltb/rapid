package io.cygnuxltb.jcts.core.handler.impl;

import io.cygnuxltb.jcts.core.adaptor.Adaptor;
import io.cygnuxltb.jcts.core.handler.MarketDataRecorder;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;

public   /**
 * @author yellow013
 */
class LoggerMarketDataRecorder extends MarketDataRecorder.BaseMarketDataRecorder<BasicMarketData> {

    private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

    public LoggerMarketDataRecorder(Adaptor adaptor, Instrument... instruments) {
        super(adaptor, instruments);
    }

    @Override
    public void onMarketData(@Nonnull BasicMarketData marketData) {
        log.info("LoggerMarketDataRecorder written -> {}", marketData);
    }

    @Override
    public void close() throws IOException {
        log.info("LoggerMarketDataRecorder is closed");
    }

}

