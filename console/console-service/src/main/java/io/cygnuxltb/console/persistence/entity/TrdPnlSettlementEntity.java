package io.cygnuxltb.console.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnuxltb.console.persistence.ColumnConst.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.ColumnConst.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.TRADING_DAY;
import static io.mercury.persistence.rdb.ColumnDefinition.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 盈亏结算表
 * PnlSettlement Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TRD_PNL_SETTLEMENT")
public final class TrdPnlSettlementEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
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
     * position
     */
    @Column(name = "POSITION")
    private int position;

    /**
     * pnlTotal
     */
    @Column(name = "PNL_TOTAL")
    private double pnlTotal;

    /**
     * pnlNet
     */
    @Column(name = "PNL_NET")
    private double pnlNet;

    /**
     * tradeCost
     */
    @Column(name = "TRADE_COST")
    private double tradeCost;

    /**
     * exposure
     */
    @Column(name = "EXPOSURE")
    private double exposure;

    /**
     * approved
     */
    @Column(name = "APPROVED")
    private int approved;

}
