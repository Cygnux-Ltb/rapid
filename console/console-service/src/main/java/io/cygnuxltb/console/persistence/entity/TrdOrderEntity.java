package io.cygnuxltb.console.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

import static io.cygnuxltb.console.persistence.ColumnConst.ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.BROKER_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.ColumnConst.INVESTOR_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.SUB_ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.TRADING_DAY;
import static io.cygnuxltb.console.persistence.ColumnConst.USER_ID;
import static io.mercury.persistence.rdb.ColumnDefinition.DECIMAL_19_4;
import static io.mercury.persistence.rdb.ColumnDefinition.TIME;
import static io.mercury.persistence.rdb.ColumnDefinition.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

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
@Table(name = "TRD_ORDER")
public final class TrdOrderEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
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
    @Column(name = "ORD_SYS_ID")
    private long ordSysId;

    /**
     * ordType
     */
    @Column(name = "ORD_TYPE")
    private String ordType;

    /**
     * orderRef
     */
    @Column(name = "ORD_REF")
    private String ordRef;

    /**
     * direction
     */
    @Column(name = "DIRECTION")
    private char direction;

    /**
     * side
     */
    @Column(name = "SIDE")
    private char side;

    /**
     * offerPrice
     */
    @Column(name = "OFFER_PRICE", columnDefinition = DECIMAL_19_4)
    private double offerPrice;

    /**
     * offerQty
     */
    @Column(name = "OFFER_QTY")
    private int offerQty;

    /**
     * insertTime
     */
    @Column(name = "INSERT_TIME", columnDefinition = TIME)
    private Date insertTime;

    /**
     * fee double 19_4
     */
    @Column(name = "FEE")
    private double fee;

    /**
     * adaptorType
     */
    @Column(name = "ADAPTOR_TYPE")
    private String adaptorType;

    /**
     * remark
     */
    @Column(name = "REMARK")
    private String remark;

}
