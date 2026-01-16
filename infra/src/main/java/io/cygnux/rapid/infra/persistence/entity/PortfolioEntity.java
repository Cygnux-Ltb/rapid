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
@Table(name = "t_portfolio")
public final class PortfolioEntity {

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
     * PortfolioName
     */
    @Column(name = "PORTFOLIO_NAME")
    private String portfolioName;

}
