package io.rapid.engine.mkd;

import io.rapid.core.mkd.MarketDataBucket;
import io.rapid.core.mkd.MarketDataKeeper;
import org.springframework.stereotype.Service;

@Service
public class MarketDataManagerService implements MarketDataManager {

    private final MarketDataKeeper keeper = new MarketDataKeeper();



    @Override
    public MarketDataBucket getBucket(String instrumentCode) {
        return keeper.get(instrumentCode);
    }

}
