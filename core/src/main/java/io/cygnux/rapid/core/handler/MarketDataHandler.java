package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.event.received.FastMarketData;

@FunctionalInterface
public interface MarketDataHandler {

    void onMarketData(final FastMarketData marketData);

}
