package io.rapid.core.account;

public interface AccountManager {

    /**
     * @param subAccount SubAccount
     * @param accounts   Account[]
     */
    void mapping(SubAccount subAccount, Account... accounts);

    /**
     * @param subAccountId int
     * @return SubAccount
     * @throws SubAccount.SubAccountException e
     */
    SubAccount getSubAccount(int subAccountId) throws SubAccount.SubAccountException;

    /**
     * @param subAccountId int
     * @return AccountGroup
     * @throws Account.AccountException e
     */
    SubAccountMapping getSubAccountMapping(int subAccountId);

    /**
     * @param accountId int
     * @return Account
     * @throws Account.AccountException e
     */
    Account getAccount(int accountId) throws Account.AccountException;

    /**
     * @param investorId String
     * @return Account
     * @throws Account.AccountException e
     */
    Account getAccount(String investorId) throws Account.AccountException;


    /**
     * @param accountId int
     */
    default void setAccountNotTradable(int accountId) {
        getAccount(accountId).disable();
    }

    /**
     * @param accountId int
     */
    default void setAccountTradable(int accountId) {
        getAccount(accountId).enable();
    }

    /**
     * @param accountId int
     * @return boolean
     */
    default boolean isAccountTradable(int accountId) {
        return getAccount(accountId).isEnabled();
    }

    /**
     * @param subAccountId int
     */
    default void setSubAccountNotTradable(int subAccountId) {
        getSubAccount(subAccountId).disable();
    }

    /**
     * @param subAccountId int
     */
    default void setSubAccountTradable(int subAccountId) {
        getSubAccount(subAccountId).enable();
    }

    /**
     * @param subAccountId int
     * @return boolean
     */
    default boolean isSubAccountTradable(int subAccountId) {
        return getSubAccount(subAccountId).isEnabled();
    }

}
