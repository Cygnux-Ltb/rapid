package io.rapid.engine.service;

import io.cygnuxltb.console.beans.outbound.SubAccountMappingRsp;
import io.cygnuxltb.console.beans.outbound.SubAccountRsp;
import io.cygnuxltb.console.component.ApplicationConfiguration;
import io.cygnuxltb.console.service.AccountService;
import io.mercury.common.collections.CollectionUtil;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.account.Account;
import io.rapid.core.account.AccountException;
import io.rapid.core.account.AccountManager;
import io.rapid.core.account.SubAccount;
import io.rapid.core.account.SubAccountException;
import io.rapid.core.account.SubAccountMapping;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 用于全局管理 [Account]
 *
 * @author yellow013
 */
@NotThreadSafe
@Service
public final class AccountManagerService implements Serializable, AccountManager {

    @Serial
    private static final long serialVersionUID = -6883109944757142986L;

    private static final Logger log = Log4j2LoggerFactory.getLogger(AccountManagerService.class);

    /**
     * 存储[SubAccount]信息, 一对一关系, 以subAccountId索引
     */
    private final MutableIntObjectMap<SubAccount> subAccountMap = MutableMaps.newIntObjectMap();

    /**
     * 存储[SubAccountMapping]信息, 一对一关系, 以subAccountId索引
     */
    private final MutableIntObjectMap<SubAccountMapping> subAccountMappingMap = MutableMaps.newIntObjectMap();

    /**
     * 存储[Account]信息, 一对一关系, 以[accountId]索引
     */
    private final MutableIntObjectMap<Account> accountMap = MutableMaps.newIntObjectMap();

    /**
     * 存储[Account]信息, 一对一关系, 以[investorCode]索引
     */
    private final MutableMap<String, Account> accountMapByInvestorCode = MutableMaps.newUnifiedMap();

    /**
     * 初始化标识
     */
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);

    @Resource
    private ApplicationConfiguration configuration;

    @Resource
    private AccountService service;

    private AccountManagerService() {
    }

    @PostConstruct
    public void initialize() throws IllegalStateException {
        if (configuration.isInitializeData()
                && isInitialized.compareAndSet(false, true)) {
            try {
                for (SubAccountRsp subAccountRsp : service.getAllSubAccount()) {
                    SubAccount subAccount = new SubAccount(subAccountRsp);
                    SubAccountMappingRsp mappingRsp = service
                            .getSubAccountMapping(subAccountRsp.getSubAccountId());
                    List<Account> accountList = mappingRsp
                            .getSubAccountMappings()
                            .stream()
                            .map(obj -> Optional
                                    .ofNullable(accountMap.get(obj.getAccountId()))
                                    .orElse(saveAccount(new Account(service.getAccount(obj.getAccountId())))))
                            .toList();
                    mapping(subAccount, CollectionUtil.toArray(accountList, Account[]::new));
                }
            } catch (Exception e) {
                isInitialized.set(false);
                var ise = new IllegalStateException("AccountStorage initialization failed", e);
                log.error("AccountStorage initialization failed", ise);
                throw ise;
            }
        }
    }

    @Override
    public void mapping(SubAccount subAccount, Account... accounts) {
        subAccountMap.put(subAccount.getSubAccountId(), subAccount);
        log.info("Saved [SubAccount] -> subAccountId==[{}]", subAccount.getSubAccountId());
        subAccount.bindAccount(accounts);
        subAccountMappingMap.put(subAccount.getSubAccountId(), subAccount.getSubAccountMapping());
    }

    private Account saveAccount(Account account) {
        accountMap.put(account.getAccountId(), account);
        accountMapByInvestorCode.put(account.getInvestorCode(), account);
        log.info("Saved [Account] -> accountId==[{}], investorCode==[{}]",
                account.getAccountId(), account.getInvestorCode());
        return account;
    }

    public boolean isInitialized() {
        return isInitialized.get();
    }

    @Override
    public Account getAccount(int accountId) throws AccountException {
        var account = accountMap.get(accountId);
        if (account == null)
            throw new AccountException("Account error in mapping : accountId[" + accountId + "] no mapped instance");
        return account;
    }

    @Override
    public SubAccountMapping getSubAccountMapping(int subAccountId) throws AccountException {
        var subAccountMapping = subAccountMappingMap.get(subAccountId);
        if (subAccountMapping == null)
            throw new AccountException("Account error in mapping : subAccountId[" + subAccountId + "] no mapped instance");
        return subAccountMapping;
    }

    @Override
    public Account getAccount(String investorId) throws AccountException {
        var account = accountMapByInvestorCode.get(investorId);
        if (account == null)
            throw new AccountException("Account error in mapping : investorId[" + investorId + "] no mapped instance");
        return account;
    }

    @Override
    public SubAccount getSubAccount(int subAccountId) throws SubAccountException {
        var subAccount = subAccountMap.get(subAccountId);
        if (subAccount == null)
            throw new SubAccountException(
                    "SubAccount error in mapping : subAccountId[" + subAccountId + "] no mapped instance");
        return subAccount;
    }

    @Override
    public String toString() {
        return "";
    }

}
