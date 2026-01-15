package io.cygnux.rapid.core.types.trade.enums;

/**
 * 交易状态
 */
public enum TradingStatus {

    /**
     * 未提供
     */
    NOT_PROVIDED,

    /**
     * 开盘前
     */
    NO_TRADING,

    /**
     * 交易中
     */
    TRADING,

    /**
     * 已收盘, 未结算
     */
    CLOSED_UNSETTLED,

    /**
     * 已收盘, 已结算
     */
    CLOSED_SETTLED

}
