package io.rapid.core.strategy;

import io.rapid.core.instrument.Instrument;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

/**
 * 策略管理器接口
 */
public interface StrategyManager {

    void addStrategy(Strategy strategy);

    Strategy getStrategy(int strategyId);

    MutableIntObjectMap<Strategy> getStrategies();

    MutableIntObjectMap<Instrument> getInstruments();

    long nextOrdSysId();

    void onEvent(StrategyEvent event);

    void onException(Exception exception) throws StrategyException;

}
