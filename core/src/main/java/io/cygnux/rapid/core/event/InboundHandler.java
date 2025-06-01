package io.cygnux.rapid.core.event;

import com.lmax.disruptor.EventHandler;
import io.mercury.common.log4j2.StaticLogger;
import io.cygnux.rapid.core.event.inbound.AdaptorReport;
import io.cygnux.rapid.core.event.inbound.BalanceReport;
import io.cygnux.rapid.core.event.inbound.DepthMarketDataReport;
import io.cygnux.rapid.core.event.inbound.InstrumentStatusReport;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import io.cygnux.rapid.core.event.inbound.MarketDataReport;

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
            case RAW_MARKET_DATA -> handleMarketDataReport(event.getMarketDataReport());
            case DEPTH_MARKET_DATA -> handleDepthMarketData(event.getDepthMarketDataReport());
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
     * @param event FastMarketData
     */
    void handleMarketDataReport(MarketDataReport event);

    /**
     * [2].深度行情处理
     *
     * @param event DepthMarketData
     */
    void handleDepthMarketData(DepthMarketDataReport event);

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
     * [7].交易标的状态回报处理
     *
     * @param event InstrumentStatusReport
     */
    void handleInstrumentStatusReport(InstrumentStatusReport event);

}
