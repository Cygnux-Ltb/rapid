package io.cygnux.rapid.core.shared.enums;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.core.order.OrdConstant;
import lombok.Getter;
import org.slf4j.Logger;

public enum OrdStatus {

    /**
     * 未知
     */
    UNKNOWN(OrdConstant.STATUS_UNKNOWN, false),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 新订单未确认
     */
    PENDING_NEW(OrdConstant.STATUS_PENDING_NEW, false),
    /**
     * 新订单
     */
    NEW(OrdConstant.STATUS_NEW, false),
    /**
     * 新订单已拒绝
     */
    NEW_REJECTED(OrdConstant.STATUS_NEW_REJECTED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 部分成交
     */
    PARTIALLY_FILLED(OrdConstant.STATUS_PARTIALLY_FILLED, false),
    /**
     * 全部成交
     */
    FILLED(OrdConstant.STATUS_FILLED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 未确认撤单
     */
    PENDING_CANCEL(OrdConstant.STATUS_PENDING_CANCEL, false),
    /**
     * 已撤单
     */
    CANCELED(OrdConstant.STATUS_CANCELED, true),
    /**
     * 撤单已拒绝
     */
    CANCEL_REJECTED(OrdConstant.STATUS_CANCEL_REJECTED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 未确认修改订单
     */
    PENDING_REPLACE(OrdConstant.STATUS_PENDING_REPLACE, false),
    /**
     * 已修改
     */
    REPLACED(OrdConstant.STATUS_REPLACED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 已暂停
     */
    SUSPENDED(OrdConstant.STATUS_SUSPENDED, false),
    /**
     * 已停止
     */
    STOPPED(OrdConstant.STATUS_STOPPED, true),
    /**
     * 已过期
     */
    EXPIRED(OrdConstant.STATUS_EXPIRED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 未提供
     */
    UNPROVIDED(OrdConstant.STATUS_UNPROVIDED, false),

    ;

    @Getter
    private final char code;

    @Getter
    private final boolean finished;

    private final String str;

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrdStatus.class);

    /**
     * @param code     代码
     * @param finished 是否为已结束状态
     */
    OrdStatus(char code, boolean finished) {
        this.code = code;
        this.finished = finished;
        this.str = name() + "[" + code + "-" + (finished ? "Finished" : "Unfinished") + "]";
    }

    @Override
    public String toString() {
        return str;
    }

    /**
     * @param code int
     * @return OrdStatus
     */
    public static OrdStatus valueOf(char code) {
        return switch (code) {
            // 未确认新订单
            case OrdConstant.STATUS_PENDING_NEW -> PENDING_NEW;
            // 新订单
            case OrdConstant.STATUS_NEW -> NEW;
            // 新订单已拒绝
            case OrdConstant.STATUS_NEW_REJECTED -> NEW_REJECTED;
            // 部分成交
            case OrdConstant.STATUS_PARTIALLY_FILLED -> PARTIALLY_FILLED;
            // 全部成交
            case OrdConstant.STATUS_FILLED -> FILLED;
            // 未确认撤单
            case OrdConstant.STATUS_PENDING_CANCEL -> PENDING_CANCEL;
            // 已撤单
            case OrdConstant.STATUS_CANCELED -> CANCELED;
            // 撤单已拒绝
            case OrdConstant.STATUS_CANCEL_REJECTED -> CANCEL_REJECTED;
            // 未确认修改订单
            case OrdConstant.STATUS_PENDING_REPLACE -> PENDING_REPLACE;
            // 已修改
            case OrdConstant.STATUS_REPLACED -> REPLACED;
            // 已暂停
            case OrdConstant.STATUS_SUSPENDED -> SUSPENDED;
            // 未提供
            case OrdConstant.STATUS_UNPROVIDED -> UNPROVIDED;
            // 没有匹配项
            default -> {
                log.error("OrdStatus valueOf error, return OrdStatus -> [UNKNOWN], input code==[{}]", code);
                yield UNKNOWN;
            }
        };
    }

}
