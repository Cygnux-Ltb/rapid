package io.rapid.core.mkd.copy;

public interface MarketDataDeleteOrder extends MarketDataEvent {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
