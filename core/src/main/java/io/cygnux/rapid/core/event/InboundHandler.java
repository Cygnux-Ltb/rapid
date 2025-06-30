package io.cygnux.rapid.core.event;

import com.lmax.disruptor.EventHandler;
import io.cygnux.rapid.core.event.inbound.AdaptorReport;
import io.cygnux.rapid.core.event.inbound.BalanceReport;
import io.cygnux.rapid.core.event.inbound.DepthMarketData;
import io.cygnux.rapid.core.event.inbound.FastMarketData;
import io.cygnux.rapid.core.event.inbound.InstrumentStatusReport;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import io.mercury.common.log4j2.StaticLogger;

import java.io.Closeable;

/**
 * 处理入站信息接口
 *
 * @author yellow013
 */
public interface InboundHandler extends EventHandler<InboundEvent>,
        // 清理资源
        Closeable {

    @Override
    default void onEvent(InboundEvent event, long sequence, boolean endOfBatch) throws Exception {
        switch (event.getType()) {
            case FAST_MARKET_DATA -> handleFastMarketData(event.getFastMarketData());
            case DEPTH_MARKET_DATA -> handleDepthMarketData(event.getDepthMarketData());
            case ORDER_REPORT -> handleOrderReport(event.getOrderReport());
            case POSITIONS_REPORT -> handlePositionsReport(event.getPositionsReport());
            case BALANCE_REPORT -> handleBalanceReport(event.getBalanceReport());
            case INSTRUMENT_STATUS_REPORT -> handleInstrumentStatusReport(event.getInstrumentStatusReport());
            case ADAPTOR_STATUS_REPORT -> handleAdaptorReport(event.getAdaptorReport());
            case null, default ->
                    StaticLogger.error("NOTE InboundHandler::onEvent, event -> {}, sequence==[{}], endOfBatch==[{}]",
                            event, sequence, endOfBatch);
        }
    }

    /**
     * [1].快速行情处理
     *
     * @param marketData FastMarketData
     */
    void handleFastMarketData(FastMarketData marketData);

    /**
     * [2].深度行情处理
     *
     * @param marketData DepthMarketData
     */
    void handleDepthMarketData(DepthMarketData marketData);

    /**
     * [3].订单回报处理
     *
     * @param report OrderReport
     */
    void handleOrderReport(OrderReport report);

    /**
     * [4].持仓回报处理
     *
     * @param report PositionsReport
     */
    void handlePositionsReport(PositionsReport report);

    /**
     * [5].余额回报处理
     *
     * @param report BalanceReport
     */
    void handleBalanceReport(BalanceReport report);

    /**
     * [6].Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    void handleAdaptorReport(AdaptorReport report);

    /**
     * [7].交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    void handleInstrumentStatusReport(InstrumentStatusReport report);

}
