package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.cygnuxltb.adaptor.ctp.CtpConfiguration;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcRspMsg;
import io.cygnuxltb.jcts.core.adaptor.ConnectionType;
import io.mercury.common.annotation.thread.MustBeThreadSafe;
import io.mercury.common.functional.Handler;
import io.mercury.common.lang.Asserter;
import io.mercury.common.lang.exception.NativeLibraryLoadException;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Closeable;
import java.io.IOException;

import static io.mercury.common.thread.SleepSupport.sleep;

@NotThreadSafe
public final class CtpGateway implements Closeable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpGateway.class);

    // 静态加载FtdcLibrary
    static {
        try {
            CtpLibraryManager.startLoad(CtpGateway.class);
        } catch (NativeLibraryLoadException e) {
            log.error(e.getMessage(), e);
            log.error("CTP native library file loading error, System must exit. status -1");
            System.exit(-1);
        }
    }

    // gatewayId
    private final String gatewayId;

    // 基础配置信息
    private final CtpConfiguration config;

    // 行情Gateway
    private CtpMdGateway mdGateway;

    // 交易Gateway
    private CtpTraderGateway traderGateway;

    // RSP消息处理器
    private final Handler<FtdcRspMsg> handler;

    private final long REQUEST_INTERVAL = 850;

    /**
     * @param gatewayId String
     * @param config    CtpConfig
     * @param handler   Handler<FtdcRspMsg>
     * @param type      运行模式: 0,正常模式; 1,行情模式; 2,交易模式
     */
    public CtpGateway(@Nonnull String gatewayId,
                      @Nonnull CtpConfiguration config,
                      @Nonnull ConnectionType type,
                      @MustBeThreadSafe Handler<FtdcRspMsg> handler) {
        Asserter.nonEmpty(gatewayId, "gatewayId");
        Asserter.nonNull(config, "config");
        Asserter.nonNull(handler, "handler");
        this.gatewayId = gatewayId;
        this.config = config;
        this.handler = handler;
        initializer(type);
    }

    @PostConstruct
    private void initializer(ConnectionType type) {
        switch (type) {
            case OnlyMarketData -> this.mdGateway = new CtpMdGateway(gatewayId, config, handler);
            case OnlyTrade -> this.traderGateway = new CtpTraderGateway(gatewayId, config, handler);
            default -> {
                this.mdGateway = new CtpMdGateway(gatewayId, config, handler);
                this.traderGateway = new CtpTraderGateway(gatewayId, config, handler);
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
    public void SubscribeMarketData(@Nonnull String[] instruments) {
        mdGateway.SubscribeMarketData(instruments);
    }

    /**
     * 报单请求
     *
     * @param field CThostFtdcInputOrderField
     */
    public void ReqOrderInsert(CThostFtdcInputOrderField field) {
        traderGateway.ReqOrderInsert(field);
    }

    /**
     * 撤单请求
     *
     * @param field CThostFtdcInputOrderActionField
     */
    public void ReqOrderAction(CThostFtdcInputOrderActionField field) {
        traderGateway.ReqOrderAction(field);
    }

    /**
     * 查询订单
     *
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public void ReqQryOrder(String exchangeCode, String instrumentCode) {
        traderGateway.ReqQryOrder(exchangeCode, instrumentCode);
    }

    /**
     * 查询账户
     */
    public void ReqQryTradingAccount() {
        traderGateway.ReqQryTradingAccount();
    }

    /**
     * @param exchangeCode   String
     * @param instrumentCode String
     */
    public void ReqQryInvestorPosition(String exchangeCode, String instrumentCode) {
        traderGateway.ReqQryInvestorPosition(exchangeCode, instrumentCode);
    }

    /**
     * 查询结算信息
     */
    public void ReqQrySettlementInfo() {
        traderGateway.ReqQrySettlementInfo();
    }

    /**
     * 查询交易标的
     *
     * @param exchangeId   String
     * @param instrumentId String
     */
    public void ReqQryInstrument(String exchangeId, String instrumentId) {
        traderGateway.ReqQryInstrument(exchangeId, instrumentId);
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