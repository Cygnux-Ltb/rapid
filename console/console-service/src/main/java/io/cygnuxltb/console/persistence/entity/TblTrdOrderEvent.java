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

import static io.cygnuxltb.console.persistence.ColumnConst.ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.BROKER_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.ColumnConst.INVESTOR_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.SUB_ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.TRADING_DAY;
import static io.cygnuxltb.console.persistence.ColumnConst.USER_ID;

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
@Table(name = "TBL_TRD_ORDER_EVENT")
public final class TblTrdOrderEvent {

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
