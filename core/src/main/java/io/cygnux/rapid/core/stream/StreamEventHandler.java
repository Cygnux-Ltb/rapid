package io.cygnux.rapid.core.stream;

import com.lmax.disruptor.EventHandler;
import io.cygnux.rapid.core.adaptor.event.CancelOrder;
import io.cygnux.rapid.core.adaptor.event.NewOrder;
import io.cygnux.rapid.core.adaptor.event.QueryBalance;
import io.cygnux.rapid.core.adaptor.event.QueryOrder;
import io.cygnux.rapid.core.adaptor.event.QueryPosition;
import io.cygnux.rapid.core.adaptor.event.SubscribeMarketData;
import io.cygnux.rapid.core.stream.event.AdaptorReport;
import io.cygnux.rapid.core.stream.event.BalanceReport;
import io.cygnux.rapid.core.stream.event.DepthMarketData;
import io.cygnux.rapid.core.stream.event.FastMarketData;
import io.cygnux.rapid.core.stream.event.InstrumentStatusReport;
import io.cygnux.rapid.core.stream.event.OrderReport;
import io.cygnux.rapid.core.stream.event.PositionsReport;
import io.mercury.common.log4j2.StaticLogger;

/**
 * 处理入站信息接口
 *
 * @author yellow013
 */
public interface StreamEventHandler extends EventHandler<SharedEvent> {

    @Override
    default void onEvent(SharedEvent event, long sequence, boolean endOfBatch) throws Exception {
        switch (event.getType()) {
            case FAST_MARKET_DATA -> handleFastMarketData(event.getFastMarketData());
            case DEPTH_MARKET_DATA -> handleDepthMarketData(event.getDepthMarketData());
            case ORDER_REPORT -> handleOrderReport(event.getOrderReport());
            case POSITIONS_REPORT -> handlePositionsReport(event.getPositionsReport());
            case BALANCE_REPORT -> handleBalanceReport(event.getBalanceReport());
            case INSTRUMENT_STATUS_REPORT -> handleInstrumentStatusReport(event.getInstrumentStatusReport());
            case ADAPTOR_STATUS_REPORT -> handleAdaptorReport(event.getAdaptorReport());
            case null, default ->
                    StaticLogger.debug("NOTE InboundHandler::onEvent, event -> {}, sequence==[{}], endOfBatch==[{}]",
                            event, sequence, endOfBatch);
        }
    }


    /**
     * [1].快速行情处理
     *
     * @param marketData FastMarketData
     */
    default void handleFastMarketData(FastMarketData marketData) {
    }

    /**
     * [2].深度行情处理
     *
     * @param marketData DepthMarketData
     */
    default void handleDepthMarketData(DepthMarketData marketData) {
    }

    /**
     * [3].订单回报处理
     *
     * @param report OrderReport
     */
    default void handleOrderReport(OrderReport report) {
    }

    /**
     * [4].持仓回报处理
     *
     * @param report PositionsReport
     */
    default void handlePositionsReport(PositionsReport report) {
    }

    /**
     * [5].余额回报处理
     *
     * @param report BalanceReport
     */
    default void handleBalanceReport(BalanceReport report) {
    }

    /**
     * [6].Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    default void handleAdaptorReport(AdaptorReport report) {
    }

    /**
     * [7].交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    default void handleInstrumentStatusReport(InstrumentStatusReport report) {
    }


    /**
     * [1].处理行情订阅
     *
     * @param event SubscribeMarketData
     */
    default void handleSubscribeMarketData(SubscribeMarketData event) {
    }

    /**
     * [2].处理新订单
     *
     * @param event NewOrder
     */
    default void handleNewOrder(NewOrder event) {
    }

    /**
     * [3].处理撤单
     *
     * @param event CancelOrder
     */
    default void handleCancelOrder(CancelOrder event) {
    }

    /**
     * [4].处理订单查询
     *
     * @param event QueryOrder
     */
    default void handleQueryOrder(QueryOrder event) {
    }

    /**
     * [5].处理仓位查询
     *
     * @param event QueryPosition
     */
    default void handleQueryPosition(QueryPosition event) {
    }

    /**
     * [6].处理余额查询
     *
     * @param event QueryBalance
     */
    default void handleQueryBalance(QueryBalance event) {
    }


}
