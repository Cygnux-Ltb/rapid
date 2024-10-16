package io.rapid.core.indicator.base;

import io.mercury.common.sequence.TimePoint;

public abstract class FloatPeriodPoint extends BasePoint {

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