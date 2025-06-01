package io.cygnux.rapid.core.indicator;


import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.mdata.SavedMarketData;

/**
 * @param <P> Point 类型
 * @param <E> IndicatorEvent 类型
 * @author yellow013
 */
public sealed interface Indicator<P extends Point, E extends IndicatorEvent>
        permits AbstractIndicator {

    Instrument getInstrument();

    void onMarketData(SavedMarketData md);

    void addEvent(E event);

    P getPoint(int index);

    P getFastPoint();

    P getCurrentPoint();

    PointSet<P> getPointSet();

}
