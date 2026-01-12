package io.cygnux.rapid.core.event.received;

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
    public void copyOf(DepthMarketData source) {
        // 复制时间戳
        this.epochMicros = source.epochMicros;
        // 复制交易标的ID
        this.instrumentId = source.instrumentId;
        // 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 复制最新价格
        this.lastPrice = source.lastPrice;
        // 复制成交量
        this.volume = source.volume;
        // 复制成交额
        this.turnover = source.turnover;
        // 复制买价列表
        this.bidPrices = source.bidPrices;
        // 复制买量列表
        this.bidVolumes = source.bidVolumes;
        // 复制卖价列表
        this.askPrices = source.askPrices;
        // 复制卖量列表
        this.askVolumes = source.askVolumes;
        // 复制深度
        this.depth = source.depth;

        this.tradingDay = source.tradingDay;
        this.updateTime = source.updateTime;
        this.updateMillisec = source.updateMillisec;
    }

}