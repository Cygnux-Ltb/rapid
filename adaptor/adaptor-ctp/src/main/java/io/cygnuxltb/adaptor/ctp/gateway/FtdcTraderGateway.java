package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcBatchOrderActionField;
import ctp.thostapi.CThostFtdcInputBatchOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentField;
import ctp.thostapi.CThostFtdcInstrumentStatusField;
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
     * @param Field CThostFtdcInputOrderField
     */
    public int ReqOrderInsert(CThostFtdcInputOrderField Field) {
        if (isTraderLogin) {
            // 设置账号信息
            int RequestID = requestIdGetter.incrementAndGet();
            NativeApi.ReqOrderInsert(Field, RequestID);
            log.info("Send TraderApi::ReqOrderInsert OK ->  RequestID==[{}], OrderRef==[{}], InstrumentID==[{}], "
                            + "CombOffsetFlag==[{}], Direction==[{}], VolumeTotalOriginal==[{}], LimitPrice==[{}]",
                    RequestID, Field.getOrderRef(), Field.getInstrumentID(), Field.getCombOffsetFlag(),
                    Field.getDirection(), Field.getVolumeTotalOriginal(), Field.getLimitPrice());
            return RequestID;
        } else {
            log.error("TraderApi::ReqOrderInsert call error :: TraderApi is not login");
            return -1;
        }
    }

    /**
     * 撤单请求
     *
     * @param Field CThostFtdcInputOrderActionField
     */
    public int ReqOrderAction(CThostFtdcInputOrderActionField Field) {
        if (isTraderLogin) {
            int RequestID = requestIdGetter.incrementAndGet();
            NativeApi.ReqOrderAction(Field, RequestID);
            log.info("Send TraderApi::ReqOrderAction OK -> RequestID==[{}], OrderRef==[{}], OrderActionRef==[{}], "
                            + "BrokerID==[{}], InvestorID==[{}], InstrumentID==[{}]",
                    RequestID, Field.getOrderRef(), Field.getOrderActionRef(), Field.getBrokerID(),
                    Field.getInvestorID(), Field.getInstrumentID());
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
        CThostFtdcQryOrderField ReqField = new CThostFtdcQryOrderField();
        ReqField.setBrokerID(config.getBrokerId());
        ReqField.setInvestorID(config.getInvestorId());
        ReqField.setExchangeID(exchangeCode);
        ReqField.setInstrumentID(instrumentCode);
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryOrder(ReqField, RequestID);
        log.info(
                "Send TraderApi::ReqQryOrder OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, ReqField.getBrokerID(), ReqField.getInvestorID(), ReqField.getExchangeID(), ReqField.getInstrumentID());
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
        CThostFtdcQryTradingAccountField ReqField = new CThostFtdcQryTradingAccountField();
        ReqField.setBrokerID(config.getBrokerId());
        ReqField.setAccountID(config.getAccountId());
        ReqField.setInvestorID(config.getInvestorId());
        ReqField.setCurrencyID(config.getCurrencyId());
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryTradingAccount(ReqField, RequestID);
        log.info(
                "Send TraderApi::ReqQryTradingAccount OK -> RequestID==[{}], BrokerID==[{}], AccountID==[{}], InvestorID==[{}], CurrencyID==[{}]",
                RequestID, ReqField.getBrokerID(), ReqField.getAccountID(), ReqField.getInvestorID(), ReqField.getCurrencyID());
        return RequestID;
    }

    /**
     * 查询持仓
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int ReqQryInvestorPosition(String exchangeCode, String instrumentCode) {
        CThostFtdcQryInvestorPositionField ReqField = new CThostFtdcQryInvestorPositionField();
        ReqField.setBrokerID(config.getBrokerId());
        ReqField.setInvestorID(config.getInvestorId());
        ReqField.setExchangeID(exchangeCode);
        ReqField.setInstrumentID(instrumentCode);
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryInvestorPosition(ReqField, RequestID);
        log.info(
                "Send TraderApi::ReqQryInvestorPosition OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, ReqField.getBrokerID(), ReqField.getInvestorID(), ReqField.getExchangeID(), ReqField.getInstrumentID());
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
        CThostFtdcQrySettlementInfoField ReqField = new CThostFtdcQrySettlementInfoField();
        ReqField.setBrokerID(config.getBrokerId());
        ReqField.setInvestorID(config.getInvestorId());
        ReqField.setTradingDay(config.getTradingDay());
        ReqField.setAccountID(config.getAccountId());
        ReqField.setCurrencyID(config.getCurrencyId());
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQrySettlementInfo(ReqField, RequestID);
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
        CThostFtdcQryInstrumentField ReqField = new CThostFtdcQryInstrumentField();
        ReqField.setExchangeID(exchangeCode);
        ReqField.setInstrumentID(instrumentCode);
        int RequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqQryInstrument(ReqField, RequestID);
        log.info("Send TraderApi::ReqQryInstrument OK -> RequestID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, ReqField.getExchangeID(), ReqField.getInstrumentID());
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
        log.info("TraderGateway::fireFrontConnected");
        if (StringSupport.nonEmpty(config.getAuthCode()) && !isAuthenticate) {
            // 发送认证请求
            CThostFtdcReqAuthenticateField ReqField = config.getReqAuthenticateField();
            int newRequestID = requestIdGetter.incrementAndGet();
            NativeApi.ReqAuthenticate(ReqField, newRequestID);
            log.info(
                    "Send TraderApi::ReqAuthenticate OK -> RequestID==[{}], BrokerID==[{}], UserID==[{}], AppID==[{}], AuthCode==[{}]",
                    newRequestID, ReqField.getBrokerID(), ReqField.getUserID(), ReqField.getAppID(), ReqField.getAuthCode());
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
        log.warn("TraderGateway::fireFrontDisconnected");
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
        log.info("TraderGateway::fireHeartBeatWarning");
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
        log.info("TraderGateway::fireRspAuthenticate, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::OnRspAuthenticate", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspAuthenticate -> BrokerID==[{}], UserID==[{}]", Field.getBrokerID(),
                        Field.getUserID());
                isAuthenticate = true;
                CThostFtdcReqUserLoginField ReqField = config.getReqUserLoginField();
                int newRequestID = requestIdGetter.incrementAndGet();
                NativeApi.ReqUserLogin(ReqField, newRequestID);
                log.info("Send TraderApi::ReqUserLogin OK -> RequestID==[{}]", newRequestID);
            } else {
                log.error("TraderGateway::OnRspAuthenticate return null");
            }
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
        log.info("TraderGateway::fireRspUserLogin, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::fireRspUserLogin", RspInfo)) {
            if (Field != null) {
                log.info(
                        "TraderGateway::fireRspUserLogin -> BrokerID==[{}], UserID==[{}], LoginTime==[{}], MaxOrderRef==[{}]",
                        Field.getBrokerID(), Field.getUserID(), Field.getLoginTime(), Field.getMaxOrderRef());
                frontID = Field.getFrontID();
                sessionID = Field.getSessionID();
                isTraderLogin = true;
                publisher.publishTraderAvailable(Field, RspInfo, RequestID, IsLast);
            } else {
                log.error("TraderGateway::fireRspUserLogin return null");
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
        log.warn("TraderGateway::fireRspUserLogout, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::fireRspUserLogout", RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspUserLogout -> BrokerID==[{}], UserID==[{}]", Field.getBrokerID(),
                        Field.getUserID());
                // TODO 处理用户登出
                publisher.publishTraderUserLogout(Field, RequestID, IsLast);
            } else {
                log.error("TraderGateway::fireRspUserLogout return null");
            }
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
        log.info("TraderGateway::OnRspOrderInsert, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::OnRspOrderInsert", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspOrderInsert -> OrderRef==[{}]", Field.getOrderRef());
                publisher.publish(Field);
            } else {
                log.error("TraderGateway::OnRspOrderInsert return null");
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

        log.info("TraderGateway::OnRspOrderAction, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);

        if (nonError("TraderGateway::OnRspOrderAction", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publish(Field);
            } else {
                log.error("TraderGateway::OnRspOrderAction return null");
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
        log.info("TraderGateway::fireRspBatchOrderAction");
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
        log.info("TraderGateway::OnRspQryOrder, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::OnRspQryOrder", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspQryOrder -> AccountID==[{}], OrderRef==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getOrderRef(), IsLast);
                publisher.publish(Field, IsLast);
            } else {
                log.error("TraderGateway::OnRspQryOrder return null");
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
        log.info("TraderGateway::fireRspQryTrade");
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
        log.info("TraderGateway::OnRspQryInvestorPosition, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::OnRspQryInvestorPosition", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onRspQryInvestorPosition -> InvestorID==[{}], ExchangeID==[{}], " +
                                "InstrumentID==[{}], Position==[{}], isLast==[{}]",
                        Field.getInvestorID(), Field.getExchangeID(),
                        Field.getInstrumentID(), Field.getPosition(), IsLast);
                publisher.publish(Field, IsLast);
            } else {
                log.error("TraderGateway::OnRspQryInvestorPosition return null");
            }
        }
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

        log.info("TraderGateway::OnRspQryTradingAccount, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::OnRspQryTradingAccount", RspInfo))
            if (Field != null) {
                log.info("FtdcCallback::onQryTradingAccount -> AccountID==[{}], Balance==[{}], " +
                                "Available==[{}], Credit==[{}], WithdrawQuota==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getBalance(),
                        Field.getAvailable(), Field.getCredit(), Field.getWithdrawQuota(), IsLast);
                publisher.publish(Field);
            } else
                log.error("TraderGateway::OnRspQryTradingAccount return null");
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
        log.info("TraderGateway::OnRspQryInstrument, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::OnRspQryInstrument", RspInfo))
            if (Field != null)
                log.info("Output :: OnRspQryInstrument, ExchangeID==[{}], InstrumentID==[{}]",
                        Field.getExchangeID(), Field.getInstrumentID());
            else
                log.error("TraderGateway::OnRspQryInstrument return null");
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

        log.info("TraderGateway::OnRspQrySettlementInfo, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderGateway::OnRspQrySettlementInfo", RspInfo))
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
                log.error("TraderGateway::OnRspQrySettlementInfo return null");
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
        log.info("TraderGateway::fireRspQrySettlementInfoConfirm");
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
        log.info("TraderGateway::fireRspError, ErrorID=[{}], ErrorMsg=[{}], RequestID==[{}], IsLast==[{}]",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
        publisher.publish(RspInfo, RequestID, IsLast);
    }

    // 报单推送消息模板
    private static final String OnRtnOrderMsg = "TraderGateway::fireRtnOrder -> OrderRef==[{}], " +
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
            log.error("TraderGateway::fireRtnOrder return null");
    }

    // 成交推送消息模板
    private static final String OnRtnTradeMsg = "TraderGateway::fireRtnTrade -> OrderRef==[{}], " +
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
            log.error("TraderGateway::fireRtnTrade return null");
    }

    /**
     * ///报单录入错误回报
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderInsert(CThostFtdcInputOrderField Field, CThostFtdcRspInfoField RspInfo) {
        log.info("TraderGateway::OnErrRtnOrderInsert");
        if (nonError("TraderGateway::OnErrRtnOrderInsert", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onErrRtnOrderInsert -> OrderRef==[{}], RequestID==[{}]", Field.getOrderRef(),
                        Field.getRequestID());
                publisher.publish(Field);
            } else {
                log.error("TraderGateway::OnErrRtnOrderInsert return null");
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
        log.info("TraderGateway::OnErrRtnOrderAction");
        if (nonError("TraderGateway::OnErrRtnOrderAction", RspInfo)) {
            if (Field != null) {
                log.info("FtdcCallback::onErrRtnOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publish(Field);
            } else {
                log.error("TraderGateway::OnErrRtnOrderAction return null");
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
        log.info("");
        publisher.publishInstrumentStatus(Field);
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

    }

    @Override
    public String toString() {
        return "FtdcTraderGateway{gatewayId='" + gatewayId + "'}";
    }

}
