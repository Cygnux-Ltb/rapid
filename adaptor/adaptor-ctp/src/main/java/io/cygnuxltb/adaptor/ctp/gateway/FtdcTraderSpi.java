package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcAccountregisterField;
import ctp.thostapi.CThostFtdcBatchOrderActionField;
import ctp.thostapi.CThostFtdcBrokerTradingAlgosField;
import ctp.thostapi.CThostFtdcBrokerTradingParamsField;
import ctp.thostapi.CThostFtdcBulletinField;
import ctp.thostapi.CThostFtdcCFMMCTradingAccountKeyField;
import ctp.thostapi.CThostFtdcCFMMCTradingAccountTokenField;
import ctp.thostapi.CThostFtdcCancelAccountField;
import ctp.thostapi.CThostFtdcChangeAccountField;
import ctp.thostapi.CThostFtdcCombActionField;
import ctp.thostapi.CThostFtdcCombInstrumentGuardField;
import ctp.thostapi.CThostFtdcContractBankField;
import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcEWarrantOffsetField;
import ctp.thostapi.CThostFtdcErrorConditionalOrderField;
import ctp.thostapi.CThostFtdcExchangeField;
import ctp.thostapi.CThostFtdcExchangeMarginRateAdjustField;
import ctp.thostapi.CThostFtdcExchangeMarginRateField;
import ctp.thostapi.CThostFtdcExchangeRateField;
import ctp.thostapi.CThostFtdcExecOrderActionField;
import ctp.thostapi.CThostFtdcExecOrderField;
import ctp.thostapi.CThostFtdcForQuoteField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcInputBatchOrderActionField;
import ctp.thostapi.CThostFtdcInputCombActionField;
import ctp.thostapi.CThostFtdcInputExecOrderActionField;
import ctp.thostapi.CThostFtdcInputExecOrderField;
import ctp.thostapi.CThostFtdcInputForQuoteField;
import ctp.thostapi.CThostFtdcInputOptionSelfCloseActionField;
import ctp.thostapi.CThostFtdcInputOptionSelfCloseField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInputQuoteActionField;
import ctp.thostapi.CThostFtdcInputQuoteField;
import ctp.thostapi.CThostFtdcInstrumentCommissionRateField;
import ctp.thostapi.CThostFtdcInstrumentField;
import ctp.thostapi.CThostFtdcInstrumentMarginRateField;
import ctp.thostapi.CThostFtdcInstrumentOrderCommRateField;
import ctp.thostapi.CThostFtdcInstrumentStatusField;
import ctp.thostapi.CThostFtdcInvestUnitField;
import ctp.thostapi.CThostFtdcInvestorField;
import ctp.thostapi.CThostFtdcInvestorPositionCombineDetailField;
import ctp.thostapi.CThostFtdcInvestorPositionDetailField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcInvestorProductGroupMarginField;
import ctp.thostapi.CThostFtdcMMInstrumentCommissionRateField;
import ctp.thostapi.CThostFtdcMMOptionInstrCommRateField;
import ctp.thostapi.CThostFtdcNoticeField;
import ctp.thostapi.CThostFtdcNotifyQueryAccountField;
import ctp.thostapi.CThostFtdcOpenAccountField;
import ctp.thostapi.CThostFtdcOptionInstrCommRateField;
import ctp.thostapi.CThostFtdcOptionInstrTradeCostField;
import ctp.thostapi.CThostFtdcOptionSelfCloseActionField;
import ctp.thostapi.CThostFtdcOptionSelfCloseField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcParkedOrderActionField;
import ctp.thostapi.CThostFtdcParkedOrderField;
import ctp.thostapi.CThostFtdcProductExchRateField;
import ctp.thostapi.CThostFtdcProductField;
import ctp.thostapi.CThostFtdcProductGroupField;
import ctp.thostapi.CThostFtdcQueryCFMMCTradingAccountTokenField;
import ctp.thostapi.CThostFtdcQueryMaxOrderVolumeField;
import ctp.thostapi.CThostFtdcQuoteActionField;
import ctp.thostapi.CThostFtdcQuoteField;
import ctp.thostapi.CThostFtdcRemoveParkedOrderActionField;
import ctp.thostapi.CThostFtdcRemoveParkedOrderField;
import ctp.thostapi.CThostFtdcReqQueryAccountField;
import ctp.thostapi.CThostFtdcReqRepealField;
import ctp.thostapi.CThostFtdcReqTransferField;
import ctp.thostapi.CThostFtdcRspAuthenticateField;
import ctp.thostapi.CThostFtdcRspGenUserCaptchaField;
import ctp.thostapi.CThostFtdcRspGenUserTextField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspRepealField;
import ctp.thostapi.CThostFtdcRspTransferField;
import ctp.thostapi.CThostFtdcRspUserAuthMethodField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSecAgentACIDMapField;
import ctp.thostapi.CThostFtdcSecAgentCheckModeField;
import ctp.thostapi.CThostFtdcSecAgentTradeInfoField;
import ctp.thostapi.CThostFtdcSettlementInfoConfirmField;
import ctp.thostapi.CThostFtdcSettlementInfoField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcTradingAccountPasswordUpdateField;
import ctp.thostapi.CThostFtdcTradingCodeField;
import ctp.thostapi.CThostFtdcTradingNoticeField;
import ctp.thostapi.CThostFtdcTradingNoticeInfoField;
import ctp.thostapi.CThostFtdcTransferBankField;
import ctp.thostapi.CThostFtdcTransferSerialField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import ctp.thostapi.CThostFtdcUserPasswordUpdateField;
import io.mercury.common.annotation.NativeSpiImpl;

@NativeSpiImpl
public final class FtdcTraderSpi extends CThostFtdcTraderSpi {

    private final FtdcTraderCallback callback;

    FtdcTraderSpi(FtdcTraderCallback callback) {
        this.callback = callback;
    }

    /**
     * ///当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    @Override
    public void OnFrontConnected() {
        callback.fireFrontConnected();
    }

    /**
     * ///当客户端与交易后台通信连接断开时, 该方法被调用. 当发生这个情况后. API会自动重新连接, 客户端可不做处理.
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
        callback.fireFrontDisconnected(Reason);
    }

    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    @Override
    public void OnHeartBeatWarning(int TimeLapse) {
        callback.fireHeartBeatWarning(TimeLapse);
    }

    /**
     * ///客户端认证响应
     *
     * @param Field     CThostFtdcRspAuthenticateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspAuthenticate(CThostFtdcRspAuthenticateField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspAuthenticate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///登录请求响应
     *
     * @param Field     CThostFtdcRspUserLoginField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspUserLogin(CThostFtdcRspUserLoginField Field,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspUserLogin(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///登出请求响应
     *
     * @param Field     CThostFtdcUserLogoutField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspUserLogout(CThostFtdcUserLogoutField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspUserLogout(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///用户口令更新请求响应
     *
     * @param Field     CThostFtdcUserPasswordUpdateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspUserPasswordUpdate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///资金账户口令更新请求响应
     *
     * @param Field     CThostFtdcTradingAccountPasswordUpdateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField Field,
                                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspTradingAccountPasswordUpdate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///查询用户当前支持的认证模式的回复
     *
     * @param Field     CThostFtdcRspUserAuthMethodField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspUserAuthMethod(CThostFtdcRspUserAuthMethodField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspUserAuthMethod(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///获取图形验证码请求的回复
     *
     * @param Field     CThostFtdcRspGenUserCaptchaField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspGenUserCaptcha(CThostFtdcRspGenUserCaptchaField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspGenUserCaptcha(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///获取短信验证码请求的回复
     *
     * @param Field     CThostFtdcRspGenUserTextField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspGenUserText(CThostFtdcRspGenUserTextField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspGenUserText(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///报单录入请求响应
     *
     * @param Field     CThostFtdcInputOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspOrderInsert(CThostFtdcInputOrderField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspOrderInsert(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///预埋单录入请求响应
     *
     * @param Field     CThostFtdcParkedOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspParkedOrderInsert(CThostFtdcParkedOrderField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspParkedOrderInsert(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///预埋撤单录入请求响应
     *
     * @param Field     CThostFtdcParkedOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspParkedOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///报单操作请求响应
     *
     * @param Field     CThostFtdcInputOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspOrderAction(CThostFtdcInputOrderActionField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///查询最大报单数量响应
     *
     * @param Field     CThostFtdcQueryMaxOrderVolumeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQueryMaxOrderVolume(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///投资者结算结果确认响应
     *
     * @param Field     CThostFtdcSettlementInfoConfirmField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspSettlementInfoConfirm(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///删除预埋单响应
     *
     * @param Field     CThostFtdcRemoveParkedOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspRemoveParkedOrder(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///删除预埋撤单响应
     *
     * @param Field     CThostFtdcRemoveParkedOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspRemoveParkedOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///执行宣告录入请求响应
     *
     * @param Field     CThostFtdcInputExecOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspExecOrderInsert(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///执行宣告操作请求响应
     *
     * @param Field     CThostFtdcInputExecOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspExecOrderAction(CThostFtdcInputExecOrderActionField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspExecOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///询价录入请求响应
     *
     * @param Field     CThostFtdcInputForQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspForQuoteInsert(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///报价录入请求响应
     *
     * @param Field     CThostFtdcInputQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQuoteInsert(CThostFtdcInputQuoteField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQuoteInsert(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///报价操作请求响应
     *
     * @param Field     CThostFtdcInputQuoteActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQuoteAction(CThostFtdcInputQuoteActionField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQuoteAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///批量报单操作请求响应
     *
     * @param Field     CThostFtdcInputBatchOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspBatchOrderAction(CThostFtdcInputBatchOrderActionField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspBatchOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///期权自对冲录入请求响应
     *
     * @param Field     CThostFtdcInputOptionSelfCloseField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspOptionSelfCloseInsert(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///期权自对冲操作请求响应
     *
     * @param Field     CThostFtdcInputOptionSelfCloseActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspOptionSelfCloseAction(CThostFtdcInputOptionSelfCloseActionField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspOptionSelfCloseAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///申请组合录入请求响应
     *
     * @param Field     CThostFtdcInputCombActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspCombActionInsert(CThostFtdcInputCombActionField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspCombActionInsert(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询报单响应
     *
     * @param Field     CThostFtdcOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryOrder(CThostFtdcOrderField Field,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryOrder(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询成交响应
     *
     * @param Field     CThostFtdcTradeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryTrade(CThostFtdcTradeField Field, CThostFtdcRspInfoField RspInfo,
                              int RequestID, boolean IsLast) {
        callback.fireRspQryTrade(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询投资者持仓响应
     *
     * @param Field     CThostFtdcInvestorPositionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInvestorPosition(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询资金账户响应
     *
     * @param Field     CThostFtdcTradingAccountField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryTradingAccount(CThostFtdcTradingAccountField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryTradingAccount(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询投资者响应
     *
     * @param Field     CThostFtdcInvestorField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInvestor(CThostFtdcInvestorField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInvestor(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询交易编码响应
     *
     * @param Field     CThostFtdcTradingCodeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryTradingCode(CThostFtdcTradingCodeField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryTradingCode(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询合约保证金率响应
     *
     * @param Field     CThostFtdcInstrumentMarginRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInstrumentMarginRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询合约手续费率响应
     *
     * @param Field     CThostFtdcInstrumentCommissionRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInstrumentCommissionRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询交易所响应
     *
     * @param Field     CThostFtdcExchangeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryExchange(CThostFtdcExchangeField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryExchange(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询产品响应
     *
     * @param Field     CThostFtdcProductField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryProduct(CThostFtdcProductField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryProduct(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询合约响应
     *
     * @param Field     CThostFtdcInstrumentField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInstrument(CThostFtdcInstrumentField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInstrument(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询行情响应
     *
     * @param Field     CThostFtdcDepthMarketDataField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryDepthMarketData(CThostFtdcDepthMarketDataField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryDepthMarketData(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询投资者结算结果响应
     *
     * @param Field     CThostFtdcSettlementInfoField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQrySettlementInfo(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询转帐银行响应
     *
     * @param Field     CThostFtdcTransferBankField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryTransferBank(CThostFtdcTransferBankField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryTransferBank(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询投资者持仓明细响应
     *
     * @param Field     CThostFtdcInvestorPositionDetailField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInvestorPositionDetail(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询客户通知响应
     *
     * @param Field     CThostFtdcNoticeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryNotice(CThostFtdcNoticeField Field,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryNotice(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询结算信息确认响应
     *
     * @param Field     CThostFtdcSettlementInfoConfirmField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQrySettlementInfoConfirm(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询投资者持仓明细响应
     *
     * @param Field     CThostFtdcInvestorPositionCombineDetailField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField Field,
                                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInvestorPositionCombineDetail(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///查询保证金监管系统经纪公司资金账户密钥响应
     *
     * @param Field     CThostFtdcCFMMCTradingAccountKeyField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryCFMMCTradingAccountKey(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询仓单折抵信息响应
     *
     * @param Field     CThostFtdcEWarrantOffsetField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryEWarrantOffset(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询投资者品种/跨品种保证金响应
     *
     * @param Field     CThostFtdcInvestorProductGroupMarginField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInvestorProductGroupMargin(CThostFtdcInvestorProductGroupMarginField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInvestorProductGroupMargin(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询交易所保证金率响应
     *
     * @param Field     CThostFtdcExchangeMarginRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryExchangeMarginRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询交易所调整保证金率响应
     *
     * @param Field     CThostFtdcExchangeMarginRateAdjustField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryExchangeMarginRateAdjust(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询汇率响应
     *
     * @param Field     CThostFtdcExchangeRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryExchangeRate(CThostFtdcExchangeRateField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryExchangeRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询二级代理操作员银期权限响应
     *
     * @param Field     CThostFtdcSecAgentACIDMapField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQrySecAgentACIDMap(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询产品报价汇率
     *
     * @param Field     CThostFtdcProductExchRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryProductExchRate(CThostFtdcProductExchRateField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryProductExchRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询产品组
     *
     * @param Field     CThostFtdcProductGroupField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryProductGroup(CThostFtdcProductGroupField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryProductGroup(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询做市商合约手续费率响应
     *
     * @param Field     CThostFtdcMMInstrumentCommissionRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryMMInstrumentCommissionRate(CThostFtdcMMInstrumentCommissionRateField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryMMInstrumentCommissionRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询做市商期权合约手续费响应
     *
     * @param Field     CThostFtdcMMOptionInstrCommRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryMMOptionInstrCommRate(CThostFtdcMMOptionInstrCommRateField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryMMOptionInstrCommRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询报单手续费响应
     *
     * @param Field     CThostFtdcInstrumentOrderCommRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInstrumentOrderCommRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询资金账户响应
     *
     * @param Field     CThostFtdcTradingAccountField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQrySecAgentTradingAccount(CThostFtdcTradingAccountField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQrySecAgentTradingAccount(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询二级代理商资金校验模式响应
     *
     * @param Field     CThostFtdcSecAgentCheckModeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQrySecAgentCheckMode(CThostFtdcSecAgentCheckModeField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQrySecAgentCheckMode(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询二级代理商信息响应
     *
     * @param Field     CThostFtdcSecAgentTradeInfoField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQrySecAgentTradeInfo(CThostFtdcSecAgentTradeInfoField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQrySecAgentTradeInfo(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询期权交易成本响应
     *
     * @param Field     CThostFtdcOptionInstrTradeCostField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryOptionInstrTradeCost(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询期权合约手续费响应
     *
     * @param Field     CThostFtdcOptionInstrCommRateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryOptionInstrCommRate(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询执行宣告响应
     *
     * @param Field     CThostFtdcExecOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryExecOrder(CThostFtdcExecOrderField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryExecOrder(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询询价响应
     *
     * @param Field     CThostFtdcForQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryForQuote(CThostFtdcForQuoteField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryForQuote(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询报价响应
     *
     * @param Field     CThostFtdcQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryQuote(CThostFtdcQuoteField Field,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryQuote(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询期权自对冲响应
     *
     * @param Field     CThostFtdcOptionSelfCloseField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryOptionSelfClose(CThostFtdcOptionSelfCloseField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryOptionSelfClose(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询投资单元响应
     *
     * @param Field     CThostFtdcInvestUnitField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryInvestUnit(CThostFtdcInvestUnitField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryInvestUnit(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询组合合约安全系数响应
     *
     * @param Field     CThostFtdcCombInstrumentGuardField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryCombInstrumentGuard(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询申请组合响应
     *
     * @param Field     CThostFtdcCombActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryCombAction(CThostFtdcCombActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryCombAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询转帐流水响应
     *
     * @param Field     CThostFtdcTransferSerialField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryTransferSerial(CThostFtdcTransferSerialField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryTransferSerial(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询银期签约关系响应
     *
     * @param Field     CThostFtdcAccountregisterField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryAccountregister(CThostFtdcAccountregisterField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryAccountregister(Field, RspInfo, RequestID, IsLast);
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
        callback.fireRspError(RspInfo, RequestID, IsLast);
    }

    /**
     * ///报单通知
     *
     * @param Order CThostFtdcOrderField
     */
    @Override
    public void OnRtnOrder(CThostFtdcOrderField Order) {
        callback.fireRtnOrder(Order);
    }

    /**
     * ///成交通知
     *
     * @param Trade CThostFtdcTradeField
     */
    @Override
    public void OnRtnTrade(CThostFtdcTradeField Trade) {
        callback.fireRtnTrade(Trade);
    }

    /**
     * ///报单录入错误回报
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOrderInsert(CThostFtdcInputOrderField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnOrderInsert(Field, RspInfo);
    }

    /**
     * ///报单操作错误回报
     *
     * @param Field   CThostFtdcOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOrderAction(CThostFtdcOrderActionField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnOrderAction(Field, RspInfo);
    }

    /**
     * ///合约交易状态通知
     *
     * @param Field CThostFtdcInstrumentStatusField
     */
    @Override
    public void OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        callback.fireRtnInstrumentStatus(Field);
    }

    /**
     * ///交易所公告通知
     *
     * @param Field CThostFtdcBulletinField
     */
    @Override
    public void OnRtnBulletin(CThostFtdcBulletinField Field) {
        callback.fireRtnBulletin(Field);
    }

    /**
     * ///交易通知
     *
     * @param Field CThostFtdcTradingNoticeInfoField
     */
    @Override
    public void OnRtnTradingNotice(CThostFtdcTradingNoticeInfoField Field) {
        callback.fireRtnTradingNotice(Field);
    }

    /**
     * ///提示条件单校验错误
     *
     * @param Field CThostFtdcErrorConditionalOrderField
     */
    @Override
    public void OnRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField Field) {
        callback.fireRtnErrorConditionalOrder(Field);
    }

    /**
     * ///执行宣告通知
     *
     * @param Field CThostFtdcExecOrderField
     */
    @Override
    public void OnRtnExecOrder(CThostFtdcExecOrderField Field) {
        callback.fireRtnExecOrder(Field);
    }

    /**
     * ///执行宣告录入错误回报
     *
     * @param Field   CThostFtdcInputExecOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                        CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnExecOrderInsert(Field, RspInfo);
    }

    /**
     * ///执行宣告操作错误回报
     *
     * @param Field   CThostFtdcExecOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnExecOrderAction(CThostFtdcExecOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnExecOrderAction(Field, RspInfo);
    }

    /**
     * ///询价录入错误回报
     *
     * @param Field   CThostFtdcInputForQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                       CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnForQuoteInsert(Field, RspInfo);
    }

    /**
     * ///报价通知
     *
     * @param Field CThostFtdcQuoteField
     */
    @Override
    public void OnRtnQuote(CThostFtdcQuoteField Field) {
        callback.fireRtnQuote(Field);
    }

    /**
     * ///报价录入错误回报
     *
     * @param Field   CThostFtdcInputQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnQuoteInsert(CThostFtdcInputQuoteField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnQuoteInsert(Field, RspInfo);
    }

    /**
     * ///报价操作错误回报
     *
     * @param Field   CThostFtdcQuoteActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnQuoteAction(CThostFtdcQuoteActionField Field,
                                    CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnQuoteAction(Field, RspInfo);
    }

    /**
     * ///询价通知
     *
     * @param Field CThostFtdcForQuoteRspField
     */
    @Override
    public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField Field) {
        callback.fireRtnForQuoteRsp(Field);
    }

    /**
     * ///保证金监控中心用户令牌
     *
     * @param Field CThostFtdcCFMMCTradingAccountTokenField
     */
    @Override
    public void OnRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField Field) {
        callback.fireRtnCFMMCTradingAccountToken(Field);
    }

    /**
     * ///批量报单操作错误回报
     *
     * @param Field   CThostFtdcBatchOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField Field,
                                         CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnBatchOrderAction(Field, RspInfo);
    }

    /**
     * ///期权自对冲通知
     *
     * @param Field CThostFtdcOptionSelfCloseField
     */
    @Override
    public void OnRtnOptionSelfClose(CThostFtdcOptionSelfCloseField Field) {
        callback.fireRtnOptionSelfClose(Field);
    }

    /**
     * ///期权自对冲录入错误回报
     *
     * @param Field   CThostFtdcInputOptionSelfCloseField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                              CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnOptionSelfCloseInsert(Field, RspInfo);
    }

    /**
     * ///期权自对冲操作错误回报
     *
     * @param Field   CThostFtdcOptionSelfCloseActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnOptionSelfCloseAction(CThostFtdcOptionSelfCloseActionField Field,
                                              CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnOptionSelfCloseAction(Field, RspInfo);
    }

    /**
     * ///申请组合通知
     *
     * @param Field CThostFtdcCombActionField
     */
    @Override
    public void OnRtnCombAction(CThostFtdcCombActionField Field) {
        callback.fireRtnCombAction(Field);
    }

    /**
     * ///申请组合录入错误回报
     *
     * @param Field   CThostFtdcInputCombActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnCombActionInsert(CThostFtdcInputCombActionField Field,
                                         CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnCombActionInsert(Field, RspInfo);
    }

    /**
     * ///请求查询签约银行响应
     *
     * @param Field     CThostFtdcContractBankField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryContractBank(CThostFtdcContractBankField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryContractBank(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询预埋单响应
     *
     * @param Field     CThostFtdcParkedOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryParkedOrder(CThostFtdcParkedOrderField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryParkedOrder(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询预埋撤单响应
     *
     * @param Field     CThostFtdcParkedOrderActionField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryParkedOrderAction(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询交易通知响应
     *
     * @param Field     CThostFtdcTradingNoticeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryTradingNotice(CThostFtdcTradingNoticeField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryTradingNotice(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询经纪公司交易参数响应
     *
     * @param Field     CThostFtdcBrokerTradingParamsField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryBrokerTradingParams(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询经纪公司交易算法响应
     *
     * @param Field     CThostFtdcBrokerTradingAlgosField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQryBrokerTradingAlgos(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///请求查询监控中心用户令牌
     *
     * @param Field     CThostFtdcQueryCFMMCTradingAccountTokenField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQueryCFMMCTradingAccountToken(CThostFtdcQueryCFMMCTradingAccountTokenField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQueryCFMMCTradingAccountToken(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///银行发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromBankToFutureByBank(CThostFtdcRspTransferField Field) {
        callback.fireRtnFromBankToFutureByBank(Field);
    }

    /**
     * ///银行发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromFutureToBankByBank(CThostFtdcRspTransferField Field) {
        callback.fireRtnFromFutureToBankByBank(Field);
    }

    /**
     * ///银行发起冲正银行转期货通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField Field) {
        callback.fireRtnRepealFromBankToFutureByBank(Field);
    }

    /**
     * ///银行发起冲正期货转银行通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField Field) {
        callback.fireRtnRepealFromFutureToBankByBank(Field);
    }

    /**
     * ///期货发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromBankToFutureByFuture(CThostFtdcRspTransferField Field) {
        callback.fireRtnFromBankToFutureByFuture(Field);
    }

    /**
     * ///期货发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void OnRtnFromFutureToBankByFuture(CThostFtdcRspTransferField Field) {
        callback.fireRtnFromFutureToBankByFuture(Field);
    }

    /**
     * ///系统运行时期货端手工发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField Field) {
        callback.fireRtnRepealFromBankToFutureByFutureManual(Field);
    }

    /**
     * ///系统运行时期货端手工发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField Field) {
        callback.fireRtnRepealFromFutureToBankByFutureManual(Field);
    }

    /**
     * ///期货发起查询银行余额通知
     *
     * @param Field CThostFtdcNotifyQueryAccountField
     */
    @Override
    public void OnRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField Field) {
        callback.fireRtnQueryBankBalanceByFuture(Field);
    }

    /**
     * ///期货发起银行资金转期货错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                             CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnBankToFutureByFuture(Field, RspInfo);
    }

    /**
     * ///期货发起期货资金转银行错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                             CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnFutureToBankByFuture(Field, RspInfo);
    }

    /**
     * ///系统运行时期货端手工发起冲正银行转期货错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField Field,
                                                         CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnRepealBankToFutureByFutureManual(Field, RspInfo);
    }

    /**
     * ///系统运行时期货端手工发起冲正期货转银行错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField Field,
                                                         CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnRepealFutureToBankByFutureManual(Field, RspInfo);
    }

    /**
     * ///期货发起查询银行余额错误回报
     *
     * @param Field   CThostFtdcReqQueryAccountField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void OnErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField Field,
                                                 CThostFtdcRspInfoField RspInfo) {
        callback.fireErrRtnQueryBankBalanceByFuture(Field, RspInfo);
    }

    /**
     * ///期货发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField Field) {
        callback.fireRtnRepealFromBankToFutureByFuture(Field);
    }

    /**
     * ///期货发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void OnRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField Field) {
        callback.fireRtnRepealFromFutureToBankByFuture(Field);
    }

    /**
     * ///期货发起银行资金转期货应答
     *
     * @param Field     CThostFtdcReqTransferField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspFromBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspFromBankToFutureByFuture(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///期货发起期货资金转银行应答
     *
     * @param Field     CThostFtdcReqTransferField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspFromFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspFromFutureToBankByFuture(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///期货发起查询银行余额应答
     *
     * @param Field     CThostFtdcReqQueryAccountField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void OnRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        callback.fireRspQueryBankAccountMoneyByFuture(Field, RspInfo, RequestID, IsLast);
    }

    /**
     * ///银行发起银期开户通知
     *
     * @param Field CThostFtdcOpenAccountField
     */
    @Override
    public void OnRtnOpenAccountByBank(CThostFtdcOpenAccountField Field) {
        callback.fireRtnOpenAccountByBank(Field);
    }

    /**
     * ///银行发起银期销户通知
     *
     * @param Field CThostFtdcCancelAccountField
     */
    @Override
    public void OnRtnCancelAccountByBank(CThostFtdcCancelAccountField Field) {
        callback.fireRtnCancelAccountByBank(Field);
    }

    /**
     * ///银行发起变更银行账号通知
     *
     * @param Field CThostFtdcChangeAccountField
     */
    @Override
    public void OnRtnChangeAccountByBank(CThostFtdcChangeAccountField Field) {
        callback.fireRtnChangeAccountByBank(Field);
    }

}