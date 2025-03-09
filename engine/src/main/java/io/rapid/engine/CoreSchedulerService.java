package io.rapid.engine;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.CoreScheduler;
import io.rapid.core.account.Account;
import io.rapid.core.account.AccountManager;
import io.rapid.core.adaptor.AdaptorManager;
import io.rapid.core.event.InboundEvent;
import io.rapid.core.event.InboundEventLoop;
import io.rapid.core.event.InboundFeeder;
import io.rapid.core.event.OutboundHandler;
import io.rapid.core.event.enums.MarketDataType;
import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.TrdAction;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.InstrumentStatusReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.event.outbound.SubscribeMarketData;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.MarketDataManager;
import io.rapid.core.order.OrderManager;
import io.rapid.core.order.impl.ChildOrder;
import io.rapid.core.order.impl.ParentOrder;
import io.rapid.core.position.PositionManager;
import io.rapid.core.strategy.StrategyManager;
import io.rapid.core.strategy.StrategySignal;
import io.rapid.core.trade.TradeCommand;
import io.rapid.core.trade.TradeCommandProducer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static io.mercury.common.collections.MutableLists.newFastList;

@Service("coreScheduler")
public class CoreSchedulerService implements CoreScheduler {

    private final MutableList<StrategySignal> signals = newFastList(128);

    private static final Logger log = Log4j2LoggerFactory.getLogger(CoreSchedulerService.class);

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
    private AdaptorManager adaptorManager;

    @Resource
    private InboundFeeder eventFeeder;

    @Resource
    private OutboundHandler outboundHandler;

    private final InboundEventLoop eventLoop = new InboundEventLoop() {
        @Override
        public void onEvent(InboundEvent event, long sequence, boolean endOfBatch) throws Exception {
            switch (event.getType()) {
                case RawMarketData -> handleRawMarketData(event.getRawMarketData());
                case DepthMarketData -> handleDepthMarketData(event.getDepthMarketData());
                case OrderReport -> handleOrderReport(event.getOrderReport());
                case PositionsReport -> handlePositionsReport(event.getPositionsReport());
                case BalanceReport -> handleBalanceReport(event.getBalanceReport());
                case AdaptorReport -> handleAdaptorReport(event.getAdaptorReport());
                case InstrumentStatusReport -> handleInstrumentStatusReport(event.getInstrumentStatusReport());
                case Invalid -> log.error("NOTE Unknown InboundEvent -> {}", event);
            }
        }
    };


    @PostConstruct
    public void init() {
        // 注册事件循环队列, 用于接收事件并且处理
        eventFeeder.addEventLoop(eventLoop);


        // 启动事件提供者
        eventFeeder.startup();
    }


    /**
     * 深度行情处理
     *
     * @param event DepthMarketData
     */
    @Override
    public void handleDepthMarketData(DepthMarketData event) {

    }

    // 行情处理计数器
    private long marketDataCounter = -1;

    /**
     * 行情处理
     *
     * @param event RawMarketData
     */
    @Override
    public void handleRawMarketData(RawMarketData event) {
        // 行情内核穿透
        log.info("Core process start count -> {}", ++marketDataCounter);
        marketDataManager.onMarketData(event);
        // 处理本次内核穿透信号
        handleSignal(signals);
        log.info("Core process end count -> {}", marketDataCounter);
    }

    /**
     * 交易标的状态回报处理
     *
     * @param event InstrumentStatusReport
     */
    @Override
    public void handleInstrumentStatusReport(InstrumentStatusReport event) {
        log.info("CoreSchedulerService::handleInstrumentStatusReport, [InstrumentStatusReport] -> {}", event);
        event.getSubscribeStatus();
    }

    /**
     * 订单回报处理
     *
     * @param event OrderReport
     */
    @Override
    public void handleOrderReport(OrderReport event) {
        log.info("CoreSchedulerService::handleOrderReport, [OrderReport] -> {}", event);
    }

    /**
     * Adaptor回报处理
     *
     * @param event AdaptorReport
     */
    @Override
    public void handleAdaptorReport(AdaptorReport event) {
        log.info("CoreSchedulerService::handleAdaptorReport, [AdaptorReport] -> {}", event);
        adaptorManager.onAdaptorEvent(event);
        var currentStatus = adaptorManager.getCurrentStatus(event.getAccountId());
        log.info("Adaptor current status -> [{}], adaptorId==[{}]", currentStatus, event.getAdaptorId());
        if (currentStatus.isMarketDataEnabled()) {
            var subscribeMarketData = new SubscribeMarketData()
                    .setAccountId(event.getAccountId())
                    .setType(MarketDataType.FAST)
                    .setInstrumentCodes(strategyManager.getInstruments()
                            .stream()
                            .map(Instrument::getInstrumentCode)
                            .toList())
                    // TODO 补充接收地址
                    .setRecvAddr("");
            log.info("Publish [SubscribeMarketData] in loop -> {}", subscribeMarketData);
            outboundHandler.handleSubscribeMarketData(subscribeMarketData);
        }
        if (currentStatus.isTraderEnabled()) {
            Account account = accountManager.getAccount(event.getAccountId());

            /// 查询订单 ///
            var queryOrder = account.newQueryOrder()
                    .setReason("AdaptorReport-Response")
                    .setSource("CoreScheduler");
            log.info("Publish [QueryOrder] in loop -> {}", queryOrder);
            outboundHandler.handleQueryOrder(queryOrder);

            /// 查询仓位 ///
            var queryPosition = account.newQueryPosition()
                    .setReason("AdaptorReport-Response")
                    .setSource("CoreScheduler");
            log.info("Publish [QueryPosition] in loop -> {}", queryPosition);
            outboundHandler.handleQueryPosition(queryPosition);

            /// 查询余额 ///
            var queryBalance = account.newQueryBalance()
                    .setReason("AdaptorReport-Response")
                    .setSource("CoreScheduler");
            log.info("Publish [QueryBalance] in loop -> {}", queryBalance);
            outboundHandler.handleQueryBalance(queryBalance);
        }
    }

    /**
     * 持仓回报处理
     *
     * @param event PositionsReport
     */
    @Override
    public void handlePositionsReport(PositionsReport event) {
        log.info("CoreSchedulerService::handlePositionsReport, [PositionsReport] -> {}", event);

    }

    /**
     * 余额回报处理
     *
     * @param event BalanceReport
     */
    @Override
    public void handleBalanceReport(BalanceReport event) {
        log.info("CoreSchedulerService::handleBalanceReport, [BalanceReport] -> {}", event);
    }

    @Resource
    private TradeCommandProducer tradeCommandProducer;

    /**
     * 对本次数据运行产生的信号进行处理
     *
     * @param signal StrategySignal
     */
    @Override
    public void handleSignal(StrategySignal signal) {
        if (!signal.isVerification()) {
            log.error("CoreSchedulerService::handleSignal, Signal is invalid, [Signal] -> {}", signal);
            return;
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
            outboundHandler.handleNewOrder(longChildOrder.toNewOrder());
        }

        // 空单指令处理
        if (command.shortAction() != TrdAction.INVALID) {
            ChildOrder shortChildOrder = ChildOrder.newWithParent(parentOrder, account.getAccountId(),
                    command.shortQty(), signal.getOfferPrice(),
                    OrdType.defaultType(), TrdDirection.SHORT, command.shortAction());
            // 存储子订单
            orderManager.putOrder(shortChildOrder);
            // 发送空单
            outboundHandler.handleNewOrder(shortChildOrder.toNewOrder());
        }

    }


    @Override
    public void onSignal(StrategySignal signal) {
        signals.add(signal);
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
        // TODO 资源清理
    }

}
