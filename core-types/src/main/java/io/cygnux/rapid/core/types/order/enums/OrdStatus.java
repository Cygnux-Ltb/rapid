package io.cygnux.rapid.core.types.order.enums;

import io.cygnux.rapid.core.types.constant.OrdConst;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import lombok.Getter;
import org.slf4j.Logger;

public enum OrdStatus {

    /**
     * 未知
     */
    UNKNOWN(OrdConst.STATUS_UNKNOWN, false),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 新订单未确认
     */
    PENDING_NEW(OrdConst.STATUS_PENDING_NEW, false),
    /**
     * 新订单
     */
    NEW(OrdConst.STATUS_NEW, false),
    /**
     * 新订单已拒绝
     */
    NEW_REJECTED(OrdConst.STATUS_NEW_REJECTED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 部分成交
     */
    PARTIALLY_FILLED(OrdConst.STATUS_PARTIALLY_FILLED, false),
    /**
     * 全部成交
     */
    FILLED(OrdConst.STATUS_FILLED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 未确认撤单
     */
    PENDING_CANCEL(OrdConst.STATUS_PENDING_CANCEL, false),
    /**
     * 已撤单
     */
    CANCELED(OrdConst.STATUS_CANCELED, true),
    /**
     * 撤单已拒绝
     */
    CANCEL_REJECTED(OrdConst.STATUS_CANCEL_REJECTED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 未确认修改订单
     */
    PENDING_REPLACE(OrdConst.STATUS_PENDING_REPLACE, false),
    /**
     * 已修改
     */
    REPLACED(OrdConst.STATUS_REPLACED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 已暂停
     */
    SUSPENDED(OrdConst.STATUS_SUSPENDED, false),
    /**
     * 已停止
     */
    STOPPED(OrdConst.STATUS_STOPPED, true),
    /**
     * 已过期
     */
    EXPIRED(OrdConst.STATUS_EXPIRED, true),

    /////////////////////////////////////////////////////////////////////////////

    /**
     * 未提供
     */
    UNPROVIDED(OrdConst.STATUS_UNPROVIDED, false),

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
            case OrdConst.STATUS_PENDING_NEW -> PENDING_NEW;
            // 新订单
            case OrdConst.STATUS_NEW -> NEW;
            // 新订单已拒绝
            case OrdConst.STATUS_NEW_REJECTED -> NEW_REJECTED;
            // 部分成交
            case OrdConst.STATUS_PARTIALLY_FILLED -> PARTIALLY_FILLED;
            // 全部成交
            case OrdConst.STATUS_FILLED -> FILLED;
            // 未确认撤单
            case OrdConst.STATUS_PENDING_CANCEL -> PENDING_CANCEL;
            // 已撤单
            case OrdConst.STATUS_CANCELED -> CANCELED;
            // 撤单已拒绝
            case OrdConst.STATUS_CANCEL_REJECTED -> CANCEL_REJECTED;
            // 未确认修改订单
            case OrdConst.STATUS_PENDING_REPLACE -> PENDING_REPLACE;
            // 已修改
            case OrdConst.STATUS_REPLACED -> REPLACED;
            // 已暂停
            case OrdConst.STATUS_SUSPENDED -> SUSPENDED;
            // 未提供
            case OrdConst.STATUS_UNPROVIDED -> UNPROVIDED;
            // 没有匹配项
            default -> {
                log.error("OrdStatus valueOf error, return OrdStatus -> [UNKNOWN], input code==[{}]", code);
                yield UNKNOWN;
            }
        };
    }

}
