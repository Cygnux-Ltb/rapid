package io.cygnux.rapid.core.trade;

import io.mercury.common.sequence.OrderedObject;
import io.cygnux.rapid.core.stream.enums.TrdDirection;
import io.cygnux.rapid.core.stream.event.FastMarketData;
import io.cygnux.rapid.core.order.impl.ChildOrder;

public class StopLoss implements OrderedObject<StopLoss> {

    /**
     * ordSysId
     */
    private final long ordSysId;

    /**
     * direction
     */
    private final TrdDirection direction;

    /**
     * 止损价
     */
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

    public long getOrdSysId() {
        return ordSysId;
    }

    public TrdDirection getDirection() {
        return direction;
    }

    public double getStopPrice() {
        return stopPrice;
    }

    public StopLoss setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
        return this;
    }

}
