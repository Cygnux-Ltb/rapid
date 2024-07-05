package io.rapid.core.indicator.base;


import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.instrument.Instrument;

import java.time.Duration;

public abstract class FixedPeriodIndicator<P extends FixedPeriodPoint,
        E extends IndicatorEvent> extends BaseIndicator<P, E> {

    protected final Duration duration;

    protected final int cycle;

    /**
     * @param instrument Instrument
     * @param duration   Duration
     */
    public FixedPeriodIndicator(Instrument instrument, Duration duration) {
        this(instrument, duration, 0);
    }

    /**
     * @param instrument Instrument
     * @param duration   Duration
     * @param cycle      int
     */
    public FixedPeriodIndicator(Instrument instrument, Duration duration, int cycle) {
        super(instrument);
        this.duration = duration;
        this.cycle = cycle;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getCycle() {
        return cycle;
    }

}
