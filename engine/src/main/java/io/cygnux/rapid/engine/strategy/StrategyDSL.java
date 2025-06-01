package io.cygnux.rapid.engine.strategy;

import io.mercury.common.collections.ImmutableMaps;
import io.mercury.common.collections.ImmutableSets;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.strategy.Strategy;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

public final class StrategyDSL {

    private StrategyDSL() {

    }

    public static class StrategyBuilder {

        private final int strategyId;

        private final String strategyName;

        private ImmutableIntObjectMap<Instrument> instruments;

        public StrategyBuilder(int strategyId, String strategyName) {
            this.strategyId = strategyId;
            this.strategyName = strategyName;
        }

        public StrategyBuilder setInstrument(Instrument... instruments) {
            this.instruments = ImmutableMaps.newImmutableIntMap(
                    ImmutableSets.from(instruments),
                    Instrument::getInstrumentId);
            return this;
        }

        public Strategy build() {

            return null;
        }


    }


}
