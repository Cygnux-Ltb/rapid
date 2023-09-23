package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.cygnuxltb.adaptor.ctp.CtpConfig;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEventPublisher;
import io.cygnuxltb.jcts.core.adaptor.ConnectionMode;
import io.mercury.common.lang.Asserter;
import io.mercury.common.lang.exception.NativeLibraryLoadException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.sys.SysProperties.JAVA_LIBRARY_PATH;
import static io.mercury.common.sys.SysProperties.OS_NAME;
import static io.mercury.common.thread.SleepSupport.sleep;

@NotThreadSafe
public final class CtpGateway implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpGateway.class);

    // 静态加载FtdcLibrary
    static {
        try {
            loadNativeLibrary();
        } catch (NativeLibraryLoadException e) {
            log.error(e.getMessage(), e);
            log.error("CTP native library file loading error, System must exit. status -1");
            System.exit(-1);
        }
    }

    private static final AtomicBoolean isLoaded = new AtomicBoolean(false);

    private static void loadNativeLibrary() throws NativeLibraryLoadException {
        if (isLoaded.compareAndSet(false, true)) {
            try {
                log.info("Trying to load library !!!");
                // 根据操作系统选择加载不同库文件
                if (OS_NAME.toLowerCase().startsWith("windows")) {
                    log.info("Copy win64 library file to java.library.path -> {}", JAVA_LIBRARY_PATH);
                    // TODO 复制DLL文件到LIBRARY_PATH目录
                    // 加载.dll文件
                    //////////////////////////////// thostapi_wrap.dll
                    System.loadLibrary("thostapi_wrap");
                    log.info("System.loadLibrary() -> thostapi_wrap");
                    //////////////////////////////// thostmduserapi_se.dll
                    System.loadLibrary("thostmduserapi_se");
                    log.info("System.loadLibrary() -> thostmduserapi_se");
                    //////////////////////////////// thosttraderapi_se.dll
                    System.loadLibrary("thosttraderapi_se");
                    log.info("System.loadLibrary() -> thosttraderapi_se");
                } else if (OS_NAME.toLowerCase().startsWith("linux")) {
                    log.info("Copy linux64 library file to java.library.path -> {}", JAVA_LIBRARY_PATH);
                    // TODO 复制SO文件到LIBRARY_PATH目录
                    // 加载.so文件
                    //////////////////////////////// libthostapi_wrap.so
                    System.load("/usr/lib/ctp_6.3.13/libthostapi_wrap.so");
                    log.info("System.load() -> /usr/lib/libthostapi_wrap.so");
                    //////////////////////////////// libthostmduserapi_se.so
                    System.load("/usr/lib/ctp_6.3.13/libthostmduserapi_se.so");
                    log.info("System.load() -> /usr/lib/libthostmduserapi_se.so");
                    //////////////////////////////// libthosttraderapi_se.so
                    System.load("/usr/lib/ctp_6.3.13/libthosttraderapi_se.so");
                    log.info("System.load() -> /usr/lib/libthosttraderapi_se.so");
                } else {
                    log.error("Unsupported operating system : {}", OS_NAME);
                    throw new UnsupportedOperationException("Unsupported operating system : " + OS_NAME);
                }
                log.info("Load library success by os -> {}", OS_NAME);
            } catch (SecurityException | UnsatisfiedLinkError | NullPointerException e) {
                isLoaded.set(false);
                log.info("Load library failure by os -> {}", OS_NAME);
                throw new NativeLibraryLoadException("Load native library failure, Throw by -> " + e.getClass(), e);
            }
        } else {
            log.warn("Library already loaded, CtpGateway::loadNativeLibrary() cannot be called repeatedly by CtpGateway");
        }
    }


    // gatewayId
    private final String gatewayId;

    // 基础配置信息
    private final CtpConfig config;

    // 行情Gateway
    private CtpMdGateway mdGateway;

    // 交易Gateway
    private CtpTraderGateway traderGateway;

    // FtdcEvent 事件发布器
    private final FtdcEventPublisher publisher;

    private final long REQUEST_INTERVAL = 850;

    /**
     * @param gatewayId String
     * @param config    CtpConfig
     * @param publisher FtdcEventPublisher
     * @param type      运行模式: 0,正常模式; 1,行情模式; 2,交易模式
     */
    public CtpGateway(@Nonnull String gatewayId,
                      @Nonnull CtpConfig config, @Nonnull ConnectionMode type,
                      @Nonnull FtdcEventPublisher publisher) {
        Asserter.nonEmpty(gatewayId, "gatewayId");
        Asserter.nonNull(config, "config");
        Asserter.nonNull(publisher, "publisher");
        this.gatewayId = gatewayId;
        this.config = config;
        this.publisher = publisher;
        initializer(type);
    }

    @PostConstruct
    private void initializer(ConnectionMode type) {
        switch (type) {
            case OnlyMarketData -> this.mdGateway = new CtpMdGateway(gatewayId, config, publisher);
            case OnlyTrade -> this.traderGateway = new CtpTraderGateway(gatewayId, config, publisher);
            default -> {
                this.mdGateway = new CtpMdGateway(gatewayId, config, publisher);
                this.traderGateway = new CtpTraderGateway(gatewayId, config, publisher);
            }
        }
    }

    /**
     * 启动并挂起线程
     */
    public void startup() {
        if (mdGateway != null) {
            mdGateway.startup();
            sleep(REQUEST_INTERVAL);
        }
        if (traderGateway != null) {
            traderGateway.startup();
            sleep(REQUEST_INTERVAL);
        }
    }

    /**
     * 行情订阅接口
     *
     * @param instruments String[]
     */
    public int SubscribeMarketData(@Nonnull String[] instruments) {
        return mdGateway.SubscribeMarketData(instruments);
    }

    /**
     * 报单请求
     *
     * @param field CThostFtdcInputOrderField
     */
    public int ReqOrderInsert(CThostFtdcInputOrderField field) {
        return traderGateway.ReqOrderInsert(field);
    }

    /**
     * 撤单请求
     *
     * @param field CThostFtdcInputOrderActionField
     */
    public int ReqOrderAction(CThostFtdcInputOrderActionField field) {
        return traderGateway.ReqOrderAction(field);
    }

    /**
     * 查询订单
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int ReqQryOrder(String exchangeCode, String instrumentCode) {
        return traderGateway.ReqQryOrder(exchangeCode, instrumentCode);
    }

    /**
     * 查询账户
     */
    public int ReqQryTradingAccount() {
        return traderGateway.ReqQryTradingAccount();
    }

    /**
     * 查询持仓信息
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int ReqQryInvestorPosition(String exchangeCode, String instrumentCode) {
        return traderGateway.ReqQryInvestorPosition(exchangeCode, instrumentCode);
    }

    /**
     * 查询结算信息
     */
    public int ReqQrySettlementInfo() {
        return traderGateway.ReqQrySettlementInfo();
    }

    /**
     * 查询交易标的
     *
     * @param exchangeId   String
     * @param instrumentId String
     */
    public int ReqQryInstrument(String exchangeId, String instrumentId) {
        return traderGateway.ReqQryInstrument(exchangeId, instrumentId);
    }

    /**
     * @throws IOException ioe
     */
    @Override
    public void close() throws IOException {
        if (mdGateway != null) {
            mdGateway.close();
            sleep(REQUEST_INTERVAL);
        }
        if (traderGateway != null) {
            traderGateway.close();
            sleep(REQUEST_INTERVAL);
        }
    }

}