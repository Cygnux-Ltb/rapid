package io.cygnux.rapid.core.types.instrument.futures;

import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.instrument.enums.PriceMultiplier;
import io.cygnux.rapid.core.types.instrument.enums.PriorityCloseType;

import static io.cygnux.rapid.core.types.instrument.Exchange.ZCE;
import static io.cygnux.rapid.core.types.instrument.futures.ChinaFutures.PRICE_MULTIPLIER;
import static io.cygnux.rapid.core.types.instrument.futures.ChinaFutures.parseInstrumentTerm;
import static io.cygnux.rapid.core.types.instrument.futures.ChinaFutures.parseSymbolCode;

/**
 * @author yellow013
 */
public final class ChinaFuturesInstrument extends AbstractFutures {

    private final PriorityCloseType priorityCloseType;

    /**
     * @param symbol         ChinaFuturesSymbol
     * @param instrumentId   int
     * @param instrumentCode String
     */
    ChinaFuturesInstrument(ChinaFuturesSymbol symbol, int instrumentId, String instrumentCode) {
        super(instrumentId, instrumentCode, symbol);
        this.priorityCloseType = symbol.getPriorityCloseType();
    }

    @Override
    public PriorityCloseType getPriorityCloseType() {
        return priorityCloseType;
    }

    @Override
    public PriceMultiplier getMultiplier() {
        return PRICE_MULTIPLIER;
    }

    static Instrument newInstance(String instrumentCode) {
        return newInstance(ChinaFuturesSymbol.of(
                        // 解析SymbolCode
                        parseSymbolCode(instrumentCode)),
                // 分析期限
                parseInstrumentTerm(instrumentCode));
    }

    /**
     * @param symbol ChinaFuturesSymbol
     * @param term   int
     * @return Instrument
     */
    static Instrument newInstance(ChinaFuturesSymbol symbol, int term) {
        int instrumentId = symbol.acquireInstrumentId(term);
        String instrumentCode;
        // 对郑商所合约代码做特殊处理, 去除最高位年份, 比如CF2405需要处理为CF405
        if (symbol.getExchange() == ZCE) {
            instrumentCode = symbol.getSymbolCode() + String.valueOf(term).substring(1);
        } else {
            instrumentCode = symbol.getSymbolCode() + term;
        }
        return new ChinaFuturesInstrument(symbol, instrumentId, instrumentCode);
    }

}
