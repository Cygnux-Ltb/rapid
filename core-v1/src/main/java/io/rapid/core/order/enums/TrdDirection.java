package io.rapid.core.order.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrdDirection {

    Invalid(TrdDirectionCode.INVALID),

    Long(TrdDirectionCode.LONG),

    Short(TrdDirectionCode.SHORT),

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
            case TrdDirectionCode.LONG -> TrdDirection.Long;
            case TrdDirectionCode.SHORT -> TrdDirection.Short;
            default -> TrdDirection.Invalid;
        };
    }

    /**
     * @return TrdDirection
     */
    public static TrdDirection valueOf(io.rapid.core.protocol.avro.enums.TrdDirection direction) {
        return switch (direction) {
            case LONG -> TrdDirection.Long;
            case SHORT -> TrdDirection.Short;
            default -> TrdDirection.Invalid;
        };
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