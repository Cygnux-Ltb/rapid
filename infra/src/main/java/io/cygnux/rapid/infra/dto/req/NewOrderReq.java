package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class NewOrderReq {

    /**
     * [*] 用户ID
     */
    private int userid;

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * [*] 交易标的 (例: 期货代码, 股票代码)
     */
    private String instrumentCode;

    /**
     * 订单交易方向, 默认:0 (0:由策略自行决定; 1:买入; 2:卖出)
     */
    private int side;

    /**
     * [*] 交易动作 (1:开仓; 2:平仓)
     */
    private int action;

    /**
     * 委托价格 [*]
     */
    private double offerPrice;

    /**
     * 价格限制类型, 默认:0 (0:无限制; 1:涨停不卖; 2:跌停不买; 3:涨跌停同时限制)
     */
    private int priceLimitType;

    /**
     * 委托金额 (委托金额/委托数量必须提交一项) [#]
     */
    private double offerAmount;

    /**
     * 委托数量 (委托金额/委托数量必须提交一项) [#]
     */
    private int offerQty;

    /**
     * 订单类型, 默认:1 (1:COMMON, 2:FAK, 4:FOK)
     */
    private int type;

    /**
     * 有效类型, 默认:1 (1:GTC, 2:GTD, 3:GFD)
     */
    private int valid;

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
