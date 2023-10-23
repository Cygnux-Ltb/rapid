package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;

public abstract class FtdcMdCallback {


    ///当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
    abstract void onFrontConnected();

    ///当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后, API会自动重新连接, 客户端可不做处理.
    ///@param Reason 错误原因
    ///        0x1001 网络读失败
    ///        0x1002 网络写失败
    ///        0x2001 接收心跳超时
    ///        0x2002 发送心跳失败
    ///        0x2003 收到错误报文
    abstract void onFrontDisconnected(int Reason);

    ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
    ///@param TimeLapse 距离上次接收报文的时间
    abstract void onHeartBeatWarning(int TimeLapse);

    ///登录请求响应
    abstract void onRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    ///登出请求响应
    abstract void onRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    ///错误应答
    abstract void onRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    ///订阅行情应答
    abstract void onRspSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    ///取消订阅行情应答
    abstract void onRspUnSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);

    ///订阅询价应答
    abstract void onRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast);

    ///取消订阅询价应答
    abstract void onRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast);

    ///深度行情通知
    abstract void onRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData);

    ///询价通知
    abstract void onRtnForQuoteRsp(CThostFtdcForQuoteRspField ForQuoteRsp);

}
