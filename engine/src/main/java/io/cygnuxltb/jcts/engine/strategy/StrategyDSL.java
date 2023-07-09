package io.cygnuxltb.jcts.engine.strategy;

import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.strategy.Strategy;

public final class StrategyDSL {

    private StrategyDSL() {

    }


    public static StrategyDSL useAvro() {

    }

    public static StrategyDSL useWire() {

    }

    public static StrategyDSL subAccount(SubAccount subAccount){

    }




    Instrument instrument

    public static class StrategyBuilder<M extends MarketData> {

        private final int strategyId;

        private final String strategyName;

        public StrategyBuilder(int strategyId, String strategyName) {
            this.strategyId = strategyId;
            this.strategyName = strategyName;
        }

        public <M extends MarketData> Strategy<M> build() {

        }


    }


}
