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
@Table(name = "t_bar")
public final class BarEntity {

    /**
     * UID
     */
    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     *
     */
    @NotBlank
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     *
     */
    @Min(MIN_EPOCH_DATE)
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     *
     */
    @Min(MIN_EPOCH_DATE)
    @Column(name = "ACTUAL_DATE")
    private int actualDate;

    /**
     *
     */
    @Min(0)
    @Column(name = "ACTUAL_TIME")
    private int actualTime;

    /**
     *
     */
    @Column(name = "OPEN")
    private double open;

    /**
     *
     */
    @Column(name = "HIGH")
    private double high;

    /**
     *
     */
    @Column(name = "LOW")
    private double low;

    /**
     *
     */
    @Column(name = "CLOSE")
    private double close;

    /**
     *
     */
    @Column(name = "VWAP")
    private double vwap;

    /**
     *
     */
    @Column(name = "VOLUME")
    private int volume;

    /**
     *
     */
    @Column(name = "TURNOVER")
    private long turnover;

}
