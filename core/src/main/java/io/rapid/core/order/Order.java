package io.rapid.core.order;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.attr.OrdPrice;
import io.rapid.core.order.attr.OrdQty;
import io.rapid.core.order.attr.OrdRemark;
import io.rapid.core.order.attr.OrdTimestamp;
import io.rapid.core.order.enums.OrdStatus;
import io.rapid.core.order.enums.OrdType;
import io.rapid.core.order.enums.OrdValid;
import io.rapid.core.order.enums.TrdDirection;
import io.mercury.common.sequence.Serial;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * @author yellow013
 */
public interface Order extends Serial<Order>, Serializable {

    /**
     * ordSysId构成, 使用雪花算法实现<br>
     * <br>
     * 策略Id | 时间戳Second | 自增量Number<br>
     * strategyId | epochSecond| increment<br>
     *
     * @return long
     */
    long getOrdSysId();

    /**
     * strategyId
     *
     * @return int
     */
    int getStrategyId();

    /**
     * subAccountId
     *
     * @return int
     */
    int getSubAccountId();

    /**
     * accountId
     *
     * @return int
     */
    int getAccountId();

    /**
     * instrument
     *
     * @return Instrument
     */
    Instrument getInstrument();

    /**
     * OrdQty
     *
     * @return OrdQty
     */
    OrdQty getQty();

    /**
     * OrdPrice
     *
     * @return OrdPrice
     */
    OrdPrice getPrice();

    /**
     * OrdType
     *
     * @return OrdType
     */
    OrdType getType();

    /**
     * OrdValid
     *
     * @return OrdValid
     */
    OrdValid getValid();

    /**
     * TrdDirection
     *
     * @return TrdDirection
     */
    TrdDirection getDirection();

    /**
     * OrdTimestamp
     *
     * @return OrdTimestamp
     */
    OrdTimestamp getTimestamp();

    /**
     * OrdStatus
     *
     * @return current status
     */
    OrdStatus getStatus();

    /**
     * @param status Order
     */
    Order setStatus(@Nonnull OrdStatus status);

    /**
     * remark
     *
     * @return OrdRemark
     */
    OrdRemark getRemark();

    /**
     * @param remark String
     */
    default void addRemark(@Nonnull String remark) {
        getRemark().add(remark);
    }

    /**
     * @return int
     */
    int getOrdLevel();

    /**
     * @param logger Logger
     * @param msg    String
     */
    void printLog(Logger logger, String msg);

    @Override
    default long serialId() {
        return getOrdSysId();
    }

    @Override
    default int compareTo(Order o) {
        return getOrdLevel() > o.getOrdLevel() ? -1
                : getOrdLevel() < o.getOrdLevel() ? 1
                : Long.compare(getOrdSysId(), o.getOrdSysId());
    }

    /**
     * OrdStatusException
     *
     * @author yellow013
     */
    class OrdStatusException extends RuntimeException {

        @java.io.Serial
        private static final long serialVersionUID = -4772495541311633988L;

        public OrdStatusException(String message) {
            super(message);
        }

    }

}
