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
@Table(name = "MKD_BAR")
public final class MkdBar {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    @Column(name = TRADING_DAY)
    private int tradingDay;

    @Column(name = "ACTUAL_DATE")
    private int actualDate;

    @Column(name = "ACTUAL_TIME")
    private int actualTime;

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
