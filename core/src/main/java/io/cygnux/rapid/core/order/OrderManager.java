package io.cygnux.rapid.core.order;

import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.event.inbound.OrderReport;

public interface OrderManager {

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
