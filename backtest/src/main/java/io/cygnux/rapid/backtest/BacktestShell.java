package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.CoreScheduler;
import io.cygnux.rapid.core.event.inbound.AdaptorReport;
import io.cygnux.rapid.core.event.inbound.BalanceReport;
import io.cygnux.rapid.core.event.inbound.DepthMarketDataReport;
import io.cygnux.rapid.core.event.inbound.InstrumentStatusReport;
import io.cygnux.rapid.core.event.inbound.MarketDataReport;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import io.cygnux.rapid.core.strategy.StrategySignal;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("backtestScheduler")
public class BacktestShell implements CoreScheduler {

    @Resource(name = "coreScheduler")
    private CoreScheduler scheduler;

    /**
     * 对本次数据运行产生的信号进行处理
     *
     * @param signal StrategySignal
     */
    @Override
    public void handleSignal(StrategySignal signal) {

    }

    /**
     * [1].快速行情处理
     *
     * @param event FastMarketData
     */
    @Override
    public void handleMarketDataReport(MarketDataReport event) {

    }

    /**
     * [2].深度行情处理
     *
     * @param event DepthMarketData
     */
    @Override
    public void handleDepthMarketData(DepthMarketDataReport event) {

    }

    /**
     * [3].订单回报处理
     *
     * @param event OrderReport
     */
    @Override
    public void handleOrderReport(OrderReport event) {

    }

    /**
     * [4].持仓回报处理
     *
     * @param event PositionsReport
     */
    @Override
    public void handlePositionsReport(PositionsReport event) {

    }

    /**
     * [5].余额回报处理
     *
     * @param event BalanceReport
     */
    @Override
    public void handleBalanceReport(BalanceReport event) {

    }

    /**
     * [6].Adaptor回报处理
     *
     * @param event AdaptorReport
     */
    @Override
    public void handleAdaptorReport(AdaptorReport event) {

    }

    /**
     * [7].交易标的状态回报处理
     *
     * @param event InstrumentStatusReport
     */
    @Override
    public void handleInstrumentStatusReport(InstrumentStatusReport event) {

    }

    @Override
    public void onSignal(StrategySignal signal) {

    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {

    }
}
