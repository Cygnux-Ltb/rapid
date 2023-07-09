package io.cygnuxltb.jcts.core.mkd.api;

import io.horizon.market.data.api.MarketDataReplaceOrder;
import io.horizon.market.data.api.MarketDataSnapshot;

public interface Visitor<R, I> {

    default R visit(MarketDataNewOrder event, I input) {
        return null;
    }

    default R visit(MarketDataDeleteOrder event, I input) {
        return null;
    }

    default R visit(MarketDataReplaceOrder event, I input) {
        return null;
    }

    default R visit(MarketDataIncrement message, I input) {
        return null;
    }

    default R visit(MarketDataSnapshot message, I input) {
        return null;
    }

}
