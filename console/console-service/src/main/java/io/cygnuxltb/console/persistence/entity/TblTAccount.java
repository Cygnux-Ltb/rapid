package io.cygnuxltb.console.persistence.entity;

import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnuxltb.console.persistence.CommonConst.Column.ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.BROKER_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.INVESTOR_ID;
import static io.cygnuxltb.console.persistence.CommonConst.Column.SUB_ACCOUNT_ID;

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
@Table(name = "TBL_T_ACCOUNT")
public final class TblTAccount {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = ACCOUNT_ID)
    private int accountId;

    @Column(name = "ACCOUNT_NAME")
    public String accountName;

    @Column(name = SUB_ACCOUNT_ID)
    private int subAccountId;

    @Column(name = BROKER_ID)
    private String brokerId;

    @Column(name = INVESTOR_ID)
    private String investorId;

    @Column(name = "ADAPTOR_ID")
    private String adaptorId;

}
