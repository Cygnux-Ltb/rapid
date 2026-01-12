package io.cygnux.rapid.core.types;

import org.slf4j.Logger;

public record TradingDay(
        int value
) {

    public static final int MIN_TRADING_DAY = 20000101;

    /**
     * @param tradingDay int
     * @param log        Logger
     * @return boolean
     */
    public static boolean illegalTradingDay(int tradingDay, Logger log) {
        if (tradingDay < MIN_TRADING_DAY) {
            log.error("illegal param -> tradingDay=={}", tradingDay);
            return true;
        }
        return false;
    }

    /**
     * @param startTradingDay int
     * @param endTradingDay   int
     * @param log             Logger
     * @return boolean
     */
    public static boolean illegalTradingDay(int startTradingDay, int endTradingDay, Logger log) {
        if (startTradingDay < MIN_TRADING_DAY || endTradingDay < startTradingDay) {
            log.error("illegal param -> startTradingDay=={}, endTradingDay=={}",
                    startTradingDay, endTradingDay);
            return true;
        }
        return false;
    }

}
