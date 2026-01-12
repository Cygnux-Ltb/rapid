package io.cygnux.rapid.core.indicator;

import io.mercury.common.sequence.TimePoint;
import lombok.Getter;

public abstract class FloatPeriodPoint extends AbstractPoint {

    @Getter
    private final TimePoint point;

    protected FloatPeriodPoint(int index, TimePoint point) {
        super(index);
        this.point = point;
    }

    @Override
    public long orderNum() {
        return point.orderNum();
    }

}