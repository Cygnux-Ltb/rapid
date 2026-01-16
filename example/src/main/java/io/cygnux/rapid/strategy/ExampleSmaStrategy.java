package io.cygnux.rapid.strategy;

import io.cygnux.rapid.core.types.account.SubAccount;
import io.cygnux.rapid.core.types.event.received.OrderReport;
import io.cygnux.rapid.core.indicator.impl.SMA;
import io.cygnux.rapid.core.indicator.impl.SmaPoint;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.mkd.SavedMarketData;
import io.cygnux.rapid.core.strategy.Strategy;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import io.cygnux.rapid.engine.strategy.AbstractStrategy;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import jakarta.annotation.Nonnull;

/**
 * @author yellow013
 */
public final class ExampleSmaStrategy extends AbstractStrategy implements SMA.SmaEvent {

    public ExampleSmaStrategy(SubAccount subAccount, Instrument instrument) {
        super(100, "ExampleSmaStrategy", subAccount,  instrument);
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
    protected void handleMarketData(SavedMarketData marketData) {

    }

    @Override
    protected void handleOrder(OrderReport report) {

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

}
