package io.cygnuxltb.engine.strategy;

import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.Account;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.order.Order;
import io.horizon.trader.strategy.Strategy;
import io.horizon.trader.strategy.StrategyEvent;
import io.horizon.trader.strategy.StrategyException;
import io.horizon.trader.transport.avro.outbound.TdxAdaptorReport;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.function.Supplier;

public class StrategyImpl<M extends MarketData> implements Strategy<M> {


    StrategyImpl() {

    }


    @Override
    public void onMarketData(@Nonnull M m) {

    }

    @Override
    public void onAdaptorReport(@Nonnull TdxAdaptorReport report) {

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
    public Strategy<M> addAdaptor(@Nonnull Adaptor adaptor) {
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
