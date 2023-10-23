package io.rapid.core.risk;

import io.rapid.core.order.ChildOrder;

import java.util.function.Predicate;

@FunctionalInterface
public interface OrderBarrier extends Predicate<ChildOrder> {

    boolean filter(ChildOrder order);

    @Override
    default boolean test(ChildOrder t) {
        return filter(t);
    }

}
