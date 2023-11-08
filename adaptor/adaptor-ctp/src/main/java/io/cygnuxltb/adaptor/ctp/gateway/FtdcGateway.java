package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcMdApi;
import ctp.thostapi.CThostFtdcMdSpi;
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
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderApi;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.cygnuxltb.adaptor.ctp.CtpConfig;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEventPublisher;
import io.cygnuxltb.adaptor.ctp.gateway.utils.NativeLibraryManager;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.file.FileUtil;
import io.mercury.common.lang.Asserter;
import io.mercury.common.lang.exception.NativeLibraryException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.rapid.core.adaptor.ConnectionMode;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static ctp.thostapi.THOST_TE_RESUME_TYPE.THOST_TERT_RESUME;
import static io.cygnuxltb.adaptor.ctp.gateway.utils.FtdcRspInfoHandler.nonError;
import static io.mercury.common.thread.SleepSupport.sleep;
import static io.mercury.common.thread.ThreadSupport.startNewMaxPriorityThread;
import static io.mercury.common.thread.ThreadSupport.startNewThread;

@NotThreadSafe
public final class FtdcGateway implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcGateway.class);



    // gatewayId
    private final String gatewayId;

    // 基础配置信息
    private final CtpConfig config;

    // 行情Gateway
    private FtdcMdGateway mdGateway;

    // 交易Gateway
    private FtdcTraderGateway traderGateway;

    // FtdcEvent 事件发布器
    private final FtdcEventPublisher publisher;

    // 查询间隔
    private final long REQUEST_INTERVAL = 750;

    /**
     * @param gatewayId String
     * @param config    CtpConfig
     * @param type      运行模式: 0,正常模式; 1,行情模式; 2,交易模式
     * @param publisher FtdcEventPublisher
     */
    public FtdcGateway(@Nonnull String gatewayId, @Nonnull CtpConfig config,
                       @Nonnull ConnectionMode type, @Nonnull FtdcEventPublisher publisher) {
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
            case MARKET_DATA -> this.mdGateway = new FtdcMdGateway();
            case TRADE -> this.traderGateway = new FtdcTraderGateway();
            default -> {
                this.mdGateway = new FtdcMdGateway();
                this.traderGateway = new FtdcTraderGateway();
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
    public int subscribeMarketData(@Nonnull String[] instruments) {
        return mdGateway.SubscribeMarketData(instruments);
    }

    /**
     * 报单请求
     *
     * @param field CThostFtdcInputOrderField
     */
    public int reqOrderInsert(CThostFtdcInputOrderField field) {
        return traderGateway.ReqOrderInsert(field);
    }

    /**
     * 撤单请求
     *
     * @param field CThostFtdcInputOrderActionField
     */
    public int reqOrderAction(CThostFtdcInputOrderActionField field) {
        return traderGateway.ReqOrderAction(field);
    }

    /**
     * 查询订单
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int reqQryOrder(String exchangeCode, String instrumentCode) {
        return traderGateway.ReqQryOrder(exchangeCode, instrumentCode);
    }

    /**
     * 查询账户
     */
    public int reqQryTradingAccount() {
        return traderGateway.ReqQryTradingAccount();
    }

    /**
     * 查询持仓信息
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public int reqQryInvestorPosition(String exchangeCode, String instrumentCode) {
        return traderGateway.ReqQryInvestorPosition(exchangeCode, instrumentCode);
    }

    /**
     * 查询结算信息
     */
    public int reqQrySettlementInfo() {
        return traderGateway.ReqQrySettlementInfo();
    }

    /**
     * 查询交易标的
     *
     * @param exchangeId   String
     * @param instrumentId String
     */
    public int reqQryInstrument(String exchangeId, String instrumentId) {
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







    abstract static class AbstractFtdcCallback {

        protected final String callbackName = this.getClass().getSimpleName();

        // FtdcEvent 事件发布器
        protected final FtdcEventPublisher publisher;

        protected AbstractFtdcCallback(FtdcEventPublisher publisher) {
            this.publisher = publisher;
        }

        /**
         * 错误推送回调
         *
         * @param field     CThostFtdcRspInfoField
         * @param RequestID int
         * @param isLast    boolean
         */
        void onRspError(CThostFtdcRspInfoField field, int RequestID, boolean isLast) {
            log.error("{}::onRspError -> RequestID==[{}], ErrorID==[{}], ErrorMsg==[{}], isLast==[{}]",
                    callbackName, RequestID, field.getErrorID(), field.getErrorMsg(), isLast);
            publisher.publish(field, RequestID, isLast);
        }

    }


}