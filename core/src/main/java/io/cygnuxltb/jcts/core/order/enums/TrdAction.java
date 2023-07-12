package io.cygnuxltb.jcts.core.order.enums;

import io.cygnuxltb.jcts.core.order.TdxProvider;
import io.cygnuxltb.jcts.core.serialization.avro.enums.AEnumTrdAction;

public enum TrdAction implements TdxProvider<AEnumTrdAction> {

    /**
     * 无效
     */
    Invalid(TrdActionCode.INVALID, AEnumTrdAction.INVALID),

    /**
     * 开仓
     */
    Open(TrdActionCode.OPEN, AEnumTrdAction.OPEN),

    /**
     * 平仓
     */
    Close(TrdActionCode.CLOSE, AEnumTrdAction.CLOSE),

    /**
     * 平今仓
     */
    CloseToday(TrdActionCode.CLOSE_TODAY, AEnumTrdAction.CLOSE_TODAY),

    /**
     * 平昨仓
     */
    CloseYesterday(TrdActionCode.CLOSE_YESTERDAY, AEnumTrdAction.CLOSE_YESTERDAY),

    ;

    private final char code;

    private final AEnumTrdAction tdxValue;

    TrdAction(char code, AEnumTrdAction tdxValue) {
        this.code = code;
        this.tdxValue = tdxValue;
    }

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

    public static TrdAction valueOf(AEnumTrdAction action) {
        return switch (action) {
            case OPEN -> Open;
            case CLOSE -> Close;
            case CLOSE_TODAY -> CloseToday;
            case CLOSE_YESTERDAY -> CloseYesterday;
            default -> Invalid;
        };
    }

    @Override
    public AEnumTrdAction getTdxValue() {
        return tdxValue;
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