package io.rapid.core.strategy;

import java.util.function.Predicate;

public interface StrategyRule<T> extends Predicate<T> {

    default boolean and(T t, StrategyRule<T> rule0, StrategyRule<T> rule1) {
        return rule0.test(t) && rule1.test(t);
    }

    default <T0, T1> boolean and(T0 t0, T1 t1, StrategyRule<T0> rule0, StrategyRule<T1> rule1) {
        return rule0.test(t0) && rule1.test(t1);
    }

    default boolean or(T t, StrategyRule<T> rule0, StrategyRule<T> rule1) {
        return rule0.test(t) || rule1.test(t);
    }

    default <T0, T1> boolean or(T0 t0, T1 t1, StrategyRule<T0> rule0, StrategyRule<T1> rule1) {
        return rule0.test(t0) || rule1.test(t1);
    }

}
