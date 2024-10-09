package io.rapid.core.instrument.stock;

import io.mercury.common.collections.ImmutableLists;
import io.rapid.core.instrument.AbstractInstrument;
import io.rapid.core.instrument.Exchange;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.Symbol;
import io.rapid.core.instrument.TradingPeriod;
import io.rapid.core.instrument.enums.InstrumentType;
import io.rapid.core.instrument.enums.PriceMultiplier;
import lombok.Getter;
import org.eclipse.collections.api.list.ImmutableList;

public abstract class AbstractStock extends AbstractInstrument implements Symbol {

    @Getter
    private final double tickSize;

    @Getter
    private final ImmutableList<TradingPeriod> tradablePeriods;

    @Getter
    private final ImmutableList<Instrument> instruments;

    protected AbstractStock(int instrumentId, String instrumentCode, Exchange exchange,
                            PriceMultiplier priceMultiplier, int tickSize,
                            ImmutableList<TradingPeriod> tradablePeriods) {
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
