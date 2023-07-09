package io.cygnuxltb.jcts.core.trader.order.enums;

import io.cygnuxltb.jcts.core.trader.order.TdxProvider;
import io.cygnuxltb.jcts.core.trader.serialization.avro.enums.AvroOrdValid;
import io.horizon.trader.order.TdxProvider;
import io.horizon.trader.serialization.avro.enums.AvroOrdValid;

public enum OrdValid implements TdxProvider<AvroOrdValid> {

    /**
     * Good Till Cancel, 将一直有效, 直到交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GoodTillCancel(OrdValidCode.GTC, AvroOrdValid.GTC),

    /**
     * Good Till Date, 将一直有效, 直到指定日期或交易员取消这个报单, 或者该合约本身到期的报单.
     */
    GoodTillDate(OrdValidCode.GTD, AvroOrdValid.GTD),

    /**
     * Good For Day, 只在当日的交易时段有效, 一旦当前交易时段结束, 自动取消的报单.
     */
    GoodForDay(OrdValidCode.GFD, AvroOrdValid.GFD),

    ;

    private final char code;

    private final AvroOrdValid tdxValue;

    OrdValid(char code, AvroOrdValid tdxValue) {
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
    public AvroOrdValid getTdxValue() {
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
