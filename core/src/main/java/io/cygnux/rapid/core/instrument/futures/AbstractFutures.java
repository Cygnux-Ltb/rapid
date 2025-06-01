package io.cygnux.rapid.core.instrument.futures;


import io.cygnux.rapid.core.instrument.AbstractInstrument;
import io.cygnux.rapid.core.instrument.Symbol;
import io.cygnux.rapid.core.instrument.enums.InstrumentType;

public abstract class AbstractFutures extends AbstractInstrument {

    private final Symbol symbol;

    /**
     * @param instrumentId   * FUTURES: exchange|symbol|term<br>
     *                       * MAX_VALUE  == 214|  74  |83647<br>
     * @param instrumentCode String
     * @param symbol         Symbol
     */
    protected AbstractFutures(int instrumentId, String instrumentCode, Symbol symbol) {
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

    public static final int TERM_MASK = 100000;

    public static int generateSymbolId(int exchangeId, int serialInExchange) {
        return exchangeId + serialInExchange * TERM_MASK;
    }

}
