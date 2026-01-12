package io.cygnux.rapid.engine;

import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.adapter.SentEventHandler;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.manager.AccountManager;
import io.cygnux.rapid.core.manager.AdapterManager;
import io.cygnux.rapid.core.manager.MarketDataManager;
import io.cygnux.rapid.core.manager.OrderManager;
import io.cygnux.rapid.core.manager.PositionManager;
import io.cygnux.rapid.core.manager.StrategyManager;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import io.cygnux.rapid.core.order.impl.ParentOrder;
import io.cygnux.rapid.core.event.SharedEvent;
import io.cygnux.rapid.core.event.SharedEventFeeder;
import io.cygnux.rapid.core.event.SharedEventbus;
import io.cygnux.rapid.core.event.enums.MarketDataType;
import io.cygnux.rapid.core.event.enums.OrdType;
import io.cygnux.rapid.core.event.enums.TrdAction;
import io.cygnux.rapid.core.event.enums.TrdDirection;
import io.cygnux.rapid.core.event.received.AdapterStatusReport;
import io.cygnux.rapid.core.event.received.InstrumentStatusReport;
import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.event.sent.StrategySignal;
import io.cygnux.rapid.core.strategy.StrategySignalAggregator;
import io.cygnux.rapid.core.trade.TradeCommand;
import io.cygnux.rapid.core.trade.TradeCommandProducer;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import static io.mercury.common.collections.MutableLists.newFastList;

/**
 * 需要实现的事件调度器接口<p>
 * (核心处理接口)<p>
 * 1.处理入站事件, 并且管理订单, 策略, 仓位, 风控等管理器的调用顺序<p>
 * 2.负责策略信号的收集与处理.<p>
 * 3.负责管理出站事件的发布.
 */
@Service("coreScheduler")
public class CoreSchedulerService implements StrategySignalAggregator {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CoreSchedulerService.class);

    private final MutableList<StrategySignal> signals = newFastList(64);

    @Resource
    private MarketDataManager marketDataManager;

    @Resource
    private AccountManager accountManager;

    @Resource
    private OrderManager orderManager;

    @Resource
    private PositionManager positionManager;

    @Resource
    private StrategyManager strategyManager;

    @Resource
    private AdapterManager adapterManager;

    @Resource
    private SharedEventFeeder eventFeeder;

    @Resource
    private SentEventHandler sentEventHandler;

    private final SharedEventbus eventLoop = new SharedEventbus() {
        @Override
        public void onEvent(SharedEvent event, long sequence, boolean endOfBatch) throws Exception {
            switch (event.getType()) {
                case FAST_MARKET_DATA -> fireFastMarketData(event.getPayload().fastMarketData());
                case DEPTH_MARKET_DATA -> fireDepthMarketData(event.getPayload().depthMarketData());
                case ORDER_REPORT -> fireOrderReport(event.getPayload().orderReport());
                case POSITIONS_REPORT -> firePositionsReport(event.getPayload().positionsReport());
                case BALANCE_REPORT -> fireBalanceReport(event.getPayload().balanceReport());
                case ADAPTER_STATUS_REPORT -> fireAdapterReport(event.getPayload().adapterReport());
                case INSTRUMENT_STATUS_REPORT ->
                        fireInstrumentStatusReport(event.getPayload().instrumentStatusReport());
                case STRATEGY_SIGNALS -> fireStrategySignals(event.getStrategySignals());
                case INVALID -> log.error("NOTE Unknown InboundEvent -> {}", event);
                //turning-point
            }
        }
    };


    @PostConstruct
    public void init() {
        // 注册事件循环队列, 用于接收事件并且处理
        eventFeeder.addEventbus(eventLoop);


        // 启动事件提供者
        eventFeeder.startup();
    }


    // 行情处理计数器
    private long marketDataCounter = -1;

    /**
     * 行情处理
     *
     * @param marketData RawMarketData
     */
//    @Override
//    public void fireFastMarketData(FastMarketData marketData) {
//        // 行情内核穿透
//        log.info("Core process start count -> {}", ++marketDataCounter);
//        marketDataManager.onMarketData(marketData);
//        // 处理本次内核穿透信号
//        handleStrategySignal(signals);
//        log.info("Core process end count -> {}", marketDataCounter);
//    }

    /**
     * 交易标的状态回报处理
     *
     * @param report InstrumentStatusReport
     */
    @Override
    public void fireInstrumentStatusReport(InstrumentStatusReport report) {
        log.info("CoreSchedulerService::handleInstrumentStatusReport, [InstrumentStatusReport] -> {}", report);
        report.getSubscribeStatus();
    }

    /**
     * 订单回报处理
     *
     * @param report OrderReport
     */
    @Override
    public void fireOrderReport(OrderReport report) {
        log.info("CoreSchedulerService::handleOrderReport, [OrderReport] -> {}", report);
    }

    /**
     * Adaptor回报处理
     *
     * @param report AdaptorReport
     */
    @Override
    public void fireAdapterReport(AdapterStatusReport report) {
        log.info("CoreSchedulerService::handleAdapterReport, [AdapterReport] -> {}", report);
        adapterManager.onAdapterEvent(report);
        var currentStatus = adapterManager.getCurrentStatus(report.getAccountId());
        log.info("Adaptor current status -> [{}], adaptorId==[{}]", currentStatus, report.getAdaptorId());
        if (currentStatus.isMarketDataEnabled()) {
            var subscribeMarketData = new SubscribeMarketData()
                    .setAccountId(report.getAccountId())
                    .setType(MarketDataType.FAST)
                    .setInstrumentCodes(strategyManager.getInstruments()
                            .stream()
                            .map(Instrument::getInstrumentCode)
                            .toList())
                    // TODO 补充接收地址
                    .setReceivedAddr("");
            log.info("Publish [SubscribeMarketData] in loop -> {}", subscribeMarketData);
            sentEventHandler.handleSubscribeMarketData(subscribeMarketData);
        }
        if (currentStatus.isTraderEnabled()) {
            Account account = accountManager.getAccount(report.getAccountId());

            /// 查询订单 ///
            var queryOrder = account.newQueryOrder()
                    .setReason("AdaptorReport-Response")
                    .setSource("CoreScheduler");
            log.info("Publish [QueryOrder] in loop -> {}", queryOrder);
            sentEventHandler.handleQueryOrder(queryOrder);

            /// 查询仓位 ///
            var queryPosition = account.newQueryPosition()
                    .setReason("AdaptorReport-Response")
                    .setSource("CoreScheduler");
            log.info("Publish [QueryPosition] in loop -> {}", queryPosition);
            sentEventHandler.handleQueryPosition(queryPosition);

            /// 查询余额 ///
            var queryBalance = account.newQueryBalance()
                    .setReason("AdaptorReport-Response")
                    .setSource("CoreScheduler");
            log.info("Publish [QueryBalance] in loop -> {}", queryBalance);
            sentEventHandler.handleQueryBalance(queryBalance);
        }
    }

    @Resource
    private TradeCommandProducer tradeCommandProducer;

    /**
     * 对本次数据运行产生的信号进行处理
     *
     * @param signals StrategySignal[]
     */
    @Override
    public void onStrategySignals(StrategySignal[] signals) {
        for (var signal : signals) {
            if (!signal.isVerification() || !signal.isAvailable()) {
                log.error("CoreSchedulerService::handleSignal, Signal is invalid, [Signal] -> {}", signal);
                continue;
            }
            int targetQty = signal.getTargetQty();
            TrdDirection direction = targetQty > 0 ? TrdDirection.LONG : TrdDirection.SHORT;
            var strategy = strategyManager.getStrategy(signal.getStrategyId());
            var subAccountMapping = accountManager.getSubAccountMapping(signal.getSubAccountId());
            // TODO 改进子账户映射获取实际账户的逻辑
            var account = subAccountMapping.getAccountMap().getAny();

            // 创建父订单
            var parentOrder = new ParentOrder(signal.getStrategyId(), signal.getSubAccountId(), signal.getInstrumentCode(),
                    signal.getTargetQtyAbs(), signal.getOfferPrice(), OrdType.defaultType(), direction);

            var position = positionManager.acquirePosition(account.getAccountId(), signal.getInstrumentCode());

            // 转换交易指令, 多空分别如何交易
            TradeCommand command = tradeCommandProducer.toTradeCommand(targetQty, position);

            // 存储父订单
            orderManager.putOrder(parentOrder);

            // 多单指令处理
            if (command.longAction() != TrdAction.INVALID) {
                ChildOrder longChildOrder = ChildOrder.newWithParent(parentOrder, account.getAccountId(),
                        command.longQty(), signal.getOfferPrice(),
                        OrdType.defaultType(), TrdDirection.LONG, command.longAction());
                // 存储子订单
                orderManager.putOrder(longChildOrder);
                // 发送多单
                sentEventHandler.handleNewOrder(longChildOrder.toNewOrder());
            }

            // 空单指令处理
            if (command.shortAction() != TrdAction.INVALID) {
                ChildOrder shortChildOrder = ChildOrder.newWithParent(parentOrder, account.getAccountId(),
                        command.shortQty(), signal.getOfferPrice(),
                        OrdType.defaultType(), TrdDirection.SHORT, command.shortAction());
                // 存储子订单
                orderManager.putOrder(shortChildOrder);
                // 发送空单
                sentEventHandler.handleNewOrder(shortChildOrder.toNewOrder());
            }
        }
    }


}
