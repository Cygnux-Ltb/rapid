package io.cygnux.rapid.core.types.field;

import io.mercury.common.lang.Validator;
import org.slf4j.Logger;

public record AccountID(
        int value) {

    public AccountID {
        checkAccountId(value);
    }

    /**
     * 最小AccountID
     */
    public static final int MIN_ACCOUNT_ID = 1;

    /**
     * 最大AccountID
     */
    public static final int MAX_ACCOUNT_ID = Integer.MAX_VALUE >> 1;

    /**
     * @param value int
     * @return AccountID
     */
    public static AccountID of(int value) {
        return new AccountID(value);
    }

    /**
     * @param accountId int
     */
    public static void checkAccountId(int accountId) {
        checkAccountId(accountId, null);
    }

    /**
     * @param accountId int
     * @param logger    Logger
     */
    public static void checkAccountId(int accountId, Logger logger) {
        Validator.atWithinRange(accountId, MIN_ACCOUNT_ID, MAX_ACCOUNT_ID, "accountId", logger);
    }

}