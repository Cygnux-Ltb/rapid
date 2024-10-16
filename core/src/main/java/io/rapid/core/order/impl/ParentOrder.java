package io.rapid.core.order.impl;

import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.order.AbstractOrder;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import static io.cygnuxltb.console.beans.ValueLimitation.MAX_ACCOUNT_ID;
import static io.mercury.common.collections.MutableLists.newFastList;
import static io.rapid.core.order.attribute.OrdPrice.withOffer;
import static io.rapid.core.order.attribute.OrdQty.withOffer;

/**
 * TODO 暂时无用,
 * <p>
 * 一个实际需要执行的订单, 在具体执行时可以被拆分为多个子订单
 *
 * @author yellow013
 * @creation 2018-07-09
 */
public final class ParentOrder extends AbstractOrder {

    @java.io.Serial
    private static final long serialVersionUID = -5096106824571703291L;

    /**
     * 所属子订单
     */
    @Getter
    private final MutableList<ChildOrder> childOrders = newFastList(4);

    /**
     * @param strategyId     int
     * @param subAccountId   int
     * @param instrumentCode String
     * @param offerQty       int
     * @param offerPrice     double
     * @param type           OrdType
     * @param direction      TrdDirection
     */
    public ParentOrder(int strategyId, int subAccountId, String instrumentCode,
                       int offerQty, double offerPrice, OrdType type, TrdDirection direction) {
        super(strategyId, subAccountId, instrumentCode, MAX_ACCOUNT_ID,
                withOffer(offerQty), withOffer(offerPrice), type, direction);
    }

    /**
     * @param order ChildOrder
     */
    public void addChild(ChildOrder order) {
        this.childOrders.add(order);
    }

    @Override
    public boolean isChild() {
        return false;
    }

    private static final String LogTemplate = "Msg : {}, ParentOrder attr : ordSysId==[{}], "
            + "status==[{}], direction==[{}], type==[{}], "
            + "instrumentCode==[{}], price -> {}, qty -> {}, timestamp -> {}";

    @Override
    public void toLog(Logger log, String msg) {
        log.info(LogTemplate, msg, ordSysId, status, direction, type,
                instrument.getInstrumentCode(), price, qty, timestamp);
    }

}
