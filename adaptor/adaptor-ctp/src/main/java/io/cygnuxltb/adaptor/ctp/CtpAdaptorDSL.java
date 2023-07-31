package io.cygnuxltb.adaptor.ctp;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.cygnuxltb.adaptor.ctp.OrderRefKeeper.OrderRefNotFoundException;
import io.cygnuxltb.adaptor.ctp.converter.FtdcOrderConverter;
import io.cygnuxltb.adaptor.ctp.converter.MarketDataConverter;
import io.cygnuxltb.adaptor.ctp.converter.OrderReportConverter;
import io.cygnuxltb.adaptor.ctp.gateway.CtpGateway;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcRspMsg;
import io.cygnuxltb.jcts.core.account.Account;
import io.cygnuxltb.jcts.core.adaptor.AbstractAdaptor;
import io.cygnuxltb.jcts.core.adaptor.AdaptorType;
import io.cygnuxltb.jcts.core.adaptor.ConnectionType;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.ser.req.CancelOrder;
import io.cygnuxltb.jcts.core.ser.req.NewOrder;
import io.cygnuxltb.jcts.core.ser.req.QueryBalance;
import io.cygnuxltb.jcts.core.ser.req.QueryOrder;
import io.cygnuxltb.jcts.core.ser.req.QueryPositions;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.concurrent.queue.ScQueueWithJCT;
import io.mercury.common.functional.Processor;
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

import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

/**
 * CTP Adaptor, 用于连接上期CTP柜台
 *
 * @author yellow013
 */
public class CtpAdaptorDSL extends AbstractAdaptor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpAdaptorDSL.class);

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


    // GatewayId
    private String gatewayId;

    // CtpGateway
    private CtpGateway gateway;

    // FTDC报单请求转换器
    private FtdcOrderConverter orderConverter;

    // 队列缓冲区
    private final Queue<FtdcRspMsg> queue;


    /**
     * 传入InboundScheduler实现, 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param account    Account
     * @param config     CtpConfig
     * @param mode       AdaptorRunMode
     * @param processors Processor<FtdcRspMsg>
     */
    public CtpAdaptorDSL(@Nonnull Account account, @Nonnull CtpConfiguration config,
                         @Nonnull ConnectionType mode,
                         @Nonnull Processor<FtdcRspMsg> processors) {
        super("CtpAdaptor", account);
        // 创建队列缓冲区
        this.queue = ScQueueWithJCT.mpscQueue(adaptorId + "-BUF")
                .capacity(32).process(processors);
        this.config = config;
        this.type = mode;
        initializer();
    }


    @PostConstruct
    private void initializer() {
        // 创建FtdcOrderConverter
        this.orderConverter = new FtdcOrderConverter(config);
        // 创建GatewayId
        this.gatewayId = config.getBrokerId() + "-" + config.getInvestorId();
        // 创建Gateway
        log.info("Try create gateway, gatewayId -> {}", gatewayId);
        this.gateway = new CtpGateway(gatewayId, config, type,
                // 消息放入缓冲区
                queue::enqueue);
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
    public boolean queryOrder(@Nonnull QueryOrder request) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryOrder-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                        sleep(queryInterval);
                        gateway.ReqQryOrder(request.getExchangeCode(), request.getInstrumentCode());
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
    public boolean queryPositions(@Nonnull QueryPositions request) {
        try {
            if (isTraderAvailable) {
                startNewThread("QueryPositions-Worker", () -> {
                    synchronized (mutex) {
                        log.info("{} -> Ready to sent ReqQryInvestorPosition, Waiting...", adaptorId);
                        sleep(queryInterval);
                        gateway.ReqQryInvestorPosition(request.getExchangeCode(), request.getInstrumentCode());
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
    public boolean queryBalance(@Nonnull QueryBalance request) {
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
