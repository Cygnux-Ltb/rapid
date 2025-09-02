package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.mdata.MarketDataBucket;
import io.cygnux.rapid.core.mdata.MarketDataSnapshot;
import io.cygnux.rapid.core.stream.StreamEventHandler;
import io.cygnux.rapid.core.stream.event.FastMarketData;
import io.cygnux.rapid.core.instrument.Instrument;

public interface MarketDataManager extends StreamEventHandler {

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
