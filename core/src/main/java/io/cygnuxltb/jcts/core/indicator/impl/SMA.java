package io.cygnuxltb.jcts.core.indicator.impl;

import java.time.Duration;

import io.cygnuxltb.jcts.core.indicator.IndicatorEvent;
import io.cygnuxltb.jcts.core.indicator.base.FixedPeriodIndicator;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;
import io.cygnuxltb.jcts.core.pool.TimeWindowPool;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.common.collections.window.LongRingWindow;
import io.mercury.common.sequence.TimeWindow;

public final class SMA extends FixedPeriodIndicator<SmaPoint, SMA.SmaEvent, BasicMarketData> {

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
	protected void handleMarketData(BasicMarketData marketData) {

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
