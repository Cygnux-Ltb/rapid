package io.cygnux.rapid.core.mdata;

/**
 * @param instrumentId    交易标的ID [*]
 * @param instrumentCode  交易标的代码 [*]
 * @param lastPrice       最新价
 * @param volume          成交量
 * @param totalVolume     今总成交量
 * @param turnover        成交金额
 * @param totalTurnover   今总成交金额
 * @param openPrice       今开盘价
 * @param highestPrice    今最高价
 * @param lowestPrice     今最低价
 * @param upperLimitPrice 今涨停板价
 * @param lowerLimitPrice 今跌停板价
 * @param openInterest    今持仓量
 * @param bidPrice1       买价1
 * @param bidVolume1      买量1
 * @param askPrice1       卖价1
 * @param askVolume1      卖量1
 * @param averagePrice    平均价格
 * @param epochMicros     Epoch微秒 [*]
 * @param tradingDay      交易日
 * @param actualDate      实际日期
 * @param updateTime      更新时间
 * @param updateMillisec  更新毫秒数
 */
public record SavedMarketData(
        int instrumentId,
        String instrumentCode,
        double lastPrice,
        int volume,
        int totalVolume,
        double turnover,
        double totalTurnover,
        double openPrice,
        double highestPrice,
        double lowestPrice,
        double upperLimitPrice,
        double lowerLimitPrice,
        double openInterest,
        double bidPrice1,
        int bidVolume1,
        double askPrice1,
        int askVolume1,
        double averagePrice,
        long epochMicros,
        int tradingDay,
        int actualDate,
        int updateTime,
        int updateMillisec) {

    /// 复合时间坐标 ///
    public long getEpochMillis() {
        return epochMicros / 1000;
    }

}
