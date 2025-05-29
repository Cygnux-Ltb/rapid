package io.rapid.adaptor.ctp.gateway;

import io.mercury.common.annotation.NativeSpiImpl;
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
import org.rationalityfrontline.jctp.CThostFtdcTraderSpi;
import org.rationalityfrontline.jctp.CThostFtdcTradingAccountField;
import org.rationalityfrontline.jctp.CThostFtdcTradingAccountPasswordUpdateField;
import org.rationalityfrontline.jctp.CThostFtdcTradingCodeField;
import org.rationalityfrontline.jctp.CThostFtdcTradingNoticeField;
import org.rationalityfrontline.jctp.CThostFtdcTradingNoticeInfoField;
import org.rationalityfrontline.jctp.CThostFtdcTransferBankField;
import org.rationalityfrontline.jctp.CThostFtdcTransferSerialField;
import org.rationalityfrontline.jctp.CThostFtdcUserLogoutField;
import org.rationalityfrontline.jctp.CThostFtdcUserPasswordUpdateField;

@NativeSpiImpl
public final class FtdcTraderSpi_V672 extends CThostFtdcTraderSpi {

    /**
     * FTDC Trader Listener
     */
    private final FtdcTraderListener listener;

    /**
     * @param listener FtdcTraderListener
     */
    FtdcTraderSpi_V672(FtdcTraderListener listener) {
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
     * 当客户端与交易后台通信连接断开时, 该方法被调用. <br>
     * 当发生这个情况后. API会自动重新连接, 客户端可不做处理.
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
     * 客户端认证响应
     *
     * @param Field     CThostFtdcRspAuthenticateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspAuthenticate(CThostFtdcRspAuthenticateField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspAuthenticate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspUserLogin(CThostFtdcRspUserLoginField Field,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUserLogin(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspUserLogout(CThostFtdcUserLogoutField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUserLogout(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUserPasswordUpdate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField Field,
                                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspTradingAccountPasswordUpdate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspUserAuthMethod(CThostFtdcRspUserAuthMethodField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspUserAuthMethod(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspGenUserCaptcha(CThostFtdcRspGenUserCaptchaField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspGenUserCaptcha(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspGenUserText(CThostFtdcRspGenUserTextField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspGenUserText(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * 报单录入请求响应
     *
     * @param Field     CThostFtdcInputOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspOrderInsert(CThostFtdcInputOrderField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspOrderInsert(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspParkedOrderInsert(CThostFtdcParkedOrderField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspParkedOrderInsert(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspParkedOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * 报单操作请求响应
     *
     * @param Field     CThostFtdcInputOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspOrderAction(CThostFtdcInputOrderActionField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * 查询最大报单数量响应
     *
     * @param Field     CThostFtdcQryMaxOrderVolumeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryMaxOrderVolume(CThostFtdcQryMaxOrderVolumeField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryMaxOrderVolume(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspSettlementInfoConfirm(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspRemoveParkedOrder(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspRemoveParkedOrderAction(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspExecOrderInsert(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspExecOrderAction(CThostFtdcInputExecOrderActionField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspExecOrderAction(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspForQuoteInsert(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQuoteInsert(CThostFtdcInputQuoteField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQuoteInsert(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQuoteAction(CThostFtdcInputQuoteActionField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQuoteAction(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspBatchOrderAction(CThostFtdcInputBatchOrderActionField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspBatchOrderAction(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspOptionSelfCloseInsert(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspOptionSelfCloseAction(CThostFtdcInputOptionSelfCloseActionField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspOptionSelfCloseAction(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspCombActionInsert(CThostFtdcInputCombActionField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspCombActionInsert(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryOrder(CThostFtdcOrderField Field,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryOrder(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryTrade(CThostFtdcTradeField Field,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryTrade(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * 请求查询投资者持仓响应
     *
     * @param Field     CThostFtdcInvestorPositionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInvestorPosition(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryTradingAccount(CThostFtdcTradingAccountField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryTradingAccount(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInvestor(CThostFtdcInvestorField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInvestor(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryTradingCode(CThostFtdcTradingCodeField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryTradingCode(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInstrumentMarginRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInstrumentCommissionRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryExchange(CThostFtdcExchangeField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryExchange(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryProduct(CThostFtdcProductField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryProduct(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInstrument(CThostFtdcInstrumentField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInstrument(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryDepthMarketData(CThostFtdcDepthMarketDataField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryDepthMarketData(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQrySettlementInfo(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryTransferBank(CThostFtdcTransferBankField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryTransferBank(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInvestorPositionDetail(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryNotice(CThostFtdcNoticeField Field,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryNotice(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQrySettlementInfoConfirm(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField Field,
                                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInvestorPositionCombineDetail(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryCFMMCTradingAccountKey(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryEWarrantOffset(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInvestorProductGroupMargin(CThostFtdcInvestorProductGroupMarginField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInvestorProductGroupMargin(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryExchangeMarginRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryExchangeMarginRateAdjust(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryExchangeRate(CThostFtdcExchangeRateField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryExchangeRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQrySecAgentACIDMap(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryProductExchRate(CThostFtdcProductExchRateField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryProductExchRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryProductGroup(CThostFtdcProductGroupField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryProductGroup(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryMMInstrumentCommissionRate(CThostFtdcMMInstrumentCommissionRateField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryMMInstrumentCommissionRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryMMOptionInstrCommRate(CThostFtdcMMOptionInstrCommRateField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryMMOptionInstrCommRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * 请求查询报单手续费响应
     *
     * @param Field     CThostFtdcInstrumentOrderCommRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInstrumentOrderCommRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQrySecAgentTradingAccount(CThostFtdcTradingAccountField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQrySecAgentTradingAccount(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQrySecAgentCheckMode(CThostFtdcSecAgentCheckModeField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQrySecAgentCheckMode(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQrySecAgentTradeInfo(CThostFtdcSecAgentTradeInfoField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQrySecAgentTradeInfo(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryOptionInstrTradeCost(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryOptionInstrCommRate(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryExecOrder(CThostFtdcExecOrderField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryExecOrder(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryForQuote(CThostFtdcForQuoteField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryForQuote(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryQuote(CThostFtdcQuoteField Field,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryQuote(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryOptionSelfClose(CThostFtdcOptionSelfCloseField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryOptionSelfClose(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryInvestUnit(CThostFtdcInvestUnitField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryInvestUnit(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryCombInstrumentGuard(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryCombAction(CThostFtdcCombActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryCombAction(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryTransferSerial(CThostFtdcTransferSerialField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryTransferSerial(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryAccountregister(CThostFtdcAccountregisterField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryAccountregister(Field, RspInfo, RequestID, IsLast);
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
     * 报单通知
     *
     * @param Order CThostFtdcOrderField
     */
    @Override
    public void OnRtnOrder(CThostFtdcOrderField Order) {
        listener.fireRtnOrder(Order);
    }

    /**
     * 成交通知
     *
     * @param Trade CThostFtdcTradeField
     */
    @Override
    public void OnRtnTrade(CThostFtdcTradeField Trade) {
        listener.fireRtnTrade(Trade);
    }

    /**
     * 报单录入错误回报
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOrderInsert(CThostFtdcInputOrderField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnOrderInsert(Field, RspInfo);
    }

    /**
     * 报单操作错误回报
     *
     * @param Field   CThostFtdcOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOrderAction(CThostFtdcOrderActionField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnOrderAction(Field, RspInfo);
    }

    /**
     * 合约交易状态通知
     *
     * @param Field CThostFtdcInstrumentStatusField
     */
    @Override
    public void OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        listener.fireRtnInstrumentStatus(Field);
    }

    /**
     * 交易所公告通知
     *
     * @param Field CThostFtdcBulletinField
     */
    @Override
    public void OnRtnBulletin(CThostFtdcBulletinField Field) {
        listener.fireRtnBulletin(Field);
    }

    /**
     * 交易通知
     *
     * @param Field CThostFtdcTradingNoticeInfoField
     */
    @Override
    public void OnRtnTradingNotice(CThostFtdcTradingNoticeInfoField Field) {
        listener.fireRtnTradingNotice(Field);
    }

    /**
     * 提示条件单校验错误
     *
     * @param Field CThostFtdcErrorConditionalOrderField
     */
    @Override
    public void OnRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField Field) {
        listener.fireRtnErrorConditionalOrder(Field);
    }

    /**
     * 执行宣告通知
     *
     * @param Field CThostFtdcExecOrderField
     */
    @Override
    public void OnRtnExecOrder(CThostFtdcExecOrderField Field) {
        listener.fireRtnExecOrder(Field);
    }

    /**
     * 执行宣告录入错误回报
     *
     * @param Field   CThostFtdcInputExecOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                        CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnExecOrderInsert(Field, RspInfo);
    }

    /**
     * 执行宣告操作错误回报
     *
     * @param Field   CThostFtdcExecOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnExecOrderAction(CThostFtdcExecOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnExecOrderAction(Field, RspInfo);
    }

    /**
     * 询价录入错误回报
     *
     * @param Field   CThostFtdcInputForQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                       CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnForQuoteInsert(Field, RspInfo);
    }

    /**
     * 报价通知
     *
     * @param Field CThostFtdcQuoteField
     */
    @Override
    public void OnRtnQuote(CThostFtdcQuoteField Field) {
        listener.fireRtnQuote(Field);
    }

    /**
     * 报价录入错误回报
     *
     * @param Field   CThostFtdcInputQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnQuoteInsert(CThostFtdcInputQuoteField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnQuoteInsert(Field, RspInfo);
    }

    /**
     * 报价操作错误回报
     *
     * @param Field   CThostFtdcQuoteActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnQuoteAction(CThostFtdcQuoteActionField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnQuoteAction(Field, RspInfo);
    }

    /**
     * 询价通知
     *
     * @param Field CThostFtdcForQuoteRspField
     */
    @Override
    public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField Field) {
        listener.fireRtnForQuoteRsp(Field);
    }

    /**
     * 保证金监控中心用户令牌
     *
     * @param Field CThostFtdcCFMMCTradingAccountTokenField
     */
    @Override
    public void OnRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField Field) {
        listener.fireRtnCFMMCTradingAccountToken(Field);
    }

    /**
     * 批量报单操作错误回报
     *
     * @param Field   CThostFtdcBatchOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField Field,
                                         CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnBatchOrderAction(Field, RspInfo);
    }

    /**
     * 期权自对冲通知
     *
     * @param Field CThostFtdcOptionSelfCloseField
     */
    @Override
    public void OnRtnOptionSelfClose(CThostFtdcOptionSelfCloseField Field) {
        listener.fireRtnOptionSelfClose(Field);
    }

    /**
     * 期权自对冲录入错误回报
     *
     * @param Field   CThostFtdcInputOptionSelfCloseField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                              CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnOptionSelfCloseInsert(Field, RspInfo);
    }

    /**
     * 期权自对冲操作错误回报
     *
     * @param Field   CThostFtdcOptionSelfCloseActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOptionSelfCloseAction(CThostFtdcOptionSelfCloseActionField Field,
                                              CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnOptionSelfCloseAction(Field, RspInfo);
    }

    /**
     * 申请组合通知
     *
     * @param Field CThostFtdcCombActionField
     */
    @Override
    public void OnRtnCombAction(CThostFtdcCombActionField Field) {
        listener.fireRtnCombAction(Field);
    }

    /**
     * 申请组合录入错误回报
     *
     * @param Field   CThostFtdcInputCombActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnCombActionInsert(CThostFtdcInputCombActionField Field,
                                         CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnCombActionInsert(Field, RspInfo);
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
    public void OnRspQryContractBank(CThostFtdcContractBankField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryContractBank(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryParkedOrder(CThostFtdcParkedOrderField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryParkedOrder(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryParkedOrderAction(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryTradingNotice(CThostFtdcTradingNoticeField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryTradingNotice(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryBrokerTradingParams(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQryBrokerTradingAlgos(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQueryCFMMCTradingAccountToken(CThostFtdcQueryCFMMCTradingAccountTokenField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQueryCFMMCTradingAccountToken(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * 银行发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromBankToFutureByBank(CThostFtdcRspTransferField Field) {
        listener.fireRtnFromBankToFutureByBank(Field);
    }

    /**
     * 银行发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromFutureToBankByBank(CThostFtdcRspTransferField Field) {
        listener.fireRtnFromFutureToBankByBank(Field);
    }

    /**
     * 银行发起冲正银行转期货通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField Field) {
        listener.fireRtnRepealFromBankToFutureByBank(Field);
    }

    /**
     * 银行发起冲正期货转银行通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField Field) {
        listener.fireRtnRepealFromFutureToBankByBank(Field);
    }

    /**
     * 期货发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromBankToFutureByFuture(CThostFtdcRspTransferField Field) {
        listener.fireRtnFromBankToFutureByFuture(Field);
    }

    /**
     * 期货发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromFutureToBankByFuture(CThostFtdcRspTransferField Field) {
        listener.fireRtnFromFutureToBankByFuture(Field);
    }

    /**
     * 系统运行时期货端手工发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField Field) {
        listener.fireRtnRepealFromBankToFutureByFutureManual(Field);
    }

    /**
     * 系统运行时期货端手工发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField Field) {
        listener.fireRtnRepealFromFutureToBankByFutureManual(Field);
    }

    /**
     * 期货发起查询银行余额通知
     *
     * @param Field CThostFtdcNotifyQueryAccountField
     */
    @Override
    public void OnRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField Field) {
        listener.fireRtnQueryBankBalanceByFuture(Field);
    }

    /**
     * 期货发起银行资金转期货错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                             CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnBankToFutureByFuture(Field, RspInfo);
    }

    /**
     * 期货发起期货资金转银行错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                             CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnFutureToBankByFuture(Field, RspInfo);
    }

    /**
     * 系统运行时期货端手工发起冲正银行转期货错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField Field,
                                                         CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnRepealBankToFutureByFutureManual(Field, RspInfo);
    }

    /**
     * 系统运行时期货端手工发起冲正期货转银行错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField Field,
                                                         CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnRepealFutureToBankByFutureManual(Field, RspInfo);
    }

    /**
     * 期货发起查询银行余额错误回报
     *
     * @param Field   CThostFtdcReqQueryAccountField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField Field,
                                                 CThostFtdcRspInfoField RspInfo) {
        listener.fireErrRtnQueryBankBalanceByFuture(Field, RspInfo);
    }

    /**
     * 期货发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField Field) {
        listener.fireRtnRepealFromBankToFutureByFuture(Field);
    }

    /**
     * 期货发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField Field) {
        listener.fireRtnRepealFromFutureToBankByFuture(Field);
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
    public void OnRspFromBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspFromBankToFutureByFuture(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspFromFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspFromFutureToBankByFuture(Field, RspInfo, RequestID, IsLast);
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
    public void OnRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        listener.fireRspQueryBankAccountMoneyByFuture(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * 银行发起银期开户通知
     *
     * @param Field CThostFtdcOpenAccountField
     */
    @Override
    public void OnRtnOpenAccountByBank(CThostFtdcOpenAccountField Field) {
        listener.fireRtnOpenAccountByBank(Field);
    }

    /**
     * 银行发起银期销户通知
     *
     * @param Field CThostFtdcCancelAccountField
     */
    @Override
    public void OnRtnCancelAccountByBank(CThostFtdcCancelAccountField Field) {
        listener.fireRtnCancelAccountByBank(Field);
    }

    /**
     * 银行发起变更银行账号通知
     *
     * @param Field CThostFtdcChangeAccountField
     */
    @Override
    public void OnRtnChangeAccountByBank(CThostFtdcChangeAccountField Field) {
        listener.fireRtnChangeAccountByBank(Field);
    }

}