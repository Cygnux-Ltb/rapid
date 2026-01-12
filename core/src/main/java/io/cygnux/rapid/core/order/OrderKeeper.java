package io.cygnux.rapid.core.order;

import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.instrument.Instrument;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 统一管理订单<br>
 * OrderKeeper只对订单进行存储<br>
 * 根据订单状态存储在不同的分区<br>
 * 不对订单进行处理<br>
 *
 * @author yellow013
 */
public interface OrderKeeper {

    /**
     * @param ordSysId long
     * @return boolean
     */
    boolean isExists(long ordSysId);

    /**
     * @param ordSysId long
     * @return Order
     */
    @Nullable
    Order getOrder(long ordSysId);

    /**
     * 新增订单
     *
     * @param order Order
     */
    void putOrder(@Nonnull Order order);

    /**
     * @param subAccountId int
     * @return OrderBook
     */
    OrderBook getSubAccountOrderBook(int subAccountId);

    /**
     * @param accountId int
     * @return OrderBook
     */
    OrderBook getAccountOrderBook(int accountId);

    /**
     * @param strategyId int
     * @return OrderBook
     */
    OrderBook getStrategyOrderBook(int strategyId);

    /**
     * @param instrumentId int
     * @return OrderBook
     */
    OrderBook getInstrumentOrderBook(int instrumentId);

    /**
     * @param instrument Instrument
     * @return OrderBook
     */
    default OrderBook getInstrumentOrderBook(@Nonnull Instrument instrument) {
        return getInstrumentOrderBook(instrument.getInstrumentId());
    }

    /**
     * 处理订单回报
     *
     * @param report OrderReport
     * @return ChildOrder
     */
    Order onOrderReport(@Nonnull OrderReport report);

}
