package io.rapid.engine.strategy.manager;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.event.inbound.AdaptorEvent;
import io.rapid.core.event.inbound.FastMarketData;
import io.rapid.core.event.inbound.OrderEvent;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyException;
import io.rapid.engine.trader.OrderKeeper;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class SyncMultiEventScheduler extends MultiEventScheduler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SyncMultiEventScheduler.class);

    public SyncMultiEventScheduler() {
    }

    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        subscribedMap.get(marketData.getInstrumentCode())
                .each(strategy -> {
                    if (strategy.isEnabled())
                        strategy.onMarketData(marketData);
                });
    }

    @Override
    public void onOrderEvent(@Nonnull OrderEvent event) {
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
    public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
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
