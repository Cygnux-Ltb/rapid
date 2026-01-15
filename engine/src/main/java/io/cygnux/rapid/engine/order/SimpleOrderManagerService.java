package io.cygnux.rapid.engine.order;

import io.cygnux.rapid.core.manager.AccountManager;
import io.cygnux.rapid.core.manager.OrderManager;
import io.cygnux.rapid.core.order.OrderBook;
import io.cygnux.rapid.core.order.OrderKeeper;
import io.cygnux.rapid.core.types.event.received.OrderReport;
import io.cygnux.rapid.core.types.order.Order;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Service
@org.springframework.core.annotation.Order(HIGHEST_PRECEDENCE + 1)
public final class SimpleOrderManagerService implements OrderManager {

    @Resource
    private AccountManager accountManager;

    @Resource(name = "inHeap")
    private OrderKeeper orderKeeper;

    private SimpleOrderManagerService() {
    }

    @Override
    public void onOrderReport(OrderReport report) {
        var account = accountManager.getAccount(report.getAccountId());
        report.setAccountId(account.getAccountId());
        orderKeeper.onOrderReport(report);
    }

    @Override
    public void putOrder(Order order) {
        orderKeeper.putOrder(order);
    }

    @Override
    public Order getOrder(long ordSysId) {
        return orderKeeper.getOrder(ordSysId);
    }

    @Override
    public OrderBook getAccountOrder(String investorId) {
        var account = accountManager.getAccount(investorId);
        return getAccountOrder(account.getAccountId());
    }

    @Override
    public OrderBook getAccountOrder(int accountId) {
        return orderKeeper.getAccountOrderBook(accountId);
    }

    @Override
    public OrderBook getSubAccountOrder(int subAccountId) {
        return orderKeeper.getSubAccountOrderBook(subAccountId);
    }

}
