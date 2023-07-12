package io.cygnuxltb.jcts.core.mkd;


import io.cygnuxltb.jcts.core.serialization.avro.request.AvMarketDataSubscribeRequest;

import javax.annotation.Nonnull;


public interface MarketDataReceiver {

    /**
     * 订阅行情
     *
     * @param request AvMarketDataSubscribeRequest
     * @return boolean
     */
    boolean subscribeMarketData(@Nonnull AvMarketDataSubscribeRequest request);

}
