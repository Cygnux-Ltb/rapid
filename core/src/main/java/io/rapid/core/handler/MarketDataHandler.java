package io.rapid.core.handler;

import io.rapid.core.event.inbound.RawMarketData;

@FunctionalInterface
public interface MarketDataHandler {

    void onMarketData(final RawMarketData marketData);

}
