package io.rapid.engine.service;

import io.rapid.core.account.AccountManager;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.instrument.InstrumentKeeper;
import io.rapid.core.order.Order;
import io.rapid.core.order.OrderManager;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.impl.ChildOrder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class OrderManagerService implements OrderManager {




    @Resource
    private AccountManager accountManager;

    private OrderManagerService() {
    }

    /**
     * 用于构建外部来源的新订单, 通常是根据系统未托管的订单回报构建, 此时需要传递订单当前状态
     *
     * @param event OrderReport
     * @return ChildOrder
     */
    public ChildOrder newExternalOrder(OrderReport event) {
        var account = accountManager.getAccount(event.getInvestorId());
        var instrument = InstrumentKeeper.getInstrument();
        var direction = event.getDirection();
        var action = event.getAction();

        ChildOrder childOrder = ChildOrder.newWithExternal(account.getAccountId(), event.getInstrumentCode(),
                OrdQty.withOffer(event.getOfferQty()).addFilledQty(event.getFilledQty()),
                OrdPrice.withOffer(event.getOfferPrice()),
                direction, action);
        childOrder.setBrokerRef0(event.getOrderRef());
        childOrder.setBrokerRef1(event.getBrokerOrdSysId());

        return childOrder;
    }


    @Override
    public void onOrderReport(OrderReport report) {

    }

    @Override
    public void putOrder(Order order) {

    }

    @Override
    public Order getOrder(long orderSysId) {
        return null;
    }

    @Override
    public List<Order> getOrder(String investorId) {
        return List.of();
    }

    @Override
    public List<Order> getOrder(int subAccountId) {
        return List.of();
    }
}
