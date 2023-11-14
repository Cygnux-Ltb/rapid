package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcBatchOrderActionField;
import ctp.thostapi.CThostFtdcInputBatchOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentField;
import ctp.thostapi.CThostFtdcInstrumentStatusField;
import ctp.thostapi.CThostFtdcInvestorField;
import ctp.thostapi.CThostFtdcInvestorPositionDetailField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcQryInstrumentField;
import ctp.thostapi.CThostFtdcQryInvestorPositionField;
import ctp.thostapi.CThostFtdcQryOrderField;
import ctp.thostapi.CThostFtdcQrySettlementInfoField;
import ctp.thostapi.CThostFtdcQryTradingAccountField;
import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspAuthenticateField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSettlementInfoConfirmField;
import ctp.thostapi.CThostFtdcSettlementInfoField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderApi;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.cygnuxltb.adaptor.ctp.CtpConfig;
import io.cygnuxltb.adaptor.ctp.gateway.event.FtdcEventPublisher;
import io.cygnuxltb.adaptor.ctp.gateway.event.listener.BaseFtdcTraderListener;
import io.cygnuxltb.adaptor.ctp.gateway.spi.FtdcTraderSpi;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.file.FileUtil;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import lombok.Getter;
import org.slf4j.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static ctp.thostapi.THOST_TE_RESUME_TYPE.THOST_TERT_RESUME;
import static io.cygnuxltb.adaptor.ctp.gateway.util.FtdcRspInfoHandler.nonError;
import static io.cygnuxltb.adaptor.ctp.gateway.util.NativeLibraryManager.tryLoad;
import static io.mercury.common.lang.Asserter.nonNull;
import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

public final class FtdcTraderGateway extends BaseFtdcTraderListener implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcTraderGateway.class);

    // 静态加载FtdcLibrary
    static {
        try {
            tryLoad();
        } catch (NativeLibraryException e) {
            log.error(e.getMessage(), e);
            log.error("CTP native library file loading error, System must exit. status -1");
            System.exit(-1);
        }
    }

    @Native
    private CThostFtdcTraderApi NativeApi;

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
    private final AtomicInteger requestIdGetter = new AtomicInteger(-1);

    @Getter
    private final String gatewayId;

    private final CtpConfig config;

    private final FtdcEventPublisher publisher;

    public FtdcTraderGateway(CtpConfig config, FtdcEventPublisher publisher) {
        this.config = nonNull(config, "config");
        this.publisher = nonNull(publisher, "publisher");
        this.gatewayId = "GATEWAY-TD-" + config.getBrokerId() + "-" + config.getInvestorId();
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcTraderApi.GetApiVersion() -> {}", CThostFtdcTraderApi.GetApiVersion());
            try {
                startNewMaxPriorityThread(gatewayId + "-Thread", this::initAndJoin);
            } catch (Exception e) {
                log.error("FtdcTraderGateway initAndJoin throw Exception -> {}", e.getMessage(), e);
                isInitialize.set(false);
                throw new RuntimeException(e);
            }
        }
    }

    private void initAndJoin() {
        // 创建CTP数据文件临时目录
        var tempDir = FileUtil.mkdirInTmp(gatewayId + "-" + DateTimeUtil.date());
        log.info("{} -> use trader tempDir: {}", gatewayId, tempDir.getAbsolutePath());
        // 指定trader临时文件地址
        var tempFile = new File(tempDir, "trader").getAbsolutePath();
        log.info("{} -> use trader tempFile : {}", gatewayId, tempFile);
        // 创建traderApi
        this.NativeApi = CThostFtdcTraderApi.CreateFtdcTraderApi(tempFile);
        log.info("{} -> call native CThostFtdcTraderApi::CreateFtdcTraderApi", gatewayId);
        // 创建traderSpi
        CThostFtdcTraderSpi Spi = new FtdcTraderSpi(this);
        log.info("{} -> created CThostFtdcTraderSpi with FtdcTraderSpi", gatewayId);
        // 将Spi注册到CThostFtdcTraderApi
        NativeApi.RegisterSpi(Spi);
        log.info("{} -> call native CThostFtdcTraderApi::RegisterSpi", gatewayId);
        // 注册到trader前置机
        NativeApi.RegisterFront(config.getTraderAddr());
        log.info("{} -> call native CThostFtdcTraderApi::RegisterFront", gatewayId);
        /// THOST_TERT_RESTART:从本交易日开始重传
        /// THOST_TERT_RESUME:从上次收到的续传
        /// THOST_TERT_QUICK:只传送登录后私有流的内容
        // 订阅公有流和私有流, 设置为[THOST_TERT_RESUME]
        NativeApi.SubscribePublicTopic(THOST_TERT_RESUME);
        log.info("{} -> call native CThostFtdcTraderApi::SubscribePublicTopic(THOST_TERT_RESUME)", gatewayId);
        NativeApi.SubscribePrivateTopic(THOST_TERT_RESUME);
        log.info("{} -> call native CThostFtdcTraderApi::SubscribePrivateTopic(THOST_TERT_RESUME)", gatewayId);
        // 初始化traderApi
        NativeApi.Init();
        log.info("{} -> call native CThostFtdcTraderApi::Init", gatewayId);
        // 阻塞当前线程
        log.info("{} -> call native CThostFtdcTraderApi::Join", gatewayId);
        NativeApi.Join();
    }

    /**
     * 报单请求
     *
     * @param field CThostFtdcInputOrderField
     */
    public int ReqOrderInsert(CThostFtdcInputOrderField field) {
        if (isTraderLogin) {
            // 设置账号信息
            int RequestID = requestIdGetter.incrementAndGet();
            NativeApi.ReqOrderInsert(field, RequestID);
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
            int RequestID = requestIdGetter.incrementAndGet();
            NativeApi.ReqOrderAction(field, RequestID);
            log.info("Send TraderApi::ReqOrderAction OK -> RequestID==[{}], OrderRef==[{}], OrderActionRef==[{}], "
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
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryOrder(field, RequestID);
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
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryTradingAccount(field, RequestID);
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
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryInvestorPosition(field, RequestID);
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
     * TThostFtdcDateType	    TradingDay;
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
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQrySettlementInfo(field, RequestID);
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
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryInstrument(field, RequestID);
        log.info("Send TraderApi::ReqQryInstrument OK -> RequestID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, field.getExchangeID(), field.getInstrumentID());
        return RequestID;
    }


    @Override
    public void close() throws IOException {
        startNewThread("TraderApi-Release", () -> {
            log.info("CThostFtdcTraderApi start release");
            if (NativeApi != null) NativeApi.Release();
            log.info("CThostFtdcTraderApi is released");
        });
        sleep(1000);
    }

//*******************************************************************************************************//
//*******************************************************************************************************//
//******************************************** EVENT TRIGGER ********************************************//
//*******************************************************************************************************//
//*******************************************************************************************************//

    /**
     * 交易前置机连接回调
     */
    @Override
    public void fireFrontConnected() {
        log.info("TraderSpi::fireFrontConnected");
        if (StringSupport.nonEmpty(config.getAuthCode()) && !isAuthenticate) {
            // 发送认证请求
            CThostFtdcReqAuthenticateField field = new CThostFtdcReqAuthenticateField();
            field.setAppID(config.getAppId());
            field.setUserID(config.getUserId());
            field.setBrokerID(config.getBrokerId());
            field.setAuthCode(config.getAuthCode());
            int RequestID = requestIdGetter.incrementAndGet();
            NativeApi.ReqAuthenticate(field, RequestID);
            log.info(
                    "Send TraderApi::ReqAuthenticate OK -> RequestID==[{}], BrokerID==[{}], UserID==[{}], AppID==[{}], AuthCode==[{}]",
                    RequestID, field.getBrokerID(), field.getUserID(), field.getAppID(), field.getAuthCode());
        } else {
            log.error("Cannot sent TraderApi::ReqAuthenticate, authCode==[{}], isAuthenticate==[{}]",
                    config.getAuthCode(), isAuthenticate);
        }
    }

    /**
     * ///当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后. API会自动重新连接, 客户端可不做处理.
     *
     * @param Reason 错误原因
     *               <br> 0x1001 网络读失败
     *               <br> 0x1002 网络写失败
     *               <br> 0x2001 接收心跳超时
     *               <br> 0x2002 发送心跳失败
     *               <br> 0x2003 收到错误报文
     */
    @Override
    public void fireFrontDisconnected(int Reason) {
        log.warn("TraderSpi::fireFrontDisconnected");
        isTraderLogin = false;
        isAuthenticate = false;
        // 交易前置断开处理
        // publisher.publish(false, frontID, sessionID);

    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.info("TraderSpi::fireHeartBeatWarning");
    }

    /**
     * ///客户端认证响应
     *
     * @param Field     CThostFtdcRspAuthenticateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspAuthenticate(CThostFtdcRspAuthenticateField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspAuthenticate, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspAuthenticate", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspAuthenticate -> BrokerID==[{}], UserID==[{}]", Field.getBrokerID(),
                        Field.getUserID());
                isAuthenticate = true;
                CThostFtdcReqUserLoginField loginField = new CThostFtdcReqUserLoginField();
                loginField.setBrokerID(config.getBrokerId());
                loginField.setUserID(config.getUserId());
                loginField.setPassword(config.getPassword());
                loginField.setClientIPAddress(config.getIpAddr());
                loginField.setMacAddress(config.getMacAddr());
                int newRequestID = requestIdGetter.incrementAndGet();
                NativeApi.ReqUserLogin(loginField, newRequestID);
                log.info("Send TraderApi::ReqUserLogin OK -> RequestID==[{}]", newRequestID);
            } else {
                log.error("FtdcTraderSpi::OnRspAuthenticate return null");
            }
        } else {
            log.error("");
        }
    }

    /**
     * ///登录请求响应
     *
     * @param Field     CThostFtdcRspUserLoginField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspUserLogin(CThostFtdcRspUserLoginField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspUserLogin, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspUserLogin", RspInfo)) {
            if (Field != null) {
                log.info(
                        "FtdcCallback::onTraderRspUserLogin -> BrokerID==[{}], UserID==[{}], LoginTime==[{}], MaxOrderRef==[{}]",
                        Field.getBrokerID(), Field.getUserID(), Field.getLoginTime(), Field.getMaxOrderRef());
                frontID = Field.getFrontID();
                sessionID = Field.getSessionID();
                isTraderLogin = true;
                publisher.publishMdAvailable(Field, RspInfo, RequestID, IsLast);
            } else {
                log.error("FtdcTraderSpi::OnRspUserLogin return null");
            }
        } else {
            log.error("");
        }
    }

    /**
     * ///登出请求响应
     *
     * @param Field     CThostFtdcUserLogoutField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspUserLogout(CThostFtdcUserLogoutField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspUserLogout, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspUserLogout", RspInfo)) {
            if (Field != null) {
                log.info("Output :: OnRspUserLogout -> BrokerID==[{}], UserID==[{}]", Field.getBrokerID(),
                        Field.getUserID());
                // TODO 处理用户登出
                publisher.publishTrader();
            } else {
                log.error("FtdcTraderSpi::OnRspUserLogout return null");
            }
        } else {
            log.error("");
        }
    }


    /**
     * ///报单录入请求响应
     *
     * @param Field     CThostFtdcInputOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOrderInsert(CThostFtdcInputOrderField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspOrderInsert, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspOrderInsert", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspOrderInsert -> OrderRef==[{}]", Field.getOrderRef());
                publisher.publish(Field);
            } else {
                log.error("FtdcTraderSpi::OnRspOrderInsert return null");
            }
        } else {
            log.error("");
        }
    }


    /**
     * ///报单操作请求响应-[撤单错误回调: 1]
     *
     * @param Field     CThostFtdcInputOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOrderAction(CThostFtdcInputOrderActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {

        log.info("TraderSpi::OnRspOrderAction, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);

        if (nonError("TraderSpi::OnRspOrderAction", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publish(Field);
            } else {
                log.error("TraderSpi::OnRspOrderAction return null");
            }
        } else {
            log.error("");
        }
    }


    /**
     * ///批量报单操作请求响应
     *
     * @param Field     CThostFtdcInputBatchOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspBatchOrderAction(CThostFtdcInputBatchOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("TraderSpi::fireRspBatchOrderAction");
    }


    /**
     * ///请求查询报单响应
     *
     * @param Field     CThostFtdcOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryOrder(CThostFtdcOrderField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQryOrder, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryOrder", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspQryOrder -> AccountID==[{}], OrderRef==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getOrderRef(), IsLast);
                publisher.publish(Field, IsLast);
            } else {
                log.error("FtdcTraderSpi::OnRspQryOrder return null");
            }
        } else {

        }
    }

    /**
     * ///请求查询成交响应
     *
     * @param Field     CThostFtdcTradeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTrade(CThostFtdcTradeField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("TraderSpi::fireRspQryTrade");
    }

    /**
     * ///请求查询投资者持仓响应
     *
     * @param Field     CThostFtdcInvestorPositionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestorPosition(CThostFtdcInvestorPositionField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQryInvestorPosition, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryInvestorPosition", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspQryInvestorPosition -> InvestorID==[{}], ExchangeID==[{}], " +
                                "InstrumentID==[{}], Position==[{}], isLast==[{}]",
                        Field.getInvestorID(), Field.getExchangeID(),
                        Field.getInstrumentID(), Field.getPosition(), IsLast);
                publisher.publish(Field, IsLast);
            } else {
                log.error("FtdcTraderSpi::OnRspQryInvestorPosition return null");
            }
        }
    }

    /**
     * ///请求查询投资者持仓明细响应
     *
     * @param Field     CThostFtdcInvestorPositionDetailField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("TraderSpi::fireRspQryInvestorPositionDetail");
    }

    /**
     * ///请求查询资金账户响应
     *
     * @param Field     CThostFtdcTradingAccountField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTradingAccount(CThostFtdcTradingAccountField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {

        log.info("FtdcTraderSpi::OnRspQryTradingAccount, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryTradingAccount", RspInfo))
            if (Field != null) {
                log.info("FtdcCallback::onQryTradingAccount -> AccountID==[{}], Balance==[{}], " +
                                "Available==[{}], Credit==[{}], WithdrawQuota==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getBalance(),
                        Field.getAvailable(), Field.getCredit(), Field.getWithdrawQuota(), IsLast);
                publisher.publish(Field);
            } else
                log.error("FtdcTraderSpi::OnRspQryTradingAccount return null");
    }

    /**
     * ///请求查询投资者响应
     *
     * @param Field     CThostFtdcInvestorField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestor(CThostFtdcInvestorField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("TraderSpi::fireRspQryInvestor");
    }


    /**
     * ///请求查询合约响应
     *
     * @param Field     CThostFtdcInstrumentField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInstrument(CThostFtdcInstrumentField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQryInstrument, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryInstrument", RspInfo))
            if (Field != null)
                log.info("Output :: OnRspQryInstrument, ExchangeID==[{}], InstrumentID==[{}]",
                        Field.getExchangeID(), Field.getInstrumentID());
            else
                log.error("FtdcTraderSpi::OnRspQryInstrument return null");
    }


    /**
     * ///请求查询投资者结算结果响应
     *
     * @param Field     CThostFtdcSettlementInfoField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySettlementInfo(CThostFtdcSettlementInfoField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {

        log.info("FtdcTraderSpi::OnRspQrySettlementInfo, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQrySettlementInfo", RspInfo))
            if (Field != null)
                log.info(
                        """
                                OnRspQrySettlementInfo -> BrokerID==[{}], AccountID==[{}], InvestorID==[{}],
                                SettlementID==[{}], TradingDay==[{}], CurrencyID==[{}]
                                <<<<<<<<<<<<<<<< CONTENT TEXT >>>>>>>>>>>>>>>>
                                {}
                                """,
                        Field.getBrokerID(), Field.getAccountID(), Field.getInvestorID(),
                        Field.getSettlementID(), Field.getTradingDay(),
                        Field.getCurrencyID(), Field.getContent());
            else
                log.error("FtdcTraderSpi::OnRspQrySettlementInfo return null");
    }


    /**
     * ///请求查询结算信息确认响应
     *
     * @param Field     CThostFtdcSettlementInfoConfirmField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("TraderSpi::fireRspQrySettlementInfoConfirm");
    }


    /**
     * ///错误应答
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("TraderSpi::fireRspError, ErrorID=[{}], ErrorMsg=[{}], RequestID==[{}], IsLast==[{}]",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
        publisher.publishRspError(RspInfo, RequestID, IsLast);
    }

    // 报单推送消息模板
    private static final String OnRtnOrderMsg = "FtdcTraderSpi::fireRtnOrder -> OrderRef==[{}], " +
            "AccountID==[{}], OrderSysID==[{}], InstrumentID==[{}], OrderStatus==[{}], " +
            "Direction==[{}], VolumeTotalOriginal==[{}], LimitPrice==[{}]";

    /**
     * ///报单通知
     *
     * @param Order CThostFtdcOrderField
     */
    @Override
    public void fireRtnOrder(CThostFtdcOrderField Order) {
        if (Order != null) {
            log.info(OnRtnOrderMsg, Order.getOrderRef(), Order.getAccountID(), Order.getOrderSysID(),
                    Order.getInstrumentID(), Order.getOrderStatus(), Order.getDirection(),
                    Order.getVolumeTotalOriginal(), Order.getLimitPrice());
            publisher.publish(Order, true);
        } else
            log.error("FtdcTraderSpi::fireRtnOrder return null");
    }

    // 成交推送消息模板
    private static final String OnRtnTradeMsg = "FtdcTraderSpi::fireRtnTrade -> OrderRef==[{}], " +
            "OrderSysID==[{}], InstrumentID==[{}], Direction==[{}], Price==[{}], Volume==[{}]";

    /**
     * ///成交通知
     *
     * @param Trade CThostFtdcTradeField
     */
    @Override
    public void fireRtnTrade(CThostFtdcTradeField Trade) {
        if (Trade != null) {
            log.info(OnRtnTradeMsg, Trade.getOrderRef(), Trade.getOrderSysID(), Trade.getInstrumentID(),
                    Trade.getDirection(), Trade.getPrice(), Trade.getVolume());
            publisher.publish(Trade);
        } else
            log.error("FtdcTraderSpi::fireRtnTrade return null");
    }

    /**
     * ///报单录入错误回报
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderInsert(CThostFtdcInputOrderField Field,
                                      CThostFtdcRspInfoField RspInfo) {
        log.info("TraderSpi::OnErrRtnOrderInsert");
        if (nonError("TraderSpi::OnErrRtnOrderInsert", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onErrRtnOrderInsert -> OrderRef==[{}], RequestID==[{}]", Field.getOrderRef(),
                        Field.getRequestID());
                publisher.publish(Field);
            } else {
                log.error("TraderSpi::OnErrRtnOrderInsert return null");
            }
        } else {
        }
    }

    /**
     * ///报单操作错误回报 - [撤单错误回调: 2]
     *
     * @param Field   CThostFtdcOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderAction(CThostFtdcOrderActionField Field,
                                      CThostFtdcRspInfoField RspInfo) {
        log.info("TraderSpi::OnErrRtnOrderAction");
        if (nonError("TraderSpi::OnErrRtnOrderAction", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onErrRtnOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publish(Field);
            } else {
                log.error("TraderSpi::OnErrRtnOrderAction return null");
            }
        } else {

        }


    }

    /**
     * ///合约交易状态通知
     *
     * @param Field CThostFtdcInstrumentStatusField
     */
    @Override
    public void fireRtnInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        publisher.publish(Field);
    }


    /**
     * ///批量报单操作错误回报
     *
     * @param Field   CThostFtdcBatchOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField Field,
                                           CThostFtdcRspInfoField RspInfo) {
        // TODO 批量撤单回调当前未实现
    }

    @Override
    public String toString() {
        return gatewayId;
    }
}
