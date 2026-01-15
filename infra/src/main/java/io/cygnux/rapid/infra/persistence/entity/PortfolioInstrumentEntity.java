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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_USERID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.INSTRUMENT_CODE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.USERID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 投资组合表
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_portfolio_instrument")
public final class PortfolioInstrumentEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * Userid [*]
     */
    @Min(MIN_USERID)
    @Column(name = USERID)
    private int userid;

    /**
     * PortfolioCode [*]
     */
    @NotBlank
    @Column(name = "PORTFOLIO_CODE")
    private String portfolioCode;

    /**
     * InstrumentCode [*]
     */
    @NotBlank
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

}
