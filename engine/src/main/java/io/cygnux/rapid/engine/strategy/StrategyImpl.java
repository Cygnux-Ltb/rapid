package io.cygnux.rapid.engine.strategy;

import io.cygnux.rapid.core.types.account.SubAccount;
import io.cygnux.rapid.core.types.event.received.AdapterReport;
import io.cygnux.rapid.core.types.event.received.OrderReport;
import io.cygnux.rapid.core.handler.AdapterReportHandler;
import io.cygnux.rapid.core.handler.MarketDataHandler;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.mkd.SavedMarketData;
import io.cygnux.rapid.core.strategy.Strategy;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import io.cygnux.rapid.core.types.strategy.StrategyException;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public class StrategyImpl extends AbstractStrategy {

    private static final Logger log = getLogger(StrategyImpl.class);

    protected final MarketDataHandler marketDataHandler;

    protected StrategyImpl(int strategyId, @Nonnull String strategyName,
                           @Nonnull SubAccount subAccount, @Nonnull Params params,
                           MarketDataHandler marketDataHandler, @Nonnull Instrument... instruments) {
        super(strategyId, strategyName, subAccount, instruments);
        this.marketDataHandler = marketDataHandler;
    }

    private final AdapterReportHandler adapterReportHandler = event -> {
        log.info("{} :: On adaptor status callback, adaptorId==[{}], channelType==[{}], available==[{}]",
                getStrategyName(), event.getAdapterId(), event.getAdapterType(), event.isAvailable());
        switch (event.getAdapterType()) {
            case MARKET_DATA -> {
                log.info("{} :: Handle adapter MdEnable, adapterId==[{}]", getStrategyName(), event.getAdapterId());
                //adaptor.subscribeMarketData(instrument);
                // log.info("{} :: Call subscribeMarketData, instrument -> {}", getName(), instrument);
            }
            case TRADING -> {
                log.info("{} :: Handle adapter TdEnable, adapterId==[{}]", getStrategyName(), event.getAdapterId());
                // TODO
//			adaptor.queryOrder(null);
//			log.info("{} :: Call queryOrder, adaptorId==[{}], account is default", getStrategyName(),
//					event.getAdaptorId());
                //adaptor.queryPositions(queryPositions.setExchangeCode(instrument.getExchangeCode())
                //       .setInstrumentCode(instrument.getInstrumentCode()).setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryPositions, adapterId==[{}], account is default",
                        getStrategyName(), event.getAdapterId());
                // adaptor.queryBalance(queryBalance.setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryBalance, adapterId==[{}], account is default",
                        getStrategyName(), event.getAdapterId());
            }
            default -> log.warn("{} unhandled event received {}", getStrategyName(), event);
        }

    };

    public void onAdapterEvent(@Nonnull AdapterReport event) {
        adapterReportHandler.onAdapterReport(event);
    }


    @Override
    protected void handleOrder(OrderReport report) {

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

}
