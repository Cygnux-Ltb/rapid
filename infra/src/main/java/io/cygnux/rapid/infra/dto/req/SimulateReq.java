package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class SimulateReq {

    /**
     * [不可为空] 用户ID
     */
    private int userid;

    /**
     * [不可为空] 策略ID
     */
    private String strategyId;

    /**
     * [不可为空] 投资组合(股票池/目标池) 代码
     */
    private String portfolioCode;

    /**
     * [不可为空] 委托金额
     */
    private double offerAmount;

    /**
     * [可为空] 开始时间, 精确到秒, 时间格式[YYYYMMDD-HHMMSS]
     */
    private String startTime;

    /**
     * [可为空] 结束时间, 精确到秒, 时间格式[YYYYMMDD-HHMMSS]
     */
    private String endTime;

}
