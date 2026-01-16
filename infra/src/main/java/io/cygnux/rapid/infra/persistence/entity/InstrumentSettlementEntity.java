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
import static io.cygnux.rapid.infra.persistence.NonnullColumn.INSTRUMENT_CODE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.TRADING_DAY;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 交易标的结算表
 * InstrumentSettlement Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_instrument_settlement")
public final class InstrumentSettlementEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * InstrumentCode [*]
     */
    @NotBlank
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * TradingDay [*]
     */
    @Min(MIN_EPOCH_DATE)
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * ClosePrice
     */
    @Column(name = "CLOSE_PRICE")
    private double closePrice;

    /**
     * OpenPrice
     */
    @Column(name = "OPEN_PRICE")
    private double openPrice;

    /**
     * HighestPrice
     */
    @Column(name = "HIGHEST_PRICE")
    private double highestPrice;

    /**
     * LowestPrice
     */
    @Column(name = "LOWEST_PRICE")
    private double lowestPrice;

    /**
     * SettlementPrice
     */
    @Column(name = "SETTLEMENT_PRICE")
    private double settlementPrice;

}
