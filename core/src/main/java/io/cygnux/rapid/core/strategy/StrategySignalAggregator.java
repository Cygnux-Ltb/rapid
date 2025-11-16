package io.cygnux.rapid.core.strategy;

import io.cygnux.rapid.core.shared.SharedEventHandler;
import io.cygnux.rapid.core.shared.event.StrategySignal;

/**
 * 策略信号处理器
 */
@FunctionalInterface
public interface StrategySignalAggregator extends SharedEventHandler {

    /**
     * [9].处理策略信号
     *
     * @param signals StrategySignal[]
     */
    @Override
    default void fireStrategySignals(StrategySignal[] signals) {
        onStrategySignals(signals);
    }


    void onStrategySignals(StrategySignal[] signals);

}
