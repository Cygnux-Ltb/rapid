package io.rapid.engine.strategy.manager;

import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.util.ResourceUtil;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.OrderKeeper;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

@Service("multiStrategyManager")
public class MultiStrategyManager extends AbstractStrategyManager {

    /**
     * Logger
     */
    private static final Logger log = getLogger(MultiStrategyManager.class);

    @Resource(name = "mem")
    protected OrderKeeper orderKeeper;

    /**
     * 策略列表
     */
    protected final MutableIntObjectMap<Strategy> strategyMap = MutableMaps.newIntObjectMap();

    /**
     * 订阅合约的策略列表 <br>
     * instrumentId -> Set::[Strategy]
     */
    protected final MutableMap<String, MutableSet<Strategy>> subscribedMap = newUnifiedMap();

    @Override
    public void addStrategy(Strategy strategy) {
        log.info("Add strategy -> strategyId==[{}], strategyName==[{}], subAccount==[{}]",
                strategy.getStrategyId(), strategy.getStrategyName(), strategy.getSubAccount());
        strategyMap.put(strategy.getStrategyId(), strategy);
        strategy.enable();
    }

    @Override
    public void subscribeInstrument(Strategy strategy, Instrument... instruments) {
        Arrays.stream(instruments).forEach(instrument -> {
            subscribedMap.getIfAbsentPut(instrument.getInstrumentCode(), MutableSets::newUnifiedSet).add(strategy);
            log.info("Add subscribe instrument, strategyId==[{}], instrumentId==[{}]", strategy.getStrategyId(),
                    instrument.getInstrumentId());
        });
    }

    @Override
    public void onEvent(StrategyEvent event) {

    }

    @AbstractFunction
    protected void close0() throws IOException {

    }

    public void close() throws IOException {
        strategyMap.each(ResourceUtil::closeIgnoreException);
        close0();
    }

}
