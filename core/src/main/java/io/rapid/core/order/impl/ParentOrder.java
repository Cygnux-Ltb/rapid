package io.rapid.core.order;

import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import java.io.Serial;
import java.util.List;
import java.util.function.Function;

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

    @Serial
    private static final long serialVersionUID = -5096106824571703291L;

    /**
     * 所属子订单
     */
    @Getter
    private final MutableList<ChildOrder> childOrders = newFastList(4);

    /**
     * @param strategyId     int
     * @param subAccountId   int
     * @param accountId      int
     * @param instrumentCode String
     * @param qty            OrdQty
     * @param price          OrdPrice
     * @param type           OrdType
     * @param direction      TrdDirection
     */
    public ParentOrder(int strategyId, int subAccountId, int accountId,
                       String instrumentCode, OrdQty qty, OrdPrice price, OrdType type,
                       TrdDirection direction) {
        super(strategyId, subAccountId, accountId, instrumentCode, qty, price, type, direction);
    }


    /**
     * 由外部传入拆分为多个订单的逻辑
     *
     * @param splitFunc Function<ParentOrder, Collection<ChildOrder>>
     * @return MutableList<ChildOrder>
     */
    public MutableList<ChildOrder> splitChildOrder(Function<ParentOrder, List<ChildOrder>> splitFunc) {
        this.childOrders.addAll(splitFunc.apply(this));
        return this.childOrders;
    }


    @Override
    public int getOrdLevel() {
        return 1;
    }

    private static final String LogTemplate = "Msg : {}, ParentOrder attr : ordSysId==[{}], "
            + "status==[{}], direction==[{}], action==[{}], type==[{}], "
            + "instrumentCode==[{}], price -> {}, qty -> {}, timestamp -> {}";

    @Override
    public void toLog(Logger log, String msg) {
        log.info(LogTemplate, msg, ordSysId, status, direction, null, type, instrumentCode, price, qty, timestamp);
    }

}
