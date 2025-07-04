package io.cygnux.rapid.engine.strategy;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.core.strategy.Strategy;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;

/**
 * 可以使用在 #StrategyScheduler 中进行行情分发的管理
 *
 * @author yellow013
 */
@NotThreadSafe
@Deprecated
public final class StrategyKeeper implements Serializable {

    @Serial
    private static final long serialVersionUID = -4657849933310280319L;

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyKeeper.class);

    /**
     * 策略列表
     */
    private static final MutableIntObjectMap<Strategy> strategyMap = MutableMaps.newIntObjectMap();

    private StrategyKeeper() {
    }

    /**
     * 添加策略并置为激活, 同时添加策略订阅的Instrument
     *
     * @param strategy Strategy<?>
     */
    public static void putStrategy(Strategy strategy) {
        if (strategyMap.containsKey(strategy.getStrategyId())) {
            log.error("Strategy id is existed, Have stored or have duplicate strategy id");
        } else {
            strategyMap.put(strategy.getStrategyId(), strategy);
            log.info("Put strategy, strategyId==[{}]", strategy.getStrategyId());
//            strategy.getInstruments().each(instrument -> {
//                SubscribedInstrumentMap.getIfAbsentPut(instrument.getInstrumentId(), MutableLists::newFastList).add(strategy);
//                log.info("Add subscribe instrument, strategyId==[{}], instrumentId==[{}]", strategy.getStrategyId(),
//                        instrument.getInstrumentId());
//            });
            strategy.enable();
            log.info("Strategy is enable, strategyId==[{}]", strategy.getStrategyId());
        }
    }

    public static Strategy getStrategy(int strategyId) {
        return strategyMap.get(strategyId);
    }

    @Override
    public String toString() {
        return "";
    }

}
