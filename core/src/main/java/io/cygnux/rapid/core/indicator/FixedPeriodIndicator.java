package io.cygnux.rapid.core.indicator;


import io.cygnux.rapid.core.types.instrument.Instrument;
import lombok.Getter;

import java.time.Duration;

public abstract non-sealed class FixedPeriodIndicator<P extends FixedPeriodPoint, E extends IndicatorEvent>
        extends AbstractIndicator<P, E> {

    @Getter
    protected final Duration duration;

    @Getter
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

}
