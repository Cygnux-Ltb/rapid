package io.cygnux.rapid.core.types.position;

import io.cygnux.rapid.core.types.trade.enums.TrdDirection;
import io.cygnux.rapid.core.types.field.AccountID;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.order.Order;
import lombok.Getter;

/**
 * 持仓对象基础实现
 *
 * @author yellow013
 */
public non-sealed class PositionImpl implements Position {

    @Getter
    private final int accountId;

    @Getter
    private final Instrument instrument;

    private int currentLongQty;

    private int currentShortQty;

    protected PositionImpl(AccountID accountId, Instrument instrument) {
        this.accountId = accountId.value();
        this.instrument = instrument;
    }

    /**
     * 获取当前仓位
     *
     * @param direction TrdDirection
     * @return int
     */
    @Override
    public int getCurrentQty(TrdDirection direction) {
        return switch (direction) {
            case LONG -> currentLongQty;
            case SHORT -> currentShortQty;
            default -> 0;
        };
    }

    /**
     * 设置当前仓位
     *
     * @param direction TrdDirection
     * @param qty       int
     */
    @Override
    public void setCurrentQty(TrdDirection direction, int qty) {
        switch (direction) {
            case LONG -> currentLongQty = qty;
            case SHORT -> currentShortQty = qty;
            default -> {
            }
        }
    }

    /**
     * 获取净持仓
     *
     * @return int
     */
    @Override
    public int getNetQty() {
        return currentLongQty - currentShortQty;
    }

    /**
     * 获取可用仓位
     *
     * @param direction TrdDirection
     * @return int
     */
    @Override
    public int getTradableQty(TrdDirection direction) {
        return getCurrentQty(direction);
    }

    /**
     * 设置可用仓位
     *
     * @param direction TrdDirection
     * @param qty       int
     */
    @Override
    public void setTradableQty(TrdDirection direction, int qty) {
        setCurrentQty(direction, qty);
    }

    /**
     * 使用订单更新仓位
     *
     * @param order Order
     */
    @Override
    public void updateWith(Order order) {

    }

}
