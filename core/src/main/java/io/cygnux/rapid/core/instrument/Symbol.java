package io.cygnux.rapid.core.instrument;

import io.mercury.common.util.StringSupport;
import io.cygnux.rapid.core.instrument.enums.PriceMultiplier;
import org.eclipse.collections.api.list.ImmutableList;

public interface Symbol {

    Exchange getExchange();

    int getSymbolId();

    String getSymbolCode();

    ImmutableList<TradablePeriod> getTradablePeriods();

    ImmutableList<Instrument> getInstruments();

    PriceMultiplier getMultiplier();

    double getTickSize();

    default boolean isSymbolCode(String code) {
        if (StringSupport.isNullOrEmpty(code))
            return false;
        return getSymbolCode().equalsIgnoreCase(code);
    }

}
