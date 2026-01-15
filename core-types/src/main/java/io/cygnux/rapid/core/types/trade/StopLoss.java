package io.cygnux.rapid.core.types.trade;

import io.cygnux.rapid.core.types.event.received.FastMarketData;
import io.cygnux.rapid.core.types.order.impl.ChildOrder;
import io.cygnux.rapid.core.types.trade.enums.TrdDirection;
import io.mercury.common.sequence.OrderedObject;
import lombok.Getter;

public class StopLoss implements OrderedObject<StopLoss> {

    /**
     * ordSysId
     */
    @Getter
    private final long ordSysId;

    /**
     * direction
     */
    @Getter
    private final TrdDirection direction;

    /**
     * 止损价
     */
    @Getter
    private double stopPrice;

    /**
     * @param order ChildOrder
     */
    public StopLoss(ChildOrder order) {
        this.ordSysId = order.getOrdSysId();
        this.direction = order.getDirection();
    }

    /**
     * @param order      ChildOrder
     * @param offsetTick int
     */
    public StopLoss(ChildOrder order, int offsetTick) {
        this.ordSysId = order.getOrdSysId();
        this.direction = order.getDirection();
        switch (direction) {
            case LONG -> stopPrice = order.getPrice().getAvgTradePrice() - offsetTick;
            case SHORT -> stopPrice = order.getPrice().getAvgTradePrice() + offsetTick;
            default -> throw new IllegalStateException(
                    "direction error -> direction == [" + direction + "]");
        }
    }

    public StopLoss(long ordSysId, TrdDirection direction, double stopPrice) {
        this.ordSysId = ordSysId;
        this.direction = direction;
        this.stopPrice = stopPrice;
    }

    public StopLoss(long ordSysId, TrdDirection direction) {
        this.ordSysId = ordSysId;
        this.direction = direction;
        switch (direction) {
            case LONG -> stopPrice = Long.MIN_VALUE;
            case SHORT -> stopPrice = Long.MAX_VALUE;
            default -> throw new IllegalStateException(
                    "direction error -> direction == [" + direction + "]");
        }
    }

    @Override
    public long orderNum() {
        return ordSysId;
    }

    public final boolean isStopLoss(FastMarketData marketData) {
        return switch (direction) {
            case LONG -> stopPrice < marketData.getAskPrice1();
            case SHORT -> stopPrice > marketData.getBidPrice1();
            default -> throw new IllegalStateException(
                    "direction error -> direction == [" + direction + "]");
        };
    }

    public StopLoss setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
        return this;
    }

}
