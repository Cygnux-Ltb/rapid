package io.rapid.core.mdata;

import io.rapid.core.event.inbound.RawMarketData;
import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData(
        MutableSet<RawMarketData> marketDataSet) {

}
