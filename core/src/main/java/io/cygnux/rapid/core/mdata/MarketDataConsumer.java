package io.cygnux.rapid.core.mdata;

import java.util.function.Consumer;

@FunctionalInterface
public interface MarketDataConsumer extends Consumer<SavedMarketData> {

    void acceptMarketData(SavedMarketData marketData);

    default void accept(SavedMarketData marketData) {
        acceptMarketData(marketData);
    }

}
