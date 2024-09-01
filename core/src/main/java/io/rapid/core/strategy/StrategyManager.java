package io.rapid.core.strategy;

import io.rapid.core.account.SubAccount;
import io.rapid.core.instrument.Instrument;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 *
 */
public interface StrategyManager {

    int getStrategyId();

    String getStrategyName();

    SubAccount getSubAccount();

    ImmutableIntObjectMap<Instrument> getInstruments();

    strategyManager initialize(@Nonnull Supplier<Boolean> initializer);

    void onEvent(@Nonnull StrategyEvent event);

    void onException(Exception exception) throws StrategyException;

    @Override
    default int compareTo(strategyManager o) {
        return Integer.compare(this.getStrategyId(), o.getStrategyId());
    }

}
