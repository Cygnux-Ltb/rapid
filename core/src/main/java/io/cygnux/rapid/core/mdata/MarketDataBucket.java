package io.cygnux.rapid.core.mdata;

import io.mercury.common.collections.MutableLists;
import io.cygnux.rapid.core.event.inbound.FastMarketData;
import io.cygnux.rapid.core.handler.impl.AbstractMarketDataHandler;
import io.cygnux.rapid.core.instrument.Instrument;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;

import javax.annotation.concurrent.NotThreadSafe;

import static io.mercury.common.collections.Capacity.HEX_10;
import static io.mercury.common.collections.Capacity.HEX_8_000;

@NotThreadSafe
public final class MarketDataBucket extends AbstractMarketDataHandler {

    @Getter
    private final Instrument instrument;

    @Getter
    private final MarketDataSnapshot snapshot = new MarketDataSnapshot();

    @Getter
    private final MutableList<SavedMarketData> saved;

    @Getter
    private final MutableList<MarketDataConsumer> consumers = MutableLists.newFastList(HEX_10.size());

    public MarketDataBucket(Instrument instrument) {
        this(instrument, HEX_8_000.size());
    }

    public MarketDataBucket(Instrument instrument, int size) {
        this.instrument = instrument;
        this.saved = MutableLists.newFastList(size);
    }

    @Override
    protected void handleMarketData(FastMarketData marketData) {
        var dumped = marketData.dump();
        saved.add(dumped);
        snapshot.updateWith(marketData);
        consumers.each(handler -> handler.acceptMarketData(dumped));
    }

    /**
     * @param consumer MarketDataHandler
     * @return MarketDataBucket
     */
    public MarketDataBucket addHandler(MarketDataConsumer consumer) {
        consumers.add(consumer);
        return this;
    }

}
