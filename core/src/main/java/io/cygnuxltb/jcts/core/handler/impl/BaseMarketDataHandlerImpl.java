package io.cygnuxltb.jcts.core.handler.impl;

import io.cygnuxltb.jcts.core.handler.MarketDataHandler;
import io.cygnuxltb.jcts.core.mkd.impl.FastMarketData;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * @param <M>
 * @author yellow013
 */
@NotThreadSafe
public abstract class BaseMarketDataHandlerImpl implements MarketDataHandler {

    protected FastMarketData curr;
    protected FastMarketData prev;

    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        this.curr = marketData;
        handleMarketData(marketData);
        this.prev = marketData;
    }

    protected abstract void handleMarketData(FastMarketData marketData);

}
