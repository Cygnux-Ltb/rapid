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

import static io.cygnuxltb.console.persistence.CommonConst.Column.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.CommonConst.Column.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.TRADING_DAY;

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
@Table(name = "t_pnl_settlement")
public final class PnlSettlementEntity {

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
     * position
     */
    @Column(name = "position")
    private int position;

    /**
     * pnlTotal
     */
    @Column(name = "pnl_total")
    private double pnlTotal;

    /**
     * pnlNet
     */
    @Column(name = "pnl_net")
    private double pnlNet;

    /**
     * tradeCost
     */
    @Column(name = "trade_cost")
    private double tradeCost;

    /**
     * exposure
     */
    @Column(name = "exposure")
    private double exposure;

    /**
     * approved
     */
    @Column(name = "approved")
    private int approved;

}
