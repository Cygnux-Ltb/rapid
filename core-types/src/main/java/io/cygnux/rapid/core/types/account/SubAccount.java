package io.cygnux.rapid.core.types.account;

import io.cygnux.rapid.core.types.field.AccountID;
import io.cygnux.rapid.core.types.field.SubAccountID;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.AvailableInstance;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.stream.Stream;

import static io.mercury.common.util.ArrayUtil.isNullOrEmpty;
import static io.mercury.common.util.StringSupport.nonEmpty;

/**
 * 系統內使用的虚拟账户
 *
 * @author yellow013
 */
@Accessors(chain = true)
public final class SubAccount extends AvailableInstance implements Comparable<SubAccount> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SubAccount.class);

    /**
     * 处理外部订单使用的子账户
     */
    public static final SubAccount EXTERNAL_ORDER_SUB_ACCOUNT = new SubAccount();

    /**
     * 子账户ID
     */
    @Getter
    private final int subAccountId;

    /**
     * 子账户名称
     */
    @Getter
    private final String subAccountName;

    /**
     * 所属账户[一对多映射关系]
     */
    @Getter
    private final SubAccountMapping subAccountMapping;

    /**
     * 账户余额
     */
    @Getter
    @Setter
    private long balance;

    /**
     * 当日账户余额
     */
    @Getter
    @Setter
    private long dailyBalance;

    /**
     * 信用额度
     */
    @Getter
    @Setter
    private long credit;

    // INNER USE
    private SubAccount() {
        this.subAccountId = 0;
        this.subAccountName = "EXTERNAL_ORDER_SUBACCOUNT";
        this.subAccountMapping = null;
    }

    public SubAccount(SubAccountID subAccountId, String subAccountName, @Nullable Account... accounts) {
        this(subAccountId, subAccountName,
                isNullOrEmpty(accounts) ? 0 :
                        Stream.of(accounts).mapToLong(Account::getBalance).sum(),
                0, accounts);
    }

    public SubAccount(SubAccountID subAccountId, String subAccountName, long balance, long dailyBalance,
                      @Nullable Account... accounts) {
        this.subAccountId = subAccountId.value();
        this.subAccountMapping = new SubAccountMapping(subAccountId.value());
        if (!isNullOrEmpty(accounts))
            bindAccount(accounts);
        this.balance = balance;
        this.dailyBalance = dailyBalance;
        if (nonEmpty(subAccountName))
            this.subAccountName = subAccountName;
        else
            this.subAccountName = "SubAccount[" + subAccountId + "]=>SubAccountMapping[" + this.subAccountMapping + "]";
        enable();
    }

    public SubAccount bindAccount(Account... accounts) {
        for (Account account : accounts) {
            subAccountMapping.saveAccount(account);
            log.info("Bind SubAccount[{}] to Account[{}]", subAccountId, account.getAccountId());
        }
        return this;
    }

    @Override
    public String toString() {
        return "{\"subAccountId\" : " + subAccountId
               + ", \"subAccountName\" : " + subAccountName
               + ", \"subAccountMapping\" : " + subAccountMapping
               + ", \"balance\" : " + balance
               + ", \"dailyBalance\" : " + dailyBalance
               + ", \"credit\" : " + credit
               + ", \"isEnabled\" : " + isEnabled()
               + "}";
    }

    @Override
    public int compareTo(SubAccount o) {
        return Integer.compare(this.subAccountId, o.subAccountId);
    }

    public static void main(String[] args) {
        SubAccount subAccount = new SubAccount(SubAccountID.of(10), "TEST_SUB",
                new Account(AccountID.of(1), "HYQH", "200500", 100000, 0));
        System.out.println(subAccount);
        System.out.println(subAccount.toString().length());
    }

}
