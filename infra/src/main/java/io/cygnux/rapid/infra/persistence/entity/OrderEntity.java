package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ACCOUNT_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_EPOCH_DATE;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ORD_SYS_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_STRATEGY_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_SUB_ACCOUNT_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_USERID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.BROKER_CODE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.INSTRUMENT_CODE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.INVESTOR_CODE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ORD_SYS_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.STRATEGY_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.SUB_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.TRADING_DAY;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.USERID;
import static io.mercury.common.constant.DbColumnConst.DECIMAL_19_4;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 订单基本信息表
 * <p>
 * Order Entity 基本信息
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_order")
public final class OrderEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * TradingDay [*]
     */
    @Min(MIN_EPOCH_DATE)
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * StrategyId [*]
     */
    @Min(MIN_STRATEGY_ID)
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * InstrumentCode [*]
     */
    @NotBlank
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * InvestorId [*]
     */
    @NotBlank
    @Column(name = INVESTOR_CODE)
    private String investorCode;

    /**
     * BrokerId [*]
     */
    @NotBlank
    @Column(name = BROKER_CODE)
    private String brokerCode;

    /**
     * AccountId [*]
     */
    @Min(MIN_ACCOUNT_ID)
    @Column(name = ACCOUNT_ID)
    private int accountId;

    /**
     * SubAccountId [*]
     */
    @Min(MIN_SUB_ACCOUNT_ID)
    @Column(name = SUB_ACCOUNT_ID)
    private int subAccountId;

    /**
     * Userid [*]
     */
    @Min(MIN_USERID)
    @Column(name = USERID)
    private int userid;

    /**
     * OrdSysId [*]
     */
    @Min(MIN_ORD_SYS_ID)
    @Column(name = ORD_SYS_ID)
    private long ordSysId;

    /**
     * OrdType
     */
    @Column(name = "ORD_TYPE")
    private String ordType;

    /**
     * OrderRef
     */
    @NotBlank
    @Column(name = "ORD_REF")
    private String ordRef;

    /**
     * Direction
     */
    @Column(name = "DIRECTION")
    private char direction;

    /**
     * Side
     */
    @Column(name = "SIDE")
    private char side;

    /**
     * OfferPrice
     */
    @Column(name = "OFFER_PRICE", columnDefinition = DECIMAL_19_4)
    private double offerPrice;

    /**
     * OfferQty
     */
    @Column(name = "OFFER_QTY")
    private int offerQty;

    /**
     * InsertTime
     */
    @Min(1)
    @Column(name = "INSERT_TIME")
    private int insertTime;

    /**
     * Fee double 19_4
     */
    @Column(name = "FEE")
    private double fee = 0.0d;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
