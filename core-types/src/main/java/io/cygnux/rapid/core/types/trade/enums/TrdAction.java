package io.cygnux.rapid.core.types.trade.enums;

import io.cygnux.rapid.core.types.constant.TraConst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrdAction {

    /**
     * 无效
     */
    INVALID(TraConst.INVALID),

    /**
     * 开仓
     */
    OPEN(TraConst.ACTION_OPEN),

    /**
     * 平仓
     */
    CLOSE(TraConst.ACTION_CLOSE),

    /**
     * 平今仓
     */
    CLOSE_TODAY(TraConst.ACTION_CLOSE_TODAY),

    /**
     * 平昨仓
     */
    CLOSE_YESTERDAY(TraConst.ACTION_CLOSE_YESTERDAY),

    ;

    @Getter
    private final char code;

    /**
     * @param code int
     * @return TrdAction
     */
    public static TrdAction valueOf(char code) {
        return switch (code) {
            case TraConst.ACTION_OPEN -> OPEN;
            case TraConst.ACTION_CLOSE -> CLOSE;
            case TraConst.ACTION_CLOSE_TODAY -> CLOSE_TODAY;
            case TraConst.ACTION_CLOSE_YESTERDAY -> CLOSE_YESTERDAY;
            default -> INVALID;
        };
    }

}