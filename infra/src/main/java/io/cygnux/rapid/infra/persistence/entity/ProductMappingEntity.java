package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_STRATEGY_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_SUB_ACCOUNT_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_USERID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.STRATEGY_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.SUB_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.USERID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 交易产品映射表
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
@Table(name = "t_product_mapping")
public final class ProductMappingEntity {

    /**
     * UID
     */
    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * 用户ID
     */
    @Min(MIN_USERID)
    @Column(name = USERID)
    private String userid;

    /**
     * 产品代码
     */
    @Column(name = "PRODUCT_CODE")
    private String productCode;

    /**
     * 子账户ID
     */
    @Min(MIN_SUB_ACCOUNT_ID)
    @Column(name = SUB_ACCOUNT_ID)
    private String subAccountId;

    /**
     * 策略ID
     */
    @Min(MIN_STRATEGY_ID)
    @Column(name = STRATEGY_ID)
    private int strategyId;

}
