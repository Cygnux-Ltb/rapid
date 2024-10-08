package io.rapid.core.order;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.enums.TrdAction;
import io.rapid.core.order.enums.TrdDirection;
import io.mercury.common.state.Signal;

public interface TradeSignal extends Signal {

    Instrument getInstrument();

    int getStrategyId();

    TrdAction getAction();

    TrdDirection getDirection();

    static TradeSignal newOpenLongSignal(Instrument instrument, int strategyId) {
        return new OpenLongSignal(instrument, strategyId);
    }

    static TradeSignal newOpenShortSignal(Instrument instrument, int strategyId) {
        return new OpenShortSignal(instrument, strategyId);
    }

    static TradeSignal newCloseLongSignal(Instrument instrument, int strategyId) {
        return new CloseLongSignal(instrument, strategyId);
    }

    static TradeSignal newCloseShortSignal(Instrument instrument, int strategyId) {
        return new CloseShortSignal(instrument, strategyId);
    }

    abstract class BaseTradeSignal implements TradeSignal {

        private final Instrument instrument;

        private final int strategyId;

        public BaseTradeSignal(Instrument instrument, int strategyId) {
            this.instrument = instrument;
            this.strategyId = strategyId;
        }

        public Instrument getInstrument() {
            return instrument;
        }

        public int getStrategyId() {
            return strategyId;
        }

    }

    class OpenLongSignal extends BaseTradeSignal {

        private OpenLongSignal(Instrument instrument, int strategyId) {
            super(instrument, strategyId);
        }

        @Override
        public TrdAction getAction() {
            return TrdAction.Open;
        }

        @Override
        public TrdDirection getDirection() {
            return TrdDirection.Long;
        }

        @Override
        public int getSignalCode() {
            return 1;
        }
    }

    class OpenShortSignal extends BaseTradeSignal {

        private OpenShortSignal(Instrument instrument, int strategyId) {
            super(instrument, strategyId);
        }

        @Override
        public TrdAction getAction() {
            return TrdAction.Open;
        }

        @Override
        public TrdDirection getDirection() {
            return TrdDirection.Short;
        }

        @Override
        public int getSignalCode() {
            return 2;
        }
    }

    class CloseLongSignal extends BaseTradeSignal {

        private CloseLongSignal(Instrument instrument, int strategyId) {
            super(instrument, strategyId);
        }

        @Override
        public TrdAction getAction() {
            return TrdAction.Close;
        }

        @Override
        public TrdDirection getDirection() {
            return TrdDirection.Long;
        }

        @Override
        public int getSignalCode() {
            return 4;
        }
    }

    class CloseShortSignal extends BaseTradeSignal {

        private CloseShortSignal(Instrument instrument, int strategyId) {
            super(instrument, strategyId);
        }

        @Override
        public TrdAction getAction() {
            return TrdAction.Close;
        }

        @Override
        public TrdDirection getDirection() {
            return TrdDirection.Short;
        }

        @Override
        public int getSignalCode() {
            return 8;
        }

    }


}
