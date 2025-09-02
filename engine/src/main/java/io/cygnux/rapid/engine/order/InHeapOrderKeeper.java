package io.cygnux.rapid.engine.order;

import io.cygnux.rapid.core.stream.event.OrderReport;
import io.cygnux.rapid.core.order.Order;
import io.cygnux.rapid.core.order.OrderBook;
import io.cygnux.rapid.core.order.OrderKeeper;
import io.cygnux.rapid.core.order.attr.OrdPrice;
import io.cygnux.rapid.core.order.attr.OrdQty;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.cygnux.rapid.core.order.impl.ChildOrder.newWithExternal;
import static io.cygnux.rapid.engine.order.OrderActuator.updateOrderWith;

/**
 * 统一管理订单<br>
 * OrderKeeper只对订单进行存储<br>
 * 根据订单状态存储在不同的分区<br>
 * 不对订单进行处理<br>
 *
 * @author yellow013
 */

@NotThreadSafe
@Component("inHeap")
public final class InHeapOrderKeeper implements OrderKeeper {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InHeapOrderKeeper.class);

    /**
     * 存储所有的order
     */
    private final OrderBook allOrders = new OrderBook(Capacity.HEX_2_000);

    /**
     * 按照subAccountId分组存储
     */
    private final MutableIntObjectMap<OrderBook> subAccountOrders = MutableMaps.newIntObjectMap();

    /**
     * 按照accountId分组存储
     */
    private final MutableIntObjectMap<OrderBook> accountOrders = MutableMaps.newIntObjectMap();

    /**
     * 按照strategyId分组存储
     */
    private final MutableIntObjectMap<OrderBook> strategyOrders = MutableMaps.newIntObjectMap();

    /**
     * 按照instrumentId分组存储
     */
    private final MutableIntObjectMap<OrderBook> instrumentOrders = MutableMaps.newIntObjectMap();

    private InHeapOrderKeeper() {
    }

    /**
     * @param ordSysId long
     * @return boolean
     */
    @Override
    public boolean isExists(long ordSysId) {
        return allOrders.isExists(ordSysId);
    }

    /**
     * @param ordSysId long
     * @return Order
     */
    @Nullable
    @Override
    public Order getOrder(long ordSysId) {
        return allOrders.getOrder(ordSysId);
    }

    /**
     * @param subAccountId int
     * @return OrderBook
     */
    @Override
    public OrderBook getSubAccountOrderBook(int subAccountId) {
        return subAccountOrders.getIfAbsentPut(subAccountId, () -> new OrderBook(Capacity.HEX_100));
    }

    /**
     * @param accountId int
     * @return OrderBook
     */
    @Override
    public OrderBook getAccountOrderBook(int accountId) {
        return accountOrders.getIfAbsentPut(accountId, () -> new OrderBook(Capacity.HEX_100));
    }

    /**
     * @param strategyId int
     * @return OrderBook
     */
    @Override
    public OrderBook getStrategyOrderBook(int strategyId) {
        return strategyOrders.getIfAbsentPut(strategyId, () -> new OrderBook(Capacity.HEX_200));
    }

    /**
     * @param instrumentId int
     * @return OrderBook
     */
    @Override
    public OrderBook getInstrumentOrderBook(int instrumentId) {
        return instrumentOrders.getIfAbsentPut(instrumentId, () -> new OrderBook(Capacity.HEX_200));
    }

    /**
     * 新增订单
     *
     * @param order Order
     */
    @Override
    public void putOrder(@Nonnull Order order) {
        int subAccountId = order.getSubAccountId();
        int accountId = order.getAccountId();
        allOrders.putOrder(order);
        getSubAccountOrderBook(subAccountId).putOrder(order);
        getAccountOrderBook(accountId).putOrder(order);
        getStrategyOrderBook(order.getStrategyId()).putOrder(order);
        getInstrumentOrderBook(order.getInstrument()).putOrder(order);
    }


    /**
     * @param order Order
     */
    private void updateOrder(ChildOrder order) {
        switch (order.getStatus()) {
            case FILLED, CANCELED, NEW_REJECTED, CANCEL_REJECTED -> {
                allOrders.finishOrder(order);
                getSubAccountOrderBook(order.getSubAccountId()).finishOrder(order);
                getAccountOrderBook(order.getAccountId()).finishOrder(order);
                getStrategyOrderBook(order.getStrategyId()).finishOrder(order);
                getInstrumentOrderBook(order.getInstrument()).finishOrder(order);
            }
            default ->
                    log.info("Not need processed, strategyId==[{}], ordSysId==[{}], status==[{}]", order.getStrategyId(),
                            order.getOrdSysId(), order.getStatus());
        }
    }

    /**
     * 处理订单回报
     *
     * @param report OrderReport
     * @return ChildOrder
     */
    @Override
    public ChildOrder onOrderReport(@Nonnull OrderReport report) {
        log.info("Handle OrderEvent, report -> {}", report);
        // 根据订单回报查找所属订单
        var order = getOrder(report.getOrdSysId());
        if (order == null) {
            // 处理订单由外部系统发出而收到报单回报的情况
            log.warn("Received other source order, ordSysId==[{}]", report.getOrdSysId());
            // 根据成交回报创建新订单, 放入OrderBook托管
            // 用于构建外部来源的新订单, 通常是根据系统未托管的订单回报构建, 此时需要传递订单当前状态
            ChildOrder childOrder = newWithExternal(report.getAccountId(), report.getInstrumentCode(),
                    OrdQty.withOffer(report.getOfferQty()).addFilledQty(report.getFilledQty()),
                    OrdPrice.withOffer(report.getOfferPrice()), report.getDirection(), report.getAction());
            childOrder.setBrokerRef0(report.getOrderRef());
            childOrder.setBrokerRef1(report.getBrokerOrdSysId());
            // 新订单放入OrderBook
            putOrder(childOrder);
            order = childOrder;
        } else {
            order.logging(log, "OrderBookKeeper :: Search order OK");
        }
        ChildOrder childOrder = (ChildOrder) order;
        // 根据订单回报更新订单状态
        updateOrderWith(childOrder, report);
        // 更新Keeper内订单
        updateOrder(childOrder);
        return childOrder;
    }


}
