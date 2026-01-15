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

import static io.cygnux.rapid.core.types.field.AdapterID.MIN_ADAPTER_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ADAPTER_ID;

/**
 * 交易适配器表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_adapter")
public final class AdapterEntity {

    /**
     * Adaptor ID
     */
    @Id
    @Min(MIN_ADAPTER_ID)
    @Column(name = ADAPTER_ID)
    private int adapterId;

    /**
     * Adaptor Type
     */
    @NotBlank
    @Column(name = "ADAPTER_TYPE")
    private String adapterType;

    /**
     * Adaptor Name
     */
    @NotBlank
    @Column(name = "ADAPTER_CODE")
    private String adapterCode;

    /**
     * Adaptor Name
     */
    @NotBlank
    @Column(name = "ADAPTER_NAME")
    private String adapterName;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";


    /**
     * Enabled
     */
    @Column(name = "ENABLED")
    private boolean enabled = true;

}
