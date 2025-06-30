package io.cygnux.rapid.core.mdata;

import io.cygnux.rapid.core.event.inbound.FastMarketData;
import io.cygnux.rapid.core.instrument.Instrument;

public interface MarketDataManager {

    default void onMarketData(FastMarketData marketData) {
        getBucket(marketData.getInstrumentCode()).onMarketData(marketData);
    }

    MarketDataBucket getBucket(String instrumentCode);

    default MarketDataBucket getBucket(Instrument instrument) {
        return getBucket(instrument.getInstrumentCode());
    }

    default MarketDataSnapshot getSnapshot(Instrument instrument) {
        return getSnapshot(instrument.getInstrumentCode());
    }

    default MarketDataSnapshot getSnapshot(String instrumentCode) {
        return getBucket(instrumentCode).getSnapshot();
    }


}
