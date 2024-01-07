package io.rapid.core.indicator.base;

import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.collections.Capacity;
import io.rapid.core.indicator.Indicator;
import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.indicator.Point;
import io.rapid.core.indicator.PointSet;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.FastMarketData;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import static io.mercury.common.collections.MutableLists.newFastList;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class BaseIndicator<P extends BasePoint & Point,
        E extends IndicatorEvent> implements Indicator<P, E> {

    private static final Logger log = getLogger(BaseIndicator.class);

    /**
     * 指标对应的标的
     */
    protected final Instrument instrument;

    /**
     * 存储所有Point的集合
     */
    protected final PointSet<P> pointSet;

    /**
     * 当前Point
     */
    protected P currentPoint;

    /**
     * 前一个Point
     */
    protected P prePoint;

    /**
     * 前一笔行情
     */
    protected FastMarketData preMarketData;

    /**
     * 存储事件的集合
     */
    protected MutableList<E> events = newFastList(8);

    protected BaseIndicator(Instrument instrument) {
        this(instrument, Capacity.L08_SIZE);
    }

    protected BaseIndicator(Instrument instrument, Capacity capacity) {
        this.instrument = instrument;
        this.pointSet = PointSet.newEmpty(capacity);
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public FastMarketData getPreMarketData() {
        return preMarketData;
    }

    @Override
    public void addEvent(E event) {
        if (event != null) {
            log.info("Add IndicatorEvent -> name==[{}]", event.getEventName());
            events.add(event);
        }
    }

    @Override
    public PointSet<P> getPointSet() {
        return pointSet;
    }

    @Override
    public P getCurrentPoint() {
        return currentPoint;
    }

    @Override
    public P getFastPoint() {
        if (pointSet.size() == 0)
            return currentPoint;
        return pointSet.getFirst();
    }

    @Override
    public P getPoint(int index) {
        if (index < 0 || pointSet.size() == 0)
            return currentPoint;
        if (index >= pointSet.size())
            index = pointSet.size() - 1;
        return pointSet.get(index).orElse(currentPoint);
    }

    @Override
    public void onMarketData(FastMarketData marketData) {
        handleMarketData(marketData);
        this.preMarketData = marketData;
    }

    @AbstractFunction
    protected abstract void handleMarketData(FastMarketData marketData);

}
