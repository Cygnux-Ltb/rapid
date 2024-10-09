package io.rapid.engine.strategy.manager;

import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.ResourceUtil;
import io.rapid.core.event.inbound.AdaptorEvent;
import io.rapid.core.event.inbound.FastMarketData;
import io.rapid.core.event.inbound.OrderEvent;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyException;
import io.rapid.engine.trader.OrderKeeper;
import jakarta.annotation.PostConstruct;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * 单策略调度器, 直接调用策略回调函数, 没有异步操作, 最简单并且速度最快的调度器
 *
 * @author yellow013
 */
@Service("singleStrategyManager")
public class SingleEventScheduler extends AbstractEventScheduler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SingleEventScheduler.class);

    /**
     * Only one strategy
     */
    private Strategy strategy;

    @PostConstruct
    private void init() {
        this.strategy = strategyMap.getOnly();
        this.strategy.enable();
    }

    @Override
    public void addStrategy(Strategy strategy) {
        Throws.unsupportedOperation("");
    }

    @Override
    public ImmutableIntObjectMap<Strategy> getStrategies() {
        return null;
    }

    @Override
    public ImmutableIntObjectMap<Instrument> getInstruments() {
        return null;
    }

    @Override
    public void onEvent(StrategyEvent event) {

    }

    @Override
    public void onException(Exception exception) throws StrategyException {

    }

    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        strategy.onMarketData(marketData);
    }

    @Override
    public void onOrderEvent(@Nonnull OrderEvent event) {
        var order = OrderKeeper.handleOrderReport(event);
        // 调用策略实现的订单回调函数
        strategy.onOrder(order);
    }

    @Override
    public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
        log.error("On Adaptor event -> {}", event);

    }

    @Override
    public void close() throws IOException {
        ResourceUtil.closeIgnoreException(strategy);
        log.info("Strategy [{}] closed", strategy.getStrategyName());
    }


}
