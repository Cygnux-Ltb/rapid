package io.cygnux.rapid.gateway.ctp;

import java.util.concurrent.atomic.AtomicInteger;

public final class RequestIdAllocator {

    private final AtomicInteger counter = new AtomicInteger(0);

    private static final RequestIdAllocator INSTANCE = new RequestIdAllocator();

    private RequestIdAllocator() {
    }

    public static int allocate() {
        return INSTANCE.counter.incrementAndGet();
    }

}
