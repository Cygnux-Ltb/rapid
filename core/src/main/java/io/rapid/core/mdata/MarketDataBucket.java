package io.rapid.core.mdata;

import io.mercury.common.collections.MutableLists;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.handler.impl.AbstractMarketDataHandler;
import io.rapid.core.instrument.Instrument;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

import static io.mercury.common.collections.Capacity.L04_16;
import static io.mercury.common.collections.Capacity.L15_32768;

@NotThreadSafe
public final class MarketDataBucket extends AbstractMarketDataHandler implements Serializable {

    @Getter
    private final Instrument instrument;

    @Getter
    private final MarketDataSnapshot snapshot = new MarketDataSnapshot();

    @Getter
    private final MutableList<SavedMarketData> saved;

    @Getter
    private final MutableList<MarketDataConsumer> consumers = MutableLists.newFastList(L04_16.size());

    public MarketDataBucket(Instrument instrument) {
        this(instrument, L15_32768.size());
    }

    public MarketDataBucket(Instrument instrument, int size) {
        this.instrument = instrument;
        this.saved = MutableLists.newFastList(size);
    }

    @Override
    protected void handleMarketData(RawMarketData marketData) {
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
