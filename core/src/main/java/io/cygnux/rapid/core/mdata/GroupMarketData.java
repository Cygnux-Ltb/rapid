package io.cygnux.rapid.core.mdata;

import io.cygnux.rapid.core.event.inbound.MarketDataReport;
import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData(
        MutableSet<MarketDataReport> marketDataSet
) {
}
