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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ACCOUNT_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_SUB_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.SUB_ACCOUNT_ID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 交易子账户映射表
 * Account Entity
 *
 * @author yellow013
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_sub_account_mapping")
public final class SubAccountMappingEntity {

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
     * 账户ID
     */
    @Min(MIN_ACCOUNT_ID)
    @Column(name = ACCOUNT_ID)
    private int accountId;

    /**
     * 可用余额
     */
    @Column(name = "AVAILABLE_BALANCE")
    private double availableBalance;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
