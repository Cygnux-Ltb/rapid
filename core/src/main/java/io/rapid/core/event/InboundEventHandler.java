package io.rapid.core.event;

import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.MarketDataSubscribeReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
import io.rapid.core.event.inbound.RawMarketData;

import java.io.Closeable;

/**
 * 处理入站信息接口
 *
 * @author yellow013
 */
public interface InboundEventHandler extends
        // 清理资源
        Closeable {

    /**
     * [1].快速行情处理
     *
     * @param event FastMarketData
     */
    void handleRawMarketData(RawMarketData event);

    /**
     * [2].深度行情处理
     *
     * @param event DepthMarketData
     */
    void handleDepthMarketData(DepthMarketData event);

    /**
     * [3].订单回报处理
     *
     * @param event OrderReport
     */
    void handleOrderReport(OrderReport event);

    /**
     * [4].持仓回报处理
     *
     * @param event PositionsReport
     */
    void handlePositionsReport(PositionsReport event);

    /**
     * [5].余额回报处理
     *
     * @param event BalanceReport
     */
    void handleBalanceReport(BalanceReport event);

    /**
     * [6].Adaptor回报处理
     *
     * @param event AdaptorReport
     */
    void handleAdaptorReport(AdaptorReport event);

    /**
     * [7].行情订阅回报处理
     *
     * @param event MarketDataSubscribeReport
     */
    void handleMarketDataSubscribeReport(MarketDataSubscribeReport event);

}
