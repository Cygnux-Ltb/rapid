package io.cygnuxltb.adaptor.ctp;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.cygnuxltb.adaptor.ctp.OrderRefKeeper.OrderRefNotFoundException;
import io.cygnuxltb.adaptor.ctp.converter.FtdcOrderConverter;
import io.cygnuxltb.adaptor.ctp.converter.MarketDataConverter;
import io.cygnuxltb.adaptor.ctp.converter.OrderReportConverter;
import io.cygnuxltb.adaptor.ctp.gateway.CtpGateway;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcRspMsg;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcOrder;
import io.cygnuxltb.jcts.core.account.Account;
import io.cygnuxltb.jcts.core.adaptor.AbstractAdaptor;
import io.cygnuxltb.jcts.core.adaptor.AdaptorType;
import io.cygnuxltb.jcts.core.adaptor.ConnectionType;
import io.cygnuxltb.jcts.core.handler.AdaptorEventHandler;
import io.cygnuxltb.jcts.core.handler.InboundHandler;
import io.cygnuxltb.jcts.core.handler.InboundHandler.InboundSchedulerWrapper;
import io.cygnuxltb.jcts.core.handler.MarketDataHandler;
import io.cygnuxltb.jcts.core.handler.OrderEventHandler;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;
import io.cygnuxltb.jcts.core.ser.enums.AdaptorStatus;
import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;
import io.cygnuxltb.jcts.core.ser.req.CancelOrder;
import io.cygnuxltb.jcts.core.ser.req.NewOrder;
import io.cygnuxltb.jcts.core.ser.req.QueryBalance;
import io.cygnuxltb.jcts.core.ser.req.QueryOrder;
import io.cygnuxltb.jcts.core.ser.req.QueryPositions;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.concurrent.queue.ScQueueWithJCT;
import io.mercury.common.functional.Handler;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.ArrayUtil;
import io.mercury.serialization.json.JsonWrapper;
import jakarta.annotation.PostConstruct;
import org.agrona.concurrent.IdleStrategy;
import org.agrona.concurrent.SleepingIdleStrategy;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;

import static io.mercury.common.datetime.EpochTime.getEpochMillis;
import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

/**
 * CTP Adaptor, 用于连接上期CTP柜台
 *
 * @author yellow013
 */
public class CtpAdaptor extends AbstractAdaptor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpAdaptor.class);

    // 行情转换器
    private final MarketDataConverter marketDataConverter = new MarketDataConverter();

    // 转换订单回报
    private final OrderReportConverter orderReportConverter = new OrderReportConverter();

    // Ftdc Config
    private final CtpConfiguration config;

    // TODO 两个INT类型可以合并
    private volatile int frontId;
    private volatile int sessionId;

    private volatile boolean mdAvailable;
    private volatile boolean isTraderAvailable;

    // FTDC RSP 消息处理器
    private final Handler<FtdcRspMsg> handler;

    // 队列缓冲区
    private Queue<FtdcRspMsg> queue;


    /**
     * 传入MarketDataHandler, OrderReportHandler, AdaptorReportHandler实现,
     * 由构造函数内部转换为MPSC队列缓冲区
     *
     * @param account             Account
     * @param config              CtpConfig
     * @param marketDataHandler   MarketDataHandler<BasicMarketData>
     * @param orderEventHandler   OrderReportHandler
     * @param adaptorEventHandler AdaptorReportHandler
     */
    public CtpAdaptor(@Nonnull Account account,
                      @Nonnull CtpConfiguration config,
                      @Nonnull MarketDataHandler<BasicMarketData> marketDataHandler,
                      @Nonnull OrderEventHandler orderEventHandler,
                      @Nonnull AdaptorEventHandler adaptorEventHandler) {
        this(account, config, ConnectionType.Normal, marketDataHandler, orderEventHandler, adaptorEventHandler);
    }

    /**
     * 传入MarketDataHandler, OrderReportHandler, AdaptorReportHandler实现,
     * 由构造函数内部转换为MPSC队列缓冲区
     *
     * @param account             Account
     * @param config              CtpConfig
     * @param mode                AdaptorRunMode
     * @param marketDataHandler   MarketDataHandler<BasicMarketData>
     * @param orderEventHandler   OrderReportHandler
     * @param adaptorEventHandler AdaptorReportHandler
     */
    public CtpAdaptor(@Nonnull Account account,
                      @Nonnull CtpConfiguration config,
                      @Nonnull ConnectionType mode,
                      @Nonnull MarketDataHandler<BasicMarketData> marketDataHandler,
                      @Nonnull OrderEventHandler orderEventHandler,
                      @Nonnull AdaptorEventHandler adaptorEventHandler) {
        this(account, config, mode,
                new InboundSchedulerWrapper<>(
                        marketDataHandler,
                        orderEventHandler,
                        adaptorEventHandler, log));
    }

    /**
     * 传入InboundScheduler实现, 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param account   Account
     * @param config    CtpConfig
     * @param scheduler InboundHandler<BasicMarketData>
     */
    public CtpAdaptor(@Nonnull Account account, @Nonnull CtpConfiguration config,
                      @Nonnull InboundHandler<BasicMarketData> scheduler) {
        this(account, config, ConnectionType.Normal, scheduler);
    }

    /**
     * 传入InboundScheduler实现, 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param account   Account
     * @param config    CtpConfig
     * @param mode      AdaptorRunMode
     * @param scheduler InboundHandler<BasicMarketData>
     */
    public CtpAdaptor(@Nonnull Account account, @Nonnull CtpConfiguration config,
                      @Nonnull ConnectionType mode,
                      @Nonnull InboundHandler<BasicMarketData> scheduler) {
        super(CtpAdaptor.class.getSimpleName(), account);
        // 创建队列缓冲区
        this.queue = ScQueueWithJCT
                .mpscQueue(this.getClass().getSimpleName() + "-Buf")
                .capacity(32).process(msg -> {
                    switch (msg.getType()) {
                        case MdConnect -> {
                            var mdConnect = msg.getMdConnect();
                            this.mdAvailable = mdConnect.available();
                            log.info("Adaptor buf processed FtdcMdConnect, isMdAvailable==[{}]", mdAvailable);
                            final AdaptorEvent mdReport;
                            if (mdAvailable)
                                mdReport = AdaptorEvent.newBuilder().setEpochMillis(getEpochMillis()).setAdaptorId(getAdaptorId())
                                        .setStatus(AdaptorStatus.MD_ENABLE).build();
                            else
                                mdReport = AdaptorEvent.newBuilder().setEpochMillis(getEpochMillis()).setAdaptorId(getAdaptorId())
                                        .setStatus(AdaptorStatus.MD_DISABLE).build();
                            scheduler.onAdaptorEvent(mdReport);
                        }
                        case TraderConnect -> {
                            var traderConnect = msg.getTraderConnect();
                            this.isTraderAvailable = traderConnect.available();
                            this.frontId = traderConnect.frontId();
                            this.sessionId = traderConnect.sessionId();
                            log.info(
                                    "Adaptor buf processed FtdcTraderConnect, isTraderAvailable==[{}], frontId==[{}], sessionId==[{}]",
                                    isTraderAvailable, frontId, sessionId);
                            final AdaptorEvent adaptorEvent;
                            if (isTraderAvailable)
                                adaptorEvent = AdaptorEvent.newBuilder().setEpochMillis(getEpochMillis())
                                        .setAdaptorId(getAdaptorId()).setStatus(AdaptorStatus.TRADER_ENABLE).build();
                            else
                                adaptorEvent = AdaptorEvent.newBuilder().setEpochMillis(getEpochMillis())
                                        .setAdaptorId(getAdaptorId()).setStatus(AdaptorStatus.TRADER_DISABLE).build();
                            scheduler.onAdaptorEvent(adaptorEvent);
                        }
                        case DepthMarketData -> {
                            // 行情处理
                            // TODO
                            // multicaster.publish(rspMsg.getDepthMarketData());
                            var marketData = marketDataConverter.withFtdcDepthMarketData(msg.getDepthMarketData());
                            scheduler.onMarketData(marketData);
                        }
                        case Order -> {
                            // 报单回报处理
                            FtdcOrder ftdcOrder = msg.getOrder();
                            log.info(
                                    "Adaptor buf in FtdcOrder, InstrumentID==[{}], InvestorID==[{}], "
                                            + "OrderRef==[{}], LimitPrice==[{}], VolumeTotalOriginal==[{}], OrderStatus==[{}]",
                                    ftdcOrder.getInstrumentID(), ftdcOrder.getInvestorID(), ftdcOrder.getOrderRef(),
                                    ftdcOrder.getLimitPrice(), ftdcOrder.getVolumeTotalOriginal(), ftdcOrder.getOrderStatus());
                            var report0 = orderReportConverter.withFtdcOrder(ftdcOrder);
                            scheduler.onOrderEvent(report0);
                        }
                        case Trade -> {
                            // 成交回报处理
                            var ftdcTrade = msg.getTrade();
                            log.info("Adaptor buf in FtdcTrade, InstrumentID==[{}], InvestorID==[{}], OrderRef==[{}]",
                                    ftdcTrade.getInstrumentID(), ftdcTrade.getInvestorID(), ftdcTrade.getOrderRef());
                            var report1 = orderReportConverter.withFtdcTrade(ftdcTrade);
                            scheduler.onOrderEvent(report1);
                        }
                        case InputOrder -> {
                            // TODO 报单错误处理
                            var ftdcInputOrder = msg.getInputOrder();
                            log.info("Adaptor buf in [FtdcInputOrder] -> {}", JsonWrapper.toJson(ftdcInputOrder));
                        }
                        case InputOrderAction -> {
                            // TODO 撤单错误处理1
                            var ftdcInputOrderAction = msg.getInputOrderAction();
                            log.info("Adaptor buf in [FtdcInputOrderAction] -> {}", JsonWrapper.toJson(ftdcInputOrderAction));
                        }
                        case OrderAction -> {
                            // TODO 撤单错误处理2
                            var ftdcOrderAction = msg.getOrderAction();
                            log.info("Adaptor buf in [FtdcOrderAction] -> {}", JsonWrapper.toJson(ftdcOrderAction));
                        }
                        default -> log.warn("Adaptor buf unprocessed [FtdcRspMsg] -> {}", JsonWrapper.toJson(msg));
                    }
                });
        this.handler = queue::enqueue;
        this.config = config;
        this.type = mode;
        initializer();
    }

    /**
     * 使用正常模式和指定的FTDC消息队列构建Adaptor
     *
     * @param account Account
     * @param config  CtpConfig
     * @param queue   Queue<FtdcRspMsg>
     */
    public CtpAdaptor(@Nonnull Account account, @Nonnull CtpConfiguration config,
                      @Nonnull Queue<FtdcRspMsg> queue) {
        this(account, config, ConnectionType.Normal, queue);
    }

    /**
     * 使用指定的运行模式和FTDC消息队列构建Adaptor
     *
     * @param account Account
     * @param config  CtpConfig
     * @param mode    AdaptorRunMode
     * @param queue   Queue<FtdcRspMsg>
     */
    public CtpAdaptor(@Nonnull Account account, @Nonnull CtpConfiguration config,
                      @Nonnull ConnectionType mode, @Nonnull Queue<FtdcRspMsg> queue) {
        this(account, config, mode,
                // 使用入队函数实现Handler<FtdcRspMsg>
                queue::enqueue);
        this.queue = queue;
    }

    /**
     * 使用正常模式和指定的FTDC消息处理器构建Adaptor
     *
     * @param account Account
     * @param config  CtpConfig
     * @param handler Handler<FtdcRspMsg>
     */
    public CtpAdaptor(@Nonnull Account account, @Nonnull CtpConfiguration config,
                      @Nonnull Handler<FtdcRspMsg> handler) {
        this(account, config, ConnectionType.Normal, handler);
    }

    /**
     * 使用指定的运行模式和FTDC消息处理器构建Adaptor
     *
     * @param account Account
     * @param config  CtpConfig
     * @param mode    AdaptorRunMode
     * @param handler Handler<FtdcRspMsg>
     */
    public CtpAdaptor(@Nonnull Account account, @Nonnull CtpConfiguration config, ConnectionType mode,
                      @Nonnull Handler<FtdcRspMsg> handler) {
        super(CtpAdaptor.class.getSimpleName(), account);
        this.handler = handler;
        this.config = config;
        this.type = mode;
        initializer();
    }

    // GatewayId
    private String gatewayId;
    // CtpGateway
    private CtpGateway gateway;
    // FTDC报单请求转换器
    private FtdcOrderConverter orderConverter;

    @PostConstruct
    private void initializer() {
        // 创建FtdcOrderConverter
        this.orderConverter = new FtdcOrderConverter(config);
        // 创建GatewayId
        this.gatewayId = config.getBrokerId() + "-" + config.getInvestorId();
        // 创建Gateway
        log.info("Try create gateway, gatewayId -> {}", gatewayId);
        this.gateway = new CtpGateway(gatewayId, config, type, handler);
        log.info("Create gateway success, gatewayId -> {}", gatewayId);
    }

    @Override
    protected boolean startup0() {
        try {
            gateway.startup();
            log.info("{} -> bootstrap finish", gatewayId);
            return true;
        } catch (Exception e) {
            log.error("Gateway exception -> {}", e.getMessage(), e);
            return false;
        }
    }

    // 存储已订阅合约
    private final MutableSet<String> subscribedInstrumentCodes = MutableSets.newUnifiedSet();

    @Override
    public AdaptorType getAdaptorType() {
        return CtpAdaptorType.INSTANCE;
    }

    /**
     * 订阅行情实现
     */
    @Override
    public boolean subscribeMarketData(@Nonnull Instrument... instruments) {
        try {
            if (mdAvailable) {
                if (ArrayUtil.isNullOrEmpty(instruments)) {
                    // 输入的Instrument数组为空或null
                    log.warn("{} -> Input instruments is null or empty, Use subscribed instruments", adaptorId);
                    if (subscribedInstrumentCodes.isEmpty()) {
                        // 已记录的订阅Instrument为空
                        log.warn("{} -> Subscribed instruments is empty", adaptorId);
                        return false;
                    } else {
                        // 使用已经订阅过的Instrument
                        String[] instrumentCodes = new String[subscribedInstrumentCodes.size()];
                        log.info("Add subscribe instrument code -> Count==[{}]", subscribedInstrumentCodes.size());
                        subscribedInstrumentCodes.toArray(instrumentCodes);
                        gateway.SubscribeMarketData(instrumentCodes);
                        return true;
                    }
                } else {
                    String[] instrumentCodes = new String[instruments.length];
                    for (int i = 0; i < instruments.length; i++) {
                        instrumentCodes[i] = instruments[i].getInstrumentCode();
                        log.info("Add subscribe instrument -> instrumentCode==[{}]", instrumentCodes[i]);
                        subscribedInstrumentCodes.add(instrumentCodes[i]);
                    }
                    gateway.SubscribeMarketData(instrumentCodes);
                    return true;
                }
            } else {
                Arrays.stream(instruments)
                        .forEach(instrument -> subscribedInstrumentCodes.add(instrument.getInstrumentCode()));
                log.info("{} -> market not available, already recorded instrument code", gatewayId);
                log.info("subscribed instrument codes -> {}", JsonWrapper.toJson(subscribedInstrumentCodes));
                return false;
            }
        } catch (Exception e) {
            log.error("{} -> exec SubscribeMarketData has exception -> {}", gatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean newOrder(@Nonnull NewOrder request) {
        try {
            CThostFtdcInputOrderField field = orderConverter.convertToInputOrder(request);
            String orderRef = Integer.toString(OrderRefKeeper.nextOrderRef());
            // 设置OrderRef
            field.setOrderRef(orderRef);
            OrderRefKeeper.put(orderRef, request.getOrdSysId());
            gateway.ReqOrderInsert(field);
            return true;
        } catch (Exception e) {
            log.error("{} -> exec ReqOrderInsert has exception -> {}", gatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean cancelOrder(@Nonnull CancelOrder request) {
        try {
            CThostFtdcInputOrderActionField field = orderConverter.convertToInputOrderAction(request);
            String orderRef = OrderRefKeeper.getOrderRef(request.getOrdSysId());
            // 目前使用orderRef进行撤单
            field.setOrderRef(orderRef);
            field.setOrderActionRef(OrderRefKeeper.nextOrderRef());
            gateway.ReqOrderAction(field);
            return true;
        } catch (OrderRefNotFoundException e) {
            log.error(e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqOrderAction has exception -> {}", gatewayId, e.getMessage(), e);
            return false;
        }
    }

    // 查询互斥锁, 保证同时只进行一次查询, 满足监管要求
    private final Object mutex = new Object();

    // 查询间隔, 依据CTP规定限制
    private final long queryInterval = 1100L;

    @Override
    public boolean queryOrder(@Nonnull QueryOrder req) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryOrder-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                        sleep(queryInterval);
                        gateway.ReqQryOrder(req.getExchangeCode(), req.getInstrumentCode());
                        log.info("{} -> Has been sent ReqQryInvestorPosition", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqQryOrder has exception -> {}", gatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean queryPositions(@Nonnull QueryPositions req) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryPositions-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                        sleep(queryInterval);
                        gateway.ReqQryInvestorPosition(req.getExchangeCode(), req.getInstrumentCode());
                        log.info("{} -> Has been sent ReqQryInvestorPosition", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqQryInvestorPosition has exception -> {}", gatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean queryBalance(@Nonnull QueryBalance req) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryBalance-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryTradingAccount, Waiting...", adaptorId);
                        sleep(queryInterval);
                        gateway.ReqQryTradingAccount();
                        log.info("{} -> Has been sent ReqQryTradingAccount", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqQryTradingAccount has exception -> {}", gatewayId, e.getMessage(), e);
            return false;
        }
    }

    IdleStrategy idleStrategy = new SleepingIdleStrategy(100000);

    @Override
    public void close() throws IOException {
        try {
            gateway.close();
            if (queue != null) {
                while (!queue.isEmpty())
                    idleStrategy.idle();
            }
            log.info("{} -> already closed", adaptorId);
        } catch (Exception e) {
            log.error("{} -> exec close has exception -> {}", gatewayId, e.getMessage(), e);
            throw new IOException(e);
        }
    }

}
