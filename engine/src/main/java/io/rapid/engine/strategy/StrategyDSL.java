package io.rapid.engine.strategy;

import io.rapid.core.account.SubAccount;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.strategy.Strategy;

public final class StrategyDSL {

    private StrategyDSL() {

    }


    public static StrategyDSL useAvro() {
        return null;
    }

    public static StrategyDSL useWire() {
        return null;
    }

    public static StrategyDSL subAccount(SubAccount subAccount) {
        return null;
    }


    Instrument instrument;

    public static class StrategyBuilder {

        private final int strategyId;

        private final String strategyName;

        public StrategyBuilder(int strategyId, String strategyName) {
            this.strategyId = strategyId;
            this.strategyName = strategyName;
        }

        public Strategy build() {
            return null;
        }


    }


}
