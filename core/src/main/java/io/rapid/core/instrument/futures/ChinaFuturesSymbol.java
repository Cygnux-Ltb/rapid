package io.rapid.core.instrument.futures;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonWriter;
import io.rapid.core.instrument.Exchange;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.Symbol;
import io.rapid.core.instrument.base.BaseFutures;
import io.rapid.core.instrument.base.TradingPeriod;
import io.rapid.core.instrument.enums.PriceMultiplier;
import io.rapid.core.instrument.enums.PriorityCloseType;
import lombok.Getter;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static io.mercury.common.collections.ImmutableLists.newImmutableList;

/**
 * ChinaFutures Symbol
 *
 * @author yellow013
 */
public enum ChinaFuturesSymbol implements Symbol {

    // ************************上海期货交易所************************//
    /**
     * 铜 cu
     */
    CU(Exchange.SHFE, "cu", 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 铜期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(1, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 铝 al
     */
    AL(Exchange.SHFE, "al", 2,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 铝期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(1, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 锌 zn
     */
    ZN(Exchange.SHFE, "zn", 3,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 锌期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(1, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 镍
     */
    NI(Exchange.SHFE, "ni", 4,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 镍期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(1, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 黄金
     */
    AU(Exchange.SHFE, "au", 5,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_4
            // 黄金期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(2, 30, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            6, 12
    ),

    /**
     * 白银
     */
    AG(Exchange.SHFE, "ag",
            6,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 白银期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(2, 30, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            6, 12
    ),

    /**
     * 螺纹钢
     */
    RB(Exchange.SHFE, "rb", 7,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 螺纹钢期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 10
    ),

    /**
     * 热卷扎板
     */
    HC(Exchange.SHFE, "hc", 8,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 热卷扎板期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 10
    ),

    /**
     * 橡胶
     */
    RU(Exchange.SHFE, "ru", 9,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 橡胶期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 燃油
     */
    FU(Exchange.SHFE, "fu", 10,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 燃油期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    // ************************上海能源交易所************************//
    /**
     * 原油
     */
    SC(Exchange.SHINE, "sc", 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 原油期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(1, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    // **************************大连商品交易所*************************//
    /**
     * 大豆 a
     */
    A(Exchange.DCE, "a", 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 大豆期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 豆粕 m
     */
    M(Exchange.DCE, "m", 2,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 豆粕期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 豆油 y
     */
    Y(Exchange.DCE, "y", 3,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 豆油期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 棕榈油 p
     */
    P(Exchange.DCE, "p", 4,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 棕榈油期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 玉米 p
     */
    C(Exchange.DCE, "c", 4,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 棕榈油期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 铁矿石 i
     */
    I(Exchange.DCE, "i", 5,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2
            // 铁矿石期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    // 大商所品种 : 塑料, PVC, PP,
    // *****************************郑州商品交易所***********************************//
    /**
     * 棉花 cf
     */
    CF(Exchange.ZCE, "CF", 1,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 棉花交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(3, LocalTime.of(13, 30, 0),
                    LocalTime.of(15, 0, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 白糖 sr
     */
    SR(Exchange.ZCE, "SR", 2,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 白糖交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(15, 15, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * PTA
     */
    TA(Exchange.ZCE, "TA", 3,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // PTA交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(15, 15, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 乙醇
     */
    MA(Exchange.ZCE, "MA", 4,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 乙醇交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(15, 15, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    /**
     * 菜粕
     */
    RM(Exchange.ZCE, "RM", 5,
            PriorityCloseType.NONE, PriceMultiplier.NONE
            // 菜粕交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(21, 0, 0),
                    LocalTime.of(23, 0, 0)),
            new TradingPeriod(1, LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 15, 0)),
            new TradingPeriod(2, LocalTime.of(10, 30, 0),
                    LocalTime.of(15, 15, 0))),
            // 主力合约月份
            1, 5, 9
    ),

    // ************************中国金融交易所************************//
    /**
     * 沪深300
     */
    IF(Exchange.CFFEX, "IF", 1,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2
            // 股指期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(9, 15, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(1, LocalTime.of(13, 0, 0),
                    LocalTime.of(15, 15, 0))),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 上证50
     */
    IH(Exchange.CFFEX, "IH", 2,
            PriorityCloseType.NONE, PriceMultiplier.MULTIPLIER_2
            // 股指期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(9, 15, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(1, LocalTime.of(13, 0, 0),
                    LocalTime.of(15, 15, 0))),
            // 主力合约月份
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    ),

    /**
     * 中证500
     */
    IC(Exchange.CFFEX, "IC", 3,
            PriorityCloseType.NONE,
            PriceMultiplier.MULTIPLIER_2
            // 股指期货交易时段
            , newImmutableList(
            new TradingPeriod(0, LocalTime.of(9, 15, 0),
                    LocalTime.of(11, 30, 0)),
            new TradingPeriod(1, LocalTime.of(13, 0, 0),
                    LocalTime.of(15, 15, 0))),
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
    private int tickSize = 1;

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
    ChinaFuturesSymbol(Exchange exchange, String symbolCode, int sortingInExchange,
                       PriorityCloseType priorityCloseType, PriceMultiplier multiplier,
                       // 交易时间段列表
                       ImmutableList<TradingPeriod> tradingPeriods,
                       // 主力合约月份
                       int... mainMonths) {
        this.exchange = exchange;
        this.symbolId = BaseFutures.generateSymbolId(exchange.getExchangeId(), sortingInExchange);
        this.symbolCode = symbolCode;
        this.priorityCloseType = priorityCloseType;
        this.multiplier = multiplier;
        this.tradablePeriods = tradingPeriods;
        this.instruments = generateInstruments(mainMonths);
    }

    // symbolId -> symbol映射
    private final static ImmutableIntObjectMap<ChinaFuturesSymbol> SYMBOL_ID_MAP;

    // symbolCode -> symbol的映射
    private final static ImmutableMap<String, ChinaFuturesSymbol> SYMBOL_CODE_MAP;

    static {
        var symbolIdMap = MutableMaps.<ChinaFuturesSymbol>newIntObjectMap();
        var symbolCodeMap = MutableMaps.<String, ChinaFuturesSymbol>newUnifiedMap();
        for (var symbol : ChinaFuturesSymbol.values()) {
            symbolIdMap.put(symbol.getSymbolId(), symbol);
            symbolCodeMap.put(symbol.getSymbolCode().toLowerCase(), symbol);
            symbolCodeMap.put(symbol.getSymbolCode().toUpperCase(), symbol);
        }
        SYMBOL_ID_MAP = symbolIdMap.toImmutable();
        SYMBOL_CODE_MAP = symbolCodeMap.toImmutable();
    }

    /**
     * 以主力合约月份构建当年, 次年, 下一个次年的合约列表
     *
     * @return ImmutableList<Instrument>
     */
    private ImmutableList<Instrument> generateInstruments(int... mainMonths) {
        MutableList<Instrument> instruments = MutableLists.newFastList();
        // 获取去年年份
        int preYear = LocalDate.now(exchange.getZoneOffset()).getYear() - 1;
        // 生成主力期货合约
        for (int mainMonth : mainMonths) {
            IntStream.range(preYear, preYear + 3)
                    .map(year -> DateTimeUtil.dateOfMonth(LocalDate.of(year, mainMonth, 1)) % 10000)
                    .forEach(term -> instruments.add(ChinaFuturesInstrument.newInstance(this, term)));
        }
        return instruments.toImmutable();
    }


    /**
     * @param symbolId int
     * @return ChinaFuturesSymbol
     */
    public static ChinaFuturesSymbol of(int symbolId) {
        var symbol = SYMBOL_ID_MAP.get(symbolId);
        if (symbol == null)
            throw new IllegalArgumentException("symbolId -> " + symbolId + " is not mapping object");
        return symbol;
    }

    /**
     * @param symbolCode String
     * @return ChinaFuturesSymbol
     */
    public static ChinaFuturesSymbol of(String symbolCode) {
        var symbol = SYMBOL_CODE_MAP.get(symbolCode);
        if (symbol == null)
            throw new IllegalArgumentException("symbolCode -> " + symbolCode + " is not mapping object");
        return symbol;
    }

    /**
     * @param term int
     * @return int
     */
    int acquireInstrumentId(int term) {
        if (term > 9999)
            throw new IllegalArgumentException("term > 9999, Is too much.");
        return symbolId + term;
    }

    @Override
    public String format() {
        return toString();
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
        System.out.println(ChinaFuturesSymbol.AG.format());
        System.out.println(ChinaFuturesSymbol.AG.getExchange().getExchangeId());
        System.out.println(ChinaFuturesSymbol.AG.getSymbolId());

        for (ChinaFuturesSymbol symbol : ChinaFuturesSymbol.values()) {
            symbol.getInstruments().each(instrument -> System.out.println(instrument.format()));
        }

        int year = LocalDate.now().getYear() - 1;


    }
}
