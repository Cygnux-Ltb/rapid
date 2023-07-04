package io.cygnuxltb.engine.manager;

import io.horizon.market.data.MarketData;
import io.horizon.trader.handler.InboundHandler;
import io.horizon.trader.strategy.Strategy;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface StrategyManager<M extends MarketData> extends InboundHandler<M> {

	StrategyManager<M> addStrategy(Strategy<M> strategy);

}
