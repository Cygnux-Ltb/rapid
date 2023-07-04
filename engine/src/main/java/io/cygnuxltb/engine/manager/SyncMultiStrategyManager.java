package io.cygnuxltb.engine.scheduler;

import io.cygnuxltb.engine.trader.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.serialization.avro.outbound.AvroAdaptorReport;
import io.horizon.trader.serialization.avro.outbound.AvroOrderReport;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class SyncMultiStrategyScheduler<M extends MarketData> extends MultiStrategyScheduler<M> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SyncMultiStrategyScheduler.class);

    public SyncMultiStrategyScheduler() {
    }

    @Override
    public void onMarketData(@Nonnull M marketData) {
        MarketDataKeeper.onMarketDate(marketData);
        subscribedMap.get(marketData.getInstrumentId()).each(strategy -> {
            if (strategy.isEnabled()) {
                strategy.onMarketData(marketData);
            }
        });
    }

    @Override
    public void onOrderReport(@Nonnull AvroOrderReport report) {
        log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", report.getBrokerOrdSysId(),
                report.getOrdSysId());
        var order = OrderKeeper.handleOrderReport(report);
        log.info("Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
                report.getBrokerOrdSysId(), order.getStrategyId(), order.getInstrument().getInstrumentCode(),
                report.getOrdSysId());
        strategyMap.get(order.getStrategyId()).onOrder(order);
    }

    // TODO add pools
    @Override
    public void onAdaptorReport(@Nonnull AvroAdaptorReport report) {
        log.info("Recv AdaptorReport -> {}", report);
    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub

    }

}
