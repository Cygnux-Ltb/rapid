package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcRspInfoField;
import io.cygnuxltb.adaptor.ctp.CtpConfig;
import io.cygnuxltb.adaptor.ctp.gateway.event.FtdcEventPublisher;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.adaptor.ConnectionMode;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Closeable;
import java.io.IOException;

import static io.mercury.common.thread.SleepSupport.sleep;

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