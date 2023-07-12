package io.cygnuxltb.jcts.example.strategy;

import io.cygnuxltb.jcts.core.account.SubAccount;
import io.cygnuxltb.jcts.core.indicator.impl.SMA;
import io.cygnuxltb.jcts.core.indicator.impl.SmaPoint;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;
import io.cygnuxltb.jcts.core.order.Order;
import io.cygnuxltb.jcts.engine.strategy.SingleInstrumentStrategy;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;

/**
 * @author yellow013
 */
public final class ExampleSmaStrategy extends SingleInstrumentStrategy<BasicMarketData, ParamKey> implements SMA.SmaEvent {

    public ExampleSmaStrategy(SubAccount subAccount, Params<ParamKey> params, Instrument instrument) {
        super(100, "ExampleSmaStrategy", subAccount, params, instrument);
    }

    @Override
    public String getName() {
        return "SmaStrategyExample";
    }

    @Override
    protected void handleMarketData(BasicMarketData marketData) {

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
