package io.cygnuxltb.protocol.http.response.status;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StrategyStatus {

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 策略状态
     */
    private int status;

}
