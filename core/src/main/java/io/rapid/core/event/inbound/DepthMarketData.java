package io.rapid.core.event.inbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 深度行情数据结构
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepthMarketData extends JsonBean implements Copyable<DepthMarketData> {

    /**
     * 接收时[Epoch微秒]
     */
    private long epochMicros;

    private int instrumentId;
    private String instrumentCode;
    private double lastPrice;
    private int volume;
    private long turnover;
    private double[] bidPrices;
    private int[] bidVolumes;
    private double[] askPrices;
    private int[] askVolumes;
    private int depth;

    private String tradingDay;
    private String updateTime;
    private int updateMillisec;

    @Override
    public void copyFrom(DepthMarketData source) {
        // 复制时间戳
        this.epochMicros = source.getEpochMicros();
        // 复制交易标的ID
        this.instrumentId = source.getInstrumentId();
        // 复制交易标的代码
        this.instrumentCode = source.getInstrumentCode();
        // 复制最新价格
        this.lastPrice = source.getLastPrice();
        // 复制成交量
        this.volume = source.getVolume();
        // 复制成交额
        this.turnover = source.getTurnover();
        // 复制买价列表
        this.bidPrices = source.getBidPrices();
        // 复制买量列表
        this.bidVolumes = source.getBidVolumes();
        // 复制卖价列表
        this.askPrices = source.getAskPrices();
        // 复制卖量列表
        this.askVolumes = source.getAskVolumes();
        // 复制深度
        this.depth = source.getDepth();

        this.tradingDay = source.getTradingDay();
        this.updateTime = source.getUpdateTime();
        this.updateMillisec = source.getUpdateMillisec();
    }

}