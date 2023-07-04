package io.cygnuxltb.console.persistence.entity;

import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnuxltb.console.persistence.CommonConst.Column.ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.BROKER_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.CommonConst.Column.INVESTOR_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.SUB_ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.TRADING_DAY;
import static io.cygnuxltb.console.persistence.CommonConst.Column.USER_ID;

/**
 * 订单事件表
 * Trade Entity 订单事件
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TB_T_ORDER_EVENT")
public final class TbtOrderEvent {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * tradingDay [*]
     */
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * strategyId [*]
     */
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * instrumentCode [*]
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * investorId [*]
     */
    @Column(name = INVESTOR_ID)
    private String investorId;

    /**
     * brokerId [*]
     */
    @Column(name = BROKER_ID)
    private String brokerId;

    /**
     * accountId [*]
     */
    @Column(name = ACCOUNT_ID)
    private int accountId;

    /**
     * subAccountId [*]
     */
    @Column(name = SUB_ACCOUNT_ID)
    private int subAccountId;

    /**
     * userId [*]
     */
    @Column(name = USER_ID)
    private int userId;


    /**
     * ord_sys_id [*]
     */
    @Column(name = "ORD_SYS_ID")
    private long ordSysId;

    /**
     * order_ref
     */
    @Column(name = "ORD_REF")
    private String ordRef;

    /**
     * order_msg_type
     */
    @Column(name = "ORD_MSG_TYPE")
    private int ordMsgType;

    /**
     * ord_offset
     */
    @Column(name = "ORD_OFFSET")
    private char ordOffset;

    /**
     * direction
     */
    @Column(name = "DIRECTION")
    private char direction;

    /**
     * limit_price double 19_4
     */
    @Column(name = "LIMIT_PRICE")
    private double limitPrice;

    /**
     * order_status char
     */
    @Column(name = "STATUS")
    private int status;

    /**
     * status_msg
     */
    @Column(name = "STATUS_MSG")
    private String statusMsg;


    /**
     * brokerSysID
     */
    @Column(name = "BROKER_SYS_ID")
    private Long brokerSysID;

    /**
     * volume int
     */
    @Column(name = "VOLUME")
    private int volume;

    /**
     * volume_filled int
     */
    @Column(name = "VOLUME_FILLED")
    private int volumeFilled;

    /**
     * volume_remained int
     */
    @Column(name = "VOLUME_REMAINED")
    private int volumeRemained;

    /**
     * price double 19_4
     */
    @Column(name = "PRICE")
    private double price;

    /**
     * trade_id varchar 21
     */
    @Column(name = "TRADE_ID")
    private String tradeId;

    /**
     * ord_rej_reason
     */
    @Column(name = "ORD_REJ_REASON")
    private int ordRejReason;

    // InsertTime datetime
    /**
     * insert_time
     */
    @Column(name = "INSERT_TIME")
    private int insertTime;

    /**
     * update_time
     */
    @Column(name = "UPDATE_TIME")
    private int updateTime;

    /**
     * cancel_time
     */
    @Column(name = "CANCEL_TIME")
    private int cancelTime;

    /**
     * remark
     */
    @Column(name = "REMARK")
    private String remark;

}
