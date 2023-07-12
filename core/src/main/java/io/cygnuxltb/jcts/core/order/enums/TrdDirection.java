package io.cygnuxltb.jcts.core.order.enums;

import io.cygnuxltb.jcts.core.order.TdxProvider;
import io.cygnuxltb.jcts.core.serialization.avro.enums.AEnumTrdDirection;

public enum TrdDirection implements TdxProvider<AEnumTrdDirection> {

    Invalid(TrdDirectionCode.INVALID, AEnumTrdDirection.INVALID),

    Long(TrdDirectionCode.LONG, AEnumTrdDirection.LONG),

    Short(TrdDirectionCode.SHORT, AEnumTrdDirection.SHORT),

    ;

    private final char code;

    private final AEnumTrdDirection tdxValue;

    TrdDirection(char code, AEnumTrdDirection tdxValue) {
        this.code = code;
        this.tdxValue = tdxValue;
    }

    public char getCode() {
        return code;
    }

    /**
     * @param code int
     * @return TrdDirection
     */
    public static TrdDirection valueOf(int code) {
        return switch (code) {
            case TrdDirectionCode.LONG -> TrdDirection.Long;
            case TrdDirectionCode.SHORT -> TrdDirection.Short;
            default -> TrdDirection.Invalid;
        };
    }

    /**
     * @param tdxValue TdxTrdDirection
     * @return TrdDirection
     */
    public static TrdDirection valueOf(AEnumTrdDirection tdxValue) {
        return switch (tdxValue) {
            case LONG -> TrdDirection.Long;
            case SHORT -> TrdDirection.Short;
            default -> TrdDirection.Invalid;
        };
    }

    @Override
    public AEnumTrdDirection getTdxValue() {
        return tdxValue;
    }

    public interface TrdDirectionCode {
        // 无效
        char INVALID = 'I';
        // 多
        char LONG = 'L';
        // 空
        char SHORT = 'S';
    }

}