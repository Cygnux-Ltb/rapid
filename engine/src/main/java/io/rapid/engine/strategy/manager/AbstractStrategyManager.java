package io.rapid.engine.strategy.manager;


import io.mercury.common.collections.MutableMaps;
import io.mercury.common.epoch.EpochTimeUtil;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.adaptor.AdaptorManager;
import io.rapid.core.event.inbound.AdaptorEvent;
import io.rapid.core.event.outbound.TradeSignal;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.EventScheduler;
import io.rapid.engine.mkd.MarketDataManager;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

/**
 * @author yellow013
 */
public abstract class AbstractEventScheduler implements EventScheduler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AbstractEventScheduler.class);

    @Resource
    protected MarketDataManager marketDataManager;

    @Resource
    protected AdaptorManager adaptorManager;

    /**
     * 策略列表
     */
    protected final MutableIntObjectMap<Strategy> strategyMap = MutableMaps.newIntObjectMap();

    protected AbstractEventScheduler() {

    }

    @Override
    public void addStrategy(Strategy strategy) {
        strategyMap.put(strategy.getStrategyId(), strategy);
    }

    @Override
    public ImmutableIntObjectMap<Instrument> getInstruments() {
        return null;
    }

    @Override
    public void onSignal(@Nonnull TradeSignal signal) {

    }

    @Override
    public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
        log.info("onAdaptorEvent, adaptorId==[{}], status==[{}]", event.getAdaptorId(), event.getStatus());
        var adaptor = adaptorManager.getAdaptor(event.getAccountId());
        if (adaptor == null){
            log.error("");
            return;
        }
        switch (event.getStatus()) {
            case MD_ENABLE -> {
                log.info("Adaptor Status -> [MD_ENABLE], adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                //agent.subscribeMarketData(instrument);
                log.info("{} :: Call subscribeMarketData, instrument -> {}", getStrategyName(), instrument);
            }
            case TRADER_ENABLE -> {

                log.info("Adaptor Status -> [TRADER_ENABLE], adaptorId==[{}]", getStrategyName(), adaptor.getAdaptorId());
                // TODO
//			adaptor.queryOrder(null);
//			log.info("{} :: Call queryOrder, adaptorId==[{}], account is default", getStrategyName(),
//					event.getAdaptorId());

                adaptor.queryPosition(queryPositions.setExchangeCode(instrument.getExchangeCode())
                        .setInstrumentCode(instrument.getInstrumentCode()).setGenerateTime(EpochTimeUtil.getEpochMillis()));
                log.info("{} :: Call queryPositions, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
                adaptor.queryBalance(queryBalance.setGenerateTime(EpochTimeUtil.getEpochMillis()));
                log.info("{} :: Call queryBalance, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
            }
            default -> log.warn("{} unhandled event received {}", getStrategyName(), event);
        }
    }

}
