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

import static io.cygnuxltb.console.persistence.CommonConst.Column.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.CommonConst.Column.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.TRADING_DAY;

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
@Table(name = "tbt_pnl")
public final class TbtPnl {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategyId
     */
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * instrumentCode
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay
     */
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * avgBuyPrice
     */
    @Column(name = "avg_buy_price")
    private double avgBuyPrice;

    /**
     * avgSellPrice
     */
    @Column(name = "avg_sell_price")
    private double avgSellPrice;

    /**
     * buyQuantity
     */
    @Column(name = "buy_quantity")
    private int buyQuantity;

    /**
     * sellQuantity
     */
    @Column(name = "sell_quantity")
    private int sellQuantity;

    /**
     * todayLong
     */
    @Column(name = "today_long")
    private int todayLong;

    /**
     * todayShort
     */
    @Column(name = "today_short")
    private int todayShort;

    /**
     * yesterdayLong
     */
    @Column(name = "yesterday_long")
    private int yesterdayLong;

    /**
     * yesterdayShort
     */
    @Column(name = "yesterday_short")
    private int yesterdayShort;

    /**
     * netPosition
     */
    @Column(name = "net_position")
    private int netPosition;

    /**
     * aggregatedFee
     */
    @Column(name = "aggregated_fee")
    private double aggregatedFee;

    /**
     * approved
     */
    @Column(name = "approved")
    private int approved;

    /**
     * turnover
     */
    @Column(name = "turnover")
    private int turnover;

}
