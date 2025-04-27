package io.rapid.core.event.enums;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.order.OrdConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@RequiredArgsConstructor
public enum OrdSide {

    /**
     * 无效
     */
    INVALID(OrdConstant.INVALID),

    /**
     * 买
     */
    BUY(OrdConstant.SIDE_BUY),

    /**
     * 卖
     */
    SELL(OrdConstant.SIDE_SELL),

    /**
     * 融资买入
     */
    MARGIN_BUY(OrdConstant.SIDE_MARGIN_BUY),

    /**
     * 融券卖出
     */
    SHORT_SELL(OrdConstant.SIDE_SHORT_SELL),

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
            case OrdConstant.SIDE_BUY -> BUY;
            case OrdConstant.SIDE_SELL -> SELL;
            case OrdConstant.SIDE_MARGIN_BUY -> MARGIN_BUY;
            case OrdConstant.SIDE_SHORT_SELL -> SHORT_SELL;
            default -> {
                log.error("OrdSide valueOf error, return OrdSide -> [INVALID], input code==[{}]", code);
                yield INVALID;
            }
        };
    }

}
