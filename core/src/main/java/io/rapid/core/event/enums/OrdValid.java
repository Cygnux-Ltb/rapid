package io.rapid.core.event.enums;

import io.rapid.core.constant.OrdConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrdValid {

    /**
     * 无效
     */
    INVALID(OrdConstant.INVALID),
    /**
     * Good Till Cancel, 将一直有效, 直到交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GTC(OrdConstant.VALID_GTC),
    /**
     * Good Till Date, 将一直有效, 直到指定日期或交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GTD(OrdConstant.VALID_GTD),
    /**
     * Good For Day, 只在当日的交易时段有效, 一旦当前交易时段结束, 自动取消的报单.
     */
    GFD(OrdConstant.VALID_GFD),

    ;

    @Getter
    private final char code;

    /**
     * @return OrdValid.GoodTillCancel
     */
    public static OrdValid defaultValid() {
        return OrdValid.GTC;
    }

}
