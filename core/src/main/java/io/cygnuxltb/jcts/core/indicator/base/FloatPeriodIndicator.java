package io.cygnuxltb.jcts.core.indicator.base;


import io.cygnuxltb.jcts.core.indicator.IndicatorEvent;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.MarketData;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint<M>,
        E extends IndicatorEvent, M extends MarketData> extends BaseIndicator<P, E, M> {

    protected FloatPeriodIndicator(Instrument instrument) {
        super(instrument);
    }

}
