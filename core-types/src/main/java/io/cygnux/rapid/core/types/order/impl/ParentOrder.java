package io.cygnux.rapid.core.types.order.impl;

import io.cygnux.rapid.core.types.order.enums.OrdType;
import io.cygnux.rapid.core.types.trade.enums.TrdDirection;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.order.AbstractOrder;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MAX_ACCOUNT_ID;
import static io.cygnux.rapid.core.types.order.attr.OrdPrice.withOffer;
import static io.cygnux.rapid.core.types.order.attr.OrdQty.withOffer;
import static io.mercury.common.collections.MutableLists.newFastList;

/**
 * TODO 暂时无用,
 * <p>
 * 一个实际需要执行的订单, 在具体执行时可以被拆分为多个子订单
 *
 * @author yellow013
 * @creation 2018-07-09
 */
public final class ParentOrder extends AbstractOrder {

    private static final long serialVersionUID = -5096106824571703291L;

    /**
     * 所属子订单
     */
    @Getter
    private final MutableList<ChildOrder> childOrders = newFastList(4);

    /**
     * @param ordSysId     long
     * @param strategyId   int
     * @param subAccountId int
     * @param instrument   Instrument
     * @param offerQty     int
     * @param offerPrice   double
     * @param type         OrdType
     * @param direction    TrdDirection
     */
    public ParentOrder(long ordSysId, int strategyId, int subAccountId, Instrument instrument,
                       int offerQty, double offerPrice, OrdType type, TrdDirection direction) {
        super(ordSysId, strategyId, subAccountId, instrument, MAX_ACCOUNT_ID,
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
    public void logging(Logger log, String msg) {
        log.info(LogTemplate, msg, ordSysId, status, direction, type,
                instrument.getInstrumentCode(), price, qty, timestamp);
    }

}
