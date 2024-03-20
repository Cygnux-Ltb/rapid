package io.cygnuxltb.protocol.http.request;

public class CancelOrderDTO {

    /**
     * [不可为空]用户ID [*]
     */
    private int userId;

    /**
     * [不可为空]订单系统编号 [*]
     */
    private long ordSysId;

    /**
     * [不可为空]撤单数量, 最少为1 [*]
     */
    private int cancelQty;

    /**
     * [可为空]委托开始时间, 可精确到毫米, 时间格式[yyyymmdd-HHMMSS.SSS]
     */
    private String offerStartTime;

    /**
     * [可为空]委托结束时间, 可精确到毫米, 时间格式[yyyymmdd-HHMMSS.SSS]
     */
    private String offerEndTime;

}
