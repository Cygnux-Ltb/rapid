package io.cygnuxltb.jcts.core.mkd.api;

public interface MarketDataDeleteOrder extends MarketDataEvent {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
