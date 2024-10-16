package io.rapid.core.mdata;

import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.instrument.Instrument;

public interface MarketDataManager {

    default void onMarketData(RawMarketData marketData) {
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
