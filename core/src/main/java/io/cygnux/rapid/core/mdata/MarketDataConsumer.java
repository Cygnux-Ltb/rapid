package io.cygnux.rapid.core.mdata;

import io.cygnux.rapid.core.types.mkd.SavedMarketData;

import java.util.function.Consumer;

@FunctionalInterface
public interface MarketDataConsumer extends Consumer<SavedMarketData> {

    void acceptMarketData(SavedMarketData marketData);

    default void accept(SavedMarketData marketData) {
        acceptMarketData(marketData);
    }

}
