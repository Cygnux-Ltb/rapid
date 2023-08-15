package io.cygnuxltb.jcts.core.mkd.impl;

/**
 * 统一行情处理数据结构
 *
 * @author yellow013
 * @creation 2019年5月24日
 * @description 价格转换使用对应Instrument的价格乘数
 */
public class FastMarketData {

    // 标的代码 [Required]
    protected String instrumentCode;

    // Epoch [Required]
    protected long epochMillis;

    // 行情深度 [Required]
    protected int depth;

    // 最新价
    protected double lastPrice;

    // 成交量
    protected int volume;

    // 成交金额
    protected double turnover;

    // 交易日
    protected String tradingDay;

    // 上次结算价
    protected double preSettlementPrice;

    // 昨收盘
    protected double preClosePrice;

    // 昨持仓量
    protected double preOpenInterest;

    // 开盘价
    protected double openPrice;

    // 最高价
    protected double highestPrice;

    // 最低价
    protected double lowestPrice;

    // 持仓量
    protected double openInterest;

    // 收盘价
    protected double closePrice;

    // 结算价
    protected double settlementPrice;

    // 涨停板价
    protected double upperLimitPrice;

    // 跌停板价
    protected double lowerLimitPrice;

    // 昨Delta
    protected double preDelta;

    // 今Delta
    protected double currDelta;

    /* 五档买价卖价及买量卖量 v */
    protected double bidPrice1;
    protected int bidVolume1;
    protected double askPrice1;
    protected int askVolume1;
    protected double bidPrice2;
    protected int bidVolume2;
    protected double askPrice2;
    protected int askVolume2;
    protected double bidPrice3;
    protected int bidVolume3;
    protected double askPrice3;
    protected int askVolume3;
    protected double bidPrice4;
    protected int bidVolume4;
    protected double askPrice4;
    protected int askVolume4;
    protected double bidPrice5;
    protected int bidVolume5;
    protected double askPrice5;
    protected int askVolume5;
    /* 五档买价卖价及买量卖量 ^ */

    // 平均价格
    protected double averagePrice;

    // 更新时间
    protected String updateTime;

    // 更新毫秒数
    protected int updateMillisec;

    // 业务日期
    protected String actionDay;


    public String getInstrumentCode() {
        return instrumentCode;
    }

    public long getEpochMillis() {
        return epochMillis;
    }

    public int getDepth() {
        return depth;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public int getVolume() {
        return volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public String getTradingDay() {
        return tradingDay;
    }

    public double getPreSettlementPrice() {
        return preSettlementPrice;
    }

    public double getPreClosePrice() {
        return preClosePrice;
    }

    public double getPreOpenInterest() {
        return preOpenInterest;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public double getOpenInterest() {
        return openInterest;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public double getUpperLimitPrice() {
        return upperLimitPrice;
    }

    public double getLowerLimitPrice() {
        return lowerLimitPrice;
    }

    public double getPreDelta() {
        return preDelta;
    }

    public double getCurrDelta() {
        return currDelta;
    }

    public double getBidPrice1() {
        return bidPrice1;
    }

    public int getBidVolume1() {
        return bidVolume1;
    }

    public double getAskPrice1() {
        return askPrice1;
    }

    public int getAskVolume1() {
        return askVolume1;
    }

    public double getBidPrice2() {
        return bidPrice2;
    }

    public int getBidVolume2() {
        return bidVolume2;
    }

    public double getAskPrice2() {
        return askPrice2;
    }

    public int getAskVolume2() {
        return askVolume2;
    }

    public double getBidPrice3() {
        return bidPrice3;
    }

    public int getBidVolume3() {
        return bidVolume3;
    }

    public double getAskPrice3() {
        return askPrice3;
    }

    public int getAskVolume3() {
        return askVolume3;
    }

    public double getBidPrice4() {
        return bidPrice4;
    }

    public int getBidVolume4() {
        return bidVolume4;
    }

    public double getAskPrice4() {
        return askPrice4;
    }

    public int getAskVolume4() {
        return askVolume4;
    }

    public double getBidPrice5() {
        return bidPrice5;
    }

    public int getBidVolume5() {
        return bidVolume5;
    }

    public double getAskPrice5() {
        return askPrice5;
    }

    public int getAskVolume5() {
        return askVolume5;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getUpdateMillisec() {
        return updateMillisec;
    }

    public String getActionDay() {
        return actionDay;
    }

    public FastMarketData setInstrumentCode(String instrumentCode) {
        this.instrumentCode = instrumentCode;
        return this;
    }

    public FastMarketData setEpochMillis(long epochMillis) {
        this.epochMillis = epochMillis;
        return this;
    }

    public FastMarketData setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public FastMarketData setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
        return this;
    }

    public FastMarketData setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public FastMarketData setTurnover(double turnover) {
        this.turnover = turnover;
        return this;
    }

    public FastMarketData setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay;
        return this;
    }

    public FastMarketData setPreSettlementPrice(double preSettlementPrice) {
        this.preSettlementPrice = preSettlementPrice;
        return this;
    }

    public FastMarketData setPreClosePrice(double preClosePrice) {
        this.preClosePrice = preClosePrice;
        return this;
    }

    public FastMarketData setPreOpenInterest(double preOpenInterest) {
        this.preOpenInterest = preOpenInterest;
        return this;
    }

    public FastMarketData setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
        return this;
    }

    public FastMarketData setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
        return this;
    }

    public FastMarketData setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
        return this;
    }

    public FastMarketData setOpenInterest(double openInterest) {
        this.openInterest = openInterest;
        return this;
    }

    public FastMarketData setClosePrice(double closePrice) {
        this.closePrice = closePrice;
        return this;
    }

    public FastMarketData setSettlementPrice(double settlementPrice) {
        this.settlementPrice = settlementPrice;
        return this;
    }

    public FastMarketData setUpperLimitPrice(double upperLimitPrice) {
        this.upperLimitPrice = upperLimitPrice;
        return this;
    }

    public FastMarketData setLowerLimitPrice(double lowerLimitPrice) {
        this.lowerLimitPrice = lowerLimitPrice;
        return this;
    }

    public FastMarketData setPreDelta(double preDelta) {
        this.preDelta = preDelta;
        return this;
    }

    public FastMarketData setCurrDelta(double currDelta) {
        this.currDelta = currDelta;
        return this;
    }

    public FastMarketData setBidPrice1(double bidPrice1) {
        this.bidPrice1 = bidPrice1;
        return this;
    }

    public FastMarketData setBidVolume1(int bidVolume1) {
        this.bidVolume1 = bidVolume1;
        return this;
    }

    public FastMarketData setAskPrice1(double askPrice1) {
        this.askPrice1 = askPrice1;
        return this;
    }

    public FastMarketData setAskVolume1(int askVolume1) {
        this.askVolume1 = askVolume1;
        return this;
    }

    public FastMarketData setBidPrice2(double bidPrice2) {
        this.bidPrice2 = bidPrice2;
        return this;
    }

    public FastMarketData setBidVolume2(int bidVolume2) {
        this.bidVolume2 = bidVolume2;
        return this;
    }

    public FastMarketData setAskPrice2(double askPrice2) {
        this.askPrice2 = askPrice2;
        return this;
    }

    public FastMarketData setAskVolume2(int askVolume2) {
        this.askVolume2 = askVolume2;
        return this;
    }

    public FastMarketData setBidPrice3(double bidPrice3) {
        this.bidPrice3 = bidPrice3;
        return this;
    }

    public FastMarketData setBidVolume3(int bidVolume3) {
        this.bidVolume3 = bidVolume3;
        return this;
    }

    public FastMarketData setAskPrice3(double askPrice3) {
        this.askPrice3 = askPrice3;
        return this;
    }

    public FastMarketData setAskVolume3(int askVolume3) {
        this.askVolume3 = askVolume3;
        return this;
    }

    public FastMarketData setBidPrice4(double bidPrice4) {
        this.bidPrice4 = bidPrice4;
        return this;
    }

    public FastMarketData setBidVolume4(int bidVolume4) {
        this.bidVolume4 = bidVolume4;
        return this;
    }

    public FastMarketData setAskPrice4(double askPrice4) {
        this.askPrice4 = askPrice4;
        return this;
    }

    public FastMarketData setAskVolume4(int askVolume4) {
        this.askVolume4 = askVolume4;
        return this;
    }

    public FastMarketData setBidPrice5(double bidPrice5) {
        this.bidPrice5 = bidPrice5;
        return this;
    }

    public FastMarketData setBidVolume5(int bidVolume5) {
        this.bidVolume5 = bidVolume5;
        return this;
    }

    public FastMarketData setAskPrice5(double askPrice5) {
        this.askPrice5 = askPrice5;
        return this;
    }

    public FastMarketData setAskVolume5(int askVolume5) {
        this.askVolume5 = askVolume5;
        return this;
    }

    public FastMarketData setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public FastMarketData setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public FastMarketData setUpdateMillisec(int updateMillisec) {
        this.updateMillisec = updateMillisec;
        return this;
    }

    public FastMarketData setActionDay(String actionDay) {
        this.actionDay = actionDay;
        return this;
    }

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);

    }

}
