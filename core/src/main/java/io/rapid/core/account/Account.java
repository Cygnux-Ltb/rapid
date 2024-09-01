package io.rapid.core.account;

import io.cygnuxltb.console.beans.outbound.AccountRsp;
import io.mercury.common.lang.Asserter;
import io.mercury.common.state.EnableableComponent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import java.io.Serial;

/**
 * 实际账户, 对应一个实际的资金账户
 *
 * @author yellow013
 */
@Accessors(chain = true)
public final class Account extends EnableableComponent implements Comparable<Account> {

    /**
     * 账户ID
     */
    @Getter
    private final int accountId;

    /**
     * 投资者账户ID
     */
    @Getter
    private final String investorId;

    /**
     * 经纪商ID
     */
    @Getter
    private final String brokerId;

    /**
     * 经纪商名称
     */
    @Getter
    private final String brokerName;

    /**
     * 账户余额
     */
    @Getter
    @Setter
    private long balance;

    /**
     * 信用额度
     */
    @Getter
    @Setter
    private long credit;

    /**
     * 备注
     */
    @Getter
    @Setter
    private String remark = "";

    // 备用, 数组下标, 用于快速访问本账户对应的仓位信息集合
    // private int positionManagerIndex;

    /**
     * 全部子账户
     */
    // private final MutableSet<SubAccount> subAccounts = MutableSets.newUnifiedSet();
    public Account(@Nonnull AccountRsp accountRsp) {
        this(accountRsp.getAccountId(),
                accountRsp.getInvestorCode(),
                accountRsp.getBrokerCode(),
                accountRsp.getBrokerCode(),
                (long) accountRsp.getBalance(),
                (long) accountRsp.getCredit());
        this.remark = accountRsp.getRemark();
    }

    /**
     * @param accountId  int
     * @param investorId String
     * @param brokerId   String
     * @param brokerName String
     */
    public Account(int accountId, @Nonnull String investorId,
                   @Nonnull String brokerId, @Nonnull String brokerName) {
        this(accountId, brokerId, brokerName, investorId, 0L, 0L);
    }

    /**
     * @param accountId  int
     * @param investorId String
     * @param brokerId   String
     * @param brokerName String
     * @param balance    long
     * @param credit     long
     */
    public Account(int accountId, @Nonnull String investorId,
                   @Nonnull String brokerId, @Nonnull String brokerName,
                   long balance, long credit) {
        Asserter.greaterThan(accountId, 0, "accountId");
        Asserter.nonEmpty(investorId, "investorId");
        Asserter.nonEmpty(brokerId, "brokerId");
        Asserter.nonEmpty(brokerName, "brokerName");
        this.accountId = accountId;
        this.investorId = investorId;
        this.brokerId = brokerId;
        this.brokerName = brokerName;
        this.balance = balance;
        this.credit = credit;
        enable();
    }


    public final static class AccountException extends RuntimeException {

        /**
         *
         */
        @Serial
        private static final long serialVersionUID = -6421678546942382394L;

        public AccountException(String message) {
            super(message);
        }

    }

    @Override
    public String toString() {
        return "{\"accountId\" : " + accountId
                + ", \"brokerName\" : " + brokerName
                + ", \"investorId\" : " + investorId
                + ", \"balance\" : " + balance
                + ", \"credit\" : " + credit
                + ", \"remark\" : " + remark
                + ", \"isEnabled\" : " + isEnabled()
                + "}";
    }

    @Override
    public int compareTo(Account o) {
        return Integer.compare(this.accountId, o.accountId);
    }

    public static void main(String[] args) {
        Account account = new Account(1, "ZSQH", "ZSQH", "200500");
        System.out.println(account);
        System.out.println(account.toString().length());
    }

}
