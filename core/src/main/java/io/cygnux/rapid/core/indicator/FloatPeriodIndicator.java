package io.cygnux.rapid.core.indicator;

import io.cygnux.rapid.core.instrument.Instrument;

public abstract non-sealed class FloatPeriodIndicator<P extends FloatPeriodPoint, E extends IndicatorEvent>
        extends AbstractIndicator<P, E> {

    protected FloatPeriodIndicator(Instrument instrument) {
        super(instrument);
    }

}
