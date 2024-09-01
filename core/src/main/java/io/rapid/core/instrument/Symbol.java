package io.rapid.core.instrument;

import io.mercury.common.functional.Formatter;
import io.mercury.common.util.StringSupport;
import io.rapid.core.instrument.base.TradingPeriod;
import io.rapid.core.instrument.enums.PriceMultiplier;
import org.eclipse.collections.api.list.ImmutableList;

public interface Symbol extends Formatter<String> {

    Exchange getExchange();

    int getSymbolId();

    String getSymbolCode();

    ImmutableList<TradingPeriod> getTradablePeriods();

    ImmutableList<Instrument> getInstruments();

    PriceMultiplier getMultiplier();

    int getTickSize();

    default boolean isSymbolCode(String code) {
        if (StringSupport.isNullOrEmpty(code))
            return false;
        return getSymbolCode().equalsIgnoreCase(code);
    }

}
