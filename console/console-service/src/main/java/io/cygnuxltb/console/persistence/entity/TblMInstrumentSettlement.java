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
import static io.cygnuxltb.console.persistence.CommonConst.Column.TRADING_DAY;

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
@Table(name = "TBL_M_INSTRUMENT_SETTLEMENT")
public final class TblMInstrumentSettlement {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay [*]
     */
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * lastPrice
     */
    @Column(name = "LAST_PRICE")
    private double lastPrice;

    /**
     * openPrice
     */
    @Column(name = "OPEN_PRICE")
    private double openPrice;

    /**
     * openPrice
     */
    @Column(name = "HIGHEST_PRICE")
    private double highestPrice;

    /**
     * lowestPrice
     */
    @Column(name = "LOWEST_PRICE")
    private double lowestPrice;

    /**
     * settlementPrice
     */
    @Column(name = "SETTLEMENT_PRICE")
    private double settlementPrice;

}
