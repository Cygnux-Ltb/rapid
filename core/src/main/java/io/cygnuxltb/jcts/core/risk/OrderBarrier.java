package io.cygnuxltb.jcts.core.risk;

import io.cygnuxltb.jcts.core.order.ChildOrder;

import java.util.function.Predicate;

@FunctionalInterface
public interface OrderBarrier extends Predicate<ChildOrder> {

    boolean filter(ChildOrder order);

    @Override
    default boolean test(ChildOrder t) {
        return filter(t);
    }

}
