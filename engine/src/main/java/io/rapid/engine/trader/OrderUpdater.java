package io.rapid.engine.trader;

import io.rapid.core.order.ChildOrder;
import io.rapid.core.order.enums.OrdStatus;
import io.rapid.core.serializable.avro.inbound.OrderEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

public final class OrderUpdater {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderUpdater.class);

    /**
     * 根据订单回报处理订单状态
     *
     * @param order ChildOrder
     * @param event DtoOrderReport
     */
    public static void updateOrder(@Nonnull ChildOrder order, @Nonnull OrderEvent event) {
        var qty = order.getQty();
        int filledQty = event.getFilledQty();
        OrdStatus status = OrdStatus.valueOf(event.getStatus());
        log.info("OrderEvent status==[{}], filledQty==[{}], tradePrice==[{}], order.getQty() -> {}", status, filledQty,
                event.getTradePrice(), qty);
        switch (status) {
            case Unprovided, Invalid -> {
                // 处理未返回订单状态的情况, 根据成交数量判断
                int offerQty = qty.getOfferQty();
                order.setStatus(
                        // 成交数量等于委托数量, 状态为全部成交
                        filledQty == offerQty ? OrdStatus.Filled
                                // 成交数量小于委托数量同时成交数量大于0, 状态为部分成交
                                : filledQty < offerQty && filledQty > 0 ? OrdStatus.PartiallyFilled
                                // 成交数量等于0, 状态为New
                                : OrdStatus.New);
            }
            default ->
                // 已返回订单状态, 直接读取
                    order.setStatus(status);
        }
        switch (order.getStatus()) {
            case PartiallyFilled -> {
                // 处理部分成交, 设置已成交数量
                // Set FilledQty
                order.getQty().setFilledQty(filledQty);
                // 新增订单成交记录
                // Add NewTrade record
                order.addRecord(event.getEpochMicros(), event.getTradePrice(),
                        filledQty - order.getQty().getLastFilledQty());
                log.info(
                        "ChildOrder current status PartiallyFilled, strategyId==[{}], ordSysId==[{}], "
                                + "filledQty==[{}], avgTradePrice==[{}]",
                        order.getStrategyId(), order.getOrdSysId(),
                        order.getQty().getFilledQty(),
                        order.getPrice().getAvgTradePrice());
            }
            case Filled -> {
                // 处理全部成交, 设置已成交数量
                // Set FilledQty
                order.getQty().setFilledQty(filledQty);
                // 新增订单成交记录
                // Add NewTrade Record
                order.addRecord(event.getEpochMicros(), event.getTradePrice(),
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
                            + "order -> {}, event -> {}", order.getStatus(), order, event);
        }
    }

}
