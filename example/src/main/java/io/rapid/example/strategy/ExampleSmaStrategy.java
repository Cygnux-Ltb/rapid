package io.rapid.example.strategy;

import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.indicator.impl.SMA;
import io.rapid.core.indicator.impl.SmaPoint;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.FastMarketData;
import io.rapid.core.order.Order;
import io.rapid.engine.strategy.SingleInstrumentStrategy;

/**
 * @author yellow013
 */
public final class ExampleSmaStrategy extends SingleInstrumentStrategy implements SMA.SmaEvent {

    public ExampleSmaStrategy(SubAccount subAccount, Params params, Instrument instrument) {
        super(100, "ExampleSmaStrategy", subAccount, params, instrument);
    }

    @Override
    public String getStrategyName() {
        return "SmaStrategyExample";
    }

    @Override
    protected void handleMarketData(FastMarketData marketData) {

    }

    @Override
    protected void handleOrder(Order order) {

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
