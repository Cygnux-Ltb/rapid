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
            case FrontDisconnected -> onFrontDisconnected(event);
            case HeartBeatWarning -> onHeartBeatWarning(event);
            case RspError -> onRspError(event);
            case RspUserLogin -> onRspUserLogin(event);
            case UserLogout -> onUserLogout(event);
            case FtdcDepthMarketData -> onFtdcDepthMarketData(event);
            case FtdcSpecificInstrument -> onFtdcSpecificInstrument(event);
            case FtdcInputOrder -> onFtdcInputOrder(event);
            case FtdcInputOrderAction -> onFtdcInputOrderAction(event);
            case FtdcInstrumentStatus -> onFtdcInstrumentStatus(event);
            case FtdcInvestorPosition -> onFtdcInvestorPosition(event);
            case FtdcOrder -> onFtdcOrder(event);
            case FtdcOrderAction -> onFtdcOrderAction(event);
            case FtdcTrade -> onFtdcTrade(event);
            case FtdcTradingAccount -> onFtdcTradingAccount(event);
            case Unsupported -> onUnsupported(event);
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
    abstract protected void onUnsupported(FtdcRspEvent event);

    /**
     * 通信连接断开
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFrontDisconnected(FtdcRspEvent event);

    /**
     * 心跳超时警告
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onHeartBeatWarning(FtdcRspEvent event);

    /**
     * 错误应答
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onRspError(FtdcRspEvent event);

    /**
     * 用户登录响应
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onRspUserLogin(FtdcRspEvent event);

    /**
     * 用户登出请求(响应)
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onUserLogout(FtdcRspEvent event);

    //////////////////////////////////// MD RSP ////////////////////////////////////

    /**
     * 行情处理
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcDepthMarketData(FtdcRspEvent event);

    /**
     * 指定的合约
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcSpecificInstrument(FtdcRspEvent event);


    //////////////////////////////////// TRADER RSP ////////////////////////////////////

    /**
     * 返回报单错误
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcInputOrder(FtdcRspEvent event);

    /**
     * 返回撤单提交错误
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcInputOrderAction(FtdcRspEvent event);

    /**
     * 合约状态
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcInstrumentStatus(FtdcRspEvent event);

    /**
     * 持仓信息
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcInvestorPosition(FtdcRspEvent event);

    /**
     * 报单推送
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcOrder(FtdcRspEvent event);

    /**
     * 返回撤单错误
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcOrderAction(FtdcRspEvent event);

    /**
     * 成交推送
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcTrade(FtdcRspEvent event);

    /**
     * 账户信息(余额)
     *
     * @param event FtdcRspEvent
     */
    abstract protected void onFtdcTradingAccount(FtdcRspEvent event);

}
