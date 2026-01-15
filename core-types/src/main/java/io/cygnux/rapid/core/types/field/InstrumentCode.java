package io.cygnux.rapid.core.types.field;

import io.mercury.common.lang.Validator;
import org.slf4j.Logger;

public record InstrumentCode(
        String value
) {

    public InstrumentCode {
        checkInstrumentCode(value);
    }


    /**
     * @param instrumentCode String
     */
    public static void checkInstrumentCode(String instrumentCode) {
        checkInstrumentCode(instrumentCode, null);
    }

    /**
     * @param instrumentCode String
     * @param logger         Logger
     */
    public static void checkInstrumentCode(String instrumentCode, Logger logger) {
        Validator.nonEmpty(instrumentCode, "instrumentCode", logger);
    }

}
