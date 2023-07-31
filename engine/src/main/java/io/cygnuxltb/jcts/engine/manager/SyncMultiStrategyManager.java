package io.cygnuxltb.jcts.engine.manager;

import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.mkd.MarketDataKeeper;
import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;
import io.cygnuxltb.jcts.core.ser.event.OrderEvent;
import io.cygnuxltb.jcts.engine.trader.OrderKeeper;
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
    public void onOrderEvent(@Nonnull OrderEvent event) {
        log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", event.getBrokerOrdSysId(),
                event.getOrdSysId());
        var order = OrderKeeper.handleOrderReport(event);
        log.info("Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
                event.getBrokerOrdSysId(), order.getStrategyId(), order.getInstrument().getInstrumentCode(),
                event.getOrdSysId());
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

}
