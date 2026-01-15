package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.infra.persistence.NonnullColumn.INSTRUMENT_CODE;

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
@Table(name = "t_instrument",
        uniqueConstraints = {@UniqueConstraint(name = "INDEX_1", columnNames = INSTRUMENT_CODE)})
public final class InstrumentEntity {

    /**
     * InstrumentCode [*]
     */
    @Id
    @NotBlank
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * InstrumentType [*]
     */
    @NotBlank
    @Column(name = "INSTRUMENT_TYPE")
    private String instrumentType;

    /**
     * ExchangeCode [*]
     */
    @NotBlank
    @Column(name = "EXCHANGE_CODE")
    private String exchangeCode;

    /**
     * Fee
     */
    @Column(name = "FEE")
    private double fee = 0.00005d;

    /**
     * Tradable
     */
    @Column(name = "TRADABLE")
    private boolean tradable = false;

}
