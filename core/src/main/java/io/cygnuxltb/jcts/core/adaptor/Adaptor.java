package io.cygnuxltb.jcts.core.trader.adaptor;

import io.cygnuxltb.jcts.core.mkd.MarketDataFeed;

public interface Adaptor extends MarketDataFeed, TraderAdaptor {

    AdaptorType getAdaptorType();

}
