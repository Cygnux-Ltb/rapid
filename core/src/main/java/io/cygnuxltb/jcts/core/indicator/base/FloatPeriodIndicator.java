package io.cygnuxltb.jcts.core.indicator.base;


import io.cygnuxltb.jcts.core.indicator.IndicatorEvent;
import io.cygnuxltb.jcts.core.instrument.Instrument;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint,
        E extends IndicatorEvent> extends BaseIndicator<P, E> {

    protected FloatPeriodIndicator(Instrument instrument) {
        super(instrument);
    }

}
