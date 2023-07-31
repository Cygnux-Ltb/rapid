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

/**
 * 交易标的表
 * Instrument Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TBL_MKD_INSTRUMENT")
public final class TblMkdInstrument {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode [*]
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * instrumentType [*]
     */
    @Column(name = "INSTRUMENT_TYPE")
    private String instrumentType;

    /**
     * exchangeCode [*]
     */
    @Column(name = "EXCHANGE_CODE")
    private String exchangeCode;

    /**
     * fee
     */
    @Column(name = "FEE")
    private double fee;

    /**
     * tradable
     */
    @Column(name = "TRADABLE")
    private boolean tradable;

}
