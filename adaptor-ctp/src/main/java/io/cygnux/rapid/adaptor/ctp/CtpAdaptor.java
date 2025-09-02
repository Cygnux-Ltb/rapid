package io.cygnux.rapid.adaptor.ctp;

import io.cygnux.rapid.adaptor.ctp.event.FtdcRspEvent;
import io.cygnux.rapid.adaptor.ctp.event.FtdcRspPublisher;
import io.cygnux.rapid.adaptor.ctp.event.source.EventSource;
import io.cygnux.rapid.adaptor.ctp.gateway.FtdcMdGateway;
import io.cygnux.rapid.adaptor.ctp.gateway.FtdcTraderGateway;
import io.cygnux.rapid.adaptor.ctp.gateway.NativeLibraryLoader;
import io.cygnux.rapid.adaptor.ctp.param.FtdcParams;
import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.adaptor.AbstractAdaptor;
import io.cygnux.rapid.core.adaptor.AdaptorRunningMode;
import io.cygnux.rapid.core.stream.StreamEventHandler;
import io.cygnux.rapid.core.stream.enums.AdaptorType;
import io.cygnux.rapid.core.stream.event.AdaptorReport;
import io.cygnux.rapid.core.adaptor.event.CancelOrder;
import io.cygnux.rapid.core.adaptor.event.NewOrder;
import io.cygnux.rapid.core.adaptor.event.QueryBalance;
import io.cygnux.rapid.core.adaptor.event.QueryOrder;
import io.cygnux.rapid.core.adaptor.event.QueryPosition;
import io.cygnux.rapid.core.adaptor.event.SubscribeMarketData;
import io.cygnux.rapid.core.order.OrderRefKeeper;
import io.cygnux.rapid.core.order.OrderRefNotFoundException;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.thread.Sleep;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.thread.Fibers.startNewFiber;
import static java.lang.System.currentTimeMillis;

public class CtpAdaptor extends AbstractAdaptor {

    // CTP OrderRef分配器
    private final OrderRefKeeper orderRefKeeper;

    // CTP Params
    private final FtdcParams params;

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
    private FtdcMdGateway mdGateway;
    private String mdGatewayId;

    // FtdcTraderGateway
    private FtdcTraderGateway traderGateway;
    private String traderGatewayId;

    // [系统Order]==>[FTDC报单请求]转换器
    private final FtdcReqConverter ftdcReqConverter;

    // [FTDC响应]==>[系统Event]转换器
    private final FtdcRspConverter ftdcRspConverter;

    // 是否有扩展处理器
    private final boolean hasExtFtdcRspHandler;

    // 扩展FTDC应答处理器
    private final FtdcRspHandler extFtdcRspHandler;


    /**
     * 传入[FtdcRspHandler]实现
     *
     * @param builder           Builder
     * @param extFtdcRspHandler FtdcEventHandler
     * @param inboundHandler    InboundEventHandler
     */
    private CtpAdaptor(@Nonnull Builder builder,
                       @Nonnull StreamEventHandler inboundHandler,
                       @Nullable FtdcRspHandler extFtdcRspHandler) {
        super(builder.account, builder.isAsync, inboundHandler);
        this.params = builder.params;
        this.runningMode = builder.runningMode;
        this.orderRefKeeper = builder.orderRefKeeper;
        this.extFtdcRspHandler = extFtdcRspHandler;
        // 传递[OrderRefKeeper]实现
        if (extFtdcRspHandler != null) {
            this.hasExtFtdcRspHandler = true;
            ((FtdcRspHandlerImpl) extFtdcRspHandler).setOrderRefKeeper(orderRefKeeper);
        } else
            this.hasExtFtdcRspHandler = false;
        this.publisher = new FtdcRspPublisher(this::processFtdcRspEvent);
        this.ftdcReqConverter = new FtdcReqConverter(builder.params);
        this.ftdcRspConverter = new FtdcRspConverter(builder.params);
        log.info("Adaptor==[{}], Mode==[{}], isAsync==[{}], Account -> {}",
                getAdaptorId(), runningMode, builder.isAsync, account);
    }

    /**
     * 事件循环主要处理逻辑
     *
     * @param event FtdcRspEvent
     */
    private void processFtdcRspEvent(FtdcRspEvent event) {
        switch (event.getType()) {
            case FRONT_DISCONNECTED -> {
                var received = event.getFrontDisconnected();
                var report = createAdaptorReportAndUpdate(received.Source,
                        false, received.Msg);
                log.info("Received [FrontDisconnected] convert to [AdaptorReport] -> {}", report);
                streamEventbus.put(report);
            }
            case RSP_USER_LOGIN -> {
                var received = event.getRspUserLogin();
                var report = createAdaptorReportAndUpdate(received.Source,
                        true, received.ErrorMsg);
                ftdcReqConverter.setTradingDay(received.TradingDay);
                ftdcRspConverter.setTradingDay(received.TradingDay);
                log.info("Updated [ReqConverter]&[RspConverter] internal TradingDay==[{}]", received.TradingDay);
                log.info("Received [RspUserLogin] convert to [AdaptorReport] -> {}", report);
                streamEventbus.put(report);
            }
            case USER_LOGOUT -> {
                var received = event.getUserLogout();
                var report = createAdaptorReportAndUpdate(received.Source,
                        false, received.ErrorMsg);
                log.info("Received [UserLogout] convert to [AdaptorReport] -> {}", report);
                streamEventbus.put(report);
            }
            case FTDC_DEPTH_MARKET_DATA -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getDepthMarketData()));

            case FTDC_SPECIFIC_INSTRUMENT -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getSpecificInstrument()));

            case FTDC_INSTRUMENT_STATUS -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getInstrumentStatus()));

            case FTDC_INPUT_ORDER -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getInputOrder()));

            case FTDC_INPUT_ORDER_ACTION -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getInputOrderAction()));

            case FTDC_INVESTOR_POSITION -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getInvestorPosition()));

            case FTDC_ORDER -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getOrder()));

            case FTDC_ORDER_ACTION -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getOrderAction()));

            case FTDC_TRADE -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getTrade()));

            case FTDC_TRADING_ACCOUNT -> streamEventbus
                    .put(ftdcRspConverter.convert(event.getTradingAccount()));

            case HEARTBEAT_WARNING -> log
                    .warn("Received [HeartBeatWarning] Event -> {}", event.getHeartBeatWarning());

            case RSP_ERROR -> log.warn("Received [RspError] Event -> {}", event.getRspError());

            case UNSUPPORTED -> log.error("Received Unsupported Event");

            case null -> log.error("Received NULL Event");
        }
        /// 扩展FTDC应答事件处理, 用于进行转发或DUMP
        if (hasExtFtdcRspHandler)
            extFtdcRspHandler.handle(event);
    }


    /**
     * 更新当前Adaptor状态, 并且设置AdaptorReport的通道类型
     *
     * @param source      EventSource
     * @param isAvailable boolean
     * @param msg         String
     */
    public AdaptorReport createAdaptorReportAndUpdate(EventSource source, boolean isAvailable,
                                                      String msg) {
        var report = AdaptorReport.builder();
        if (source == EventSource.MD) {
            this.isMdAvailable = isAvailable;
            report.adaptorType(AdaptorType.MARKET_DATA);
        } else if (source == EventSource.TD) {
            this.isTraderAvailable = isAvailable;
            report.adaptorType(AdaptorType.TRADING);
        }
        return report.adaptorId(adaptorId)
                .accountId(account.getAccountId())
                .epochMillis(currentTimeMillis())
                .msg(msg == null ? "" : msg)
                .isAvailable(isAvailable).build();
    }

    @PostConstruct
    private CtpAdaptor initializer() {
        if (initialized.compareAndSet(false, true)) {
            log.info("Adaptor -> {}, Mode -> {}", getAdaptorId(), runningMode);
            // 加载NativeLibrary
            NativeLibraryLoader.tryLoadOrCrash();
            // 根据连接模式创建相应的Gateway
            switch (runningMode) {
                case MARKET_DATA -> initMdGateway();
                case TRADE -> initTraderGateway();
                default -> {
                    initMdGateway();
                    initTraderGateway();
                }
            }
        } else {
            log.info("Adaptor -> {}, Mode -> {}, Already initialized", getAdaptorId(), runningMode);
            return this;
        }
        return this;
    }

    /**
     * 初始化[FtdcMdGateway]
     */
    private void initMdGateway() {
        this.mdGateway = new FtdcMdGateway(params, publisher);
        this.mdGatewayId = mdGateway.getGatewayId();
        log.info("{} MODE -> Create [FtdcMdGateway] success, gatewayId -> {}", runningMode, mdGatewayId);
    }

    /**
     * 初始化[FtdcTraderGateway]
     */
    private void initTraderGateway() {
        this.traderGateway = new FtdcTraderGateway(params, publisher);
        this.traderGatewayId = traderGateway.getGatewayId();
        log.info("{} MODE -> Create [FtdcTraderGateway] success, gatewayId -> {}", runningMode, traderGatewayId);
    }

    /**
     * 启动并挂起线程
     */
    @Override
    protected boolean startup0() {
        initializer();
        return switch (runningMode) {
            case MARKET_DATA -> startupMdGateway();
            case TRADE -> startupTraderGateway();
            default -> startupMdGateway() && startupTraderGateway();
        };
    }

    private boolean startupMdGateway() {
        try {
            mdGateway.startup();
            return true;
        } catch (Exception e) {
            log.error("FtdcMdGateway::startup has exception from {}, Cause by -> {}",
                    mdGatewayId, e.getMessage(), e);
            return false;
        }
    }

    private boolean startupTraderGateway() {
        try {
            traderGateway.startup();
            return true;
        } catch (Exception e) {
            log.error("FtdcTraderGateway::startup has exception from {}, Cause by -> {}",
                    traderGatewayId, e.getMessage(), e);
            return false;
        }
    }


    /**
     * 订阅行情实现
     */
    @Override
    protected boolean directSubscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {
        try {
            if (isMdAvailable) {
                // 去除重复合约代码
                var instrumentCodeSet = MutableSets.newUnifiedSet(subscribeMarketData.getInstrumentCodes());
                if (instrumentCodeSet.isEmpty()) {
                    // 输入的[InstrumentCodes]为空
                    log.warn("{} -> Input instrument code array is null or empty", adaptorId);
                    return false;
                }
                var instrumentCodes = new String[instrumentCodeSet.size()];
                int i = -1;
                for (String code : instrumentCodeSet) {
                    log.info("Subscribe instrument -> instrumentCode==[{}]", code);
                    instrumentCodes[++i] = code;
                }
                mdGateway.nativeSubscribeMarketData(instrumentCodes);
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
            var field = ftdcReqConverter.convertTo(order);
            String orderRef = Integer.toString(orderRefKeeper.nextOrderRef());
            // 设置OrderRef
            field.setOrderRef(orderRef);
            orderRefKeeper.binding(orderRef, order.getOrdSysId());
            traderGateway.nativeReqOrderInsert(field);
            return true;
        } catch (Exception e) {
            log.error("{} -> Native ReqOrderInsert has exception -> {}", traderGatewayId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    protected boolean directCancelOrder(@Nonnull CancelOrder order) {
        try {
            var field = ftdcReqConverter.convertTo(order);
            String orderRef = orderRefKeeper.getOrderRef(order.getOrdSysId());
            int orderActionRef = orderRefKeeper.nextOrderRef();
            // 设置[OrderRef]
            field.setOrderRef(orderRef);
            // 设置[OrderActionRef]
            field.setOrderActionRef(orderActionRef);
            traderGateway.nativeReqOrderAction(field);
            return true;
        } catch (OrderRefNotFoundException e) {
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
    private static final long QUERY_INTERVAL = 1100L;

    @Override
    protected boolean directQueryOrder(@Nonnull QueryOrder query) {
        log.info("Adaptor::directQueryOrder calling, adaptorId==[{}], QueryOrder -> {}", adaptorId, query);
        if (isTraderAvailable) {
            try {
                startNewFiber("QueryOrder-Worker",
                        () -> {
                            synchronized (mutex) {
                                log.info("{} -> Ready to sent ReqQryOrder, Waiting...", adaptorId);
                                Sleep.millis(QUERY_INTERVAL);
                                traderGateway.nativeReqQryOrder();
                                log.info("{} -> Has been sent ReqQryOrder", adaptorId);
                            }
                        });
                return true;
            } catch (Exception e) {
                log.error("{} -> Native ReqQryOrder has exception -> {}", traderGatewayId, e.getMessage(), e);
                return false;
            }
        } else {
            log.error("Adaptor::directQueryOrder unable to call, adaptorId==[{}], isTraderAvailable==[{}]",
                    adaptorId, isTraderAvailable);
            return false;
        }
    }

    @Override
    protected boolean directQueryPosition(@Nonnull QueryPosition query) {
        log.info("Adaptor::directQueryPositions calling, adaptorId==[{}], QueryPosition -> {}", adaptorId, query);
        if (isTraderAvailable) {
            try {
                startNewFiber("QueryPositions-Worker",
                        () -> {
                            synchronized (mutex) {
                                log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                                Sleep.millis(QUERY_INTERVAL);
                                traderGateway.nativeReqQryInvestorPosition(query.getExchangeCode(), query.getInstrumentCode());
                                log.info("{} -> Has been sent ReqQryInvestorPosition", adaptorId);
                            }
                        });
                return true;
            } catch (Exception e) {
                log.error("{} -> Native ReqQryInvestorPosition has exception -> {}", traderGatewayId, e.getMessage(), e);
                return false;
            }
        } else {
            log.error("Adaptor::directQueryPositions unable to call, adaptorId==[{}], isTraderAvailable==[{}]",
                    adaptorId, isTraderAvailable);
            return false;
        }
    }

    @Override
    protected boolean directQueryBalance(@Nonnull QueryBalance query) {
        log.info("Adaptor::directQueryBalance calling, adaptorId==[{}], QueryBalance -> {}", adaptorId, query);
        if (isTraderAvailable) {
            try {
                startNewFiber("QueryBalance-Worker",
                        () -> {
                            synchronized (mutex) {
                                log.info("{} -> Ready to sent ReqQryTradingAccount, Waiting...", adaptorId);
                                Sleep.millis(QUERY_INTERVAL);
                                traderGateway.nativeReqQryTradingAccount();
                                log.info("{} -> Has been sent ReqQryTradingAccount", adaptorId);
                            }
                        });
                return true;
            } catch (Exception e) {
                log.error("{} -> Native ReqQryTradingAccount has exception -> {}", traderGatewayId, e.getMessage(), e);
                return false;
            }
        } else {
            log.error("Adaptor::directQueryBalance unable to call, adaptorId==[{}], isTraderAvailable==[{}]",
                    adaptorId, isTraderAvailable);
            return false;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            publisher.publishFrontDisconnected(EventSource.MD, 1);
            publisher.publishFrontDisconnected(EventSource.TD, 1);
            isMdAvailable = false;
            isTraderAvailable = false;
            mdGateway.close();
            traderGateway.close();
        } catch (IOException e) {
            log.error("{} -> Native close has exception -> {}", traderGatewayId, e.getMessage(), e);
            throw e;
        }
    }

    public static Builder builder(Account account, FtdcParams params) {
        return new Builder(account, params);
    }

    public static class Builder {

        private final Account account;
        private final FtdcParams params;
        private boolean isAsync = false;
        private AdaptorRunningMode runningMode = AdaptorRunningMode.FULL;
        private OrderRefKeeper orderRefKeeper = OrderRefKeeper.DEFAULT;

        private Builder(Account account, FtdcParams params) {
            this.account = account;
            this.params = params;
        }

        public Builder setRunningMode(AdaptorRunningMode runningMode) {
            this.runningMode = runningMode;
            return this;
        }

        public Builder setOrderRefKeeper(OrderRefKeeper orderRefKeeper) {
            this.orderRefKeeper = orderRefKeeper;
            return this;
        }

        public Builder asyncMode() {
            this.isAsync = true;
            return this;
        }

        public CtpAdaptor build(StreamEventHandler inboundHandler) {
            return build(inboundHandler);
        }

        public CtpAdaptor build(StreamEventHandler inboundHandler, FtdcRspHandler extFtdcRspHandler) {
            return new CtpAdaptor(this, inboundHandler, extFtdcRspHandler);
        }


    }

}
