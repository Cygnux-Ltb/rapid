package io.rapid.core.order;

import io.rapid.core.account.Account;
import io.rapid.core.account.SubAccount;
import io.rapid.core.serializable.avro.inbound.OrderEvent;

import java.util.List;

public interface OrderManager {

    void onOrderEvent(OrderEvent event);

    ChildOrder getChildOrder(long orderSysId);

    default List<ChildOrder> getChildOrder(Account account) {
        return getChildOrder(account.getInvestorId());
    }

    List<ChildOrder> getChildOrder(String investorId);

    default List<ChildOrder> getChildOrder(SubAccount subAccount) {
        return getChildOrder(subAccount.getSubAccountId());
    }

    List<ChildOrder> getChildOrder(int subAccountId);

}
