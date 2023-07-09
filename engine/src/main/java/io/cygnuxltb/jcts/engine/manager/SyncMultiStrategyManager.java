package io.cygnuxltb.engine.manager;

import io.cygnuxltb.engine.trader.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.serialization.avro.receive.AvroAdaptorEvent;
import io.horizon.trader.serialization.avro.receive.AvroOrderEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class SyncMultiStrategyManager<M extends MarketData> extends MultiStrategyManager<M> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SyncMultiStrategyManager.class);

    public SyncMultiStrategyManager() {
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
    public void onOrderEvent(@Nonnull AvroOrderEvent report) {
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
    public void onAdaptorEvent(@Nonnull AvroAdaptorEvent event) {
        log.info("Recv AdaptorEvent -> {}", event);
    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub

    }

}
