package io.rapid.core.indicator;

import io.mercury.common.sequence.TimeWindow;

/**
 * @author yellow013
 */
public abstract class FixedPeriodPoint extends AbstractPoint {

    protected final TimeWindow window;

    protected FixedPeriodPoint(int index, TimeWindow window) {
        super(index);
        this.window = window;
    }

    public TimeWindow getWindow() {
        return window;
    }

    @Override
    public long orderNum() {
        return window.orderNum();
    }

}
