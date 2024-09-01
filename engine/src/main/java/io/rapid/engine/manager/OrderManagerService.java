package io.rapid.engine.manager;

import io.mercury.common.collections.MutableLists;
import io.rapid.core.account.AccountManager;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.InstrumentKeeper;
import io.rapid.core.order.ChildOrder;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.enums.OrdType;
import io.rapid.core.order.enums.TrdAction;
import io.rapid.core.order.enums.TrdDirection;
import io.rapid.core.serializable.avro.inbound.OrderEvent;
import io.rapid.core.strategy.Strategy;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.list.MutableList;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

import static io.rapid.core.account.SubAccount.ExternalOrderSubAccount;

@Component
public final class OrderManager {

    MutableList<Strategy> strategies = MutableLists.newFastList(8);

    @Resource
    private AccountManager accountManager;

    private OrderManager() {
    }

    /**
     * 用于构建外部来源的新订单, 通常是根据系统未托管的订单回报构建, 此时需要传递订单当前状态
     *
     * @param event OrderReport
     * @return ChildOrder
     */
    public ChildOrder newExternalOrder(OrderEvent event) {
        var account = accountManager.getAccount(event.getInvestorId());
        var instrument = InstrumentKeeper.getInstrument(event.getInstrumentCode());
        var direction = TrdDirection.valueOf(event.getDirection());
        var action = TrdAction.valueOf(event.getAction());
        return new ChildOrder(event.getOrdSysId(),
                // -------------------------------
                // 外部订单使用的策略ID
                0,
                // 专用的处理外部来源订单的子账户
                ExternalOrderSubAccount.getSubAccountId(),
                // -------------------------------
                account.getAccountId(), instrument,
                // 以委托数量创建
                OrdQty.withOffer(event.getOfferQty()),
                // 以委托价格创建
                OrdPrice.withOffer(event.getOfferPrice()),
                // -------------------------------
                OrdType.Limited, direction, action);

    }

    /**
     * @param ordSysId   外部传入的ordSysId, 用于处理非系统订单
     * @param accountId  实际账户ID
     * @param instrument 交易标的
     * @param qty        委托数量
     * @param price      委托价格
     * @param direction  交易方向
     * @param action     交易动作
     * @return ChildOrder
     */
    @Deprecated
    public static ChildOrder newExternalOrder(
            //
            final long ordSysId,
            //
            final int accountId,
            //
            @Nonnull final Instrument instrument,
            //
            final OrdQty qty,
            //
            final OrdPrice price,
            //
            @Nonnull final TrdDirection direction,
            //
            @Nonnull final TrdAction action) {
        return new ChildOrder(ordSysId,
                // -------------------------------
                // 外部策略
                0,
                // -------------------------------
                ExternalOrderSubAccount.getSubAccountId(),
                // -------------------------------
                accountId, instrument, qty, price,
                // -------------------------------
                OrdType.Limited, direction, action);
    }


}
