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
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_EPOCH_DATE;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.TRADING_DAY;
import static io.mercury.common.constant.DbColumnConst.UID;
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
@Table(name = "t_account_settlement")
public final class AccountSettlementEntity {

    /**
     * UID
     */
    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * AccountID
     */
    @Min(MIN_ACCOUNT_ID)
    @Column(name = ACCOUNT_ID)
    private int accountId;

    /**
     * TradingDay
     */
    @Min(MIN_EPOCH_DATE)
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * Balance
     */
    @Column(name = "BALANCE")
    private double balance;

    /**
     * Credit
     */
    @Column(name = "CREDIT")
    private double credit;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
