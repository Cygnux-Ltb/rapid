package io.rapid.core.indicator.impl;

import io.mercury.common.sequence.TimeWindow;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.indicator.base.FixedPeriodIndicator;
import io.rapid.core.indicator.base.FixedPeriodPoint;
import io.rapid.core.instrument.Instrument;

import java.time.Duration;

public final class MACD extends
        FixedPeriodIndicator<MACD.MacdPoint, MACD.MacdEvent> {

    public MACD(Instrument instrument, Duration duration) {
        super(instrument, duration);
    }

    @Override
    protected void handleMarketData(RawMarketData marketData) {
        // TODO Auto-generated method stub
    }

    public interface MacdEvent extends IndicatorEvent {

        @Override
        default String getEventName() {
            return "MacdEvent";
        }

    }

    public static final class MacdPoint extends FixedPeriodPoint {

        private MacdPoint(int index, TimeWindow window) {
            super(index, window);
        }

        @Override
        protected void handleMarketData0(RawMarketData marketData) {

        }

    }

}
