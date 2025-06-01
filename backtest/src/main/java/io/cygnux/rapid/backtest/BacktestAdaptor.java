package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.adaptor.AbstractAdaptor;
import io.cygnux.rapid.core.event.outbound.CancelOrder;
import io.cygnux.rapid.core.event.outbound.NewOrder;
import io.cygnux.rapid.core.event.outbound.QueryBalance;
import io.cygnux.rapid.core.event.outbound.QueryOrder;
import io.cygnux.rapid.core.event.outbound.QueryPosition;
import io.cygnux.rapid.core.event.outbound.SubscribeMarketData;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component("backtestAdaptor")
public class BacktestAdaptor extends AbstractAdaptor {

    @Resource
    private BacktestMatchMachine matchMachine;

    /**
     *
     */
    public BacktestAdaptor() {
        super(new BacktestAccount(), false);
    }

    @Override
    protected boolean directSubscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {
        return false;
    }

    @Override
    protected boolean directNewOrder(@Nonnull NewOrder order) {

        return false;
    }

    @Override
    protected boolean directCancelOrder(@Nonnull CancelOrder order) {
        return false;
    }

    @Override
    protected boolean directQueryOrder(@Nonnull QueryOrder query) {
        return false;
    }

    @Override
    protected boolean directQueryPosition(@Nonnull QueryPosition query) {
        return false;
    }

    @Override
    protected boolean directQueryBalance(@Nonnull QueryBalance query) {
        return false;
    }

    @Override
    protected boolean startup0() {
        return false;
    }

    @Override
    public void close() {
        log.info("Closing BacktestAdaptor");
    }

    public void onNext() {
        matchMachine.doNext();
    }


}
