package io.cygnux.rapid.core.types.order.enums;

import io.cygnux.rapid.core.types.constant.OrdConst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrdValid {

    /**
     * 无效
     */
    INVALID(OrdConst.INVALID),

    /**
     * Good Till Cancel, 将一直有效, 直到交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GTC(OrdConst.VALID_GTC),

    /**
     * Good Till Date, 将一直有效, 直到指定日期或交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GTD(OrdConst.VALID_GTD),

    /**
     * Good For Day, 只在当日的交易时段有效, 一旦当前交易时段结束, 自动取消的报单.
     */
    GFD(OrdConst.VALID_GFD),

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
