package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.strategy.Strategy;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import io.cygnux.rapid.core.strategy.StrategyParam;
import io.mercury.common.collections.CollectionUtil;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import java.util.Set;

/**
 * 策略管理器接口
 */
public interface StrategyManager {

    void addStrategy(Strategy strategy);

    Strategy getStrategy(int strategyId);

    void subscribeInstrument(Strategy strategy, Instrument... instruments);

    MutableIntObjectMap<Strategy> getStrategies();

    default Strategy[] strategies() {
        return CollectionUtil.toArray(getStrategies().toList(), Strategy[]::new);
    }

    MutableIntObjectMap<Instrument> getInstruments();

    void onEvent(StrategyEvent event);

    Set<StrategyParam> getParams(int strategyId);

    SubAccount getSubAccount(int strategyId);

}
