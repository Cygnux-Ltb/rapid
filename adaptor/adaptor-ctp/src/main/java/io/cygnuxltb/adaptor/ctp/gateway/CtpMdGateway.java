package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcMdApi;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import io.cygnuxltb.adaptor.ctp.CtpConfig;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEventPublisher;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.file.FileUtil;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

public class CtpMdGateway// implements Closeable
{
//
//    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpMdGateway.class);
//
//    // gatewayId
//    private final String gatewayId;
//
//    // 基础配置信息
//    private final CtpConfig config;
//
//    @Native
//    private CThostFtdcMdApi mdApi;
//
//    // 是否已初始化
//    private final AtomicBoolean isInitialize = new AtomicBoolean(false);
//
//    // 是否登陆行情接口
//    private final AtomicBoolean isMdLogin = new AtomicBoolean(false);
//
//    // 行情请求ID
//    private final AtomicInteger requestId = new AtomicInteger(-1);
//
//    // FtdcEvent 事件发布器
//    private final FtdcEventPublisher publisher;
//
//    /**
//     * @param gatewayId String
//     * @param config    CtpConfig
//     * @param publisher FtdcEventPublisher
//     */
//    CtpMdGateway(@Nonnull String gatewayId, @Nonnull CtpConfig config,
//                 @Nonnull FtdcEventPublisher publisher) {
//        Asserter.nonEmpty(gatewayId, "gatewayId");
//        Asserter.nonNull(config, "config");
//        Asserter.nonNull(publisher, "publisher");
//        this.gatewayId = gatewayId;
//        this.config = config;
//        this.publisher = publisher;
//    }
//
//    /**
//     * 启动并挂起线程
//     */
//    final void startup() {
//        if (isInitialize.compareAndSet(false, true)) {
//            log.info("CThostFtdcMdApi.version() -> {}", CThostFtdcMdApi.GetApiVersion());
//            try {
//                startNewMaxPriorityThread("FtdcMd-Thread", this::mdApiInitAndJoin);
//            } catch (Exception e) {
//                log.error("Method initAndJoin throw Exception -> {}", e.getMessage(), e);
//                isInitialize.set(false);
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    private void mdApiInitAndJoin() {
//        // 创建CTP数据文件临时目录
//        File tempDir = FileUtil.mkdirInTmp(gatewayId + "-" + DateTimeUtil.date());
//        log.info("Gateway -> [{}] use md tempDir: {}", gatewayId, tempDir.getAbsolutePath());
//        // 指定md临时文件地址
//        String tempFile = new File(tempDir, "md").getAbsolutePath();
//        log.info("Gateway -> [{}] use md tempFile : {}", gatewayId, tempFile);
//        // 创建CThostFtdcMdApi
//        this.mdApi = CThostFtdcMdApi.CreateFtdcMdApi(tempFile);
//        log.info("Gateway -> [{}] called native CThostFtdcMdApi::CreateFtdcMdApi", gatewayId);
//        // 创建CThostFtdcMdSpi
//        CThostFtdcMdSpi ftdcMdSpi = new FtdcMdSpiImpl(new FtdcMdCallback(publisher));
//        log.info("Gateway -> [{}] created CThostFtdcMdSpi with FtdcMdSpiImpl", gatewayId);
//        // 将ftdcMdSpi注册到ftdcMdApi
//        mdApi.RegisterSpi(ftdcMdSpi);
//        log.info("Gateway -> [{}] created CThostFtdcMdApi::RegisterSpi", gatewayId);
//        // 注册到ftdcMdApi前置机
//        mdApi.RegisterFront(config.getMdAddr());
//        log.info("Gateway -> [{}] called native function CThostFtdcMdApi::RegisterFront", gatewayId);
//        // 初始化ftdcMdApi
//        mdApi.Init();
//        log.info("Gateway -> [{}] called native function CThostFtdcMdApi::Init", gatewayId);
//        // 阻塞当前线程
//        log.info("Gateway -> [{}] calling native function CThostFtdcMdApi::Join", gatewayId);
//        mdApi.Join();
//    }
//
//    /**
//     * 行情订阅接口
//     *
//     * @param instruments String[]
//     */
//    public final int SubscribeMarketData(@Nonnull String[] instruments) {
//        if (isMdLogin.get()) {
//            mdApi.SubscribeMarketData(instruments, instruments.length);
//            log.info("Send SubscribeMarketData -> count==[{}]", instruments.length);
//            return 0;
//        } else {
//            log.warn("Cannot SubscribeMarketData -> isMdLogin == [false]");
//            return -1;
//        }
//    }
//
//    @Override
//    public void close() throws IOException {
//        startNewThread("FtdcMdApi-Release", () -> {
//            log.info("CThostFtdcMdApi start release");
//            if (mdApi != null) mdApi.Release();
//            log.info("CThostFtdcMdApi is released");
//        });
//        sleep(1000);
//    }
//
//    /**
//     * FTDC行情回调
//     *
//     * @author yellow013
//     */
//    class FtdcMdCallback extends AbstractFtdcCallback {
//
//        private FtdcMdCallback(FtdcEventPublisher publisher) {
//            super(publisher);
//        }
//
//        /**
//         * 行情前置断开回调
//         */
//        void onFrontDisconnected() {
//            log.warn("{}::onFrontDisconnected", callbackName);
//            // 行情断开处理逻辑
//            isMdLogin.set(false);
//
//            publisher.publish(false);
//        }
//
//        /**
//         * 行情前置连接回调
//         */
//        void onFrontConnected() {
//            log.info("{}::onFrontConnected", callbackName);
//            // this.isMdConnect = true;
//            CThostFtdcReqUserLoginField field = new CThostFtdcReqUserLoginField();
//            field.setBrokerID(config.getBrokerId());
//            field.setUserID(config.getUserId());
//            field.setClientIPAddress(config.getIpAddr());
//            field.setMacAddress(config.getMacAddr());
//            int RequestID = requestId.incrementAndGet();
//            mdApi.ReqUserLogin(field, RequestID);
//            log.info("Send Md ReqUserLogin OK -> nRequestID==[{}]", RequestID);
//        }
//
//        /**
//         * 行情登录回调
//         *
//         * @param field CThostFtdcRspUserLoginField
//         */
//        void onRspUserLogin(CThostFtdcRspUserLoginField field) {
//            log.info("{}::onMdRspUserLogin -> FrontID==[{}], SessionID==[{}], TradingDay==[{}]",
//                    callbackName, field.getFrontID(), field.getSessionID(), field.getTradingDay());
//            isMdLogin.set(true);
//            publisher.publish(true);
//        }
//
//        /**
//         * 订阅行情回调
//         *
//         * @param field CThostFtdcSpecificInstrumentField
//         */
//        void onRspSubMarketData(CThostFtdcSpecificInstrumentField field) {
//            log.info("{}::onRspSubMarketData -> InstrumentCode==[{}]",
//                    callbackName, field.getInstrumentID());
//        }
//
//        /**
//         * 行情推送回调
//         *
//         * @param field CThostFtdcDepthMarketDataField
//         */
//        void onRtnDepthMarketData(CThostFtdcDepthMarketDataField field) {
//            log.debug("{}::onRtnDepthMarketData -> InstrumentID == [{}], UpdateTime==[{}], UpdateMillisec==[{}]",
//                    callbackName, field.getInstrumentID(), field.getUpdateTime(), field.getUpdateMillisec());
//            publisher.publish(field);
//        }
//    }

}