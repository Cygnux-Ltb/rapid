package io.cygnux.rapid.adapter.ctp.component.handler;

import io.cygnux.rapid.adapter.ctp.FtdcRspHandlerImpl;
import io.cygnux.rapid.gateway.ctp.event.FtdcRspEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("logFtdcHandler")
public class LogFtdcRspHandlerImpl extends FtdcRspHandlerImpl {

    private static final Logger log = Log4j2LoggerFactory.getLogger(LogFtdcRspHandlerImpl.class);

    @Value("${adaptor.event.logging:true}")
    private boolean isEventLogging;

    @PostConstruct
    public void init() {
        if (isEventLogging) {
            FtdcRspEvent.enableLogging();
            log.info("FtdcRspHandler init -> FtdcRspEvent.enableLogging()");
        }
        log.info("FtdcRspHandler initialized");
    }

    /**
     * 未支持的事件
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onUnsupported(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 通信连接断开
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFrontDisconnected(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 心跳超时警告
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onHeartBeatWarning(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 错误应答
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onRspError(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 用户登录响应
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onRspUserLogin(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 用户登出请求(响应)
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onUserLogout(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 行情处理
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcDepthMarketData(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 指定的合约
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcSpecificInstrument(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 返回报单错误
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInputOrder(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 返回撤单提交错误
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInputOrderAction(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 合约状态
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInstrumentStatus(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 持仓信息
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInvestorPosition(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 报单推送
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcOrder(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 返回撤单错误
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcOrderAction(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 成交推送
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcTrade(FtdcRspEvent event) {
        event.logging();
    }

    /**
     * 账户信息(余额)
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcTradingAccount(FtdcRspEvent event) {
        event.logging();
    }

}
