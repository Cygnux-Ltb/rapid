package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.event.inbound.MarketDataReport;

@FunctionalInterface
public interface MarketDataHandler {

    void onMarketData(final MarketDataReport marketData);

}
