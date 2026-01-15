package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_SUB_ACCOUNT_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_USERID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.SUB_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.USERID;

/**
 * 交易子账户表
 * Account Entity
 *
 * @author yellow013
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_sub_account")
public final class SubAccountEntity {

    /**
     * 子账户ID
     */
    @Id
    @Min(MIN_SUB_ACCOUNT_ID)
    @Column(name = SUB_ACCOUNT_ID)
    private int subAccountId;

    /**
     * 子账户名称
     */
    @Column(name = "SUB_ACCOUNT_NAME")
    private String subAccountName;

    /**
     * 用户ID
     */
    @Min(MIN_USERID)
    @Column(name = USERID)
    private int userid;

    /**
     * 总可用余额
     */
    @Column(name = "TOTAL_AVAILABLE")
    private double totalAvailable;

    /**
     * 每日可用余额
     */
    @Column(name = "DAILY_AVAILABLE")
    private double dailyAvailable;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
