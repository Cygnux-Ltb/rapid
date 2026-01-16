package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class NewOrderBatchReq {

    /**
     * [*] 用户ID
     */
    private int userid;

    /**
     * [*] 策略ID
     */
    private String strategyId;

    /**
     * [*] 投资组合(股票池/目标池) 代码
     */
    private String portfolioCode;

    /**
     * [*] 委托金额
     */
    private double offerAmount;

    /**
     * 投资标的(单票)分配方式, 默认:0 (0:不指定; 1:金额平均; 2:数量平均)
     */
    private int executionType;

    /**
     * 价格限制类型, 默认:0 (0:无限制; 1:涨停不卖; 2:跌停不买; 3:涨跌停同时限制)
     */
    private int priceLimitType;

    /**
     * 订单交易方向, 默认:0 (0:由策略自行决定; 1:买入; 2:卖出)
     */
    private int side;

    /**
     * 委托开始时间, 可精确到秒, 格式[YYYYMMDD-HHMMSS]
     */
    private String offerStartTime;

    /**
     * 委托结束时间, 可精确到秒, 格式[YYYYMMDD-HHMMSS]
     */
    private String offerEndTime;

    /**
     * 请求时间, 前端请求时间戳, 可自行填充, 可精确到毫秒, 格式[YYYYMMDD-HHMMSS.SSS]
     */
    private long requestTime;

}
