package io.rapid.core.account;

import io.cygnuxltb.console.beans.response.AccountRsp;
import io.mercury.common.state.EnableableComponent;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;

import static io.mercury.common.lang.Asserter.greaterThan;
import static io.mercury.common.lang.Asserter.nonEmpty;
import static java.lang.System.currentTimeMillis;

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
     * 经纪商Code
     */
    @Getter
    private final String brokerCode;

    /**
     * 投资者账户CODE (*在内部系统中使用唯一代码)
     */
    @Getter
    private final String investorCode;

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
                accountRsp.getBrokerCode(),
                accountRsp.getInvestorCode(),
                (long) accountRsp.getBalance(),
                (long) accountRsp.getCredit());
        this.remark = accountRsp.getRemark();
    }

    /**
     * @param accountId    int
     * @param brokerCode   String
     * @param investorCode String
     */
    public Account(int accountId, @Nonnull String brokerCode, @Nonnull String investorCode) {
        this(accountId, brokerCode, investorCode, 0L, 0L);
    }

    /**
     * @param accountId    int
     * @param brokerCode   String
     * @param investorCode String
     * @param balance      long
     * @param credit       long
     */
    public Account(int accountId, @Nonnull String brokerCode, @Nonnull String investorCode,
                   long balance, long credit) {
        this.accountId = greaterThan(accountId, 0, "accountId");
        this.brokerCode = nonEmpty(brokerCode, "brokerCode");
        this.investorCode = nonEmpty(investorCode, "investorId");
        this.balance = balance;
        this.credit = credit;
        enable();
    }

    /**
     * @return QueryOrder
     */
    public QueryOrder newQueryOrder() {
        return new QueryOrder()
                .setGenerateTime(currentTimeMillis())
                .setAccountId(accountId)
                .setBrokerId(brokerCode);
    }

    /**
     * @return QueryPosition
     */
    public QueryPosition newQueryPosition() {
        return new QueryPosition()
                .setGenerateTime(currentTimeMillis())
                .setAccountId(accountId)
                .setBrokerId(brokerCode);
    }

    /**
     * @return QueryBalance
     */
    public QueryBalance newQueryBalance() {
        return new QueryBalance()
                .setGenerateTime(currentTimeMillis())
                .setAccountId(accountId)
                .setBrokerId(brokerCode);
    }

    @Override
    public String toString() {
        return "{\"accountId\" : " + accountId
                + ", \"brokerCode\" : " + brokerCode
                + ", \"investorCode\" : " + investorCode
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

    /**
     * 此账户对应的Topic<br>
     * brokerCode + "/" + investorCode
     *
     * @return String
     */
    public String getTopic() {
        return brokerCode + "/" + investorCode;
    }

    /**
     * 此账户对应的行情Topic<br>
     * Account::getTopic + "/md"
     *
     * @return String
     */
    public String getTopicByMd() {
        return getTopic() + "/md";
    }


    /**
     * 此账户对应的交易Topic<br>
     * Account::getTopic + "/td"
     *
     * @return String
     */
    public String getTopicByTd() {
        return getTopic() + "/td";
    }

    public static void main(String[] args) {
        var account = new Account(1, "ZSQH", "200500");
        System.out.println(account);
        System.out.println(account.toString().length());
        System.out.println(account.getTopic());
        System.out.println(account.getTopicByMd());
        System.out.println(account.getTopicByTd());
    }

}
