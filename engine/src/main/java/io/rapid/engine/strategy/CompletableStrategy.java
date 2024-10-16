package io.rapid.engine.strategy;

import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.SavedMarketData;
import io.rapid.core.order.OrdSysIdAllocator;
import io.rapid.core.order.Order;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyException;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableStrategy extends CompletableFuture<Strategy> implements Strategy {

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
        return "";
    }

    @Override
    public SubAccount getSubAccount() {
        return null;
    }

    @Override
    public OrdSysIdAllocator getAllocator() {
        return null;
    }

    @Override
    public void addInstrument(Instrument instrument) {

    }

    @Override
    public ImmutableIntObjectMap<Instrument> getInstruments() {
        return null;
    }

    @Override
    public Strategy initialize(Supplier<Boolean> initializer) {
        return null;
    }

    @Override
    public void onEpochTime(long epochTime, EpochUnit epochUnit) {

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
    public void onMarketData(RawMarketData marketData) {

    }

    @Override
    public void onOrder(@Nonnull Order order) {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void acceptMarketData(SavedMarketData marketData) {

    }
}
