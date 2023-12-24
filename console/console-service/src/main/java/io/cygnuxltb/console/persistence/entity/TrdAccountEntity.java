package io.cygnuxltb.console.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnuxltb.console.persistence.ColumnConst.ACCOUNT_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.BROKER_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.INVESTOR_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.SUB_ACCOUNT_ID;
import static io.mercury.persistence.rdb.ColumnDefinition.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

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
@Table(name = "TRD_ACCOUNT")
public final class TrdAccountEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
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
