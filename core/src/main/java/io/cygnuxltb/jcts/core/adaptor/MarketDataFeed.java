package io.cygnuxltb.jcts.core.mkd;

import io.cygnuxltb.jcts.core.handler.MarketDataHandler;
import io.mercury.common.fsm.Enableable;
import io.mercury.common.lang.exception.ComponentStartupException;

import java.io.Closeable;
import java.io.IOException;

public interface MarketDataFeed extends Closeable, Enableable, MarketAgent {

    /**
     * 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, ComponentStartupException;


    MarketDataFeed registerMarketDataHandler(MarketDataHandler handler);

}
