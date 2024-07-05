package io.rapid.core.mkd;

import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData(
        MutableSet<FastMarketData> marketDataSet) {

}
