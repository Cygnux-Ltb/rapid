package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
public final class OrderRsp {

    /**
     * 交易日
     */
    private int tradingDay;

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 策略名称
     */
    private String strategyName;

    /**
     * 交易标的代码
     */
    private String instrumentCode;

    /**
     * 交易标的名称
     */
    private String instrumentName;

    /**
     * 投资者CODE
     */
    private String investorCode;

    /**
     * 经纪商Code
     */
    private String brokerCode;

    /**
     * 经纪商名称
     */
    private String brokerName;

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
     * 用户名
     */
    private int username;

    /**
     * 订单系统编号 [*]
     */
    private long ordSysId;

    /**
     * 订单类型
     */
    private int ordType;

    /**
     * 订单类型名称
     */
    private String ordTypeName;

    /**
     * 订单引用
     */
    private String orderRef;

    /**
     * 订单方向
     */
    private int direction;

    /**
     * 订单交易类型
     */
    private int side;

    /**
     * 当前状态 [V]
     */
    private int status;

    /**
     * 当前状态名称
     */
    private String statusName;

    /**
     * 委托价格
     */
    private double offerPrice;

    /**
     * 委托数量
     */
    private int offerQty;

    /**
     * 交易费用
     */
    private double fee;

    /**
     * 创建时间
     */
    private String insertTime;

    /**
     * 取消时间
     */
    private String cancelTime;

    /**
     * 交易适配器代码
     */
    private int adaptorCode;

    /**
     * 交易通道名称
     */
    private String adapterName;

    /**
     * 备注0
     */
    private String remark0;

    /**
     * 备注1
     */
    private String remark1;


}
