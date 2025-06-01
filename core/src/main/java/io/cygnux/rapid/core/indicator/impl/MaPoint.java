package io.cygnux.rapid.core.indicator.impl;

import io.mercury.common.collections.window.LongRingWindow;
import io.mercury.common.sequence.TimeWindow;
import io.cygnux.rapid.core.indicator.FixedPeriodPoint;
import io.cygnux.rapid.core.instrument.Instrument;

import java.time.Duration;

public abstract class MaPoint extends FixedPeriodPoint {

    protected LongRingWindow historyPriceWindow;

    protected double avgPrice;

    protected double lastPrice;

    protected MaPoint(int index, Instrument instrument, Duration duration, TimeWindow window,
                      LongRingWindow historyPriceWindow) {
        super(index, window);
        this.historyPriceWindow = historyPriceWindow;
    }

    public LongRingWindow getHistoryPriceWindow() {
        return historyPriceWindow;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

}
