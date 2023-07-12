package io.cygnuxltb.jcts.core.mkd.impl;

import io.cygnuxltb.jcts.core.mkd.MarketData;
import org.eclipse.collections.api.set.MutableSet;

public record GroupMarketData<M extends MarketData>(
        MutableSet<M> marketDataSet) {

}
