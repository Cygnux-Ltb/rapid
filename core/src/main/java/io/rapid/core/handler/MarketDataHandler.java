package io.rapid.core.handler;

import io.rapid.core.event.inbound.MarketDataReport;

@FunctionalInterface
public interface MarketDataHandler {

    void onMarketData(final MarketDataReport marketData);

}
