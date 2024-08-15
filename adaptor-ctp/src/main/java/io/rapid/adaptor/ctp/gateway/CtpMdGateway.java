package io.rapid.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcMdApi;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.annotation.CalledNativeFunction;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.Sleep;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspPublisher;
import io.rapid.adaptor.ctp.gateway.upstream.FtdcMdSpi;
import io.rapid.adaptor.ctp.gateway.upstream.LogFtdcMdListener;
import io.rapid.adaptor.ctp.param.CtpParams;
import io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource;
import lombok.Getter;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static io.mercury.common.datetime.DateTimeUtil.date;
import static io.mercury.common.file.FileUtil.mkdirInTmp;
import static io.mercury.common.lang.Asserter.nonNull;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;
import static io.rapid.adaptor.ctp.serializable.avro.shared.EventSource.MD;

public final class CtpMdGateway extends LogFtdcMdListener implements Closeable {

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
    private CThostFtdcMdApi FtdcMdApi;

    // 是否已初始化
    private final AtomicBoolean isInitialize = new AtomicBoolean(false);

    // 是否登陆行情接口
    private final AtomicBoolean isAvailable = new AtomicBoolean(false);

    // 行情请求ID
    private final AtomicInteger requestIdAllocator = new AtomicInteger(-1);

    @Getter
    private final String gatewayId;

    private final CtpParams params;

    private final FtdcRspPublisher publisher;

    /**
     * 自行初始化时使用构造函数
     *
     * @param params    CtpConfig
     * @param publisher FtdcEventPublisher
     */
    public CtpMdGateway(CtpParams params, FtdcRspPublisher publisher) {
        this.params = nonNull(params, "params");
        this.publisher = nonNull(publisher, "publisher");
        this.gatewayId = "GATEWAY-MD-" + params.getBrokerId() + "-" + params.getInvestorId();
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (isInitialize.compareAndSet(false, true)) {
            log.info("CThostFtdcMdApi.GetApiVersion() -> {}", CThostFtdcMdApi.GetApiVersion());
            try {
                startNewMaxPriorityThread(gatewayId + "-Thread", this::ftdcInitAndJoin);
            } catch (Exception e) {
                log.error("FtdcMdGateway initAndJoin throw Exception -> {}", e.getMessage(), e);
                isInitialize.set(false);
                throw new RuntimeException(e);
            }
        }
    }

    @CalledNativeFunction
    private void ftdcInitAndJoin() {
        // 创建CTP数据文件临时目录
        var tempDir = mkdirInTmp(gatewayId + "-" + date());
        log.info("{} -> use md tempDir: {}", gatewayId, tempDir.getAbsolutePath());
        // 指定md临时文件地址
        var tempFile = new File(tempDir, "md").getAbsolutePath();
        log.info("{} -> use md tempFile : {}", gatewayId, tempFile);
        // 创建CThostFtdcMdApi
        this.FtdcMdApi = CThostFtdcMdApi.CreateFtdcMdApi(tempFile);
        log.info("{} -> call CThostFtdcMdApi::CreateFtdcMdApi", gatewayId);
        // 创建CThostFtdcMdSpi
        CThostFtdcMdSpi Spi = new FtdcMdSpi(this);
        log.info("{} -> created CThostFtdcMdSpi with FtdcMdSpi", gatewayId);
        // 将ftdcMdSpi注册到ftdcMdApi
        FtdcMdApi.RegisterSpi(Spi);
        log.info("{} -> call CThostFtdcMdApi::RegisterSpi", gatewayId);
        // 注册到ftdcMdApi前置机
        FtdcMdApi.RegisterFront(params.getMdAddr());
        log.info("{} -> call native CThostFtdcMdApi::RegisterFront", gatewayId);
        // 初始化ftdcMdApi
        FtdcMdApi.Init();
        log.info("{} -> call native CThostFtdcMdApi::Init", gatewayId);
        // 阻塞当前线程
        log.info("{} -> call native CThostFtdcMdApi::Join", gatewayId);
        FtdcMdApi.Join();
    }

    /**
     * 行情订阅接口
     *
     * @param instruments String[]
     */
    @CalledNativeFunction
    public int ftdcSubscribeMarketData(@Nonnull String[] instruments) {
        if (isAvailable.get()) {
            var result = FtdcMdApi.SubscribeMarketData(instruments, instruments.length);
            log.info("Send CThostFtdcMdApi::SubscribeMarketData OK, subscriptions count==[{}], result==[{}]",
                    instruments.length, result);
            return result;
        } else {
            log.warn("Cannot SubscribeMarketData -> isMdLogin == [false]");
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
        log.info("FtdcMdGateway::fireFrontConnected");
        CThostFtdcReqUserLoginField ReqField = params.getReqUserLoginField();
        int RequestID = requestIdAllocator.incrementAndGet();
        FtdcMdApi.ReqUserLogin(ReqField, RequestID);
        log.info("Sent CThostFtdcMdApi::ReqUserLogin OK -> RequestID==[{}]", RequestID);
    }

    /**
     * 行情前置断开回调
     */
    @Override
    public void fireFrontDisconnected(int Reason) {
        log.warn("FtdcMdGateway::fireFrontDisconnected, Reason==[{}]", Reason);
        isAvailable.set(false);
        publisher.publishFrontDisconnected(MD, Reason, params.getBrokerId(), params.getUserId());
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.warn("FtdcMdGateway::fireHeartBeatWarning, TimeLapse==[{}]", TimeLapse);
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
        log.info("FtdcMdGateway::fireRspUserLogin");
        if (FtdcRspInfoHandler.nonError(RspInfo)) {
            if (Field != null) {
                log.info("FtdcMdGateway::fireRspUserLogin -> FrontID==[{}], SessionID==[{}], TradingDay==[{}]",
                        Field.getFrontID(), Field.getSessionID(), Field.getTradingDay());
                isAvailable.set(true);
                publisher.publishRspUserLogin(MD, Field, RspInfo, RequestID, IsLast);
            } else
                log.error("FtdcMdGateway::fireRspUserLogin return null");
        }
    }

    @Override
    public void fireRspUserLogout(CThostFtdcUserLogoutField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("FtdcMdGateway::fireRspUserLogout");
        if (FtdcRspInfoHandler.nonError(RspInfo)) {
            if (Field != null) {
                // TODO 处理用户登出
                log.info("FtdcMdGateway::fireRspUserLogout -> BrokerID==[{}], UserID==[{}]", Field.getBrokerID(),
                        Field.getUserID());
            } else {
                log.error("FtdcMdGateway::OnRspUserLogin return null");
            }
        }

    }

    @Override
    public void fireRspError(CThostFtdcRspInfoField Field, int RequestID, boolean IsLast) {
        log.info("FtdcMdGateway::fireRspError, ErrorID==[{}], ErrorMsg==[{}], RequestID==[{}], IsLast==[{}]",
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
        log.info("FtdcMdGateway::fireRspSubMarketData");
        if (FtdcRspInfoHandler.nonError(RspInfo)) {
            if (Field != null) {
                log.info("FtdcMdGateway::fireRspSubMarketData -> RequestID==[{}], IsLast==[{}], InstrumentCode==[{}]",
                        RequestID, IsLast, Field.getInstrumentID());
                //TODO
                publisher.publishSpecificInstrument(Field, RspInfo, SpecificInstrumentSource.SubMarketData, RequestID, IsLast);
            } else
                log.error("FtdcMdGateway::fireRspSubMarketData return null");
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
        log.info("FtdcMdGateway::fireRspUnSubMarketData -> InstrumentCode==[{}], RequestID==[{}], IsLast==[{}]",
                Field.getInstrumentID(), RequestID, IsLast);
    }

    /**
     * 行情推送回调
     *
     * @param Field CThostFtdcDepthMarketDataField
     */
    @Override
    public void fireRtnDepthMarketData(CThostFtdcDepthMarketDataField Field) {
        if (Field != null) {
            log.debug("FtdcMdGateway::fireRtnDepthMarketData -> InstrumentID==[{}], LastPrice==[{}], Volume==[{}], Turnover==[{}], UpdateTime==[{}], UpdateMillisec==[{}]",
                    Field.getInstrumentID(), Field.getLastPrice(), Field.getVolume(),
                    Field.getTurnover(), Field.getUpdateTime(), Field.getUpdateMillisec());
            publisher.publishDepthMarketData(Field);
        } else
            log.error("FtdcMdGateway::fireRtnDepthMarketData return null");
    }

    @Override
    public String toString() {
        return gatewayId;
    }

    @Override
    public void close() throws IOException {
        startNewThread("FtdcMdApi-Release", this::ftdcRelease);
        Sleep.millis(1000);
    }

    @CalledNativeFunction
    private void ftdcRelease() {
        log.info("CThostFtdcMdApi start release");
        if (FtdcMdApi != null) FtdcMdApi.Release();
        log.info("CThostFtdcMdApi is released");
    }

}