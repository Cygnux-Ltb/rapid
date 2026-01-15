package io.cygnux.rapid.core.strategy;

import java.util.concurrent.atomic.AtomicInteger;

public final class StrategySlotCounter {

    private StrategySlotCounter() {
    }

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static int getCurrentCount() {
        return COUNTER.get();
    }

    public static int incr() {
        return COUNTER.incrementAndGet();
    }

    public static int decr() {
        return COUNTER.decrementAndGet();
    }

}
