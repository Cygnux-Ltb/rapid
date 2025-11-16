package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.mdata.MarketDataBucket;
import io.cygnux.rapid.core.mdata.MarketDataSnapshot;
import io.cygnux.rapid.core.shared.SharedEventHandler;
import io.cygnux.rapid.core.shared.event.DepthMarketData;
import io.cygnux.rapid.core.shared.event.FastMarketData;
import io.cygnux.rapid.core.shared.event.InstrumentStatusReport;

public interface MarketDataManager extends SharedEventHandler {

    @Override
    default void fireInstrumentStatusReport(InstrumentStatusReport report) {
        onInstrumentStatusReport(report);
    }

    void onInstrumentStatusReport(InstrumentStatusReport report);

    @Override
    default void fireFastMarketData(FastMarketData marketData) {
        onMarketData(marketData);
    }

    default void onMarketData(FastMarketData marketData) {
        getBucket(marketData.getInstrumentCode()).onMarketData(marketData);
    }

    @Override
    default void fireDepthMarketData(DepthMarketData marketData) {

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
