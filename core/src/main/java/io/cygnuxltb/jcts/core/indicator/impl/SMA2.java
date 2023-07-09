package io.cygnuxltb.jcts.core.indicator.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.indicator.base.FixedPeriodIndicator;
import io.horizon.market.indicator.impl.SmaPoint;
import io.horizon.market.instrument.Instrument;
import io.horizon.market.instrument.attr.TradablePeriod;
import io.horizon.market.pool.TradablePeriodPool;
import io.mercury.common.collections.window.LongRingWindow;
import io.mercury.common.sequence.TimeWindow;

public final class SMA2 extends FixedPeriodIndicator<io.horizon.market.indicator.impl.SmaPoint, SMA.SmaEvent, BasicMarketData> {

	private final LongRingWindow historyPriceWindow;

	// TODO
	public SMA2(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
		this.historyPriceWindow = new LongRingWindow(cycle);
		TradablePeriod tradingPeriod = TradablePeriodPool.nextTradingPeriod(instrument, LocalTime.now());
		LocalDate date = LocalDate.now();
		ZoneOffset offset = instrument.getZoneOffset();
		TimeWindow timePeriod = TimeWindow.with(LocalDateTime.of(date, tradingPeriod.getStart()),
				LocalDateTime.of(date, tradingPeriod.getStart()).plusSeconds(duration.getSeconds()), offset);
		currentPoint = SmaPoint.with(0, instrument, duration, timePeriod, cycle, historyPriceWindow);
	}

	public static SMA2 with(Instrument instrument, Duration duration, int cycle) {
		return new SMA2(instrument, duration, cycle);
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {

	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

}
