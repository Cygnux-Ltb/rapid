package io.rapid.adaptor.ctp;

import io.mercury.common.log4j2.StaticLogger;
import io.rapid.adaptor.ctp.event.FtdcRspEvent;
import io.rapid.core.order.OrderRefKeeper;
import lombok.Setter;

import javax.annotation.Nonnull;

public abstract non-sealed class FtdcRspHandlerImpl implements FtdcRspHandler {

    @Override
    public void handle(@Nonnull FtdcRspEvent event) {
        switch (event.getType()) {
            case FRONT_DISCONNECTED -> onFrontDisconnected(event);
            case HEARTBEAT_WARNING -> onHeartBeatWarning(event);
            case RSP_ERROR -> onRspError(event);
            case RSP_USER_LOGIN -> onRspUserLogin(event);
            case USER_LOGOUT -> onUserLogout(event);
            case FTDC_DEPTH_MARKET_DATA -> onFtdcDepthMarketData(event);
            case FTDC_SPECIFIC_INSTRUMENT -> onFtdcSpecificInstrument(event);
            case FTDC_INPUT_ORDER -> onFtdcInputOrder(event);
            case FTDC_INPUT_ORDER_ACTION -> onFtdcInputOrderAction(event);
            case FTDC_INSTRUMENT_STATUS -> onFtdcInstrumentStatus(event);
            case FTDC_INVESTOR_POSITION -> onFtdcInvestorPosition(event);
            case FTDC_ORDER -> onFtdcOrder(event);
            case FTDC_ORDER_ACTION -> onFtdcOrderAction(event);
            case FTDC_TRADE -> onFtdcTrade(event);
            case FTDC_TRADING_ACCOUNT -> onFtdcTradingAccount(event);
            case UNSUPPORTED -> onUnsupported(event);
            case null, default -> StaticLogger.error("NOTE FtdcRspHandlerImpl::handle, event -> {},", event);
        }
    }

    @Setter
    protected OrderRefKeeper orderRefKeeper;

    /**
     * 未支持的事件
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onUnsupported(FtdcRspEvent event);

    /**
     * 通信连接断开
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFrontDisconnected(FtdcRspEvent event);

    /**
     * 心跳超时警告
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onHeartBeatWarning(FtdcRspEvent event);

    /**
     * 错误应答
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onRspError(FtdcRspEvent event);

    /**
     * 用户登录响应
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onRspUserLogin(FtdcRspEvent event);

    /**
     * 用户登出请求(响应)
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onUserLogout(FtdcRspEvent event);

    //////////////////////////////////// MD RSP ////////////////////////////////////

    /**
     * 行情处理
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcDepthMarketData(FtdcRspEvent event);

    /**
     * 指定的合约
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcSpecificInstrument(FtdcRspEvent event);


    //////////////////////////////////// TRADER RSP ////////////////////////////////////

    /**
     * 返回报单错误
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcInputOrder(FtdcRspEvent event);

    /**
     * 返回撤单提交错误
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcInputOrderAction(FtdcRspEvent event);

    /**
     * 合约状态
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcInstrumentStatus(FtdcRspEvent event);

    /**
     * 持仓信息
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcInvestorPosition(FtdcRspEvent event);

    /**
     * 报单推送
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcOrder(FtdcRspEvent event);

    /**
     * 返回撤单错误
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcOrderAction(FtdcRspEvent event);

    /**
     * 成交推送
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcTrade(FtdcRspEvent event);

    /**
     * 账户信息(余额)
     *
     * @param event FtdcRspEvent
     */
    protected abstract void onFtdcTradingAccount(FtdcRspEvent event);

}
