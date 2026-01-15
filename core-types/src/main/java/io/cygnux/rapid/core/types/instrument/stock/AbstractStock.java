package io.cygnux.rapid.core.types.instrument.stock;

import io.cygnux.rapid.core.types.instrument.AbstractInstrument;
import io.cygnux.rapid.core.types.instrument.Exchange;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.instrument.Symbol;
import io.cygnux.rapid.core.types.instrument.TradablePeriod;
import io.cygnux.rapid.core.types.instrument.enums.InstrumentType;
import io.cygnux.rapid.core.types.instrument.enums.PriceMultiplier;
import io.mercury.common.collections.ImmutableLists;
import lombok.Getter;
import org.eclipse.collections.api.list.ImmutableList;

public abstract class AbstractStock extends AbstractInstrument implements Symbol {

    @Getter
    private final double tickSize;

    @Getter
    private final ImmutableList<TradablePeriod> tradablePeriods;

    @Getter
    private final ImmutableList<Instrument> instruments;

    protected AbstractStock(int instrumentId, String instrumentCode, Exchange exchange, int tickSize,
                            ImmutableList<TradablePeriod> tradablePeriods) {
        super(instrumentId, instrumentCode, exchange);
        this.tickSize = tickSize;
        this.tradablePeriods = tradablePeriods;
        this.instruments = ImmutableLists.newImmutableList(this);
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
    public PriceMultiplier getMultiplier() {
        return PriceMultiplier.NONE;
    }

}
