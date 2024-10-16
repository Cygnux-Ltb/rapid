package io.rapid.core.event.inbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.rapid.core.mdata.SavedMarketData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 统一行情处理数据结构
 * (由外部输入)
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class RawMarketData extends JsonBean implements Copyable<RawMarketData> {

    /**
     * 交易标的ID [*]
     */
    private int instrumentId;
    /**
     * 交易标的代码 [*]
     */
    private String instrumentCode;
    /**
     * 最新价
     */
    private double lastPrice;
    /**
     * 成交量
     */
    private int volume;
    /**
     * 成交量偏移
     */
    private int volumeDelta;
    /**
     * 成交金额
     */
    private double turnover;
    /**
     * 成交金额偏移
     */
    private double turnoverDelta;
    /**
     * 持仓量
     */
    private double openInterest;
    /**
     * 持仓量偏移
     */
    private double openInterestDelta;
    /**
     * 今开盘价
     */
    private double openPrice;
    /**
     * 今最高价
     */
    private double highestPrice;
    /**
     * 今最低价
     */
    private double lowestPrice;
    /**
     * 今涨停板价
     */
    private double upperLimitPrice;
    /**
     * 今跌停板价
     */
    private double lowerLimitPrice;
    /**
     * 买价1
     */
    private double bidPrice1;
    /**
     * 买量1
     */
    private int bidVolume1;
    /**
     * 卖价1
     */
    private double askPrice1;
    /**
     * 卖量1
     */
    private int askVolume1;
    /**
     * 平均价格
     */
    private double averagePrice;

    /// 复合时间坐标 ///
    /**
     * Epoch微秒 [*]
     */
    private long epochMicros;
    /**
     * 交易日
     */
    private int tradingDay;
    /**
     * 实际日期
     */
    private int actualDate;
    /**
     * 更新时间
     */
    private int updateTime;
    /**
     * 更新毫秒数
     */
    private int updateMillisec;

    public long getEpochMillis() {
        return epochMicros / 1000;
    }

    @Override
    public void copyFrom(RawMarketData source) {
        /// 复制交易标的ID
        this.instrumentId = source.instrumentId;
        /// 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        /// 复制最新价格
        this.lastPrice = source.lastPrice;
        /// 复制成交量
        this.volume = source.volume;
        /// 复制成交量偏移
        this.volumeDelta = source.volumeDelta;
        /// 复制成交金额
        this.turnover = source.turnover;
        /// 复制成交金额偏移
        this.turnoverDelta = source.turnoverDelta;
        /// 复制持仓量
        this.openInterest = source.openInterest;
        /// 复制持仓量偏移
        this.openInterestDelta = source.openInterestDelta;
        /// 复制今开盘价
        this.openPrice = source.openPrice;
        /// 复制今最高价
        this.highestPrice = source.highestPrice;
        /// 复制今最低价
        this.lowestPrice = source.lowestPrice;
        /// 复制今涨停板价
        this.upperLimitPrice = source.upperLimitPrice;
        /// 复制今跌停板价
        this.lowerLimitPrice = source.lowerLimitPrice;
        /// 复制买价1
        this.bidPrice1 = source.bidPrice1;
        /// 复制买量1
        this.bidVolume1 = source.bidVolume1;
        /// 复制卖价1
        this.askPrice1 = source.askPrice1;
        /// 复制卖量1
        this.askVolume1 = source.askVolume1;
        /// 复制平均价格
        this.averagePrice = source.averagePrice;

        /// 复制时间坐标相关字段
        this.epochMicros = source.epochMicros;
        this.tradingDay = source.tradingDay;
        this.actualDate = source.actualDate;
        this.updateTime = source.updateTime;
        this.updateMillisec = source.updateMillisec;
    }

    public SavedMarketData dump() {
        return new SavedMarketData(
                this.instrumentId,
                this.instrumentCode,
                this.lastPrice,
                this.volume,
                this.volumeDelta,
                this.turnover,
                this.turnoverDelta,
                this.openPrice,
                this.highestPrice,
                this.lowestPrice,
                this.upperLimitPrice,
                this.lowerLimitPrice,
                this.openInterest,
                this.bidPrice1,
                this.bidVolume1,
                this.askPrice1,
                this.askVolume1,
                this.averagePrice,
                this.epochMicros,
                this.tradingDay,
                this.actualDate,
                this.updateTime,
                this.updateMillisec
        );
    }

    public RawMarketData newInstance() {
        var instance = new RawMarketData();
        instance.copyFrom(this);
        return instance;
    }

}