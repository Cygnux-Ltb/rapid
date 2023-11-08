package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcQryInstrumentField;
import ctp.thostapi.CThostFtdcQryInvestorPositionField;
import ctp.thostapi.CThostFtdcQryOrderField;
import ctp.thostapi.CThostFtdcQrySettlementInfoField;
import ctp.thostapi.CThostFtdcQryTradingAccountField;
import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcTraderApi;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import io.cygnuxltb.adaptor.ctp.CtpConfig;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEventPublisher;
import io.cygnuxltb.adaptor.ctp.gateway.utils.NativeLibraryManager;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.file.FileUtil;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import org.slf4j.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static ctp.thostapi.THOST_TE_RESUME_TYPE.THOST_TERT_RESUME;
import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

public final class FtdcTraderGateway implements FtdcTraderCallback, Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcTraderGateway.class);

    // 静态加载FtdcLibrary
    static {
        try {
            NativeLibraryManager.tryLoad();
        } catch (NativeLibraryException e) {
            log.error(e.getMessage(), e);
            log.error("CTP native library file loading error, System must exit. status -1");
            System.exit(-1);
        }
    }

    @Native
    private CThostFtdcTraderApi traderApi;

    private final String callbackName = FtdcMdGateway.class.getSimpleName();

    // 是否已初始化
    private final AtomicBoolean isInitialize = new AtomicBoolean(false);

    // 是否已登陆交易接口
    private volatile boolean isTraderLogin = false;

    // 是否已认证
    private volatile boolean isAuthenticate;

    // 交易前置号
    private volatile int frontID;
    // 交易会话号
    private volatile int sessionID;

    // 交易请求ID
    private final AtomicInteger requestId = new AtomicInteger(-1);
    private final String gatewayId;

    private final CtpConfig config;

    private final FtdcEventPublisher publisher;


    public FtdcTraderGateway(String gatewayId, CtpConfig config, FtdcEventPublisher publisher) {
        this.gatewayId = gatewayId;
        this.config = config;
        this.publisher = publisher;
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcTraderApi.version() -> {}", CThostFtdcTraderApi.GetApiVersion());
            try {
                startNewMaxPriorityThread("FtdcTrader-Thread", this::initAndJoin);
            } catch (Exception e) {
                log.error("Method initAndJoin throw Exception -> {}", e.getMessage(), e);
                isInitialize.set(false);
                throw new RuntimeException(e);
            }
        }
    }

    private void initAndJoin() {
        // 创建CTP数据文件临时目录
        File tempDir = FileUtil.mkdirInTmp(gatewayId + "-" + DateTimeUtil.date());
        log.info("Gateway -> [{}] trader temp dir : {}", gatewayId, tempDir.getAbsolutePath());
        // 指定trader临时文件地址
        String tempFile = new File(tempDir, "trader").getAbsolutePath();
        log.info("Gateway -> [{}] trader temp file : {}", gatewayId, tempFile);
        // 创建traderApi
        this.traderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(tempFile);
        // 创建traderSpi
        CThostFtdcTraderSpi traderSpi = new FtdcTraderSpi(this);
        // 将traderSpi注册到traderApi
        traderApi.RegisterSpi(traderSpi);
        // 注册到trader前置机
        traderApi.RegisterFront(config.getTraderAddr());
        /// THOST_TERT_RESTART:从本交易日开始重传
        /// THOST_TERT_RESUME:从上次收到的续传
        /// THOST_TERT_QUICK:只传送登录后私有流的内容
        // 订阅公有流和私有流
        traderApi.SubscribePublicTopic(THOST_TERT_RESUME);
        traderApi.SubscribePrivateTopic(THOST_TERT_RESUME);
        // 初始化traderApi
        log.info("Call native function TraderApi::Init()");
        traderApi.Init();
        // 阻塞当前线程
        log.info("Call native function TraderApi::Join()");
        traderApi.Join();
    }

    /**
     * 报单请求
     *
     * @param field CThostFtdcInputOrderField
     */
    public int ReqOrderInsert(CThostFtdcInputOrderField field) {
        if (isTraderLogin) {
            // 设置账号信息
            int RequestID = requestId.incrementAndGet();
            traderApi.ReqOrderInsert(field, RequestID);
            log.info("Send TraderApi::ReqOrderInsert OK ->  RequestID==[{}], OrderRef==[{}], InstrumentID==[{}], "
                            + "CombOffsetFlag==[{}], Direction==[{}], VolumeTotalOriginal==[{}], LimitPrice==[{}]",
                    RequestID, field.getOrderRef(), field.getInstrumentID(), field.getCombOffsetFlag(),
                    field.getDirection(), field.getVolumeTotalOriginal(), field.getLimitPrice());
            return RequestID;
        } else {
            log.error("TraderApi::ReqOrderInsert call error :: TraderApi is not login");
            return -1;
        }
    }

    /**
     * 撤单请求
     *
     * @param field CThostFtdcInputOrderActionField
     */
    public int ReqOrderAction(CThostFtdcInputOrderActionField field) {
        if (isTraderLogin) {
            int RequestID = requestId.incrementAndGet();
            traderApi.ReqOrderAction(field, RequestID);
            log.info(
                    "Send TraderApi::ReqOrderAction OK -> RequestID==[{}], OrderRef==[{}], OrderActionRef==[{}], "
                            + "BrokerID==[{}], InvestorID==[{}], InstrumentID==[{}]",
                    RequestID, field.getOrderRef(), field.getOrderActionRef(), field.getBrokerID(),
                    field.getInvestorID(), field.getInstrumentID());
            return RequestID;
        } else {
            log.error("TraderApi::ReqOrderAction call error :: TraderApi is not login");
            return -1;
        }

    }

    /**
     * 查询订单
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int ReqQryOrder(String exchangeCode, String instrumentCode) {
        CThostFtdcQryOrderField field = new CThostFtdcQryOrderField();
        field.setBrokerID(config.getBrokerId());
        field.setInvestorID(config.getInvestorId());
        field.setExchangeID(exchangeCode);
        field.setInstrumentID(instrumentCode);
        int RequestID = requestId.incrementAndGet();
        traderApi.ReqQryOrder(field, RequestID);
        log.info(
                "Send TraderApi::ReqQryOrder OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, field.getBrokerID(), field.getInvestorID(), field.getExchangeID(), field.getInstrumentID());
        return RequestID;
    }

    /**
     * 查询账户
     *
     * <pre>
     * ///查询资金账户
     * struct CThostFtdcQryTradingAccountField
     * {
     * ///经纪公司代码
     * TThostFtdcBrokerIDType	BrokerID;
     * ///投资者代码
     * TThostFtdcInvestorIDType	InvestorID;
     * ///币种代码
     * TThostFtdcCurrencyIDType	CurrencyID;
     * ///业务类型
     * TThostFtdcBizTypeType	BizType;
     * ///投资者帐号
     * TThostFtdcAccountIDType	AccountID;
     * };
     * </pre>
     */
    public int ReqQryTradingAccount() {
        CThostFtdcQryTradingAccountField field = new CThostFtdcQryTradingAccountField();
        field.setBrokerID(config.getBrokerId());
        field.setAccountID(config.getAccountId());
        field.setInvestorID(config.getInvestorId());
        field.setCurrencyID(config.getCurrencyId());
        int RequestID = requestId.incrementAndGet();
        traderApi.ReqQryTradingAccount(field, RequestID);
        log.info(
                "Send TraderApi::ReqQryTradingAccount OK -> RequestID==[{}], BrokerID==[{}], AccountID==[{}], InvestorID==[{}], CurrencyID==[{}]",
                RequestID, field.getBrokerID(), field.getAccountID(), field.getInvestorID(), field.getCurrencyID());
        return RequestID;
    }

    /**
     * 查询持仓
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int ReqQryInvestorPosition(String exchangeCode, String instrumentCode) {
        CThostFtdcQryInvestorPositionField field = new CThostFtdcQryInvestorPositionField();
        field.setBrokerID(config.getBrokerId());
        field.setInvestorID(config.getInvestorId());
        field.setExchangeID(exchangeCode);
        field.setInstrumentID(instrumentCode);
        int RequestID = requestId.incrementAndGet();
        traderApi.ReqQryInvestorPosition(field, RequestID);
        log.info(
                "Send TraderApi::ReqQryInvestorPosition OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, field.getBrokerID(), field.getInvestorID(), field.getExchangeID(), field.getInstrumentID());
        return RequestID;
    }

    /**
     * 查询结算信息
     *
     * <pre>
     * ///查询投资者结算结果
     * struct CThostFtdcQrySettlementInfoField
     * {
     * ///经纪公司代码
     * TThostFtdcBrokerIDType	BrokerID;
     * ///投资者代码
     * TThostFtdcInvestorIDType	InvestorID;
     * ///交易日
     * TThostFtdcDateType	TradingDay;
     * ///投资者帐号
     * TThostFtdcAccountIDType	AccountID;
     * ///币种代码
     * TThostFtdcCurrencyIDType	CurrencyID;
     * };
     * </pre>
     */
    public int ReqQrySettlementInfo() {
        CThostFtdcQrySettlementInfoField field = new CThostFtdcQrySettlementInfoField();
        field.setBrokerID(config.getBrokerId());
        field.setInvestorID(config.getInvestorId());
        field.setTradingDay(config.getTradingDay());
        field.setAccountID(config.getAccountId());
        field.setCurrencyID(config.getCurrencyId());
        int RequestID = requestId.incrementAndGet();
        traderApi.ReqQrySettlementInfo(field, RequestID);
        log.info("Send TraderApi::ReqQrySettlementInfo OK -> RequestID==[{}]", RequestID);
        return RequestID;
    }

    /**
     * 查询交易标的
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int ReqQryInstrument(String exchangeCode, String instrumentCode) {
        CThostFtdcQryInstrumentField field = new CThostFtdcQryInstrumentField();
        field.setExchangeID(exchangeCode);
        field.setInstrumentID(instrumentCode);
        int RequestID = requestId.incrementAndGet();
        traderApi.ReqQryInstrument(field, RequestID);
        log.info("Send TraderApi::ReqQryInstrument OK -> RequestID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, field.getExchangeID(), field.getInstrumentID());
        return RequestID;
    }


    @Override
    public void close() throws IOException {
        startNewThread("TraderApi-Release", () -> {
            log.info("CThostFtdcTraderApi start release");
            if (traderApi != null) traderApi.Release();
            log.info("CThostFtdcTraderApi is released");
        });
        sleep(1000);
    }

    //*************************************************************************************//
    //************************************ CALLBACK ***************************************//
    //*************************************************************************************//

    /**
     * 交易前置断开回调
     */
    public void onFrontDisconnected() {
        log.warn("FtdcCallback::onTraderFrontDisconnected");
        isTraderLogin = false;
        isAuthenticate = false;
        // 交易前置断开处理
        // publisher.publish(false, frontID, sessionID);
    }

    /**
     * 交易前置机连接回调
     */
    public void onFrontConnected() {
        log.info("FtdcCallback::onTraderFrontConnected");
        if (StringSupport.nonEmpty(config.getAuthCode()) && !isAuthenticate) {
            // 发送认证请求
            CThostFtdcReqAuthenticateField field = new CThostFtdcReqAuthenticateField();
            field.setAppID(config.getAppId());
            field.setUserID(config.getUserId());
            field.setBrokerID(config.getBrokerId());
            field.setAuthCode(config.getAuthCode());
            int RequestID = requestId.incrementAndGet();
            traderApi.ReqAuthenticate(field, RequestID);
            log.info(
                    "Send TraderApi::ReqAuthenticate OK -> RequestID==[{}], BrokerID==[{}], UserID==[{}], AppID==[{}], AuthCode==[{}]",
                    RequestID, field.getBrokerID(), field.getUserID(), field.getAppID(), field.getAuthCode());
        } else {
            log.error("Cannot sent TraderApi::ReqAuthenticate, authCode==[{}], isAuthenticate==[{}]",
                    config.getAuthCode(), isAuthenticate);
        }
    }




    /**
     * 撤单错误回调: 1
     *
     * @param field CThostFtdcInputOrderActionField
     */
    public void onRspOrderAction(CThostFtdcInputOrderActionField field) {
        log.info("FtdcCallback::onRspOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                        "OrderActionRef==[{}], InstrumentID==[{}]",
                field.getOrderRef(), field.getOrderSysID(),
                field.getOrderActionRef(), field.getInstrumentID());
        publisher.publish(field);
    }


    /**
     * 撤单错误回调: 2
     *
     * @param field CThostFtdcOrderActionField
     */
    public void onErrRtnOrderAction(CThostFtdcOrderActionField field) {
        log.info("FtdcCallback::onErrRtnOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                        "OrderActionRef==[{}], InstrumentID==[{}]",
                field.getOrderRef(), field.getOrderSysID(),
                field.getOrderActionRef(), field.getInstrumentID());
        publisher.publish(field);
    }

    /**
     * 账户查询回调
     *
     * @param field  CThostFtdcTradingAccountField
     * @param isLast boolean
     */
    public void onQryTradingAccount(CThostFtdcTradingAccountField field, boolean isLast) {
        log.info("FtdcCallback::onQryTradingAccount -> AccountID==[{}], Balance==[{}], " +
                        "Available==[{}], Credit==[{}], WithdrawQuota==[{}], isLast==[{}]",
                field.getAccountID(), field.getBalance(),
                field.getAvailable(), field.getCredit(), field.getWithdrawQuota(), isLast);
        publisher.publish(field);
    }


    /**
     * 账户持仓查询回调
     *
     * @param field  CThostFtdcInvestorPositionField
     * @param isLast boolean
     */
    public void onRspQryInvestorPosition(CThostFtdcInvestorPositionField field, boolean isLast) {
        log.info("FtdcCallback::onRspQryInvestorPosition -> InvestorID==[{}], ExchangeID==[{}], " +
                        "InstrumentID==[{}], Position==[{}], isLast==[{}]",
                field.getInvestorID(), field.getExchangeID(),
                field.getInstrumentID(), field.getPosition(), isLast);
        publisher.publish(field, isLast);
    }













}
