package io.rapid.engine.manager;


import io.rapid.core.handler.InboundHandler;
import io.rapid.core.strategy.Strategy;

/**
 * 
 * @author yellow013
 *
 */
public interface StrategyManager extends InboundHandler {

	StrategyManager addStrategy(Strategy strategy);

}
