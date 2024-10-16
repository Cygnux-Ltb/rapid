package io.rapid.core.order;

import io.rapid.core.position.Position;

@FunctionalInterface
public interface TradeCommandProducer {

    TradeCommand toTradeCommand(int targetQty, Position position);

}
