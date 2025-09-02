package io.cygnux.rapid.core.stream.enums;

import io.cygnux.rapid.core.trade.TrdConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrdDirection {

    INVALID(TrdConstant.INVALID),

    LONG(TrdConstant.DIRECTION_LONG),

    SHORT(TrdConstant.DIRECTION_SHORT),

    ;

    @Getter
    private final char code;

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