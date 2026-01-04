package io.cygnux.rapid.gateway.ctp;

import io.cygnux.rapid.gateway.ctp.consts.FtdcBizType;
import io.cygnux.rapid.gateway.ctp.consts.FtdcFrontDisconnectedReason;
import io.cygnux.rapid.gateway.ctp.consts.FtdcInstrumentStatus;
import io.cygnux.rapid.gateway.ctp.event.FtdcRspPublisher;
import io.mercury.common.annotation.CalledNativeFunction;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.Sleep;
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

import static io.cygnux.rapid.gateway.ctp.FtdcFieldValidator.nonError;
import static io.cygnux.rapid.gateway.ctp.FtdcFieldValidator.nonnull;
import static io.cygnux.rapid.gateway.ctp.event.source.EventSource.TD;
import static io.mercury.common.datetime.DateTimeUtil.date;
import static io.mercury.common.file.FileUtil.mkdirInHome;
import static io.mercury.common.lang.Validator.nonNull;
import static io.mercury.common.thread.Fibers.startNewFiber;
import static io.mercury.common.thread.Threads.startNewMaxPriorityThread;
import static io.mercury.common.util.StringSupport.nonEmpty;
import static org.rationalityfrontline.jctp.THOST_TE_RESUME_TYPE.THOST_TERT_RESUME;

public final class FtdcTraderGateway extends FtdcTraderListenerImpl implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcTraderGateway.class);

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

    @Getter
    private final String gatewayId;

    private final FtdcParams params;

    private final FtdcRspPublisher publisher;

    public FtdcTraderGateway(FtdcParams params, FtdcRspPublisher publisher) {
        this.params = nonNull(params, "params");
        this.publisher = nonNull(publisher, "publisher");
        this.gatewayId = "FTDC-T-" + params.getBrokerId() + "-" + params.getInvestorId();
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcTraderApi::GetApiVersion -> {}", CThostFtdcTraderApi.GetApiVersion());
            try {
                startNewMaxPriorityThread(gatewayId + "-Thread", this::nativeCallInitAndJoin);
            } catch (Exception e) {
                log.error("TraderGateway initAndJoin throw Exception -> {}", e.getMessage(), e);
                isInitialize.set(false);
                throw new RuntimeException(e);
            }
        }
    }

    @CalledNativeFunction
    private void nativeCallInitAndJoin() {
        var tempFile = new File(
                // 在[home]目录创建CTP临时数据文件目录, (前缀+BrokerId+InvestorId+启动时日期)
                mkdirInHome("ctp-tmp-" + params.getBrokerId() + "-" + params.getInvestorId() + "-" + date()),
                // 临时文件名
                "trader")
                // 文件绝对路径
                .getAbsolutePath();
        log.info("TraderGateway -> used trader tempFile : {}", tempFile);

        // 创建traderApi
        log.info("TraderGateway -> native CThostFtdcTraderApi::CreateFtdcTraderApi#{}", tempFile);
        this.FtdcTraderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(tempFile);

        // 创建traderSpi
        log.info("TraderGateway -> create native CThostFtdcTraderSpi implement with FtdcTraderSpi");
        CThostFtdcTraderSpi TraderSpi = new FtdcTraderSpi(this);

        // 将Spi注册到CThostFtdcTraderApi
        log.info("TraderGateway -> native CThostFtdcTraderApi::RegisterSpi#FtdcTraderSpi");
        FtdcTraderApi.RegisterSpi(TraderSpi);

        // 注册到trader前置机
        log.info("TraderGateway -> native CThostFtdcTraderApi::RegisterFront#{}", params.getTraderAddr());
        FtdcTraderApi.RegisterFront(params.getTraderAddr());

        /// 重传方式选项
        // THOST_TERT_RESTART:从本交易日开始重传
        // THOST_TERT_RESUME:从上次收到的续传
        // THOST_TERT_QUICK:只传送登录后私有流的内容
        // THOST_TERT_NONE:取消订阅公共流
        // 订阅公有流, 设置为[THOST_TERT_RESUME]
        log.info("TraderGateway -> native CThostFtdcTraderApi::SubscribePublicTopic#THOST_TERT_RESUME");
        FtdcTraderApi.SubscribePublicTopic(THOST_TERT_RESUME);

        // 订阅私有流, 设置为[THOST_TERT_RESUME]
        log.info("TraderGateway -> native CThostFtdcTraderApi::SubscribePrivateTopic#THOST_TERT_RESUME");
        FtdcTraderApi.SubscribePrivateTopic(THOST_TERT_RESUME);

        // 初始化traderApi
        log.info("TraderGateway -> native CThostFtdcTraderApi::Init");
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
    public int nativeReqOrderInsert(CThostFtdcInputOrderField ReqField) {
        if (isAvailable.get()) {
            // 设置账号信息
            int RequestID = RequestIdAllocator.allocate();
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
    public int nativeReqOrderAction(CThostFtdcInputOrderActionField ReqField) {
        if (isAvailable.get()) {
            int RequestID = RequestIdAllocator.allocate();
            ReqField.setFrontID(frontId.get());
            ReqField.setSessionID(sessionId.get());
            FtdcTraderApi.ReqOrderAction(ReqField, RequestID);
            log.info("Send CThostFtdcTraderApi::ReqOrderAction OK -> " +
                            "RequestID==[{}], OrderRef==[{}], OrderActionRef==[{}], " +
                            "BrokerID==[{}], InvestorID==[{}], InstrumentID==[{}]",
                    RequestID, ReqField.getOrderRef(), ReqField.getOrderActionRef(),
                    ReqField.getBrokerID(), ReqField.getInvestorID(), ReqField.getInstrumentID());
            return RequestID;
        } else {
            log.error("CThostFtdcTraderApi::ReqOrderAction is Unavailable");
            return -1;
        }
    }


    /**
     * <pre>
     * ///输入批量报单操作
     * struct CThostFtdcInputBatchOrderActionField
     * {
     * 	///经纪公司代码
     * 	TThostFtdcBrokerIDType	BrokerID;
     * 	///投资者代码
     * 	TThostFtdcInvestorIDType	InvestorID;
     * 	///报单操作引用
     * 	TThostFtdcOrderActionRefType	OrderActionRef;
     * 	///请求编号
     * 	TThostFtdcRequestIDType	RequestID;
     * 	///前置编号
     * 	TThostFtdcFrontIDType	FrontID;
     * 	///会话编号
     * 	TThostFtdcSessionIDType	SessionID;
     * 	///交易所代码
     * 	TThostFtdcExchangeIDType	ExchangeID;
     * 	///用户代码
     * 	TThostFtdcUserIDType	UserID;
     * 	///投资单元代码
     * 	TThostFtdcInvestUnitIDType	InvestUnitID;
     * 	///保留的无效字段
     * 	TThostFtdcOldIPAddressType	reserve1;
     * 	///Mac地址
     * 	TThostFtdcMacAddressType	MacAddress;
     * 	///IP地址
     * 	TThostFtdcIPAddressType	IPAddress;
     * };
     * </pre>
     *
     * @param Field CThostFtdcInputBatchOrderActionField
     * @return int
     */
    public int nativeReqBatchOrderAction(CThostFtdcInputBatchOrderActionField Field) {
        if (isAvailable.get()) {
            int RequestID = RequestIdAllocator.allocate();
            FtdcTraderApi.ReqBatchOrderAction(Field, RequestID);
        }
        return 0;
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
    public int nativeReqQryOrder() {
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
        int RequestID = RequestIdAllocator.allocate();
        FtdcTraderApi.ReqQryOrder(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryOrder OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], " +
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
    public int nativeReqQryTrade() {
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
        int RequestID = RequestIdAllocator.allocate();
        FtdcTraderApi.ReqQryTrade(ReqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryTrade OK -> RequestID==[{}], BrokerID==[{}], InvestorID==[{}], " +
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
    public int nativeReqQryTradingAccount() {
        var ReqField = new CThostFtdcQryTradingAccountField();
        //经纪公司代码
        ReqField.setBrokerID(params.getBrokerId());
        //投资者代码
        ReqField.setInvestorID(params.getInvestorId());
        //币种代码
        ReqField.setCurrencyID(params.getCurrencyId());
        //业务类型
        ReqField.setBizType(FtdcBizType.FUTURE);
        //投资者账号
        ReqField.setAccountID(params.getAccountId());
        int RequestID = RequestIdAllocator.allocate();
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
    public int nativeReqQryInvestorPosition(String ExchangeID, String InstrumentID) {
        var reqField = new CThostFtdcQryInvestorPositionField();
        //经纪公司代码
        reqField.setBrokerID(params.getBrokerId());
        //投资者代码
        reqField.setInvestorID(params.getInvestorId());
        //交易所代码
        reqField.setExchangeID(ExchangeID);
        //合约代码
        reqField.setInstrumentID(InstrumentID);
        //投资单元代码
        // reqField.setInvestUnitID("");
        int RequestID = RequestIdAllocator.allocate();
        FtdcTraderApi.ReqQryInvestorPosition(reqField, RequestID);
        log.info("Send CThostFtdcTraderApi::ReqQryInvestorPosition OK -> " +
                        "RequestID==[{}], BrokerID==[{}], InvestorID==[{}], " +
                        "ExchangeID==[{}], InstrumentID==[{}]",
                RequestID, reqField.getBrokerID(), reqField.getInvestorID(),
                reqField.getExchangeID(), reqField.getInstrumentID());
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
    public int nativeReqQrySettlementInfo() {
        var ReqField = new CThostFtdcQrySettlementInfoField();
        ReqField.setBrokerID(params.getBrokerId());
        ReqField.setInvestorID(params.getInvestorId());
        ReqField.setTradingDay(params.getTradingDay());
        ReqField.setAccountID(params.getAccountId());
        ReqField.setCurrencyID(params.getCurrencyId());
        int RequestID = RequestIdAllocator.allocate();
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
    public int nativeReqQryInstrument(String exchangeCode, String instrumentCode) {
        var ReqField = new CThostFtdcQryInstrumentField();
        ReqField.setExchangeID(exchangeCode);
        ReqField.setInstrumentID(instrumentCode);
        int RequestID = RequestIdAllocator.allocate();
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
            int RequestID = RequestIdAllocator.allocate();
            FtdcTraderApi.ReqAuthenticate(ReqField, RequestID);
            log.info("Sent CThostFtdcTraderApi::ReqAuthenticate OK -> " +
                            "RequestID==[{}], BrokerID==[{}], UserID==[{}], " +
                            "AppID==[{}], AuthCode==[{}]",
                    RequestID, ReqField.getBrokerID(), ReqField.getUserID(),
                    ReqField.getAppID(), ReqField.getAuthCode());
        } else {
            log.error("Cannot sent CThostFtdcTraderApi::ReqAuthenticate, AuthCode==[{}], isAuthenticate==[{}]",
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
        log.warn("TraderGateway::fireFrontDisconnected -> Reason==[{}]",
                FtdcFrontDisconnectedReason.getPrompt(Reason));
        isAvailable.set(false);
        isAuthenticate.set(false);
        // 交易前置断开处理
        publisher.publishFrontDisconnected(TD, Reason);
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.info("TraderGateway::fireHeartBeatWarning OK -> TimeLapse==[{}]", TimeLapse);
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
        if (nonError(RspInfo, "TraderGateway::fireRspAuthenticate", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspAuthenticate", RequestID, IsLast)) {
            log.info("TraderGateway::fireRspAuthenticate OK -> BrokerID==[{}], UserID==[{}]",
                    Field.getBrokerID(), Field.getUserID());
            isAuthenticate.set(true);
            var ReqField = params.getReqUserLoginField();
            int ReqRequestID = RequestIdAllocator.allocate();
            FtdcTraderApi.ReqUserLogin(ReqField, ReqRequestID);
            log.info("Sent CThostFtdcTraderApi::ReqUserLogin OK -> RequestID==[{}]", ReqRequestID);
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
    @CalledNativeFunction
    public void fireRspUserLogin(CThostFtdcRspUserLoginField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "TraderGateway::fireRspUserLogin", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspUserLogin", RequestID, IsLast)) {
            log.info("TraderGateway::fireRspUserLogin -> BrokerID==[{}], UserID==[{}], FrontID==[{}], " +
                            "SessionID==[{}], TradingDay==[{}], LoginTime==[{}], MaxOrderRef==[{}]",
                    Field.getBrokerID(), Field.getUserID(), Field.getFrontID(),
                    Field.getSessionID(), Field.getTradingDay(), Field.getLoginTime(), Field.getMaxOrderRef());
            this.frontId.set(Field.getFrontID());
            this.sessionId.set(Field.getSessionID());
            this.isAvailable.set(true);
            log.info(" CThostFtdcTraderApi::GetTradingDay == [{}]", FtdcTraderApi.GetTradingDay());
            publisher.publishRspUserLogin(TD, Field, RspInfo, RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspUserLogout", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspUserLogout", RequestID, IsLast))
            log.info("TraderGateway::fireRspUserLogout OK -> BrokerID==[{}], UserID==[{}]",
                    Field.getBrokerID(), Field.getUserID());
        publisher.publishUserLogout(TD, Field, RspInfo, RequestID, IsLast);
    }


    /**
     * ///报单通知
     *
     * @param Field CThostFtdcOrderField
     */
    @Override
    public void fireRtnOrder(CThostFtdcOrderField Field) {
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
     * ///报单录入请求响应 - [报单错误回调:1]
     *
     * @param Field     CThostFtdcInputOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOrderInsert(CThostFtdcInputOrderField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "TraderGateway::fireRspOrderInsert", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspOrderInsert", RequestID, IsLast)) {
            log.info("TraderGateway::fireRspOrderInsert OK -> OrderRef==[{}]", Field.getOrderRef());
            publisher.publishInputOrder(Field, RspInfo, IsLast);
        }
    }

    /**
     * ///报单录入错误回报 - [报单错误回调:2]
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderInsert(CThostFtdcInputOrderField Field, CThostFtdcRspInfoField RspInfo) {
        if (nonError(RspInfo, "TraderGateway::fireErrRtnOrderInsert")
                && nonnull(Field, "TraderGateway::fireErrRtnOrderInsert")) {
            log.warn("TraderGateway::fireErrRtnOrderInsert OK -> OrderRef==[{}], RequestID==[{}]",
                    Field.getOrderRef(), Field.getRequestID());
            publisher.publishInputOrder(Field, RspInfo, true);
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
        if (nonError(RspInfo, "TraderGateway::fireRspOrderAction", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspOrderAction", RequestID, IsLast)) {
            log.info("TraderGateway::fireRspOrderAction OK -> OrderRef==[{}], OrderSysID==[{}], " +
                            "OrderActionRef==[{}], InstrumentID==[{}]",
                    Field.getOrderRef(), Field.getOrderSysID(),
                    Field.getOrderActionRef(), Field.getInstrumentID());
            publisher.publishInputOrderAction(Field, RspInfo, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireErrRtnOrderAction")
                && nonnull(Field, "TraderGateway::fireErrRtnOrderAction")) {
            log.warn("TraderGateway::fireErrRtnOrderAction OK -> OrderRef==[{}], OrderSysID==[{}], " +
                            "OrderActionRef==[{}], InstrumentID==[{}]",
                    Field.getOrderRef(), Field.getOrderSysID(),
                    Field.getOrderActionRef(), Field.getInstrumentID());
            publisher.publishOrderAction(Field);
        }
    }


    /**
     * ///批量报单操作请求响应 - [批量撤单错误回调: 1]
     *
     * @param Field     CThostFtdcInputBatchOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspBatchOrderAction(CThostFtdcInputBatchOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "TraderGateway::fireRspBatchOrderAction")
                && nonnull(Field, "TraderGateway::fireRspBatchOrderAction")) {
            log.warn("TraderGateway::fireRspBatchOrderAction OK -> UserID==[{}], OrderActionRef==[{}]",
                    Field.getUserID(), Field.getOrderActionRef());
        }
    }


    /**
     * ///批量报单操作错误回报 - [批量撤单错误回调: 2]
     *
     * @param Field   CThostFtdcBatchOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField Field,
                                           CThostFtdcRspInfoField RspInfo) {
        if (nonError(RspInfo, "TraderGateway::fireErrRtnBatchOrderAction")
                && nonnull(Field, "TraderGateway::fireErrRtnBatchOrderAction")) {
            log.warn("TraderGateway::fireErrRtnBatchOrderAction OK -> UserID==[{}], OrderActionRef==[{}]",
                    Field.getUserID(), Field.getOrderActionRef());
        }
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
                        "ErrorID==[{}], ErrorMsg==[{}], RequestID==[{}], IsLast==[{}]",
                Field.getErrorID(), Field.getErrorMsg(), RequestID, IsLast);
        publisher.publishRspError(TD, Field, RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryOrder", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspQryOrder", RequestID, IsLast)) {
            log.info("TraderGateway::fireRspQryOrder OK -> AccountID==[{}], OrderRef==[{}], IsLast==[{}]",
                    Field.getAccountID(), Field.getOrderRef(), IsLast);
            publisher.publishOrder(Field);
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
        log.info("TraderGateway::fireRspQryTrade OK -> RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryInvestorPosition", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspQryInvestorPosition", RequestID, IsLast)) {
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryTradingAccount")
                && nonnull(Field, "TraderGateway::fireRspQryTradingAccount")) {
            log.info("TraderGateway::fireRspQryTradingAccount OK -> " +
                            "AccountID==[{}], Balance==[{}], Available==[{}], " +
                            "Credit==[{}], WithdrawQuota==[{}], isLast==[{}]",
                    Field.getAccountID(), Field.getBalance(), Field.getAvailable(),
                    Field.getCredit(), Field.getWithdrawQuota(), IsLast);
            publisher.publishTradingAccount(Field, RspInfo, RequestID, IsLast);
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
        if (nonError(RspInfo, "TraderGateway::fireRspQryInstrument", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspQryInstrument", RequestID, IsLast)) {
            log.info("TraderGateway::fireRspQryInstrument OK -> " +
                            "ExchangeID==[{}], InstrumentID==[{}]",
                    Field.getExchangeID(), Field.getInstrumentID());
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
        if (nonError(RspInfo, "TraderGateway::fireRspQrySettlementInfo", RequestID, IsLast)
                && nonnull(Field, "TraderGateway::fireRspQrySettlementInfo", RequestID, IsLast)) {
            log.info("""
                            TraderGateway::fireRspQrySettlementInfo OK ->
                            BrokerID==[{}], AccountID==[{}], InvestorID==[{}],
                            SettlementID==[{}], TradingDay==[{}], SequenceNo==[{}] CurrencyID==[{}]
                            <<<<<<<<<<<<<<<< CONTENT TEXT START >>>>>>>>>>>>>>>>
                            {}
                            <<<<<<<<<<<<<<<<  CONTENT TEXT END  >>>>>>>>>>>>>>>>
                            """,
                    Field.getBrokerID(), Field.getAccountID(), Field.getInvestorID(),
                    Field.getSettlementID(), Field.getTradingDay(), Field.getSequenceNo(),
                    Field.getCurrencyID(), Field.getContent());
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
     * ///合约交易状态通知
     *
     * @param Field CThostFtdcInstrumentStatusField
     */
    @Override
    public void fireRtnInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        log.info("TraderGateway::fireRtnInstrumentStatus >>> InstrumentID==[{}], InstrumentStatus==[{}]",
                Field.getInstrumentID(), FtdcInstrumentStatus.getPrompt(Field.getInstrumentStatus()));
        publisher.publishInstrumentStatus(Field);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return gatewayId;
    }

    /**
     * 关闭函数, 资源清理
     */
    @Override
    public void close() throws IOException {
        startNewFiber("FtdcTraderApi-Release", this::FtdcTraderApiRelease);
    }

    @CalledNativeFunction
    private void FtdcTraderApiRelease() {
        log.info("CThostFtdcTraderApi start release");
        if (FtdcTraderApi != null) {
            FtdcTraderApi.Release();
            Sleep.millis(1200);
        }
        log.info("CThostFtdcTraderApi is released");
    }

}
