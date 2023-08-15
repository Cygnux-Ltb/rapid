package io.cygnuxltb.jcts.core.mkd.impl;

import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData(
        MutableSet<FastMarketData> marketDataSet) {

}
