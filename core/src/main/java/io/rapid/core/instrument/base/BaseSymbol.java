package io.rapid.core.instrument.base;

import io.rapid.core.instrument.Exchange;
import io.rapid.core.instrument.Symbol;
import io.rapid.core.instrument.enums.PriceMultiplier;
import org.eclipse.collections.api.list.ImmutableList;

public abstract class BaseSymbol implements Symbol {

    protected final int symbolId;

    protected final String symbolCode;

    protected final Exchange exchange;

    protected final PriceMultiplier priceMultiplier;

    protected final ImmutableList<TradingPeriod> tradablePeriods;

    public BaseSymbol(int symbolId, String symbolCode, Exchange exchange, PriceMultiplier priceMultiplier,
                      ImmutableList<TradingPeriod> tradablePeriods) {
        this.symbolId = symbolId;
        this.symbolCode = symbolCode;
        this.exchange = exchange;
        this.priceMultiplier = priceMultiplier;
        this.tradablePeriods = tradablePeriods;
    }

    @Override
    public int getSymbolId() {
        return symbolId;
    }

    @Override
    public String getSymbolCode() {
        return symbolCode;
    }

    @Override
    public Exchange getExchange() {
        return exchange;
    }

    @Override
    public PriceMultiplier getMultiplier() {
        return priceMultiplier;
    }

    @Override
    public ImmutableList<TradingPeriod> getTradablePeriods() {
        return tradablePeriods;
    }

}
