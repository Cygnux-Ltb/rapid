package io.cygnux.rapid.engine.strategy.manager;

import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.mdata.SavedMarketData;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class SyncMultiStrategyManager extends MultiStrategyManager {

    private static final Logger log = getLogger(SyncMultiStrategyManager.class);

    public SyncMultiStrategyManager() {
    }


    public void onMarketData(@Nonnull SavedMarketData marketData) {
        subscribedMap.get(marketData.instrumentCode())
                .each(strategy -> {
                    if (strategy.isEnabled())
                        strategy.acceptMarketData(marketData);
                });
    }


    public void onOrderEvent(@Nonnull OrderReport event) {
        log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", event.getBrokerOrdSysId(),
                event.getOrdSysId());
        var order = orderKeeper.onOrderReport(event);
        log.info("Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
                event.getBrokerOrdSysId(), order.getStrategyId(),
                order.getInstrument().getInstrumentCode(), event.getOrdSysId());
        strategyMap.get(order.getStrategyId()).onOrder(order);
    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onEvent(StrategyEvent event) {

    }

}
