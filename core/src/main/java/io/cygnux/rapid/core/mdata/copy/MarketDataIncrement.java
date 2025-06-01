package io.cygnux.rapid.core.mdata.copy;

public interface MarketDataIncrement extends MarketDataMessage {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
