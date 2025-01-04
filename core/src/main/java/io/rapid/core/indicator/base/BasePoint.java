package io.rapid.core.indicator.base;

import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.lang.Asserter;
import io.rapid.core.indicator.Point;
import io.rapid.core.mdata.SavedMarketData;

/**
 * @author yellow013
 */
public abstract class BasePoint implements Point {

    protected final int index;

    protected SavedMarketData preMarketData;

    protected BasePoint(int index) {
        Asserter.greaterThan(index, -1, "index");
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public SavedMarketData getPreMarketData() {
        return preMarketData;
    }

    public void onMarketData(SavedMarketData marketData) {
        handleMarketData0(marketData);
        updatePreMarketData(marketData);
    }

    public void updatePreMarketData(SavedMarketData marketData) {
        this.preMarketData = marketData;
    }

    @AbstractFunction
    protected abstract void handleMarketData0(SavedMarketData marketData);

    @Override
    public int compareTo(Point o) {
        return Integer.compare(index, o.getIndex());
    }

}
