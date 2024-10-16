package io.rapid.engine.strategy.manager;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyException;
import io.rapid.engine.order.OrderKeeper;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class SyncMultiStrategyManager extends MultiStrategyManager {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SyncMultiStrategyManager.class);

    public SyncMultiStrategyManager() {
    }

    @Override
    public void onMarketData(@Nonnull RawMarketData marketData) {
        subscribedMap.get(marketData.getInstrumentCode())
                .each(strategy -> {
                    if (strategy.isEnabled())
                        strategy.onMarketData(marketData);
                });
    }

    @Override
    public void onOrderEvent(@Nonnull OrderReport event) {
        log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", event.getBrokerOrdSysId(),
                event.getOrdSysId());
        var order = OrderKeeper.handleOrderReport(event);
        log.info("Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
                event.getBrokerOrdSysId(), order.getStrategyId(),
                order.getInstrument().getInstrumentCode(), event.getOrdSysId());
        strategyMap.get(order.getStrategyId()).onOrder(order);
    }

    // TODO add pools
    @Override
    public void onAdaptorEvent(@Nonnull AdaptorReport event) {
        log.info("Recv AdaptorEvent -> {}", event);
    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub
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

}
