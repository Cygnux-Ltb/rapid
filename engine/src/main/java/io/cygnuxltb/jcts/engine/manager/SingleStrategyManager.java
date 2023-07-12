package io.cygnuxltb.jcts.engine.manager;

import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.mkd.MarketDataKeeper;
import io.cygnuxltb.jcts.core.serialization.avro.event.AvAdaptorEvent;
import io.cygnuxltb.jcts.core.serialization.avro.event.AvOrderEvent;
import io.cygnuxltb.jcts.core.strategy.Strategy;
import io.cygnuxltb.jcts.engine.trader.OrderKeeper;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * 单策略调度器, 直接调用策略回调函数, 没有异步操作, 最简单并且速度最快的调度器
 *
 * @param <M>
 * @author yellow013
 */
public class SingleStrategyManager<M extends MarketData> implements StrategyManager<M> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SingleStrategyManager.class);

    /**
     * Only one strategy
     */
    private Strategy<M> strategy;

    @Override
    public StrategyManager<M> addStrategy(Strategy<M> strategy) {
        this.strategy = strategy;
        strategy.enable();
        return this;
    }

    @Override
    public void onMarketData(@Nonnull M marketData) {
        MarketDataKeeper.onMarketDate(marketData);
        strategy.onMarketData(marketData);
    }

    @Override
    public void onOrderEvent(@Nonnull AvOrderEvent event) {
        var order = OrderKeeper.handleOrderReport(event);
        // 调用策略实现的订单回调函数
        strategy.onOrder(order);
    }

    @Override
    public void onAdaptorEvent(@Nonnull AvAdaptorEvent event) {
        log.error("On Adaptor event -> {}", event);
        strategy.onAdaptorEvent(event);
    }

    @Override
    public void close() throws IOException {
        strategy.close();
        log.info("Strategy [{}] closed", strategy.getName());
    }


}
