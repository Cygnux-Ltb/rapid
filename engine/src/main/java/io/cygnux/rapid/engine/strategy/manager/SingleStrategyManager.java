package io.cygnux.rapid.engine.strategy.manager;

import io.mercury.common.lang.Throws;
import io.mercury.common.util.ResourceUtil;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.order.OrderKeeper;
import io.cygnux.rapid.core.strategy.Strategy;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import io.cygnux.rapid.core.strategy.StrategyManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.io.IOException;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * 单策略调度器, 直接调用策略回调函数, 没有异步操作, 最简单并且速度最快的调度器
 *
 * @author yellow013
 */
@Service("singleStrategyManager")
public class SingleStrategyManager implements StrategyManager {

    private static final Logger log = getLogger(SingleStrategyManager.class);

    @Resource(name = "mem")
    private OrderKeeper orderKeeper;

    /**
     * Only one strategy
     */
    private Strategy strategy;

    @PostConstruct
    private void init() {
        this.strategy.enable();
    }

    @Override
    public void addStrategy(Strategy strategy) {
        Throws.unsupportedOperation("");
    }

    @Override
    public Strategy getStrategy(int strategyId) {
        return null;
    }

    @Override
    public void subscribeInstrument(Strategy strategy, Instrument... instruments) {

    }

    @Override
    public MutableIntObjectMap<Strategy> getStrategies() {
        return null;
    }

    @Override
    public MutableIntObjectMap<Instrument> getInstruments() {
        return null;
    }

    @Override
    public void onEvent(StrategyEvent event) {

    }


    public void onOrderEvent(@Nonnull OrderReport event) {
        var order = orderKeeper.onOrderReport(event);
        // 调用策略实现的订单回调函数
        strategy.onOrder(order);
    }

//    @Override
//    public void onAdaptorEvent(@Nonnull AdaptorReport event) {
//        log.error("On Adaptor event -> {}", event);
//
//    }

    public void close() throws IOException {
        ResourceUtil.closeIgnoreException(strategy);
        log.info("Strategy [{}] closed", strategy.getStrategyName());
    }


}
