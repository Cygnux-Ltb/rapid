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
 * K线表
 * Bar Entity of 1 minute
 * <p>
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TBL_MKD_BAR")
public final class TblMkdBar {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    @Column(name = TRADING_DAY)
    private int tradingDay;

    @Column(name = "ACTUAL_DATE")
    private int actualDate;

    @Column(name = "TIME_POINT")
    private int timePoint;

    @Column(name = "OPEN")
    private double open;

    @Column(name = "HIGH")
    private double high;

    @Column(name = "LOW")
    private double low;

    @Column(name = "CLOSE")
    private double close;

    @Column(name = "VOLUME")
    private double volume;

    @Column(name = "TURNOVER")
    private long turnover;

}
