package io.cygnuxltb.jcts.core.indicator;


import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.MarketData;

/**
 * 
 * @author yellow013
 *
 * @param <P> Point 类型
 * @param <E> IndicatorEvent 类型
 * @param <M> MarketData 类型
 */
public interface Indicator<P extends Point, E extends IndicatorEvent, M extends MarketData> {

	Instrument getInstrument();

	void onMarketData(M md);

	void addEvent(E event);

	P getPoint(int index);

	P getFastPoint();

	P getCurrentPoint();

	PointSet<P> getPointSet();

}
