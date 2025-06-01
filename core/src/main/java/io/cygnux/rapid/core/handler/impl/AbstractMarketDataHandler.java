package io.cygnux.rapid.core.handler.impl;

import io.cygnux.rapid.core.event.inbound.MarketDataReport;
import io.cygnux.rapid.core.handler.MarketDataHandler;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author yellow013
 */
@NotThreadSafe
public abstract class AbstractMarketDataHandler implements MarketDataHandler {

    protected MarketDataReport curr;

    protected MarketDataReport prev;

    @Override
    public void onMarketData(MarketDataReport marketData) {
        this.curr = marketData;
        handleMarketData(marketData);
        this.prev = marketData;
    }

    protected abstract void handleMarketData(MarketDataReport marketData);

}
