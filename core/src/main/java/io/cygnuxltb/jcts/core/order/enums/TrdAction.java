package io.cygnuxltb.jcts.core.order.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrdAction {

    /**
     * 无效
     */
    Invalid(TrdActionCode.INVALID),

    /**
     * 开仓
     */
    Open(TrdActionCode.OPEN),

    /**
     * 平仓
     */
    Close(TrdActionCode.CLOSE),

    /**
     * 平今仓
     */
    CloseToday(TrdActionCode.CLOSE_TODAY),

    /**
     * 平昨仓
     */
    CloseYesterday(TrdActionCode.CLOSE_YESTERDAY),

    ;

    private final char code;

    public char getCode() {
        return code;
    }

    /**
     * @param code int
     * @return TrdAction
     */
    public static TrdAction valueOf(int code) {
        return switch (code) {
            case TrdActionCode.OPEN -> Open;
            case TrdActionCode.CLOSE -> Close;
            case TrdActionCode.CLOSE_TODAY -> CloseToday;
            case TrdActionCode.CLOSE_YESTERDAY -> CloseYesterday;
            default -> Invalid;
        };
    }

    public static TrdAction valueOf(io.cygnuxltb.jcts.core.ser.enums.TrdAction action) {
        return switch (action) {
            case OPEN -> Open;
            case CLOSE -> Close;
            case CLOSE_TODAY -> CloseToday;
            case CLOSE_YESTERDAY -> CloseYesterday;
            default -> Invalid;
        };
    }


    public interface TrdActionCode {
        // 无效
        char INVALID = 'I';
        // 开仓
        char OPEN = 'O';
        // 平仓
        char CLOSE = 'C';
        // 平今仓
        char CLOSE_TODAY = 'T';
        // 平昨仓
        char CLOSE_YESTERDAY = 'Y';
    }

}