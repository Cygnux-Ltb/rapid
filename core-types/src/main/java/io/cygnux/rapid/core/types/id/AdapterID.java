package io.cygnux.rapid.core.types.id;

import io.mercury.common.lang.Validator;
import org.slf4j.Logger;

public record AdapterID(
        int value
) {

    public AdapterID {
        checkAdapterId(value);
    }

    public static final int MIN_ADAPTER_ID = 1;

    /**
     * @param adapterId int
     */
    public static void checkAdapterId(int adapterId) {
        checkAdapterId(adapterId, null);
    }

    /**
     * @param adapterId int
     * @param logger    Logger
     */
    public static void checkAdapterId(int adapterId, Logger logger) {
        Validator.greaterOrEqualThan(adapterId, MIN_ADAPTER_ID, "adapterId");
    }

}
