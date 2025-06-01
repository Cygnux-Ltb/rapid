package io.cygnux.rapid.core.trade;

import io.cygnux.rapid.core.position.Position;

@FunctionalInterface
public interface TradeCommandProducer {

    TradeCommand toTradeCommand(int targetQty, Position position);

}
