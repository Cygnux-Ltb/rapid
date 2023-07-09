package io.cygnuxltb.jcts.core.market.data.api;

public interface MarketDataEvent extends Visitable {

    String getOrderId();

    String getInstrument();

    String getMarket();

}
