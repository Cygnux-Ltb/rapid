package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_STRATEGY_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.STRATEGY_ID;

/**
 * 策略表
 * Strategy Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_strategy")
public final class StrategyEntity {

    /**
     * StrategyID
     */
    @Id
    @Min(MIN_STRATEGY_ID)
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * StrategyName
     */
    @NotBlank
    @Column(name = "STRATEGY_NAME")
    private String strategyName;

    /**
     * StrategyType
     */
    @NotBlank
    @Column(name = "STRATEGY_TYPE")
    private String strategyType;

    /**
     * Enabled
     */
    @Column(name = "ENABLED")
    private boolean enabled = true;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
