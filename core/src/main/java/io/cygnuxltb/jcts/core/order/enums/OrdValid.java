package io.cygnuxltb.jcts.core.order.enums;

import io.cygnuxltb.jcts.core.order.TdxProvider;
import io.cygnuxltb.jcts.core.serialization.avro.enums.AEnumOrdValid;

public enum OrdValid implements TdxProvider<AEnumOrdValid> {

    /**
     * Good Till Cancel, 将一直有效, 直到交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GoodTillCancel(OrdValidCode.GTC, AEnumOrdValid.GTC),

    /**
     * Good Till Date, 将一直有效, 直到指定日期或交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GoodTillDate(OrdValidCode.GTD, AEnumOrdValid.GTD),

    /**
     * Good For Day, 只在当日的交易时段有效, 一旦当前交易时段结束, 自动取消的报单.
     */
    GoodForDay(OrdValidCode.GFD, AEnumOrdValid.GFD),

    ;

    private final char code;

    private final AEnumOrdValid tdxValue;

    OrdValid(char code, AEnumOrdValid tdxValue) {
        this.code = code;
        this.tdxValue = tdxValue;
    }

    public char getCode() {
        return code;
    }


    /**
     * @return OrdValid.GoodTillCancel
     */
    public static OrdValid defaultValid() {
        return OrdValid.GoodTillCancel;
    }

    @Override
    public AEnumOrdValid getTdxValue() {
        return tdxValue;
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
