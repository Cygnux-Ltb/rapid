package io.rapid.core.indicator;


import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.instrument.Instrument;

/**
 * @param <P> Point 类型
 * @param <E> IndicatorEvent 类型
 * @author yellow013
 */
public interface Indicator<P extends Point, E extends IndicatorEvent> {

    Instrument getInstrument();

    void onMarketData(RawMarketData md);

    void addEvent(E event);

    P getPoint(int index);

    P getFastPoint();

    P getCurrentPoint();

    PointSet<P> getPointSet();

}
