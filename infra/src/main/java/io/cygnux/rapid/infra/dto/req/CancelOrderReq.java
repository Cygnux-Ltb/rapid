package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class CancelOrderReq {

    /**
     * 用户ID [*]
     */
    private int userid;

    /**
     * 订单系统编号 [*]
     */
    private long ordSysId;

    /**
     * 撤单数量; 最少为(1) [*]
     */
    private int cancelQty;

    /**
     * 委托开始时间; 可精确到秒, 时间格式[yyyymmdd-HHMMSS]
     */
    private String offerStartTime;

    /**
     * 委托结束时间; 可精确到秒, 时间格式[yyyymmdd-HHMMSS]
     */
    private String offerEndTime;

}
