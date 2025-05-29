package io.rapid.example.strategy;

import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.indicator.impl.SMA;
import io.rapid.core.indicator.impl.SmaPoint;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.SavedMarketData;
import io.rapid.core.order.Order;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.engine.strategy.SingleInstrumentStrategy;
import jakarta.annotation.Nonnull;

/**
 * @author yellow013
 */
public final class ExampleSmaStrategy extends SingleInstrumentStrategy implements SMA.SmaEvent {

    public ExampleSmaStrategy(SubAccount subAccount, Params params, Instrument instrument) {
        super(100, "ExampleSmaStrategy", subAccount, params, instrument);
    }

    @Override
    public Strategy setParams(Params params) {
        return null;
    }

    @Override
    public String getStrategyName() {
        return "SmaStrategyExample";
    }

    @Override
    public void onEpochTime(long epochTime, EpochUnit epochUnit) {

    }


    @Override
    protected boolean verification() {
        return false;
    }

    @Override
    protected void handleMarketData(SavedMarketData marketData) {

    }

    @Override
    protected void handleOrder(Order order) {

    }

    @Override
    protected void handleStrategyEvent(@Nonnull StrategyEvent event) {

    }

    @Override
    public void onCurrentPointAvgPriceChanged(SmaPoint point) {

    }

    @Override
    public void onStartSmaPoint(SmaPoint point) {

    }

    @Override
    public void onEndSmaPoint(SmaPoint point) {

    }

    @Override
    public String getEventName() {
        return null;
    }

    @Override
    public void close() {

    }

}
