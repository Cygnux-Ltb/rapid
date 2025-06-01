package io.cygnux.rapid.core.strategy;

import java.io.Serial;

public class StrategyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8145540141394714301L;

    public StrategyException(int strategyId, String message) {
        super("Strategy id -> [" + strategyId + "] throw StrategyException, message -> [" + message + "]");
    }

    public StrategyException(int strategyId, Throwable throwable) {
        super("Strategy id -> [" + strategyId + "] throw StrategyException", throwable);
    }

}
