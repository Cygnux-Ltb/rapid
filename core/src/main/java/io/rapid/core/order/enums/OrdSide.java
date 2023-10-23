package io.rapid.core.order.enums;

import io.rapid.core.order.TdxProvider;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

public enum OrdSide implements TdxProvider<io.cygnuxltb.jcts.core.ser.enums.OrdSide> {

    /**
     * 无效
     */
    Invalid(OrdSideCode.INVALID, io.cygnuxltb.jcts.core.ser.enums.OrdSide.INVALID, TrdDirection.Invalid),

    /**
     * 买
     */
    Buy(OrdSideCode.BUY, io.cygnuxltb.jcts.core.ser.enums.OrdSide.BUY, TrdDirection.Long),

    /**
     * 卖
     */
    Sell(OrdSideCode.SELL, io.cygnuxltb.jcts.core.ser.enums.OrdSide.SELL, TrdDirection.Short),

    /**
     * 融资买入
     */
    MarginBuy(OrdSideCode.MARGIN_BUY, io.cygnuxltb.jcts.core.ser.enums.OrdSide.MARGIN_BUY, TrdDirection.Long),

    /**
     * 融券卖出
     */
    ShortSell(OrdSideCode.SHORT_SELL, io.cygnuxltb.jcts.core.ser.enums.OrdSide.SHORT_SELL, TrdDirection.Short),

    ;

    private final char code;

    private final io.cygnuxltb.jcts.core.ser.enums.OrdSide tdxValue;

    private final TrdDirection direction;

    private final String str;

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrdSide.class);

    OrdSide(char code, io.cygnuxltb.jcts.core.ser.enums.OrdSide tdxValue, TrdDirection direction) {
        this.code = code;
        this.tdxValue = tdxValue;
        this.direction = direction;
        this.str = name() + "[" + code + "-" + direction + "]";
    }

    public char getCode() {
        return code;
    }

    public TrdDirection getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return str;
    }

    /**
     * @param code int
     * @return OrdSide
     */
    public static OrdSide valueOf(int code) {
        return switch (code) {
            case OrdSideCode.BUY -> Buy;
            case OrdSideCode.SELL -> Sell;
            case OrdSideCode.MARGIN_BUY -> MarginBuy;
            case OrdSideCode.SHORT_SELL -> ShortSell;
            default ->
                //log.error("OrdSide valueOf error, return OrdSide -> [Invalid], input code==[{}]", code);
                    Invalid;
        };
    }

    @Override
    public io.cygnuxltb.jcts.core.ser.enums.OrdSide getTdxValue() {
        return tdxValue;
    }

    public interface OrdSideCode {
        // 无效
        char INVALID = 'I';
        // 买
        char BUY = 'B';
        // 卖
        char SELL = 'S';
        // 融资买入
        char MARGIN_BUY = 'M';
        // 融券卖出
        char SHORT_SELL = 'T';
    }

}
