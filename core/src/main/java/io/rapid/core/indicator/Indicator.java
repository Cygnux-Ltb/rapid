package io.rapid.core.indicator;


import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.FastMarketData;

/**
 * 
 * @author yellow013
 *
 * @param <P> Point 类型
 * @param <E> IndicatorEvent 类型
 */
public interface Indicator<P extends Point, E extends IndicatorEvent> {

	Instrument getInstrument();

	void onMarketData(FastMarketData md);

	void addEvent(E event);

	P getPoint(int index);

	P getFastPoint();

	P getCurrentPoint();

	PointSet<P> getPointSet();

}
