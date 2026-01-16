package io.cygnux.rapid.core.types.order.enums;

import io.cygnux.rapid.core.types.constant.OrdConst;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@RequiredArgsConstructor
public enum OrdSide {

    /**
     * 无效
     */
    INVALID(OrdConst.INVALID),

    /**
     * 买
     */
    BUY(OrdConst.SIDE_BUY),

    /**
     * 卖
     */
    SELL(OrdConst.SIDE_SELL),

    /**
     * 融资买入
     */
    MARGIN_BUY(OrdConst.SIDE_MARGIN_BUY),

    /**
     * 融券卖出
     */
    SHORT_SELL(OrdConst.SIDE_SHORT_SELL),

    ;

    @Getter
    private final char code;

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrdSide.class);

    /**
     * @param code int
     * @return OrdSide
     */
    public static OrdSide valueOf(int code) {
        return switch (code) {
            case OrdConst.SIDE_BUY -> BUY;
            case OrdConst.SIDE_SELL -> SELL;
            case OrdConst.SIDE_MARGIN_BUY -> MARGIN_BUY;
            case OrdConst.SIDE_SHORT_SELL -> SHORT_SELL;
            default -> {
                log.error("OrdSide valueOf error, return OrdSide -> [INVALID], input code==[{}]", code);
                yield INVALID;
            }
        };
    }

}
