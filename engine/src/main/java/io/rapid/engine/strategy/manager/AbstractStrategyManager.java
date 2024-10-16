package io.rapid.engine.strategy.manager;


import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.MarketDataManager;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyManager;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

/**
 * @author yellow013
 */
public abstract class AbstractStrategyManager implements StrategyManager {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AbstractStrategyManager.class);

    @Resource
    protected MarketDataManager marketDataManager;

    /**
     * 策略列表
     */
    protected final MutableIntObjectMap<Strategy> strategies = MutableMaps.newIntObjectMap();

    /**
     * 交易标的列表
     */
    protected final MutableIntObjectMap<Instrument> instruments = MutableMaps.newIntObjectMap();

    /**
     * 订阅合约的策略列表
     */
    private final MutableIntObjectMap<MutableList<Strategy>> SubscribedInstrumentMap = MutableMaps
            .newIntObjectMap();

    protected AbstractStrategyManager() {
    }

    @Override
    public void addStrategy(Strategy strategy) {
        strategies.put(strategy.getStrategyId(), strategy);
        strategy.getInstruments();
    }

    @Override
    public MutableIntObjectMap<Instrument> getInstruments() {
        return instruments;
    }

    @Override
    public MutableIntObjectMap<Strategy> getStrategies() {
        return strategies;
    }

    @Override
    public Strategy getStrategy(int strategyId) {
        return strategies.get(strategyId);
    }

}
