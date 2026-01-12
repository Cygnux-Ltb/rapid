package io.cygnux.rapid.core.types.id;

import io.mercury.common.lang.Validator;

public record OrdSysID(
        long value
) {

    public OrdSysID {
        checkOrdSysId(value);
    }

    public static final int MIN_ORD_SYS_ID = 1;

    /**
     * @param ordSysId long
     */
    public static void checkOrdSysId(long ordSysId) {
        Validator.lessThan(ordSysId, MIN_ORD_SYS_ID, "ordSysId");
    }

}
