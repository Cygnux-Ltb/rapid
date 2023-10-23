package io.cygnuxltb.jcts.core.adaptor;

public interface Adaptor extends MarketDataFeed, TraderAdaptor {

    AdaptorAvailableTime getAvailableTime();

    @Override
    default String getFeedId() {
        return getAdaptorId();
    }

}
