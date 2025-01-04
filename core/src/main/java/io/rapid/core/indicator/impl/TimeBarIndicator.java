package io.rapid.core.indicator.impl;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.epoch.EpochTimeUtil;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.sequence.TimeWindow;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.indicator.IndicatorEvent;
import io.rapid.core.indicator.base.FixedPeriodIndicator;
import io.rapid.core.indicator.base.FixedPeriodPoint;
import io.rapid.core.indicator.structure.Bar;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.SavedMarketData;
import io.rapid.core.pool.TimeWindowPool;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.slf4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * K-Line 指标
 *
 * @author yellow013
 */
public final class TimeBarIndicator extends FixedPeriodIndicator<TimeBarIndicator.TimeBarPoint, TimeBarIndicator.TimeBarEvent> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(TimeBarIndicator.class);

    public TimeBarIndicator(Instrument instrument, Duration duration) {
        super(instrument, duration);
        // 从已经根据交易周期分配好的池中获取此指标的分割节点
        var timeWindows = TimeWindowPool.Singleton.getTimePeriodSet(instrument, duration);
        int i = -1;
        for (TimeWindow timeWindow : timeWindows)
            pointSet.add(new TimeBarPoint(++i, timeWindow));
        this.currentPoint = pointSet.getFirst();
    }

    public static TimeBarIndicator with(Instrument instrument, Duration duration) {
        return new TimeBarIndicator(instrument, duration);
    }

    // @Override
    private TimeBarPoint generateNextPoint(TimeBarPoint currentPoint) {
        // 根据当前周期的开始时间和结束时间以及时间周期创建新的点
        TimeBarPoint newPoint = currentPoint.generateNext();
        pointSet.add(newPoint);
        return newPoint;
    }

    @Override
    protected void handleMarketData(SavedMarketData marketData) {
        TimeWindow currentPointSerial = currentPoint.getWindow();
        LocalDateTime marketDatetime = EpochTimeUtil.ofEpochMillis(marketData.epochMicros() / 1000,
                        instrument.getZoneOffset())
                .toLocalDateTime();
        if (currentPointSerial.isPeriod(marketDatetime)) {
            currentPoint.onMarketData(marketData);
            for (TimeBarEvent timeBarsEvent : events) {
                timeBarsEvent.onCurrentTimeBarChanged(currentPoint);
            }
        } else {
            for (TimeBarEvent timeBarsEvent : events) {
                timeBarsEvent.onEndTimeBar(currentPoint);
            }
            TimeBarPoint newBar = pointSet.nextOf(currentPoint).orElse(null);
            if (newBar == null) {
                log.error("TimeBar [{}-{}] next is null.", currentPointSerial.getStart(), currentPointSerial.getEnd());
                return;
            }
            while (!newBar.getWindow().isPeriod(marketDatetime)) {
                newBar.onMarketData(preMarketData);
                for (TimeBarEvent timeBarsEvent : events) {
                    timeBarsEvent.onStartTimeBar(newBar);
                }
                for (TimeBarEvent timeBarsEvent : events) {
                    timeBarsEvent.onEndTimeBar(newBar);
                }
                newBar = pointSet.nextOf(currentPoint).orElseGet(null);
                if (newBar == null) {
                    log.error("TimeBar [{}-{}] next is null.", currentPointSerial.getStart(),
                            currentPointSerial.getEnd());
                    break;
                }
            }
            for (TimeBarEvent timeBarsEvent : events) {
                timeBarsEvent.onStartTimeBar(newBar);
            }
        }

    }

    public interface TimeBarEvent extends IndicatorEvent {

        @Override
        default String getEventName() {
            return "TimeBarEvent";
        }

        void onCurrentTimeBarChanged(TimeBarPoint point);

        void onStartTimeBar(TimeBarPoint point);

        void onEndTimeBar(TimeBarPoint point);

    }

    /**
     * @author yellow013
     */
    public static final class TimeBarPoint extends FixedPeriodPoint {

        // 存储开高低收价格和成交量以及成交金额的字段
        private final Bar bar = new Bar(0);

        // 总成交量
        private long volumeSum = 0L;

        // 总成交金额
        private long turnoverSum = 0L;

        private final MutableDoubleList priceRecord = MutableLists.newDoubleArrayList(64);
        private final MutableIntList volumeRecord = MutableLists.newIntArrayList(64);

        private TimeBarPoint(int index, TimeWindow serial) {
            super(index, serial);
        }

        private TimeBarPoint generateNext() {
            return new TimeBarPoint(index + 1, TimeWindow.getNext(window));
        }

        public double getOpen() {
            return bar.getOpen();
        }

        public double getHighest() {
            return bar.getHighest();
        }

        public double getLowest() {
            return bar.getLowest();
        }

        public double getLast() {
            return bar.getLast();
        }

        public MutableDoubleList priceRecord() {
            return priceRecord;
        }

        public long volumeSum() {
            return volumeSum;
        }

        public MutableIntList volumeRecord() {
            return volumeRecord;
        }

        public long turnoverSum() {
            return turnoverSum;
        }

        @Override
        protected void handleMarketData0(SavedMarketData marketData) {
            // 处理当前价格
            bar.onPrice(marketData.lastPrice());
            // 记录当前价格
            priceRecord.add(marketData.lastPrice());
            // 总成交量增加处理当前行情
            volumeSum += marketData.volume();
            // 记录当前成交量
            volumeRecord.add(marketData.volume());
            // 处理当前成交额
            turnoverSum += (long) marketData.turnover();
        }

    }

}
