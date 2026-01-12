package io.cygnux.rapid.core.indicator;

import io.mercury.common.sequence.TimeWindow;
import lombok.Getter;

/**
 * @author yellow013
 */
public abstract class FixedPeriodPoint extends AbstractPoint {

    @Getter
    protected final TimeWindow window;

    protected FixedPeriodPoint(int index, TimeWindow window) {
        super(index);
        this.window = window;
    }

    @Override
    public long orderNum() {
        return window.orderNum();
    }

}
