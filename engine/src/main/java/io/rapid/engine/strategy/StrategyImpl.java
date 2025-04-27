package io.rapid.engine.strategy;

import io.mercury.common.collections.ImmutableMaps;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.handler.AdaptorReportHandler;
import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.SavedMarketData;
import io.rapid.core.order.Order;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyException;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public class StrategyImpl extends AbstractStrategy {

    private static final Logger log = getLogger(StrategyImpl.class);

    protected final MarketDataHandler marketDataHandler;

    protected StrategyImpl(int strategyId, @Nonnull String strategyName,
                           @Nonnull SubAccount subAccount, @Nonnull Params params,
                           MarketDataHandler marketDataHandler, @Nonnull Instrument... instruments) {
        super(strategyId, strategyName, subAccount, params,
                ImmutableMaps.newImmutableIntMap(List.of(instruments), Instrument::getInstrumentId));
        this.marketDataHandler = marketDataHandler;
    }

    private final AdaptorReportHandler adaptorReportHandler = event -> {
        log.info("{} :: On adaptor status callback, adaptorId==[{}], channelType==[{}], available==[{}]",
                getStrategyName(), event.getAdaptorId(), event.getAdaptorType(), event.isAvailable());
        switch (event.getAdaptorType()) {
            case MARKET_DATA -> {
                log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                //adaptor.subscribeMarketData(instrument);
                // log.info("{} :: Call subscribeMarketData, instrument -> {}", getName(), instrument);
            }
            case TRADING -> {
                log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                // TODO
//			adaptor.queryOrder(null);
//			log.info("{} :: Call queryOrder, adaptorId==[{}], account is default", getStrategyName(),
//					event.getAdaptorId());
                //adaptor.queryPositions(queryPositions.setExchangeCode(instrument.getExchangeCode())
                //       .setInstrumentCode(instrument.getInstrumentCode()).setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryPositions, adaptorId==[{}], account is default",
                        getStrategyName(), event.getAdaptorId());
                // adaptor.queryBalance(queryBalance.setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryBalance, adaptorId==[{}], account is default",
                        getStrategyName(), event.getAdaptorId());
            }
            default -> log.warn("{} unhandled event received {}", getStrategyName(), event);
        }

    };

    public void onAdaptorEvent(@Nonnull AdaptorReport event) {
        adaptorReportHandler.onAdaptorReport(event);
    }

    @Override
    public void onOrder(@Nonnull Order order) {

    }

    @Override
    protected void handleOrder(Order order) {

    }

    @Override
    public Strategy setParams(Params params) {
        return null;
    }


    @Override
    public int getStrategyId() {
        return 0;
    }

    @Override
    public String getStrategyName() {
        return null;
    }

    @Override
    public void onEpochTime(long epochTime, EpochUnit epochUnit) {

    }

    @Override
    protected boolean verification() {
        return false;
    }

    @Override
    public Strategy initialize(@Nonnull Supplier<Boolean> initializer) {
        return null;
    }

    @Override
    protected void handleMarketData(SavedMarketData marketData) {

    }

    @Override
    public void onStrategyEvent(@Nonnull StrategyEvent event) {

    }

    @Override
    protected void handleStrategyEvent(@jakarta.annotation.Nonnull StrategyEvent event) {

    }

    @Override
    public void onException(Exception exception) throws StrategyException {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean disable() {
        return false;
    }

    @Override
    public boolean enable() {
        return false;
    }

    @Override
    public void close() throws IOException {

    }

}
