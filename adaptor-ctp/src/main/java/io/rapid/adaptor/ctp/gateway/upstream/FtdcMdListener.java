package io.rapid.adaptor.ctp.gateway.upstream;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;

public interface FtdcMdListener {

    /**
     * 当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    void fireFrontConnected();

    /**
     * 当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后, API会自动重新连接, 客户端可不做处理.
     *
     * @param Reason 错误原因
     *               0x1001 网络读失败
     *               0x1002 网络写失败
     *               0x2001 接收心跳超时
     *               0x2002 发送心跳失败
     *               0x2003 收到错误报文
     */
    void fireFrontDisconnected(int Reason);

    /**
     * 心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    void fireHeartBeatWarning(int TimeLapse);

    /**
     * 登录请求响应
     *
     * @param RspUserLogin CThostFtdcRspUserLoginField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    void fireRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    /**
     * 登出请求响应
     *
     * @param UserLogout CThostFtdcUserLogoutField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    void fireRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    /**
     * 错误应答
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void fireRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    /**
     * 订阅行情应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    void fireRspSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    /**
     * 取消订阅行情应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    void fireRspUnSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    /**
     * 订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param bIsLast            boolean
     */
    void fireRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast);

    /**
     * 取消订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param bIsLast            boolean
     */
    void fireRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast);

    /**
     * 深度行情通知
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     */
    void fireRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData);

    /**
     * 询价通知
     *
     * @param ForQuoteRsp CThostFtdcForQuoteRspField
     */
    void fireRtnForQuoteRsp(CThostFtdcForQuoteRspField ForQuoteRsp);

}
