package io.cygnuxltb.jcts.core.mkd;


import io.cygnuxltb.jcts.core.ser.req.MarketDataSubscribe;

import javax.annotation.Nonnull;


public interface MarketDataReceiver {

    /**
     * 订阅行情
     *
     * @param request AvMarketDataSubscribeRequest
     * @return boolean
     */
    boolean subscribeMarketData(@Nonnull MarketDataSubscribe request);

}
