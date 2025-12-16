package io.cygnux.rapid.ctp.adaptor.component.handler;

import io.cygnux.rapid.core.shared.SharedEventHandler;
import io.cygnux.rapid.core.shared.event.AdapterReport;
import io.cygnux.rapid.core.shared.event.BalanceReport;
import io.cygnux.rapid.core.shared.event.DepthMarketData;
import io.cygnux.rapid.core.shared.event.FastMarketData;
import io.cygnux.rapid.core.shared.event.InstrumentStatusReport;
import io.cygnux.rapid.core.shared.event.OrderReport;
import io.cygnux.rapid.core.shared.event.PositionsReport;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component("ipcInboundHandler")
public class IpcSharedEventHandlerImpl implements SharedEventHandler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(IpcSharedEventHandlerImpl.class);

    /**
     * [1].快速行情处理
     *
     * @param marketData FastMarketData
     */
    @Override
    public void fireFastMarketData(FastMarketData marketData) {
        log.info("Received RawMarketData -> {}", marketData);
    }

    /**
     * [2].深度行情处理
     *
     * @param marketData DepthMarketData
     */
    @Override
    public void fireDepthMarketData(DepthMarketData marketData) {
    }

    /**
     * [3].订单回报处理
     *
     * @param report OrderReport
     */
    @Override
    public void fireOrderReport(OrderReport report) {
        log.info("Received OrderReport -> {}", report);
    }

    /**
     * [4].持仓回报处理
     *
     * @param report PositionsReport
     */
    @Override
    public void firePositionsReport(PositionsReport report) {
        log.info("Received PositionsReport -> {}", report);
    }

    /**
     * [5].余额回报处理
     *
     * @param report BalanceReport
     */
    @Override
    public void fireBalanceReport(BalanceReport report) {
        log.info("Received BalanceReport -> {}", report);
    }

    /**
     * [6].Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    @Override
    public void fireAdaptorReport(AdapterReport report) {
        log.info("Received AdaptorReport -> {}", report);
    }

    /**
     * [7].交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    @Override
    public void fireInstrumentStatusReport(InstrumentStatusReport report) {
        log.info("Received order InstrumentStatusReport -> {}", report);
    }

}
