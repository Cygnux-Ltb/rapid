package io.cygnuxltb.jcts.core.mkd.copy;

import io.cygnuxltb.jcts.core.mkd.copy.MarketDataDeleteOrder;
import io.cygnuxltb.jcts.core.mkd.copy.MarketDataIncrement;
import io.cygnuxltb.jcts.core.mkd.copy.MarketDataNewOrder;
import io.cygnuxltb.jcts.core.mkd.copy.MarketDataReplaceOrder;
import io.cygnuxltb.jcts.core.mkd.copy.MarketDataSnapshot;

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
