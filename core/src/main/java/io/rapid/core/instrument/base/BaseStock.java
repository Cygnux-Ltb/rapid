package io.rapid.core.instrument.base;

import io.rapid.core.instrument.Exchange;
import io.rapid.core.instrument.Symbol;
import io.rapid.core.instrument.enums.InstrumentType;
import io.rapid.core.instrument.enums.PriceMultiplier;
import org.eclipse.collections.api.list.ImmutableList;

public abstract class BaseStock extends BaseInstrument implements Symbol {

    private final int tickSize;

    protected BaseStock(int instrumentId, String instrumentCode, Exchange exchange,
                        PriceMultiplier priceMultiplier, int tickSize,
                        ImmutableList<TradingPeriod> tradablePeriods) {
        super(instrumentId, instrumentCode, exchange);
        this.tickSize = tickSize;
    }

    @Override
    public InstrumentType getType() {
        return InstrumentType.STOCK;
    }

    @Override
    public int getSymbolId() {
        return instrumentId;
    }

    @Override
    public String getSymbolCode() {
        return instrumentCode;
    }

    @Override
    public Symbol getSymbol() {
        return this;
    }

    @Override
    public int getTickSize() {
        return tickSize;
    }

    @Override
    public PriceMultiplier getMultiplier() {
        // TODO Auto-generated method stub
        return null;
    }

}
