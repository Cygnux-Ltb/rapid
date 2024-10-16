package io.rapid.core.trade;

import io.rapid.core.position.Position;

@FunctionalInterface
public interface TradeCommandProducer {

    TradeCommand toTradeCommand(int targetQty, Position position);

}
