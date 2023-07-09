package io.cygnuxltb.jcts.core.mkd.api;

import io.horizon.market.data.api.MarketDataEvent;
import io.horizon.market.data.api.Visitor;

public interface MarketDataDeleteOrder extends MarketDataEvent {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
