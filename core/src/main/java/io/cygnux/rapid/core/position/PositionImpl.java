package io.cygnux.rapid.core.position;


import io.cygnux.rapid.core.stream.enums.TrdDirection;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.order.Order;

import java.io.Serial;

/**
 * 持仓对象基础实现
 *
 * @author yellow013
 */
public non-sealed class PositionImpl implements Position {

    @Serial
    private static final long serialVersionUID = 7464979857942714749L;

    private final int accountId;

    private final Instrument instrument;

    private int currentLongQty;

    private int currentShortQty;

    protected PositionImpl(int accountId, Instrument instrument) {
        this.accountId = accountId;
        this.instrument = instrument;
    }

    /**
     * @return int
     */
    @Override
    public int getAccountId() {
        return accountId;
    }

    /**
     * @return Instrument
     */
    @Override
    public Instrument getInstrument() {
        return instrument;
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
