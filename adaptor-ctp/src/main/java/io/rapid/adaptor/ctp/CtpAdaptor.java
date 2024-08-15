package io.rapid.adaptor.ctp;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.concurrent.disruptor.RingEventbus.MultiProducerRingEventbus;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.AvailableTime;
import io.mercury.common.thread.Sleep;
import io.mercury.common.util.ArrayUtil;
import io.rapid.adaptor.ctp.gateway.CtpMdGateway;
import io.rapid.adaptor.ctp.gateway.CtpTraderGateway;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspEvent;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspHandler;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspPublisher;
import io.rapid.adaptor.ctp.param.CtpParams;
import io.rapid.core.account.Account;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.OrderRefAllocator;
import io.rapid.core.serializable.avro.outbound.CancelOrder;
import io.rapid.core.serializable.avro.outbound.NewOrder;
import io.rapid.core.serializable.avro.outbound.QueryBalance;
import io.rapid.core.serializable.avro.outbound.QueryOrder;
import io.rapid.core.serializable.avro.outbound.QueryPositions;
import io.rapid.core.upstream.AbstractAdaptor;
import io.rapid.core.upstream.ConnectionMode;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;

import java.io.IOException;

import static io.mercury.common.concurrent.disruptor.base.CommonStrategy.Yielding;
import static io.mercury.common.thread.ThreadSupport.startNewThread;
import static io.mercury.serialization.json.JsonWriter.toJson;
import static java.util.Arrays.stream;

public class CtpAdaptor extends AbstractAdaptor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpAdaptor.class);

    // CTP OrderRef分配器
    private final OrderRefAllocator orderRefAllocator;

    // 行情转换器
    private final MarketDataConverter marketDataConverter = new MarketDataConverter();

    // 转换订单回报
    private final OrderEventConverter orderEventConverter = new OrderEventConverter();

    // CTP Params
    private final CtpParams params;

    private final FtdcRspPublisher publisher;

    // TODO 两个INT类型可以合并
    private volatile int frontId;
    private volatile int sessionId;

    private volatile boolean isMdAvailable;
    private volatile boolean isTraderAvailable;


    /**
     * 传入FtdcEventHandler实现, 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param account Account
     * @param params  CtpParam
     * @param mode    ConnectionMode
     * @param handler FtdcEventHandler
     */
    protected CtpAdaptor(@Nonnull Account account, @Nonnull CtpParams params,
                         @Nonnull ConnectionMode mode, @Nonnull OrderRefAllocator orderRefAllocator,
                         @Nonnull FtdcRspHandler handler) {
        this(account, params, mode, orderRefAllocator,
                RingEventbus.multiProducer(FtdcRspEvent.EVENT_FACTORY)
                        .size(512).name("internal-ctp-eventbus")
                        .waitStrategy(Yielding.get())
                        .process(handler)
        );
    }


    /**
     * 传入MpRingEventbus实现, 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param account  Account
     * @param params   CtpParam
     * @param mode     ConnectionMode
     * @param eventbus MpRingEventbus<FtdcEvent>
     */
    protected CtpAdaptor(@Nonnull Account account, @Nonnull CtpParams params,
                         @Nonnull ConnectionMode mode, @Nonnull OrderRefAllocator orderRefAllocator,
                         @Nonnull MultiProducerRingEventbus<FtdcRspEvent> eventbus) {
        super(account);
        this.params = params;
        this.mode = mode;
        this.orderRefAllocator = orderRefAllocator;
        this.publisher = new FtdcRspPublisher(eventbus);
        log.info("Adaptor -> {}, Mode -> {}, Use Account -> {}", getAdaptorId(), mode, account);
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
        this.orderConverter = new OrderConverter(params);
        // 根据连接模式创建相应的Gateway
        switch (mode) {
            case MARKET_DATA -> {
                this.mdGateway = new CtpMdGateway(params, publisher);
                this.mdGatewayId = mdGateway.getGatewayId();
                log.info("MARKET_DATA MODE -> Create md gateway success, gatewayId -> {}", mdGatewayId);
            }
            case TRADE -> {
                this.traderGateway = new CtpTraderGateway(params, publisher);
                this.traderGatewayId = traderGateway.getGatewayId();
                log.info("TRADE MODE -> Create td gateway success, gatewayId -> {}", traderGatewayId);
            }
            default -> {
                this.mdGateway = new CtpMdGateway(params, publisher);
                this.mdGatewayId = mdGateway.getGatewayId();
                log.info("FULL MODE -> Create md gateway success, gatewayId -> {}", mdGatewayId);
                this.traderGateway = new CtpTraderGateway(params, publisher);
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
                        mdGateway.ftdcSubscribeMarketData(instrumentCodes);
                        return true;
                    }
                } else {
                    String[] instrumentCodes = new String[instruments.length];
                    for (int i = 0; i < instruments.length; i++) {
                        instrumentCodes[i] = instruments[i].getInstrumentCode();
                        log.info("Add subscribe instrument -> instrumentCode==[{}]", instrumentCodes[i]);
                        subscribedInstrumentCodes.add(instrumentCodes[i]);
                    }
                    mdGateway.ftdcSubscribeMarketData(instrumentCodes);
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
            log.error("{} -> Native SubscribeMarketData has exception -> {}", mdGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean newOrder(@Nonnull NewOrder order) {
        try {
            CThostFtdcInputOrderField ReqField = orderConverter.toInputOrder(order);
            String orderRef = Integer.toString(orderRefAllocator.nextOrderRef());
            // 设置OrderRef
            ReqField.setOrderRef(orderRef);
            orderRefAllocator.related(orderRef, order.getOrdSysId());
            traderGateway.ftdcReqOrderInsert(ReqField);
            return true;
        } catch (Exception e) {
            log.error("{} -> Native ReqOrderInsert has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean cancelOrder(@Nonnull CancelOrder order) {
        try {
            CThostFtdcInputOrderActionField ReqField = orderConverter.toFtdcInputOrderAction(order);
            String orderRef = orderRefAllocator.getOrderRef(order.getOrdSysId());
            // 目前使用orderRef进行撤单
            ReqField.setOrderRef(orderRef);
            ReqField.setOrderActionRef(orderRefAllocator.nextOrderRef());
            traderGateway.ftdcReqOrderAction(ReqField);
            return true;
        } catch (FastOrderRefAllocator.OrderRefNotFoundException e) {
            log.error(e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("{} -> Native ReqOrderAction has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    // 查询互斥锁, 保证同时只进行一次查询, 以此符合满足监管要求
    private final Object mutex = new Object();

    // 查询间隔, 依据CTP规定限制
    private final long queryInterval = 1100L;

    @Override
    public boolean queryOrder(@Nonnull QueryOrder query) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryOrder-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryOrder, Waiting...", adaptorId);
                        Sleep.millis(queryInterval);
                        traderGateway.ftdcReqQryOrder();
                        log.info("{} -> Has been sent ReqQryOrder", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> Native ReqQryOrder has exception -> {}", traderGatewayId, e.getMessage(), e);
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
                        Sleep.millis(queryInterval);
                        traderGateway.ftdcReqQryInvestorPosition(query.getExchangeCode(), query.getInstrumentCode());
                        log.info("{} -> Has been sent ReqQryInvestorPosition", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> Native ReqQryInvestorPosition has exception -> {}", traderGatewayId, e.getMessage(), e);
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
                        Sleep.millis(queryInterval);
                        traderGateway.ftdcReqQryTradingAccount();
                        log.info("{} -> Has been sent ReqQryTradingAccount", adaptorId);
                    }
                });
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error("{} -> Native ReqQryTradingAccount has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }


    @Override
    public void close() throws IOException {
        try {
            // publisher.publishMdUnavailable(-1);
            // publisher.publishTraderUnavailable(-1);
            mdGateway.close();
            traderGateway.close();
        } catch (Exception e) {
            log.error("{} -> Native close has exception -> {}", traderGatewayId, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    public static Builder builder(Account account, CtpParams params) {
        return new Builder(account, params);
    }


    public static class Builder {

        private final Account account;
        private final CtpParams params;
        private ConnectionMode mode = ConnectionMode.FULL;
        private FastOrderRefAllocator orderRefAllocator = new FastOrderRefAllocator();

        private Builder(Account account, CtpParams params) {
            this.account = account;
            this.params = params;
        }

        public void setMode(ConnectionMode mode) {
            this.mode = mode;
        }

        public Builder setOrderRefKeeper(FastOrderRefAllocator orderRefAllocator) {
            this.orderRefAllocator = orderRefAllocator;
            return this;
        }

        public CtpAdaptor build(MultiProducerRingEventbus<FtdcRspEvent> eventbus) {
            return new CtpAdaptor(account, params, mode, orderRefAllocator, eventbus);
        }

        public CtpAdaptor build(FtdcRspHandler handler) {
            return new CtpAdaptor(account, params, mode, orderRefAllocator, handler);
        }
    }

}
