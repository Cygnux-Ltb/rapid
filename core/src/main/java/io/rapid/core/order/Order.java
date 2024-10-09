package io.rapid.core.order;

import io.mercury.common.sequence.SerialObject;
import io.rapid.core.event.enums.OrdStatus;
import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.OrdValid;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.attribute.OrdRemark;
import io.rapid.core.order.attribute.OrdTimestamp;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static java.lang.Long.compare;

/**
 * @author yellow013
 */
public sealed interface Order extends SerialObject<Order>, Serializable permits AbstractOrder {

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
    String getInstrumentCode();

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
     * @param type OrdType
     */
    void setType(OrdType type);

    /**
     * @param valid OrdValid
     */
    void setValid(OrdValid valid);

    /**
     * @param status Order
     */
    void setStatus(OrdStatus status);

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
     * @param log Logger
     * @param msg String
     */
    default void toLog(Logger log, String msg) {
        log.info("{}, Order attribute : ordSysId==[{}], status==[{}], direction==[{}], type==[{}], " +
                        "instrumentCode==[{}], price -> {}, qty -> {}, timestamp -> {}, remark -> {}",
                msg, getOrdSysId(), getStatus(), getDirection(), getType(), getInstrumentCode(),
                getPrice(), getQty(), getTimestamp(), getRemark());
    }

    @Override
    default long serialId() {
        return getOrdSysId();
    }

    @Override
    default int compareTo(Order o) {
        return getOrdLevel() > o.getOrdLevel() ? -1
                : getOrdLevel() < o.getOrdLevel() ? 1
                : compare(getOrdSysId(), o.getOrdSysId());
    }

}
