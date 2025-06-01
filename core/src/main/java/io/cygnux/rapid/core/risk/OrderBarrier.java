package io.cygnux.rapid.core.risk;

import io.cygnux.rapid.core.order.impl.ChildOrder;

import java.util.function.Predicate;

@FunctionalInterface
public interface OrderBarrier extends Predicate<ChildOrder> {

    boolean filter(ChildOrder order);

    @Override
    default boolean test(ChildOrder t) {
        return filter(t);
    }

}
