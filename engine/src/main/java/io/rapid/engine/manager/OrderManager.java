package io.rapid.engine.manager;

import io.rapid.core.strategy.Strategy;
import io.mercury.common.collections.MutableLists;
import org.eclipse.collections.api.list.MutableList;

public final class OrderManager {

    MutableList<Strategy> strategies = MutableLists.newFastList(8);

    private OrderManager() {
    }


}
