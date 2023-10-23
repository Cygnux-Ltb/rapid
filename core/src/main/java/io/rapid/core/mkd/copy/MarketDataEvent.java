package io.cygnuxltb.jcts.core.mkd.copy;

public interface MarketDataEvent extends Visitable {

    String getOrderId();

    String getInstrument();

    String getMarket();

}
