package io.rapid.adaptor.ctp.gateway.upstream;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.rationalityfrontline.jctp.CThostFtdcAccountregisterField;
import org.rationalityfrontline.jctp.CThostFtdcBatchOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcBrokerTradingAlgosField;
import org.rationalityfrontline.jctp.CThostFtdcBrokerTradingParamsField;
import org.rationalityfrontline.jctp.CThostFtdcBulletinField;
import org.rationalityfrontline.jctp.CThostFtdcCFMMCTradingAccountKeyField;
import org.rationalityfrontline.jctp.CThostFtdcCFMMCTradingAccountTokenField;
import org.rationalityfrontline.jctp.CThostFtdcCancelAccountField;
import org.rationalityfrontline.jctp.CThostFtdcChangeAccountField;
import org.rationalityfrontline.jctp.CThostFtdcCombActionField;
import org.rationalityfrontline.jctp.CThostFtdcCombInstrumentGuardField;
import org.rationalityfrontline.jctp.CThostFtdcContractBankField;
import org.rationalityfrontline.jctp.CThostFtdcDepthMarketDataField;
import org.rationalityfrontline.jctp.CThostFtdcEWarrantOffsetField;
import org.rationalityfrontline.jctp.CThostFtdcErrorConditionalOrderField;
import org.rationalityfrontline.jctp.CThostFtdcExchangeField;
import org.rationalityfrontline.jctp.CThostFtdcExchangeMarginRateAdjustField;
import org.rationalityfrontline.jctp.CThostFtdcExchangeMarginRateField;
import org.rationalityfrontline.jctp.CThostFtdcExchangeRateField;
import org.rationalityfrontline.jctp.CThostFtdcExecOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcExecOrderField;
import org.rationalityfrontline.jctp.CThostFtdcForQuoteField;
import org.rationalityfrontline.jctp.CThostFtdcForQuoteRspField;
import org.rationalityfrontline.jctp.CThostFtdcInputBatchOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputCombActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputExecOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputExecOrderField;
import org.rationalityfrontline.jctp.CThostFtdcInputForQuoteField;
import org.rationalityfrontline.jctp.CThostFtdcInputOptionSelfCloseActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOptionSelfCloseField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderField;
import org.rationalityfrontline.jctp.CThostFtdcInputQuoteActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputQuoteField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentCommissionRateField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentMarginRateField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentOrderCommRateField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentStatusField;
import org.rationalityfrontline.jctp.CThostFtdcInvestUnitField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorPositionCombineDetailField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorPositionDetailField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorPositionField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorProductGroupMarginField;
import org.rationalityfrontline.jctp.CThostFtdcMMInstrumentCommissionRateField;
import org.rationalityfrontline.jctp.CThostFtdcMMOptionInstrCommRateField;
import org.rationalityfrontline.jctp.CThostFtdcNoticeField;
import org.rationalityfrontline.jctp.CThostFtdcNotifyQueryAccountField;
import org.rationalityfrontline.jctp.CThostFtdcOpenAccountField;
import org.rationalityfrontline.jctp.CThostFtdcOptionInstrCommRateField;
import org.rationalityfrontline.jctp.CThostFtdcOptionInstrTradeCostField;
import org.rationalityfrontline.jctp.CThostFtdcOptionSelfCloseActionField;
import org.rationalityfrontline.jctp.CThostFtdcOptionSelfCloseField;
import org.rationalityfrontline.jctp.CThostFtdcOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcOrderField;
import org.rationalityfrontline.jctp.CThostFtdcParkedOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcParkedOrderField;
import org.rationalityfrontline.jctp.CThostFtdcProductExchRateField;
import org.rationalityfrontline.jctp.CThostFtdcProductField;
import org.rationalityfrontline.jctp.CThostFtdcProductGroupField;
import org.rationalityfrontline.jctp.CThostFtdcQryMaxOrderVolumeField;
import org.rationalityfrontline.jctp.CThostFtdcQueryCFMMCTradingAccountTokenField;
import org.rationalityfrontline.jctp.CThostFtdcQuoteActionField;
import org.rationalityfrontline.jctp.CThostFtdcQuoteField;
import org.rationalityfrontline.jctp.CThostFtdcRemoveParkedOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcRemoveParkedOrderField;
import org.rationalityfrontline.jctp.CThostFtdcReqQueryAccountField;
import org.rationalityfrontline.jctp.CThostFtdcReqRepealField;
import org.rationalityfrontline.jctp.CThostFtdcReqTransferField;
import org.rationalityfrontline.jctp.CThostFtdcRspAuthenticateField;
import org.rationalityfrontline.jctp.CThostFtdcRspGenUserCaptchaField;
import org.rationalityfrontline.jctp.CThostFtdcRspGenUserTextField;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.rationalityfrontline.jctp.CThostFtdcRspRepealField;
import org.rationalityfrontline.jctp.CThostFtdcRspTransferField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserAuthMethodField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcSecAgentACIDMapField;
import org.rationalityfrontline.jctp.CThostFtdcSecAgentCheckModeField;
import org.rationalityfrontline.jctp.CThostFtdcSecAgentTradeInfoField;
import org.rationalityfrontline.jctp.CThostFtdcSettlementInfoConfirmField;
import org.rationalityfrontline.jctp.CThostFtdcSettlementInfoField;
import org.rationalityfrontline.jctp.CThostFtdcTradeField;
import org.rationalityfrontline.jctp.CThostFtdcTradingAccountField;
import org.rationalityfrontline.jctp.CThostFtdcTradingAccountPasswordUpdateField;
import org.rationalityfrontline.jctp.CThostFtdcTradingCodeField;
import org.rationalityfrontline.jctp.CThostFtdcTradingNoticeField;
import org.rationalityfrontline.jctp.CThostFtdcTradingNoticeInfoField;
import org.rationalityfrontline.jctp.CThostFtdcTransferBankField;
import org.rationalityfrontline.jctp.CThostFtdcTransferSerialField;
import org.rationalityfrontline.jctp.CThostFtdcUserLogoutField;
import org.rationalityfrontline.jctp.CThostFtdcUserPasswordUpdateField;
import org.slf4j.Logger;

/**
 * FtdcTrader SPI 回调基础实现
 */
public abstract class LoggingFtdcTraderListener implements FtdcTraderListener {

    private static final Logger log = Log4j2LoggerFactory.getLogger(LoggingFtdcTraderListener.class);

    protected LoggingFtdcTraderListener() {
    }

    /**
     * 当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    @Override
    public void fireFrontConnected() {
        log.warn("TraderSpi::fireFrontConnected Unsupported");
    }


    /**
     * 当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后. API会自动重新连接, 客户端可不做处理.
     *
     * @param Reason 错误原因
     *               <br> 0x1001 网络读失败
     *               <br> 0x1002 网络写失败
     *               <br> 0x2001 接收心跳超时
     *               <br> 0x2002 发送心跳失败
     *               <br> 0x2003 收到错误报文
     */
    @Override
    public void fireFrontDisconnected(int Reason) {
        log.warn("TraderSpi::fireFrontDisconnected Unsupported -> ");
    }


    /**
     * 心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void fireHeartBeatWarning(int TimeLapse) {
        log.warn("TraderSpi::fireHeartBeatWarning Unsupported -> ");
    }


    /**
     * 客户端认证响应
     *
     * @param Field     CThostFtdcRspAuthenticateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspAuthenticate(CThostFtdcRspAuthenticateField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspAuthenticate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 登录请求响应
     *
     * @param Field     CThostFtdcRspUserLoginField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspUserLogin(CThostFtdcRspUserLoginField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspUserLogin Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 登出请求响应
     *
     * @param Field     CThostFtdcUserLogoutField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspUserLogout(CThostFtdcUserLogoutField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspUserLogout Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 用户口令更新请求响应
     *
     * @param Field     CThostFtdcUserPasswordUpdateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspUserPasswordUpdate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 资金账户口令更新请求响应
     *
     * @param Field     CThostFtdcTradingAccountPasswordUpdateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField Field,
                                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspTradingAccountPasswordUpdate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 查询用户当前支持的认证模式的回复
     *
     * @param Field     CThostFtdcRspUserAuthMethodField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspUserAuthMethod(CThostFtdcRspUserAuthMethodField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspUserAuthMethod Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 获取图形验证码请求的回复
     *
     * @param Field     CThostFtdcRspGenUserCaptchaField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspGenUserCaptcha(CThostFtdcRspGenUserCaptchaField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspGenUserCaptcha Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 获取短信验证码请求的回复
     *
     * @param Field     CThostFtdcRspGenUserTextField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspGenUserText(CThostFtdcRspGenUserTextField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspGenUserText Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 报单录入请求响应 *** (交易相关)
     *
     * @param Field     CThostFtdcInputOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOrderInsert(CThostFtdcInputOrderField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspOrderInsert Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 预埋单录入请求响应
     *
     * @param Field     CThostFtdcParkedOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspParkedOrderInsert(CThostFtdcParkedOrderField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspParkedOrderInsert Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 预埋撤单录入请求响应
     *
     * @param Field     CThostFtdcParkedOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspParkedOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 报单操作请求响应 *** (交易相关)
     *
     * @param Field     CThostFtdcInputOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOrderAction(CThostFtdcInputOrderActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 查询最大报单数量响应
     *
     * @param Field     CThostFtdcQueryMaxOrderVolumeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryMaxOrderVolume(CThostFtdcQryMaxOrderVolumeField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQueryMaxOrderVolume Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 投资者结算结果确认响应
     *
     * @param Field     CThostFtdcSettlementInfoConfirmField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspSettlementInfoConfirm Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 删除预埋单响应
     *
     * @param Field     CThostFtdcRemoveParkedOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspRemoveParkedOrder Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 删除预埋撤单响应
     *
     * @param Field     CThostFtdcRemoveParkedOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspRemoveParkedOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 执行宣告录入请求响应
     *
     * @param Field     CThostFtdcInputExecOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspExecOrderInsert Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 执行宣告操作请求响应
     *
     * @param Field     CThostFtdcInputExecOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspExecOrderAction(CThostFtdcInputExecOrderActionField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspExecOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 询价录入请求响应
     *
     * @param Field     CThostFtdcInputForQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspForQuoteInsert Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 报价录入请求响应
     *
     * @param Field     CThostFtdcInputQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQuoteInsert(CThostFtdcInputQuoteField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQuoteInsert Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 报价操作请求响应
     *
     * @param Field     CThostFtdcInputQuoteActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQuoteAction(CThostFtdcInputQuoteActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQuoteAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 批量报单操作请求响应
     *
     * @param Field     CThostFtdcInputBatchOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspBatchOrderAction(CThostFtdcInputBatchOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspBatchOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 期权自对冲录入请求响应
     *
     * @param Field     CThostFtdcInputOptionSelfCloseField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspOptionSelfCloseInsert Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 期权自对冲操作请求响应
     *
     * @param Field     CThostFtdcInputOptionSelfCloseActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspOptionSelfCloseAction(CThostFtdcInputOptionSelfCloseActionField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspOptionSelfCloseAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 申请组合录入请求响应
     *
     * @param Field     CThostFtdcInputCombActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspCombActionInsert(CThostFtdcInputCombActionField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspCombActionInsert Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询报单响应
     *
     * @param Field     CThostFtdcOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryOrder(CThostFtdcOrderField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryOrder Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询成交响应
     *
     * @param Field     CThostFtdcTradeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTrade(CThostFtdcTradeField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryTrade Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询投资者持仓响应 *** (交易相关)
     *
     * @param Field     CThostFtdcInvestorPositionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestorPosition(CThostFtdcInvestorPositionField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInvestorPosition Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询资金账户响应 *** (交易相关)
     *
     * @param Field     CThostFtdcTradingAccountField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTradingAccount(CThostFtdcTradingAccountField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryTradingAccount Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询投资者响应
     *
     * @param Field     CThostFtdcInvestorField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestor(CThostFtdcInvestorField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInvestor Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询交易编码响应
     *
     * @param Field     CThostFtdcTradingCodeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTradingCode(CThostFtdcTradingCodeField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryTradingCode Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询合约保证金率响应
     *
     * @param Field     CThostFtdcInstrumentMarginRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInstrumentMarginRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询合约手续费率响应
     *
     * @param Field     CThostFtdcInstrumentCommissionRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInstrumentCommissionRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询交易所响应
     *
     * @param Field     CThostFtdcExchangeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryExchange(CThostFtdcExchangeField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryExchange Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询产品响应
     *
     * @param Field     CThostFtdcProductField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryProduct(CThostFtdcProductField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryProduct Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询合约响应
     *
     * @param Field     CThostFtdcInstrumentField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInstrument(CThostFtdcInstrumentField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInstrument Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询行情响应
     *
     * @param Field     CThostFtdcDepthMarketDataField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryDepthMarketData(CThostFtdcDepthMarketDataField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryDepthMarketData Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询投资者结算结果响应
     *
     * @param Field     CThostFtdcSettlementInfoField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySettlementInfo(CThostFtdcSettlementInfoField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQrySettlementInfo Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询转帐银行响应
     *
     * @param Field     CThostFtdcTransferBankField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTransferBank(CThostFtdcTransferBankField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryTransferBank Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询投资者持仓明细响应
     *
     * @param Field     CThostFtdcInvestorPositionDetailField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInvestorPositionDetail Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询客户通知响应
     *
     * @param Field     CThostFtdcNoticeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryNotice(CThostFtdcNoticeField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryNotice Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询结算信息确认响应
     *
     * @param Field     CThostFtdcSettlementInfoConfirmField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQrySettlementInfoConfirm Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询投资者持仓明细响应
     *
     * @param Field     CThostFtdcInvestorPositionCombineDetailField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField Field,
                                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInvestorPositionCombineDetail Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 查询保证金监管系统经纪公司资金账户密钥响应
     *
     * @param Field     CThostFtdcCFMMCTradingAccountKeyField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryCFMMCTradingAccountKey Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询仓单折抵信息响应
     *
     * @param Field     CThostFtdcEWarrantOffsetField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryEWarrantOffset Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询投资者品种/跨品种保证金响应
     *
     * @param Field     CThostFtdcInvestorProductGroupMarginField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestorProductGroupMargin(CThostFtdcInvestorProductGroupMarginField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInvestorProductGroupMargin Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询交易所保证金率响应
     *
     * @param Field     CThostFtdcExchangeMarginRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryExchangeMarginRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询交易所调整保证金率响应
     *
     * @param Field     CThostFtdcExchangeMarginRateAdjustField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryExchangeMarginRateAdjust Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询汇率响应
     *
     * @param Field     CThostFtdcExchangeRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryExchangeRate(CThostFtdcExchangeRateField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryExchangeRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询二级代理操作员银期权限响应
     *
     * @param Field     CThostFtdcSecAgentACIDMapField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQrySecAgentACIDMap Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询产品报价汇率
     *
     * @param Field     CThostFtdcProductExchRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryProductExchRate(CThostFtdcProductExchRateField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryProductExchRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询产品组
     *
     * @param Field     CThostFtdcProductGroupField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryProductGroup(CThostFtdcProductGroupField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryProductGroup Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询做市商合约手续费率响应
     *
     * @param Field     CThostFtdcMMInstrumentCommissionRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryMMInstrumentCommissionRate(CThostFtdcMMInstrumentCommissionRateField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryMMInstrumentCommissionRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询做市商期权合约手续费响应
     *
     * @param Field     CThostFtdcMMOptionInstrCommRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryMMOptionInstrCommRate(CThostFtdcMMOptionInstrCommRateField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryMMOptionInstrCommRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询报单手续费响应 *** (交易相关)
     *
     * @param Field     CThostFtdcInstrumentOrderCommRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField Field,
                                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInstrumentOrderCommRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询资金账户响应
     *
     * @param Field     CThostFtdcTradingAccountField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySecAgentTradingAccount(CThostFtdcTradingAccountField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQrySecAgentTradingAccount Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询二级代理商资金校验模式响应
     *
     * @param Field     CThostFtdcSecAgentCheckModeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySecAgentCheckMode(CThostFtdcSecAgentCheckModeField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQrySecAgentCheckMode Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询二级代理商信息响应
     *
     * @param Field     CThostFtdcSecAgentTradeInfoField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQrySecAgentTradeInfo(CThostFtdcSecAgentTradeInfoField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQrySecAgentTradeInfo Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询期权交易成本响应
     *
     * @param Field     CThostFtdcOptionInstrTradeCostField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryOptionInstrTradeCost Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询期权合约手续费响应
     *
     * @param Field     CThostFtdcOptionInstrCommRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryOptionInstrCommRate Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询执行宣告响应
     *
     * @param Field     CThostFtdcExecOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryExecOrder(CThostFtdcExecOrderField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryExecOrder Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询询价响应
     *
     * @param Field     CThostFtdcForQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryForQuote(CThostFtdcForQuoteField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryForQuote Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询报价响应
     *
     * @param Field     CThostFtdcQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryQuote(CThostFtdcQuoteField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryQuote Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询期权自对冲响应
     *
     * @param Field     CThostFtdcOptionSelfCloseField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryOptionSelfClose(CThostFtdcOptionSelfCloseField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryOptionSelfClose Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询投资单元响应
     *
     * @param Field     CThostFtdcInvestUnitField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryInvestUnit(CThostFtdcInvestUnitField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryInvestUnit Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询组合合约安全系数响应
     *
     * @param Field     CThostFtdcCombInstrumentGuardField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryCombInstrumentGuard Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询申请组合响应
     *
     * @param Field     CThostFtdcCombActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryCombAction(CThostFtdcCombActionField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryCombAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询转帐流水响应
     *
     * @param Field     CThostFtdcTransferSerialField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTransferSerial(CThostFtdcTransferSerialField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryTransferSerial Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询银期签约关系响应
     *
     * @param Field     CThostFtdcAccountregisterField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryAccountregister(CThostFtdcAccountregisterField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryAccountregister Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 错误应答 *** (交易相关)
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspError Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 报单通知 *** (交易相关)
     *
     * @param Order CThostFtdcOrderField
     */
    @Override
    public void fireRtnOrder(CThostFtdcOrderField Order) {
        log.warn("TraderSpi::fireRtnOrder Unsupported");
    }

    /**
     * 成交通知 *** (交易相关)
     *
     * @param Trade CThostFtdcTradeField
     */
    @Override
    public void fireRtnTrade(CThostFtdcTradeField Trade) {
        log.warn("TraderSpi::fireRtnTrade Unsupported");
    }

    /**
     * 报单录入错误回报 *** (交易相关)
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderInsert(CThostFtdcInputOrderField Field,
                                      CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnOrderInsert Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 报单操作错误回报 *** (交易相关)
     *
     * @param Field   CThostFtdcOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderAction(CThostFtdcOrderActionField Field,
                                      CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 合约交易状态通知 *** (交易相关)
     *
     * @param Field CThostFtdcInstrumentStatusField
     */
    @Override
    public void fireRtnInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        log.warn("TraderSpi::fireRtnInstrumentStatus Unsupported");
    }

    /**
     * 交易所公告通知
     *
     * @param Field CThostFtdcBulletinField
     */
    @Override
    public void fireRtnBulletin(CThostFtdcBulletinField Field) {
        log.warn("TraderSpi::fireRtnBulletin Unsupported");
    }

    /**
     * 交易通知
     *
     * @param Field CThostFtdcTradingNoticeInfoField
     */
    @Override
    public void fireRtnTradingNotice(CThostFtdcTradingNoticeInfoField Field) {
        log.warn("TraderSpi::fireRtnTradingNotice Unsupported");
    }

    /**
     * 提示条件单校验错误
     *
     * @param Field CThostFtdcErrorConditionalOrderField
     */
    @Override
    public void fireRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField Field) {
        log.warn("TraderSpi::fireRtnErrorConditionalOrder Unsupported");
    }

    /**
     * 执行宣告通知
     *
     * @param Field CThostFtdcExecOrderField
     */
    @Override
    public void fireRtnExecOrder(CThostFtdcExecOrderField Field) {
        log.warn("TraderSpi::fireRtnExecOrder Unsupported");
    }

    /**
     * 执行宣告录入错误回报
     *
     * @param Field   CThostFtdcInputExecOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                          CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnExecOrderInsert Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 执行宣告操作错误回报
     *
     * @param Field   CThostFtdcExecOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnExecOrderAction(CThostFtdcExecOrderActionField Field,
                                          CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnExecOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 询价录入错误回报
     *
     * @param Field   CThostFtdcInputForQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                         CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnForQuoteInsert Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 报价通知
     *
     * @param Field CThostFtdcQuoteField
     */
    @Override
    public void fireRtnQuote(CThostFtdcQuoteField Field) {
        log.warn("TraderSpi::fireRtnQuote Unsupported");
    }

    /**
     * 报价录入错误回报
     *
     * @param Field   CThostFtdcInputQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnQuoteInsert(CThostFtdcInputQuoteField Field,
                                      CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnQuoteInsert Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 报价操作错误回报
     *
     * @param Field   CThostFtdcQuoteActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnQuoteAction(CThostFtdcQuoteActionField Field,
                                      CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnQuoteAction Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 询价通知
     *
     * @param Field CThostFtdcForQuoteRspField
     */
    @Override
    public void fireRtnForQuoteRsp(CThostFtdcForQuoteRspField Field) {
        log.warn("TraderSpi::fireRtnForQuoteRsp Unsupported");
    }

    /**
     * 保证金监控中心用户令牌
     *
     * @param Field CThostFtdcCFMMCTradingAccountTokenField
     */
    @Override
    public void fireRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField Field) {
        log.warn("TraderSpi::fireRtnCFMMCTradingAccountToken Unsupported");
    }

    /**
     * 批量报单操作错误回报
     *
     * @param Field   CThostFtdcBatchOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField Field,
                                           CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnBatchOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 期权自对冲通知
     *
     * @param Field CThostFtdcOptionSelfCloseField
     */
    @Override
    public void fireRtnOptionSelfClose(CThostFtdcOptionSelfCloseField Field) {
        log.warn("TraderSpi::fireRtnOptionSelfClose Unsupported");
    }

    /**
     * 期权自对冲录入错误回报
     *
     * @param Field   CThostFtdcInputOptionSelfCloseField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                                CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnOptionSelfCloseInsert Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 期权自对冲操作错误回报
     *
     * @param Field   CThostFtdcOptionSelfCloseActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOptionSelfCloseAction(CThostFtdcOptionSelfCloseActionField Field,
                                                CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnOptionSelfCloseAction Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 申请组合通知
     *
     * @param Field CThostFtdcCombActionField
     */
    @Override
    public void fireRtnCombAction(CThostFtdcCombActionField Field) {
        log.warn("TraderSpi::fireRtnCombAction Unsupported");
    }

    /**
     * 申请组合录入错误回报
     *
     * @param Field   CThostFtdcInputCombActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnCombActionInsert(CThostFtdcInputCombActionField Field,
                                           CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnCombActionInsert Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 请求查询签约银行响应
     *
     * @param Field     CThostFtdcContractBankField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryContractBank(CThostFtdcContractBankField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryContractBank Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询预埋单响应
     *
     * @param Field     CThostFtdcParkedOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryParkedOrder(CThostFtdcParkedOrderField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryParkedOrder Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询预埋撤单响应
     *
     * @param Field     CThostFtdcParkedOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryParkedOrderAction Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询交易通知响应
     *
     * @param Field     CThostFtdcTradingNoticeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryTradingNotice(CThostFtdcTradingNoticeField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryTradingNotice Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询经纪公司交易参数响应
     *
     * @param Field     CThostFtdcBrokerTradingParamsField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryBrokerTradingParams Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询经纪公司交易算法响应
     *
     * @param Field     CThostFtdcBrokerTradingAlgosField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQryBrokerTradingAlgos Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 请求查询监控中心用户令牌
     *
     * @param Field     CThostFtdcQueryCFMMCTradingAccountTokenField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQueryCFMMCTradingAccountToken(CThostFtdcQueryCFMMCTradingAccountTokenField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQueryCFMMCTradingAccountToken Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 银行发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromBankToFutureByBank(CThostFtdcRspTransferField Field) {
        log.warn("TraderSpi::fireRtnFromBankToFutureByBank Unsupported");
    }

    /**
     * 银行发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromFutureToBankByBank(CThostFtdcRspTransferField Field) {
        log.warn("TraderSpi::fireRtnFromFutureToBankByBank Unsupported");
    }

    /**
     * 银行发起冲正银行转期货通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField Field) {
        log.warn("TraderSpi::fireRtnRepealFromBankToFutureByBank Unsupported");
    }

    /**
     * 银行发起冲正期货转银行通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField Field) {
        log.warn("TraderSpi::fireRtnRepealFromFutureToBankByBank Unsupported");
    }

    /**
     * 期货发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromBankToFutureByFuture(CThostFtdcRspTransferField Field) {
        log.warn("TraderSpi::fireRtnFromBankToFutureByFuture Unsupported");
    }

    /**
     * 期货发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromFutureToBankByFuture(CThostFtdcRspTransferField Field) {
        log.warn("TraderSpi::fireRtnFromFutureToBankByFuture Unsupported");
    }

    /**
     * 系统运行时期货端手工发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField Field) {
        log.warn("TraderSpi::fireRtnRepealFromBankToFutureByFutureManual Unsupported");
    }

    /**
     * 系统运行时期货端手工发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField Field) {
        log.warn("TraderSpi::fireRtnRepealFromFutureToBankByFutureManual Unsupported");
    }

    /**
     * 期货发起查询银行余额通知
     *
     * @param Field CThostFtdcNotifyQueryAccountField
     */
    @Override
    public void fireRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField Field) {
        log.warn("TraderSpi::fireRtnQueryBankBalanceByFuture Unsupported");
    }

    /**
     * 期货发起银行资金转期货错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                               CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnBankToFutureByFuture Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 期货发起期货资金转银行错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                               CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnFutureToBankByFuture Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 系统运行时期货端手工发起冲正银行转期货错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField Field,
                                                           CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnRepealBankToFutureByFutureManual Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 系统运行时期货端手工发起冲正期货转银行错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField Field,
                                                           CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnRepealFutureToBankByFutureManual Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 期货发起查询银行余额错误回报
     *
     * @param Field   CThostFtdcReqQueryAccountField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField Field,
                                                   CThostFtdcRspInfoField RspInfo) {
        log.warn("TraderSpi::fireErrRtnQueryBankBalanceByFuture Unsupported -> ErrorID=={}, ErrorMsg=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg());
    }

    /**
     * 期货发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField Field) {
        log.warn("TraderSpi::fireRtnRepealFromBankToFutureByFuture Unsupported");
    }

    /**
     * 期货发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField Field) {
        log.warn("TraderSpi::fireRtnRepealFromFutureToBankByFuture Unsupported");
    }

    /**
     * 期货发起银行资金转期货应答
     *
     * @param Field     CThostFtdcReqTransferField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspFromBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspFromBankToFutureByFuture Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 期货发起期货资金转银行应答
     *
     * @param Field     CThostFtdcReqTransferField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspFromFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspFromFutureToBankByFuture Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 期货发起查询银行余额应答
     *
     * @param Field     CThostFtdcReqQueryAccountField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.warn("TraderSpi::fireRspQueryBankAccountMoneyByFuture Unsupported -> ErrorID=={}, ErrorMsg=={}, RequestID=={}, IsLast=={}",
                RspInfo.getErrorID(), RspInfo.getErrorMsg(), RequestID, IsLast);
    }

    /**
     * 银行发起银期开户通知
     *
     * @param Field CThostFtdcOpenAccountField
     */
    @Override
    public void fireRtnOpenAccountByBank(CThostFtdcOpenAccountField Field) {
        log.warn("TraderSpi::fireRtnOpenAccountByBank Unsupported");
    }

    /**
     * 银行发起银期销户通知
     *
     * @param Field CThostFtdcCancelAccountField
     */
    @Override
    public void fireRtnCancelAccountByBank(CThostFtdcCancelAccountField Field) {
        log.warn("TraderSpi::fireRtnCancelAccountByBank Unsupported");
    }

    /**
     * 银行发起变更银行账号通知
     *
     * @param Field CThostFtdcChangeAccountField
     */
    @Override
    public void fireRtnChangeAccountByBank(CThostFtdcChangeAccountField Field) {
        log.warn("TraderSpi::fireRtnChangeAccountByBank Unsupported");
    }

}