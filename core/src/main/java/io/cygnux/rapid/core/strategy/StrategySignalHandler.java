package io.cygnux.rapid.core.strategy;

import io.cygnux.rapid.core.stream.StreamEventHandler;
import io.cygnux.rapid.core.stream.event.StrategySignal;

/**
 * 策略信号处理器
 */
@FunctionalInterface
public interface StrategySignalHandler extends StreamEventHandler {

    void onSignal(final StrategySignal signal);

}
