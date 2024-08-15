package io.rapid.core.handler.impl;

import io.rapid.core.upstream.MarketDataFeed;
import io.rapid.core.handler.AdaptorEventHandler;
import io.rapid.core.handler.MarketDataRecorder;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.serializable.avro.inbound.AdaptorEvent;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * MarketDataRecorder base implements
 *
 * @author yellow013
 */
public abstract class BaseMarketDataRecorder implements MarketDataRecorder, AdaptorEventHandler {

    private static final Logger log = getLogger(BaseMarketDataRecorder.class);

    protected final Instrument[] instruments;

    protected final MarketDataFeed dataFeed;

    protected BaseMarketDataRecorder(@Nonnull MarketDataFeed dataFeed,
                                     @Nonnull Instrument... instruments) {
        this.dataFeed = dataFeed;
        this.instruments = instruments;
    }

    @Override
    public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
        log.info("Received event -> {}", event);
        switch (event.getStatus()) {
            case MD_ENABLE -> dataFeed.subscribeMarketData(instruments);
            case MD_DISABLE -> log.info("Agent -> {} market data is disable", dataFeed.getFeedId());
            default -> log.warn("Event no processing, AdaptorEvent -> {}", event);
        }
    }


}

