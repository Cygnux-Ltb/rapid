package io.cygnux.rapid.core.types.instrument.stock;

import io.cygnux.rapid.core.types.instrument.Exchange;
import io.cygnux.rapid.core.types.instrument.TradablePeriod;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * [ChinaStockInstrument]实现
 *
 * @author yellow013
 */
public final class ChinaStockInstrument extends AbstractStock {

    public ChinaStockInstrument(int instrumentId, String instrumentCode,
                                Exchange exchange, ImmutableList<TradablePeriod> tradablePeriods) {
        super(instrumentId, instrumentCode, exchange, 1, tradablePeriods);
    }

}
