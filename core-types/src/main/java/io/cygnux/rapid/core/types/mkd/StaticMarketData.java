package io.cygnux.rapid.core.types.mkd;

/**
 * 静态行情数据
 * <br>
 * @param tradingDay         交易日
 * @param upperLimitPrice    今涨停板价
 * @param lowerLimitPrice    今跌停板价
 * @param preSettlementPrice 昨结算价
 * @param preClosePrice      昨收盘
 * @param preOpenInterest    昨持仓量
 */
public record StaticMarketData(
        // * 交易日
        int tradingDay,
        // * 今涨停板价
        double upperLimitPrice,
        // * 今跌停板价
        double lowerLimitPrice,
        // * 昨结算价
        double preSettlementPrice,
        // * 昨收盘
        double preClosePrice,
        // * 昨持仓量
        double preOpenInterest
) {
}
