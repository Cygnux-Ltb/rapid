package io.cygnuxltb.console.persistence.entity.market;

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
@Table(name = "tbm_bar")
public final class TbmBar {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    @Column(name = TRADING_DAY)
    private int tradingDay;

    @Column(name = "actual_date")
    private int actualDate;

    @Column(name = "time_point")
    private int timePoint;

    @Column(name = "open")
    private double open;

    @Column(name = "high")
    private double high;

    @Column(name = "low")
    private double low;

    @Column(name = "close")
    private double close;

    @Column(name = "volume")
    private double volume;

    @Column(name = "turnover")
    private long turnover;

}
