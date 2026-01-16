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
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * StrategyID
     */
    @Min(MIN_STRATEGY_ID)
    @Column(name = STRATEGY_ID, nullable = false)
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
     * Position
     */
    @Column(name = "POSITION")
    private int position;

    /**
     * PnlTotal
     */
    @Column(name = "PNL_TOTAL")
    private double pnlTotal;

    /**
     * PnlNet
     */
    @Column(name = "PNL_NET")
    private double pnlNet;

    /**
     * TradeCost
     */
    @Column(name = "TRADE_COST")
    private double tradeCost;

    /**
     * Exposure
     */
    @Column(name = "EXPOSURE")
    private double exposure;

    /**
     * Approved
     */
    @Column(name = "APPROVED")
    private int approved;

}
