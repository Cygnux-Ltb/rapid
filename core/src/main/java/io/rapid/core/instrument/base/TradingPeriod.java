package io.rapid.core.instrument.base;

import io.mercury.common.datetime.TimeZone;
import io.mercury.common.sequence.SerialObj;
import io.mercury.common.sequence.TimeWindow;
import io.mercury.serialization.json.JsonWriter;
import org.eclipse.collections.api.list.ImmutableList;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static io.mercury.common.lang.Asserter.nonNull;

/**
 * 指示某交易标的一段交易时间
 *
 * @author yellow013
 */
public final class TradingPeriod implements SerialObj<TradingPeriod> {

    private final int serialId;

    private final LocalTime start;

    private final LocalTime end;

    private final Duration duration;

    // 全天交易
    public static TradingPeriod TRADING_ALL_DAY = new TradingPeriod(0,
            LocalTime.of(0, 0, 0), LocalTime.of(0, 0, 0));

    public TradingPeriod(int serialId, LocalTime start, LocalTime end) {
        nonNull(start, "start");
        nonNull(end, "end");
        this.serialId = serialId;
        this.start = start;
        this.end = end;
        Duration between = Duration.between(start, end);
        if (between.getSeconds() > 0)
            this.duration = between;
        else
            this.duration = between.plusDays(1);
    }

    @Override
    public long serialId() {
        return serialId;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public Duration getDuration() {
        return duration;
    }

    /**
     * 分割交易时段
     *
     * @param date     LocalDate
     * @param offset   ZoneOffset
     * @param duration Duration
     * @return ImmutableList<TimeWindow>
     */
    public ImmutableList<TimeWindow> segmentation(@Nonnull LocalDate date, @Nonnull ZoneOffset offset,
                                                  @Nonnull Duration duration) {
        return TimeWindow.segmentationWindow(date, start, end, offset, duration);
    }

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

    public static void main(String[] args) {

        TradingPeriod tradingPeriod = new TradingPeriod(0, LocalTime.of(21, 0, 0), LocalTime.of(2, 30, 0));

        tradingPeriod.segmentation(LocalDate.now(), TimeZone.CST, Duration.ofMinutes(45))
                .each(timePeriod -> System.out.println(timePeriod.getStart() + " - " + timePeriod.getEnd()));

        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 55, 30));

        System.out.println(of);
        System.out.println(of.plusMinutes(30));

        System.out.println(Duration.between(LocalTime.of(23, 0), LocalTime.of(23, 0)).toHours());

        System.out.println(LocalTime.of(23, 0).plusHours(3));
    }

}
