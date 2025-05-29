package io.rapid.core.indicator;

import io.mercury.common.sequence.TimePoint;

public abstract class FloatPeriodPoint extends AbstractPoint {

    private final TimePoint point;

    protected FloatPeriodPoint(int index, TimePoint point) {
        super(index);
        this.point = point;
    }

    public TimePoint getPoint() {
        return point;
    }

    @Override
    public long orderNum() {
        return point.orderNum();
    }

}