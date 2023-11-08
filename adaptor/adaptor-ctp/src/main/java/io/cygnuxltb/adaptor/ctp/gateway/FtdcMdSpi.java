package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;

public final class FtdcMdSpi extends CThostFtdcMdSpi {

    private final FtdcMdCallback callback;

    FtdcMdSpi(FtdcMdCallback callback) {
        this.callback = callback;
    }

    /**
     * ///当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    @Override
    public void OnFrontConnected() {
        callback.onFrontConnected();
    }


    /**
     * ///当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后, API会自动重新连接, 客户端可不做处理.
     *
     * @param Reason 错误原因
     *               <br> 0x1001 网络读失败
     *               <br> 0x1002 网络写失败
     *               <br> 0x2001 接收心跳超时
     *               <br> 0x2002 发送心跳失败
     *               <br> 0x2003 收到错误报文
     */
    @Override
    public void OnFrontDisconnected(int Reason) {
        callback.onFrontDisconnected(Reason);
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void OnHeartBeatWarning(int TimeLapse) {
        callback.onHeartBeatWarning(TimeLapse);
    }

    /**
     * ///登录请求响应
     *
     * @param RspUserLogin CThostFtdcRspUserLoginField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    @Override
    public void OnRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.onRspUserLogin(RspUserLogin, RspInfo, RequestID, IsLast);
    }

    /**
     * ///登出请求响应
     *
     * @param UserLogout CThostFtdcUserLogoutField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    @Override
    public void OnRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.onRspUserLogout(UserLogout, RspInfo, RequestID, IsLast);
    }

    /**
     * ///错误应答
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.onRspError(RspInfo, RequestID, IsLast);
    }

    /**
     * ///订阅行情应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.onRspSubMarketData(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * ///取消订阅行情应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspUnSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.onRspUnSubMarketData(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * ///订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.onRspSubForQuoteRsp(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * ///取消订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.onRspUnSubForQuoteRsp(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * ///深度行情通知
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     */
    @Override
    public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData) {
        callback.onRtnDepthMarketData(DepthMarketData);
    }

    /**
     * ///询价通知
     *
     * @param ForQuoteRsp CThostFtdcForQuoteRspField
     */
    @Override
    public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField ForQuoteRsp) {
        callback.onRtnForQuoteRsp(ForQuoteRsp);
    }

}