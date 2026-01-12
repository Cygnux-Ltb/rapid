package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.order.Order;
import io.cygnux.rapid.core.order.OrderBook;
import io.cygnux.rapid.core.event.SharedEventHandler;
import io.cygnux.rapid.core.event.received.OrderReport;

import javax.annotation.Nullable;

public interface OrderManager extends SharedEventHandler {

    @Override
    default void fireOrderReport(OrderReport report) {
        onOrderReport(report);
    }

    /**
     *
     * @param report OrderReport
     */
    void onOrderReport(OrderReport report);

    /**
     * Save Order
     *
     * @param order Order
     */
    void putOrder(Order order);

    /**
     * Get Order by [ordSysId]
     *
     * @param ordSysId long
     * @return Order
     */
    @Nullable
    Order getOrder(long ordSysId);

    /**
     * Checkout OrderBook By [account]
     *
     * @param account Account
     * @return OrderBook
     */
    default OrderBook getAccountOrder(Account account) {
        return getAccountOrder(account.getAccountId());
    }

    /**
     * Checkout OrderBook By [investorId]
     *
     * @param investorId String
     * @return OrderBook
     */
    OrderBook getAccountOrder(String investorId);

    /**
     * Checkout OrderBook By [accountId]
     *
     * @param accountId int
     * @return OrderBook
     */
    OrderBook getAccountOrder(int accountId);

    /**
     * Checkout OrderBook By [subAccount]
     *
     * @param subAccount SubAccount
     * @return OrderBook
     */
    default OrderBook getSubAccountOrder(SubAccount subAccount) {
        return getSubAccountOrder(subAccount.getSubAccountId());
    }

    /**
     * Checkout OrderBook By [subAccountId]
     *
     * @param subAccountId int
     * @return OrderBook
     */
    OrderBook getSubAccountOrder(int subAccountId);

}
