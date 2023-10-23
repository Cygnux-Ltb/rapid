package io.cygnuxltb.jcts.engine.manager;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.strategy.Strategy;
import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;

import java.io.IOException;

import static io.mercury.common.collections.MutableMaps.newIntObjectHashMap;
import static io.mercury.common.collections.MutableMaps.newUnifiedMap;
import static org.apache.commons.io.IOUtils.closeQuietly;

public abstract class MultiStrategyManager implements StrategyManager {

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(MultiStrategyManager.class);

    /**
     * 策略列表
     */
    protected final MutableIntObjectMap<Strategy> strategyMap = newIntObjectHashMap();

    /**
     * 订阅合约的策略列表 <br>
     * instrumentId -> Set::[Strategy]
     */
    protected final MutableMap<String, MutableSet<Strategy>> subscribedMap = newUnifiedMap();

    @Override
    public StrategyManager addStrategy(Strategy strategy) {
        log.info("Add strategy -> strategyId==[{}], strategyName==[{}], subAccount==[{}]",
                strategy.getStrategyId(), strategy.getStrategyName(), strategy.getSubAccount());
        strategyMap.put(strategy.getStrategyId(), strategy);
        strategy.getInstruments().each(instrument -> subscribeInstrument(instrument, strategy));
        strategy.enable();
        return this;
    }

    private void subscribeInstrument(Instrument instrument, Strategy strategy) {
        subscribedMap.getIfAbsentPut(instrument.getInstrumentCode(), MutableSets::newUnifiedSet).add(strategy);
        log.info("Add subscribe instrument, strategyId==[{}], instrumentId==[{}]", strategy.getStrategyId(),
                instrument.getInstrumentId());
    }

    @AbstractFunction
    protected abstract void close0() throws IOException;

    @Override
    public void close() throws IOException {
        strategyMap.each(strategy ->
                closeQuietly(strategy, e ->
                        log.error("strategy -> {} close exception!",
                                strategy.getStrategyName(), e)));
        close0();
    }

}
