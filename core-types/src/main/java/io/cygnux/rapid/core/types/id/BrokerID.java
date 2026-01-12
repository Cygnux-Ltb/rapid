package io.cygnux.rapid.core.types.id;

import io.mercury.common.lang.Validator;
import org.slf4j.Logger;

public record BrokerID(
        String value
) {

    public BrokerID {
        checkBrokerId(value);
    }

    /**
     * @param brokerId String
     */
    public static void checkBrokerId(String brokerId) {
        checkBrokerId(brokerId, null);
    }

    /**
     * @param brokerId String
     * @param logger   Logger
     */
    public static void checkBrokerId(String brokerId, Logger logger) {
        Validator.nonEmpty(brokerId, "brokerId", logger);
    }

}
