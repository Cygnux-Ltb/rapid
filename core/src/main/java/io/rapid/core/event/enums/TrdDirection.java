package io.rapid.core.event.enums;

import io.rapid.core.trade.TrdConstant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrdDirection {

    INVALID(TrdConstant.INVALID),

    LONG(TrdConstant.DIRECTION_LONG),

    SHORT(TrdConstant.DIRECTION_SHORT),

    ;

    private final char code;

    public char getCode() {
        return code;
    }

    /**
     * @param code int
     * @return TrdDirection
     */
    public static TrdDirection valueOf(int code) {
        return switch (code) {
            case TrdConstant.DIRECTION_LONG -> TrdDirection.LONG;
            case TrdConstant.DIRECTION_SHORT -> TrdDirection.SHORT;
            default -> TrdDirection.INVALID;
        };
    }

}