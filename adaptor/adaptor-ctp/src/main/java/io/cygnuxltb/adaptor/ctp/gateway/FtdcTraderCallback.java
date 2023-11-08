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
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcTradingAccountPasswordUpdateField;
import ctp.thostapi.CThostFtdcTradingCodeField;
import ctp.thostapi.CThostFtdcTradingNoticeField;
import ctp.thostapi.CThostFtdcTradingNoticeInfoField;
import ctp.thostapi.CThostFtdcTransferBankField;
import ctp.thostapi.CThostFtdcTransferSerialField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import ctp.thostapi.CThostFtdcUserPasswordUpdateField;

public interface FtdcTraderCallback {

    /**
     * ///当客户端与交易后台建立起通信连接时(还未登录前), 该方法被调用.
     */
    void onFrontConnected();


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
    void onFrontDisconnected(int Reason);


    /**
     * ///心跳超时警告. 当长时间未收到报文时, 该方法被调用.
     *
     * @param TimeLapse 距离上次接收报文的时间
     */
    void onHeartBeatWarning(int TimeLapse);


    /**
     * ///客户端认证响应
     *
     * @param RspAuthenticate CThostFtdcRspAuthenticateField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspAuthenticate(CThostFtdcRspAuthenticateField RspAuthenticate,
                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///登录请求响应
     *
     * @param RspUserLogin CThostFtdcRspUserLoginField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    void onRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///登出请求响应
     *
     * @param UserLogout CThostFtdcUserLogoutField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    void onRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///用户口令更新请求响应
     *
     * @param UserPasswordUpdate CThostFtdcUserPasswordUpdateField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    void onRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField UserPasswordUpdate,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///资金账户口令更新请求响应
     *
     * @param TradingAccountPasswordUpdate CThostFtdcTradingAccountPasswordUpdateField
     * @param RspInfo                      CThostFtdcRspInfoField
     * @param RequestID                    int
     * @param IsLast                       boolean
     */
    void onRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField TradingAccountPasswordUpdate,
                                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///查询用户当前支持的认证模式的回复
     *
     * @param RspUserAuthMethod CThostFtdcRspUserAuthMethodField
     * @param RspInfo           CThostFtdcRspInfoField
     * @param RequestID         int
     * @param IsLast            boolean
     */
    void onRspUserAuthMethod(CThostFtdcRspUserAuthMethodField RspUserAuthMethod,
                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///获取图形验证码请求的回复
     *
     * @param RspGenUserCaptcha CThostFtdcRspGenUserCaptchaField
     * @param RspInfo           CThostFtdcRspInfoField
     * @param RequestID         int
     * @param IsLast            boolean
     */
    void onRspGenUserCaptcha(CThostFtdcRspGenUserCaptchaField RspGenUserCaptcha,
                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///获取短信验证码请求的回复
     *
     * @param RspGenUserText CThostFtdcRspGenUserTextField
     * @param RspInfo        CThostFtdcRspInfoField
     * @param RequestID      int
     * @param IsLast         boolean
     */
    void onRspGenUserText(CThostFtdcRspGenUserTextField RspGenUserText,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///报单录入请求响应
     *
     * @param InputOrder CThostFtdcInputOrderField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    void onRspOrderInsert(CThostFtdcInputOrderField InputOrder,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///预埋单录入请求响应
     *
     * @param ParkedOrder CThostFtdcParkedOrderField
     * @param RspInfo     CThostFtdcRspInfoField
     * @param RequestID   int
     * @param IsLast      boolean
     */
    void onRspParkedOrderInsert(CThostFtdcParkedOrderField ParkedOrder,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///预埋撤单录入请求响应
     *
     * @param ParkedOrderAction CThostFtdcParkedOrderActionField
     * @param RspInfo           CThostFtdcRspInfoField
     * @param RequestID         int
     * @param IsLast            boolean
     */
    void onRspParkedOrderAction(CThostFtdcParkedOrderActionField ParkedOrderAction,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///报单操作请求响应
     *
     * @param InputOrderAction CThostFtdcInputOrderActionField
     * @param RspInfo          CThostFtdcRspInfoField
     * @param RequestID        int
     * @param IsLast           boolean
     */
    void onRspOrderAction(CThostFtdcInputOrderActionField InputOrderAction,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///查询最大报单数量响应
     *
     * @param QueryMaxOrderVolume CThostFtdcQueryMaxOrderVolumeField
     * @param RspInfo             CThostFtdcRspInfoField
     * @param RequestID           int
     * @param IsLast              boolean
     */
    void onRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField QueryMaxOrderVolume,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///投资者结算结果确认响应
     *
     * @param SettlementInfoConfirm CThostFtdcSettlementInfoConfirmField
     * @param RspInfo               CThostFtdcRspInfoField
     * @param RequestID             int
     * @param IsLast                boolean
     */
    void onRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField SettlementInfoConfirm,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///删除预埋单响应
     *
     * @param RemoveParkedOrder CThostFtdcRemoveParkedOrderField
     * @param RspInfo           CThostFtdcRspInfoField
     * @param RequestID         int
     * @param IsLast            boolean
     */
    void onRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField RemoveParkedOrder,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///删除预埋撤单响应
     *
     * @param RemoveParkedOrderAction CThostFtdcRemoveParkedOrderActionField
     * @param RspInfo                 CThostFtdcRspInfoField
     * @param RequestID               int
     * @param IsLast                  boolean
     */
    void onRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField RemoveParkedOrderAction,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///执行宣告录入请求响应
     *
     * @param InputExecOrder CThostFtdcInputExecOrderField
     * @param RspInfo        CThostFtdcRspInfoField
     * @param RequestID      int
     * @param IsLast         boolean
     */
    void onRspExecOrderInsert(CThostFtdcInputExecOrderField InputExecOrder,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///执行宣告操作请求响应
     *
     * @param InputExecOrderAction CThostFtdcInputExecOrderActionField
     * @param RspInfo              CThostFtdcRspInfoField
     * @param RequestID            int
     * @param IsLast               boolean
     */
    void onRspExecOrderAction(CThostFtdcInputExecOrderActionField InputExecOrderAction,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///询价录入请求响应
     *
     * @param InputForQuote CThostFtdcInputForQuoteField
     * @param RspInfo       CThostFtdcRspInfoField
     * @param RequestID     int
     * @param IsLast        boolean
     */
    void onRspForQuoteInsert(CThostFtdcInputForQuoteField InputForQuote,
                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///报价录入请求响应
     *
     * @param InputQuote CThostFtdcInputQuoteField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    void onRspQuoteInsert(CThostFtdcInputQuoteField InputQuote,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///报价操作请求响应
     *
     * @param InputQuoteAction CThostFtdcInputQuoteActionField
     * @param RspInfo          CThostFtdcRspInfoField
     * @param RequestID        int
     * @param IsLast           boolean
     */
    void onRspQuoteAction(CThostFtdcInputQuoteActionField InputQuoteAction,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///批量报单操作请求响应
     *
     * @param InputBatchOrderAction CThostFtdcInputBatchOrderActionField
     * @param RspInfo               CThostFtdcRspInfoField
     * @param RequestID             int
     * @param IsLast                boolean
     */
    void onRspBatchOrderAction(CThostFtdcInputBatchOrderActionField InputBatchOrderAction,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///期权自对冲录入请求响应
     *
     * @param InputOptionSelfClose CThostFtdcInputOptionSelfCloseField
     * @param RspInfo              CThostFtdcRspInfoField
     * @param RequestID            int
     * @param IsLast               boolean
     */
    void onRspOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField InputOptionSelfClose,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///期权自对冲操作请求响应
     *
     * @param InputOptionSelfCloseAction CThostFtdcInputOptionSelfCloseActionField
     * @param RspInfo                    CThostFtdcRspInfoField
     * @param RequestID                  int
     * @param IsLast                     boolean
     */
    void onRspOptionSelfCloseAction(CThostFtdcInputOptionSelfCloseActionField InputOptionSelfCloseAction,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///申请组合录入请求响应
     *
     * @param InputCombAction CThostFtdcInputCombActionField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspCombActionInsert(CThostFtdcInputCombActionField InputCombAction,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询报单响应
     *
     * @param Order     CThostFtdcOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryOrder(CThostFtdcOrderField Order,
                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询成交响应
     *
     * @param Trade     CThostFtdcTradeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryTrade(CThostFtdcTradeField Trade, CThostFtdcRspInfoField RspInfo,
                       int RequestID, boolean IsLast);


    /**
     * ///请求查询投资者持仓响应
     *
     * @param InvestorPosition CThostFtdcInvestorPositionField
     * @param RspInfo          CThostFtdcRspInfoField
     * @param RequestID        int
     * @param IsLast           boolean
     */
    void onRspQryInvestorPosition(CThostFtdcInvestorPositionField InvestorPosition,
                                  CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询资金账户响应
     *
     * @param TradingAccount CThostFtdcTradingAccountField
     * @param RspInfo        CThostFtdcRspInfoField
     * @param RequestID      int
     * @param IsLast         boolean
     */
    void onRspQryTradingAccount(CThostFtdcTradingAccountField TradingAccount,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询投资者响应
     *
     * @param Investor  CThostFtdcInvestorField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryInvestor(CThostFtdcInvestorField Investor,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询交易编码响应
     *
     * @param TradingCode CThostFtdcTradingCodeField
     * @param RspInfo     CThostFtdcRspInfoField
     * @param RequestID   int
     * @param IsLast      boolean
     */
    void onRspQryTradingCode(CThostFtdcTradingCodeField TradingCode,
                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询合约保证金率响应
     *
     * @param InstrumentMarginRate CThostFtdcInstrumentMarginRateField
     * @param RspInfo              CThostFtdcRspInfoField
     * @param RequestID            int
     * @param IsLast               boolean
     */
    void onRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField InstrumentMarginRate,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询合约手续费率响应
     *
     * @param InstrumentCommissionRate CThostFtdcInstrumentCommissionRateField
     * @param RspInfo                  CThostFtdcRspInfoField
     * @param RequestID                int
     * @param IsLast                   boolean
     */
    void onRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField InstrumentCommissionRate,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询交易所响应
     *
     * @param Exchange  CThostFtdcExchangeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryExchange(CThostFtdcExchangeField Exchange,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询产品响应
     *
     * @param Product   CThostFtdcProductField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryProduct(CThostFtdcProductField Product,
                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询合约响应
     *
     * @param Instrument CThostFtdcInstrumentField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    void onRspQryInstrument(CThostFtdcInstrumentField Instrument,
                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询行情响应
     *
     * @param DepthMarketData CThostFtdcDepthMarketDataField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspQryDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketData,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询投资者结算结果响应
     *
     * @param SettlementInfo CThostFtdcSettlementInfoField
     * @param RspInfo        CThostFtdcRspInfoField
     * @param RequestID      int
     * @param IsLast         boolean
     */
    void onRspQrySettlementInfo(CThostFtdcSettlementInfoField SettlementInfo,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询转帐银行响应
     *
     * @param TransferBank CThostFtdcTransferBankField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
   void onRspQryTransferBank(CThostFtdcTransferBankField TransferBank,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询投资者持仓明细响应
     *
     * @param InvestorPositionDetail CThostFtdcInvestorPositionDetailField
     * @param RspInfo                CThostFtdcRspInfoField
     * @param RequestID              int
     * @param IsLast                 boolean
     */
    void onRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField InvestorPositionDetail,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询客户通知响应
     *
     * @param Notice    CThostFtdcNoticeField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryNotice(CThostFtdcNoticeField Notice,
                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询结算信息确认响应
     *
     * @param SettlementInfoConfirm CThostFtdcSettlementInfoConfirmField
     * @param RspInfo               CThostFtdcRspInfoField
     * @param RequestID             int
     * @param IsLast                boolean
     */
    void onRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField SettlementInfoConfirm,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询投资者持仓明细响应
     *
     * @param InvestorPositionCombineDetail CThostFtdcInvestorPositionCombineDetailField
     * @param RspInfo                       CThostFtdcRspInfoField
     * @param RequestID                     int
     * @param IsLast                        boolean
     */
    void onRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField InvestorPositionCombineDetail,
                                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///查询保证金监管系统经纪公司资金账户密钥响应
     *
     * @param CFMMCTradingAccountKey CThostFtdcCFMMCTradingAccountKeyField
     * @param RspInfo                CThostFtdcRspInfoField
     * @param RequestID              int
     * @param IsLast                 boolean
     */
    void onRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField CFMMCTradingAccountKey,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询仓单折抵信息响应
     *
     * @param EWarrantOffset CThostFtdcEWarrantOffsetField
     * @param RspInfo        CThostFtdcRspInfoField
     * @param RequestID      int
     * @param IsLast         boolean
     */
    void onRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField EWarrantOffset,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询投资者品种/跨品种保证金响应
     *
     * @param InvestorProductGroupMargin CThostFtdcInvestorProductGroupMarginField
     * @param RspInfo                    CThostFtdcRspInfoField
     * @param RequestID                  int
     * @param IsLast                     boolean
     */
    void onRspQryInvestorProductGroupMargin(CThostFtdcInvestorProductGroupMarginField InvestorProductGroupMargin,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询交易所保证金率响应
     *
     * @param ExchangeMarginRate CThostFtdcExchangeMarginRateField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    void onRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField ExchangeMarginRate,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询交易所调整保证金率响应
     *
     * @param ExchangeMarginRateAdjust CThostFtdcExchangeMarginRateAdjustField
     * @param RspInfo                  CThostFtdcRspInfoField
     * @param RequestID                int
     * @param IsLast                   boolean
     */
    void onRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField ExchangeMarginRateAdjust,
                                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询汇率响应
     *
     * @param ExchangeRate CThostFtdcExchangeRateField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    void onRspQryExchangeRate(CThostFtdcExchangeRateField ExchangeRate,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询二级代理操作员银期权限响应
     *
     * @param SecAgentACIDMap CThostFtdcSecAgentACIDMapField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField SecAgentACIDMap,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询产品报价汇率
     *
     * @param ProductExchRate CThostFtdcProductExchRateField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspQryProductExchRate(CThostFtdcProductExchRateField ProductExchRate,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询产品组
     *
     * @param ProductGroup CThostFtdcProductGroupField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    void onRspQryProductGroup(CThostFtdcProductGroupField ProductGroup,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询做市商合约手续费率响应
     *
     * @param MMInstrumentCommissionRate CThostFtdcMMInstrumentCommissionRateField
     * @param RspInfo                    CThostFtdcRspInfoField
     * @param RequestID                  int
     * @param IsLast                     boolean
     */
    void onRspQryMMInstrumentCommissionRate(CThostFtdcMMInstrumentCommissionRateField MMInstrumentCommissionRate,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询做市商期权合约手续费响应
     *
     * @param MMOptionInstrCommRate CThostFtdcMMOptionInstrCommRateField
     * @param RspInfo               CThostFtdcRspInfoField
     * @param RequestID             int
     * @param IsLast                boolean
     */
    void onRspQryMMOptionInstrCommRate(CThostFtdcMMOptionInstrCommRateField MMOptionInstrCommRate,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询报单手续费响应
     *
     * @param InstrumentOrderCommRate CThostFtdcInstrumentOrderCommRateField
     * @param RspInfo                 CThostFtdcRspInfoField
     * @param RequestID               int
     * @param IsLast                  boolean
     */
    void onRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField InstrumentOrderCommRate,
                                         CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询资金账户响应
     *
     * @param TradingAccount CThostFtdcTradingAccountField
     * @param RspInfo        CThostFtdcRspInfoField
     * @param RequestID      int
     * @param IsLast         boolean
     */
    void onRspQrySecAgentTradingAccount(CThostFtdcTradingAccountField TradingAccount,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询二级代理商资金校验模式响应
     *
     * @param SecAgentCheckMode CThostFtdcSecAgentCheckModeField
     * @param RspInfo           CThostFtdcRspInfoField
     * @param RequestID         int
     * @param IsLast            boolean
     */
    void onRspQrySecAgentCheckMode(CThostFtdcSecAgentCheckModeField SecAgentCheckMode,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询二级代理商信息响应
     *
     * @param SecAgentTradeInfo CThostFtdcSecAgentTradeInfoField
     * @param RspInfo           CThostFtdcRspInfoField
     * @param RequestID         int
     * @param IsLast            boolean
     */
    void onRspQrySecAgentTradeInfo(CThostFtdcSecAgentTradeInfoField SecAgentTradeInfo,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询期权交易成本响应
     *
     * @param OptionInstrTradeCost CThostFtdcOptionInstrTradeCostField
     * @param RspInfo              CThostFtdcRspInfoField
     * @param RequestID            int
     * @param IsLast               boolean
     */
    void onRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField OptionInstrTradeCost,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询期权合约手续费响应
     *
     * @param OptionInstrCommRate CThostFtdcOptionInstrCommRateField
     * @param RspInfo             CThostFtdcRspInfoField
     * @param RequestID           int
     * @param IsLast              boolean
     */
    void onRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField OptionInstrCommRate,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询执行宣告响应
     *
     * @param ExecOrder CThostFtdcExecOrderField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryExecOrder(CThostFtdcExecOrderField ExecOrder,
                           CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询询价响应
     *
     * @param ForQuote  CThostFtdcForQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryForQuote(CThostFtdcForQuoteField ForQuote,
                          CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询报价响应
     *
     * @param Quote     CThostFtdcQuoteField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspQryQuote(CThostFtdcQuoteField Quote,
                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询期权自对冲响应
     *
     * @param OptionSelfClose CThostFtdcOptionSelfCloseField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspQryOptionSelfClose(CThostFtdcOptionSelfCloseField OptionSelfClose,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询投资单元响应
     *
     * @param InvestUnit CThostFtdcInvestUnitField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    void onRspQryInvestUnit(CThostFtdcInvestUnitField InvestUnit,
                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询组合合约安全系数响应
     *
     * @param CombInstrumentGuard CThostFtdcCombInstrumentGuardField
     * @param RspInfo             CThostFtdcRspInfoField
     * @param RequestID           int
     * @param IsLast              boolean
     */
    void onRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField CombInstrumentGuard,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询申请组合响应
     *
     * @param CombAction CThostFtdcCombActionField
     * @param RspInfo    CThostFtdcRspInfoField
     * @param RequestID  int
     * @param IsLast     boolean
     */
    void onRspQryCombAction(CThostFtdcCombActionField CombAction,
                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询转帐流水响应
     *
     * @param TransferSerial CThostFtdcTransferSerialField
     * @param RspInfo        CThostFtdcRspInfoField
     * @param RequestID      int
     * @param IsLast         boolean
     */
    void onRspQryTransferSerial(CThostFtdcTransferSerialField TransferSerial,
                                CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询银期签约关系响应
     *
     * @param Accountregister CThostFtdcAccountregisterField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspQryAccountregister(CThostFtdcAccountregisterField Accountregister,
                                 CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///错误应答
     *
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    void onRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///报单通知
     *
     * @param Order CThostFtdcOrderField
     */
    void onRtnOrder(CThostFtdcOrderField Order);


    /**
     * ///成交通知
     *
     * @param Trade CThostFtdcTradeField
     */
    void onRtnTrade(CThostFtdcTradeField Trade);


    /**
     * ///报单录入错误回报
     *
     * @param InputOrder CThostFtdcInputOrderField
     * @param RspInfo    CThostFtdcRspInfoField
     */
    void onErrRtnOrderInsert(CThostFtdcInputOrderField InputOrder,
                             CThostFtdcRspInfoField RspInfo);


    /**
     * ///报单操作错误回报
     *
     * @param OrderAction CThostFtdcOrderActionField
     * @param RspInfo     CThostFtdcRspInfoField
     */
    void onErrRtnOrderAction(CThostFtdcOrderActionField OrderAction,
                             CThostFtdcRspInfoField RspInfo);


    /**
     * ///合约交易状态通知
     *
     * @param InstrumentStatus CThostFtdcInstrumentStatusField
     */
    void onRtnInstrumentStatus(CThostFtdcInstrumentStatusField InstrumentStatus);


    /**
     * ///交易所公告通知
     *
     * @param Bulletin CThostFtdcBulletinField
     */
    void onRtnBulletin(CThostFtdcBulletinField Bulletin);


    /**
     * ///交易通知
     *
     * @param TradingNoticeInfo CThostFtdcTradingNoticeInfoField
     */
    void onRtnTradingNotice(CThostFtdcTradingNoticeInfoField TradingNoticeInfo);


    /**
     * ///提示条件单校验错误
     *
     * @param ErrorConditionalOrder CThostFtdcErrorConditionalOrderField
     */
    void onRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField ErrorConditionalOrder);


    /**
     * ///执行宣告通知
     *
     * @param ExecOrder CThostFtdcExecOrderField
     */
    void onRtnExecOrder(CThostFtdcExecOrderField ExecOrder);


    /**
     * ///执行宣告录入错误回报
     *
     * @param InputExecOrder CThostFtdcInputExecOrderField
     * @param RspInfo        CThostFtdcRspInfoField
     */
    void onErrRtnExecOrderInsert(CThostFtdcInputExecOrderField InputExecOrder,
                                 CThostFtdcRspInfoField RspInfo);


    /**
     * ///执行宣告操作错误回报
     *
     * @param ExecOrderAction CThostFtdcExecOrderActionField
     * @param RspInfo         CThostFtdcRspInfoField
     */
    void onErrRtnExecOrderAction(CThostFtdcExecOrderActionField ExecOrderAction,
                                 CThostFtdcRspInfoField RspInfo);


    /**
     * ///询价录入错误回报
     *
     * @param InputForQuote CThostFtdcInputForQuoteField
     * @param RspInfo       CThostFtdcRspInfoField
     */
    void onErrRtnForQuoteInsert(CThostFtdcInputForQuoteField InputForQuote,
                                CThostFtdcRspInfoField RspInfo);


    /**
     * ///报价通知
     *
     * @param Quote CThostFtdcQuoteField
     */
    void onRtnQuote(CThostFtdcQuoteField Quote);


    /**
     * ///报价录入错误回报
     *
     * @param InputQuote CThostFtdcInputQuoteField
     * @param RspInfo    CThostFtdcRspInfoField
     */
    void onErrRtnQuoteInsert(CThostFtdcInputQuoteField InputQuote,
                             CThostFtdcRspInfoField RspInfo);


    /**
     * ///报价操作错误回报
     *
     * @param QuoteAction CThostFtdcQuoteActionField
     * @param RspInfo     CThostFtdcRspInfoField
     */
    void onErrRtnQuoteAction(CThostFtdcQuoteActionField QuoteAction,
                             CThostFtdcRspInfoField RspInfo);


    /**
     * ///询价通知
     *
     * @param ForQuoteRsp CThostFtdcForQuoteRspField
     */
    void onRtnForQuoteRsp(CThostFtdcForQuoteRspField ForQuoteRsp);


    /**
     * ///保证金监控中心用户令牌
     *
     * @param CFMMCTradingAccountToken CThostFtdcCFMMCTradingAccountTokenField
     */
    void onRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField CFMMCTradingAccountToken);


    /**
     * ///批量报单操作错误回报
     *
     * @param BatchOrderAction CThostFtdcBatchOrderActionField
     * @param RspInfo          CThostFtdcRspInfoField
     */
    void onErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField BatchOrderAction,
                                  CThostFtdcRspInfoField RspInfo);


    /**
     * ///期权自对冲通知
     *
     * @param OptionSelfClose CThostFtdcOptionSelfCloseField
     */
    void onRtnOptionSelfClose(CThostFtdcOptionSelfCloseField OptionSelfClose);


    /**
     * ///期权自对冲录入错误回报
     *
     * @param InputOptionSelfClose CThostFtdcInputOptionSelfCloseField
     * @param RspInfo              CThostFtdcRspInfoField
     */
    void onErrRtnOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField InputOptionSelfClose,
                                       CThostFtdcRspInfoField RspInfo);


    /**
     * ///期权自对冲操作错误回报
     *
     * @param OptionSelfCloseAction CThostFtdcOptionSelfCloseActionField
     * @param RspInfo               CThostFtdcRspInfoField
     */
    void onErrRtnOptionSelfCloseAction(CThostFtdcOptionSelfCloseActionField OptionSelfCloseAction,
                                       CThostFtdcRspInfoField RspInfo);


    /**
     * ///申请组合通知
     *
     * @param CombAction CThostFtdcCombActionField
     */
    void onRtnCombAction(CThostFtdcCombActionField CombAction);


    /**
     * ///申请组合录入错误回报
     *
     * @param InputCombAction CThostFtdcInputCombActionField
     * @param RspInfo         CThostFtdcRspInfoField
     */
    void onErrRtnCombActionInsert(CThostFtdcInputCombActionField InputCombAction,
                                  CThostFtdcRspInfoField RspInfo);


    /**
     * ///请求查询签约银行响应
     *
     * @param ContractBank CThostFtdcContractBankField
     * @param RspInfo      CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    void onRspQryContractBank(CThostFtdcContractBankField ContractBank,
                              CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询预埋单响应
     *
     * @param ParkedOrder CThostFtdcParkedOrderField
     * @param RspInfo     CThostFtdcRspInfoField
     * @param RequestID   int
     * @param IsLast      boolean
     */
    void onRspQryParkedOrder(CThostFtdcParkedOrderField ParkedOrder,
                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询预埋撤单响应
     *
     * @param ParkedOrderAction CThostFtdcParkedOrderActionField
     * @param RspInfo           CThostFtdcRspInfoField
     * @param RequestID         int
     * @param IsLast            boolean
     */
    void onRspQryParkedOrderAction(CThostFtdcParkedOrderActionField ParkedOrderAction,
                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询交易通知响应
     *
     * @param TradingNotice CThostFtdcTradingNoticeField
     * @param RspInfo       CThostFtdcRspInfoField
     * @param RequestID     int
     * @param IsLast        boolean
     */
    void onRspQryTradingNotice(CThostFtdcTradingNoticeField TradingNotice,
                               CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询经纪公司交易参数响应
     *
     * @param BrokerTradingParams CThostFtdcBrokerTradingParamsField
     * @param RspInfo             CThostFtdcRspInfoField
     * @param RequestID           int
     * @param IsLast              boolean
     */
    void onRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField BrokerTradingParams,
                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询经纪公司交易算法响应
     *
     * @param BrokerTradingAlgos CThostFtdcBrokerTradingAlgosField
     * @param RspInfo            CThostFtdcRspInfoField
     * @param RequestID          int
     * @param IsLast             boolean
     */
    void onRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField BrokerTradingAlgos,
                                    CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///请求查询监控中心用户令牌
     *
     * @param QueryCFMMCTradingAccountToken CThostFtdcQueryCFMMCTradingAccountTokenField
     * @param RspInfo                       CThostFtdcRspInfoField
     * @param RequestID                     int
     * @param IsLast                        boolean
     */
    void onRspQueryCFMMCTradingAccountToken(CThostFtdcQueryCFMMCTradingAccountTokenField QueryCFMMCTradingAccountToken,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///银行发起银行资金转期货通知
     *
     * @param RspTransfer CThostFtdcRspTransferField
     */
    void onRtnFromBankToFutureByBank(CThostFtdcRspTransferField RspTransfer);


    /**
     * ///银行发起期货资金转银行通知
     *
     * @param RspTransfer CThostFtdcRspTransferField
     */
    void onRtnFromFutureToBankByBank(CThostFtdcRspTransferField RspTransfer);


    /**
     * ///银行发起冲正银行转期货通知
     *
     * @param RspRepeal CThostFtdcRspRepealField
     */
    void onRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField RspRepeal);


    /**
     * ///银行发起冲正期货转银行通知
     *
     * @param RspRepeal CThostFtdcRspRepealField
     */
    void onRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField RspRepeal);


    /**
     * ///期货发起银行资金转期货通知
     *
     * @param RspTransfer CThostFtdcRspTransferField
     */
    void onRtnFromBankToFutureByFuture(CThostFtdcRspTransferField RspTransfer);


    /**
     * ///期货发起期货资金转银行通知
     *
     * @param RspTransfer CThostFtdcRspTransferField
     */
    void onRtnFromFutureToBankByFuture(CThostFtdcRspTransferField RspTransfer);


    /**
     * ///系统运行时期货端手工发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param RspRepeal CThostFtdcRspRepealField
     */
    void onRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField RspRepeal);


    /**
     * ///系统运行时期货端手工发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param RspRepeal CThostFtdcRspRepealField
     */
    void onRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField RspRepeal);


    /**
     * ///期货发起查询银行余额通知
     *
     * @param NotifyQueryAccount CThostFtdcNotifyQueryAccountField
     */
    void onRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField NotifyQueryAccount);


    /**
     * ///期货发起银行资金转期货错误回报
     *
     * @param ReqTransfer CThostFtdcReqTransferField
     * @param RspInfo     CThostFtdcRspInfoField
     */
    void onErrRtnBankToFutureByFuture(CThostFtdcReqTransferField ReqTransfer,
                                      CThostFtdcRspInfoField RspInfo);


    /**
     * ///期货发起期货资金转银行错误回报
     *
     * @param ReqTransfer CThostFtdcReqTransferField
     * @param RspInfo     CThostFtdcRspInfoField
     */
    void onErrRtnFutureToBankByFuture(CThostFtdcReqTransferField ReqTransfer,
                                      CThostFtdcRspInfoField RspInfo);


    /**
     * ///系统运行时期货端手工发起冲正银行转期货错误回报
     *
     * @param ReqRepeal CThostFtdcReqRepealField
     * @param RspInfo   CThostFtdcRspInfoField
     */
    void onErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField ReqRepeal,
                                                  CThostFtdcRspInfoField RspInfo);


    /**
     * ///系统运行时期货端手工发起冲正期货转银行错误回报
     *
     * @param ReqRepeal CThostFtdcReqRepealField
     * @param RspInfo   CThostFtdcRspInfoField
     */
    void onErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField ReqRepeal,
                                                  CThostFtdcRspInfoField RspInfo);


    /**
     * ///期货发起查询银行余额错误回报
     *
     * @param ReqQueryAccount CThostFtdcReqQueryAccountField
     * @param RspInfo         CThostFtdcRspInfoField
     */
    void onErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField ReqQueryAccount,
                                          CThostFtdcRspInfoField RspInfo);


    /**
     * ///期货发起冲正银行转期货请求, 银行处理完毕后报盘发回的通知
     *
     * @param RspRepeal CThostFtdcRspRepealField
     */
    void onRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField RspRepeal);


    /**
     * ///期货发起冲正期货转银行请求, 银行处理完毕后报盘发回的通知
     *
     * @param RspRepeal CThostFtdcRspRepealField
     */
    void onRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField RspRepeal);


    /**
     * ///期货发起银行资金转期货应答
     *
     * @param ReqTransfer CThostFtdcReqTransferField
     * @param RspInfo     CThostFtdcRspInfoField
     * @param RequestID   int
     * @param IsLast      boolean
     */
    void onRspFromBankToFutureByFuture(CThostFtdcReqTransferField ReqTransfer,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///期货发起期货资金转银行应答
     *
     * @param ReqTransfer CThostFtdcReqTransferField
     * @param RspInfo     CThostFtdcRspInfoField
     * @param RequestID   int
     * @param IsLast      boolean
     */
    void onRspFromFutureToBankByFuture(CThostFtdcReqTransferField ReqTransfer,
                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///期货发起查询银行余额应答
     *
     * @param ReqQueryAccount CThostFtdcReqQueryAccountField
     * @param RspInfo         CThostFtdcRspInfoField
     * @param RequestID       int
     * @param IsLast          boolean
     */
    void onRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField ReqQueryAccount,
                                            CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast);


    /**
     * ///银行发起银期开户通知
     *
     * @param OpenAccount CThostFtdcOpenAccountField
     */
    void onRtnOpenAccountByBank(CThostFtdcOpenAccountField OpenAccount);


    /**
     * ///银行发起银期销户通知
     *
     * @param CancelAccount CThostFtdcCancelAccountField
     */
    void onRtnCancelAccountByBank(CThostFtdcCancelAccountField CancelAccount);


    /**
     * ///银行发起变更银行账号通知
     *
     * @param ChangeAccount CThostFtdcChangeAccountField
     */
    void onRtnChangeAccountByBank(CThostFtdcChangeAccountField ChangeAccount);


}
