package io.rapid.core.mdata;

public class StaticMarketData {

    /**
     * 交易日
     */
    protected int tradingDay;
    /**
     * 今涨停板价
     */
    protected double upperLimitPrice;
    /**
     * 今跌停板价
     */
    protected double lowerLimitPrice;
    /**
     * 昨结算价
     */
    protected double preSettlementPrice;
    /**
     * 昨收盘
     */
    protected double preClosePrice;
    /**
     * 昨持仓量
     */
    protected double preOpenInterest;

}
