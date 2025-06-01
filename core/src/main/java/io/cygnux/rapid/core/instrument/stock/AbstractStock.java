package io.cygnux.rapid.core.instrument.stock;

import io.mercury.common.collections.ImmutableLists;
import io.cygnux.rapid.core.instrument.AbstractInstrument;
import io.cygnux.rapid.core.instrument.Exchange;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.instrument.Symbol;
import io.cygnux.rapid.core.instrument.TradablePeriod;
import io.cygnux.rapid.core.instrument.enums.InstrumentType;
import io.cygnux.rapid.core.instrument.enums.PriceMultiplier;
import lombok.Getter;
import org.eclipse.collections.api.list.ImmutableList;

public abstract class AbstractStock extends AbstractInstrument implements Symbol {

    @Getter
    private final double tickSize;

    @Getter
    private final ImmutableList<TradablePeriod> tradablePeriods;

    @Getter
    private final ImmutableList<Instrument> instruments;

    protected AbstractStock(int instrumentId, String instrumentCode, Exchange exchange,
                            PriceMultiplier priceMultiplier, int tickSize,
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
