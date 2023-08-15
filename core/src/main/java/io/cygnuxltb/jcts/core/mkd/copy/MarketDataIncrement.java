package io.cygnuxltb.jcts.core.mkd.api;

public interface MarketDataIncrement extends MarketDataMessage {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
