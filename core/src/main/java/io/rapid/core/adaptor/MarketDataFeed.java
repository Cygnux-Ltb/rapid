package io.rapid.core.adaptor;

import io.mercury.common.fsm.Enableable;
import io.mercury.common.lang.exception.ComponentStartupException;
import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.instrument.Instrument;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.IOException;

public interface MarketDataFeed extends Closeable, Enableable {

    String getFeedId();

    /**
     * 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, ComponentStartupException;

    /**
     * 订阅行情
     *
     * @param instruments Instrument[]
     */
    boolean subscribeMarketData(@Nonnull Instrument... instruments);

    /**
     * @param handler MarketDataHandler
     * @return MarketDataFeed
     */
    MarketDataFeed setMarketDataHandler(MarketDataHandler handler);

}
