package io.cygnux.rapid.core.types.field;

import org.slf4j.Logger;

import static io.mercury.common.lang.Validator.atWithinRange;

public record SubAccountID(
        int value) {

    public SubAccountID {
        checkSubAccountId(value);
    }

    /**
     * 子账户ID最小值
     */
    public static final int MIN_SUB_ACCOUNT_ID = 1;

    /**
     * 子账户ID最大值
     */
    public static final int MAX_SUB_ACCOUNT_ID = Integer.MAX_VALUE >> 1;

    /**
     * @param value int
     * @return SubAccountID
     */
    public static SubAccountID of(int value) {
        return new SubAccountID(value);
    }

    /**
     * @param subAccountId int
     * @throws IllegalArgumentException exception
     */
    public static void checkSubAccountId(int subAccountId) {
        checkSubAccountId(subAccountId, null);
    }

    /**
     * @param subAccountId int
     * @param logger       Logger
     * @throws IllegalArgumentException exception
     */
    public static void checkSubAccountId(int subAccountId, Logger logger) {
        atWithinRange(subAccountId, MIN_SUB_ACCOUNT_ID, MAX_SUB_ACCOUNT_ID, "subAccountId", logger);
    }

}
