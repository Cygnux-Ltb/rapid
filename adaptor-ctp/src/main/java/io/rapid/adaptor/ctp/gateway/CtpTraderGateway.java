package io.rapid.adaptor.ctp.gateway;

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
import ctp.thostapi.CThostFtdcQryTradeField;
import ctp.thostapi.CThostFtdcQryTradingAccountField;
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
import io.mercury.common.annotation.CalledNativeFunction;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.Sleep;
import io.mercury.common.util.StringSupport;
import io.rapid.adaptor.ctp.consts.FtdcBizType;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspPublisher;
import io.rapid.adaptor.ctp.gateway.upstream.FtdcTraderSpi;
import io.rapid.adaptor.ctp.gateway.upstream.LogFtdcTraderListener;
import io.rapid.adaptor.ctp.param.CtpParams;
import lombok.Getter;
import org.slf4j.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static ctp.thostapi.THOST_TE_RESUME_TYPE.THOST_TERT_RESUME;
import static io.mercury.common.datetime.DateTimeUtil.date;
import static io.mercury.common.file.FileUtil.mkdirInTmp;
import static io.mercury.common.lang.Asserter.nonNull;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;
import static io.rapid.adaptor.ctp.gateway.FtdcRspInfoHandler.nonError;
import static io.rapid.adaptor.ctp.serializable.avro.shared.EventSource.TD;

public final class CtpTraderGateway extends LogFtdcTraderListener implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpTraderGateway.class);

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
    private CThostFtdcTraderApi FtdcTraderApi;

    // 是否已初始化
    private final AtomicBoolean isInitialize = new AtomicBoolean(false);

    // 是否已认证
    private volatile boolean isAuthenticate;

    // 是否已登陆交易接口
    private volatile boolean isAvailable;

    // 交易前置号
    private volatile int frontId;
    // 交易会话号
    private volatile int sessionId;

    // 交易请求ID
    private final AtomicInteger requestIdAllocator = new AtomicInteger(-1);

    @Getter
    private final String gatewayId;

    private final CtpParams params;

    private final FtdcRspPublisher publisher;

    public CtpTraderGateway(CtpParams params, FtdcRspPublisher publisher) {
        this.params = nonNull(params, "params");
        this.publisher = nonNull(publisher, "publisher");
        this.gatewayId = "GATEWAY-TD-" + params.getBrokerId() + "-" + params.getInvestorId();
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcTraderApi.GetApiVersion() -> {}", CThostFtdcTraderApi.GetApiVersion());
            try {
                startNewMaxPriorityThread(gatewayId + "-Thread", this::callInitAndJoin);
            } catch (Exception e) {
                log.error("TraderGateway initAndJoin throw Exception -> {}", e.getMessage(), e);
                isInitialize.set(false);
                throw new RuntimeException(e);
            }
        }
    }

    @CalledNativeFunction
    private void callInitAndJoin() {
        // 创建CTP数据文件临时目录
        var tempDir = mkdirInTmp(gatewayId + "-" + date());
        log.info("{} -> used trader tempDir: {}", gatewayId, tempDir.getAbsolutePath());
        // 指定trader临时文件地址
        var tempFile = new File(tempDir, "trader").getAbsolutePath();
        log.info("{} -> used trader tempFile : {}", gatewayId, tempFile);
        // 创建traderApi
        this.FtdcTraderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(tempFile);
        log.info("{} -> called native CThostFtdcTraderApi::CreateFtdcTraderApi", gatewayId);
        // 创建traderSpi
        CThostFtdcTraderSpi Spi = new FtdcTraderSpi(this);
        log.info("{} -> created CThostFtdcTraderSpi with FtdcTraderSpi", gatewayId);
        // 将Spi注册到CThostFtdcTraderApi
        FtdcTraderApi.RegisterSpi(Spi);
        log.info("{} -> called native CThostFtdcTraderApi::RegisterSpi", gatewayId);
        // 注册到trader前置机
        FtdcTraderApi.RegisterFront(params.getTraderAddr());
        log.info("{} -> called native CThostFtdcTraderApi::RegisterFront", gatewayId);
        /// THOST_TERT_RESTART:从本交易日开始重传
        /// THOST_TERT_RESUME:从上次收到的续传
        /// THOST_TERT_QUICK:只传送登录后私有流的内容
        // 订阅公有流, 设置为[THOST_TERT_RESUME]
        FtdcTraderApi.SubscribePublicTopic(THOST_TERT_RESUME);
        log.info("{} -> called native CThostFtdcTraderApi::SubscribePublicTopic(THOST_TERT_RESUME)", gatewayId);
        // 订阅私有流, 设置为[THOST_TERT_RESUME]
        FtdcTraderApi.SubscribePrivateTopic(THOST_TERT_RESUME);
        log.info("{} -> called native CThostFtdcTraderApi::SubscribePrivateTopic(THOST_TERT_RESUME)", gatewayId);
        // 初始化traderApi
        FtdcTraderApi.Init();
        log.info("{} -> called native CThostFtdcTraderApi::Init", gatewayId);
        // 阻塞当前线程
        log.info("{} -> calling native CThostFtdcTraderApi::Join", gatewayId);
        FtdcTraderApi.Join();
    }

    /**
     * 报单请求
     *
     * @param Field CThostFtdcInputOrderField
     */
    @CalledNativeFunction
    public int callReqOrderInsert(CThostFtdcInputOrderField Field) {
        if (isAvailable) {
            // 设置账号信息
            int RequestID = requestIdAllocator.incrementAndGet();
            FtdcTraderApi.ReqOrderInsert(Field, RequestID);
            log.info("Send TraderApi::ReqOrderInsert OK -> RequestID==[{}], OrderRef==[{}], InstrumentID==[{}], "
                            + "CombOffsetFlag==[{}], Direction==[{}], VolumeTotalOriginal==[{}], LimitPrice==[{}]",
                    RequestID, Field.getOrderRef(), Field.getInstrumentID(), Field.getCombOffsetFlag(),
                    Field.getDirection(), Field.getVolumeTotalOriginal(), Field.getLimitPrice());
            return RequestID;
        } else {
            log.error("TraderApi::ReqOrderInsert call error :: TraderApi is Unavailable");
            return -1;
        }
    }

    /**
     * 撤单请求
     *
     * @param Field CThostFtdcInputOrderActionField
     */
    @CalledNativeFunction
    public int callReqOrderAction(CThostFtdcInputOrderActionField Field) {
        if (isAvailable) {
            int RequestID = requestIdAllocator.incrementAndGet();
            FtdcTraderApi.ReqOrderAction(Field, RequestID);
            log.info("Send TraderApi::ReqOrderAction OK -> RequestID==[{}], OrderRef==[{}], OrderActionRef==[{}], "
                            + "BrokerID==[{}], InvestorID==[{}], InstrumentID==[{}]",
                    RequestID, Field.getOrderRef(), Field.getOrderActionRef(), Field.getBrokerID(),
                    Field.getInvestorID(), Field.getInstrumentID());
            return RequestID;
        } else {
            log.error("TraderApi::ReqOrderAction call error :: TraderApi is Unavailable");
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
    public int callReqQryOrder() {
        var Field = new CThostFtdcQryOrderField();
        ///经纪公司代码
        Field.setBrokerID(params.getBrokerId());
        ///投资者代码
        Field.setInvestorID(params.getInvestorId());
        ///合约代码
        // Field.setInstrumentID(InstrumentID);
        ///交易所代码
        // Field.setExchangeID(ExchangeID);
        ///报单编号
        // Field.setOrderSysID("");
        ///开始时间
        // Field.setInsertTimeStart("");
        ///结束时间
        // Field.setInsertTimeEnd("");
        ///投资单元代码
        // Field.setInvestUnitID("");
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryOrder(Field, RequestID);
        log.info("Send TraderApi::ReqQryOrder OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, Field.getBrokerID(), Field.getInvestorID(), Field.getExchangeID(), Field.getInstrumentID());
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
    public int callReqQryTrade() {
        var Field = new CThostFtdcQryTradeField();
        ///经纪公司代码
        Field.setBrokerID(params.getBrokerId());
        ///投资者代码
        Field.setInvestorID(params.getInvestorId());
        ///合约代码
        // Field.setInstrumentID(InstrumentID);
        ///交易所代码
        // Field.setExchangeID(ExchangeID);
        ///报单编号
        // Field.setOrderSysID("");
        ///开始时间
        // Field.setInsertTimeStart("");
        ///结束时间
        // Field.setInsertTimeEnd("");
        ///投资单元代码
        // Field.setInvestUnitID("");
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryTrade(Field, RequestID);
        log.info("Send TraderApi::ReqQryOrder OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, Field.getBrokerID(), Field.getInvestorID(), Field.getExchangeID(), Field.getInstrumentID());
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
    public int callReqQryTradingAccount() {
        var Field = new CThostFtdcQryTradingAccountField();
        ///经纪公司代码
        Field.setBrokerID(params.getBrokerId());
        ///投资者代码
        Field.setInvestorID(params.getInvestorId());
        ///币种代码
        Field.setCurrencyID(params.getCurrencyId());
        ///业务类型
        Field.setBizType(FtdcBizType.Future);
        ///投资者账号
        Field.setAccountID(params.getAccountId());
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryTradingAccount(Field, RequestID);
        log.info("Send TraderApi::ReqQryTradingAccount OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], CurrencyID==[{}], BizType==[{}] AccountID==[{}]",
                RequestID, Field.getBrokerID(), Field.getInvestorID(), Field.getCurrencyID(), Field.getBizType(), Field.getAccountID());
        return RequestID;
    }

    /**
     * 查询持仓
     *
     * @param ExchangeID   String
     * @param InstrumentID String
     */
    @CalledNativeFunction
    public int callReqQryInvestorPosition(String ExchangeID, String InstrumentID) {
        var Field = new CThostFtdcQryInvestorPositionField();
        ///经纪公司代码
        Field.setBrokerID(params.getBrokerId());
        ///投资者代码
        Field.setInvestorID(params.getInvestorId());
        ///合约代码
        Field.setInstrumentID(InstrumentID);
        ///交易所代码
        Field.setExchangeID(ExchangeID);
        ///投资单元代码
        // Field.setInvestUnitID("");
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryInvestorPosition(Field, RequestID);
        log.info("Send TraderApi::ReqQryInvestorPosition OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, Field.getBrokerID(), Field.getInvestorID(), Field.getExchangeID(), Field.getInstrumentID());
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
    public int callReqQrySettlementInfo() {
        var Field = new CThostFtdcQrySettlementInfoField();
        Field.setBrokerID(params.getBrokerId());
        Field.setInvestorID(params.getInvestorId());
        Field.setTradingDay(params.getTradingDay());
        Field.setAccountID(params.getAccountId());
        Field.setCurrencyID(params.getCurrencyId());
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQrySettlementInfo(Field, RequestID);
        log.info("Send TraderApi::ReqQrySettlementInfo OK -> RequestID==[{}]", RequestID);
        return RequestID;
    }

    /**
     * 查询交易标的
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    @CalledNativeFunction
    public int callReqQryInstrument(String exchangeCode, String instrumentCode) {
        var Field = new CThostFtdcQryInstrumentField();
        Field.setExchangeID(exchangeCode);
        Field.setInstrumentID(instrumentCode);
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcTraderApi.ReqQryInstrument(Field, RequestID);
        log.info("Send TraderApi::ReqQryInstrument OK -> RequestID==[{}], ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, Field.getExchangeID(), Field.getInstrumentID());
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
        if (StringSupport.nonEmpty(params.getAuthCode()) && !isAuthenticate) {
            // 发送认证请求
            var Field = params.getReqAuthenticateField();
            int RequestID = requestIdAllocator.incrementAndGet();
            FtdcTraderApi.ReqAuthenticate(Field, RequestID);
            log.info("Sent TraderApi::ReqAuthenticate OK -> RequestID==[{}], BrokerID==[{}], UserID==[{}], AppID==[{}], AuthCode==[{}]",
                    RequestID, Field.getBrokerID(), Field.getUserID(), Field.getAppID(), Field.getAuthCode());
        } else {
            log.error("Cannot sent TraderApi::ReqAuthenticate, authCode==[{}], isAuthenticate==[{}]",
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
        log.warn("TraderGateway::fireFrontDisconnected");
        isAvailable = false;
        isAuthenticate = false;
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
        log.info("TraderGateway::fireHeartBeatWarning");
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
        log.info("TraderGateway::fireRspAuthenticate, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspAuthenticate -> BrokerID==[{}], UserID==[{}]", Field.getBrokerID(),
                        Field.getUserID());
                isAuthenticate = true;
                var ReqField = params.getReqUserLoginField();
                int ReqRequestID = requestIdAllocator.incrementAndGet();
                FtdcTraderApi.ReqUserLogin(ReqField, ReqRequestID);
                log.info("Sent TraderApi::ReqUserLogin OK -> RequestID==[{}]", ReqRequestID);
            } else {
                log.error("TraderGateway::fireRspAuthenticate return null");
            }
        } else {
            log.error("TraderGateway::fireRspAuthenticate has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspUserLogin -> BrokerID==[{}], UserID==[{}], LoginTime==[{}], MaxOrderRef==[{}]",
                        Field.getBrokerID(), Field.getUserID(), Field.getLoginTime(), Field.getMaxOrderRef());
                this.frontId = Field.getFrontID();
                this.sessionId = Field.getSessionID();
                this.isAvailable = true;
                publisher.publishRspUserLogin(TD, Field, RspInfo, RequestID, IsLast);
            } else {
                log.error("TraderGateway::fireRspUserLogin return null");
            }
        } else {
            log.error("TraderGateway::fireRspUserLogin has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspUserLogout -> BrokerID==[{}], UserID==[{}]", Field.getBrokerID(),
                        Field.getUserID());
                publisher.publishUserLogout(TD, Field, RspInfo, RequestID, IsLast);
            } else {
                log.error("TraderGateway::fireRspUserLogout return null");
            }
        } else {
            log.error("TraderGateway::fireRspUserLogout has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        log.info("TraderGateway::fireRspOrderInsert, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspOrderInsert -> OrderRef==[{}]", Field.getOrderRef());
                publisher.publishInputOrder(Field, RspInfo, IsLast);
            } else {
                log.error("TraderGateway::fireRspOrderInsert return null");
            }
        } else {
            log.error("TraderGateway::fireRspOrderInsert has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        log.info("TraderGateway::fireRspOrderAction, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publishInputOrderAction(Field, RspInfo, IsLast);
            } else {
                log.error("TraderGateway::fireRspOrderAction return null");
            }
        } else {
            log.error("TraderGateway::fireRspOrderAction has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
                                        CThostFtdcRspInfoField RspInfo,
                                        int RequestID, boolean IsLast) {
        log.info("TraderGateway::fireRspBatchOrderAction, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
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
                                CThostFtdcRspInfoField RspInfo,
                                int RequestID, boolean IsLast) {
        log.info("TraderGateway::fireRspQryOrder, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspQryOrder -> AccountID==[{}], OrderRef==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getOrderRef(), IsLast);
                publisher.publishOrder(Field);
            } else {
                log.error("TraderGateway::fireRspQryOrder return null");
            }
        } else {
            log.error("TraderGateway::fireRspQryOrder has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        log.info("TraderGateway::fireRspQryTrade, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
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
        log.info("TraderGateway::fireRspQryInvestorPosition, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireRspQryInvestorPosition -> InvestorID==[{}], ExchangeID==[{}], " +
                                "InstrumentID==[{}], Position==[{}], isLast==[{}]",
                        Field.getInvestorID(), Field.getExchangeID(),
                        Field.getInstrumentID(), Field.getPosition(), IsLast);
                publisher.publishInvestorPosition(Field, RspInfo, RequestID, IsLast);
            } else {
                log.error("TraderGateway::fireRspQryInvestorPosition return null");
            }
        } else {
            log.error("TraderGateway::fireRspQryInvestorPosition has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        log.info("TraderGateway::fireRspQryTradingAccount, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo))
            if (Field != null) {
                log.info("TraderGateway::fireRspQryTradingAccount -> AccountID==[{}], Balance==[{}], " +
                                "Available==[{}], Credit==[{}], WithdrawQuota==[{}], isLast==[{}]",
                        Field.getAccountID(), Field.getBalance(),
                        Field.getAvailable(), Field.getCredit(), Field.getWithdrawQuota(), IsLast);
                publisher.publishTradingAccount(Field, RspInfo, RequestID, IsLast);
            } else
                log.error("TraderGateway::fireRspQryTradingAccount return null");
        else {
            log.error("TraderGateway::fireRspQryTradingAccount has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
    public void fireRspQryInstrument(CThostFtdcInstrumentField Field, CThostFtdcRspInfoField RspInfo,
                                     int RequestID, boolean IsLast) {
        log.info("TraderGateway::fireRspQryInstrument, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo))
            if (Field != null)
                log.info("TraderGateway::fireRspQryInstrument, ExchangeID==[{}], InstrumentID==[{}]",
                        Field.getExchangeID(), Field.getInstrumentID());
            else
                log.error("TraderGateway::fireRspQryInstrument return null");
        else {
            log.error("TraderGateway::fireRspQryInstrument has error, ErrorID==[{}], ErrorMsg==[{}]", RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        log.info("TraderGateway::fireRspQrySettlementInfo, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError(RspInfo))
            if (Field != null)
                log.info("""
                                TraderGateway::fireRspQrySettlementInfo -> BrokerID==[{}], AccountID==[{}], InvestorID==[{}],
                                SettlementID==[{}], TradingDay==[{}], CurrencyID==[{}]
                                <<<<<<<<<<<<<<<< CONTENT TEXT >>>>>>>>>>>>>>>>
                                {}
                                """,
                        Field.getBrokerID(), Field.getAccountID(), Field.getInvestorID(),
                        Field.getSettlementID(), Field.getTradingDay(),
                        Field.getCurrencyID(), Field.getContent());
            else
                log.error("TraderGateway::fireRspQrySettlementInfo return null");
        else {
            log.error("TraderGateway::fireRspQrySettlementInfo has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        log.info("TraderGateway::fireRspQrySettlementInfoConfirm, ErrorID==[{}], ErrorMsg==[{}] RequestID==[{}], IsLast==[{}]",
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
        log.info("TraderGateway::fireRspError, ErrorID=[{}], ErrorMsg=[{}], RequestID==[{}], IsLast==[{}]",
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
        if (Field != null) {
            log.info( // 报单推送消息模板
                    "TraderGateway::fireRtnOrder -> OrderRef==[{}], OrderSysID==[{}], RequestID==[{}]"
                            + "InvestorID==[{}], InstrumentID==[{}], OrderStatus==[{}], "
                            + "Direction==[{}], VolumeTotalOriginal==[{}], LimitPrice==[{}]",
                    Field.getOrderRef(), Field.getOrderSysID(), Field.getRequestID(),
                    Field.getInvestorID(), Field.getInstrumentID(), Field.getOrderStatus(),
                    Field.getDirection(), Field.getVolumeTotalOriginal(), Field.getLimitPrice());
            publisher.publishOrder(Field);
        } else
            log.error("TraderGateway::fireRtnOrder return null");
    }


    /**
     * ///成交通知
     *
     * @param Field CThostFtdcTradeField
     */
    @Override
    public void fireRtnTrade(CThostFtdcTradeField Field) {
        if (Field != null) {
            log.info( // 成交推送消息模板
                    "TraderGateway::fireRtnTrade -> OrderRef==[{}], OrderSysID==[{}], TradeID==[{}]"
                            + "InvestorID==[{}], InstrumentID==[{}], TradeTime==[{}]"
                            + "Direction==[{}], Volume==[{}], Price==[{}]",
                    Field.getOrderRef(), Field.getOrderSysID(), Field.getTradeID(),
                    Field.getInvestorID(), Field.getInstrumentID(), Field.getTradeTime(),
                    Field.getDirection(), Field.getVolume(), Field.getPrice());
            publisher.publishTrade(Field);
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
        log.info("TraderGateway::fireErrRtnOrderInsert");
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireErrRtnOrderInsert -> OrderRef==[{}], RequestID==[{}]",
                        Field.getOrderRef(), Field.getRequestID());
                publisher.publishInputOrder(Field, RspInfo, true);
            } else {
                log.error("TraderGateway::fireErrRtnOrderInsert return null");
            }
        } else {
            log.error("TraderGateway::fireErrRtnOrderInsert has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
        log.info("TraderGateway::fireErrRtnOrderAction");
        if (nonError(RspInfo)) {
            if (Field != null) {
                log.info("TraderGateway::fireErrRtnOrderAction -> OrderRef==[{}], OrderSysID==[{}], " +
                                "OrderActionRef==[{}], InstrumentID==[{}]",
                        Field.getOrderRef(), Field.getOrderSysID(),
                        Field.getOrderActionRef(), Field.getInstrumentID());
                publisher.publishOrderAction(Field);
            } else {
                log.error("TraderGateway::fireErrRtnOrderAction return null");
            }
        } else {
            log.error("TraderGateway::fireErrRtnOrderAction has error, ErrorID==[{}], ErrorMsg==[{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
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
