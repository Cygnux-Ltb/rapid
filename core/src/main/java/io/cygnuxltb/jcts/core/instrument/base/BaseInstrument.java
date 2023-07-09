package io.cygnuxltb.jcts.core.instrument.base;

import io.horizon.market.instrument.Exchange;
import io.horizon.market.instrument.Instrument;
import io.mercury.common.fsm.EnableableComponent;
import io.mercury.serialization.json.JsonWrapper;

import java.util.HashMap;

public abstract class BaseInstrument extends EnableableComponent implements Instrument {

    // 唯一编码
    protected final int instrumentId;

    // String唯一编码
    protected final String instrumentCode;

    // 所属交易所
    protected final Exchange exchange;

    /**
     * @param instrumentId   int
     * @param instrumentCode String
     * @param exchange       Exchange
     */
    protected BaseInstrument(int instrumentId,
                             String instrumentCode,
                             Exchange exchange) {
        this.instrumentId = instrumentId;
        this.instrumentCode = instrumentCode;
        this.exchange = exchange;
    }

    @Override
    public int getInstrumentId() {
        return instrumentId;
    }

    @Override
    public String getInstrumentCode() {
        return instrumentCode;
    }

    @Override
    public Exchange getExchange() {
        return exchange;
    }

    @Override
    public String toString() {
        return instrumentCode;
    }

    private String formatText;

    @Override
    public String format() {
        if (formatText == null) {
            var map = new HashMap<>();
            map.put("type", getType());
            map.put("instrumentId", getInstrumentId());
            map.put("instrumentCode", getInstrumentCode());
            map.put("symbol", getSymbol());
            this.formatText = JsonWrapper.toJson(map);
        }
        return formatText;
    }

}
