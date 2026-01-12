package io.cygnux.rapid.core.handler.impl;

import io.cygnux.rapid.core.event.received.FastMarketData;
import io.cygnux.rapid.core.handler.MarketDataHandler;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author yellow013
 */
@NotThreadSafe
public abstract class AbstractMarketDataHandler implements MarketDataHandler {

    protected FastMarketData curr;

    protected FastMarketData prev;

    @Override
    public void onMarketData(FastMarketData marketData) {
        this.curr = marketData;
        handleMarketData(marketData);
        this.prev = marketData;
    }

    protected abstract void handleMarketData(FastMarketData marketData);

}
