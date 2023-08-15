package io.cygnuxltb.jcts.engine.manager;


import io.cygnuxltb.jcts.core.handler.InboundHandler;
import io.cygnuxltb.jcts.core.strategy.Strategy;

/**
 * 
 * @author yellow013
 *
 */
public interface StrategyManager extends InboundHandler {

	StrategyManager addStrategy(Strategy strategy);

}
