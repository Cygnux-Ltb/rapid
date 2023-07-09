package io.cygnuxltb.jcts.core.market.data.api;

import io.horizon.market.data.api.MarketDataEvent;
import io.horizon.market.data.api.Side;
import io.horizon.market.data.api.Visitor;

public interface MarketDataNewOrder extends MarketDataEvent {

    double getPrice();

    double getQty();

    Side getSide();

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
