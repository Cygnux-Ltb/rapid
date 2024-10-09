package io.rapid.core.instrument.futures;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.serialization.json.JsonWriter;
import io.rapid.core.instrument.Exchange;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.Symbol;
import io.rapid.core.instrument.TradingPeriod;
import io.rapid.core.instrument.enums.PriceMultiplier;
import io.rapid.core.instrument.enums.PriorityCloseType;
import lombok.Getter;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static io.mercury.common.collections.ImmutableLists.newImmutableList;
import static io.mercury.common.datetime.DateTimeUtil.dateOfMonth;

/**
 * ChinaFutures Symbol
 *
 * @author yellow013
 */
public enum ChinaFuturesSymbol implements Symbol {

    // ************************上海期货交易所************************//
    /**
     * 铜
     */
    CU(Exchange.SHFE, "cu", 1, 10,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 铜期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 1, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 铝
     */
    AL(Exchange.SHFE, "al", 2, 5,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 铝期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 1, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 锌
     */
    ZN(Exchange.SHFE, "zn", 3, 5,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 锌期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 1, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 镍
     */
    NI(Exchange.SHFE, "ni", 4, 10,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 镍期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 1, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 黄金
     */
    AU(Exchange.SHFE, "au", 5, 0.02,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_4,
            // 黄金期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 2, 30),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            6, 12
    ),

    /**
     * 白银
     */
    AG(Exchange.SHFE, "ag", 6, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 白银期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 2, 30),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            6, 12
    ),

    /**
     * 螺纹钢
     */
    RB(Exchange.SHFE, "rb", 7, 10,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 螺纹钢期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 10
    ),

    /**
     * 热卷扎板
     */
    HC(Exchange.SHFE, "hc", 8, 10,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 热卷扎板期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 10
    ),

    /**
     * 橡胶
     */
    RU(Exchange.SHFE, "ru", 9, 5,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 橡胶期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 燃油
     */
    FU(Exchange.SHFE, "fu", 10, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 燃油期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    // ************************上海能源交易所************************//
    /**
     * 原油
     */
    SC(Exchange.SHINE, "sc", 1, 0.1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 原油期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 1, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    // **************************大连商品交易所*************************//
    /**
     * 大豆
     */
    A(Exchange.DCE, "a", 1, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 大豆期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 豆粕
     */
    M(Exchange.DCE, "m", 2, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 豆粕期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 豆油
     */
    Y(Exchange.DCE, "y", 3, 2,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 豆油期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 棕榈油
     */
    P(Exchange.DCE, "p", 4, 2,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 棕榈油期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 玉米
     */
    C(Exchange.DCE, "c", 5, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 棕榈油期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 铁矿石
     */
    I(Exchange.DCE, "i", 6, 0.5,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 铁矿石期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    // 大商所品种 : 塑料, PVC, PP,
    // *****************************郑州商品交易所***********************************//
    /**
     * 棉花
     */
    CF(Exchange.ZCE, "CF", 1, 5,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 棉花交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 11, 30),
                    new TradingPeriod(3, 13, 30, 15, 0)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 白糖
     */
    SR(Exchange.ZCE, "SR", 2, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 白糖交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 15, 15)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * PTA
     */
    TA(Exchange.ZCE, "TA", 3, 2,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // PTA交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 15, 15)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 甲醇
     */
    MA(Exchange.ZCE, "MA", 4, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 甲醇交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 15, 15)),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 菜粕
     */
    RM(Exchange.ZCE, "RM", 5, 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE,
            // 菜粕交易时段
            newImmutableList(
                    new TradingPeriod(0, 21, 0, 23, 0),
                    new TradingPeriod(1, 9, 0, 10, 15),
                    new TradingPeriod(2, 10, 30, 15, 15)),
            // 主力合约月份
            1, 5, 9
    ),

    // ************************中国金融交易所************************//
    /**
     * 沪深300
     */
    IF(Exchange.CFFEX, "IF", 1, 0.2,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 沪深300期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 9, 15, 11, 30),
                    new TradingPeriod(1, 13, 0, 15, 15)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 上证50
     */
    IH(Exchange.CFFEX, "IH", 2, 0.2,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 上证50期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 9, 15, 11, 30),
                    new TradingPeriod(1, 13, 0, 15, 15)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 中证500
     */
    IC(Exchange.CFFEX, "IC", 3, 0.2,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 中证500期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 9, 15, 11, 30),
                    new TradingPeriod(1, 13, 0, 15, 15)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 30年期国债
     */
    TL(Exchange.CFFEX, "TL", 4, 0.01,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 30年期国债期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 9, 15, 11, 30),
                    new TradingPeriod(1, 13, 0, 15, 15)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 10年期国债
     */
    T(Exchange.CFFEX, "T", 5, 0.005,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 10年期国债期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 9, 15, 11, 30),
                    new TradingPeriod(1, 13, 0, 15, 15)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 5年期国债
     */
    TF(Exchange.CFFEX, "TF", 6, 0.005,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 5年期国债期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 9, 15, 11, 30),
                    new TradingPeriod(1, 13, 0, 15, 15)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 2年期国债
     */
    TS(Exchange.CFFEX, "TS", 7, 0.002,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2,
            // 2年期国债期货交易时段
            newImmutableList(
                    new TradingPeriod(0, 9, 15, 11, 30),
                    new TradingPeriod(1, 13, 0, 15, 15)),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    ;

    // 交易所
    @Getter
    private final Exchange exchange;

    // SymbolId
    @Getter
    private final int symbolId;

    // Symbol代码
    @Getter
    private final String symbolCode;

    // Tick Size
    @Getter
    private final double tickSize;

    // 优先平仓类型
    @Getter
    private final PriorityCloseType priorityCloseType;

    // 价格乘数
    @Getter
    private final PriceMultiplier multiplier;

    // Symbol包含的主力合约列表
    @Getter
    private final ImmutableList<Instrument> instruments;

    // 交易时间段
    @Getter
    private final ImmutableList<TradingPeriod> tradablePeriods;

    /**
     * @param exchange          Exchange
     * @param symbolCode        String
     * @param sortingInExchange int
     * @param priorityCloseType PriorityCloseType
     * @param multiplier        PriceMultiplier
     * @param tradingPeriods    TradablePeriod...
     */
    ChinaFuturesSymbol(Exchange exchange, String symbolCode,
                       int sortingInExchange, double tickSize,
                       PriorityCloseType priorityCloseType, PriceMultiplier multiplier,
                       // 交易时间段列表
                       ImmutableList<TradingPeriod> tradingPeriods,
                       // 主力合约月份
                       int... mainMonths) {
        this.exchange = exchange;
        this.symbolId = AbstractFutures.generateSymbolId(exchange.getExchangeId(), sortingInExchange);
        this.symbolCode = symbolCode;
        this.tickSize = tickSize;
        this.priorityCloseType = priorityCloseType;
        this.multiplier = multiplier;
        this.tradablePeriods = tradingPeriods;
        this.instruments = generateInstruments(mainMonths);
    }

    /**
     * symbolId -> symbol映射
     */
    private final static ImmutableIntObjectMap<ChinaFuturesSymbol> SYMBOL_MAP_BY_ID;

    /**
     * symbolCode -> symbol的映射
     */
    private final static ImmutableMap<String, ChinaFuturesSymbol> SYMBOL_MAP_BY_CODE;

    static {
        var symbolIdMap = MutableMaps.<ChinaFuturesSymbol>newIntObjectMap();
        var symbolCodeMap = MutableMaps.<String, ChinaFuturesSymbol>newUnifiedMap();
        for (var symbol : ChinaFuturesSymbol.values()) {
            symbolIdMap.put(symbol.getSymbolId(), symbol);
            symbolCodeMap.put(symbol.getSymbolCode().toLowerCase(), symbol);
            symbolCodeMap.put(symbol.getSymbolCode().toUpperCase(), symbol);
        }
        SYMBOL_MAP_BY_ID = symbolIdMap.toImmutable();
        SYMBOL_MAP_BY_CODE = symbolCodeMap.toImmutable();
    }

    /**
     * 以主力合约月份构建当年, 次年, 下一个次年的合约列表
     *
     * @return ImmutableList<Instrument>
     */
    private ImmutableList<Instrument> generateInstruments(int... mainMonths) {
        var instruments = MutableLists.<Instrument>newFastList();
        // 获取去年年份
        int preYear = LocalDate.now(exchange.getZoneOffset()).getYear() - 1;
        // 生成主力期货合约
        for (int mainMonth : mainMonths) {
            IntStream
                    .range(preYear, preYear + 3)
                    .map(year -> dateOfMonth(LocalDate.of(year, mainMonth, 1)) % 10000)
                    .forEach(term -> instruments.add(ChinaFuturesInstrument.newInstance(this, term)));
        }
        return instruments.toImmutable();
    }


    /**
     * @param symbolId int
     * @return ChinaFuturesSymbol
     */
    public static ChinaFuturesSymbol of(int symbolId) {
        var symbol = SYMBOL_MAP_BY_ID.get(symbolId);
        if (symbol == null)
            throw new IllegalArgumentException("symbolId -> " + symbolId + " is not mapping object");
        return symbol;
    }

    /**
     * @param symbolCode String
     * @return ChinaFuturesSymbol
     */
    public static ChinaFuturesSymbol of(String symbolCode) {
        var symbol = SYMBOL_MAP_BY_CODE.get(symbolCode);
        if (symbol == null)
            throw new IllegalArgumentException("symbolCode -> " + symbolCode + " is not mapping object");
        return symbol;
    }

    /**
     * @param term int
     * @return int
     */
    int acquireInstrumentId(int term) {
        if (term >= AbstractFutures.TERM_MASK)
            throw new IllegalArgumentException("term > 9999, Is too much.");
        return symbolId + term;
    }

    private String toStringCache;

    @Override
    public String toString() {
        if (toStringCache == null) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("exchangeCode", exchange.getExchangeCode());
            tempMap.put("symbolId", symbolId);
            tempMap.put("symbolCode", symbolCode);
            tempMap.put("priorityCloseType", priorityCloseType);
            tempMap.put("multiplier", multiplier);
            this.toStringCache = JsonWriter.toJson(tempMap);
        }
        return toStringCache;
    }

    public static void main(String[] args) {
        for (ChinaFuturesSymbol symbol : ChinaFuturesSymbol.values()) {
            symbol.getTradablePeriods().each(tradingPeriod -> {
                System.out.println(tradingPeriod);
                tradingPeriod
                        .segmentation(LocalDate.now(), symbol.getExchange().getZoneOffset(), Duration.ofSeconds(30))
                        .each(timePeriod -> System.out.println(symbol.getSymbolCode() + " | " + timePeriod));
            });
            symbol.getTradablePeriods()
                    .stream()
                    .map(tradingPeriod -> tradingPeriod.segmentation(LocalDate.now(),
                            symbol.getExchange().getZoneOffset(), Duration.ofSeconds(30)))
                    .forEach(list -> list.forEach(System.out::println));
        }
        System.out.println(ChinaFuturesSymbol.AG);
        System.out.println(ChinaFuturesSymbol.AG.getExchange().getExchangeId());
        System.out.println(ChinaFuturesSymbol.AG.getSymbolId());

        for (ChinaFuturesSymbol symbol : ChinaFuturesSymbol.values()) {
            symbol.getInstruments().each(System.out::println);
        }

        int year = LocalDate.now().getYear() - 1;

    }

}
