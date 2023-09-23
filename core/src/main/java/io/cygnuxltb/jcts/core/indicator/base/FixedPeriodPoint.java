package io.cygnuxltb.jcts.core.indicator.base;

import io.mercury.common.sequence.TimeWindow;

/**
 * @author yellow013
 */
public abstract class FixedPeriodPoint extends BasePoint {

    protected final TimeWindow window;

    protected FixedPeriodPoint(int index, TimeWindow window) {
        super(index);
        this.window = window;
    }

    public TimeWindow getWindow() {
        return window;
    }

    @Override
    public long serialId() {
        return window.serialId();
    }

}
