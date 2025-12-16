package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.shared.enums.OrdStatus;
import io.cygnux.rapid.core.shared.enums.TrdDirection;
import io.cygnux.rapid.core.shared.event.FastMarketData;
import io.cygnux.rapid.core.shared.event.OrderReport;
import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.epoch.HighResolutionEpoch;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.collector.Collectors2;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static io.mercury.common.datetime.pattern.impl.DateTimePattern.YY_MM_DD_HH_MM_SS;

@Component
public final class ImmediatelyMatchMachine {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ImmediatelyMatchMachine.class);

    // 当前的卖1价和买1价
    private double askPrice1;
    private double bidPrice1;

    private MutableIntObjectMap<MutableLongObjectMap<NewOrder>> waitingOrders;

    private final ImmutableList<OrderReport> empty = ImmutableLists.newImmutableList();

    @PostConstruct
    public void init() {
        if (log.isInfoEnabled())
            log.info("ImmediatelyMatchMachine::init -> {}", YY_MM_DD_HH_MM_SS.now());
    }


    private MutableLongObjectMap<NewOrder> getOrderSet(int instrumentId) {
        var orders = waitingOrders.getIfAbsentPut(instrumentId, MutableMaps.newLongObjectMap());
        log.info("ImmediatelyMatchMachine::getOrderSet, Waiting orders for instrumentId: {}, count: {}", instrumentId, orders.size());
        return orders;
    }


    public ImmutableList<OrderReport> onNewOrder(NewOrder newOrder) {
        if (isDeal(newOrder)) {
            var report = toReport(newOrder, OrdStatus.FILLED);
            log.info("ImmediatelyMatchMachine::onNewOrder order filled, ORDER: {}, REPORT: {}",
                    newOrder, report);
            return ImmutableLists.newImmutableList(report);
        } else {
            log.info("ImmediatelyMatchMachine::onNewOrder order no deal, ORDER: {}", newOrder);
            getOrderSet(newOrder.getInstrumentId()).put(newOrder.getOrdSysId(), newOrder);
            return empty;
        }
    }


    public ImmutableList<OrderReport> onCancelOrder(CancelOrder cancelOrder) {
        var orderSet = getOrderSet(cancelOrder.getInstrumentId());
        var removed = orderSet.remove(cancelOrder.getOrdSysId());
        if (removed != null) {
            var report = toReport(removed, OrdStatus.CANCELED);
            log.info("ImmediatelyMatchMachine::onCancelOrder order canceled, ORDER: {}, REPORT: {}",
                    removed, report);
            return ImmutableLists.newImmutableList(report);
        }
        return empty;
    }


    public ImmutableList<OrderReport> onMarketData(FastMarketData marketData) {
        this.askPrice1 = marketData.getAskPrice1();
        this.bidPrice1 = marketData.getBidPrice1();
        log.info("ImmediatelyMatchMachine::onMarketData market data updated, ASK: {}, BID: {}", askPrice1, bidPrice1);
        var orders = getOrderSet(marketData.getInstrumentId());
        if (orders.isEmpty()) {
            return empty;
        } else {
            var completed = orders.stream()
                    .filter(this::isDeal)
                    .map(order -> {
                        var report = toReport(order, OrdStatus.FILLED);
                        log.info("ImmediatelyMatchMachine::onMarketData order deal, ORDER -> {}, REPORT -> {}",
                                order, report);
                        return report;
                    })
                    .collect(Collectors2.toImmutableList());
            log.info("{} orders have been found", completed.size());
            return completed;
        }
    }


    private boolean isDeal(NewOrder order) {
        return  // 买单的撮合匹配
                (order.getDirection() == TrdDirection.LONG
                        && order.getOfferPrice() >= this.askPrice1)
                        || // 卖单的撮合匹配
                        (order.getDirection() == TrdDirection.SHORT
                                && order.getOfferPrice() <= this.bidPrice1);
    }


    private OrderReport toReport(NewOrder newOrder, OrdStatus status) {
        var report = new OrderReport();
        report.setEpochMicros(HighResolutionEpoch.micros());
        report.setOrdSysId(newOrder.getOrdSysId());
        report.setTradingDay(0);
        report.setBrokerId(newOrder.getBrokerId());
        report.setInvestorId(BacktestAccount.BACKTEST_INVESTOR_CODE);
        report.setAccountId(newOrder.getAccountId());
        report.setOrderRef(String.valueOf(report.getEpochMicros()));
        report.setBrokerOrdSysId(String.valueOf(report.getOrdSysId()));
        report.setExchangeCode(newOrder.getExchangeCode());
        report.setInstrumentCode(newOrder.getInstrumentCode());
        report.setStatus(status);
        report.setDirection(newOrder.getDirection());
        report.setAction(newOrder.getAction());
        report.setOfferQty(newOrder.getOfferQty());
        report.setFilledQty(newOrder.getOfferQty());
        report.setOfferPrice(newOrder.getOfferPrice());
        report.setTradePrice(newOrder.getOfferPrice());
        report.setOfferTime("");
        report.setUpdateTime("");
        report.setRemark("");
        return report;
    }


}
