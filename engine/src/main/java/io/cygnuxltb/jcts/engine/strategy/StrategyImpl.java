package io.cygnuxltb.jcts.engine.strategy;

import io.cygnuxltb.jcts.core.account.Account;
import io.cygnuxltb.jcts.core.account.SubAccount;
import io.cygnuxltb.jcts.core.handler.AdaptorEventHandler;
import io.cygnuxltb.jcts.core.handler.MarketDataHandler;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.order.Order;
import io.cygnuxltb.jcts.core.serialization.avro.event.AvAdaptorEvent;
import io.cygnuxltb.jcts.core.strategy.Strategy;
import io.cygnuxltb.jcts.core.strategy.StrategyEvent;
import io.cygnuxltb.jcts.core.strategy.StrategyException;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.function.Supplier;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public class StrategyImpl<M extends MarketData> implements Strategy<M> {

    private static final Logger log = getLogger(StrategyImpl.class);

    StrategyImpl(MarketDataHandler<M> marketDataHandler) {
        this.marketDataHandler = marketDataHandler;
    }

    private final MarketDataHandler<M> marketDataHandler;

    @Override
    public void onMarketData(@Nonnull M m) {
        marketDataHandler.onMarketData(m);
    }

    private final AdaptorEventHandler adaptorEventHandler = event -> {
        log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", getName(),
                event.getAdaptorId(), event.getStatus());
        switch (event.getStatus()) {
            case MD_ENABLE -> {
                log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getName(), event.getAdaptorId());
                //adaptor.subscribeMarketData(instrument);
                // log.info("{} :: Call subscribeMarketData, instrument -> {}", getName(), instrument);
            }
            case TRADER_ENABLE -> {
                log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", getName(), event.getAdaptorId());
                // TODO
//			adaptor.queryOrder(null);
//			log.info("{} :: Call queryOrder, adaptorId==[{}], account is default", getStrategyName(),
//					event.getAdaptorId());
                //adaptor.queryPositions(queryPositions.setExchangeCode(instrument.getExchangeCode())
                //       .setInstrumentCode(instrument.getInstrumentCode()).setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryPositions, adaptorId==[{}], account is default", getName(),
                        event.getAdaptorId());
                // adaptor.queryBalance(queryBalance.setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryBalance, adaptorId==[{}], account is default", getName(),
                        event.getAdaptorId());
            }
            default -> log.warn("{} unhandled event received {}", getName(), event);
        }

    };

    @Override
    public void onAdaptorEvent(@Nonnull AvAdaptorEvent event) {
        adaptorEventHandler.onAdaptorEvent(event);
    }

    @Override
    public void onOrder(@Nonnull Order order) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
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
    public ImmutableIntObjectMap<Instrument> getInstruments() {
        return null;
    }

    @Override
    public Strategy<M> initialize(@Nonnull Supplier<Boolean> initializer) {
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
