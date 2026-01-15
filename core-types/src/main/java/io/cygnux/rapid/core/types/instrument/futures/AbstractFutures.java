package io.cygnux.rapid.core.types.instrument.futures;


import io.cygnux.rapid.core.types.instrument.AbstractInstrument;
import io.cygnux.rapid.core.types.instrument.Symbol;
import io.cygnux.rapid.core.types.instrument.enums.InstrumentType;
import lombok.Getter;

public abstract class AbstractFutures extends AbstractInstrument {

    @Getter
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

    public static final int TERM_MASK = 100000;

    public static int generateSymbolId(int exchangeId, int serialInExchange) {
        return exchangeId + serialInExchange * TERM_MASK;
    }

}
