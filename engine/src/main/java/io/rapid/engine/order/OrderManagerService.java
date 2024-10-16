package io.rapid.engine.order;

import io.rapid.core.account.AccountManager;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.order.Order;
import io.rapid.core.order.OrderBook;
import io.rapid.core.order.OrderManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public final class OrderManagerService implements OrderManager {

    @Resource
    private AccountManager accountManager;

    private OrderManagerService() {
    }


    @Override
    public void onOrderReport(OrderReport report) {
        var account = accountManager.getAccount(report.getAccountId());
        report.setAccountId(account.getAccountId());
        OrderKeeper.handleOrderReport(report);
    }

    @Override
    public void putOrder(Order order) {
        OrderKeeper.putOrder(order);
    }

    @Override
    public Order getOrder(long orderSysId) {
        return OrderKeeper.getOrder(orderSysId);
    }

    @Override
    public OrderBook getAccountOrder(String investorId) {
        var account = accountManager.getAccount(investorId);
        return getAccountOrder(account.getAccountId());
    }

    @Override
    public OrderBook getAccountOrder(int accountId) {
        return OrderKeeper.getAccountOrderBook(accountId);
    }

    @Override
    public OrderBook getSubAccountOrder(int subAccountId) {
        return OrderKeeper.getSubAccountOrderBook(subAccountId);
    }

}
