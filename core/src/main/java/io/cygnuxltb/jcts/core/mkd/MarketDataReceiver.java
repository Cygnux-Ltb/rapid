package io.cygnuxltb.jcts.core.market.data;

import io.horizon.market.serializable.record.InAvMarketDataSubscribe;

import javax.annotation.Nonnull;


public interface MarketDataReceiver {

    /**
     * 订阅行情
     *
     * @param subscribe MarketDataSubscribe
     * @return boolean
     */
    boolean subscribeMarketData(@Nonnull InAvMarketDataSubscribe subscribe);

}
