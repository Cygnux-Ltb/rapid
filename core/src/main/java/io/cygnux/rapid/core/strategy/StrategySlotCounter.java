package io.cygnux.rapid.core.strategy;

import java.util.concurrent.atomic.AtomicInteger;

public final class StrategySlotCounter {

    private StrategySlotCounter() {
    }

    private static final AtomicInteger COUNT = new AtomicInteger(1);

    public static int getCurrentCount() {
        return COUNT.get();
    }

    public static void setCount(int count) {
        COUNT.set(count);
    }

    public static int incr() {
        return COUNT.incrementAndGet();
    }

    public static int decr() {
        return COUNT.decrementAndGet();
    }

}
