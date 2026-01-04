package io.cygnux.rapid.core.event;

import com.lmax.disruptor.EventHandler;
import io.cygnux.rapid.core.event.received.AdapterStatusReport;
import io.cygnux.rapid.core.event.received.BalanceReport;
import io.cygnux.rapid.core.event.received.DepthMarketData;
import io.cygnux.rapid.core.event.received.FastMarketData;
import io.cygnux.rapid.core.event.received.InstrumentStatusReport;
import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.event.received.PositionsReport;
import io.cygnux.rapid.core.event.sent.StrategySignal;
import io.mercury.common.lang.exception.NotImplementedFunctionException;
import io.mercury.common.log4j2.StaticLogger;

/**
 *
 * 处理入站信息接口
 * (核心管道事件处理器)
 *
 * @author yellow013
 */
public interface SharedEventHandler extends EventHandler<SharedEvent> {

    @Override
    default void onEvent(SharedEvent event, long sequence, boolean endOfBatch) throws Exception {
        switch (event.type()) {
            case SKIP -> fireSkip();
            case FAST_MARKET_DATA -> fireFastMarketData(event.fastMarketData());
            case DEPTH_MARKET_DATA -> fireDepthMarketData(event.depthMarketData());
            case ADAPTER_STATUS_REPORT -> fireAdapterReport(event.adapterStatusReport());
            case INSTRUMENT_STATUS_REPORT -> fireInstrumentStatusReport(event.instrumentStatusReport());
            case ORDER_REPORT -> fireOrderReport(event.orderReport());
            case POSITIONS_REPORT -> firePositionsReport(event.positionsReport());
            case BALANCE_REPORT -> fireBalanceReport(event.balanceReport());
            case STRATEGY_SIGNALS -> fireStrategySignals(event.strategySignals());
            case null, default ->
                    StaticLogger.debug("WARN SharedEventHandler::onEvent, event -> {}, sequence==[{}], endOfBatch==[{}]",
                            event, sequence, endOfBatch);
        }
    }

    /**
     * [1].跳过事件处理
     */
    default void fireSkip() {
        throw new NotImplementedFunctionException(this.getClass(), "fireSkip");
    }

    /**
     * [2].快速行情处理
     *
     * @param marketData FastMarketData
     */
    default void fireFastMarketData(FastMarketData marketData) {
        throw new NotImplementedFunctionException(this.getClass(), "fireFastMarketData");
    }

    /**
     * [3].深度行情处理
     *
     * @param marketData DepthMarketData
     */
    default void fireDepthMarketData(DepthMarketData marketData) {
        throw new NotImplementedFunctionException(this.getClass(), "fireDepthMarketData");
    }

    /**
     * [4].订单回报处理
     *
     * @param report OrderReport
     */
    default void fireOrderReport(OrderReport report) {
        throw new NotImplementedFunctionException(this.getClass(), "fireOrderReport");
    }

    /**
     * [5].持仓回报处理
     *
     * @param report PositionsReport
     */
    default void firePositionsReport(PositionsReport report) {
        throw new NotImplementedFunctionException(this.getClass(), "firePositionsReport");
    }

    /**
     * [6].余额回报处理
     *
     * @param report BalanceReport
     */
    default void fireBalanceReport(BalanceReport report) {
        throw new NotImplementedFunctionException(this.getClass(), "fireBalanceReport");
    }

    /**
     * [7].Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    default void fireAdapterReport(AdapterStatusReport report) {
        throw new NotImplementedFunctionException(this.getClass(), "fireAdaptorReport");
    }

    /**
     * [8].交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    default void fireInstrumentStatusReport(InstrumentStatusReport report) {
        throw new NotImplementedFunctionException(this.getClass(), "fireInstrumentStatusReport");
    }

    /**
     * [9].处理策略信号
     *
     * @param signals StrategySignal[]
     */
    default void fireStrategySignals(StrategySignal... signals) {
        throw new NotImplementedFunctionException(this.getClass(), "fireStrategySignals");
    }


//    /**
//     * [10].处理行情订阅
//     *
//     * @param event SubscribeMarketData
//     */
//    default void handleSubscribeMarketData(SubscribeMarketData event) {
//    }
//
//    /**
//     * [11].处理新订单
//     *
//     * @param event NewOrder
//     */
//    default void handleNewOrder(NewOrder event) {
//    }
//
//    /**
//     * [12].处理撤单
//     *
//     * @param event CancelOrder
//     */
//    default void handleCancelOrder(CancelOrder event) {
//    }
//
//    /**
//     * [13].处理订单查询
//     *
//     * @param event QueryOrder
//     */
//    default void handleQueryOrder(QueryOrder event) {
//    }
//
//    /**
//     * [14].处理仓位查询
//     *
//     * @param event QueryPosition
//     */
//    default void handleQueryPosition(QueryPosition event) {
//    }
//
//    /**
//     * [15].处理余额查询
//     *
//     * @param event QueryBalance
//     */
//    default void handleQueryBalance(QueryBalance event) {
//    }
//
//    /**
//     * [16].处理余额查询
//     *
//     * @param strategySignalSlots QueryBalance
//     */
//    default void handleQueryBalance(StrategySignal[] strategySignalSlots) {
//    }

}
