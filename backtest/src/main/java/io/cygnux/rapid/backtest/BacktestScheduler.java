package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.CoreScheduler;
import io.cygnux.rapid.core.event.SharedEventHandler;
import io.cygnux.rapid.core.event.received.AdapterStatusReport;
import io.cygnux.rapid.core.event.received.BalanceReport;
import io.cygnux.rapid.core.event.received.DepthMarketData;
import io.cygnux.rapid.core.event.received.FastMarketData;
import io.cygnux.rapid.core.event.received.InstrumentStatusReport;
import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.event.received.PositionsReport;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("backtestScheduler")
public class BacktestScheduler implements SharedEventHandler {

    @Resource(name = "coreScheduler")
    private CoreScheduler scheduler;

    /**
     * [1].快速行情处理
     *
     * @param marketData FastMarketData
     */
    @Override
    public void fireFastMarketData(FastMarketData marketData) {
        scheduler.fireFastMarketData(marketData);
    }

    /**
     * [2].深度行情处理
     *
     * @param marketData DepthMarketData
     */
    @Override
    public void fireDepthMarketData(DepthMarketData marketData) {
        scheduler.fireDepthMarketData(marketData);
    }

    /**
     * [3].订单回报处理
     *
     * @param report OrderReport
     */
    @Override
    public void fireOrderReport(OrderReport report) {
        scheduler.fireOrderReport(report);
    }

    /**
     * [4].持仓回报处理
     *
     * @param report PositionsReport
     */
    @Override
    public void firePositionsReport(PositionsReport report) {
        scheduler.firePositionsReport(report);
    }

    /**
     * [5].余额回报处理
     *
     * @param report BalanceReport
     */
    @Override
    public void fireBalanceReport(BalanceReport report) {
        scheduler.fireBalanceReport(report);
    }

    /**
     * [6].Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    @Override
    public void fireAdapterReport(AdapterStatusReport report) {
        scheduler.fireAdapterReport(report);
    }

    /**
     * [7].交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    @Override
    public void fireInstrumentStatusReport(InstrumentStatusReport report) {
        scheduler.fireInstrumentStatusReport(report);
    }

}
