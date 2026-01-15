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

import static io.cygnux.rapid.infra.persistence.NonnullColumn.ADAPTER_ID;
import static io.cygnux.rapid.core.types.field.AdapterID.MIN_ADAPTER_ID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 交易适配器配置表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_adapter_param")
public final class AdapterParamEntity {

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
    @Min(MIN_ADAPTER_ID)
    @Column(name = ADAPTER_ID)
    private int adapterId;

    /**
     * [e.g. CTP, SIMNOW]
     */
    @NotBlank
    @Column(name = "ADAPTER_CODE")
    private String adapterCode;

    /**
     * [e.g. 联通1, 电信1]
     */
    @NotBlank
    @Column(name = "PARAM_GROUP")
    private String paramGroup;

    /**
     * [e.g. IP, PORT]
     */
    @NotBlank
    @Column(name = "PARAM_NAME")
    private String paramName;

    /**
     *
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
