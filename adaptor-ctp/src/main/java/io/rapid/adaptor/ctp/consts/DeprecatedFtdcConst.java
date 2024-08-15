package io.rapid.adaptor.ctp.consts;

import ctp.thostapi.thosttraderapiConstants;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.impl.bimap.immutable.ImmutableBiMapFactoryImpl;

import java.io.Serial;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Deprecated
public final class DeprecatedFtdcConst {

    // 方向常量
    public static final String DIRECTION_NONE = "NONE";                   // 无方向
    public static final String DIRECTION_LONG = "LONG";                   // 多
    public static final String DIRECTION_SHORT = "SHORT";                 // 空
    public static final String DIRECTION_UNKNOWN = "UNKNOWN";             // 未知
    public static final String DIRECTION_NET = "NET";                     // 净
    public static final String DIRECTION_SELL = "SELL";                   // 卖出 IB
    public static final String DIRECTION_COVERED_SHORT = "COVERED_SHORT"; // 备兑空 // 证券期权

    // 开平常量
    public static final String OFFSET_NONE = "NONE";                       // 无开平
    public static final String OFFSET_OPEN = "OPEN";                       // 开仓
    public static final String OFFSET_CLOSE = "CLOSE";                     // 平仓
    public static final String OFFSET_CLOSE_TODAY = "CLOSE_TODAY";         // 平今
    public static final String OFFSET_CLOSE_YESTERDAY = "CLOSE_YESTERDAY"; // 平昨
    public static final String OFFSET_UNKNOWN = "UNKNOWN";                 // 未知

    // 状态常量
    public static final String STATUS_NOT_TRADED = "NOT_TRADED";   // 未成交
    public static final String STATUS_PART_TRADED = "PART_TRADED"; // 部分成交
    public static final String STATUS_ALL_TRADED = "ALL_TRADED";   // 全部成交
    public static final String STATUS_CANCELLED = "CANCELLED";     // 已撤销
    public static final String STATUS_REJECTED = "REJECTED";       // 拒单
    public static final String STATUS_UNKNOWN = "UNKNOWN";         // 未知

    HashSet<String> STATUS_FINISHED = new HashSet<>() {
        @Serial
        private static final long serialVersionUID = 8777691797309945190L;

        {
            add(DeprecatedFtdcConst.STATUS_REJECTED);
            add(DeprecatedFtdcConst.STATUS_CANCELLED);
            add(DeprecatedFtdcConst.STATUS_ALL_TRADED);
        }
    };

    HashSet<String> STATUS_WORKING = new HashSet<>() {
        @Serial
        private static final long serialVersionUID = 909683985291870766L;

        {
            add(DeprecatedFtdcConst.STATUS_UNKNOWN);
            add(DeprecatedFtdcConst.STATUS_NOT_TRADED);
            add(DeprecatedFtdcConst.STATUS_PART_TRADED);
        }
    };

    // 合约类型常量
    public static final String PRODUCT_EQUITY = "EQUITY";           // 股票
    public static final String PRODUCT_FUTURES = "FUTURES";         // 期货
    public static final String PRODUCT_OPTION = "OPTION";           // 期权
    public static final String PRODUCT_INDEX = "INDEX";             // 指数
    public static final String PRODUCT_COMBINATION = "COMBINATION"; // 组合
    public static final String PRODUCT_FOREX = "FOREX";             // 外汇
    public static final String PRODUCT_UNKNOWN = "UNKNOWN";         // 未知
    public static final String PRODUCT_SPOT = "SPOT";               // 现货
    public static final String PRODUCT_DEFER = "DEFER ";            // 延期
    public static final String PRODUCT_ETF = "ETF";                 // ETF
    public static final String PRODUCT_WARRANT = "WARRANT";         // 权证
    public static final String PRODUCT_BOND = "BOND";               // 债券
    public static final String PRODUCT_NONE = "NONE";               // NONE

    // 价格类型常量
    public static final String PRICETYPE_LIMIT_PRICE = "LIMIT_PRICE";    // 限价
    public static final String PRICETYPE_MARKET_PRICE = "MARKET_PRICE "; // 市价
    public static final String PRICETYPE_FAK = "FAK";                    // FAK
    public static final String PRICETYPE_FOK = "FOK";                    // FOK

    // 期权类型
    public static final String OPTION_CALL = "CALL"; // 看涨期权
    public static final String OPTION_PUT = "PUT";   // 看跌期权

    // 交易所类型
    public static final String EXCHANGE_SSE = "SSE";         // 上交所
    public static final String EXCHANGE_SZSE = "SZSE";       // 深交所
    public static final String EXCHANGE_CFFEX = "CFFEX";     // 中金所
    public static final String EXCHANGE_SHFE = "SHFE";       // 上期所
    public static final String EXCHANGE_CZCE = "CZCE";       // 郑商所
    public static final String EXCHANGE_DCE = "DCE";         // 大商所
    public static final String EXCHANGE_SGE = "SGE";         // 上金所
    public static final String EXCHANGE_INE = "INE";         // 国际能源交易中心
    public static final String EXCHANGE_UNKNOWN = "UNKNOWN"; // 未知交易所
    public static final String EXCHANGE_NONE = "";           // 空交易所
    public static final String EXCHANGE_SEHK = "SEHK";       // 港交所
    public static final String EXCHANGE_HKFE = "HKFE";       // 香港期货交易所

    public static final String EXCHANGE_SMART = "SMART";       // IB智能路由(股票, 期权)
    public static final String EXCHANGE_NYMEX = "NYMEX";       // IB 期货
    public static final String EXCHANGE_GLOBEX = "GLOBEX";     // CME电子交易平台
    public static final String EXCHANGE_IDEALPRO = "IDEALPRO"; // IB外汇ECN

    public static final String EXCHANGE_CME = "CME"; // 芝商所
    public static final String EXCHANGE_ICE = "ICE"; // 洲际交易所
    public static final String EXCHANGE_LME = "LME"; // 伦敦金属交易所

    public static final String EXCHANGE_OANDA = "OANDA"; // OANDA外汇做市商
    public static final String EXCHANGE_FXCM = "FXCM";   // FXCM外汇做市商

    public static final String EXCHANGE_OKCOIN = "OKCOIN";       // OKCOIN比特币交易所
    public static final String EXCHANGE_HUOBI = "HUOBI";         // 火币比特币交易所
    public static final String EXCHANGE_LBANK = "LBANK";         // LBANK比特币交易所
    public static final String EXCHANGE_KORBIT = "KORBIT";       // KORBIT韩国交易所
    public static final String EXCHANGE_ZB = "ZB";               // 比特币中国比特币交易所
    public static final String EXCHANGE_OKEX = "OKEX";           // OKEX比特币交易所
    public static final String EXCHANGE_ZAIF = "ZAIF";           // ZAIF日本比特币交易所
    public static final String EXCHANGE_COINCHECK = "COINCHECK"; // COINCHECK日本比特币交易所

    /**
     * **************************** 以下为CTP常量映射 ******************************
     */
    public static final Map<String, Character> priceTypeMap = new HashMap<>();

    public static Map<Character, String> priceTypeMapReverse;

    static {
        // 价格类型映射
        priceTypeMap.put(DeprecatedFtdcConst.PRICETYPE_LIMIT_PRICE, thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice);
        priceTypeMap.put(DeprecatedFtdcConst.PRICETYPE_MARKET_PRICE, thosttraderapiConstants.THOST_FTDC_OPT_AnyPrice);
        priceTypeMapReverse = priceTypeMap.entrySet().stream()
                .collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

    // TODO 可扩展
    // 价格类型
    public static final ImmutableBiMap<String, Character> PriceTypeBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
            // 限价单
            DeprecatedFtdcConst.PRICETYPE_LIMIT_PRICE, thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice,
            // 市价单
            DeprecatedFtdcConst.PRICETYPE_MARKET_PRICE, thosttraderapiConstants.THOST_FTDC_OPT_AnyPrice);

    public static final Map<String, Character> directionMap = new HashMap<>();

    public static Map<Character, String> directionMapReverse;

    static {
        // 方向类型映射
        directionMap.put(DeprecatedFtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_D_Buy);
        directionMap.put(DeprecatedFtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_D_Sell);
        directionMapReverse = directionMap.entrySet().stream()
                .collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

    // 方向类型
    public static final ImmutableBiMap<String, Character> DirectionBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
            // 买
            DeprecatedFtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_D_Buy,
            // 卖
            DeprecatedFtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_D_Sell);

    public static final Map<String, Character> offsetMap = new HashMap<>();

    public static Map<Character, String> offsetMapReverse;

    static {
        // 开平类型映射
        offsetMap.put(DeprecatedFtdcConst.OFFSET_OPEN, thosttraderapiConstants.THOST_FTDC_OF_Open);
        offsetMap.put(DeprecatedFtdcConst.OFFSET_CLOSE, thosttraderapiConstants.THOST_FTDC_OF_Close);
        offsetMap.put(DeprecatedFtdcConst.OFFSET_CLOSE_TODAY, thosttraderapiConstants.THOST_FTDC_OF_CloseToday);
        offsetMap.put(DeprecatedFtdcConst.OFFSET_CLOSE_YESTERDAY, thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday);
        offsetMapReverse = offsetMap.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

    // 开平类型
    public static final ImmutableBiMap<String, Character> OffsetBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
            // 开仓
            DeprecatedFtdcConst.OFFSET_OPEN, thosttraderapiConstants.THOST_FTDC_OF_Open,
            // 平仓
            DeprecatedFtdcConst.OFFSET_CLOSE, thosttraderapiConstants.THOST_FTDC_OF_Close,
            // 平今(上期所)
            DeprecatedFtdcConst.OFFSET_CLOSE_TODAY, thosttraderapiConstants.THOST_FTDC_OF_CloseToday,
            // 平昨(上期所)
            DeprecatedFtdcConst.OFFSET_CLOSE_YESTERDAY, thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday);

    public static final Map<String, String> exchangeMap = new HashMap<>();
    public static Map<String, String> exchangeMapReverse;

    static {
        // 交易所映射
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_CFFEX, "CFFEX");
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_SHFE, "SHFE");
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_CZCE, "CZCE");
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_DCE, "DCE");
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_SSE, "SSE");
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_SZSE, "SZSE");
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_INE, "INE");
        exchangeMap.put(DeprecatedFtdcConst.EXCHANGE_UNKNOWN, "");
        exchangeMapReverse = exchangeMap.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

    public static final Map<String, Character> posiDirectionMap = new HashMap<>();

    public static final Map<Character, String> posiDirectionMapReverse;

    static {
        // 持仓类型映射
        posiDirectionMap.put(DeprecatedFtdcConst.DIRECTION_NET, thosttraderapiConstants.THOST_FTDC_PD_Net);
        posiDirectionMap.put(DeprecatedFtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_PD_Long);
        posiDirectionMap.put(DeprecatedFtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_PD_Short);
        posiDirectionMapReverse = posiDirectionMap.entrySet().stream()
                .collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

    // 持仓类型
    public static final ImmutableBiMap<String, Character> PosiDirectionBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
            //
            DeprecatedFtdcConst.DIRECTION_NET, thosttraderapiConstants.THOST_FTDC_PD_Net,
            //
            DeprecatedFtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_PD_Long,
            //
            DeprecatedFtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_PD_Short);

    public static final Map<String, Character> productClassMap = new HashMap<>();
    public static Map<Character, String> productClassMapReverse;

    static {
        // 产品类型映射
        productClassMap.put(DeprecatedFtdcConst.PRODUCT_FUTURES, thosttraderapiConstants.THOST_FTDC_PC_Futures);
        productClassMap.put(DeprecatedFtdcConst.PRODUCT_OPTION, thosttraderapiConstants.THOST_FTDC_PC_Options);
        productClassMap.put(DeprecatedFtdcConst.PRODUCT_COMBINATION, thosttraderapiConstants.THOST_FTDC_PC_Combination);
        productClassMapReverse = productClassMap.entrySet().stream()
                .collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

    // 产品类型
    public static final ImmutableBiMap<String, Character> ProductClassBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
            // 期货
            DeprecatedFtdcConst.PRODUCT_FUTURES, thosttraderapiConstants.THOST_FTDC_PC_Futures,
            // 期权
            DeprecatedFtdcConst.PRODUCT_OPTION, thosttraderapiConstants.THOST_FTDC_PC_Options,
            // 组合
            DeprecatedFtdcConst.PRODUCT_COMBINATION, thosttraderapiConstants.THOST_FTDC_PC_Combination);

    public static final Map<String, Character> statusMap = new HashMap<>();

    public static Map<Character, String> statusMapReverse;

    static {
        // 委托状态映射
        statusMap.put(DeprecatedFtdcConst.STATUS_ALL_TRADED, thosttraderapiConstants.THOST_FTDC_OST_AllTraded);
        statusMap.put(DeprecatedFtdcConst.STATUS_PART_TRADED, thosttraderapiConstants.THOST_FTDC_OST_PartTradedQueueing);
        statusMap.put(DeprecatedFtdcConst.STATUS_NOT_TRADED, thosttraderapiConstants.THOST_FTDC_OST_NoTradeQueueing);
        statusMap.put(DeprecatedFtdcConst.STATUS_CANCELLED, thosttraderapiConstants.THOST_FTDC_OST_Canceled);
        statusMap.put(DeprecatedFtdcConst.STATUS_UNKNOWN, thosttraderapiConstants.THOST_FTDC_OST_Unknown);
        statusMapReverse = statusMap.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey));
    }

}
