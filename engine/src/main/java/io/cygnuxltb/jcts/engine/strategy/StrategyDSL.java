package io.cygnuxltb.jcts.engine.strategy;


import io.cygnuxltb.jcts.core.account.SubAccount;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.strategy.Strategy;

public final class StrategyDSL {

    private StrategyDSL() {

    }


    public static StrategyDSL useAvro() {
        return null;
    }

    public static StrategyDSL useWire() {
        return null;
    }

    public static StrategyDSL subAccount(SubAccount subAccount){
        return null;
    }




    Instrument instrument;

    public static class StrategyBuilder<M extends MarketData> {

        private final int strategyId;

        private final String strategyName;

        public StrategyBuilder(int strategyId, String strategyName) {
            this.strategyId = strategyId;
            this.strategyName = strategyName;
        }

        public <M extends MarketData> Strategy<M> build() {
            return null;
        }


    }


}
