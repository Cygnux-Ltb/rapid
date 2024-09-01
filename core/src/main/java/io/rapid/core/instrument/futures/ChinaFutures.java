package io.rapid.core.instrument.futures;

import io.mercury.common.util.StringSupport;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.enums.PriceMultiplier;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

/**
 * 工具类
 *
 * @author yellow013
 */
public final class ChinaFuturesUtil {

    private ChinaFuturesUtil() {
    }

    /**
     * 固定价格乘数
     */
    public static final PriceMultiplier PRICE_MULTIPLIER = PriceMultiplier.MULTIPLIER_4;

    /**
     * 交易日分割点, 此时间之后的事件, 头寸, 订单, 行情等归入下一个交易日.
     */
    public static final LocalTime TRADING_DAY_DIVIDING_POINT = LocalTime.of(17, 0);

    /**
     * 夜盘交易开盘时间
     */
    public static final LocalTime NIGHT_OPEN = LocalTime.of(21, 0);

    /**
     * 夜盘交易最后收盘时间
     */
    public static final LocalTime LATEST_NIGHT_CLOSE = LocalTime.of(2, 30);

    /**
     * 白天交易开盘时间
     */
    public static final LocalTime DAY_OPEN = LocalTime.of(9, 0);

    /**
     * 白天交易收盘时间
     */
    public static final LocalTime DAY_CLOSE = LocalTime.of(15, 0);


    /**
     * 分析<b> [Instrument]</b>
     *
     * @param instrumentCode String
     * @return Instrument
     */
    public static Instrument parseInstrument(String instrumentCode) {
        return parseInstrumentList(instrumentCode).getFirst();
    }


    /**
     * 分析<b> [Instrument] </b>列表
     *
     * @param instrumentCodes String
     * @return Instrument[]
     */
    public static List<Instrument> parseInstrumentList(String... instrumentCodes) {
        return Stream.of(instrumentCodes)
                .filter(StringSupport::nonEmpty)
                .map(ChinaFuturesInstrument::newInstance)
                .toList();
    }

    /**
     * 分析指定时间的所属交易日
     *
     * @param dateTime LocalDateTime
     * @return LocalDate
     */
    public static LocalDate parseTradingDay(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        // 判断是否是夜盘
        if (isNightTrading(dateTime.toLocalTime()))
            // 判断是否周五, 如果是加3天, 否则加1天
            if (dayOfWeek == DayOfWeek.FRIDAY)
                return dateTime.plusDays(3).toLocalDate();
            else
                return dateTime.plusDays(1).toLocalDate();
        else
            // 判断是否周六, 如果是加2天, 否则不加.
            if (dayOfWeek == DayOfWeek.SATURDAY)
                return dateTime.plusDays(2).toLocalDate();
            else
                return dateTime.toLocalDate();
    }

    /**
     * 判断时间是否在交易日时间线之前
     *
     * @param time LocalTime
     * @return boolean
     */
    public static boolean isNightTrading(LocalTime time) {
        return time.isAfter(TRADING_DAY_DIVIDING_POINT);
    }

    /**
     * 分析[instrumentCode]中的[symbol]代码
     *
     * @param instrumentCode String
     * @return String
     */
    public static String parseSymbolCode(String instrumentCode) {
        if (StringSupport.isNullOrEmpty(instrumentCode))
            return instrumentCode;
        return instrumentCode.replaceAll("\\d", "").trim();
    }

    /**
     * 分析[instrumentCode]中的日期期限
     *
     * @param instrumentCode String
     * @return int
     */
    public static int parseInstrumentTerm(String instrumentCode) {
        if (StringSupport.isNullOrEmpty(instrumentCode))
            return 0;
        return Integer.parseInt(instrumentCode.replaceAll("\\D", "").trim());
    }

    /**
     * 获取下一次关闭运行的时间点
     *
     * @return LocalDateTime
     */
    public static LocalDateTime nextCloseTime() {
        return nextCloseTime(LocalDateTime.now());
    }

    public static LocalDateTime nextCloseTime(LocalDateTime datetime) {
        // 夜盘收盘时间
        LocalDateTime nightClose = LocalDateTime.of(datetime.toLocalDate(), ChinaFuturesUtil.LATEST_NIGHT_CLOSE);
        // 输入时间在前一个夜盘中
        if (datetime.isBefore(nightClose)) {
            // 夜盘结束后10分钟
            return nightClose.plusMinutes(10);
        }

        // 白天交易收盘时间
        LocalDateTime dayClose = LocalDateTime.of(datetime.toLocalDate(), ChinaFuturesUtil.DAY_CLOSE);
        // 输入时间在夜盘收盘后, 在白天收盘前
        if (datetime.isAfter(nightClose) && datetime.isBefore(dayClose)) {
            // 白天收盘后10分钟
            return dayClose.plusMinutes(15);
        }

        // 获取下一个夜盘收盘时间
        LocalDateTime nextNightClose = LocalDateTime.of(datetime.toLocalDate().plusDays(1),
                ChinaFuturesUtil.LATEST_NIGHT_CLOSE);
        // 如果输入时间在白天交易之后, 在下一个夜盘收盘结束前
        if ((datetime.isAfter(dayClose) && datetime.isBefore(nextNightClose))) {
            // 夜盘结束后10分钟
            return nextNightClose.plusMinutes(15);
        }
        return nextNightClose.plusMinutes(10);
    }

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(ChinaFuturesSymbol.AG.getExchange().getExchangeId());
        System.out.println(ChinaFuturesSymbol.AG.getSymbolId());
        System.out.println(ChinaFuturesSymbol.AG.acquireInstrumentId(2112));
        System.out.println(parseSymbolCode("rb1901"));
        System.out.println(parseInstrumentTerm("rb1901"));
        ChinaFuturesSymbol rb1901 = ChinaFuturesSymbol.of(parseSymbolCode("rb1901"));
        System.out.println(rb1901);

        Stream.of(ChinaFuturesSymbol.values())
                .flatMap(symbol -> symbol.getInstruments().stream())
                .forEach(System.out::println);

        System.out.println(parseSymbolCode("rb1901"));
        System.out.println(parseInstrumentTerm("rb1901"));
    }

}

