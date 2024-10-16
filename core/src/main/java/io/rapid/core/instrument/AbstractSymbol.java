package io.rapid.core.instrument;

import io.rapid.core.instrument.enums.PriceMultiplier;
import lombok.Getter;
import org.eclipse.collections.api.list.ImmutableList;

public abstract class AbstractSymbol implements Symbol {

    @Getter
    protected final int symbolId;

    @Getter
    protected final String symbolCode;

    @Getter
    protected final Exchange exchange;

    @Getter
    protected final PriceMultiplier multiplier;

    @Getter
    protected final ImmutableList<TradablePeriod> tradablePeriods;

    public AbstractSymbol(int symbolId, String symbolCode, Exchange exchange, PriceMultiplier multiplier,
                          ImmutableList<TradablePeriod> tradablePeriods) {
        this.symbolId = symbolId;
        this.symbolCode = symbolCode;
        this.exchange = exchange;
        this.multiplier = multiplier;
        this.tradablePeriods = tradablePeriods;
    }

}
