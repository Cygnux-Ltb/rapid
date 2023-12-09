package io.rapid.core.adaptor;

public interface Adaptor extends MarketDataFeed, TraderAdaptor {

    AdaptorAvailableTime getAvailableTime();

    @Override
    default String getFeedId() {
        return getAdaptorId();
    }

}
