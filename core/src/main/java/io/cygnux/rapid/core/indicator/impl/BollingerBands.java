package io.cygnux.rapid.core.indicator.impl;

import io.mercury.common.sequence.TimeWindow;
import io.cygnux.rapid.core.indicator.IndicatorEvent;
import io.cygnux.rapid.core.indicator.FixedPeriodIndicator;
import io.cygnux.rapid.core.indicator.FixedPeriodPoint;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.mkd.SavedMarketData;

import java.time.Duration;

/**
 *
 */
public final class BollingerBands extends
        FixedPeriodIndicator<BollingerBands.BollingerBandsPoint, BollingerBands.BollingerBandsEvent> {

    public BollingerBands(Instrument instrument, Duration duration, int cycle) {
        super(instrument, duration, cycle);
    }

    @Override
    protected void handleMarketData(SavedMarketData marketData) {

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
        protected void handleMarketData0(SavedMarketData marketData) {

        }

    }

}
