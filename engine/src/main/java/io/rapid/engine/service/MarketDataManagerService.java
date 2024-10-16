package io.rapid.engine.service;

import io.rapid.core.mdata.MarketDataBucket;
import io.rapid.core.mdata.MarketDataKeeper;
import io.rapid.core.mdata.MarketDataManager;
import org.springframework.stereotype.Service;

@Service
public class MarketDataManagerService implements MarketDataManager {

    private final MarketDataKeeper keeper = new MarketDataKeeper();

    @Override
    public MarketDataBucket getBucket(String instrumentCode) {
        return keeper.get(instrumentCode);
    }

}
