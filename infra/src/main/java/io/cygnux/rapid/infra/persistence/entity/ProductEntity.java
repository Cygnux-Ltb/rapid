package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 交易产品表
 * Product Entity
 *
 * @author yellow013
 * @note [USERID] <--> [PRODUCT_CODE] <--> [SUB_ACCOUNT_ID] <--> [STRATEGY_ID]
 * 以上字段为多对多的松散关系
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_product")
public final class ProductEntity {

    /**
     *
     */
    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private int productId;

    /**
     *
     */
    @NotBlank
    @Column(name = "PRODUCT_CODE")
    private String productCode;

    /**
     *
     */
    @NotBlank
    @Column(name = "PRODUCT_NAME")
    private String productName;

    /**
     *
     */
    @Column(name = "PRODUCT_INFO")
    private String productInfo;

    /**
     *
     */
    @Column(name = "REMARK")
    private String remark = "";

}
