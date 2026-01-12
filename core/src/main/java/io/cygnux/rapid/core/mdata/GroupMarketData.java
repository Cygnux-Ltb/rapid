package io.cygnux.rapid.core.mdata;

import io.cygnux.rapid.core.event.received.FastMarketData;
import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData(
        MutableSet<FastMarketData> marketDataSet
) {
}
