package io.rapid.adaptor.ctp.gateway.upstream;


import io.mercury.common.annotation.NativeSpiImpl;
import org.rationalityfrontline.jctp.CThostFtdcDepthMarketDataField;
import org.rationalityfrontline.jctp.CThostFtdcForQuoteRspField;
import org.rationalityfrontline.jctp.CThostFtdcMdSpi;
import org.rationalityfrontline.jctp.CThostFtdcMulticastInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcSpecificInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcUserLogoutField;

@NativeSpiImpl
public final class FtdcMdSpi1 extends CThostFtdcMdSpi {

    /**
     * FTDC MarketData Listener
     */
    private final FtdcMdListener listener;

    /**
     * @param listener FtdcMdListener
     */
    public FtdcMdSpi1(FtdcMdListener listener) {
        this.listener = listener;
    }

    /**
     * 当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    @Override
    public void OnFrontConnected() {
        listener.fireFrontConnected();
    }

    /**
     * 当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后, API会自动重新连接, 客户端可不做处理.
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
        listener.fireFrontDisconnected(Reason);
    }

    /**
     * 心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void OnHeartBeatWarning(int TimeLapse) {
        listener.fireHeartBeatWarning(TimeLapse);
    }

    /**
     * 登录请求响应
     *
     * @param RspUserLogin CThostFtdcRspUserLoginField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    @Override
    public void OnRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUserLogin(RspUserLogin, RspInfo, RequestID, IsLast);
    }

    /**
     * 登出请求响应
     *
     * @param UserLogout CThostFtdcUserLogoutField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    @Override
    public void OnRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUserLogout(UserLogout, RspInfo, RequestID, IsLast);
    }

    /**
     * 错误应答
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspError(RspInfo, RequestID, IsLast);
    }

    /**
     * 订阅行情应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspSubMarketData(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * 取消订阅行情应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspUnSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUnSubMarketData(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * 订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspSubForQuoteRsp(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * 取消订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    @Override
    public void OnRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUnSubForQuoteRsp(SpecificInstrument, RspInfo, RequestID, IsLast);
    }

    /**
     * 深度行情通知
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     */
    @Override
    public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData) {
        listener.fireRtnDepthMarketData(DepthMarketData);
    }

    /**
     * 询价通知
     *
     * @param ForQuoteRsp CThostFtdcForQuoteRspField
     */
    @Override
    public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField ForQuoteRsp) {
        listener.fireRtnForQuoteRsp(ForQuoteRsp);
    }

    /**
     * 请求查询组播合约
     *
     * @param MulticastInstrument CThostFtdcMulticastInstrumentField
     * @param RspInfo             CThostFtdcRspInfoField
     * @param RequestID           int
     * @param IsLast              boolean
     */
    @Override
    public void OnRspQryMulticastInstrument(CThostFtdcMulticastInstrumentField MulticastInstrument,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryMulticastInstrument(MulticastInstrument, RspInfo, RequestID, IsLast);
    }

}