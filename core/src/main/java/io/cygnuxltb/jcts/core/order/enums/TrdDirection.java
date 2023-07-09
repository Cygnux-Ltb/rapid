package io.cygnuxltb.jcts.core.trader.order.enums;

import io.cygnuxltb.jcts.core.trader.order.TdxProvider;
import io.cygnuxltb.jcts.core.trader.serialization.avro.enums.AvroTrdDirection;
import io.horizon.trader.order.TdxProvider;
import io.horizon.trader.serialization.avro.enums.AvroTrdDirection;

public enum TrdDirection implements TdxProvider<AvroTrdDirection> {

    Invalid(TrdDirectionCode.INVALID, AvroTrdDirection.INVALID),

    Long(TrdDirectionCode.LONG, AvroTrdDirection.LONG),

    Short(TrdDirectionCode.SHORT, AvroTrdDirection.SHORT),

    ;

    private final char code;

    private final AvroTrdDirection tdxValue;

    TrdDirection(char code, AvroTrdDirection tdxValue) {
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
    public static TrdDirection valueOf(AvroTrdDirection tdxValue) {
        return switch (tdxValue) {
            case LONG -> TrdDirection.Long;
            case SHORT -> TrdDirection.Short;
            default -> TrdDirection.Invalid;
        };
    }

    @Override
    public AvroTrdDirection getTdxValue() {
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