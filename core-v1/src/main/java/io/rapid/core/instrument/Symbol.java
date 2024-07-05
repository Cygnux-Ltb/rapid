package io.rapid.core.instrument;

import io.rapid.core.instrument.enums.PriceMultiplier;
import io.rapid.core.instrument.base.TradingPeriod;
import io.mercury.common.functional.Formatter;
import org.eclipse.collections.api.list.ImmutableList;

public interface Symbol extends Formatter<String> {

    Exchange getExchange();

    int getSymbolId();

    String getSymbolCode();

    ImmutableList<TradingPeriod> getTradablePeriods();

    ImmutableList<Instrument> getInstruments();

    PriceMultiplier getMultiplier();

    int getTickSize();

    boolean isSymbolCode(String code);

}
