package io.cygnux.rapid.core.handler.impl;

import io.cygnux.rapid.core.types.event.received.FastMarketData;
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
        this.prev = this.curr;
        this.curr = marketData;
        handleMarketData(this.curr);
    }

    protected abstract void handleMarketData(FastMarketData marketData);

}
