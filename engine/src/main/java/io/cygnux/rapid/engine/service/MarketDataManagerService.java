package io.cygnux.rapid.engine.service;

import io.cygnux.rapid.core.mdata.MarketDataBucket;
import io.cygnux.rapid.core.mdata.MarketDataKeeper;
import io.cygnux.rapid.core.manager.MarketDataManager;
import io.cygnux.rapid.core.event.received.InstrumentStatusReport;
import org.springframework.stereotype.Service;

@Service
public class MarketDataManagerService implements MarketDataManager {

    private final MarketDataKeeper keeper = new MarketDataKeeper();

    @Override
    public void onInstrumentStatusReport(InstrumentStatusReport report) {

    }

    @Override
    public MarketDataBucket getBucket(String instrumentCode) {
        return keeper.get(instrumentCode);
    }

}
