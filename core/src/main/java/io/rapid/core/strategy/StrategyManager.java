package io.rapid.core.strategy;

import io.rapid.core.instrument.Instrument;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import javax.annotation.Nonnull;

/**
 * 策略管理器接口
 */
public interface StrategyManager {

    ImmutableIntObjectMap<Strategy> getStrategies();

    ImmutableIntObjectMap<Instrument> getInstruments();

    void onEvent(@Nonnull StrategyEvent event);

    void onException(Exception exception) throws StrategyException;

}
