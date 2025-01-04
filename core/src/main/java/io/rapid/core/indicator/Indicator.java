package io.rapid.core.indicator;


import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.SavedMarketData;

/**
 * @param <P> Point 类型
 * @param <E> IndicatorEvent 类型
 * @author yellow013
 */
public interface Indicator<P extends Point, E extends IndicatorEvent> {

    Instrument getInstrument();

    void onMarketData(SavedMarketData md);

    void addEvent(E event);

    P getPoint(int index);

    P getFastPoint();

    P getCurrentPoint();

    PointSet<P> getPointSet();

}
