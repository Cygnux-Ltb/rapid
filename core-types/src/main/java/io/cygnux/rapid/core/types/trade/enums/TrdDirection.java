package io.cygnux.rapid.core.types.trade.enums;

import io.cygnux.rapid.core.types.constant.TraConst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrdDirection {

    INVALID(TraConst.INVALID),

    LONG(TraConst.DIRECTION_LONG),

    SHORT(TraConst.DIRECTION_SHORT),

    ;

    @Getter
    private final char code;

    /**
     * @param code int
     * @return TrdDirection
     */
    public static TrdDirection valueOf(int code) {
        return switch (code) {
            case TraConst.DIRECTION_LONG -> TrdDirection.LONG;
            case TraConst.DIRECTION_SHORT -> TrdDirection.SHORT;
            default -> TrdDirection.INVALID;
        };
    }

}