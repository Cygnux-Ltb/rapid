package io.cygnuxltb.jcts.core.mkd.copy;

public interface MarketDataSnapshot extends MarketDataMessage {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
