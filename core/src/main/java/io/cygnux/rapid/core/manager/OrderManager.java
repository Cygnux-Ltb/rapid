package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.order.Order;
import io.cygnux.rapid.core.order.OrderBook;
import io.cygnux.rapid.core.stream.StreamEventHandler;
import io.cygnux.rapid.core.stream.event.OrderReport;

public interface OrderManager extends StreamEventHandler {

    void onOrderReport(OrderReport report);

    void putOrder(Order order);

    Order getOrder(long orderSysId);

    default OrderBook getAccountOrder(Account account) {
        return getAccountOrder(account.getAccountId());
    }

    OrderBook getAccountOrder(String investorId);

    OrderBook getAccountOrder(int accountId);

    default OrderBook getSubAccountOrder(SubAccount subAccount) {
        return getSubAccountOrder(subAccount.getSubAccountId());
    }

    OrderBook getSubAccountOrder(int subAccountId);

}
