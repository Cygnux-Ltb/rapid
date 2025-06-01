package io.cygnux.rapid.core.strategy;

import io.cygnux.rapid.core.instrument.Instrument;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

/**
 * 策略管理器接口
 */
public interface StrategyManager {

    void addStrategy(Strategy strategy);

    Strategy getStrategy(int strategyId);

    void subscribeInstrument(Strategy strategy, Instrument... instruments);

    MutableIntObjectMap<Strategy> getStrategies();

    MutableIntObjectMap<Instrument> getInstruments();

    void onEvent(StrategyEvent event);

}
