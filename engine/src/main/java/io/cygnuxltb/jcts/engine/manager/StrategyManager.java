package io.cygnuxltb.jcts.engine.manager;


import io.cygnuxltb.jcts.core.handler.InboundHandler;
import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.strategy.Strategy;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface StrategyManager<M extends MarketData> extends InboundHandler<M> {

	StrategyManager<M> addStrategy(Strategy<M> strategy);

}
