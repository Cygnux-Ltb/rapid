package io.cygnux.rapid.core.indicator;

import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.mkd.SavedMarketData;
import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.collections.Capacity;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import static io.mercury.common.collections.MutableLists.newFastList;

public abstract sealed class AbstractIndicator<P extends AbstractPoint & Point, E extends IndicatorEvent>
        implements Indicator<P, E> permits FixedPeriodIndicator, FloatPeriodIndicator {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AbstractIndicator.class);

    /**
     * 指标对应的标的
     */
    @Getter
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
    @Getter
    protected SavedMarketData preMarketData;

    /**
     * 存储事件的集合
     */
    protected MutableList<E> events = newFastList(8);

    protected AbstractIndicator(Instrument instrument) {
        this(instrument, Capacity.HEX_100);
    }

    protected AbstractIndicator(Instrument instrument, Capacity capacity) {
        this.instrument = instrument;
        this.pointSet = PointSet.newEmpty(capacity);
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
    public void onMarketData(SavedMarketData marketData) {
        handleMarketData(marketData);
        this.preMarketData = marketData;
    }

    @AbstractFunction
    protected abstract void handleMarketData(SavedMarketData marketData);

}
