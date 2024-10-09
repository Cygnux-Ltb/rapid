package io.rapid.core.handler.impl;

import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.handler.MarketDataHandler;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author yellow013
 */
@NotThreadSafe
public abstract class AbstractMarketDataHandler implements MarketDataHandler {

    protected RawMarketData curr;

    protected RawMarketData prev;

    @Override
    public void onMarketData(RawMarketData marketData) {
        this.curr = marketData;
        handleMarketData(marketData);
        this.prev = marketData;
    }

    protected abstract void handleMarketData(RawMarketData marketData);

}
