package io.rapid.core.indicator.base;

import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.instrument.Instrument;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint, E extends IndicatorEvent>
        extends BaseIndicator<P, E> {

    protected FloatPeriodIndicator(Instrument instrument) {
        super(instrument);
    }

}
