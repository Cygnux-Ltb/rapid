package io.cygnux.rapid.core.position;

import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.order.Order;
import io.cygnux.rapid.core.event.enums.TrdDirection;

import static java.lang.Integer.compare;

public sealed interface Position extends Comparable<Position> permits PositionImpl {

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
     * @param direction TrdDirection
     * @return int
     */
    int getCurrentQty(TrdDirection direction);

    /**
     * 设置当前仓位
     *
     * @param direction TrdDirection
     * @param qty       int
     */
    void setCurrentQty(TrdDirection direction, int qty);

    /**
     * 获取净持仓
     *
     * @return int
     */
    int getNetQty();

    /**
     * 获取可用仓位
     *
     * @return int
     */
    int getTradableQty(TrdDirection direction);

    /**
     * 设置可用仓位
     *
     * @param qty int
     */
    void setTradableQty(TrdDirection direction, int qty);

    /**
     * 使用订单更新仓位
     *
     * @param order Order
     */
    void updateWith(Order order);

    @Override
    default int compareTo(Position o) {
        if (this.getAccountId() < o.getAccountId())
            return -1;
        else if (this.getAccountId() > o.getAccountId())
            return 1;
        else return compare(this.getInstrument().getInstrumentId(),
                    o.getInstrument().getInstrumentId());
    }

}
