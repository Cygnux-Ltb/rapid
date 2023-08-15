package io.cygnuxltb.jcts.engine.strategy;

import io.cygnuxltb.jcts.core.account.Account;
import io.cygnuxltb.jcts.core.account.SubAccount;
import io.cygnuxltb.jcts.core.handler.AdaptorEventHandler;
import io.cygnuxltb.jcts.core.handler.MarketDataHandler;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.FastMarketData;
import io.cygnuxltb.jcts.core.order.Order;
import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;
import io.cygnuxltb.jcts.core.strategy.Strategy;
import io.cygnuxltb.jcts.core.strategy.StrategyEvent;
import io.cygnuxltb.jcts.core.strategy.StrategyException;
import org.eclipse.collections.api.set.ImmutableSet;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.function.Supplier;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public class StrategyImpl implements Strategy {

    private static final Logger log = getLogger(StrategyImpl.class);

    StrategyImpl(MarketDataHandler marketDataHandler) {
        this.marketDataHandler = marketDataHandler;
    }

    private final MarketDataHandler marketDataHandler;

    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        marketDataHandler.onMarketData(marketData);
    }

    private final AdaptorEventHandler adaptorEventHandler = event -> {
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
                log.info("{} :: Call queryPositions, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
                // adaptor.queryBalance(queryBalance.setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryBalance, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
            }
            default -> log.warn("{} unhandled event received {}", getStrategyName(), event);
        }

    };

    @Override
    public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
        adaptorEventHandler.onAdaptorEvent(event);
    }

    @Override
    public void onOrder(@Nonnull Order order) {

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
    public SubAccount getSubAccount() {
        return null;
    }

    @Override
    public Account getAccount() {
        return null;
    }

    @Override
    public ImmutableSet<Instrument> getInstruments() {
        return null;
    }

    @Override
    public Strategy initialize(@Nonnull Supplier<Boolean> initializer) {
        return null;
    }

    @Override
    public void onStrategyEvent(@Nonnull StrategyEvent event) {

    }

    @Override
    public void onThrowable(Throwable throwable) throws StrategyException {

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
