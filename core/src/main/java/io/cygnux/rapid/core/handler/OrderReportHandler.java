package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.event.inbound.OrderReport;

/**
 * 订单回报处理器
 */
@FunctionalInterface
public interface OrderReportHandler {

    void onOrderReport(final OrderReport event);

}
