package io.rapid.core.indicator.impl;

import io.mercury.common.collections.window.LongRingWindow;
import io.mercury.common.sequence.TimeWindow;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.indicator.base.FixedPeriodIndicator;
import io.rapid.core.indicator.impl.SMA.SmaEvent;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.TradablePeriod;
import io.rapid.core.pool.TradablePeriodPool;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public final class SMA2 extends FixedPeriodIndicator<SmaPoint, SmaEvent> {

    private final LongRingWindow historyPriceWindow;

    // TODO
    public SMA2(Instrument instrument, Duration duration, int cycle) {
        super(instrument, duration, cycle);
        this.historyPriceWindow = new LongRingWindow(cycle);
        TradablePeriod tradablePeriod = TradablePeriodPool.nextTradingPeriod(instrument, LocalTime.now());
        LocalDate date = LocalDate.now();
        ZoneOffset offset = instrument.getZoneOffset();
        TimeWindow timePeriod = TimeWindow.with(LocalDateTime.of(date, tradablePeriod.getStart()),
                LocalDateTime.of(date, tradablePeriod.getStart()).plusSeconds(duration.getSeconds()), offset);
        currentPoint = SmaPoint.with(0, instrument, duration, timePeriod, cycle, historyPriceWindow);
    }

    public static SMA2 with(Instrument instrument, Duration duration, int cycle) {
        return new SMA2(instrument, duration, cycle);
    }

    @Override
    public void onMarketData(RawMarketData marketData) {

    }

    @Override
    protected void handleMarketData(RawMarketData marketData) {

    }

}
