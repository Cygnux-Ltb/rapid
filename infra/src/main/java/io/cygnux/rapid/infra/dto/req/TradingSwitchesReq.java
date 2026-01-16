package io.cygnux.rapid.infra.dto.req;

import io.mercury.serialization.json.JsonWriter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;

@Getter
@Setter
@Accessors(chain = true)
public final class TradingSwitchesReq implements Comparable<TradingSwitchesReq> {

    /**
     * 产品ID
     */
    private int productId;

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 交易标的
     */
    private String instrumentCode;

    /**
     * 可交易标识
     */
    private boolean tradable;

    public String getKey() {
        return productId + "-" + strategyId + "-" + instrumentCode;
    }

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

    @Override
    public int compareTo(@Nonnull TradingSwitchesReq o) {
        if (this == o) return 0;
        int compared = Integer.compare(this.productId, o.productId);
        if (compared != 0) return compared;
        compared = Integer.compare(this.strategyId, o.strategyId);
        if (compared != 0) return compared;
        return this.instrumentCode.compareTo(o.instrumentCode);
    }

}
