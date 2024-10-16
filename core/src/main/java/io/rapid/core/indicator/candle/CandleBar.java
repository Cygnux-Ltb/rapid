package io.rapid.core.indicator.candle;

import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.indicator.base.BasePoint;
import io.rapid.core.indicator.structure.Bar;
import lombok.Getter;

public final class CandleBar extends BasePoint {

    @Getter
    private final Bar bar;

    @Getter
    private final CandlePeriod period;

    @Getter
    private final long startTime;

    // 总成交量
    @Getter
    private long volume = 0L;

    // 总成交金额
    @Getter
    private long turnover = 0L;

    public CandleBar(int index, double open, CandlePeriod period, long startTime) {
        super(index);
        this.bar = new Bar(open);
        this.period = period;
        this.startTime = startTime;
    }

    @Override
    protected void handleMarketData0(RawMarketData marketData) {

    }

    @Override
    public long orderNum() {
        return 0;
    }
}
