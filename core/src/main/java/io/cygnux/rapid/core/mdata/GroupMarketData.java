package io.cygnux.rapid.core.mdata;

import io.cygnux.rapid.core.stream.event.FastMarketData;
import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData(
        MutableSet<FastMarketData> marketDataSet
) {
}
