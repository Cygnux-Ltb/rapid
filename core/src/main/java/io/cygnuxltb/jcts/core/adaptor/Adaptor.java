package io.cygnuxltb.jcts.core.adaptor;

import io.cygnuxltb.jcts.core.mkd.MarketDataFeed;

public interface Adaptor extends MarketDataFeed, TraderAdaptor {

    AdaptorType getAdaptorType();

}
