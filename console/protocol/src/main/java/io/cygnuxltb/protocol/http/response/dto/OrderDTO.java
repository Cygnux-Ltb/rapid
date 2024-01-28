package io.cygnuxltb.protocol.http.response.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 订单基本信息
 * [*] 不可为空字段
 * [V] 可变字段
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class OrderDTO {

    /**
     * 交易日
     */
    private int tradingDay;

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 交易标的代码
     */
    private String instrumentCode;

    /**
     * 交易标的名称
     */
    private String instrumentName;

    /**
     * 投资者ID
     */
    private String investorId;

    /**
     * 经纪商ID
     */
    private String brokerId;

    /**
     * 交易账户ID
     */
    private int accountId;

    /**
     * 子账户ID
     */
    private int subAccountId;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 订单系统编号 [*]
     */
    private long ordSysId;

    /**
     * 订单类型
     */
    private String ordType;

    /**
     * 订单引用
     */
    private String orderRef;

    /**
     * 订单方向
     */
    private char direction;

    /**
     * 订单交易类型
     */
    private char side;

    /**
     * 当前状态 [V]
     */
    private int status;

    /**
     * 当前状态信息
     */
    private String statusMsg;

    /**
     * 委托价格
     */
    private double offerPrice;

    /**
     * 委托数量
     */
    private int offerQty;

    /**
     * 创建时间
     */
    private LocalDateTime insertTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 前置机ID
     */
    private int frontId;

    /**
     * 会话ID
     */
    private int sessionId;

    /**
     * 交易费用
     */
    private double fee;

    /**
     * 交易通道类型
     */
    private String channelType;

    /**
     * 备注
     */
    private String remark;

}
