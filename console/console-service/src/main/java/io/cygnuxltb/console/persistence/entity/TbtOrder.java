package io.cygnuxltb.console.persistence.entity.trade;

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

import java.util.Date;

import static io.cygnuxltb.console.persistence.CommonConst.Column.ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.BROKER_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.CommonConst.Column.INVESTOR_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.SUB_ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.TRADING_DAY;
import static io.cygnuxltb.console.persistence.CommonConst.Column.USER_ID;
import static io.mercury.persistence.rdb.ColumnDefinition.TIME;

/**
 * 订单基本信息表
 * Order Entity 基本信息
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "tbt_order")
public final class TbtOrder {

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
     * ordSysId [*]
     */
    @Column(name = "ord_sys_id")
    private long ordSysId;

    /**
     * ordType
     */
    @Column(name = "ord_type")
    private String ordType;

    /**
     * orderRef
     */
    @Column(name = "ord_ref")
    private String ordRef;

    /**
     * direction
     */
    @Column(name = "direction")
    private char direction;

    /**
     * side
     */
    @Column(name = "side")
    private char side;

    /**
     * offerPrice
     */
    @Column(name = "offer_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double offerPrice;

    /**
     * offerQty
     */
    @Column(name = "offer_qty")
    private int offerQty;

    /**
     * insertTime
     */
    @Column(name = "insert_time", columnDefinition = TIME)
    private Date insertTime;

    /**
     * updateTime
     */
    @Column(name = "update_time", columnDefinition = TIME)
    private Date updateTime;

    /**
     * cancelTime
     */
    @Column(name = "cancel_time", columnDefinition = TIME)
    private Date cancelTime;

    /**
     * frontId
     */
    @Column(name = "front_id")
    private int frontId;

    /**
     * sessionId
     */
    @Column(name = "session_id")
    private int sessionId;

    /**
     * fee double 19_4
     */
    @Column(name = "fee")
    private double fee;

    /**
     * adaptorType
     */
    @Column(name = "adaptor_type")
    private String adaptorType;

    /**
     * remark
     */
    @Column(name = "remark")
    private String remark;

}
