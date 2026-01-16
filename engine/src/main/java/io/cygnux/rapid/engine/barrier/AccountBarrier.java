package io.cygnux.rapid.engine.barrier;

import io.cygnux.rapid.core.risk.OrderBarrier;
import io.cygnux.rapid.core.types.order.impl.ChildOrder;

import javax.annotation.concurrent.NotThreadSafe;

/**
 *
 */
@NotThreadSafe
public class AccountBarrier implements OrderBarrier {

    @Override
    public boolean filter(ChildOrder order) {
        return false;
    }

}
