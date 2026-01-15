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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ALGO_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_STRATEGY_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_USERID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ALGO_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.STRATEGY_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.USERID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 策略参数表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_strategy_param")
public final class StrategyParamEntity {

    /**
     * UID
     */
    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * Userid
     */
    @Min(MIN_USERID)
    @Column(name = USERID)
    private int userid;

    /**
     * Strategy ID
     */
    @Min(MIN_STRATEGY_ID)
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * Algo ID
     */
    @Min(MIN_ALGO_ID)
    @Column(name = ALGO_ID)
    private int algoId;

    /**
     * ParamName
     */
    @NotBlank
    @Column(name = "PARAM_NAME")
    private String paramName;

    /**
     * ParamType
     */
    @NotBlank
    @Column(name = "PARAM_TYPE")
    private String paramType;

    /**
     * ParamValue
     */
    @NotBlank
    @Column(name = "PARAM_VALUE")
    private String paramValue;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
