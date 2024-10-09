package io.rapid.core.risk;

import io.rapid.core.order.impl.Order;

import java.util.function.Predicate;

@FunctionalInterface
public interface OrderBarrier extends Predicate<Order> {

    boolean filter(Order order);

    @Override
    default boolean test(Order t) {
        return filter(t);
    }

}
