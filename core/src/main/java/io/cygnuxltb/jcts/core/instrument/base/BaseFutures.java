package io.cygnuxltb.jcts.core.instrument.base;


import io.cygnuxltb.jcts.core.instrument.Symbol;
import io.cygnuxltb.jcts.core.instrument.attr.InstrumentType;

public abstract class BaseFutures extends BaseInstrument {

    private final Symbol symbol;

    protected BaseFutures(int instrumentId, String instrumentCode, Symbol symbol) {
        super(instrumentId, instrumentCode, symbol.getExchange());
        this.symbol = symbol;
    }

    @Override
    public InstrumentType getType() {
        return InstrumentType.FUTURES;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    public static int generateSymbolId(int exchangeId, int serialInExchange) {
        return exchangeId + serialInExchange * 100000;
    }

}
