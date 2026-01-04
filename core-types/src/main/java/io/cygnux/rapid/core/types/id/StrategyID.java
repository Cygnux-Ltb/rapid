package io.cygnux.rapid.core.types.id;

import org.slf4j.Logger;

import static io.mercury.common.lang.Validator.atWithinRange;

public record StrategyID(
        int value
) {

    public StrategyID {
        checkStrategyId(value);
    }

    /**
     * 系统可允许的最小策略ID (雪花算法ID)
     */
    public static final int MIN_STRATEGY_ID = 1;

    /**
     * 系统可允许的最大策略ID (雪花算法ID)
     */
    public static final int MAX_STRATEGY_ID = 1023;

    /**
     * @param strategyId int
     */
    public static void checkStrategyId(int strategyId) {
        checkStrategyId(strategyId, null);
    }

    /**
     * @param strategyId int
     * @param logger     Logger
     */
    public static void checkStrategyId(int strategyId, Logger logger) {
        atWithinRange(strategyId, MIN_STRATEGY_ID, MAX_STRATEGY_ID, "strategyId", logger);
    }

}
