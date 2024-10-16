package io.rapid.core.indicator.impl;

import io.mercury.common.sequence.TimeWindow;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.indicator.base.FixedPeriodIndicator;
import io.rapid.core.indicator.base.FixedPeriodPoint;
import io.rapid.core.instrument.Instrument;

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
    protected void handleMarketData(RawMarketData marketData) {

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
        protected void handleMarketData0(RawMarketData marketData) {

        }

    }

}
