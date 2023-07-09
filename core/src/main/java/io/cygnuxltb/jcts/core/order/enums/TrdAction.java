package io.cygnuxltb.jcts.core.order.enums;

import io.cygnuxltb.jcts.core.order.TdxProvider;
import io.cygnuxltb.jcts.core.serialization.avro.enums.AvroTrdAction;
import io.horizon.trader.order.TdxProvider;
import io.horizon.trader.serialization.avro.enums.AvroTrdAction;

public enum TrdAction implements TdxProvider<AvroTrdAction> {

    /**
     * 无效
     */
    Invalid(TrdActionCode.INVALID, AvroTrdAction.INVALID),

    /**
     * 开仓
     */
    Open(TrdActionCode.OPEN, AvroTrdAction.OPEN),

    /**
     * 平仓
     */
    Close(TrdActionCode.CLOSE, AvroTrdAction.CLOSE),

    /**
     * 平今仓
     */
    CloseToday(TrdActionCode.CLOSE_TODAY, AvroTrdAction.CLOSE_TODAY),

    /**
     * 平昨仓
     */
    CloseYesterday(TrdActionCode.CLOSE_YESTERDAY, AvroTrdAction.CLOSE_YESTERDAY),

    ;

    private final char code;

    private final AvroTrdAction tdxValue;

    TrdAction(char code, AvroTrdAction tdxValue) {
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

    public static TrdAction valueOf(AvroTrdAction action) {
        return switch (action) {
            case OPEN -> Open;
            case CLOSE -> Close;
            case CLOSE_TODAY -> CloseToday;
            case CLOSE_YESTERDAY -> CloseYesterday;
            default -> Invalid;
        };
    }

    @Override
    public AvroTrdAction getTdxValue() {
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