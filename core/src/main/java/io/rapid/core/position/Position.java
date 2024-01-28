package io.rapid.core.position;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.Order;

import java.io.Serializable;

import static java.lang.Integer.compare;

public interface Position extends Comparable<Position>, Serializable {

    /**
     * 投资者账户ID
     *
     * @return int
     */
    int getAccountId();

    /**
     * 获取[Instrument]
     *
     * @return Instrument
     */
    Instrument getInstrument();

    /**
     * 获取[Instrument ID]
     *
     * @return int
     */
    default int getInstrumentId() {
        return getInstrument().getInstrumentId();
    }

    /**
     * 获取[Instrument Code]
     *
     * @return String
     */
    default String getInstrumentCode() {
        return getInstrument().getInstrumentCode();
    }

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
                : compare(this.getInstrument().getInstrumentId(),
                o.getInstrument().getInstrumentId());
    }

}
