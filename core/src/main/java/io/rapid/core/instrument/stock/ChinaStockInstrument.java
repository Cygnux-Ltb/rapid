package io.rapid.core.instrument.stock;

import io.rapid.core.instrument.Exchange;
import io.rapid.core.instrument.base.BaseStock;
import io.rapid.core.instrument.base.TradingPeriod;
import io.rapid.core.instrument.enums.PriceMultiplier;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * [ChinaStockInstrument]实现
 *
 * @author yellow013
 */
public final class ChinaStockInstrument extends BaseStock {

    public ChinaStockInstrument(int instrumentId, String instrumentCode,
                                Exchange exchange, PriceMultiplier multiplier,
                                ImmutableList<TradingPeriod> tradablePeriods) {
        super(instrumentId, instrumentCode, exchange, multiplier, 1, tradablePeriods);
    }

}