package io.rapid.core.mdata;

import io.rapid.core.event.inbound.RawMarketData;
import lombok.Getter;

@Getter
public final class MarketDataSnapshot {

    /**
     * 买价
     */
    private volatile double bidPrice1;
    private volatile int bidVolume1;

    /**
     * 卖价
     */
    private volatile double askPrice1;
    private volatile int askVolume1;

    MarketDataSnapshot() {
    }

    void updateWith(RawMarketData marketData) {
        bidPrice1 = marketData.getBidPrice1();
        bidVolume1 = marketData.getBidVolume1();
        askPrice1 = marketData.getAskPrice1();
        askVolume1 = marketData.getAskVolume1();
    }

}