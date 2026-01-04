package io.cygnux.rapid.core.types.id;

import org.slf4j.Logger;

import static io.mercury.common.lang.Validator.atWithinRange;

public record SubAccountID(
        int value
) {

    public SubAccountID {
        checkSubAccountId(value);
    }

    public static final int MIN_SUB_ACCOUNT_ID = 1;

    public static final int MAX_SUB_ACCOUNT_ID = Integer.MAX_VALUE >> 1;

    public static SubAccountID of(int subAccountId) {
        return new SubAccountID(subAccountId);
    }

    /**
     * @param subAccountId int
     */
    public static void checkSubAccountId(int subAccountId) {
        checkSubAccountId(subAccountId, null);
    }

    /**
     * @param subAccountId int
     * @param logger       Logger
     */
    public static void checkSubAccountId(int subAccountId, Logger logger) {
        atWithinRange(subAccountId,
                MIN_SUB_ACCOUNT_ID, MAX_SUB_ACCOUNT_ID, "subAccountId", logger);
    }

}
