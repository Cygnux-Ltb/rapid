package io.rapid.core.indicator.impl;

import io.mercury.common.collections.window.LongRingWindow;
import io.mercury.common.sequence.TimeWindow;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.indicator.base.FixedPeriodIndicator;
import io.rapid.core.instrument.Instrument;

import java.time.Duration;

public final class EMA extends
        FixedPeriodIndicator<EMA.EmaPoint, EMA.EmaEvent> {

    public EMA(Instrument instrument, Duration duration, int cycle) {
        super(instrument, duration);
    }

    @Override
    protected void handleMarketData(RawMarketData marketData) {
        // TODO Auto-generated method stub
    }

    /**
     * @author yellow013
     */
    public interface EmaEvent extends IndicatorEvent {

        @Override
        default String getEventName() {
            return "EmaEvent";
        }

        void onCurrentEmaPointAvgPriceChanged(EmaPoint point);

        void onStartEmaPoint(EmaPoint point);

        void onEndEmaPoint(EmaPoint point);

    }

    /**
     * @author yellow013
     */
    public static final class EmaPoint extends MaPoint {

        private EmaPoint(int index, Instrument instrument, Duration duration, TimeWindow timePeriod,
                         LongRingWindow historyPriceWindow) {
            super(index, instrument, duration, timePeriod, historyPriceWindow);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void handleMarketData0(RawMarketData preMarketData) {
            // TODO Auto-generated method stub

        }

    }

}
