package io.rapid.adaptor.ctp.gateway;

import java.util.concurrent.atomic.AtomicInteger;

public final class RequestIdAllocator {

    private final AtomicInteger innerCounter = new AtomicInteger(0);

    private static final RequestIdAllocator INSTANCE = new RequestIdAllocator();

    private RequestIdAllocator() {
    }

    public static int allocate() {
        return INSTANCE.innerCounter.incrementAndGet();
    }

}
