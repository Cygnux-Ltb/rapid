package io.rapid.engine.strategy;

import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.handler.AdaptorReportHandler;
import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.strategy.StrategySignalHandler;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.Order;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyException;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.function.Supplier;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public class StrategyImpl extends BaseStrategy {

    private static final Logger log = getLogger(StrategyImpl.class);

    private final MarketDataHandler marketDataHandler;

    protected StrategyImpl(int strategyId, @Nonnull String strategyName,
                           @Nonnull SubAccount subAccount, @Nonnull Params params,
                           MarketDataHandler marketDataHandler, @Nonnull Instrument... instruments) {
        super(strategyId, strategyName, subAccount, params, instruments);
        this.marketDataHandler = marketDataHandler;
    }

    @Override
    public void onMarketData(@Nonnull RawMarketData marketData) {
        marketDataHandler.onMarketData(marketData);
    }

    @Override
    protected void handleMarketData(RawMarketData marketData) {

    }

    private final AdaptorReportHandler adaptorReportHandler = event -> {
        log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", getStrategyName(),
                event.getAdaptorId(), event.getStatus());
        switch (event.getStatus()) {
            case MD_ENABLE -> {
                log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                //adaptor.subscribeMarketData(instrument);
                // log.info("{} :: Call subscribeMarketData, instrument -> {}", getName(), instrument);
            }
            case TRADER_ENABLE -> {
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
    public Strategy setSignalHandler(StrategySignalHandler handler) {
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
    public Strategy initialize(@Nonnull Supplier<Boolean> initializer) {
        return null;
    }

    @Override
    public void onStrategyEvent(@Nonnull StrategyEvent event) {

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
