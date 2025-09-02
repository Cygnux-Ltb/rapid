package io.cygnux.rapid.core.instrument;

import io.mercury.common.state.EnableableComponent;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.core.stream.enums.SubscribeStatus;
import io.cygnux.rapid.core.stream.enums.TradingStatus;
import lombok.Getter;
import lombok.Setter;

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
     * 交易费用
     */
    @Getter
    @Setter
    protected double tradeFee;

    /**
     * [Instrument]状态
     */
    @Getter
    protected InstrumentStatus status = new InstrumentStatus()
            // 初始化交易状态
            .setTradingStatus(TradingStatus.NO_TRADING)
            // 初始化订阅状态
            .setSubscribeStatus(SubscribeStatus.SUBSCRIPTION_UNAVAILABLE);

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
