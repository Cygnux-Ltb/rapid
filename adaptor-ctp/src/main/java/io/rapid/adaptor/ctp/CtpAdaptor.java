package io.rapid.adaptor.ctp;

import io.mercury.common.collections.MutableSets;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.Sleep;
import io.rapid.adaptor.ctp.gateway.CtpMdGateway;
import io.rapid.adaptor.ctp.gateway.CtpTraderGateway;
import io.rapid.adaptor.ctp.gateway.NativeLibraryLoader;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspEvent;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspPublisher;
import io.rapid.adaptor.ctp.param.CtpParams;
import io.rapid.adaptor.ctp.serializable.source.EventSource;
import io.rapid.core.account.Account;
import io.rapid.core.adaptor.AbstractAdaptor;
import io.rapid.core.adaptor.AdaptorRunningMode;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import io.rapid.core.order.OrderRefAllocator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderField;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.thread.ThreadSupport.startNewThread;

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

    // FtdcRsp发布器
    private final FtdcRspPublisher publisher;

    // 是否已初始化, 用于控制本地库文件加载
    protected AtomicBoolean initialized = new AtomicBoolean(false);

    // TODO 两个INT类型可以合并
    private volatile int frontId;
    private volatile int sessionId;

    // TODO TEMP
    private volatile boolean isMdAvailable = true;
    private volatile boolean isTraderAvailable = true;

    // FtdcMdGateway
    private CtpMdGateway mdGateway;
    private String mdGatewayId;

    // FtdcTraderGateway
    private CtpTraderGateway traderGateway;
    private String traderGatewayId;

    // FTDC报单请求转换器
    private OrderConverter orderConverter;

    // 外部扩展处理器
    private final FtdcRspHandler extHandler;

    /**
     * 传入[FtdcRspHandler]实现
     *
     * @param builder    Builder
     * @param extHandler FtdcEventHandler
     */
    private CtpAdaptor(@Nonnull Builder builder,
                       @Nonnull FtdcRspHandler extHandler) {
        super(builder.account, builder.isAsync);
        this.params = builder.params;
        this.runningMode = builder.runningMode;
        this.orderRefAllocator = builder.orderRefAllocator;
        this.extHandler = extHandler;
        this.publisher = new FtdcRspPublisher(this::processEvent);
        log.info("Adaptor==[{}], Mode==[{}], isAsync==[{}], Account -> {}",
                getAdaptorId(), runningMode, builder.isAsync, account);
    }

    private void processEvent(FtdcRspEvent event) {
        switch (event.getType()) {
            case MdClosed -> this.isMdAvailable = false;
            case TraderClosed -> this.isTraderAvailable = false;
        }
        extHandler.handle(event);
    }

    @PostConstruct
    private CtpAdaptor initializer() {
        if (initialized.compareAndSet(false, true)) {
            log.info("Adaptor -> {}, Mode -> {}", getAdaptorId(), runningMode);
            // 加载NativeLibrary
            try {
                NativeLibraryLoader.tryLoad();
            } catch (NativeLibraryException e) {
                log.error(e.getMessage(), e);
                log.error("CTP native library file loading error, System must exit, Status -1");
                System.exit(-1);
            }
        } else {
            log.info("Adaptor -> {}, Mode -> {}, Already initialized", getAdaptorId(), runningMode);
            return this;
        }
        // 创建FtdcOrderConverter
        this.orderConverter = new OrderConverter(params);
        // 根据连接模式创建相应的Gateway
        switch (runningMode) {
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
        initializer();
        switch (runningMode) {
            case MARKET_DATA -> {
                try {
                    mdGateway.startup();
                    return true;
                } catch (Exception e) {
                    log.error("CtpMdGateway::startup has exception from {}, Cause by -> {}",
                            mdGatewayId, e.getMessage(), e);
                    return false;
                }
            }
            case TRADE -> {
                try {
                    traderGateway.startup();
                    return true;
                } catch (Exception e) {
                    log.error("CtpTraderGateway::startup has exception from {}, Cause by -> {}",
                            traderGatewayId, e.getMessage(), e);
                    return false;
                }
            }
            default -> {
                try {
                    mdGateway.startup();
                } catch (Exception e) {
                    log.error("CtpMdGateway::startup has exception from {}, Cause by -> {}",
                            mdGatewayId, e.getMessage(), e);
                    return false;
                }
                try {
                    traderGateway.startup();
                } catch (Exception e) {
                    log.error("CtpTraderGateway::startup has exception from {}, Cause by -> {}",
                            traderGatewayId, e.getMessage(), e);
                    return false;
                }
                return true;
            }
        }
    }

    /**
     * 订阅行情实现
     */
    @Override
    protected boolean directSubscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {
        try {
            // 去除重复合约代码
            if (isMdAvailable) {
                var instrumentCodeSet = MutableSets.newUnifiedSet(subscribeMarketData.getInstrumentCodes());
                if (instrumentCodeSet.isEmpty()) {
                    // 输入的[InstrumentCodes]为空
                    log.warn("{} -> Input instrument code array is null or empty", adaptorId);
                    return false;
                }
                mdGateway.SubscribeMarketData(instrumentCodeSet
                        .stream()
                        .peek(code -> log.info("Subscribe instrument -> instrumentCode==[{}]", code))
                        .toArray(String[]::new));
                return true;
            } else {
                log.warn("{} -> market not available, isMdAvailable==[{}]", mdGatewayId, isMdAvailable);
                return false;
            }
        } catch (Exception e) {
            log.error("{} -> SubscribeMarketData has exception -> {}", mdGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    protected boolean directNewOrder(@Nonnull NewOrder order) {
        try {
            CThostFtdcInputOrderField ReqField = orderConverter.toInputOrder(order);
            String orderRef = Integer.toString(orderRefAllocator.nextOrderRef());
            // 设置OrderRef
            ReqField.setOrderRef(orderRef);
            orderRefAllocator.related(orderRef, order.getOrdSysId());
            traderGateway.ReqOrderInsert(ReqField);
            return true;
        } catch (Exception e) {
            log.error("{} -> Native ReqOrderInsert has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    protected boolean directCancelOrder(@Nonnull CancelOrder order) {
        try {
            CThostFtdcInputOrderActionField ReqField = orderConverter.toFtdcInputOrderAction(order);
            String orderRef = orderRefAllocator.getOrderRef(order.getOrdSysId());
            // 目前使用orderRef进行撤单
            ReqField.setOrderRef(orderRef);
            ReqField.setOrderActionRef(orderRefAllocator.nextOrderRef());
            traderGateway.ReqOrderAction(ReqField);
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
    private final long queryInterval = 1200L;

    @Override
    protected boolean directQueryOrder(@Nonnull QueryOrder query) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryOrder-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryOrder, Waiting...", adaptorId);
                        Sleep.millis(queryInterval);
                        traderGateway.ReqQryOrder();
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
    protected boolean directQueryPositions(@Nonnull QueryPosition query) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryPositions-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                        Sleep.millis(queryInterval);
                        traderGateway.ReqQryInvestorPosition(query.getExchangeCode(), query.getInstrumentCode());
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
    protected boolean directQueryBalance(@Nonnull QueryBalance query) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryBalance-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryTradingAccount, Waiting...", adaptorId);
                        Sleep.millis(queryInterval);
                        traderGateway.ReqQryTradingAccount();
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
            publisher.publishFrontDisconnected(EventSource.MD, 1, params.getBrokerId(), params.getUserId());
            publisher.publishFrontDisconnected(EventSource.TD, 1, params.getBrokerId(), params.getUserId());
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
        private boolean isAsync = false;
        private AdaptorRunningMode runningMode = AdaptorRunningMode.FULL;
        private OrderRefAllocator orderRefAllocator = new FastOrderRefAllocator();

        private Builder(Account account, CtpParams params) {
            this.account = account;
            this.params = params;
        }

        public Builder setRunningMode(AdaptorRunningMode runningMode) {
            this.runningMode = runningMode;
            return this;
        }

        public Builder setOrderRefKeeper(OrderRefAllocator orderRefAllocator) {
            this.orderRefAllocator = orderRefAllocator;
            return this;
        }

        public Builder asyncMode() {
            this.isAsync = true;
            return this;
        }

        public CtpAdaptor build(FtdcRspHandler handler) {
            return new CtpAdaptor(this, handler);
        }

    }

}
