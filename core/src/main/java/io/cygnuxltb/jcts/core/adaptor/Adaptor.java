package io.cygnuxltb.jcts.core.adaptor;

public interface Adaptor extends MarketDataFeed, TraderAdaptor, OrderAgent {

    AdaptorAvailableTime getAvailableTime();

}
