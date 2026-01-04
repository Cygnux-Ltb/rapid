package io.cygnux.rapid.core.instrument;

import io.mercury.common.datetime.TimeZone;
import io.mercury.common.sequence.OrderedObject;
import io.mercury.common.sequence.TimeWindow;
import io.mercury.serialization.json.JsonWriter;
import lombok.Getter;
import org.eclipse.collections.api.list.ImmutableList;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static io.mercury.common.lang.Validator.nonNull;
import static io.mercury.common.sequence.TimeWindow.segmentationWindow;
import static java.time.Duration.between;
import static java.time.LocalTime.of;

/**
 * 指示某交易标的一段可交易时间段
 *
 * @author yellow013
 */
public final class TradablePeriod implements OrderedObject<TradablePeriod> {

    @Getter
    private final int serialId;

    @Getter
    private final LocalTime start;

    @Getter
    private final LocalTime end;

    @Getter
    private final Duration duration;

    /**
     * 全天交易
     */
    public static final TradablePeriod TRADING_ALL_DAY = new TradablePeriod(0,
            of(0, 0, 0),
            of(23, 59, 59, 999999999));

    public TradablePeriod(int serialId, int startHour, int endHour) {
        this(serialId, of(startHour, 0, 0),
                of(endHour, 0, 0));
    }

    public TradablePeriod(int serialId,
                          int startHour, int startMinute,
                          int endHour, int endMinute) {
        this(serialId, of(startHour, startMinute, 0),
                of(endHour, endMinute, 0));
    }

    /**
     * @param serialId    int
     * @param startHour   int
     * @param startMinute int
     * @param startSecond int
     * @param endHour     int
     * @param endMinute   int
     * @param endSecond   int
     */
    public TradablePeriod(int serialId,
                          int startHour, int startMinute, int startSecond,
                          int endHour, int endMinute, int endSecond) {
        this(serialId, of(startHour, startMinute, startSecond),
                of(endHour, endMinute, endSecond));
    }


    /**
     * @param serialId int
     * @param start    LocalTime
     * @param end      LocalTime
     */
    public TradablePeriod(int serialId, LocalTime start, LocalTime end) {
        nonNull(start, "start");
        nonNull(end, "end");
        this.serialId = serialId;
        this.start = start;
        this.end = end;
        Duration between = between(start, end);
        if (between.getSeconds() > 0)
            this.duration = between;
        else
            this.duration = between.plusDays(1);
    }

    @Override
    public long orderNum() {
        return serialId;
    }

    /**
     * 分割交易时段
     *
     * @param date     LocalDate
     * @param offset   ZoneOffset
     * @param duration Duration
     * @return ImmutableList<TimeWindow>
     */
    public ImmutableList<TimeWindow> segmentation(@Nonnull LocalDate date,
                                                  @Nonnull ZoneOffset offset,
                                                  @Nonnull Duration duration) {
        return segmentationWindow(date, start, end, offset, duration);
    }

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

    public static void main(String[] args) {

        TradablePeriod tradablePeriod = new TradablePeriod(0, of(21, 0, 0),
                of(2, 30, 0));

        tradablePeriod.segmentation(LocalDate.now(), TimeZone.CST, Duration.ofMinutes(45))
                .each(timePeriod -> System.out.println(timePeriod.getStart() + " - " + timePeriod.getEnd()));

        LocalDateTime of = LocalDateTime.of(LocalDate.now(), of(23, 55, 30));

        System.out.println(of);
        System.out.println(of.plusMinutes(30));

        System.out.println(between(of(23, 0), of(23, 0)).toHours());

        System.out.println(of(23, 0).plusHours(3));
    }


}
