package io.cygnuxltb.jcts.engine.manager;

import io.cygnuxltb.jcts.core.mkd.MarketDataKeeper;
import io.cygnuxltb.jcts.core.mkd.FastMarketData;
import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;
import io.cygnuxltb.jcts.core.ser.event.OrderEvent;
import io.cygnuxltb.jcts.core.strategy.Strategy;
import io.cygnuxltb.jcts.engine.trader.OrderKeeper;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * 单策略调度器, 直接调用策略回调函数, 没有异步操作, 最简单并且速度最快的调度器
 *
 * @author yellow013
 */
public class SingleStrategyManager implements StrategyManager {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SingleStrategyManager.class);

    /**
     * Only one strategy
     */
    private Strategy strategy;

    @Override
    public StrategyManager addStrategy(Strategy strategy) {
        this.strategy = strategy;
        strategy.enable();
        return this;
    }

    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        MarketDataKeeper.onMarketDate(marketData);
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
        strategy.onAdaptorEvent(event);
    }

    @Override
    public void close() throws IOException {
        strategy.close();
        log.info("Strategy [{}] closed", strategy.getStrategyName());
    }


}
