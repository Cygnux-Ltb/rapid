package io.cygnuxltb.jcts.core.mkd.api;

public interface MarketDataEvent extends Visitable {

    String getOrderId();

    String getInstrument();

    String getMarket();

}
