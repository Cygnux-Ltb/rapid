package io.cygnuxltb.jcts.core.position;

import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.order.Order;

import java.io.Serializable;

public interface Position extends Comparable<Position>, Serializable {

    /**
     * 投资者账户ID
     *
     * @return int
     */
    int getAccountId();

    /**
     * 获取Instrument
     *
     * @return Instrument
     */
    Instrument getInstrument();

    /**
     * 获取当前仓位
     *
     * @return int
     */
    int getCurrentQty();

    /**
     * 设置当前仓位
     *
     * @param qty int
     */
    void setCurrentQty(int qty);

    /**
     * 获取可用仓位
     *
     * @return int
     */
    int getTradableQty();

    /**
     * 设置可用仓位
     *
     * @param qty int
     */
    void setTradableQty(int qty);

    /**
     * 使用订单更新仓位
     *
     * @param order Order
     */
    void updateWithOrder(Order order);

    @Override
    default int compareTo(Position o) {
        return this.getAccountId() < o.getAccountId() ? -1
                : this.getAccountId() > o.getAccountId() ? 1
                : Integer.compare(this.getInstrument().getInstrumentId(),
                o.getInstrument().getInstrumentId());
    }

}
