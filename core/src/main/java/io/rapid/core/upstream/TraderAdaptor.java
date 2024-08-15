package io.rapid.core.adaptor;

import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.instrument.Instrument;

import javax.annotation.Nonnull;

public interface TraderAdaptor extends Adaptor {

    /**
     * 订阅行情
     *
     * @param instruments Instrument[]
     */
    @Override
    default boolean subscribeMarketData(@Nonnull Instrument... instruments) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> subscribeMarketData");
    }

    /**
     * @param handler MarketDataHandler
     * @return MarketDataFeed
     */
    @Override
    default Adaptor addMarketDataHandler(MarketDataHandler handler) {
        throw new UnsupportedOperationException(getClass().getName()
                + " : unsupported functions -> addMarketDataHandler");
    }

}
