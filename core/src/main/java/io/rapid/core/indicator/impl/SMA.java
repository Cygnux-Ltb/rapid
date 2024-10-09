package io.rapid.core.indicator.impl;

import io.mercury.common.collections.window.LongRingWindow;
import io.mercury.common.sequence.TimeWindow;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.indicator.base.FixedPeriodIndicator;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.pool.TimeWindowPool;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import java.time.Duration;

/**
 *
 */
public final class SMA extends FixedPeriodIndicator<SmaPoint, SMA.SmaEvent> {

    private final LongRingWindow historyPriceWindow;

    public SMA(Instrument instrument, Duration duration, int cycle) {
        super(instrument, duration, cycle);
        this.historyPriceWindow = new LongRingWindow(cycle);
        ImmutableSortedSet<TimeWindow> timePeriodSet = TimeWindowPool.Singleton.getTimePeriodSet(instrument,
                duration);
        int i = -1;
        for (TimeWindow timePeriod : timePeriodSet)
            pointSet.add(SmaPoint.with(++i, instrument, duration, timePeriod, cycle, historyPriceWindow));
        currentPoint = pointSet.getFirst();

    }

    public static SMA with(Instrument instrument, Duration duration, int cycle) {
        return new SMA(instrument, duration, cycle);
    }

    @Override
    protected void handleMarketData(RawMarketData marketData) {

    }

    public interface SmaEvent extends IndicatorEvent {

        default String eventName() {
            return "SmaEvent";
        }

        void onCurrentPointAvgPriceChanged(SmaPoint point);

        void onStartSmaPoint(SmaPoint point);

        void onEndSmaPoint(SmaPoint point);

    }

}
