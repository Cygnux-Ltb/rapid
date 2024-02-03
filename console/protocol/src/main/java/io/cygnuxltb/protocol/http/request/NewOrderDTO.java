package io.cygnuxltb.protocol.http.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class NewOrderDTO {

    /**
     * [不可为空]用户ID
     */
    private int userId;

    /**
     * [不可为空]交易标的 (例: 期货代码, 股票代码)
     */
    private String instrumentCode;

    /**
     * [可为空]策略名
     */
    private String strategyName;

    /**
     * [不可为空]订单交易类型 (1:买入, 2:卖出)
     */
    private int side;

    /**
     * [不可为空]交易动作 (1:开仓; 2:平仓)
     */
    private int action;

    /**
     * [不可为空]委托价格
     */
    private double offerPrice;

    /**
     * [可为空, 默认: 0]价格限制类型 (0:无限制; 1:涨停不卖; 2:跌停不买; 3:涨跌停同时限制)
     */
    private int priceLimitType;

    /**
     * [不可为空]委托数量
     */
    private int offerQty;

    /**
     * [可为空]委托开始时间, 可精确到毫米, 时间格式[yyyymmdd-HHMMSS.SSS]
     */
    private String offerStartTime;

    /**
     * [可为空]委托结束时间, 可精确到毫米, 时间格式[yyyymmdd-HHMMSS.SSS]
     */
    private String offerEndTime;

    /**
     * [可为空, 默认: 1]订单类型 (1:COMMON, 2:FAK, 4:FOK)
     */
    private int type;

    /**
     * [可为空, 默认: 1]有效类型 (1:GTC, 2:GTD, 3:GFD)
     */
    private int valid;

    /**
     * [可为空]请求时间, 前端请求时间戳, 可自行填充
     */
    private long requestTime;

}
