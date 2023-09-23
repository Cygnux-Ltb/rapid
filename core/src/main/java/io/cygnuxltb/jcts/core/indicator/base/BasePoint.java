package io.cygnuxltb.jcts.core.indicator.base;

import io.cygnuxltb.jcts.core.indicator.Point;
import io.cygnuxltb.jcts.core.mkd.FastMarketData;
import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.lang.Asserter;

/**
 * @author yellow013
 */
public abstract class BasePoint implements Point {

    protected final int index;

    protected FastMarketData preMarketData;

    protected BasePoint(int index) {
        Asserter.greaterThan(index, -1, "index");
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public FastMarketData getPreMarketData() {
        return preMarketData;
    }

    public void handleMarketData(FastMarketData marketData) {
        handleMarketData0(marketData);
        updatePreMarketData(marketData);
    }

    public void updatePreMarketData(FastMarketData marketData) {
        this.preMarketData = marketData;
    }

    @AbstractFunction
    protected abstract void handleMarketData0(FastMarketData marketData);

    @Override
    public int compareTo(Point o) {
        return Integer.compare(index, o.getIndex());
    }

}
