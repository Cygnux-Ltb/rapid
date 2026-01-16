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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ACCOUNT_ID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 交易账户配置表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_account_param")
public final class AccountParamEntity {

    /**
     * UID
     */
    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * Adaptor ID
     */
    @Min(MIN_ACCOUNT_ID)
    @Column(name = ACCOUNT_ID)
    private int accountId;

    /**
     * Param Name
     */
    @NotBlank
    @Column(name = "PARAM_NAME")
    private String paramName;

    /**
     * Param Value
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
