package io.rapid.core.mdata.copy;

public interface MarketDataEvent extends Visitable {

    String getOrderId();

    String getInstrument();

    String getMarket();

}
