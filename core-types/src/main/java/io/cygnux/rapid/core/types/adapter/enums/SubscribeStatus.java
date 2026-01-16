package io.cygnux.rapid.core.types.adapter.enums;

/**
 * 行情订阅状态
 */
public enum SubscribeStatus {

    /**
     * 未提供
     */
    NOT_PROVIDED,

    /**
     * 不可订阅
     */
    SUBSCRIPTION_UNAVAILABLE,

    /**
     * 可订阅
     */
    SUBSCRIPTION_AVAILABLE,

    /**
     * 已经订阅
     */
    SUBSCRIBED,

    /**
     * 订阅失败
     */
    SUBSCRIPTION_FAILED

}
