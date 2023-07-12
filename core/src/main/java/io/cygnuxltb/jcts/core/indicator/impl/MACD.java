package io.cygnuxltb.jcts.core.indicator.impl;

import io.cygnuxltb.jcts.core.indicator.IndicatorEvent;
import io.cygnuxltb.jcts.core.indicator.base.FixedPeriodIndicator;
import io.cygnuxltb.jcts.core.indicator.base.FixedPeriodPoint;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;
import io.mercury.common.sequence.TimeWindow;

import java.time.Duration;

public final class MACD extends
        FixedPeriodIndicator<MACD.MacdPoint, MACD.MacdEvent, BasicMarketData> {

    public MACD(Instrument instrument, Duration duration) {
        super(instrument, duration);
    }

    @Override
    protected void handleMarketData(BasicMarketData marketData) {
        // TODO Auto-generated method stub
    }

    public static interface MacdEvent extends IndicatorEvent {

        @Override
        default String getEventName() {
            return "MacdEvent";
        }

    }

    public static final class MacdPoint extends FixedPeriodPoint<BasicMarketData> {

        private MacdPoint(int index, TimeWindow window) {
            super(index, window);
        }

        @Override
        protected void handleMarketData0(BasicMarketData marketData) {

        }

    }

}
