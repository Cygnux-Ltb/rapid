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
import static io.cygnuxltb.console.persistence.ColumnConst.TRADING_DAY;
import static io.mercury.persistence.rdb.ColumnDefinition.UID;
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
@Table(name = "TBL_MKD_INSTRUMENT_SETTLEMENT")
public final class TblMkdInstrumentSettlement {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
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
