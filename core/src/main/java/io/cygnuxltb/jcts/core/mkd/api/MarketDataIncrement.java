package io.cygnuxltb.jcts.core.market.data.api;

import io.horizon.market.data.api.Visitor;

public interface MarketDataIncrement extends MarketDataMessage {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
