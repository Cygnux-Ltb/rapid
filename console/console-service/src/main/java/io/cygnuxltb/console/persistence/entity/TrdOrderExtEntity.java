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
 * 订单扩展信息表
 * <p>
 * OrderExt Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TRD_ORDER_EXT")
public final class TrdOrderExtEntity {

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
     * orderSysId [*]
     */
    @Column(name = "ORD_SYS_ID")
    private long ordSysId;


    /**
     * orderRef
     */
    @Column(name = "ORDER_REF")
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
     * brokerSysId
     */
    @Column(name = "BROKER_SYS_ID")
    private long brokerSysId;

    /**
     * volume
     */
    @Column(name = "VOLUME")
    private int volume;

    /**
     * price
     */
    @Column(name = "PRICE", columnDefinition = DECIMAL_19_4)
    private double price;


    /**
     * insertTime
     */
    @Column(name = "INSERT_TIME", columnDefinition = TIME)
    private Date insertTime;

    /**
     * updateTime
     */
    @Column(name = "UPDATE_TIME", columnDefinition = TIME)
    private Date updateTime;

    /**
     * cancelTime
     */
    @Column(name = "CANCEL_TIME", columnDefinition = TIME)
    private Date cancelTime;

    /**
     * frontId
     */
    @Column(name = "FRONT_ID")
    private int frontId;

    /**
     * sessionId
     */
    @Column(name = "SESSION_ID")
    private int sessionId;

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
