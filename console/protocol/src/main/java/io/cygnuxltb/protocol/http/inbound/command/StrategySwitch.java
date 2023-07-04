package io.cygnuxltb.protocol.http.inbound.command;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StrategySwitch implements Comparable<StrategySwitch> {
    
    private int productId;

    private int strategyId;

    private String instrumentCode;

    private boolean tradable;

    public String getKey() {
        return productId + "-" + strategyId + "-" + instrumentCode;
    }

    @Override
    public int compareTo(StrategySwitch o) {
        int compared = Integer.compare(this.productId, o.productId);
        if (compared != 0)
            return compared;
        else {
            compared = Integer.compare(this.strategyId, o.strategyId);
            if (compared != 0)
                return compared;
            else
                return o.instrumentCode.equals(this.instrumentCode)
                        ? 0 : 1;
        }
    }

}
