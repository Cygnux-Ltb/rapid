package io.cygnux.rapid.core.stream.enums;

import io.cygnux.rapid.core.trade.TrdConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrdAction {

    /**
     * 无效
     */
    INVALID(TrdConstant.INVALID),

    /**
     * 开仓
     */
    OPEN(TrdConstant.ACTION_OPEN),

    /**
     * 平仓
     */
    CLOSE(TrdConstant.ACTION_CLOSE),

    /**
     * 平今仓
     */
    CLOSE_TODAY(TrdConstant.ACTION_CLOSE_TODAY),

    /**
     * 平昨仓
     */
    CLOSE_YESTERDAY(TrdConstant.ACTION_CLOSE_YESTERDAY),

    ;

    @Getter
    private final char code;

    /**
     * @param code int
     * @return TrdAction
     */
    public static TrdAction valueOf(char code) {
        return switch (code) {
            case TrdConstant.ACTION_OPEN -> OPEN;
            case TrdConstant.ACTION_CLOSE -> CLOSE;
            case TrdConstant.ACTION_CLOSE_TODAY -> CLOSE_TODAY;
            case TrdConstant.ACTION_CLOSE_YESTERDAY -> CLOSE_YESTERDAY;
            default -> INVALID;
        };
    }

}