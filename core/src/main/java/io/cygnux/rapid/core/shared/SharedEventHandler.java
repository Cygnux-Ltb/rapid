package io.cygnux.rapid.core.shared;

import com.lmax.disruptor.EventHandler;
import io.cygnux.rapid.core.shared.event.AdapterReport;
import io.cygnux.rapid.core.shared.event.BalanceReport;
import io.cygnux.rapid.core.shared.event.DepthMarketData;
import io.cygnux.rapid.core.shared.event.FastMarketData;
import io.cygnux.rapid.core.shared.event.InstrumentStatusReport;
import io.cygnux.rapid.core.shared.event.OrderReport;
import io.cygnux.rapid.core.shared.event.PositionsReport;
import io.cygnux.rapid.core.shared.event.StrategySignal;
import io.mercury.common.lang.exception.NotImplementedMethodException;
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
        switch (event.getType()) {
            case SKIP -> fireSkip();
            case FAST_MARKET_DATA -> fireFastMarketData(event.getPayload().fastMarketData());
            case DEPTH_MARKET_DATA -> fireDepthMarketData(event.getPayload().depthMarketData());
            case ADAPTOR_STATUS_REPORT -> fireAdaptorReport(event.getPayload().adapterReport());
            case INSTRUMENT_STATUS_REPORT -> fireInstrumentStatusReport(event.getPayload().instrumentStatusReport());
            case ORDER_REPORT -> fireOrderReport(event.getPayload().orderReport());
            case POSITIONS_REPORT -> firePositionsReport(event.getPayload().positionsReport());
            case BALANCE_REPORT -> fireBalanceReport(event.getPayload().balanceReport());
            case STRATEGY_SIGNALS -> fireStrategySignals(event.getStrategySignals());
            case null, default ->
                    StaticLogger.debug("WARN SharedEventHandler::onEvent, event -> {}, sequence==[{}], endOfBatch==[{}]",
                            event, sequence, endOfBatch);
        }
    }

    /**
     * [1].跳过事件处理
     */
    default void fireSkip() {
        throw new NotImplementedMethodException(this.getClass(), "fireSkip");
    }

    /**
     * [2].快速行情处理
     *
     * @param marketData FastMarketData
     */
    default void fireFastMarketData(FastMarketData marketData) {
        throw new NotImplementedMethodException(this.getClass(), "fireFastMarketData");
    }

    /**
     * [3].深度行情处理
     *
     * @param marketData DepthMarketData
     */
    default void fireDepthMarketData(DepthMarketData marketData) {
        throw new NotImplementedMethodException(this.getClass(), "fireDepthMarketData");
    }

    /**
     * [4].订单回报处理
     *
     * @param report OrderReport
     */
    default void fireOrderReport(OrderReport report) {
        throw new NotImplementedMethodException(this.getClass(), "fireOrderReport");
    }

    /**
     * [5].持仓回报处理
     *
     * @param report PositionsReport
     */
    default void firePositionsReport(PositionsReport report) {
        throw new NotImplementedMethodException(this.getClass(), "firePositionsReport");
    }

    /**
     * [6].余额回报处理
     *
     * @param report BalanceReport
     */
    default void fireBalanceReport(BalanceReport report) {
        throw new NotImplementedMethodException(this.getClass(), "fireBalanceReport");
    }

    /**
     * [7].Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    default void fireAdaptorReport(AdapterReport report) {
        throw new NotImplementedMethodException(this.getClass(), "fireAdaptorReport");
    }

    /**
     * [8].交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    default void fireInstrumentStatusReport(InstrumentStatusReport report) {
        throw new NotImplementedMethodException(this.getClass(), "fireInstrumentStatusReport");
    }

    /**
     * [9].处理策略信号
     *
     * @param signals StrategySignal[]
     */
    default void fireStrategySignals(StrategySignal... signals) {
        throw new NotImplementedMethodException(this.getClass(), "fireStrategySignals");
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
