package io.cygnuxltb.protocol.http.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 订单事件表
 * Trade Entity 订单事件
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class OrderEventDTO {

    /**
     * 交易日 [*]
     */
    private int tradingDay;

    /**
     * Strategy ID [*]
     */
    private int strategyId;

    /**
     * 交易标的代码 [*]
     */
    private String instrumentCode;

    /**
     * Investor ID [*]
     */
    private String investorId;

    /**
     * Broker ID [*]
     */
    private String brokerId;

    /**
     * Account ID [*]
     */
    private int accountId;

    /**
     * SubAccount ID [*]
     */
    private int subAccountId;

    /**
     * User ID [*]
     */
    private int userId;

    /**
     * Order System ID [*]
     */
    private long ordSysId;

    /**
     * 订单引用(Order Reference)
     */
    private String orderRef;

    /**
     * 订单消息类型
     */
    private int orderMsgType;

    /**
     * 买/卖
     */
    private char offset;

    /**
     * 方向
     */
    private char direction;

    /**
     * 指定价格
     */
    private double limitPrice;

    /**
     * 订单状态
     */
    private int status;

    /**
     * 状态信息
     */
    private String statusMsg;


    /**
     * 经纪商系统ID
     */
    private long brokerSysID;

    /**
     * 数量
     */
    private int volume;

    /**
     * 成交数量
     */
    private int volumeFilled;

    /**
     * 剩余数量
     */
    private int volumeRemained;

    /**
     * 价格
     */
    private double price;

    /**
     * 成交ID
     */
    private String tradeId;

    /**
     * 订单拒绝理由
     */
    private int ordRejReason;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 备注
     */
    private String remark;

}
