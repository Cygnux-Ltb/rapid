package io.cygnux.rapid.core.types.mkd;

import io.cygnux.rapid.core.types.event.received.FastMarketData;
import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData(
        MutableSet<FastMarketData> marketDataSet
) {
}
