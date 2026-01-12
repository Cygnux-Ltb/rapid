package io.cygnux.rapid.core.types.id;

import io.mercury.common.lang.Validator;
import org.slf4j.Logger;

public record AccountID(
        int value
) {

    public AccountID {
        checkAccountId(value);
    }

    public static final int MIN_ACCOUNT_ID = 1;

    public static final int MAX_ACCOUNT_ID = Integer.MAX_VALUE >> 1;

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