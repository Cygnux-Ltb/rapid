package io.rapid.engine.order;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.event.enums.OrdStatus;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.Order;
import io.rapid.core.order.OrderBook;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.impl.ChildOrder;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

/**
 * 统一管理订单<br>
 * OrderBookManager只对订单进行存储<br>
 * 根据订单状态存储在不同的分区<br>
 * 不对订单进行处理<br>
 *
 * @author yellow013
 */

@NotThreadSafe
public final class OrderKeeper implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 8581377004396461013L;

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderKeeper.class);

    /**
     * 存储所有的order
     */
    private static final OrderBook ALL_ORDERS = new OrderBook(Capacity.L09_512);

    /**
     * 按照subAccountId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> SUB_ACCOUNT_ORDERS = MutableMaps.newIntObjectMap();

    /**
     * 按照accountId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> ACCOUNT_ORDERS = MutableMaps.newIntObjectMap();

    /**
     * 按照strategyId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> STRATEGY_ORDERS = MutableMaps.newIntObjectMap();

    /**
     * 按照instrumentId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> INSTRUMENT_ORDERS = MutableMaps.newIntObjectMap();

    private OrderKeeper() {
    }

    /**
     * @param ordSysId long
     * @return boolean
     */
    public static boolean isContainsOrder(long ordSysId) {
        return ALL_ORDERS.isContainsOrder(ordSysId);
    }

    /**
     * @param ordSysId long
     * @return Order
     */
    @Nullable
    public static Order getOrder(long ordSysId) {
        return ALL_ORDERS.getOrder(ordSysId);
    }

    /**
     * @param subAccountId int
     * @return OrderBook
     */
    public static OrderBook getSubAccountOrderBook(int subAccountId) {
        return SUB_ACCOUNT_ORDERS.getIfAbsentPut(subAccountId, OrderBook::new);
    }

    /**
     * @param accountId int
     * @return OrderBook
     */
    public static OrderBook getAccountOrderBook(int accountId) {
        return ACCOUNT_ORDERS.getIfAbsentPut(accountId, OrderBook::new);
    }

    /**
     * @param strategyId int
     * @return OrderBook
     */
    public static OrderBook getStrategyOrderBook(int strategyId) {
        return STRATEGY_ORDERS.getIfAbsentPut(strategyId, OrderBook::new);
    }

    /**
     * @param instrument Instrument
     * @return OrderBook
     */
    public static OrderBook getInstrumentOrderBook(Instrument instrument) {
        return INSTRUMENT_ORDERS.getIfAbsentPut(instrument.getInstrumentId(), OrderBook::new);
    }

    /**
     * 新增订单
     *
     * @param order Order
     */
    public static void putOrder(Order order) {
        int subAccountId = order.getSubAccountId();
        int accountId = order.getAccountId();
        ALL_ORDERS.putOrder(order);
        getSubAccountOrderBook(subAccountId).putOrder(order);
        getAccountOrderBook(accountId).putOrder(order);
        getStrategyOrderBook(order.getStrategyId()).putOrder(order);
        getInstrumentOrderBook(order.getInstrument()).putOrder(order);
    }


    /**
     * @param order Order
     */
    private static void updateOrder(ChildOrder order) {
        switch (order.getStatus()) {
            case FILLED, CANCELED, NEW_REJECTED, CANCEL_REJECTED -> {
                ALL_ORDERS.finishOrder(order);
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
    public static ChildOrder handleOrderReport(@Nonnull OrderReport report) {
        log.info("Handle OrderEvent, report -> {}", report);
        // 根据订单回报查找所属订单
        var order = getOrder(report.getOrdSysId());
        if (order == null) {
            // 处理订单由外部系统发出而收到报单回报的情况
            log.warn("Received other source order, ordSysId==[{}]", report.getOrdSysId());
            // 根据成交回报创建新订单, 放入OrderBook托管
            // 用于构建外部来源的新订单, 通常是根据系统未托管的订单回报构建, 此时需要传递订单当前状态
            ChildOrder childOrder = ChildOrder.newWithExternal(report.getAccountId(), report.getInstrumentCode(),
                    OrdQty.withOffer(report.getOfferQty()).addFilledQty(report.getFilledQty()),
                    OrdPrice.withOffer(report.getOfferPrice()),
                    report.getDirection(), report.getAction());
            childOrder.setBrokerRef0(report.getOrderRef());
            childOrder.setBrokerRef1(report.getBrokerOrdSysId());
            // 新订单放入OrderBook
            putOrder(childOrder);
            order = childOrder;
        } else {
            order.toLog(log, "OrderBookKeeper :: Search order OK");
        }
        ChildOrder childOrder = (ChildOrder) order;
        // 根据订单回报更新订单状态
        updateOrderWith(childOrder, report);
        // 更新Keeper内订单
        updateOrder(childOrder);
        return childOrder;
    }

    /**
     * 根据订单回报处理订单状态
     *
     * @param order  Order
     * @param report OrderReport
     */
    public static void updateOrderWith(@Nonnull ChildOrder order, @Nonnull OrderReport report) {
        var qty = order.getQty();
        int filledQty = report.getFilledQty();
        var status = report.getStatus();
        log.info("OrderReport status==[{}], filledQty==[{}], tradePrice==[{}], OrdQty -> {}",
                status, filledQty, report.getTradePrice(), qty);
        switch (status) {
            case UNPROVIDED, INVALID -> {
                // 处理未返回订单状态的情况, 根据成交数量判断
                int offerQty = qty.getOfferQty();
                order.setStatus(
                        // 成交数量等于委托数量, 状态为全部成交
                        filledQty == offerQty ? OrdStatus.FILLED
                                // 成交数量小于委托数量同时成交数量大于0, 状态为部分成交
                                : filledQty < offerQty && filledQty > 0 ? OrdStatus.PARTIALLY_FILLED
                                // 成交数量等于0, 状态为New
                                : OrdStatus.NEW);
            }
            default ->
                // 已返回订单状态, 直接读取
                    order.setStatus(status);
        }
        switch (order.getStatus()) {
            case PARTIALLY_FILLED -> {
                // 处理部分成交, 设置已成交数量
                // Set FilledQty
                order.getQty().setFilledQty(filledQty);
                // 新增订单成交记录
                // Add NewTrade record
                order.addRecord(report.getEpochMicros(), report.getTradePrice(),
                        filledQty - order.getQty().getLastFilledQty());
                log.info(
                        "ChildOrder current status PartiallyFilled, strategyId==[{}], ordSysId==[{}], "
                                + "filledQty==[{}], avgTradePrice==[{}]",
                        order.getStrategyId(), order.getOrdSysId(),
                        order.getQty().getFilledQty(),
                        order.getPrice().getAvgTradePrice());
            }
            case FILLED -> {
                // 处理全部成交, 设置已成交数量
                // Set FilledQty
                order.getQty().setFilledQty(filledQty);
                // 新增订单成交记录
                // Add NewTrade Record
                order.addRecord(report.getEpochMicros(), report.getTradePrice(),
                        filledQty - order.getQty().getLastFilledQty());
                // 计算此订单成交均价
                // Calculation AvgPrice
                double avgTradePrice = order.fillAndGetAvgTradePrice();
                log.info(
                        "ChildOrder current status Filled, strategyId==[{}], ordSysId==[{}], "
                                + "filledQty==[{}], avgTradePrice==[{}]",
                        order.getStrategyId(), order.getOrdSysId(), order.getQty().getFilledQty(), avgTradePrice);
            }
            default ->
                // 记录其他情况, 打印详细信息
                    log.warn("Order updateWithReport finish, switch in default, order status==[{}], "
                            + "order -> {}, report -> {}", order.getStatus(), order, report);
        }
    }

}
