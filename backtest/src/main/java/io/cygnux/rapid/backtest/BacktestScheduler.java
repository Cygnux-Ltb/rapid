package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.CoreScheduler;
import io.cygnux.rapid.core.event.InboundHandler;
import io.cygnux.rapid.core.event.inbound.AdaptorReport;
import io.cygnux.rapid.core.event.inbound.BalanceReport;
import io.cygnux.rapid.core.event.inbound.DepthMarketData;
import io.cygnux.rapid.core.event.inbound.FastMarketData;
import io.cygnux.rapid.core.event.inbound.InstrumentStatusReport;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("backtestScheduler")
public class BacktestScheduler implements InboundHandler {

    @Resource(name = "coreScheduler")
    private CoreScheduler scheduler;

    /**
     * [1].快速行情处理
     *
     * @param marketData FastMarketData
     */
    @Override
    public void handleFastMarketData(FastMarketData marketData) {
        scheduler.handleFastMarketData(marketData);
    }

    /**
     * [2].深度行情处理
     *
     * @param marketData DepthMarketData
     */
    @Override
    public void handleDepthMarketData(DepthMarketData marketData) {
        scheduler.handleDepthMarketData(marketData);
    }

    /**
     * [3].订单回报处理
     *
     * @param report OrderReport
     */
    @Override
    public void handleOrderReport(OrderReport report) {
        scheduler.handleOrderReport(report);
    }

    /**
     * [4].持仓回报处理
     *
     * @param report PositionsReport
     */
    @Override
    public void handlePositionsReport(PositionsReport report) {
        scheduler.handlePositionsReport(report);
    }

    /**
     * [5].余额回报处理
     *
     * @param report BalanceReport
     */
    @Override
    public void handleBalanceReport(BalanceReport report) {
        scheduler.handleBalanceReport(report);
    }

    /**
     * [6].Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    @Override
    public void handleAdaptorReport(AdaptorReport report) {
        scheduler.handleAdaptorReport(report);
    }

    /**
     * [7].交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    @Override
    public void handleInstrumentStatusReport(InstrumentStatusReport report) {
        scheduler.handleInstrumentStatusReport(report);
    }

    @Override
    public void close() throws IOException {
        scheduler.close();
    }

}
