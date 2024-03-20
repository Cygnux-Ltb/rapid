package io.cygnuxltb.adaptor.ctp;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.cygnuxltb.adaptor.ctp.OrderRefKeeper.OrderRefNotFoundException;
import io.cygnuxltb.adaptor.ctp.converter.MarketDataConverter;
import io.cygnuxltb.adaptor.ctp.converter.OrderConverter;
import io.cygnuxltb.adaptor.ctp.converter.OrderEventConverter;
import io.cygnuxltb.adaptor.ctp.gateway.FtdcMdGateway;
import io.cygnuxltb.adaptor.ctp.gateway.FtdcTraderGateway;
import io.cygnuxltb.adaptor.ctp.gateway.event.FtdcEvent;
import io.cygnuxltb.adaptor.ctp.gateway.event.FtdcEventPublisher;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.concurrent.ring.RingEventbus;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.ArrayUtil;
import io.rapid.core.account.Account;
import io.rapid.core.adaptor.AbstractAdaptor;
import io.rapid.core.adaptor.Adaptor;
import io.rapid.core.adaptor.AdaptorAvailableTime;
import io.rapid.core.adaptor.ConnectionMode;
import io.rapid.core.adaptor.MarketDataFeed;
import io.rapid.core.adaptor.TraderAdaptor;
import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.handler.OrderHandler;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.protocol.avro.request.CancelOrder;
import io.rapid.core.protocol.avro.request.NewOrder;
import io.rapid.core.protocol.avro.request.QueryBalance;
import io.rapid.core.protocol.avro.request.QueryOrder;
import io.rapid.core.protocol.avro.request.QueryPositions;
import jakarta.annotation.PostConstruct;
import org.agrona.concurrent.IdleStrategy;
import org.agrona.concurrent.SleepingIdleStrategy;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;

import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewThread;
import static io.mercury.serialization.json.JsonWrapper.toJson;
import static java.util.Arrays.stream;

/**
 * CTP Adaptor, 用于连接上期CTP柜台
 *
 * @author yellow013
 */
public class AdvCtpAdaptor extends AbstractAdaptor {

    @Override
    public MarketDataFeed setMarketDataHandler(MarketDataHandler handler) {
        return null;
    }

    private static final Logger log = Log4j2LoggerFactory.getLogger(AdvCtpAdaptor.class);

    // 行情转换器
    private final MarketDataConverter marketDataConverter = new MarketDataConverter();

    // 转换订单回报
    private final OrderEventConverter orderEventConverter = new OrderEventConverter();

    // CTP Config
    private final CtpConfig config;

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
    private AdvCtpAdaptor(@Nonnull Account account, @Nonnull CtpConfig config,
                          @Nonnull ConnectionMode mode, @Nonnull RingEventbus<FtdcEvent> eventbus) {
        super(AdvCtpAdaptor.class.getSimpleName(), account);
        this.config = config;
        this.mode = mode;
        this.publisher = new FtdcEventPublisher(eventbus);
        initializer();
    }

    // FtdcMdGateway
    private FtdcMdGateway mdGateway;
    private String mdGatewayId;

    // FtdcTraderGateway
    private FtdcTraderGateway traderGateway;
    private String traderGatewayId;

    // FTDC报单请求转换器
    private OrderConverter orderConverter;

    @PostConstruct
    private void initializer() {
        // 创建FtdcOrderConverter
        this.orderConverter = new OrderConverter(config);
        // 根据连接模式创建相应的Gateway
        switch (mode) {
            case MARKET_DATA -> {
                this.mdGateway = new FtdcMdGateway(config, publisher);
                this.mdGatewayId = mdGateway.getGatewayId();
                log.info("Create md gateway success, gatewayId -> {}", mdGatewayId);
            }
            case TRADE -> {
                this.traderGateway = new FtdcTraderGateway(config, publisher);
                this.traderGatewayId = traderGateway.getGatewayId();
                log.info("Create td gateway success, gatewayId -> {}", traderGatewayId);
            }
            default -> {
                this.mdGateway = new FtdcMdGateway(config, publisher);
                this.mdGatewayId = mdGateway.getGatewayId();
                log.info("Create md gateway success, gatewayId -> {}", mdGatewayId);
                this.traderGateway = new FtdcTraderGateway(config, publisher);
                this.traderGatewayId = traderGateway.getGatewayId();
                log.info("Create td gateway success, gatewayId -> {}", traderGatewayId);
            }
        }
    }

    // 查询间隔
    private final long REQUEST_INTERVAL = 750;

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
                    log.error("");
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
    public AdaptorAvailableTime getAvailableTime() {
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
                        mdGateway.SubscribeMarketData(instrumentCodes);
                        return true;
                    }
                } else {
                    String[] instrumentCodes = new String[instruments.length];
                    for (int i = 0; i < instruments.length; i++) {
                        instrumentCodes[i] = instruments[i].getInstrumentCode();
                        log.info("Add subscribe instrument -> instrumentCode==[{}]", instrumentCodes[i]);
                        subscribedInstrumentCodes.add(instrumentCodes[i]);
                    }
                    mdGateway.SubscribeMarketData(instrumentCodes);
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
            String orderRef = Integer.toString(OrderRefKeeper.nextOrderRef());
            // 设置OrderRef
            field.setOrderRef(orderRef);
            OrderRefKeeper.put(orderRef, order.getOrdSysId());
            traderGateway.ReqOrderInsert(field);
            return true;
        } catch (Exception e) {
            log.error("{} -> exec ReqOrderInsert has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean cancelOrder(@Nonnull CancelOrder order) {
        try {
            CThostFtdcInputOrderActionField field = orderConverter.toFtdcInputOrderAction(order);
            String orderRef = OrderRefKeeper.getOrderRef(order.getOrdSysId());
            // 目前使用orderRef进行撤单
            field.setOrderRef(orderRef);
            field.setOrderActionRef(OrderRefKeeper.nextOrderRef());
            traderGateway.ReqOrderAction(field);
            return true;
        } catch (OrderRefNotFoundException e) {
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
                        log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                        sleep(queryInterval);
                        traderGateway.ReqQryOrder(query.getExchangeCode(), query.getInstrumentCode());
                        log.info("{} -> Has been sent ReqQryInvestorPosition", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqQryOrder has exception -> {}", traderGatewayId, e.getMessage(), e);
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
                        traderGateway.ReqQryInvestorPosition(query.getExchangeCode(), query.getInstrumentCode());
                        log.info("{} -> Has been sent ReqQryInvestorPosition", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqQryInvestorPosition has exception -> {}", traderGatewayId, e.getMessage(), e);
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
                        traderGateway.ReqQryTradingAccount();
                        log.info("{} -> Has been sent ReqQryTradingAccount", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> exec ReqQryTradingAccount has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    IdleStrategy idleStrategy = new SleepingIdleStrategy(100000);

    @Override
    public void close() throws IOException {
        try {
            publisher.publishMdUnavailable(-1);
            publisher.publishTraderUnavailable(-1);
            mdGateway.close();
            traderGateway.close();
        } catch (Exception e) {
            log.error("{} -> exec close has exception -> {}", traderGatewayId, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public TraderAdaptor setOrderHandler(OrderHandler handler) {
        return null;
    }


    public static DSL dsl() {
        return new DSL();
    }


    public static class DSL {

        private Account account;
        private CtpConfig config;
        private ConnectionMode mode = ConnectionMode.ALL;

        public void account(Account account) {
            this.account = account;
        }

        public void config(CtpConfig config) {
            this.config = config;
        }

        public void mode(ConnectionMode mode) {
            this.mode = mode;
        }

        public Adaptor build(RingEventbus<FtdcEvent> eventbus) {
            return new AdvCtpAdaptor(account, config, mode, eventbus);
        }

    }

}
