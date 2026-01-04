package io.cygnux.rapid.core.order;

import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.order.attr.OrdPrice;
import io.cygnux.rapid.core.order.attr.OrdQty;
import io.cygnux.rapid.core.order.attr.OrdRemark;
import io.cygnux.rapid.core.order.attr.OrdTimestamp;
import io.cygnux.rapid.core.event.enums.OrdStatus;
import io.cygnux.rapid.core.event.enums.OrdType;
import io.cygnux.rapid.core.event.enums.OrdValid;
import io.cygnux.rapid.core.event.enums.TrdDirection;
import io.mercury.common.sequence.OrderedObject;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 */
public sealed interface Order extends OrderedObject<Order> permits AbstractOrder {

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
     * @return String
     */
    String getSignalSource();

    /**
     * @param signalSource String
     */
    void setSignalSource(String signalSource);

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
     * 是否为子订单
     *
     * @return boolean
     */
    boolean isChild();

    /**
     * @param log Logger
     * @param msg String
     */
    default void logging(Logger log, String msg) {
        log.info("""
                        {}, Order attr : ordSysId==[{}], status==[{}], direction==[{}], type==[{}],
                        "instrumentCode==[{}], price -> {}, qty -> {}, timestamp -> {}, remark -> {}
                        """,
                msg, getOrdSysId(), getStatus(), getDirection(), getType(),
                getInstrument().getInstrumentCode(), getPrice(), getQty(), getTimestamp(), getRemark());
    }

    @Override
    default long orderNum() {
        return getOrdSysId();
    }

}
