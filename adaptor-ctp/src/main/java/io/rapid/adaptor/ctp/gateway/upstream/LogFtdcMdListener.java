package io.rapid.adaptor.ctp.gateway.upstream;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

/**
 * FtdcMd SPI 回调基础实现
 */
public abstract class BaseFtdcMdListener implements FtdcMdListener {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BaseFtdcMdListener.class);

    protected BaseFtdcMdListener() {
    }

    /**
     * 当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    @Override
    public void fireFrontConnected() {
        log.warn("MdSpi::fireFrontConnected Unsupported");
    }

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
    @Override
    public void fireFrontDisconnected(int Reason) {
        log.warn("MdSpi::fireFrontDisconnected Unsupported");
    }

    /**
     * 心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.warn("MdSpi::fireHeartBeatWarning Unsupported");
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
    public void fireRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("MdSpi::fireRspUserLogin Unsupported");
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
    public void fireRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("MdSpi::fireRspUserLogout Unsupported");
    }

    /**
     * 错误应答
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("MdSpi::fireRspError Unsupported");
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
    public void fireRspSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("MdSpi::fireRspSubMarketData Unsupported");
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
    public void fireRspUnSubMarketData(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("MdSpi::fireRspUnSubMarketData Unsupported");
    }

    /**
     * 订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param bIsLast            boolean
     */
    @Override
    public void fireRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast) {
        log.warn("MdSpi::fireRspSubForQuoteRsp Unsupported");
    }

    /**
     * 取消订阅询价应答
     *
     * @param SpecificInstrument CThostFtdcSpecificInstrumentField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param bIsLast            boolean
     */
    @Override
    public void fireRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField SpecificInstrument,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean bIsLast) {
        log.warn("MdSpi::fireRspUnSubForQuoteRsp Unsupported");
    }

    /**
     * 深度行情通知
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     */
    @Override
    public void fireRtnDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData) {
        log.warn("MdSpi::fireRtnDepthMarketData Unsupported");
    }

    /**
     * 询价通知
     *
     * @param ForQuoteRsp CThostFtdcForQuoteRspField
     */
    @Override
    public void fireRtnForQuoteRsp(CThostFtdcForQuoteRspField ForQuoteRsp) {
        log.warn("MdSpi::fireRtnForQuoteRsp Unsupported");
    }

}
