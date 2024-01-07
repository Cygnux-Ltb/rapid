package io.rapid.core.account;

import io.rapid.core.account.Account.AccountException;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static io.mercury.common.collections.MutableMaps.newIntObjectHashMap;
import static io.mercury.common.collections.MutableMaps.newUnifiedMap;
import static io.mercury.common.lang.Asserter.requiredLength;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static org.eclipse.collections.impl.collector.Collectors2.toSet;

/**
 * 用于全局管理Account
 *
 * @author yellow013
 */
@NotThreadSafe
public final class AccountStorage implements Serializable {

    @Serial
    private static final long serialVersionUID = -6883109944757142986L;

    // logger
    private static final Logger log = getLogger(AccountStorage.class);

    // 存储Account信息, 一对一关系, 以accountId索引
    private static final MutableIntObjectMap<Account> Accounts = newIntObjectHashMap();

    // 存储Account信息, 一对一关系, 以investorId索引
    private static final MutableMap<String, Account> AccountsByInvestorId = newUnifiedMap();

    // 存储Account信息, 多对一关系, 以subAccountId索引
    private static final MutableIntObjectMap<Account> AccountsBySubAccountId = newIntObjectHashMap();

    // 存储SubAccount信息, 一对一关系, 以subAccountId索引
    private static final MutableIntObjectMap<SubAccount> SubAccounts = newIntObjectHashMap();

    // 初始化标识
    @Deprecated
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);

    private AccountStorage() {
    }

    @Deprecated
    public static void initialize(@Nonnull SubAccount... subAccounts) throws IllegalStateException {
        if (isInitialized.compareAndSet(false, true)) {
            try {
                requiredLength(subAccounts, 1, "subAccounts");
                // 建立subAccount相关索引
                Stream.of(subAccounts).collect(toSet()).each(AccountStorage::putSubAccount);
                // 建立account相关索引
                Stream.of(subAccounts).map(SubAccount::getAccount).collect(toSet())
                        .each(AccountStorage::putAccount);
            } catch (Exception e) {
                isInitialized.set(false);
                IllegalStateException se = new IllegalStateException("AccountKeeper initialization failed", e);
                log.error("AccountKeeper initialization failed", se);
                throw se;
            }
        } else {
            IllegalStateException e = new IllegalStateException(
                    "AccountKeeper Has been initialized, cannot be initialize again");
            log.error("AccountKeeper already initialized", e);
            throw e;
        }
    }

    private static void putAccount(Account account) {
        Accounts.put(account.getAccountId(), account);
        AccountsByInvestorId.put(account.getInvestorId(), account);
        log.info("Put account, accountId==[{}], investorId==[{}]", account.getAccountId(), account.getInvestorId());
    }

    static void putSubAccount(SubAccount subAccount) {
        SubAccounts.put(subAccount.getSubAccountId(), subAccount);
        AccountsBySubAccountId.put(subAccount.getSubAccountId(), subAccount.getAccount());
        log.info("Put subAccount, subAccountId==[{}], accountId==[{}]",
                subAccount.getSubAccountId(), subAccount.getAccount().getAccountId());
        putAccount(subAccount.getAccount());
    }

    @Deprecated
    public static boolean isInitialized() {
        return isInitialized.get();
    }

    @Nonnull
    public static Account getAccountByAccountId(int accountId) throws AccountException {
        var account = Accounts.get(accountId);
        if (account == null)
            throw new AccountException(STR."Account error in mapping : accountId[\{accountId}] no mapped instance");
        return account;
    }

    @Nonnull
    public static Account getAccountBySubAccountId(int subAccountId) throws AccountException {
        var account = AccountsBySubAccountId.get(subAccountId);
        if (account == null)
            throw new AccountException(
                    STR."Account error in mapping : subAccountId[\{subAccountId}] no mapped instance");
        return account;
    }

    @Nonnull
    public static Account getAccountByInvestorId(String investorId) throws AccountException {
        var account = AccountsByInvestorId.get(investorId);
        if (account == null)
            throw new AccountException(STR."Account error in mapping : investorId[\{investorId}] no mapped instance");
        return account;
    }

    @Nonnull
    public static SubAccount getSubAccount(int subAccountId) throws SubAccount.SubAccountException {
        var subAccount = SubAccounts.get(subAccountId);
        if (subAccount == null)
            throw new SubAccount.SubAccountException(
                    STR."SubAccount error in mapping : subAccountId[\{subAccountId}] no mapped instance");
        return subAccount;
    }

    public static void setAccountNotTradable(int accountId) {
        getAccountByAccountId(accountId).disable();
    }

    public static void setAccountTradable(int accountId) {
        getAccountByAccountId(accountId).enable();
    }

    public static boolean isAccountTradable(int accountId) {
        return getAccountByAccountId(accountId).isEnabled();
    }

    public static SubAccount setSubAccountNotTradable(int subAccountId) {
        var subAccount = getSubAccount(subAccountId);
        subAccount.disable();
        return subAccount;
    }

    public static SubAccount setSubAccountTradable(int subAccountId) {
        var subAccount = getSubAccount(subAccountId);
        subAccount.enable();
        return subAccount;
    }

    public static boolean isSubAccountTradable(int subAccountId) {
        return getSubAccount(subAccountId).isEnabled();
    }

    @Override
    public String toString() {
        return "";
    }

}
