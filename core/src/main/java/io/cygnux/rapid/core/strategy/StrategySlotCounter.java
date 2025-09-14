package io.cygnux.rapid.core.strategy;

import java.util.concurrent.atomic.AtomicInteger;

public final class StrategySlotCounter {

    private StrategySlotCounter() {
    }

    private static final AtomicInteger SLOT_COUNTER = new AtomicInteger(1);


    public static int getCurrentValue() {
        return SLOT_COUNTER.get();
    }

    public static void setValue(int slotCount) {
        SLOT_COUNTER.set(slotCount);
    }

}
