package io.cygnux.rapid.strategy;

import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.indicator.impl.SMA;
import io.cygnux.rapid.core.indicator.impl.SmaPoint;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.mdata.SavedMarketData;
import io.cygnux.rapid.core.order.Order;
import io.cygnux.rapid.core.strategy.Strategy;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import io.cygnux.rapid.engine.strategy.SingleInstrumentStrategy;
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
