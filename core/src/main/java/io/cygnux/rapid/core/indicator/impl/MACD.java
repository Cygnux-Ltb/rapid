package io.cygnux.rapid.core.indicator.impl;

import io.cygnux.rapid.core.indicator.FixedPeriodIndicator;
import io.cygnux.rapid.core.indicator.FixedPeriodPoint;
import io.cygnux.rapid.core.indicator.IndicatorEvent;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.mkd.SavedMarketData;
import io.mercury.common.sequence.TimeWindow;

import java.time.Duration;

public final class MACD extends FixedPeriodIndicator<MACD.MacdPoint, MACD.MacdEvent> {

    public MACD(Instrument instrument, Duration duration) {
        super(instrument, duration);
    }

    @Override
    protected void handleMarketData(SavedMarketData marketData) {
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
        protected void handleMarketData0(SavedMarketData marketData) {

        }

    }

}
