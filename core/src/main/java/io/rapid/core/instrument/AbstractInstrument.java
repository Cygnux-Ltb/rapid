package io.rapid.core.instrument;

import io.mercury.common.state.EnableableComponent;
import io.mercury.serialization.json.JsonWriter;
import lombok.Getter;

import java.util.HashMap;

public abstract non-sealed class AbstractInstrument extends EnableableComponent
        implements Instrument {

    /**
     * 唯一ID
     */
    @Getter
    protected final int instrumentId;

    /**
     * 唯一代码
     */
    @Getter
    protected final String instrumentCode;

    /**
     * 所属交易所
     */
    @Getter
    protected final Exchange exchange;

    /**
     * @param instrumentId   int
     * @param instrumentCode String
     * @param exchange       Exchange
     */
    protected AbstractInstrument(int instrumentId, String instrumentCode, Exchange exchange) {
        this.instrumentId = instrumentId;
        this.instrumentCode = instrumentCode;
        this.exchange = exchange;
    }

    private String formatText;

    @Override
    public String toString() {
        if (formatText == null) {
            var map = new HashMap<>();
            map.put("type", getType());
            map.put("instrumentId", getInstrumentId());
            map.put("instrumentCode", getInstrumentCode());
            map.put("symbol", getSymbol());
            this.formatText = JsonWriter.toJson(map);
        }
        return formatText;
    }

}
