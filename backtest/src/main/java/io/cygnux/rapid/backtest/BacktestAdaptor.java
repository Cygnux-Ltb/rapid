package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.adaptor.AbstractAdaptor;
import io.cygnux.rapid.core.event.enums.TrdDirection;
import io.cygnux.rapid.core.event.inbound.FastMarketData;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import io.cygnux.rapid.core.event.outbound.CancelOrder;
import io.cygnux.rapid.core.event.outbound.NewOrder;
import io.cygnux.rapid.core.event.outbound.QueryBalance;
import io.cygnux.rapid.core.event.outbound.QueryOrder;
import io.cygnux.rapid.core.event.outbound.QueryPosition;
import io.cygnux.rapid.core.event.outbound.SubscribeMarketData;
import io.mercury.common.file.FileScanner;
import io.mercury.common.lang.exception.RuntimeIOException;
import io.mercury.common.sys.SysProperties;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

@Component("backtestAdaptor")
public class BacktestAdaptor extends AbstractAdaptor implements BacktestController {

    @Value("${marketdata.file.path:~/mkt}")
    private String marketDataPath;

    @Resource
    private ImmediatelyMatchMachine matchMachine;

    private MutableIntObjectMap<FastMarketData> marketDataBucket;

    private final Queue<OrderReport> waitingSendReports = new LinkedList<>();

    /**
     * @param scheduler InboundHandler
     */
    BacktestAdaptor(BacktestScheduler scheduler) {
        super(new BacktestAccount(), false, scheduler);
    }

    @PostConstruct
    private void init() {
        log.info("BacktestAdaptor::init, loading data path: [{}]", marketDataPath);
    }

    @Override
    protected boolean directSubscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {
        nextStep();
        return true;
    }

    @Override
    protected boolean directNewOrder(@Nonnull NewOrder order) {
        var reports = matchMachine.onNewOrder(order);
        if (reports.notEmpty()) {
            // 将成交回报放入待发送队列
            reports.each(waitingSendReports::offer);
        }
        sendOrderReport(System.nanoTime());
        return true;
    }

    @Override
    protected boolean directCancelOrder(@Nonnull CancelOrder order) {
        var reports = matchMachine.onCancelOrder(order);
        if (reports.notEmpty()) {
            // 将成交回报放入待发送队列
            reports.each(waitingSendReports::offer);
        }
        sendOrderReport(System.nanoTime());
        return true;
    }

    @Override
    protected boolean directQueryOrder(@Nonnull QueryOrder query) {
        return true;
    }

    @Override
    protected boolean directQueryPosition(@Nonnull QueryPosition query) {
        var report = new PositionsReport();
        report.setBrokerId(BacktestAccount.BACKTEST_BROKER_CODE);
        report.setInvestorId(BacktestAccount.BACKTEST_INVESTOR_CODE);
        report.setExchangeCode(query.getExchangeCode());
        report.setInstrumentCode(query.getInstrumentCode());
        report.setQty(0);
        report.setMsg("BACKTEST");
        report.setDirection(TrdDirection.LONG);
        inboundHandler.handlePositionsReport(report);
        report.setDirection(TrdDirection.SHORT);
        inboundHandler.handlePositionsReport(report);
        return true;
    }

    @Override
    protected boolean directQueryBalance(@Nonnull QueryBalance query) {

        return true;
    }

    @Override
    protected boolean startup0() {
        return true;
    }

    @Override
    public void close() {
        log.info("BacktestAdaptor::close");
    }

    private int index = -1;


    /**
     * read: rb2510 bid 2000 ask 2001
     * m: rb2510 bid 2000 ask 2001
     * s: rb2510 bid 2000 ask 2001 -> buy 2000
     * read: rb2510 bid 1999 ask 2000
     * m: rb2510 bid 1999 ask 2000 -> buy 2000 fill
     * <p>
     * s: order rb2510 2000 fill
     */
    @Override
    public void nextStep() {
        var nano = System.nanoTime();
        FastMarketData marketData = marketDataBucket.remove(++index);
        log.info("BacktestAdaptor::nextStep NANO {}, index: {}, MarketData: {}", nano, index, marketData);
        // 推送行情到撮合机
        var reports = matchMachine.onMarketData(marketData);
        // 将成交回报放入待发送队列
        reports.each(waitingSendReports::offer);
        // 推送行情到下游处理器
        inboundHandler.handleFastMarketData(marketData);
        sendOrderReport(nano);
    }

    private void sendOrderReport(long nano) {
        if (waitingSendReports.isEmpty()) {
            log.info("BacktestAdaptor::sendOrderReport NANO {}, waitingSendReports is empty", nano);
        } else {
            // 发送行情回报到下游处理器
            log.info("BacktestAdaptor::sendOrderReport NANO {}, waitingSendReports size {}", nano, waitingSendReports.size());
            while (!waitingSendReports.isEmpty()) {
                var report = waitingSendReports.poll();
                log.info("BacktestAdaptor::sendOrderReport NANO {}, send report: {}", nano, report);
                inboundHandler.handleOrderReport(report);
            }
        }
    }

    public static void main(String[] args) {
        File base = new File(SysProperties.USER_HOME_FILE, "logs");
        System.out.println("base: " + base.getAbsolutePath());
        MutableSet<File> files = FileScanner.depthFirst(base);
        var counter = new AtomicInteger(0);
        files.each(file -> {
            System.out.println("file: " + file.getAbsolutePath());
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                for (String line : IOUtils.readLines(inputStream, Charset.defaultCharset())) {
                    if (line.contains("[ctp-eventbus-worker]")) {
                        System.out.println("NO:" + counter.incrementAndGet() + " | " + line);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeIOException(e);
            }
        });

    }


}
