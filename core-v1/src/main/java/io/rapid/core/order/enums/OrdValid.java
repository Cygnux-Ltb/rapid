package io.rapid.core.order.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrdValid {

    /**
     * Good Till Cancel, 将一直有效, 直到交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GoodTillCancel(OrdValidCode.GTC),

    /**
     * Good Till Date, 将一直有效, 直到指定日期或交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GoodTillDate(OrdValidCode.GTD),

    /**
     * Good For Day, 只在当日的交易时段有效, 一旦当前交易时段结束, 自动取消的报单.
     */
    GoodForDay(OrdValidCode.GFD),

    ;

    private final char code;

    public char getCode() {
        return code;
    }

    /**
     * @return OrdValid.GoodTillCancel
     */
    public static OrdValid defaultValid() {
        return OrdValid.GoodTillCancel;
    }

    public interface OrdValidCode {
        // Good Till Cancel
        char GTC = 'C';
        // Good Till Date
        char GTD = 'D';
        // Good For Day
        char GFD = 'G';
    }

}
