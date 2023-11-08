package io.cygnuxltb.jcts.example.strategy;

import io.rapid.core.account.SubAccount;
import io.rapid.core.indicator.impl.SMA;
import io.rapid.core.indicator.impl.SmaPoint;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.FastMarketData;
import io.rapid.core.order.Order;
import io.cygnuxltb.jcts.engine.strategy.SingleInstrumentStrategy;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;

/**
 * @author yellow013
 */
public final class ExampleSmaStrategy extends SingleInstrumentStrategy<ParamKey> implements SMA.SmaEvent {

    public ExampleSmaStrategy(SubAccount subAccount, Params<ParamKey> params, Instrument instrument) {
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
