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
@Table(name = "tbm_instrument")
public final class TbmInstrument {

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
    @Column(name = "instrument_type")
    private String instrumentType;

    /**
     * exchangeCode [*]
     */
    @Column(name = "exchange_code")
    private String exchangeCode;

    /**
     * fee
     */
    @Column(name = "fee")
    private double fee;

    /**
     * tradable
     */
    @Column(name = "tradable")
    private boolean tradable;

}
