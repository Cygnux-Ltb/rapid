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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ALGO_ID;

/**
 * 算法结构表
 * Account Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_algo")
public final class AlgoEntity {

    /**
     * 算法ID
     */
    @Id
    @Min(MIN_ALGO_ID)
    @Column(name = "ALGO_ID")
    private int algoId;

    /**
     * 算法名
     */
    @NotBlank
    @Column(name = "ALGO_NAME")
    private String algoName;

    /**
     * 算法类型
     */
    @NotBlank
    @Column(name = "ALGO_TYPE")
    private String algoType;

    /**
     * 算法相关信息
     */
    @Column(name = "ALGO_INFO")
    private String algoInfo;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark = "";

}

