package io.cygnuxltb.jcts.core.indicator.impl;

import io.cygnuxltb.jcts.core.indicator.IndicatorEvent;
import io.cygnuxltb.jcts.core.indicator.base.FixedPeriodIndicator;
import io.cygnuxltb.jcts.core.indicator.base.FixedPeriodPoint;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.FastMarketData;
import io.mercury.common.sequence.TimeWindow;

import java.time.Duration;

public final class BollingerBands extends
        FixedPeriodIndicator<BollingerBands.BollingerBandsPoint, BollingerBands.BollingerBandsEvent> {

    public BollingerBands(Instrument instrument, Duration duration, int cycle) {
        super(instrument, duration, cycle);
    }

    @Override
    protected void handleMarketData(FastMarketData marketData) {

    }

    /**
     * @author yellow013
     */
    public interface BollingerBandsEvent extends IndicatorEvent {

        @Override
        default String getEventName() {
            return "BollingerBandsEvent";
        }

    }

    public static final class BollingerBandsPoint extends FixedPeriodPoint {

        private BollingerBandsPoint(int index, TimeWindow timePeriod) {
            super(index, timePeriod);
        }

        @Override
        protected void handleMarketData0(FastMarketData marketData) {

        }

    }

}
