package io.rapid.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcMdApi;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.file.FileUtil;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.component.CtpConfig;
import io.rapid.adaptor.ctp.gateway.event.FtdcEventPublisher;
import io.rapid.adaptor.ctp.gateway.event.listener.BaseFtdcMdListener;
import io.rapid.adaptor.ctp.gateway.spi.FtdcMdSpi;
import io.rapid.adaptor.ctp.gateway.util.FtdcRspInfoHandler;
import io.rapid.adaptor.ctp.gateway.util.NativeLibraryManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static io.mercury.common.datetime.DateTimeUtil.date;
import static io.mercury.common.lang.Asserter.nonNull;
import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

@Component
public final class CtpMdGateway extends BaseFtdcMdListener implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpMdGateway.class);

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
    private CThostFtdcMdApi NativeApi;

    // 是否已初始化
    private final AtomicBoolean isInitialize = new AtomicBoolean(false);

    // 是否登陆行情接口
    private final AtomicBoolean isMdLogin = new AtomicBoolean(false);

    // 行情请求ID
    private final AtomicInteger requestIdGetter = new AtomicInteger(-1);

    @Getter
    private String gatewayId;

    @Resource
    private final CtpConfig config;

    @Resource
    private final FtdcEventPublisher publisher;

    /**
     * 自行初始化时使用构造函数
     *
     * @param config    CtpConfig
     * @param publisher FtdcEventPublisher
     */
    public CtpMdGateway(CtpConfig config, FtdcEventPublisher publisher) {
        this.config = nonNull(config, "config");
        this.publisher = nonNull(publisher, "publisher");
        setGatewayId();
    }

    private void setGatewayId() {
        if (config == null || publisher == null)
            throw new IllegalStateException("[CtpConfig] or [FtdcEventPublisher] has not been injected");
        this.gatewayId = "GATEWAY-MD-" + config.getBrokerId() + "-" + config.getInvestorId();
    }

    /**
     * 启动并挂起线程
     */
    @PostConstruct
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcMdApi.GetApiVersion() -> {}", CThostFtdcMdApi.GetApiVersion());
            try {
                startNewMaxPriorityThread(gatewayId + "-Thread", this::nativeInitAndJoin);
            } catch (Exception e) {
                log.error("FtdcMdGateway initAndJoin throw Exception -> {}", e.getMessage(), e);
                isInitialize.set(false);
                throw new RuntimeException(e);
            }
        }
    }

    private void nativeInitAndJoin() {
        // 创建CTP数据文件临时目录
        var tempDir = FileUtil.mkdirInTmp(gatewayId + "-" + date());
        log.info("{} -> use md tempDir: {}", gatewayId, tempDir.getAbsolutePath());
        // 指定md临时文件地址
        var tempFile = new File(tempDir, "md").getAbsolutePath();
        log.info("{} -> use md tempFile : {}", gatewayId, tempFile);
        // 创建CThostFtdcMdApi
        this.NativeApi = CThostFtdcMdApi.CreateFtdcMdApi(tempFile);
        log.info("{} -> call CThostFtdcMdApi::CreateFtdcMdApi", gatewayId);
        // 创建CThostFtdcMdSpi
        CThostFtdcMdSpi Spi = new FtdcMdSpi(this);
        log.info("{} -> created CThostFtdcMdSpi with FtdcMdSpi", gatewayId);
        // 将ftdcMdSpi注册到ftdcMdApi
        NativeApi.RegisterSpi(Spi);
        log.info("{} -> call CThostFtdcMdApi::RegisterSpi", gatewayId);
        // 注册到ftdcMdApi前置机
        NativeApi.RegisterFront(config.getMdAddr());
        log.info("{} -> call native CThostFtdcMdApi::RegisterFront", gatewayId);
        // 初始化ftdcMdApi
        NativeApi.Init();
        log.info("{} -> call native CThostFtdcMdApi::Init", gatewayId);
        // 阻塞当前线程
        log.info("{} -> call native CThostFtdcMdApi::Join", gatewayId);
        NativeApi.Join();
    }

    /**
     * 行情订阅接口
     *
     * @param instruments String[]
     */
    public int nativeSubscribeMarketData(@Nonnull String[] instruments) {
        if (isMdLogin.get()) {
            NativeApi.SubscribeMarketData(instruments, instruments.length);
            log.info("Send CThostFtdcMdApi::SubscribeMarketData OK, count==[{}]", instruments.length);
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
            if (NativeApi != null) NativeApi.Release();
            log.info("CThostFtdcMdApi is released");
        });
        sleep(1000);
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
        log.info("FtdcMdGateway::fireFrontConnected");
        CThostFtdcReqUserLoginField ReqField = config.getReqUserLoginField();
        int newRequestID = requestIdGetter.incrementAndGet();
        NativeApi.ReqUserLogin(ReqField, newRequestID);
        log.info("Send CThostFtdcMdApi::ReqUserLogin OK ->  nRequestID==[{}]", newRequestID);
    }

    /**
     * 行情前置断开回调
     */
    @Override
    public void fireFrontDisconnected(int Reason) {
        log.warn("FtdcMdGateway::fireFrontDisconnected");
        // TODO 行情断开处理逻辑
        isMdLogin.set(false);
        publisher.publishMdUnavailable(Reason);
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.info("FtdcMdGateway::fireHeartBeatWarning, TimeLapse==[{}]", TimeLapse);
    }

    /**
     * 行情登录回调
     *
     * @param RspUserLogin CThostFtdcRspUserLoginField
     */
    @Override
    public void fireRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcMdGateway::fireRspUserLogin");
        if (FtdcRspInfoHandler.nonError(RspInfo)) {
            if (RspUserLogin != null) {
                log.info("FtdcMdGateway::fireRspUserLogin -> FrontID==[{}], SessionID==[{}], TradingDay==[{}]",
                        RspUserLogin.getFrontID(), RspUserLogin.getSessionID(), RspUserLogin.getTradingDay());
                isMdLogin.set(true);
                publisher.publishMdAvailable(RspUserLogin, RspInfo, RequestID, IsLast);
            } else
                log.error("FtdcMdGateway::fireRspUserLogin return null");
        }
    }

    @Override
    public void fireRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcMdGateway::fireRspUserLogout");
        if (FtdcRspInfoHandler.nonError(RspInfo)) {
            if (UserLogout != null) {
                // TODO 处理用户登出
                log.info("FtdcMdGateway::fireRspUserLogout -> BrokerID==[{}], UserID==[{}]", UserLogout.getBrokerID(),
                        UserLogout.getUserID());
            } else {
                log.error("FtdcMdGateway::OnRspUserLogin return null");
            }
        }

    }

    @Override
    public void fireRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcMdGateway::fireRspError, ErrorID==[{}], ErrorMsg==[{}], RequestID==[{}], IsLast==[{}]",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
        publisher.publish(RspInfo, RequestID, IsLast);
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
        log.info("FtdcMdGateway::fireRspSubMarketData");
        if (FtdcRspInfoHandler.nonError(RspInfo)) {
            if (SpecificInstrument != null) {
                log.info("FtdcMdGateway::fireRspSubMarketData -> RequestID==[{}], IsLast==[{}], InstrumentCode==[{}]",
                        RequestID, IsLast, SpecificInstrument.getInstrumentID());
                //TODO
            } else
                log.error("FtdcMdGateway::fireRspSubMarketData return null");
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
        log.info("FtdcMdGateway::fireRspUnSubMarketData -> InstrumentCode==[{}], RequestID==[{}], IsLast==[{}]",
                SpecificInstrument.getInstrumentID(), RequestID, IsLast);
    }

    /**
     * 行情推送回调
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     */
    @Override
    public void fireRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData) {
        if (DepthMarketData != null) {
            log.debug("FtdcMdGateway::fireRtnDepthMarketData -> InstrumentID==[{}], LastPrice==[{}], Volume==[{}], Turnover==[{}], UpdateTime==[{}], UpdateMillisec==[{}]",
                    DepthMarketData.getInstrumentID(), DepthMarketData.getLastPrice(), DepthMarketData.getVolume(),
                    DepthMarketData.getTurnover(), DepthMarketData.getUpdateTime(), DepthMarketData.getUpdateMillisec());
            publisher.publish(DepthMarketData);
        } else
            log.error("FtdcMdGateway::fireRtnDepthMarketData return null");
    }

    @Override
    public String toString() {
        return gatewayId;
    }
}