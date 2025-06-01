package io.cygnux.rapid.core.instrument.stock;

import io.cygnux.rapid.core.instrument.Exchange;
import io.cygnux.rapid.core.instrument.TradablePeriod;
import io.cygnux.rapid.core.instrument.enums.PriceMultiplier;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * [ChinaStockInstrument]实现
 *
 * @author yellow013
 */
public final class ChinaStockInstrument extends AbstractStock {

    public ChinaStockInstrument(int instrumentId, String instrumentCode,
                                Exchange exchange, PriceMultiplier multiplier,
                                ImmutableList<TradablePeriod> tradablePeriods) {
        super(instrumentId, instrumentCode, exchange, multiplier, 1, tradablePeriods);
    }

}
