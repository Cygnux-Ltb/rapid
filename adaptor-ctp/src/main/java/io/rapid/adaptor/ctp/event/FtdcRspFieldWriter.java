package io.rapid.adaptor.ctp.event;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.event.source.EventSource;
import io.rapid.adaptor.ctp.event.source.SpecificInstrumentSource;
import org.rationalityfrontline.jctp.CThostFtdcDepthMarketDataField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentStatusField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorPositionField;
import org.rationalityfrontline.jctp.CThostFtdcOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcOrderField;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcSpecificInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcTradeField;
import org.rationalityfrontline.jctp.CThostFtdcTradingAccountField;
import org.rationalityfrontline.jctp.CThostFtdcUserLogoutField;
import org.slf4j.Logger;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

public final class FtdcRspFieldWriter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspFieldWriter.class);

    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcDepthMarketDataField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeDepthMarketData(FtdcRspEvent event,
                                                    CThostFtdcDepthMarketDataField Field) {
        var depthMarketData = event.getFtdcDepthMarketData();
        depthMarketData.RecvEpochMicros = micros();
        // 交易日, 合约ID, 交易所ID, 合约在交易所的代码
        depthMarketData.TradingDay = Field.getTradingDay();
        depthMarketData.InstrumentID = Field.getInstrumentID();
//        depthMarketData.ExchangeID = Field.getExchangeID();
//        depthMarketData.ExchangeInstID = Field.getExchangeInstID();
        // 最新价
        depthMarketData.LastPrice = Field.getLastPrice();
        // 昨结算价, 昨收盘, 昨持仓量
        depthMarketData.PreSettlementPrice = Field.getPreSettlementPrice();
        depthMarketData.PreClosePrice = Field.getPreClosePrice();
        depthMarketData.PreOpenInterest = Field.getPreOpenInterest();
        // 开盘价, 最高价, 最低价
        depthMarketData.OpenPrice = Field.getOpenPrice();
        depthMarketData.HighestPrice = Field.getHighestPrice();
        depthMarketData.LowestPrice = Field.getLowestPrice();
        // 成交量, 成交金额, 持仓量
        depthMarketData.Volume = Field.getVolume();
        depthMarketData.Turnover = Field.getTurnover();
        depthMarketData.OpenInterest = Field.getOpenInterest();
        // 收盘价, 结算价
//        depthMarketData.ClosePrice = Field.getClosePrice();
//        depthMarketData.SettlementPrice = Field.getSettlementPrice();
        // 涨停板价, 跌停板价
        depthMarketData.UpperLimitPrice = Field.getUpperLimitPrice();
        depthMarketData.LowerLimitPrice = Field.getLowerLimitPrice();
        // 昨Delta, 今Delta
//        depthMarketData.PreDelta = Field.getPreDelta();
//        depthMarketData.CurrDelta = Field.getCurrDelta();
        // 五档买价卖价及买量卖量 v
        depthMarketData.BidPrice1 = Field.getBidPrice1();
        depthMarketData.BidVolume1 = Field.getBidVolume1();
        depthMarketData.AskPrice1 = Field.getAskPrice1();
        depthMarketData.AskVolume1 = Field.getAskVolume1();
//        depthMarketData.BidPrice2 = Field.getBidPrice2();
//        depthMarketData.BidVolume2 = Field.getBidVolume2();
//        depthMarketData.AskPrice2 = Field.getAskPrice2();
//        depthMarketData.AskVolume2 = Field.getAskVolume2();
//        depthMarketData.BidPrice3 = Field.getBidPrice3();
//        depthMarketData.BidVolume3 = Field.getBidVolume3();
//        depthMarketData.AskPrice3 = Field.getAskPrice3();
//        depthMarketData.AskVolume3 = Field.getAskVolume3();
//        depthMarketData.BidPrice4 = Field.getBidPrice4();
//        depthMarketData.BidVolume4 = Field.getBidVolume4();
//        depthMarketData.AskPrice4 = Field.getAskPrice4();
//        depthMarketData.AskVolume4 = Field.getAskVolume4();
//        depthMarketData.BidPrice5 = Field.getBidPrice5();
//        depthMarketData.BidVolume5 = Field.getBidVolume5();
//        depthMarketData.AskPrice5 = Field.getAskPrice5();
//        depthMarketData.AskVolume5 = Field.getAskVolume5();
        // 五档买价卖价及买量卖量 ^
        // 平均价格
        depthMarketData.AveragePrice = Field.getAveragePrice();
        // 更新时间, 更新毫秒数, 业务日期
        depthMarketData.UpdateTime = Field.getUpdateTime();
        depthMarketData.UpdateMillisec = Field.getUpdateMillisec();
        depthMarketData.ActionDay = Field.getActionDay();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcDepthMarketData);
    }

    /**
     * @param event        FtdcRspEvent
     * @param Source       EventSource
     * @param Field        CThostFtdcRspUserLoginField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspUserLogin(FtdcRspEvent event,
                                                 EventSource Source,
                                                 CThostFtdcRspUserLoginField Field,
                                                 CThostFtdcRspInfoField RspInfoField,
                                                 int RequestID, boolean IsLast) {
        var rspUserLogin = event.getRspUserLogin();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        rspUserLogin.Source = Source;
        rspUserLogin.ErrorID = RspInfoField.getErrorID();
        rspUserLogin.ErrorMsg = RspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        rspUserLogin.RequestID = RequestID;
        rspUserLogin.IsLast = IsLast;
        // 交易日
        rspUserLogin.TradingDay = Field.getTradingDay();
        // 登录成功时间
        rspUserLogin.LoginTime = Field.getLoginTime();
        // 经纪公司代码
        rspUserLogin.BrokerID = Field.getBrokerID();
        // 用户代码
        rspUserLogin.UserID = Field.getUserID();
        // 交易系统名称
        rspUserLogin.SystemName = Field.getSystemName();
        // 前置编号
        rspUserLogin.FrontID = Field.getFrontID();
        // 会话编号
        rspUserLogin.SessionID = Field.getSessionID();
        // 最大报单引用
        rspUserLogin.MaxOrderRef = Field.getMaxOrderRef();
        // 上期所时间
        rspUserLogin.SHFETime = Field.getSHFETime();
        // 大商所时间
        rspUserLogin.DCETime = Field.getDCETime();
        // 郑商所时间
        rspUserLogin.CZCETime = Field.getCZCETime();
        // 中金所时间
        rspUserLogin.FFEXTime = Field.getFFEXTime();
        // 能源中心时间
        rspUserLogin.INETime = Field.getINETime();
        // 广期所时间
        rspUserLogin.GFEXTime = Field.getGFEXTime();
        // 后台版本信息
        rspUserLogin.SysVersion = Field.getSysVersion();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.RspUserLogin);
    }

    /**
     * @param event        FtdcRspEvent
     * @param Source       EventSource
     * @param Field        CThostFtdcUserLogoutField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspUserLogout(FtdcRspEvent event,
                                                  EventSource Source,
                                                  CThostFtdcUserLogoutField Field,
                                                  CThostFtdcRspInfoField RspInfoField,
                                                  int RequestID, boolean IsLast) {
        var userLogout = event.getUserLogout();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        userLogout.Source = Source;
        userLogout.ErrorID = RspInfoField.getErrorID();
        userLogout.ErrorMsg = RspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        userLogout.RequestID = RequestID;
        userLogout.IsLast = IsLast;
        // 经纪公司代码, 用户代码
        userLogout.BrokerID = Field.getBrokerID();
        userLogout.UserID = Field.getUserID();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.UserLogout);
    }

    /**
     * @param event    FtdcRspEvent
     * @param Source   EventSource
     * @param Reason   int
     * @param BrokerID String
     * @param UserID   String
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeFrontDisconnected(FtdcRspEvent event,
                                                      EventSource Source, int Reason,
                                                      String BrokerID, String UserID) {
        var frontDisconnected = event.getFrontDisconnected();
        // 事件来源
        frontDisconnected.Source = Source;
        // 错误原因
        frontDisconnected.Reason = Reason;
        // 经纪公司代码
        frontDisconnected.BrokerID = BrokerID;
        // 用户代码
        frontDisconnected.UserID = UserID;
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FrontDisconnected);
    }

    /**
     * @param event     FtdcRspEvent
     * @param Source    EventSource
     * @param TimeLapse int
     * @param BrokerID  String
     * @param UserID    String
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeHeartBeatWarning(FtdcRspEvent event,
                                                     EventSource Source, int TimeLapse,
                                                     String BrokerID, String UserID) {
        var heartBeatWarning = event.getHeartBeatWarning();
        // 事件来源
        heartBeatWarning.Source = Source;
        // 距离上次接收报文的时间
        heartBeatWarning.TimeLapse = TimeLapse;
        // 经纪公司代码
        heartBeatWarning.BrokerID = BrokerID;
        // 用户代码
        heartBeatWarning.UserID = UserID;
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.HeartBeatWarning);
    }

    /**
     * @param event        FtdcRspEvent
     * @param Source       EventSource
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspError(FtdcRspEvent event,
                                             EventSource Source,
                                             CThostFtdcRspInfoField RspInfoField,
                                             int RequestID, boolean IsLast) {
        var rspError = event.getRspError();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        rspError.Source = Source;
        rspError.ErrorID = RspInfoField.getErrorID();
        rspError.ErrorMsg = RspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        rspError.RequestID = RequestID;
        rspError.IsLast = IsLast;
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.RspError);
    }

    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcInstrumentStatusField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInstrumentStatus(FtdcRspEvent event,
                                                     CThostFtdcInstrumentStatusField Field) {
        var instrumentStatus = event.getFtdcInstrumentStatus();
        // 交易所代码, 合约在交易所的代码, 结算组代码, 合约代码
        instrumentStatus.ExchangeID = Field.getExchangeID();
        instrumentStatus.ExchangeInstID = Field.getExchangeInstID();
        instrumentStatus.SettlementGroupID = Field.getSettlementGroupID();
        instrumentStatus.InstrumentID = Field.getInstrumentID();
        // 合约交易状态, 交易阶段编号, 进入本状态时间, 进入本状态原因
        instrumentStatus.InstrumentStatus = Field.getInstrumentStatus();
        instrumentStatus.TradingSegmentSN = Field.getTradingSegmentSN();
        instrumentStatus.EnterTime = Field.getEnterTime();
        instrumentStatus.EnterReason = Field.getEnterReason();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcInstrumentStatus);
    }

    /**
     * @param event        FtdcRspEvent
     * @param Field        CThostFtdcInputOrderField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrder(FtdcRspEvent event,
                                               CThostFtdcInputOrderField Field,
                                               CThostFtdcRspInfoField RspInfoField,
                                               boolean IsLast) {
        var inputOrder = event.getFtdcInputOrder();
        // [FTDC响应信息] - 错误代码, 错误信息
        inputOrder.ErrorID = RspInfoField.getErrorID();
        inputOrder.ErrorMsg = RspInfoField.getErrorMsg();
        inputOrder.IsLast = IsLast;
        inputOrder.BrokerID = Field.getBrokerID();
        inputOrder.InvestorID = Field.getInvestorID();
        inputOrder.InstrumentID = Field.getInstrumentID();
        inputOrder.OrderRef = Field.getOrderRef();
        inputOrder.UserID = Field.getUserID();
        inputOrder.OrderPriceType = Field.getOrderPriceType();
        inputOrder.Direction = Field.getDirection();
        inputOrder.CombOffsetFlag = Field.getCombOffsetFlag();
        inputOrder.CombHedgeFlag = Field.getCombHedgeFlag();
        inputOrder.LimitPrice = Field.getLimitPrice();
        inputOrder.VolumeTotalOriginal = Field.getVolumeTotalOriginal();
        inputOrder.TimeCondition = Field.getTimeCondition();
        inputOrder.GTDDate = Field.getGTDDate();
        inputOrder.VolumeCondition = Field.getVolumeCondition();
        inputOrder.MinVolume = Field.getMinVolume();
        inputOrder.ContingentCondition = Field.getContingentCondition();
        inputOrder.StopPrice = Field.getStopPrice();
        inputOrder.ForceCloseReason = Field.getForceCloseReason();
        inputOrder.IsAutoSuspend = Field.getIsAutoSuspend();
        inputOrder.BusinessUnit = Field.getBusinessUnit();
        inputOrder.RequestID = Field.getRequestID();
        inputOrder.UserForceClose = Field.getUserForceClose();
        inputOrder.IsSwapOrder = Field.getIsSwapOrder();
        inputOrder.ExchangeID = Field.getExchangeID();
        inputOrder.InvestUnitID = Field.getInvestUnitID();
        inputOrder.AccountID = Field.getAccountID();
        inputOrder.CurrencyID = Field.getCurrencyID();
        inputOrder.ClientID = Field.getClientID();
        inputOrder.IPAddress = Field.getIPAddress();
        inputOrder.MacAddress = Field.getMacAddress();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcInputOrder);
    }

    /**
     * @param event        FtdcRspEvent
     * @param Field        CThostFtdcInputOrderActionField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrderAction(FtdcRspEvent event,
                                                     CThostFtdcInputOrderActionField Field,
                                                     CThostFtdcRspInfoField RspInfoField,
                                                     boolean IsLast) {
        var inputOrderAction = event.getFtdcInputOrderAction();
        // [FTDC响应信息] - 错误代码, 错误信息
        inputOrderAction.ErrorID = RspInfoField.getErrorID();
        inputOrderAction.ErrorMsg = RspInfoField.getErrorMsg();
        inputOrderAction.IsLast = IsLast;
        inputOrderAction.BrokerID = Field.getBrokerID();
        inputOrderAction.InvestorID = Field.getInvestorID();
        inputOrderAction.OrderActionRef = Field.getOrderActionRef();
        inputOrderAction.OrderRef = Field.getOrderRef();
        inputOrderAction.RequestID = Field.getRequestID();
        inputOrderAction.FrontID = Field.getFrontID();
        inputOrderAction.SessionID = Field.getSessionID();
        inputOrderAction.ExchangeID = Field.getExchangeID();
        inputOrderAction.OrderSysID = Field.getOrderSysID();
        inputOrderAction.ActionFlag = Field.getActionFlag();
        inputOrderAction.LimitPrice = Field.getLimitPrice();
        inputOrderAction.VolumeChange = Field.getVolumeChange();
        inputOrderAction.UserID = Field.getUserID();
        inputOrderAction.InstrumentID = Field.getInstrumentID();
        inputOrderAction.InvestUnitID = Field.getInvestUnitID();
        inputOrderAction.IPAddress = Field.getIPAddress();
        inputOrderAction.MacAddress = Field.getMacAddress();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcInputOrderAction);
    }


    /**
     * @param event        FtdcRspEvent
     * @param Field        CThostFtdcInvestorPositionField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInvestorPosition(FtdcRspEvent event,
                                                     CThostFtdcInvestorPositionField Field,
                                                     CThostFtdcRspInfoField RspInfoField,
                                                     int RequestID, boolean IsLast) {
        var investorPosition = event.getFtdcInvestorPosition();
        // [FTDC响应信息] - 错误代码, 错误信息
        investorPosition.ErrorID = RspInfoField.getErrorID();
        investorPosition.ErrorMsg = RspInfoField.getErrorMsg();
        investorPosition.RequestID = RequestID;
        investorPosition.IsLast = IsLast;
        // 合约代码
        investorPosition.InstrumentID = Field.getInstrumentID();
        // 经纪公司代码
        investorPosition.BrokerID = Field.getBrokerID();
        // 投资者代码
        investorPosition.InvestorID = Field.getInvestorID();
        // 持仓多空方向
        investorPosition.PosiDirection = Field.getPosiDirection();
        // 投机套保标志
        investorPosition.HedgeFlag = Field.getHedgeFlag();
        // 持仓日期
        investorPosition.PositionDate = Field.getPositionDate();
        // 上日持仓
        investorPosition.YdPosition = Field.getYdPosition();
        // 今日持仓
        investorPosition.Position = Field.getPosition();
        // 多头冻结
        investorPosition.LongFrozen = Field.getLongFrozen();
        // 空头冻结
        investorPosition.ShortFrozen = Field.getShortFrozen();
        // 开仓冻结金额
        investorPosition.LongFrozenAmount = Field.getLongFrozenAmount();
        // 开仓冻结金额
        investorPosition.ShortFrozenAmount = Field.getShortFrozenAmount();
        // 开仓量
        investorPosition.OpenVolume = Field.getOpenVolume();
        // 平仓量
        investorPosition.CloseVolume = Field.getCloseVolume();
        // 开仓金额
        investorPosition.OpenAmount = Field.getOpenAmount();
        // 平仓金额
        investorPosition.CloseAmount = Field.getCloseAmount();
        // 持仓成本
        investorPosition.PositionCost = Field.getPositionCost();
        // 上次占用的保证金
        investorPosition.PreMargin = Field.getPreMargin();
        // 占用的保证金
        investorPosition.UseMargin = Field.getUseMargin();
        // 冻结的保证金
        investorPosition.FrozenMargin = Field.getFrozenMargin();
        // 冻结的资金
        investorPosition.FrozenCash = Field.getFrozenCash();
        // 冻结的手续费
        investorPosition.FrozenCommission = Field.getFrozenCommission();
        // 资金差额
        investorPosition.CashIn = Field.getCashIn();
        // 手续费
        investorPosition.Commission = Field.getCommission();
        // 平仓盈亏
        investorPosition.CloseProfit = Field.getCloseProfit();
        // 持仓盈亏
        investorPosition.PositionProfit = Field.getPositionProfit();
        // 上次结算价
        investorPosition.PreSettlementPrice = Field.getPreSettlementPrice();
        // 本次结算价
        investorPosition.SettlementPrice = Field.getSettlementPrice();
        // 交易日
        investorPosition.TradingDay = Field.getTradingDay();
        // 结算编号
        investorPosition.SettlementID = Field.getSettlementID();
        // 开仓成本
        investorPosition.OpenCost = Field.getOpenCost();
        // 交易所保证金
        investorPosition.ExchangeMargin = Field.getExchangeMargin();
        // 组合成交形成的持仓
        investorPosition.CombPosition = Field.getCombPosition();
        // 组合多头冻结
        investorPosition.CombLongFrozen = Field.getCombLongFrozen();
        // 组合空头冻结
        investorPosition.CombShortFrozen = Field.getCombShortFrozen();
        // 逐日盯市平仓盈亏
        investorPosition.CloseProfitByDate = Field.getCloseProfitByDate();
        // 逐笔对冲平仓盈亏
        investorPosition.CloseProfitByTrade = Field.getCloseProfitByTrade();
        // 今日持仓
        investorPosition.TodayPosition = Field.getTodayPosition();
        // 保证金率
        investorPosition.MarginRateByMoney = Field.getMarginRateByMoney();
        // 保证金率(按手数)
        investorPosition.MarginRateByVolume = Field.getMarginRateByVolume();
        // 执行冻结
        investorPosition.StrikeFrozen = Field.getStrikeFrozen();
        // 执行冻结金额
        investorPosition.StrikeFrozenAmount = Field.getStrikeFrozenAmount();
        // 放弃执行冻结
        investorPosition.AbandonFrozen = Field.getAbandonFrozen();
        // 交易所代码
        investorPosition.ExchangeID = Field.getExchangeID();
        // 执行冻结的昨仓
        investorPosition.YdStrikeFrozen = Field.getYdStrikeFrozen();
        // 投资单元代码
        investorPosition.InvestUnitID = Field.getInvestUnitID();
        // 大商所持仓成本差值, 只有大商所使用
        // 6.3.15 版本使用
        investorPosition.PositionCostOffset = Field.getPositionCostOffset();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcInvestorPosition);
    }

    /**
     * @param event      FtdcRspEvent
     * @param OrderField CThostFtdcOrderField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrder(FtdcRspEvent event,
                                          CThostFtdcOrderField OrderField) {
        var order = event.getFtdcOrder();
        order.RecvEpochMicros = micros();
        order.BrokerID = OrderField.getBrokerID();
        order.InvestorID = OrderField.getInvestorID();
        order.InstrumentID = OrderField.getInstrumentID();
        order.OrderRef = OrderField.getOrderRef();
        order.UserID = OrderField.getUserID();
        order.OrderPriceType = OrderField.getOrderPriceType();
        order.Direction = OrderField.getDirection();
        order.CombOffsetFlag = OrderField.getCombOffsetFlag();
        order.CombHedgeFlag = OrderField.getCombHedgeFlag();
        order.LimitPrice = OrderField.getLimitPrice();
        order.VolumeTotalOriginal = OrderField.getVolumeTotalOriginal();
        order.TimeCondition = OrderField.getTimeCondition();
        order.GTDDate = OrderField.getGTDDate();
        order.VolumeCondition = OrderField.getVolumeCondition();
        order.MinVolume = OrderField.getMinVolume();
        order.ContingentCondition = OrderField.getContingentCondition();
        order.StopPrice = OrderField.getStopPrice();
        order.ForceCloseReason = OrderField.getForceCloseReason();
        order.IsAutoSuspend = OrderField.getIsAutoSuspend();
        order.BusinessUnit = OrderField.getBusinessUnit();
        order.RequestID = OrderField.getRequestID();
        order.OrderLocalID = OrderField.getOrderLocalID();
        order.ExchangeID = OrderField.getExchangeID();
        order.ParticipantID = OrderField.getParticipantID();
        order.ClientID = OrderField.getClientID();
        order.ExchangeInstID = OrderField.getExchangeInstID();
        order.TraderID = OrderField.getTraderID();
        order.InstallID = OrderField.getInstallID();
        order.OrderSubmitStatus = OrderField.getOrderSubmitStatus();
        order.NotifySequence = OrderField.getNotifySequence();
        order.TradingDay = OrderField.getTradingDay();
        order.SettlementID = OrderField.getSettlementID();
        order.OrderSysID = OrderField.getOrderSysID();
        order.OrderSource = OrderField.getOrderSource();
        order.OrderStatus = OrderField.getOrderStatus();
        order.OrderType = OrderField.getOrderType();
        order.VolumeTraded = OrderField.getVolumeTraded();
        order.VolumeTotal = OrderField.getVolumeTotal();
        order.InsertDate = OrderField.getInsertDate();
        order.InsertTime = OrderField.getInsertTime();
        order.ActiveTime = OrderField.getActiveTime();
        order.SuspendTime = OrderField.getSuspendTime();
        order.UpdateTime = OrderField.getUpdateTime();
        order.CancelTime = OrderField.getCancelTime();
        order.ActiveTraderID = OrderField.getActiveTraderID();
        order.ClearingPartID = OrderField.getClearingPartID();
        order.SequenceNo = OrderField.getSequenceNo();
        order.FrontID = OrderField.getFrontID();
        order.SessionID = OrderField.getSessionID();
        order.UserProductInfo = OrderField.getUserProductInfo();
        order.StatusMsg = OrderField.getStatusMsg();
        order.UserForceClose = OrderField.getUserForceClose();
        order.ActiveUserID = OrderField.getActiveUserID();
        order.BrokerOrderSeq = OrderField.getBrokerOrderSeq();
        order.RelativeOrderSysID = OrderField.getRelativeOrderSysID();
        order.ZCETotalTradedVolume = OrderField.getZCETotalTradedVolume();
        order.IsSwapOrder = OrderField.getIsSwapOrder();
        order.BranchID = OrderField.getBranchID();
        order.InvestUnitID = OrderField.getInvestUnitID();
        order.AccountID = OrderField.getAccountID();
        order.CurrencyID = OrderField.getCurrencyID();
        order.IPAddress = OrderField.getIPAddress();
        order.MacAddress = OrderField.getMacAddress();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcOrder);
    }

    /**
     * @param event            FtdcRspEvent
     * @param OrderActionField CThostFtdcOrderActionField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrderAction(FtdcRspEvent event,
                                                CThostFtdcOrderActionField OrderActionField) {
        var orderAction = event.getFtdcOrderAction();
        // 经纪公司代码
        orderAction.BrokerID = OrderActionField.getBrokerID();
        // 投资者代码
        orderAction.InvestorID = OrderActionField.getInvestorID();
        // 报单操作引用
        orderAction.OrderActionRef = OrderActionField.getOrderActionRef();
        // 报单引用
        orderAction.OrderRef = OrderActionField.getOrderRef();
        // 请求编号
        orderAction.RequestID = OrderActionField.getRequestID();
        // 前置编号
        orderAction.FrontID = OrderActionField.getFrontID();
        // 会话编号
        orderAction.SessionID = OrderActionField.getSessionID();
        // 交易所代码
        orderAction.ExchangeID = OrderActionField.getExchangeID();
        // 报单编号
        orderAction.OrderSysID = OrderActionField.getOrderSysID();
        // 操作标志
        orderAction.ActionFlag = OrderActionField.getActionFlag();
        // 价格
        orderAction.LimitPrice = OrderActionField.getLimitPrice();
        // 数量变化
        orderAction.VolumeChange = OrderActionField.getVolumeChange();
        // 操作日期
        orderAction.ActionDate = OrderActionField.getActionDate();
        // 操作时间
        orderAction.ActionTime = OrderActionField.getActionTime();
        // 交易所交易员代码
        orderAction.TraderID = OrderActionField.getTraderID();
        // 安装编号
        orderAction.InstallID = OrderActionField.getInstallID();
        // 本地报单编号
        orderAction.OrderLocalID = OrderActionField.getOrderLocalID();
        // 操作本地编号
        orderAction.ActionLocalID = OrderActionField.getActionLocalID();
        // 会员代码
        orderAction.ParticipantID = OrderActionField.getParticipantID();
        // 客户代码
        orderAction.ClientID = OrderActionField.getClientID();
        // 业务单元
        orderAction.BusinessUnit = OrderActionField.getBusinessUnit();
        // 报单操作状态
        orderAction.OrderActionStatus = OrderActionField.getOrderActionStatus();
        // 用户代码
        orderAction.UserID = OrderActionField.getUserID();
        // 状态信息
        orderAction.StatusMsg = OrderActionField.getStatusMsg();
        // 合约代码
        orderAction.InstrumentID = OrderActionField.getInstrumentID();
        // 营业部编号
        orderAction.BranchID = OrderActionField.getBranchID();
        // 投资单元代码
        orderAction.InvestUnitID = OrderActionField.getInvestUnitID();
        // IP地址
        orderAction.IPAddress = OrderActionField.getIPAddress();
        // MAC地址
        orderAction.MacAddress = OrderActionField.getMacAddress();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcOrderAction);
    }

    /**
     * @param event      FtdcRspEvent
     * @param TradeField CThostFtdcTradeField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTrade(FtdcRspEvent event,
                                          CThostFtdcTradeField TradeField) {
        var trade = event.getFtdcTrade();
        trade.RecvEpochMicros = micros();
        // 经纪公司代码
        trade.BrokerID = TradeField.getBrokerID();
        // 投资者代码
        trade.InvestorID = TradeField.getInvestorID();
        // 合约代码
        trade.InstrumentID = TradeField.getInstrumentID();
        // 报单引用
        trade.OrderRef = TradeField.getOrderRef();
        // 用户代码
        trade.UserID = TradeField.getUserID();
        // 交易所代码
        trade.ExchangeID = TradeField.getExchangeID();
        // 成交编号
        trade.TradeID = TradeField.getTradeID();
        // 买卖方向
        trade.Direction = TradeField.getDirection();
        // 报单编号
        trade.OrderSysID = TradeField.getOrderSysID();
        // 会员代码
        trade.ParticipantID = TradeField.getParticipantID();
        // 客户代码
        trade.ClientID = TradeField.getClientID();
        // 交易角色
        trade.TradingRole = TradeField.getTradingRole();
        // 合约在交易所的代码
        trade.ExchangeInstID = TradeField.getExchangeInstID();
        // 开平标志
        trade.OffsetFlag = TradeField.getOffsetFlag();
        // 投机套保标志
        trade.HedgeFlag = TradeField.getHedgeFlag();
        // 价格
        trade.Price = TradeField.getPrice();
        // 数量
        trade.Volume = TradeField.getVolume();
        // 成交日期
        trade.TradeDate = TradeField.getTradeDate();
        // 成交时间
        trade.TradeTime = TradeField.getTradeTime();
        // 成交类型
        trade.TradeType = TradeField.getTradeType();
        // 成交价来源
        trade.PriceSource = TradeField.getPriceSource();
        // 交易所交易员代码
        trade.TraderID = TradeField.getTraderID();
        // 本地报单编号
        trade.OrderLocalID = TradeField.getOrderLocalID();
        // 结算会员编号
        trade.ClearingPartID = TradeField.getClearingPartID();
        // 业务单元
        trade.BusinessUnit = TradeField.getBusinessUnit();
        // 序号
        trade.SequenceNo = TradeField.getSequenceNo();
        // 交易日
        trade.TradingDay = TradeField.getTradingDay();
        // 结算编号
        trade.SettlementID = TradeField.getSettlementID();
        // 经纪公司报单编号
        trade.BrokerOrderSeq = TradeField.getBrokerOrderSeq();
        // 成交来源
        trade.TradeSource = TradeField.getTradeSource();
        // 投资单元代码
        trade.InvestUnitID = TradeField.getInvestUnitID();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcTrade);
    }

    /**
     * @param event        FtdcRspEvent
     * @param Field        CThostFtdcTradingAccountField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTradingAccount(FtdcRspEvent event,
                                                   CThostFtdcTradingAccountField Field,
                                                   CThostFtdcRspInfoField RspInfoField,
                                                   int RequestID, boolean IsLast) {
        var tradingAccount = event.getFtdcTradingAccount();
        tradingAccount.ErrorID = RspInfoField.getErrorID();
        tradingAccount.ErrorMsg = RspInfoField.getErrorMsg();
        tradingAccount.RequestID = RequestID;
        tradingAccount.IsLast = IsLast;
        // 经纪公司代码
        tradingAccount.BrokerID = Field.getBrokerID();
        // 投资者账号
        tradingAccount.AccountID = Field.getAccountID();
        // 上次质押金额
        tradingAccount.PreMortgage = Field.getPreMortgage();
        // 上次信用额度
        tradingAccount.PreCredit = Field.getPreCredit();
        // 上次存款额
        tradingAccount.PreDeposit = Field.getPreDeposit();
        // 上次结算准备金
        tradingAccount.PreBalance = Field.getPreBalance();
        // 上次占用的保证金
        tradingAccount.PreMargin = Field.getPreMargin();
        // 利息基数
        tradingAccount.InterestBase = Field.getInterestBase();
        // 利息收入
        tradingAccount.Interest = Field.getInterest();
        // 入金金额
        tradingAccount.Deposit = Field.getDeposit();
        // 出金金额
        tradingAccount.Withdraw = Field.getWithdraw();
        // 冻结的保证金
        tradingAccount.FrozenMargin = Field.getFrozenMargin();
        // 冻结的资金
        tradingAccount.FrozenCash = Field.getFrozenCash();
        // 冻结的手续费
        tradingAccount.FrozenCommission = Field.getFrozenCommission();
        // 当前保证金总额
        tradingAccount.CurrMargin = Field.getCurrMargin();
        // 资金差额
        tradingAccount.CashIn = Field.getCashIn();
        // 手续费
        tradingAccount.Commission = Field.getCommission();
        // 平仓盈亏
        tradingAccount.CloseProfit = Field.getCloseProfit();
        // 持仓盈亏
        tradingAccount.PositionProfit = Field.getPositionProfit();
        // 期货结算准备金
        tradingAccount.Balance = Field.getBalance();
        // 可用资金
        tradingAccount.Available = Field.getAvailable();
        // 可取资金
        tradingAccount.WithdrawQuota = Field.getWithdrawQuota();
        // 基本准备金
        tradingAccount.Reserve = Field.getReserve();
        // 交易日
        tradingAccount.TradingDay = Field.getTradingDay();
        // 结算编号
        tradingAccount.SettlementID = Field.getSettlementID();
        // 信用额度
        tradingAccount.Credit = Field.getCredit();
        // 质押金额
        tradingAccount.Mortgage = Field.getMortgage();
        // 交易所保证金
        tradingAccount.ExchangeMargin = Field.getExchangeMargin();
        // 投资者交割保证金
        tradingAccount.DeliveryMargin = Field.getDeliveryMargin();
        // 交易所交割保证金
        tradingAccount.ExchangeDeliveryMargin = Field.getExchangeDeliveryMargin();
        // 保底期货结算准备金
        tradingAccount.ReserveBalance = Field.getReserveBalance();
        // 币种代码
        tradingAccount.CurrencyID = Field.getCurrencyID();
        // 上次货币质入金额
        tradingAccount.PreFundMortgageIn = Field.getPreFundMortgageIn();
        // 上次货币质出金额
        tradingAccount.PreFundMortgageOut = Field.getPreFundMortgageOut();
        // 货币质入金额
        tradingAccount.FundMortgageIn = Field.getFundMortgageIn();
        // 货币质出金额
        tradingAccount.FundMortgageOut = Field.getFundMortgageOut();
        // 货币质押余额
        tradingAccount.FundMortgageAvailable = Field.getFundMortgageAvailable();
        // 可质押货币金额
        tradingAccount.MortgageableFund = Field.getMortgageableFund();
        // 特殊产品占用保证金
        tradingAccount.SpecProductMargin = Field.getSpecProductMargin();
        // 特殊产品冻结保证金
        tradingAccount.SpecProductFrozenMargin = Field.getSpecProductFrozenMargin();
        // 特殊产品手续费
        tradingAccount.SpecProductCommission = Field.getSpecProductCommission();
        // 特殊产品冻结手续费
        tradingAccount.SpecProductFrozenCommission = Field.getSpecProductFrozenCommission();
        // 特殊产品持仓盈亏
        tradingAccount.SpecProductPositionProfit = Field.getSpecProductPositionProfit();
        // 特殊产品平仓盈亏
        tradingAccount.SpecProductCloseProfit = Field.getSpecProductCloseProfit();
        // 根据持仓盈亏算法计算的特殊产品持仓盈亏
        tradingAccount.SpecProductPositionProfitByAlg = Field.getSpecProductPositionProfitByAlg();
        // 特殊产品交易所保证金
        tradingAccount.SpecProductExchangeMargin = Field.getSpecProductExchangeMargin();
        // 业务类型
        tradingAccount.BizType = Field.getBizType();
        // 延时换汇冻结金额
        tradingAccount.FrozenSwap = Field.getFrozenSwap();
        // 剩余换汇额度
        tradingAccount.RemainSwap = Field.getRemainSwap();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcTradingAccount);
    }

    /**
     * @param event        FtdcRspEvent
     * @param Source       SpecificInstrumentSource
     * @param Field        CThostFtdcSpecificInstrumentField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeSpecificInstrument(FtdcRspEvent event,
                                                       SpecificInstrumentSource Source,
                                                       CThostFtdcSpecificInstrumentField Field,
                                                       CThostFtdcRspInfoField RspInfoField,
                                                       int RequestID, boolean IsLast) {
        var specificInstrument = event.getFtdcSpecificInstrument();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        specificInstrument.Source = Source;
        specificInstrument.ErrorID = RspInfoField.getErrorID();
        specificInstrument.ErrorMsg = RspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        specificInstrument.RequestID = RequestID;
        specificInstrument.IsLast = IsLast;
        // 合约代码
        specificInstrument.InstrumentID = Field.getInstrumentID();
        return event
                .setEpochMicros(micros())
                .setType(FtdcRspType.FtdcSpecificInstrument);
    }

}
