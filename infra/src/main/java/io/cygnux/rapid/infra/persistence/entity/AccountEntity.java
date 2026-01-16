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

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.BROKER_CODE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.INVESTOR_CODE;

/**
 * 交易账户表
 * Account Entity
 *
 * @author yellow013
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_account")
public final class AccountEntity {

    /**
     * 账户ID
     */
    @Id
    @Min(MIN_ACCOUNT_ID)
    @Column(name = ACCOUNT_ID)
    private int accountId;

    /**
     * 账户名称
     */
    @NotBlank
    @Column(name = "ACCOUNT_NAME")
    public String accountName;

    /**
     * 经纪商代码
     */
    @Column(name = BROKER_CODE)
    private String brokerCode;

    /**
     * 投资者代码
     */
    @Column(name = INVESTOR_CODE)
    private String investorCode;

    /**
     * 账户余额
     */
    @Column(name = "BALANCE")
    private double balance;

    /**
     * 账户信用
     */
    @Column(name = "CREDIT")
    private double credit;

    /**
     * 交易适配器ID
     */
    @Column(name = "ADAPTOR_ID")
    private int adaptorId;

    /**
     * 交易适配器ID
     */
    @Column(name = "ADAPTOR_CODE")
    private String adaptorCode;

    /**
     * 交易适配器ID
     */
    @Column(name = "ADAPTOR_PARAM_GROUP")
    private String adaptorParamGroup;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
