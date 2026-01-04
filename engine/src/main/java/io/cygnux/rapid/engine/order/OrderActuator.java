package io.cygnux.rapid.engine.order;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.core.event.enums.OrdStatus;
import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

public final class OrderActuator {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderActuator.class);

    private OrderActuator() {
    }

    /**
     * 根据订单回报处理订单状态
     *
     * @param order  Order
     * @param report OrderReport
     */
    public static void updateOrderWith(@Nonnull ChildOrder order, @Nonnull OrderReport report) {
        var qty = order.getQty();
        int filledQty = report.getFilledQty();
        var status = report.getStatus();
        log.info("OrderReport status==[{}], filledQty==[{}], tradePrice==[{}], OrdQty -> {}",
                status, filledQty, report.getTradePrice(), qty);
        switch (status) {
            case UNPROVIDED, UNKNOWN -> {
                // 处理未返回订单状态的情况, 根据成交数量判断
                int offerQty = qty.getOfferQty();
                if (filledQty == offerQty) // 成交数量等于委托数量, 状态为全部成交
                    order.setStatus(OrdStatus.FILLED);
                else if (filledQty < offerQty && filledQty > 0) // 成交数量小于委托数量同时成交数量大于0, 状态为部分成交
                    order.setStatus(OrdStatus.PARTIALLY_FILLED);
                else // 成交数量等于0, 状态为New
                    order.setStatus(OrdStatus.NEW);
            }
            default ->
                // 已返回订单状态, 直接读取
                    order.setStatus(status);
        }
        switch (order.getStatus()) {
            case PARTIALLY_FILLED -> {
                // 处理部分成交, 设置已成交数量
                // Set FilledQty
                order.getQty().setFilledQty(filledQty);
                // 新增订单成交记录
                // Add NewTrade record
                order.addRecord(report.getEpochMicros(), report.getTradePrice(),
                        filledQty - order.getQty().getLastFilledQty());
                log.info(
                        "ChildOrder current status PartiallyFilled, strategyId==[{}], ordSysId==[{}], "
                                + "filledQty==[{}], avgTradePrice==[{}]",
                        order.getStrategyId(), order.getOrdSysId(),
                        order.getQty().getFilledQty(),
                        order.getPrice().getAvgTradePrice());
            }
            case FILLED -> {
                // 处理全部成交, 设置已成交数量
                // Set FilledQty
                order.getQty().setFilledQty(filledQty);
                // 新增订单成交记录
                // Add NewTrade Record
                order.addRecord(report.getEpochMicros(), report.getTradePrice(),
                        filledQty - order.getQty().getLastFilledQty());
                // 计算此订单成交均价
                // Calculation AvgPrice
                double avgTradePrice = order.fillAndGetAvgTradePrice();
                log.info(
                        "ChildOrder current status Filled, strategyId==[{}], ordSysId==[{}], "
                                + "filledQty==[{}], avgTradePrice==[{}]",
                        order.getStrategyId(), order.getOrdSysId(), order.getQty().getFilledQty(), avgTradePrice);
            }
            default ->
                // 记录其他情况, 打印详细信息
                    log.warn("Order updateWithReport finish, switch in default, order status==[{}], "
                            + "order -> {}, report -> {}", order.getStatus(), order, report);
        }
    }


}
