package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class StrategyStatus {

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 策略状态
     */
    private int status;

}
