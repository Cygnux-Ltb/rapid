package io.cygnuxltb.adaptor.ctp.gateway.event.listener;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;

public abstract class BaseFtdcMdListener implements FtdcMdListener {

    protected BaseFtdcMdListener() {
    }

    /**
     * ///当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    @Override
    public void fireFrontConnected() {
    }

    /**
     * ///当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后, API会自动重新连接, 客户端可不做处理.
     *
     * @param Reason 错误原因
     *               0x1001 网络读失败
     *               0x1002 网络写失败
     *               0x2001 接收心跳超时
     *               0x2002 发送心跳失败
     *               0x2003 收到错误报文
     */
    @Override
    public void fireFrontDisconnected(int Reason) {
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
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
    public void fireRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
    }

    /**
     * ///错误应答
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspUnSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
    }

    /**
     * ///订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param bIsLast            boolean
     */
    @Override
    public void fireRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast) {
    }

    /**
     * ///取消订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param bIsLast            boolean
     */
    @Override
    public void fireRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast) {
    }

    /**
     * ///深度行情通知
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     */
    @Override
    public void fireRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData) {
    }

    /**
     * ///询价通知
     *
     * @param ForQuoteRsp CThostFtdcForQuoteRspField
     */
    @Override
    public void fireRtnForQuoteRsp(CThostFtdcForQuoteRspField ForQuoteRsp) {
    }

}