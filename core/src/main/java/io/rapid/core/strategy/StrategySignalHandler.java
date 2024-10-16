package io.rapid.core.strategy;

/**
 * 策略信号处理器
 */
@FunctionalInterface
public interface StrategySignalHandler {

    void onSignal(final StrategySignal signal);

}
