package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcMdApi;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.cygnuxltb.adaptor.ctp.CtpConfig;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEventPublisher;
import io.cygnuxltb.adaptor.ctp.gateway.utils.NativeLibraryManager;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.file.FileUtil;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static io.cygnuxltb.adaptor.ctp.gateway.utils.FtdcRspInfoHandler.nonError;
import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

public final class FtdcMdGateway extends FtdcMdCallback implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcMdGateway.class);

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
    private CThostFtdcMdApi api;

    private final String callbackName = FtdcMdGateway.class.getSimpleName();

    // 是否已初始化
    private final AtomicBoolean isInitialize = new AtomicBoolean(false);

    // 是否登陆行情接口
    private final AtomicBoolean isMdLogin = new AtomicBoolean(false);

    // 行情请求ID
    private final AtomicInteger requestId = new AtomicInteger(-1);

    private final String gatewayId;

    private final CtpConfig config;

    private final FtdcEventPublisher eventPublisher;

    public FtdcMdGateway(String gatewayId, CtpConfig config, FtdcEventPublisher eventPublisher) {
        this.gatewayId = gatewayId;
        this.config = config;
        this.eventPublisher = eventPublisher;
    }

    /**
     * 启动并挂起线程
     */
    public final void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcMdApi.version() -> {}", CThostFtdcMdApi.GetApiVersion());
            try {
                startNewMaxPriorityThread("FtdcMd-Thread", this::initAndJoin);
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
        log.info("Gateway -> [{}] use md tempDir: {}", gatewayId, tempDir.getAbsolutePath());
        // 指定md临时文件地址
        String tempFile = new File(tempDir, "md").getAbsolutePath();
        log.info("Gateway -> [{}] use md tempFile : {}", gatewayId, tempFile);
        // 创建CThostFtdcMdApi
        this.api = CThostFtdcMdApi.CreateFtdcMdApi(tempFile);
        log.info("Gateway -> [{}] called native CThostFtdcMdApi::CreateFtdcMdApi", gatewayId);
        // 创建CThostFtdcMdSpi
        CThostFtdcMdSpi mdSpi = new FtdcMdSpi(this);
        log.info("Gateway -> [{}] created CThostFtdcMdSpi with FtdcMdSpiImpl", gatewayId);
        // 将ftdcMdSpi注册到ftdcMdApi
        api.RegisterSpi(mdSpi);
        log.info("Gateway -> [{}] created CThostFtdcMdApi::RegisterSpi", gatewayId);
        // 注册到ftdcMdApi前置机
        api.RegisterFront(config.getMdAddr());
        log.info("Gateway -> [{}] called native function CThostFtdcMdApi::RegisterFront", gatewayId);
        // 初始化ftdcMdApi
        api.Init();
        log.info("Gateway -> [{}] called native function CThostFtdcMdApi::Init", gatewayId);
        // 阻塞当前线程
        log.info("Gateway -> [{}] calling native function CThostFtdcMdApi::Join", gatewayId);
        api.Join();
    }

    /**
     * 行情订阅接口
     *
     * @param instruments String[]
     */
    public final int SubscribeMarketData(@Nonnull String[] instruments) {
        if (isMdLogin.get()) {
            api.SubscribeMarketData(instruments, instruments.length);
            log.info("Send SubscribeMarketData -> count==[{}]", instruments.length);
            return 0;
        } else {
            log.warn("Cannot SubscribeMarketData -> isMdLogin == [false]");
            return -1;
        }
    }

    @Override
    public void close() throws IOException {
        startNewThread("FtdcMdApi-Release", () -> {
            log.info("CThostFtdcMdApi start release");
            if (api != null) api.Release();
            log.info("CThostFtdcMdApi is released");
        });
        sleep(1000);
    }

    /**
     * 行情前置连接回调
     */
    @Override
    public void fireFrontConnected() {
        log.info("{}::fireFrontConnected", callbackName);
        // this.isMdConnect = true;
        CThostFtdcReqUserLoginField field = new CThostFtdcReqUserLoginField();
        field.setBrokerID(config.getBrokerId());
        field.setUserID(config.getUserId());
        field.setClientIPAddress(config.getIpAddr());
        field.setMacAddress(config.getMacAddr());
        int RequestID = requestId.incrementAndGet();
        api.ReqUserLogin(field, RequestID);
        log.info("Send Md ReqUserLogin OK -> nRequestID==[{}]", RequestID);
    }

    /**
     * 行情前置断开回调
     */
    @Override
    public void fireFrontDisconnected(int Reason) {
        log.warn("{}::onFrontDisconnected", callbackName);
        // TODO 行情断开处理逻辑
        isMdLogin.set(false);
        eventPublisher.publishMdUnavailable(Reason);
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {

    }

    /**
     * 行情登录回调
     *
     * @param RspUserLogin CThostFtdcRspUserLoginField
     */
    @Override
    public void fireRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcMdSpi::OnRspUserLogin");
        if (nonError("FtdcMdSpi::OnRspUserLogin", RspInfo)) {
            if (RspUserLogin != null) {
                log.info("{}::onMdRspUserLogin -> FrontID==[{}], SessionID==[{}], TradingDay==[{}]",
                        callbackName, RspUserLogin.getFrontID(), RspUserLogin.getSessionID(), RspUserLogin.getTradingDay());
                isMdLogin.set(true);
                eventPublisher.publishMdAvailable(RspUserLogin, RspInfo, RequestID, IsLast);
            } else
                log.error("FtdcMdSpi::OnRspUserLogin return null");
        }
    }

    @Override
    public void fireRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcMdSpi::OnRspUserLogout");
        if (nonError("FtdcMdSpi::OnRspUserLogout", RspInfo)) {
            if (UserLogout != null) {
                // TODO 处理用户登出
                log.info("Output :: OnRspUserLogout -> BrokerID==[{}], UserID==[{}]", UserLogout.getBrokerID(),
                        UserLogout.getUserID());
            } else
                log.error("FtdcMdSpi::OnRspUserLogin return null");
        }
    }

    @Override
    public void fireRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.error("{}::OnRspError, RequestID==[{}], IsLast==[{}]", callbackName, RequestID, IsLast);
        eventPublisher.publishRspError(RspInfo, RequestID, IsLast);
    }

    /**
     * 订阅行情回调
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void fireRspSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcMdSpi::OnRspSubMarketData");
        if (nonError("FtdcMdSpi::OnRspSubMarketData", RspInfo)) {
            if (SpecificInstrument != null) {
                log.info("{}::onRspSubMarketData -> RequestID==[{}], IsLast==[{}], InstrumentCode==[{}]",
                        callbackName, RequestID, IsLast, SpecificInstrument.getInstrumentID());
                //TODO
            } else
                log.error("FtdcMdSpi::OnRspSubMarketData return null");
        }
    }

    /**
     * 退订行情回调
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void fireRspUnSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("{}::onRspUnSubMarketData -> RequestID==[{}], IsLast==[{}], InstrumentCode==[{}]",
                callbackName, RequestID, IsLast, SpecificInstrument.getInstrumentID());
    }

    /**
     * 行情推送回调
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     */
    @Override
    public void fireRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData) {
        if (DepthMarketData != null) {
            log.debug("{}::onRtnDepthMarketData -> InstrumentID==[{}], LastPrice==[{}], Volume==[{}], Turnover==[{}], UpdateTime==[{}], UpdateMillisec==[{}]",
                    callbackName, DepthMarketData.getInstrumentID(), DepthMarketData.getLastPrice(), DepthMarketData.getVolume(),
                    DepthMarketData.getTurnover(), DepthMarketData.getUpdateTime(), DepthMarketData.getUpdateMillisec());
            eventPublisher.publish(DepthMarketData);
        } else
            log.error("FtdcMdSpi::OnRtnDepthMarketData return null");
    }

}