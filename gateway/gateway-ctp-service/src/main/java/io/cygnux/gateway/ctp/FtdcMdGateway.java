package io.cygnux.gateway.ctp;


import io.mercury.common.annotation.CalledNativeFunction;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.Sleep;
import io.mercury.common.util.StringSupport;
import io.cygnux.gateway.ctp.consts.FtdcFrontDisconnectedReason;
import io.cygnux.gateway.ctp.event.FtdcRspPublisher;
import io.cygnux.gateway.ctp.param.FtdcParams;
import lombok.Getter;
import org.rationalityfrontline.jctp.CThostFtdcDepthMarketDataField;
import org.rationalityfrontline.jctp.CThostFtdcMdApi;
import org.rationalityfrontline.jctp.CThostFtdcMdSpi;
import org.rationalityfrontline.jctp.CThostFtdcReqUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcSpecificInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcUserLogoutField;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.datetime.DateTimeUtil.date;
import static io.mercury.common.file.FileUtil.mkdirInHome;
import static io.mercury.common.lang.Asserter.nonNull;
import static io.mercury.common.thread.Fibers.startNewFiber;
import static io.mercury.common.thread.Threads.startNewMaxPriorityThread;
import static io.cygnux.gateway.ctp.event.source.EventSource.MD;
import static io.cygnux.gateway.ctp.event.source.SpecificInstrumentSource.SUB_MARKET_DATA;
import static io.cygnux.gateway.ctp.event.source.SpecificInstrumentSource.UNSUB_MARKET_DATA;
import static io.cygnux.gateway.ctp.FtdcFieldValidator.nonError;
import static io.cygnux.gateway.ctp.FtdcFieldValidator.nonnull;

public final class FtdcMdGateway extends FtdcMdListenerImpl implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcMdGateway.class);

    @Native
    private CThostFtdcMdApi FtdcMdApi;

    // 是否已初始化
    private final AtomicBoolean isInitialize = new AtomicBoolean(false);

    // 是否登陆行情接口
    private final AtomicBoolean isAvailable = new AtomicBoolean(false);

    @Getter
    private final String gatewayId;

    private final FtdcParams params;

    private final FtdcRspPublisher publisher;

    /**
     * 自行初始化时使用构造函数
     *
     * @param params    CtpConfig
     * @param publisher FtdcEventPublisher
     */
    public FtdcMdGateway(FtdcParams params, FtdcRspPublisher publisher) {
        this.params = nonNull(params, "params");
        this.publisher = nonNull(publisher, "publisher");
        this.gatewayId = "FTDC-M-" + params.getBrokerId() + "-" + params.getInvestorId();
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcMdApi::GetApiVersion -> {}", CThostFtdcMdApi.GetApiVersion());
            try {
                startNewMaxPriorityThread(gatewayId + "-Thread", this::nativeCallInitAndJoin);
            } catch (Exception e) {
                log.error("MdGateway initAndJoin throw Exception -> {}", e.getMessage(), e);
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
                "md")
                // 文件绝对路径
                .getAbsolutePath();
        log.info("MdGateway -> used md tempFile : {}", tempFile);

        // 创建CThostFtdcMdApi
        log.info("MdGateway -> native CThostFtdcMdApi::CreateFtdcMdApi#{}", tempFile);
        this.FtdcMdApi = CThostFtdcMdApi.CreateFtdcMdApi(tempFile);

        // 创建CThostFtdcMdSpi
        log.info("MdGateway -> create native CThostFtdcMdSpi implement with FtdcMdSpi");
        CThostFtdcMdSpi MdSpi = new FtdcMdSpi(this);

        // 将ftdcMdSpi注册到ftdcMdApi
        log.info("MdGateway -> native CThostFtdcMdApi::RegisterSpi#FtdcMdSpi");
        FtdcMdApi.RegisterSpi(MdSpi);

        // 注册到ftdcMdApi前置机
        log.info("MdGateway -> native FtdcMdApi::RegisterFront#{}", params.getMdAddr());
        FtdcMdApi.RegisterFront(params.getMdAddr());

        // 初始化ftdcMdApi
        log.info("MdGateway -> native CThostFtdcMdApi::Init");
        FtdcMdApi.Init();

        // 阻塞当前线程
        log.info("MdGateway -> native CThostFtdcMdApi::Join");
        FtdcMdApi.Join();
    }

    /**
     * 行情订阅接口
     *
     * @param Instruments String[]
     */
    @CalledNativeFunction
    public int nativeSubscribeMarketData(@Nonnull String... Instruments) {
        if (isAvailable.get()) {
            var result = FtdcMdApi.SubscribeMarketData(Instruments);
            log.info("Send CThostFtdcMdApi::SubscribeMarketData OK -> subscriptions {}, result==[{}]",
                    StringSupport.toString(Instruments), result);
            return result;
        } else {
            log.error("CThostFtdcMdApi::SubscribeMarketData is Unavailable");
            return -1;
        }
    }

//*******************************************************************************************************//
//*******************************************************************************************************//
//******************************************** EVENT TRIGGER ********************************************//
//*******************************************************************************************************//
//*******************************************************************************************************//

    /**
     * 行情前置连接回调
     */
    @Override
    public void fireFrontConnected() {
        log.info("MdGateway::fireFrontConnected");
        CThostFtdcReqUserLoginField ReqField = params.getReqUserLoginField();
        int RequestID = RequestIdAllocator.allocate();
        FtdcMdApi.ReqUserLogin(ReqField, RequestID);
        log.info("Sent CThostFtdcMdApi::ReqUserLogin OK -> RequestID==[{}]", RequestID);
    }

    /**
     * 行情前置断开回调
     */
    @Override
    public void fireFrontDisconnected(int Reason) {
        log.warn("MdGateway::fireFrontDisconnected, Reason==[{}]",
                FtdcFrontDisconnectedReason.getPrompt(Reason));
        isAvailable.set(false);
        publisher.publishFrontDisconnected(MD, Reason);
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.info("MdGateway::fireHeartBeatWarning, TimeLapse==[{}]", TimeLapse);
        publisher.publishHeartBeatWarning(MD, TimeLapse, params.getBrokerId(), params.getUserId());
    }

    /**
     * 行情登录回调
     *
     * @param Field CThostFtdcRspUserLoginField
     */
    @Override
    public void fireRspUserLogin(CThostFtdcRspUserLoginField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "MdGateway::fireRspUserLogin", RequestID, IsLast)
                && nonnull(Field, "MdGateway::fireRspUserLogin", RequestID, IsLast)) {
            log.info("MdGateway::fireRspUserLogin -> BrokerID==[{}], UserID==[{}], FrontID==[{}], " +
                            "SessionID==[{}], TradingDay==[{}], LoginTime==[{}], MaxOrderRef==[{}]",
                    Field.getBrokerID(), Field.getUserID(), Field.getFrontID(),
                    Field.getSessionID(), Field.getTradingDay(), Field.getLoginTime(), Field.getMaxOrderRef());
            isAvailable.set(true);
            log.info("CThostFtdcMdApi::GetTradingDay == [{}]", FtdcMdApi.GetTradingDay());
            publisher.publishRspUserLogin(MD, Field, RspInfo, RequestID, IsLast);
        }
    }

    @Override
    public void fireRspUserLogout(CThostFtdcUserLogoutField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "MdGateway::fireRspUserLogout", RequestID, IsLast)
                && nonnull(Field, "MdGateway::fireRspUserLogout", RequestID, IsLast)) {
            log.info("MdGateway::fireRspUserLogout -> BrokerID==[{}], UserID==[{}]",
                    Field.getBrokerID(), Field.getUserID());
            publisher.publishUserLogout(MD, Field, RspInfo, RequestID, IsLast);
        }
    }

    @Override
    public void fireRspError(CThostFtdcRspInfoField Field, int RequestID, boolean IsLast) {
        log.warn("NOTE >>>> MdGateway::fireRspError OK -> " +
                        "ErrorID==[{}], ErrorMsg==[{}], RequestID==[{}], IsLast==[{}]",
                Field.getErrorID(), Field.getErrorMsg(), RequestID, IsLast);
        publisher.publishRspError(MD, Field, RequestID, IsLast);
    }

    /**
     * 订阅行情回调
     *
     * @param Field     CThostFtdcSpecificInstrumentField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspSubMarketData(CThostFtdcSpecificInstrumentField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "MdGateway::fireRspSubMarketData", RequestID, IsLast)
                && nonnull(Field, "MdGateway::fireRspSubMarketData", RequestID, IsLast)) {
            log.info("MdGateway::fireRspSubMarketData -> RequestID==[{}], IsLast==[{}], InstrumentCode==[{}]",
                    RequestID, IsLast, Field.getInstrumentID());
            publisher.publishSpecificInstrument(SUB_MARKET_DATA, Field, RspInfo, RequestID, IsLast);
        }
    }

    /**
     * 退订行情回调
     *
     * @param Field     CThostFtdcSpecificInstrumentField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspUnSubMarketData(CThostFtdcSpecificInstrumentField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        if (nonError(RspInfo, "MdGateway::fireRspUnSubMarketData", RequestID, IsLast)
                && nonnull(Field, "MdGateway::fireRspUnSubMarketData", RequestID, IsLast)) {
            log.info("MdGateway::fireRspUnSubMarketData -> RequestID==[{}], IsLast==[{}], InstrumentCode==[{}]",
                    RequestID, IsLast, Field.getInstrumentID());
            publisher.publishSpecificInstrument(UNSUB_MARKET_DATA, Field, RspInfo, RequestID, IsLast);
        }
    }

    /**
     * 行情推送回调
     *
     * @param Field CThostFtdcDepthMarketDataField
     */
    @Override
    public void fireRtnDepthMarketData(CThostFtdcDepthMarketDataField Field) {
        if (nonnull(Field, "MdGateway::fireRtnDepthMarketData")) {
            log.debug("MdGateway::fireRtnDepthMarketData -> " +
                            "InstrumentID==[{}], LastPrice==[{}], Volume==[{}], Turnover==[{}], UpdateTime==[{}], UpdateMillisec==[{}]",
                    Field.getInstrumentID(), Field.getLastPrice(), Field.getVolume(), Field.getTurnover(),
                    Field.getUpdateTime(), Field.getUpdateMillisec());
            publisher.publishDepthMarketData(Field);
        }
    }

    @Override
    public String toString() {
        return gatewayId;
    }

    @Override
    public void close() throws IOException {
        startNewFiber("FtdcMdApi-Release", this::FtdcMdApiRelease);
    }

    @CalledNativeFunction
    private void FtdcMdApiRelease() {
        log.info("CThostFtdcMdApi start release");
        if (FtdcMdApi != null) {
            FtdcMdApi.Release();
            Sleep.millis(1200);
        }
        log.info("CThostFtdcMdApi is released");
    }

}