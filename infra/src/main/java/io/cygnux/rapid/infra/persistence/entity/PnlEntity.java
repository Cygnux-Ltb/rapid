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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_EPOCH_DATE;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_STRATEGY_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.INSTRUMENT_CODE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.STRATEGY_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.TRADING_DAY;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 盈亏表
 * Pnl Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_pnl")
public final class PnlEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * StrategyID
     */
    @Min(MIN_STRATEGY_ID)
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * InstrumentCode
     */
    @NotBlank
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * TradingDay
     */
    @Min(MIN_EPOCH_DATE)
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * AvgBuyPrice
     */
    @Column(name = "AVG_BUY_PRICE")
    private double avgBuyPrice;

    /**
     * AvgSellPrice
     */
    @Column(name = "AVG_SELL_PRICE")
    private double avgSellPrice;

    /**
     * BuyQuantity
     */
    @Column(name = "BUY_QUANTITY")
    private int buyQuantity;

    /**
     * SellQuantity
     */
    @Column(name = "SELL_QUANTITY")
    private int sellQuantity;

    /**
     * TodayLong
     */
    @Column(name = "TODAY_LONG")
    private int todayLong;

    /**
     * TodayShort
     */
    @Column(name = "TODAY_SHORT")
    private int todayShort;

    /**
     * YesterdayLong
     */
    @Column(name = "YESTERDAY_LONG")
    private int yesterdayLong;

    /**
     * YesterdayShort
     */
    @Column(name = "YESTERDAY_SHORT")
    private int yesterdayShort;

    /**
     * NetPosition
     */
    @Column(name = "NET_POSITION")
    private int netPosition;

    /**
     * AggregatedFee
     */
    @Column(name = "AGGREGATED_FEE")
    private double aggregatedFee;

    /**
     * Approved
     */
    @Column(name = "APPROVED")
    private int approved;

    /**
     * Turnover
     */
    @Column(name = "TURNOVER")
    private int turnover;

}
