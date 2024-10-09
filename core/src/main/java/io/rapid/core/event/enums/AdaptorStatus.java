package io.rapid.core.event.enums;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.adaptor.AdaptorStatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

/**
 * Adaptor状态
 */
@RequiredArgsConstructor
public enum AdaptorStatus {

    /**
     * 无效
     */
    INVALID(AdaptorStatusCode.INVALID),
    /**
     * 全部启用
     */
    ALL_ENABLE(AdaptorStatusCode.ALL_ENABLE),
    /**
     * 全部禁用
     */
    ALL_DISABLE(AdaptorStatusCode.ALL_DISABLE),
    /**
     * 仅行情启用
     */
    MD_ENABLE(AdaptorStatusCode.MD_ENABLE),
    /**
     * 仅交易启用
     */
    TRADER_ENABLE(AdaptorStatusCode.TRADER_ENABLE),

    ;

    @Getter
    private final char code;

    private static final Logger log = Log4j2LoggerFactory.getLogger(AdaptorStatus.class);

    public boolean isTraderAvailable() {
        return this == TRADER_ENABLE || this == ALL_ENABLE;
    }

    public boolean isMdAvailable() {
        return this == MD_ENABLE || this == ALL_ENABLE;
    }

    /**
     * @param code int
     * @return AdaptorStatus
     */
    public static AdaptorStatus valueOf(char code) {
        return switch (code) {
            // 全部启用
            case AdaptorStatusCode.ALL_ENABLE -> AdaptorStatus.ALL_ENABLE;
            // 全部禁用
            case AdaptorStatusCode.ALL_DISABLE -> AdaptorStatus.ALL_DISABLE;
            // 仅行情启用
            case AdaptorStatusCode.MD_ENABLE -> AdaptorStatus.MD_ENABLE;
            // 仅交易启用
            case AdaptorStatusCode.TRADER_ENABLE -> AdaptorStatus.TRADER_ENABLE;
            // 没有匹配项
            default -> {
                log.error("AdaptorStatus valueOf error, return AdaptorStatus -> [INVALID], input code==[{}]", code);
                yield AdaptorStatus.INVALID;
            }
        };
    }

}
