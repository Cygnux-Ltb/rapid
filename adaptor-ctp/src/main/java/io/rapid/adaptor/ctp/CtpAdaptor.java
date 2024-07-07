package io.rapid.adaptor.ctp;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.concurrent.ring.RingEventbus.MpRingEventbus;
import io.mercury.common.state.AvailableTime;
import io.mercury.common.util.ArrayUtil;
import io.rapid.adaptor.ctp.component.CtpConfig;
import io.rapid.adaptor.ctp.component.OrderRefKeeper;
import io.rapid.adaptor.ctp.component.MarketDataConverter;
import io.rapid.adaptor.ctp.component.OrderConverter;
import io.rapid.adaptor.ctp.component.OrderEventConverter;
import io.rapid.adaptor.ctp.gateway.CtpMdGateway;
import io.rapid.adaptor.ctp.gateway.CtpTraderGateway;
import io.rapid.adaptor.ctp.gateway.event.FtdcEvent;
import io.rapid.adaptor.ctp.gateway.event.FtdcEventPublisher;
import io.rapid.core.account.Account;
import io.rapid.core.adaptor.AbstractAdaptor;
import io.rapid.core.adaptor.ConnectionMode;
import io.rapid.core.adaptor.TraderAdaptor;
import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.handler.OrderHandler;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.MarketDataFeed;
import io.rapid.core.serializable.avro.request.CancelOrder;
import io.rapid.core.serializable.avro.request.NewOrder;
import io.rapid.core.serializable.avro.request.QueryBalance;
import io.rapid.core.serializable.avro.request.QueryOrder;
import io.rapid.core.serializable.avro.request.QueryPositions;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewThread;
import static io.mercury.serialization.json.JsonWrapper.toJson;
import static java.time.LocalTime.of;
import static java.util.Arrays.stream;

@Component
public class CtpAdaptor extends AbstractAdaptor {

    private static final Logger log = getLogger(CtpAdaptor.class);

    @Resource
    private OrderRefKeeper orderRefKeeper;

    // 行情转换器
    @Resource
    private final MarketDataConverter marketDataConverter = new MarketDataConverter();

    // 转换订单回报
    @Resource
    private final OrderEventConverter orderEventConverter = new OrderEventConverter();

    // CTP Config
    @Resource
    private final CtpConfig config;

    @Resource
    private final FtdcEventPublisher publisher;

    // TODO 两个INT类型可以合并
    private volatile int frontId;
    private volatile int sessionId;

    private volatile boolean isMdAvailable;
    private volatile boolean isTraderAvailable;


    /**
     * 传入InboundScheduler实现, 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param account  Account
     * @param config   CtpConfig
     * @param mode     ConnectionMode
     * @param eventbus RingEventbus<FtdcEvent>
     */
    public CtpAdaptor(@Nonnull Account account, @Nonnull CtpConfig config,
                      @Nonnull ConnectionMode mode, @Nonnull MpRingEventbus<FtdcEvent> eventbus) {
        super(account);
        this.config = config;
        this.mode = mode;
        this.publisher = new FtdcEventPublisher(eventbus);
    }

    // FtdcMdGateway
    private CtpMdGateway mdGateway;
    private String mdGatewayId;

    // FtdcTraderGateway
    private CtpTraderGateway traderGateway;
    private String traderGatewayId;

    // FTDC报单请求转换器
    private OrderConverter orderConverter;

    @PostConstruct
    private CtpAdaptor initializer() {
        // 创建FtdcOrderConverter
        this.orderConverter = new OrderConverter(config);
        // 根据连接模式创建相应的Gateway
        switch (mode) {
            case MARKET_DATA -> {
                this.mdGateway = new CtpMdGateway(config, publisher);
                this.mdGatewayId = mdGateway.getGatewayId();
                log.info("MARKET_DATA MODE -> Create md gateway success, gatewayId -> {}", mdGatewayId);
            }
            case TRADE -> {
                this.traderGateway = new CtpTraderGateway(config, publisher);
                this.traderGatewayId = traderGateway.getGatewayId();
                log.info("TRADE MODE -> Create td gateway success, gatewayId -> {}", traderGatewayId);
            }
            default -> {
                this.mdGateway = new CtpMdGateway(config, publisher);
                this.mdGatewayId = mdGateway.getGatewayId();
                log.info("FULL MODE -> Create md gateway success, gatewayId -> {}", mdGatewayId);
                this.traderGateway = new CtpTraderGateway(config, publisher);
                this.traderGatewayId = traderGateway.getGatewayId();
                log.info("FULL MODE -> Create td gateway success, gatewayId -> {}", traderGatewayId);
            }
        }
        return this;
    }

    // 查询间隔
    private final long REQUEST_INTERVAL = 770;

    /**
     * 启动并挂起线程
     */
    @Override
    protected boolean startup0() {
        switch (mode) {
            case MARKET_DATA -> {
                try {
                    mdGateway.startup();
                    return true;
                } catch (Exception e) {
                    log.error("mdGateway.startup() has exception ", e);
                    return false;
                }
            }
            case TRADE -> {
                try {
                    traderGateway.startup();
                    return true;
                } catch (Exception e) {
                    log.error("");
                    return false;
                }
            }
            default -> {
                try {
                    mdGateway.startup();
                    traderGateway.startup();
                    return true;
                } catch (Exception e) {
                    log.error("");
                    return false;
                }
            }
        }
    }

    // 存储已订阅合约
    private final MutableSet<String> subscribedInstrumentCodes = MutableSets.newUnifiedSet();

    @Override
    public AvailableTime getAvailableTime() {
        return CtpAdaptorAvailableTime.INSTANCE;
    }

    /**
     * 订阅行情实现
     */
    @Override
    public boolean subscribeMarketData(@Nonnull Instrument... instruments) {
        try {
            if (isMdAvailable) {
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
                        mdGateway.nativeSubscribeMarketData(instrumentCodes);
                        return true;
                    }
                } else {
                    String[] instrumentCodes = new String[instruments.length];
                    for (int i = 0; i < instruments.length; i++) {
                        instrumentCodes[i] = instruments[i].getInstrumentCode();
                        log.info("Add subscribe instrument -> instrumentCode==[{}]", instrumentCodes[i]);
                        subscribedInstrumentCodes.add(instrumentCodes[i]);
                    }
                    mdGateway.nativeSubscribeMarketData(instrumentCodes);
                    return true;
                }
            } else {
                stream(instruments)
                        .forEach(instrument -> subscribedInstrumentCodes.add(instrument.getInstrumentCode()));
                log.warn("{} -> market not available, already recorded instrument code", mdGatewayId);
                log.info("subscribed instrument codes -> {}", toJson(subscribedInstrumentCodes));
                return false;
            }
        } catch (Exception e) {
            log.error("{} -> exec SubscribeMarketData has exception -> {}", mdGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean newOrder(@Nonnull NewOrder order) {
        try {
            CThostFtdcInputOrderField field = orderConverter.toInputOrder(order);
            String orderRef = Integer.toString(orderRefKeeper.nextOrderRef());
            // 设置OrderRef
            field.setOrderRef(orderRef);
            orderRefKeeper.put(orderRef, order.getOrdSysId());
            traderGateway.nativeReqOrderInsert(field);
            return true;
        } catch (Exception e) {
            log.error("{} -> exec ReqOrderInsert has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean cancelOrder(@javax.annotation.Nonnull CancelOrder order) {
        try {
            CThostFtdcInputOrderActionField field = orderConverter.toFtdcInputOrderAction(order);
            String orderRef = orderRefKeeper.getOrderRef(order.getOrdSysId());
            // 目前使用orderRef进行撤单
            field.setOrderRef(orderRef);
            field.setOrderActionRef(orderRefKeeper.nextOrderRef());
            traderGateway.nativeReqOrderAction(field);
            return true;
        } catch (OrderRefKeeper.OrderRefNotFoundException e) {
            log.error(e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqOrderAction has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    // 查询互斥锁, 保证同时只进行一次查询, 满足监管要求
    private final Object mutex = new Object();

    // 查询间隔, 依据CTP规定限制
    private final long queryInterval = 1050L;

    @Override
    public boolean queryOrder(@Nonnull QueryOrder query) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryOrder-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryOrder, Waiting...", adaptorId);
                        sleep(queryInterval);
                        traderGateway.nativeReqQryOrder(query.getExchangeCode(), query.getInstrumentCode());
                        log.info("{} -> Has been sent ReqQryOrder", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> Exec ReqQryOrder has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean queryPositions(@Nonnull QueryPositions query) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryPositions-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                        sleep(queryInterval);
                        traderGateway.nativeReqQryInvestorPosition(query.getExchangeCode(), query.getInstrumentCode());
                        log.info("{} -> Has been sent ReqQryInvestorPosition", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> Exec ReqQryInvestorPosition has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean queryBalance(@Nonnull QueryBalance query) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryBalance-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryTradingAccount, Waiting...", adaptorId);
                        sleep(queryInterval);
                        traderGateway.nativeReqQryTradingAccount();
                        log.info("{} -> Has been sent ReqQryTradingAccount", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> Exec ReqQryTradingAccount has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

//    private IdleStrategy idleStrategy = new SleepingIdleStrategy(100000);

    @Override
    public void close() throws IOException {
        try {
            publisher.publishMdUnavailable(-1);
            publisher.publishTraderUnavailable(-1);
            mdGateway.close();
            traderGateway.close();
        } catch (Exception e) {
            log.error("{} -> Exec close has exception -> {}", traderGatewayId, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public TraderAdaptor addOrderHandler(OrderHandler handler) {
        return null;
    }

    /**
     * @param handler MarketDataHandler
     * @return MarketDataFeed
     */
    @Override
    public MarketDataFeed addMarketDataHandler(MarketDataHandler handler) {
        return null;
    }

    /**
     *
     */
    public static class CtpAdaptorAvailableTime implements AvailableTime {

        public static final CtpAdaptorAvailableTime INSTANCE = new CtpAdaptorAvailableTime();

        private CtpAdaptorAvailableTime() {
        }

        @Override
        public boolean isRunningAllTime() {
            // 非全时间交易
            return false;
        }

        @Override
        public LocalTime[] getStartTimes() {
            return new LocalTime[]{
                    // 早盘开盘前10分钟
                    of(8, 50),
                    // 夜盘开盘前10分钟
                    of(20, 50)
            };
        }

        @Override
        public LocalTime[] getEndTimes() {
            return new LocalTime[]{
                    // 夜盘收盘后10分钟
                    of(15, 10),
                    // 夜盘收盘后10分钟
                    of(2, 40)};
        }
    }

}
