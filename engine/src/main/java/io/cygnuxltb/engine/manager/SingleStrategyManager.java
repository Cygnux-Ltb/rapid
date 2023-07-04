package io.cygnuxltb.engine.scheduler;

import io.cygnuxltb.engine.trader.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.serialization.avro.outbound.AvroAdaptorReport;
import io.horizon.trader.serialization.avro.outbound.AvroOrderReport;
import io.horizon.trader.strategy.Strategy;
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
public class SingleStrategyScheduler<M extends MarketData> implements StrategyScheduler<M> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SingleStrategyScheduler.class);

    /**
     * Only one strategy
     */
    private Strategy<M> strategy;

    @Override
    public void addStrategy(Strategy<M> strategy) {
        this.strategy = strategy;
        strategy.enable();
    }

    @Override
    public void onMarketData(@Nonnull M marketData) {
        MarketDataKeeper.onMarketDate(marketData);
        strategy.onMarketData(marketData);
    }

    @Override
    public void onOrderReport(@Nonnull AvroOrderReport report) {
        var order = OrderKeeper.handleOrderReport(report);
        // 调用策略实现的订单回调函数
        strategy.onOrder(order);
    }

    @Override
    public void onAdaptorReport(@Nonnull AvroAdaptorReport report) {
        log.error("On Adaptor Report -> {}", report);
        strategy.onAdaptorReport(report);
    }

    @Override
    public void close() throws IOException {
        strategy.close();
        log.info("Strategy [{}] closed", strategy.getName());
    }


}
