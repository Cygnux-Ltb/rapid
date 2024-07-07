package io.rapid.engine.trader;

import io.mercury.common.collections.MutableMaps;
import io.rapid.core.account.Account;
import io.rapid.core.account.SubAccount;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.BasicMarketData;
import io.rapid.core.order.ChildOrder;
import io.rapid.core.order.OrdSysIdAllocator;
import io.rapid.core.order.Order;
import io.rapid.core.order.enums.OrdType;
import io.rapid.core.order.enums.TrdAction;
import io.rapid.core.order.enums.TrdDirection;
import io.rapid.core.serializable.avro.event.OrderEvent;
import io.mercury.common.collections.Capacity;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;

import static io.mercury.common.collections.MutableMaps.newIntObjectMap;

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

    @Serial
    private static final long serialVersionUID = 8581377004396461013L;

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderKeeper.class);

    /*
     * 存储所有的order
     */
    private static final OrderBook AllOrders = new OrderBook(Capacity.L09_512);

    /*
     * 按照subAccountId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> SubAccountOrders = MutableMaps.newIntObjectMap();

    /*
     * 按照accountId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> AccountOrders = MutableMaps.newIntObjectMap();

    /*
     * 按照strategyId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> StrategyOrders = MutableMaps.newIntObjectMap();

    /*
     * 按照instrumentId分组存储
     */
    private static final MutableIntObjectMap<OrderBook> InstrumentOrders = MutableMaps.newIntObjectMap();

    private OrderKeeper() {
    }

    /**
     * 新增订单
     *
     * @param order Order
     */
    private static void putOrder(Order order) {
        int subAccountId = order.getSubAccountId();
        int accountId = order.getAccountId();
        AllOrders.putOrder(order);
        getSubAccountOrderBook(subAccountId).putOrder(order);
        getAccountOrderBook(accountId).putOrder(order);
        getStrategyOrderBook(order.getStrategyId()).putOrder(order);
        getInstrumentOrderBook(order.getInstrument()).putOrder(order);
    }

    /**
     * @param order Order
     */
    private static void updateOrder(Order order) {
        switch (order.getStatus()) {
            case Filled, Canceled, NewRejected, CancelRejected -> {
                AllOrders.finishOrder(order);
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
     * @param event AvOrderEvent
     * @return ChildOrder
     */
    public static ChildOrder handleOrderReport(OrderEvent event) {
        log.info("Handle OrderEvent, event -> {}", event);
        // 根据订单回报查找所属订单
        Order order = getOrder(event.getOrdSysId());
        if (order == null) {
            // 处理订单由外部系统发出而收到报单回报的情况
            log.warn("Received other source order, ordSysId==[{}]", event.getOrdSysId());
            // 根据成交回报创建新订单, 放入OrderBook托管
            order = ChildOrder.newExternalOrder(event);
            // 新订单放入OrderBook
            putOrder(order);
        } else
            order.printLog(log, "OrderBookKeeper :: Search order OK");
        ChildOrder childOrder = (ChildOrder) order;
        // 根据订单回报更新订单状态
        OrderUpdater.updateOrder(childOrder, event);
        // 更新Keeper内订单
        updateOrder(childOrder);
        return childOrder;
    }

    /**
     * @param ordSysId long
     * @return boolean
     */
    public static boolean isContainsOrder(long ordSysId) {
        return AllOrders.isContainsOrder(ordSysId);
    }

    /**
     * @param ordSysId long
     * @return Order
     */
    @Nullable
    public static Order getOrder(long ordSysId) {
        return AllOrders.getOrder(ordSysId);
    }

    /**
     * @param subAccountId int
     * @return OrderBook
     */
    public static OrderBook getSubAccountOrderBook(int subAccountId) {
        return SubAccountOrders.getIfAbsentPut(subAccountId, OrderBook::new);
    }

    /**
     * @param accountId int
     * @return OrderBook
     */
    public static OrderBook getAccountOrderBook(int accountId) {
        return AccountOrders.getIfAbsentPut(accountId, OrderBook::new);
    }

    /**
     * @param strategyId int
     * @return OrderBook
     */
    public static OrderBook getStrategyOrderBook(int strategyId) {
        return StrategyOrders.getIfAbsentPut(strategyId, OrderBook::new);
    }

    /**
     * @param instrument Instrument
     * @return OrderBook
     */
    public static OrderBook getInstrumentOrderBook(Instrument instrument) {
        return InstrumentOrders.getIfAbsentPut(instrument.getInstrumentId(), OrderBook::new);
    }

    public static void onMarketData(BasicMarketData marketData) {
        // TODO 处理行情
    }

    /**
     * 创建[ParentOrder], 并存入Keeper
     *
     * @param ordSysIdAllocator OrdSysIdAllocator
     * @param strategyId        int
     * @param subAccount        SubAccount
     * @param account           Account
     * @param instrument        Instrument
     * @param offerQty          int
     * @param offerPrice        double
     * @param type              OrdType
     * @param direction         TrdDirection
     * @param action            TrdAction
     * @return ChildOrder
     */
    public static ChildOrder createAndSaveChildOrder(OrdSysIdAllocator ordSysIdAllocator,
                                                     int strategyId,
                                                     SubAccount subAccount,
                                                     Account account,
                                                     Instrument instrument,
                                                     int offerQty,
                                                     double offerPrice,
                                                     OrdType type,
                                                     TrdDirection direction,
                                                     TrdAction action) {
        ChildOrder childOrder = ChildOrder.newOrder(ordSysIdAllocator, strategyId,
                subAccount, account, instrument, offerQty, offerPrice, type, direction, action);
        putOrder(childOrder);
        return childOrder;
    }

//	/**
//	 * 将[ParentOrder]转换为[ChildOrder], 并存入Keeper
//	 * 
//	 * @param parentOrder
//	 * @return
//	 */
//	public static ChildOrder toChildOrder(ParentOrder parentOrder) {
//		ChildOrder childOrder = parentOrder.toChildOrder();
//		putOrder(childOrder);
//		return childOrder;
//	}
//
//	/**
//	 * 将[ParentOrder]拆分为多个[ChildOrder], 并存入Keeper
//	 * 
//	 * @param parentOrder
//	 * @param count
//	 * @return
//	 */
//	public static MutableList<ChildOrder> splitChildOrder(ParentOrder parentOrder, int count) {
//		MutableList<ChildOrder> childOrders = parentOrder.splitChildOrder(order -> {
//			// TODO
//			return null;
//		});
//		childOrders.each(OrderBookKeeper::putOrder);
//		return childOrders;
//	}

    @Override
    public String toString() {
        return "";
    }

}
