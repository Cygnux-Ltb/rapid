package io.rapid.adaptor.ctp.gateway;

import io.mercury.common.annotation.CalledNativeFunction;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.Sleep;
import io.rapid.adaptor.ctp.consts.FtdcBizType;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspPublisher;
import io.rapid.adaptor.ctp.gateway.upstream.FtdcTraderSpi;
import io.rapid.adaptor.ctp.gateway.upstream.LoggingFtdcTraderListener;
import io.rapid.adaptor.ctp.param.CtpParams;
import lombok.Getter;
import org.rationalityfrontline.jctp.CThostFtdcBatchOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputBatchOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentStatusField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorPositionField;
import org.rationalityfrontline.jctp.CThostFtdcOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcOrderField;
import org.rationalityfrontline.jctp.CThostFtdcQryInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcQryInvestorPositionField;
import org.rationalityfrontline.jctp.CThostFtdcQryOrderField;
import org.rationalityfrontline.jctp.CThostFtdcQrySettlementInfoField;
import org.rationalityfrontline.jctp.CThostFtdcQryTradeField;
import org.rationalityfrontline.jctp.CThostFtdcQryTradingAccountField;
import org.rationalityfrontline.jctp.CThostFtdcRspAuthenticateField;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcSettlementInfoConfirmField;
import org.rationalityfrontline.jctp.CThostFtdcSettlementInfoField;
import org.rationalityfrontline.jctp.CThostFtdcTradeField;
import org.rationalityfrontline.jctp.CThostFtdcTraderApi;
import org.rationalityfrontline.jctp.CThostFtdcTraderSpi;
import org.rationalityfrontline.jctp.CThostFtdcTradingAccountField;
import org.rationalityfrontline.jctp.CThostFtdcUserLogoutField;
import org.slf4j.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static io.mercury.common.datetime.DateTimeUtil.date;
import static io.mercury.common.file.FileUtil.mkdirInHome;
import static io.mercury.common.lang.Asserter.nonNull;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;
import static io.mercury.common.util.StringSupport.nonEmpty;
import static io.rapid.adaptor.ctp.gateway.FtdcFieldValidator.nonError;
import static io.rapid.adaptor.ctp.gateway.FtdcFieldValidator.nonnull;
import static io.rapid.adaptor.ctp.serializable.shared.EventSource.TD;
import static org.rationalityfrontline.jctp.THOST_TE_RESUME_TYPE.THOST_TERT_RESUME;

public final class CtpTraderGateway extends LoggingFtdcTraderListener implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpTraderGateway.class);

    @Native
    private CThostFtdcTraderApi FtdcTraderApi;

    // 是否已初始化
    private final AtomicBoolean isInitialize = new AtomicBoolean(false);

    // 是否已认证
    private final AtomicBoolean isAuthenticate = new AtomicBoolean(false);

    // 是否已登陆交易接口
    private final AtomicBoolean isAvailable = new AtomicBoolean(false);

    // 交易前置号
    private final AtomicInteger frontId = new AtomicInteger(-1);
    // 交易会话号
    private final AtomicInteger sessionId = new AtomicInteger(-1);

    // 交易请求ID
    private final AtomicInteger requestIdAllocator = new AtomicInteger(-1);

    @Getter
    private final String gatewayId;

    private final CtpParams params;

    private final FtdcRspPublisher publisher;

    public CtpTraderGateway(CtpParams params, FtdcRspPublisher publisher) {
        this.params = nonNull(params, "params");
        this.publisher = nonNull(publisher, "publisher");
        this.gatewayId = "GW-TD-" + params.getBrokerId() + "-" + params.getInvestorId();
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcTraderApi::GetApiVersion -> {}", CThostFtdcTraderApi.GetApiVersion());
            try {
                startNewMaxPriorityThread(gatewayId + "-Thread", this::CallInitAndJoin);
            } catch (Exception e) {
                log.error("TraderGateway initAndJoin throw Exception -> {}", e.getMessage(), e);
                isInitialize.set(false);
                throw new RuntimeException(e);
            }
        }
    }

    @CalledNativeFunction
    private void CallInitAndJoin() {
        // 创建CTP数据文件临时目录
        File tempDir = mkdirInHome(gatewayId + "-" + date());
        log.info("TraderGateway -> used trader tempDir: {}", tempDir.getAbsolutePath());
        // 指定trader临时文件地址
        String tempFile = new File(tempDir, "trader").getAbsolutePath();
        log.info("TraderGateway -> used trader tempFile : {}", tempFile);

        // 创建traderApi
        log.info("TraderGateway -> call CThostFtdcTraderApi::CreateFtdcTraderApi#{}", tempFile);
        this.FtdcTraderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(tempFile);

        // 创建traderSpi
        log.info("TraderGateway -> create CThostFtdcTraderSpi implement with FtdcTraderSpi");
        CThostFtdcTraderSpi TraderSpi = new FtdcTraderSpi(this);

        // 将Spi注册到CThostFtdcTraderApi
        log.info("TraderGateway -> call CThostFtdcTraderApi::RegisterSpi#FtdcTraderSpi");
        FtdcTraderApi.RegisterSpi(TraderSpi);

        // 注册到trader前置机
        log.info("TraderGateway -> call CThostFtdcTraderApi::RegisterFront#{}", params.getTraderAddr());
        FtdcTraderApi.RegisterFront(params.getTraderAddr());

        // THOST_TERT_RESTART:从本交易日开始重传
        // THOST_TERT_RESUME:从上次收到的续传
        // THOST_TERT_QUICK:只传送登录后私有流的内容
        // 订阅公有流, 设置为[THOST_TERT_RESUME]
        log.info("TraderGateway -> call CThostFtdcTraderApi::SubscribePublicTopic#THOST_TERT_RESUME");
        FtdcTraderApi.SubscribePublicTopic(THOST_TERT_RESUME);

        // 订阅私有流, 设置为[THOST_TERT_RESUME]
        log.info("TraderGateway -> call CThostFtdcTraderApi::SubscribePrivateTopic#THOST_TERT_RESUME");
        FtdcTraderApi.SubscribePrivateTopic(THOST_TERT_RESUME);

        // 初始化traderApi
        log.info("TraderGateway -> call CThostFtdcTraderApi::Init");
        FtdcTraderApi.Init();

        // 阻塞当前线程
        log.info("TraderGateway -> call CThostFtdcTraderApi::Join");
        FtdcTraderApi.Join();
    }

    /**
     * 报单请求
     *
     * @param ReqField CThostFtdcInputOrderField
     */
    @CalledNativeFunction
    public int ReqOrderInsert(CThostFtdcInputOrderField ReqField) {
        if (isAvailable.get()) {
            // 设置账号信息
            int RequestID = requestIdAllocator.incrementAndGet();
            FtdcTraderApi.ReqOrderInsert(ReqField, RequestID);
            log.info("Send CThostFtdcTraderApi::ReqOrderInsert OK -> " +
                            "RequestID==[{}], OrderRef==[{}], InstrumentID==[{}], " +
                            "CombOffsetFlag==[{}], Direction==[{}], " +
                            "VolumeTotalOriginal==[{}], LimitPrice==[{}]",
                    RequestID, ReqField.getOrderRef(), ReqField.getInstrumentID(),
                    ReqField.getCombOffsetFlag(), ReqField.getDirection(),
                    ReqField.getVolumeTotalOriginal(), ReqField.getLimitPrice());
            return RequestID;
        } else {
            log.error("CThostFtdcTraderApi::ReqOrderInsert is Unavailable");
            return -1;
        }
    }

    /**
     * 撤单请求
     *
     * @param ReqField CThostFtdcInputOrderActionField
     */
    @CalledNativeFunction
    public int ReqOrderAction(CThostFtdcInputOrderActionField ReqField) {
        if (isAvailable.get()) {
            int RequestID = requestIdAllocator.incrementAndGet();
            ReqField.setFrontID(frontId.get());
            ReqField.setSessionID(sessionId.get());
            FtdcTraderApi.ReqOrderAction(ReqField, RequestID);
            log.info("Send CThostFtdcTraderApi::ReqOrderAction OK -> " +
                            "RequestID==[{}], OrderRef==[{}], OrderActionRef==[{}], " +
                            "BrokerID==[{}], InvestorID==[{}], InstrumentID==[{}]",
                    RequestID, ReqField.getOrderRef(), ReqField.getOrderActionRef(), ReqField.getBrokerID(),
                    ReqField.getInvestorID(), ReqField.getInstrumentID());
            return RequestID;
        } else {
            log.error("CThostFtdcTraderApi::ReqOrderAction is Unavailable");
            return -1;
        }

    }

    /**
     * 查询订单
     *
     * <pre>
     * ///查询报单
     * struct CThostFtdcQryOrderField
     * {
     * ///经纪公司代码
     * TThostFtdcBrokerIDType	BrokerID;
     * ///投资者代码
     * TThostFtdcInvestorIDType	InvestorID;
     * ///合约代码
     * TThostFtdcInstrumentIDType	InstrumentID;
     * ///交易所代码
     * TThostFtdcExchangeIDType	ExchangeID;
     * ///报单编号
     * TThostFtdcOrderSysIDType	OrderSysID;
     * ///开始时间
     * TThostFtdcTimeType	InsertTimeStart;
     * ///结束时间
     * TThostFtdcTimeType	InsertTimeEnd;
     * ///投资单元代码
     * TThostFtdcInvestUnitIDType	InvestUnitID;
     * };
     * </pre>
     */
    @CalledNativeFunction
    public int ReqQryOrder() {
        var ReqField = new CThostFtdcQryOrderField();
        //经纪公司代码
        ReqField.setBrokerID(params.getBrokerId());
        //投资者代码
        ReqField.setInvestorID(params.getInvestorId());
        //合约代码
        // ReqField.setInstrumentID(InstrumentID);
        //交易所代码
        // ReqField.setExchangeID(ExchangeID);
        //报单编号
        // ReqField.setOrderSysID("");
        //开始时间
        // ReqField.setInsertTimeStart("");
        //结束时间
        // ReqField.setInsertTimeEnd("");
        //投资单元代码
        // ReqField.setInvestUnitID("");
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryOrder(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryOrder OK -> " +
                        "RequestID==[{}], BrokerID==[{}], InvestorID==[{}], " +
                        "ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, ReqField.getBrokerID(), ReqField.getInvestorID(),
                ReqField.getExchangeID(), ReqField.getInstrumentID());
        return RequestID;
    }


    /**
     * 查询订单
     * <pre>
     * ///查询成交
     * struct CThostFtdcQryTradeField
     * {
     * ///经纪公司代码
     * TThostFtdcBrokerIDType	BrokerID;
     * ///投资者代码
     * TThostFtdcInvestorIDType	InvestorID;
     * ///合约代码
     * TThostFtdcInstrumentIDType	InstrumentID;
     * ///交易所代码
     * TThostFtdcExchangeIDType	ExchangeID;
     * ///成交编号
     * TThostFtdcTradeIDType	TradeID;
     * ///开始时间
     * TThostFtdcTimeType	TradeTimeStart;
     * ///结束时间
     * TThostFtdcTimeType	TradeTimeEnd;
     * ///投资单元代码
     * TThostFtdcInvestUnitIDType	InvestUnitID;
     * };
     * </pre>
     */
    @CalledNativeFunction
    public int ReqQryTrade() {
        var ReqField = new CThostFtdcQryTradeField();
        //经纪公司代码
        ReqField.setBrokerID(params.getBrokerId());
        //投资者代码
        ReqField.setInvestorID(params.getInvestorId());
        //合约代码
        // ReqField.setInstrumentID(InstrumentID);
        //交易所代码
        // ReqField.setExchangeID(ExchangeID);
        //报单编号
        // ReqField.setOrderSysID("");
        //开始时间
        // ReqField.setInsertTimeStart("");
        //结束时间
        // ReqField.setInsertTimeEnd("");
        //投资单元代码
        // ReqField.setInvestUnitID("");
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryTrade(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryTrade OK -> " +
                        "RequestID==[{}], BrokerID==[{}], InvestorID==[{}], " +
                        "ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, ReqField.getBrokerID(), ReqField.getInvestorID(),
                ReqField.getExchangeID(), ReqField.getInstrumentID());
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
    @CalledNativeFunction
    public int ReqQryTradingAccount() {
        var ReqField = new CThostFtdcQryTradingAccountField();
        //经纪公司代码
        ReqField.setBrokerID(params.getBrokerId());
        //投资者代码
        ReqField.setInvestorID(params.getInvestorId());
        //币种代码
        ReqField.setCurrencyID(params.getCurrencyId());
        //业务类型
        ReqField.setBizType(FtdcBizType.Future);
        //投资者账号
        ReqField.setAccountID(params.getAccountId());
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryTradingAccount(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryTradingAccount OK -> " +
                        "RequestID==[{}], BrokerID==[{}], InvestorID==[{}], " +
                        "CurrencyID==[{}], BizType==[{}] AccountID==[{}]",
                RequestID, ReqField.getBrokerID(), ReqField.getInvestorID(),
                ReqField.getCurrencyID(), ReqField.getBizType(), ReqField.getAccountID());
        return RequestID;
    }

    /**
     * 查询持仓
     *
     * @param ExchangeID   String
     * @param InstrumentID String
     */
    @CalledNativeFunction
    public int ReqQryInvestorPosition(String ExchangeID, String InstrumentID) {
        var ReqField = new CThostFtdcQryInvestorPositionField();
        //经纪公司代码
        ReqField.setBrokerID(params.getBrokerId());
        //投资者代码
        ReqField.setInvestorID(params.getInvestorId());
        //合约代码
        ReqField.setInstrumentID(InstrumentID);
        //交易所代码
        ReqField.setExchangeID(ExchangeID);
        //投资单元代码
        // ReqField.setInvestUnitID("");
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryInvestorPosition(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryInvestorPosition OK -> " +
                        "RequestID==[{}], BrokerID==[{}], InvestorID==[{}], " +
                        "ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, ReqField.getBrokerID(), ReqField.getInvestorID(),
                ReqField.getExchangeID(), ReqField.getInstrumentID());
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
    @CalledNativeFunction
    public int ReqQrySettlementInfo() {
        var ReqField = new CThostFtdcQrySettlementInfoField();
        ReqField.setBrokerID(params.getBrokerId());
        ReqField.setInvestorID(params.getInvestorId());
        ReqField.setTradingDay(params.getTradingDay());
        ReqField.setAccountID(params.getAccountId());
        ReqField.setCurrencyID(params.getCurrencyId());
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQrySettlementInfo(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQrySettlementInfo OK -> " +
                "RequestID==[{}]", RequestID);
        return RequestID;
    }

    /**
     * 查询交易标的
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    @CalledNativeFunction
    public int ReqQryInstrument(String exchangeCode, String instrumentCode) {
        var ReqField = new CThostFtdcQryInstrumentField();
        ReqField.setExchangeID(exchangeCode);
        ReqField.setInstrumentID(instrumentCode);
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryInstrument(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryInstrument OK -> " +
                        "RequestID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, ReqField.getExchangeID(), ReqField.getInstrumentID());
        return RequestID;
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
    @CalledNativeFunction
    public void fireFrontConnected() {
        log.info("TraderGateway::fireFrontConnected");
        if (nonEmpty(params.getAuthCode()) && !isAuthenticate.get()) {
            // 发送认证请求
            var ReqField = params.getReqAuthenticateField();
            int RequestID = requestIdAllocator.incrementAndGet();
            FtdcTraderApi.ReqAuthenticate(ReqField, RequestID);
            log.info("Sent CThostFtdcTraderApi::ReqAuthenticate OK -> " +
                            "RequestID==[{}], BrokerID==[{}], UserID==[{}], " +
                            "AppID==[{}], AuthCode==[{}]",
                    RequestID, ReqField.getBrokerID(), ReqField.getUserID(),
                    ReqField.getAppID(), ReqField.getAuthCode());
        } else {
            log.error("Cannot sent CThostFtdcTraderApi::ReqAuthenticate, " +
                            "authCode==[{}], isAuthenticate==[{}]",
                    params.getAuthCode(), isAuthenticate);
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
        log.warn("TraderGateway::fireFrontDisconnected OK -> " +
                "Reason==[{}]", Reason);
        isAvailable.set(false);
        isAuthenticate.set(false);
        // 交易前置断开处理
        publisher.publishFrontDisconnected(TD, Reason, params.getBrokerId(), params.getUserId());
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.info("TraderGateway::fireHeartBeatWarning OK -> " +
                "TimeLapse==[{}]", TimeLapse);
        publisher.publishHeartBeatWarning(TD, TimeLapse, params.getBrokerId(), params.getUserId());
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
    @CalledNativeFunction
    public void fireRspAuthenticate(CThostFtdcRspAuthenticateField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "TraderGateway::fireRspAuthenticate", RequestID, IsLast)) {
            if (nonnull(Field, "TraderGateway::fireRspAuthenticate", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspAuthenticate OK -> " +
                                "BrokerID==[{}], UserID==[{}]",
                        Field.getBrokerID(), Field.getUserID());
                isAuthenticate.set(true);
                var ReqField = params.getReqUserLoginField();
                int ReqRequestID = requestIdAllocator.incrementAndGet();
                FtdcTraderApi.ReqUserLogin(ReqField, ReqRequestID);
                log.info("Sent CThostFtdcTraderApi::ReqUserLogin OK -> RequestID==[{}]", ReqRequestID);
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
        if (nonError(RspInfo, "TraderGateway::fireRspUserLogin", RequestID, IsLast)) {
            if (nonnull(Field, "TraderGateway::fireRspUserLogin", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspUserLogin OK -> " +
                                "BrokerID==[{}], UserID==[{}], " +
                                "LoginTime==[{}], MaxOrderRef==[{}]",
                        Field.getBrokerID(), Field.getUserID(),
                        Field.getLoginTime(), Field.getMaxOrderRef());
                this.frontId.set(Field.getFrontID());
                this.sessionId.set(Field.getSessionID());
                this.isAvailable.set(true);
                publisher.publishRspUserLogin(TD, Field, RspInfo, RequestID, IsLast);
            }
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
        if (nonError(RspInfo, "TraderGateway::fireRspUserLogout", RequestID, IsLast))
            if (nonnull(Field, "TraderGateway::fireRspUserLogout", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspUserLogout OK -> " +
                                "BrokerID==[{}], UserID==[{}]",
                        Field.getBrokerID(), Field.getUserID());
                publisher.publishUserLogout(TD, Field, RspInfo, RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspOrderInsert", RequestID, IsLast)) {
            if (nonnull(Field, "TraderGateway::fireRspOrderInsert", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspOrderInsert OK -> " +
                        "OrderRef==[{}]", Field.getOrderRef());
                publisher.publishInputOrder(Field, RspInfo, IsLast);
            }
        }
    }


    /**
     * ///报单操作请求响应 - [撤单错误回调:1]
     *
     * @param Field     CThostFtdcInputOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOrderAction(CThostFtdcInputOrderActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "TraderGateway::fireRspOrderAction", RequestID, IsLast)) {
            if (nonnull(Field, "TraderGateway::fireRspOrderAction", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspOrderAction OK -> " +
                                "OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publishInputOrderAction(Field, RspInfo, IsLast);
            }
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
        log.info("TraderGateway::fireRspBatchOrderAction OK -> " +
                "RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryOrder", RequestID, IsLast)) {
            if (nonnull(Field, "TraderGateway::fireRspQryOrder", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspQryOrder OK -> " +
                                "AccountID==[{}], OrderRef==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getOrderRef(), IsLast);
                publisher.publishOrder(Field);
            }
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
        log.info("TraderGateway::fireRspQryTrade OK -> " +
                "RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryInvestorPosition", RequestID, IsLast))
            if (nonnull(Field, "TraderGateway::fireRspQryInvestorPosition", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspQryInvestorPosition OK -> " +
                                "InvestorID==[{}], ExchangeID==[{}], " +
                                "InstrumentID==[{}], Position==[{}], isLast==[{}]",
                        Field.getInvestorID(), Field.getExchangeID(),
                        Field.getInstrumentID(), Field.getPosition(), IsLast);
                publisher.publishInvestorPosition(Field, RspInfo, RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryTradingAccount")) {
            if (nonnull(Field, "TraderGateway::fireRspQryTradingAccount")) {
                log.info("TraderGateway::fireRspQryTradingAccount OK -> " +
                                "AccountID==[{}], Balance==[{}], Available==[{}], " +
                                "Credit==[{}], WithdrawQuota==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getBalance(), Field.getAvailable(),
                        Field.getCredit(), Field.getWithdrawQuota(), IsLast);
                publisher.publishTradingAccount(Field, RspInfo, RequestID, IsLast);
            }
        }
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryInstrument", RequestID, IsLast)) {
            if (nonnull(Field, "TraderGateway::fireRspQryInstrument", RequestID, IsLast)) {
                log.info("TraderGateway::fireRspQryInstrument OK -> " +
                                "ExchangeID==[{}], InstrumentID==[{}]",
                        Field.getExchangeID(), Field.getInstrumentID());
            }
        }
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
    public void fireRspQrySettlementInfo(CThostFtdcSettlementInfoField Field, CThostFtdcRspInfoField RspInfo,
                                         int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "TraderGateway::fireRspQrySettlementInfo", RequestID, IsLast)) {
            if (nonnull(Field, "TraderGateway::fireRspQrySettlementInfo", RequestID, IsLast)) {
                log.info("""
                                TraderGateway::fireRspQrySettlementInfo OK -> BrokerID==[{}], AccountID==[{}], InvestorID==[{}],
                                SettlementID==[{}], TradingDay==[{}], CurrencyID==[{}]
                                <<<<<<<<<<<<<<<< CONTENT TEXT >>>>>>>>>>>>>>>>
                                {}
                                """,
                        Field.getBrokerID(), Field.getAccountID(), Field.getInvestorID(),
                        Field.getSettlementID(), Field.getTradingDay(),
                        Field.getCurrencyID(), Field.getContent());
            }
        }
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
        log.info("TraderGateway::fireRspQrySettlementInfoConfirm OK -> " +
                        "ErrorID==[{}], ErrorMsg==[{}] RequestID==[{}], IsLast==[{}]",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }


    /**
     * ///错误应答
     *
     * @param Field     CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspError(CThostFtdcRspInfoField Field, int RequestID, boolean IsLast) {
        log.warn("NOTE >>>> TraderGateway::fireRspError OK -> " +
                        "ErrorID=[{}], ErrorMsg=[{}], RequestID==[{}], IsLast==[{}]",
                Field.getErrorID(), Field.getErrorMsg(), RequestID, IsLast);
        publisher.publishRspError(TD, Field, RequestID, IsLast);
    }


    /**
     * ///报单通知
     *
     * @param Field CThostFtdcOrderField
     */
    @Override
    public void fireRtnOrder(CThostFtdcOrderField Field) {
        log.info("NOTE >>>> TraderGateway::fireRtnOrder");
        if (nonnull(Field, "TraderGateway::fireRtnOrder")) {
            log.info(// 报单推送消息模板
                    "TraderGateway::fireRtnOrder OK -> " +
                            "OrderRef==[{}], OrderSysID==[{}], RequestID==[{}]" +
                            "InvestorID==[{}], InstrumentID==[{}], OrderStatus==[{}], " +
                            "Direction==[{}], VolumeTotalOriginal==[{}], LimitPrice==[{}]",
                    Field.getOrderRef(), Field.getOrderSysID(), Field.getRequestID(),
                    Field.getInvestorID(), Field.getInstrumentID(), Field.getOrderStatus(),
                    Field.getDirection(), Field.getVolumeTotalOriginal(), Field.getLimitPrice());
            publisher.publishOrder(Field);
        }
    }


    /**
     * ///成交通知
     *
     * @param Field CThostFtdcTradeField
     */
    @Override
    public void fireRtnTrade(CThostFtdcTradeField Field) {
        log.info("NOTE >>>> TraderGateway::fireRtnTrade");
        if (nonnull(Field, "TraderGateway::fireRtnTrade")) {
            log.info(// 成交推送消息模板
                    "TraderGateway::fireRtnTrade OK -> " +
                            "OrderRef==[{}], OrderSysID==[{}], TradeID==[{}]" +
                            "InvestorID==[{}], InstrumentID==[{}], TradeTime==[{}]" +
                            "Direction==[{}], Volume==[{}], Price==[{}]",
                    Field.getOrderRef(), Field.getOrderSysID(), Field.getTradeID(),
                    Field.getInvestorID(), Field.getInstrumentID(), Field.getTradeTime(),
                    Field.getDirection(), Field.getVolume(), Field.getPrice());
            publisher.publishTrade(Field);
        }
    }

    /**
     * ///报单录入错误回报
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderInsert(CThostFtdcInputOrderField Field, CThostFtdcRspInfoField RspInfo) {
        if (nonError(RspInfo, "TraderGateway::fireErrRtnOrderInsert")) {
            if (nonnull(Field, "TraderGateway::fireErrRtnOrderInsert")) {
                log.warn("TraderGateway::fireErrRtnOrderInsert OK -> " +
                                "OrderRef==[{}], RequestID==[{}]",
                        Field.getOrderRef(), Field.getRequestID());
                publisher.publishInputOrder(Field, RspInfo, true);
            }
        }
    }

    /**
     * ///报单操作错误回报 - [撤单错误回调: 2]
     *
     * @param Field   CThostFtdcOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderAction(CThostFtdcOrderActionField Field, CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderGateway::fireErrRtnOrderAction");
        if (nonError(RspInfo, "TraderGateway::fireErrRtnOrderAction")) {
            if (nonnull(Field, "TraderGateway::fireErrRtnOrderAction")) {
                log.warn("TraderGateway::fireErrRtnOrderAction OK -> " +
                                "OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publishOrderAction(Field);
            }
        }
    }

    /**
     * ///合约交易状态通知
     *
     * @param Field CThostFtdcInstrumentStatusField
     */
    @Override
    public void fireRtnInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        log.info("TraderGateway::fireRtnInstrumentStatus");
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
        log.info("TraderGateway::fireErrRtnBatchOrderAction");
    }


    @Override
    public String toString() {
        return gatewayId;
    }

    /**
     * 关闭函数, 资源清理
     */
    @Override
    public void close() throws IOException {
        startNewThread("TraderApi-Release", this::ftdcRelease);
        Sleep.millis(1000);
    }

    @CalledNativeFunction
    private void ftdcRelease() {
        log.info("CThostFtdcTraderApi start release");
        if (FtdcTraderApi != null) FtdcTraderApi.Release();
        log.info("CThostFtdcTraderApi is released");
    }

}
