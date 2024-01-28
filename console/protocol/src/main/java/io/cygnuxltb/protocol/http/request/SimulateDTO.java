package io.cygnuxltb.protocol.http.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SimulateDTO {

    /**
     * 用户ID
     */
    public int userId;

    /**
     * 策略ID
     */
    public int strategyId;

    /**
     * 开始交易日
     */
    public int startTradingDay;

    /**
     * 结束交易日
     */
    public int endTradingDay;

}
