package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.shared.event.FastMarketData;

@FunctionalInterface
public interface MarketDataHandler {

    void onMarketData(final FastMarketData marketData);

}
