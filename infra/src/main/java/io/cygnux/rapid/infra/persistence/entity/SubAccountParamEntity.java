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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_SUB_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.SUB_ACCOUNT_ID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 配置信息表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_sub_account_param")
public final class SubAccountParamEntity {

    /**
     * UID
     */
    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * 子账户ID
     */
    @Min(MIN_SUB_ACCOUNT_ID)
    @Column(name = SUB_ACCOUNT_ID)
    private int subAccountId;

    /**
     * 参数名
     */
    @NotBlank
    @Column(name = "PARAM_NAME")
    private String paramName;

    /**
     * 参数类型
     */
    @NotBlank
    @Column(name = "PARAM_TYPE")
    private String paramType;

    /**
     * 参数值
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
