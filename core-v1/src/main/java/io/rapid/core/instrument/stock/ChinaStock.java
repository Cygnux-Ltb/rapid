package io.rapid.core.instrument.stock;

import io.rapid.core.instrument.Exchange;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.Symbol;
import io.rapid.core.instrument.enums.PriceMultiplier;
import io.rapid.core.instrument.base.TradingPeriod;
import io.rapid.core.instrument.base.BaseStock;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * 此class仅作为namespace使用
 *
 * @author yellow013
 */
public final class ChinaStock {

    private ChinaStock() {
    }

    public static final class ChinaStockInstrument extends BaseStock implements Symbol {

        public ChinaStockInstrument(int instrumentId, String instrumentCode,
                                    Exchange exchange, PriceMultiplier multiplier,
                                    ImmutableList<TradingPeriod> tradablePeriods) {
            super(instrumentId, instrumentCode, exchange, multiplier, 1, tradablePeriods);
        }

        @Override
        public int getTickSize() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public ImmutableList<TradingPeriod> getTradablePeriods() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean isSymbolCode(String code) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public ImmutableList<Instrument> getInstruments() {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
