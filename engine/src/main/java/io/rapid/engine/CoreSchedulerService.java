package io.rapid.engine;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.CoreScheduler;
import io.rapid.core.account.AccountManager;
import io.rapid.core.adaptor.AdaptorManager;
import io.rapid.core.event.InboundEvent;
import io.rapid.core.event.InboundEventFeeder;
import io.rapid.core.event.OutboundEventHandler;
import io.rapid.core.event.container.InboundEventLoop;
import io.rapid.core.event.enums.MarketDataType;
import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.MarketDataSubscribeReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.MarketDataManager;
import io.rapid.core.order.OrderManager;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.impl.ParentOrder;
import io.rapid.core.position.PositionManager;
import io.rapid.core.strategy.StrategyManager;
import io.rapid.core.strategy.StrategySignal;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static io.mercury.common.collections.MutableLists.newFastList;
import static io.mercury.common.epoch.EpochTimeUtil.getEpochMillis;

@Service("coreScheduler")
public class CoreSchedulerService implements CoreScheduler {

    private final MutableList<StrategySignal> signals = newFastList(64);

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
    private InboundEventFeeder eventFeeder;

    @Resource
    private OutboundEventHandler outboundEventHandler;

    private final InboundEventLoop eventLoop = new InboundEventLoop() {
        @Override
        protected void process(InboundEvent event) {
            switch (event.getType()) {
                case FastMarketData -> handleRawMarketData(event.getRawMarketData());
                case DepthMarketData -> handleDepthMarketData(event.getDepthMarketData());
                case OrderReport -> handleOrderReport(event.getOrderReport());
                case PositionsReport -> handlePositionsReport(event.getPositionsReport());
                case BalanceReport -> handleBalanceReport(event.getBalanceReport());
                case AdaptorReport -> handleAdaptorReport(event.getAdaptorReport());
                case MarketDataSubscribeReport -> handleMarketDataSubscribeReport(event.getMarketDataSubscribeReport());
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
     * 行情处理
     *
     * @param event DepthMarketData
     */
    @Override
    public void handleDepthMarketData(DepthMarketData event) {

    }

    /**
     * 行情处理
     *
     * @param event FastMarketData
     */
    @Override
    public void handleRawMarketData(RawMarketData event) {

    }

    /**
     * 行情订阅回报处理
     *
     * @param event MarketDataSubscribeReport
     */
    @Override
    public void handleMarketDataSubscribeReport(MarketDataSubscribeReport event) {
        log.info("CoreSchedulerService::handleMarketDataSubscribeReport, [MarketDataSubscribeReport] -> {}", event);
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
        if (currentStatus.isMdAvailable()) {
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
            outboundEventHandler.handleSubscribeMarketData(subscribeMarketData);
        }
        if (currentStatus.isTraderAvailable()) {
            /// 查询订单 ///
            var queryOrder = new QueryOrder()
                    .setAccountId(event.getAccountId())
                    .setSource("CoreScheduler")
                    .setGenerateTime(getEpochMillis());
            log.info("Publish [QueryOrder] in loop -> {}", queryOrder);
            outboundEventHandler.handleQueryOrder(queryOrder);

            /// 查询仓位 ///
            var queryPosition = new QueryPosition()
                    .setAccountId(event.getAccountId())
                    .setSource("CoreScheduler")
                    .setGenerateTime(getEpochMillis());
            log.info("Publish [QueryPosition] in loop -> {}", queryPosition);
            outboundEventHandler.handleQueryPosition(queryPosition);

            /// 查询余额 ///
            var queryBalance = new QueryBalance()
                    .setAccountId(event.getAccountId())
                    .setSource("CoreScheduler")
                    .setGenerateTime(getEpochMillis());
            log.info("Publish [QueryBalance] in loop -> {}", queryBalance);
            outboundEventHandler.handleQueryBalance(queryBalance);
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

    /**
     * 对本次数据运行产生的信号进行处理
     */
    private void handleSignal() {
        signals.each(this::handleSignal);
        signals.clear();
    }

    private void handleSignal(StrategySignal signal) {
        int orderWatermark = signal.getOrderWatermark();
        var strategy = strategyManager.getStrategy(signal.getStrategyId());
        var subAccountMapping = accountManager.getSubAccountMapping(signal.getSubAccountId());
        // TODO 改进子账户映射获取实际账户的逻辑
        var account = subAccountMapping.getAccountMap().getAny();

        positionManager.acquirePosition(account.getAccountId(), signal.getInstrumentCode());

        /// 创建父订单
        ParentOrder parentOrder = new ParentOrder(
                signal.getStrategyId(),
                signal.getSubAccountId(),
                account.getAccountId(),
                signal.getInstrumentCode(),
                OrdQty.withOffer(signal.getOfferQty()),
                OrdPrice.withOffer(signal.getOfferPrice()),
                OrdType.defaultType(),
                signal.getDirection());


        orders.put(parentOrder.uniqueId(), parentOrder);

        // 转换为实际订单
        MutableList<ActParentOrder> parentOrders = strategyOrderConverter.apply(parentOrder);

        // 存储订单
        // TODO 未完成全部逻辑
        ActParentOrder parentOrder = parentOrders.getFirst();
        orders.put(parentOrder.uniqueId(), parentOrder);

        // 转为实际执行的子订单
        ActChildOrder childOrder = parentOrder.toChildOrder();
        orders.put(childOrder.uniqueId(), childOrder);

        getAdaptor(instrument).newOrder(childOrder);


        switch (orderWatermark) {
            case 1 -> {
                switch (signal.getDirection()) {
                    case LONG:

                        break;
                    case SHORT:

                        break;

                    default:
                        break;
                }
            }
            case -1 -> {
                switch (signal.getDirection()) {
                    case LONG:

                        break;

                    case SHORT:

                        break;

                    default:
                        break;
                }
            }
            default -> {
            }
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
