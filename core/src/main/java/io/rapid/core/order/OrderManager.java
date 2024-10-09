package io.rapid.core.order;

import io.rapid.core.account.Account;
import io.rapid.core.account.SubAccount;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.order.impl.Order;

import java.util.List;

public interface OrderManager {

    void onOrderEvent(OrderReport event);

    Order getChildOrder(long orderSysId);

    default List<Order> getChildOrder(Account account) {
        return getChildOrder(account.getInvestorCode());
    }

    List<Order> getChildOrder(String investorId);

    default List<Order> getChildOrder(SubAccount subAccount) {
        return getChildOrder(subAccount.getSubAccountId());
    }

    List<Order> getChildOrder(int subAccountId);

}
