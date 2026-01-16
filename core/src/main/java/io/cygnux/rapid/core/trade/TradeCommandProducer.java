package io.cygnux.rapid.core.trade;

import io.cygnux.rapid.core.types.position.Position;
import io.cygnux.rapid.core.types.trade.TradeCommand;

@FunctionalInterface
public interface TradeCommandProducer {

    TradeCommand toTradeCommand(int targetQty, Position position);

}
