package io.cygnuxltb.adaptor.ctp.gateway.event.listener;

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
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcTradingAccountPasswordUpdateField;
import ctp.thostapi.CThostFtdcTradingCodeField;
import ctp.thostapi.CThostFtdcTradingNoticeField;
import ctp.thostapi.CThostFtdcTradingNoticeInfoField;
import ctp.thostapi.CThostFtdcTransferBankField;
import ctp.thostapi.CThostFtdcTransferSerialField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import ctp.thostapi.CThostFtdcUserPasswordUpdateField;

public abstract class BaseFtdcTraderListener implements FtdcTraderListener {

    protected BaseFtdcTraderListener() {
    }

    /**
     * ///当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    @Override
    public void fireFrontConnected() {
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
     * ///客户端认证响应
     *
     * @param Field     CThostFtdcRspAuthenticateField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    @Override
    public void fireRspAuthenticate(CThostFtdcRspAuthenticateField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspUserLogin(CThostFtdcRspUserLoginField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspUserLogout(CThostFtdcUserLogoutField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField Field,
                                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspUserAuthMethod(CThostFtdcRspUserAuthMethodField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspGenUserCaptcha(CThostFtdcRspGenUserCaptchaField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspGenUserText(CThostFtdcRspGenUserTextField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspOrderInsert(CThostFtdcInputOrderField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspParkedOrderInsert(CThostFtdcParkedOrderField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspOrderAction(CThostFtdcInputOrderActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspExecOrderAction(CThostFtdcInputExecOrderActionField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQuoteInsert(CThostFtdcInputQuoteField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQuoteAction(CThostFtdcInputQuoteActionField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspBatchOrderAction(CThostFtdcInputBatchOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspOptionSelfCloseAction(CThostFtdcInputOptionSelfCloseActionField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspCombActionInsert(CThostFtdcInputCombActionField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryOrder(CThostFtdcOrderField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryTrade(CThostFtdcTradeField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInvestorPosition(CThostFtdcInvestorPositionField Field,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryTradingAccount(CThostFtdcTradingAccountField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInvestor(CThostFtdcInvestorField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryTradingCode(CThostFtdcTradingCodeField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryExchange(CThostFtdcExchangeField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryProduct(CThostFtdcProductField Field,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInstrument(CThostFtdcInstrumentField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryDepthMarketData(CThostFtdcDepthMarketDataField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQrySettlementInfo(CThostFtdcSettlementInfoField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryTransferBank(CThostFtdcTransferBankField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryNotice(CThostFtdcNoticeField Field,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField Field,
                                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInvestorProductGroupMargin(CThostFtdcInvestorProductGroupMarginField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryExchangeRate(CThostFtdcExchangeRateField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryProductExchRate(CThostFtdcProductExchRateField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryProductGroup(CThostFtdcProductGroupField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryMMInstrumentCommissionRate(CThostFtdcMMInstrumentCommissionRateField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryMMOptionInstrCommRate(CThostFtdcMMOptionInstrCommRateField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField Field,
                                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQrySecAgentTradingAccount(CThostFtdcTradingAccountField Field,
                                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQrySecAgentCheckMode(CThostFtdcSecAgentCheckModeField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQrySecAgentTradeInfo(CThostFtdcSecAgentTradeInfoField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField Field,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryExecOrder(CThostFtdcExecOrderField Field,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryForQuote(CThostFtdcForQuoteField Field,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryQuote(CThostFtdcQuoteField Field,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryOptionSelfClose(CThostFtdcOptionSelfCloseField Field,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryInvestUnit(CThostFtdcInvestUnitField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryCombAction(CThostFtdcCombActionField Field,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryTransferSerial(CThostFtdcTransferSerialField Field,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryAccountregister(CThostFtdcAccountregisterField Field,
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
     * ///报单通知
     *
     * @param Order CThostFtdcOrderField
     */
    @Override
    public void fireRtnOrder(CThostFtdcOrderField Order) {
    }


    /**
     * ///成交通知
     *
     * @param Trade CThostFtdcTradeField
     */
    @Override
    public void fireRtnTrade(CThostFtdcTradeField Trade) {
    }


    /**
     * ///报单录入错误回报
     *
     * @param Field   CThostFtdcInputOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderInsert(CThostFtdcInputOrderField Field,
                                      CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///报单操作错误回报
     *
     * @param Field   CThostFtdcOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOrderAction(CThostFtdcOrderActionField Field,
                                      CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///合约交易状态通知
     *
     * @param Field CThostFtdcInstrumentStatusField
     */
    @Override
    public void fireRtnInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
    }


    /**
     * ///交易所公告通知
     *
     * @param Field CThostFtdcBulletinField
     */
    @Override
    public void fireRtnBulletin(CThostFtdcBulletinField Field) {
    }


    /**
     * ///交易通知
     *
     * @param Field CThostFtdcTradingNoticeInfoField
     */
    @Override
    public void fireRtnTradingNotice(CThostFtdcTradingNoticeInfoField Field) {
    }


    /**
     * ///提示条件单校验错误
     *
     * @param Field CThostFtdcErrorConditionalOrderField
     */
    @Override
    public void fireRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField Field) {
    }


    /**
     * ///执行宣告通知
     *
     * @param Field CThostFtdcExecOrderField
     */
    @Override
    public void fireRtnExecOrder(CThostFtdcExecOrderField Field) {
    }


    /**
     * ///执行宣告录入错误回报
     *
     * @param Field   CThostFtdcInputExecOrderField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnExecOrderInsert(CThostFtdcInputExecOrderField Field,
                                          CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///执行宣告操作错误回报
     *
     * @param Field   CThostFtdcExecOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnExecOrderAction(CThostFtdcExecOrderActionField Field,
                                          CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///询价录入错误回报
     *
     * @param Field   CThostFtdcInputForQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnForQuoteInsert(CThostFtdcInputForQuoteField Field,
                                         CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///报价通知
     *
     * @param Field CThostFtdcQuoteField
     */
    @Override
    public void fireRtnQuote(CThostFtdcQuoteField Field) {
    }


    /**
     * ///报价录入错误回报
     *
     * @param Field   CThostFtdcInputQuoteField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnQuoteInsert(CThostFtdcInputQuoteField Field,
                                      CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///报价操作错误回报
     *
     * @param Field   CThostFtdcQuoteActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnQuoteAction(CThostFtdcQuoteActionField Field,
                                      CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///询价通知
     *
     * @param Field CThostFtdcForQuoteRspField
     */
    @Override
    public void fireRtnForQuoteRsp(CThostFtdcForQuoteRspField Field) {
    }


    /**
     * ///保证金监控中心用户令牌
     *
     * @param Field CThostFtdcCFMMCTradingAccountTokenField
     */
    @Override
    public void fireRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField Field) {
    }


    /**
     * ///批量报单操作错误回报
     *
     * @param Field   CThostFtdcBatchOrderActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField Field,
                                           CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///期权自对冲通知
     *
     * @param Field CThostFtdcOptionSelfCloseField
     */
    @Override
    public void fireRtnOptionSelfClose(CThostFtdcOptionSelfCloseField Field) {
    }


    /**
     * ///期权自对冲录入错误回报
     *
     * @param Field   CThostFtdcInputOptionSelfCloseField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField Field,
                                                CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///期权自对冲操作错误回报
     *
     * @param Field   CThostFtdcOptionSelfCloseActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnOptionSelfCloseAction(CThostFtdcOptionSelfCloseActionField Field,
                                                CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///申请组合通知
     *
     * @param Field CThostFtdcCombActionField
     */
    @Override
    public void fireRtnCombAction(CThostFtdcCombActionField Field) {
    }


    /**
     * ///申请组合录入错误回报
     *
     * @param Field   CThostFtdcInputCombActionField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnCombActionInsert(CThostFtdcInputCombActionField Field,
                                           CThostFtdcRspInfoField RspInfo) {
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
    public void fireRspQryContractBank(CThostFtdcContractBankField Field,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryParkedOrder(CThostFtdcParkedOrderField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryParkedOrderAction(CThostFtdcParkedOrderActionField Field,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryTradingNotice(CThostFtdcTradingNoticeField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField Field,
                                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField Field,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQueryCFMMCTradingAccountToken(CThostFtdcQueryCFMMCTradingAccountTokenField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
    }


    /**
     * ///银行发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromBankToFutureByBank(CThostFtdcRspTransferField Field) {
    }


    /**
     * ///银行发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromFutureToBankByBank(CThostFtdcRspTransferField Field) {
    }


    /**
     * ///银行发起冲正银行转期货通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField Field) {
    }


    /**
     * ///银行发起冲正期货转银行通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField Field) {
    }


    /**
     * ///期货发起银行资金转期货通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromBankToFutureByFuture(CThostFtdcRspTransferField Field) {
    }


    /**
     * ///期货发起期货资金转银行通知
     *
     * @param Field CThostFtdcRspTransferField
     */
    @Override
    public void fireRtnFromFutureToBankByFuture(CThostFtdcRspTransferField Field) {
    }


    /**
     * ///系统运行时期货端手工发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField Field) {
    }


    /**
     * ///系统运行时期货端手工发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField Field) {
    }


    /**
     * ///期货发起查询银行余额通知
     *
     * @param Field CThostFtdcNotifyQueryAccountField
     */
    @Override
    public void fireRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField Field) {
    }


    /**
     * ///期货发起银行资金转期货错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                               CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///期货发起期货资金转银行错误回报
     *
     * @param Field   CThostFtdcReqTransferField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                               CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///系统运行时期货端手工发起冲正银行转期货错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField Field,
                                                           CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///系统运行时期货端手工发起冲正期货转银行错误回报
     *
     * @param Field   CThostFtdcReqRepealField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField Field,
                                                           CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///期货发起查询银行余额错误回报
     *
     * @param Field   CThostFtdcReqQueryAccountField
     * @param RspInfo CThostFtdcRspInfoField
     */
    @Override
    public void fireErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField Field,
                                                   CThostFtdcRspInfoField RspInfo) {
    }


    /**
     * ///期货发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField Field) {
    }


    /**
     * ///期货发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param Field CThostFtdcRspRepealField
     */
    @Override
    public void fireRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField Field) {
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
    public void fireRspFromBankToFutureByFuture(CThostFtdcReqTransferField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspFromFutureToBankByFuture(CThostFtdcReqTransferField Field,
                                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
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
    public void fireRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
    }


    /**
     * ///银行发起银期开户通知
     *
     * @param Field CThostFtdcOpenAccountField
     */
    @Override
    public void fireRtnOpenAccountByBank(CThostFtdcOpenAccountField Field) {
    }


    /**
     * ///银行发起银期销户通知
     *
     * @param Field CThostFtdcCancelAccountField
     */
    @Override
    public void fireRtnCancelAccountByBank(CThostFtdcCancelAccountField Field) {
    }


    /**
     * ///银行发起变更银行账号通知
     *
     * @param Field CThostFtdcChangeAccountField
     */
    @Override
    public void fireRtnChangeAccountByBank(CThostFtdcChangeAccountField Field) {
    }

}