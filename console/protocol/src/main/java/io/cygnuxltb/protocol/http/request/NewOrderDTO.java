package io.cygnuxltb.protocol.http.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class NewOrderDTO {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 交易标的 (例: 期货代码, 股票代码)
     */
    private String instrumentCode;

    /**
     * 订单交易类型 (1:买入, 2:卖出)
     */
    private int side;

    /**
     * 交易动作 (1: 开仓, 2: 平仓)
     */
    private int action;

    /**
     * 委托价格
     */
    private double offerPrice;

    /**
     * 委托数量
     */
    private int offerQty;

    /**
     * 订单类型 (1:COMMON, 2:FAK, 3:FOK) [可为空]
     */
    private int type;

    /**
     * 有效类型 (1:GTC, 2:GTD, 3:GFD) [可为空]
     */
    private int valid;

}
