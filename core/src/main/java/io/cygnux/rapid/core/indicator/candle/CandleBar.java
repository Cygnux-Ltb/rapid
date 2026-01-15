package io.cygnux.rapid.core.indicator.candle;

import io.cygnux.rapid.core.indicator.AbstractPoint;
import io.cygnux.rapid.core.indicator.structure.Bar;
import io.cygnux.rapid.core.types.mkd.SavedMarketData;
import lombok.Getter;

public final class CandleBar extends AbstractPoint {

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
    protected void handleMarketData0(SavedMarketData marketData) {

    }

    @Override
    public long orderNum() {
        return 0;
    }

}
