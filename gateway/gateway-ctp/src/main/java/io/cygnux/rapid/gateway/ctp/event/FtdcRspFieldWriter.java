package io.cygnux.rapid.gateway.ctp.event;

import io.cygnux.rapid.gateway.ctp.consts.FtdcFrontDisconnectedReason;
import io.cygnux.rapid.gateway.ctp.event.source.EventSource;
import io.cygnux.rapid.gateway.ctp.event.source.SpecificInstrumentSource;
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

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

public final class FtdcRspFieldWriter {

    private FtdcRspFieldWriter() {
    }

    /**
     * @param event                FtdcRspEvent
     * @param field CThostFtdcDepthMarketDataField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeDepthMarketData(FtdcRspEvent event,
                                                    CThostFtdcDepthMarketDataField field) {
        var depthMarketData = event.getDepthMarketData();
        depthMarketData.RecvEpochMicros = micros();
        // 交易日, 合约ID, 交易所ID, 合约在交易所的代码
        depthMarketData.TradingDay = field.getTradingDay();
        depthMarketData.InstrumentID = field.getInstrumentID();
//        depthMarketData.ExchangeID = field.getExchangeID();
//        depthMarketData.ExchangeInstID = field.getExchangeInstID();
        // 最新价
        depthMarketData.LastPrice = field.getLastPrice();
        // 昨结算价, 昨收盘, 昨持仓量
        depthMarketData.PreSettlementPrice = field.getPreSettlementPrice();
        depthMarketData.PreClosePrice = field.getPreClosePrice();
        depthMarketData.PreOpenInterest = field.getPreOpenInterest();
        // 开盘价, 最高价, 最低价
        depthMarketData.OpenPrice = field.getOpenPrice();
        depthMarketData.HighestPrice = field.getHighestPrice();
        depthMarketData.LowestPrice = field.getLowestPrice();
        // 成交量, 成交金额, 持仓量
        depthMarketData.Volume = field.getVolume();
        depthMarketData.Turnover = field.getTurnover();
        depthMarketData.OpenInterest = field.getOpenInterest();
        // 收盘价, 结算价
//        depthMarketData.ClosePrice = field.getClosePrice();
//        depthMarketData.SettlementPrice = field.getSettlementPrice();
        // 涨停板价, 跌停板价
        depthMarketData.UpperLimitPrice = field.getUpperLimitPrice();
        depthMarketData.LowerLimitPrice = field.getLowerLimitPrice();
        // 昨Delta, 今Delta
//        depthMarketData.PreDelta = field.getPreDelta();
//        depthMarketData.CurrDelta = field.getCurrDelta();
        // 五档买价卖价及买量卖量 v
        depthMarketData.BidPrice1 = field.getBidPrice1();
        depthMarketData.BidVolume1 = field.getBidVolume1();
        depthMarketData.AskPrice1 = field.getAskPrice1();
        depthMarketData.AskVolume1 = field.getAskVolume1();
//        depthMarketData.BidPrice2 = field.getBidPrice2();
//        depthMarketData.BidVolume2 = field.getBidVolume2();
//        depthMarketData.AskPrice2 = field.getAskPrice2();
//        depthMarketData.AskVolume2 = field.getAskVolume2();
//        depthMarketData.BidPrice3 = field.getBidPrice3();
//        depthMarketData.BidVolume3 = field.getBidVolume3();
//        depthMarketData.AskPrice3 = field.getAskPrice3();
//        depthMarketData.AskVolume3 = field.getAskVolume3();
//        depthMarketData.BidPrice4 = field.getBidPrice4();
//        depthMarketData.BidVolume4 = field.getBidVolume4();
//        depthMarketData.AskPrice4 = field.getAskPrice4();
//        depthMarketData.AskVolume4 = field.getAskVolume4();
//        depthMarketData.BidPrice5 = field.getBidPrice5();
//        depthMarketData.BidVolume5 = field.getBidVolume5();
//        depthMarketData.AskPrice5 = field.getAskPrice5();
//        depthMarketData.AskVolume5 = field.getAskVolume5();
        // 五档买价卖价及买量卖量 ^
        // 平均价格
        depthMarketData.AveragePrice = field.getAveragePrice();
        // 更新时间, 更新毫秒数, 业务日期
        depthMarketData.UpdateTime = field.getUpdateTime();
        depthMarketData.UpdateMillisec = field.getUpdateMillisec();
        depthMarketData.ActionDay = field.getActionDay();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_DEPTH_MARKET_DATA);
    }

    /**
     * @param event             FtdcRspEvent
     * @param source       EventSource
     * @param field CThostFtdcRspUserLoginField
     * @param rspInfoField      CThostFtdcRspInfoField
     * @param requestID         int
     * @param isLast            boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspUserLogin(FtdcRspEvent event, EventSource source,
                                                 CThostFtdcRspUserLoginField field,
                                                 CThostFtdcRspInfoField rspInfoField,
                                                 int requestID, boolean isLast) {
        var rspUserLogin = event.getRspUserLogin();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        rspUserLogin.Source = source;
        rspUserLogin.ErrorID = rspInfoField.getErrorID();
        rspUserLogin.ErrorMsg = rspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        rspUserLogin.RequestID = requestID;
        rspUserLogin.IsLast = isLast;
        // 交易日
        rspUserLogin.TradingDay = field.getTradingDay();
        // 登录成功时间
        rspUserLogin.LoginTime = field.getLoginTime();
        // 经纪公司代码
        rspUserLogin.BrokerID = field.getBrokerID();
        // 用户代码
        rspUserLogin.UserID = field.getUserID();
        // 交易系统名称
        rspUserLogin.SystemName = field.getSystemName();
        // 前置编号
        rspUserLogin.FrontID = field.getFrontID();
        // 会话编号
        rspUserLogin.SessionID = field.getSessionID();
        // 最大报单引用
        rspUserLogin.MaxOrderRef = field.getMaxOrderRef();
        // 上期所时间
        rspUserLogin.SHFETime = field.getSHFETime();
        // 大商所时间
        rspUserLogin.DCETime = field.getDCETime();
        // 郑商所时间
        rspUserLogin.CZCETime = field.getCZCETime();
        // 中金所时间
        rspUserLogin.FFEXTime = field.getFFEXTime();
        // 能源中心时间
        rspUserLogin.INETime = field.getINETime();
        // 广期所时间
        rspUserLogin.GFEXTime = field.getGFEXTime();
        // 后台版本信息
        rspUserLogin.SysVersion = field.getSysVersion();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.RSP_USER_LOGIN);
    }

    /**
     * @param event           FtdcRspEvent
     * @param source     EventSource
     * @param field CThostFtdcUserLogoutField
     * @param rspInfoField    CThostFtdcRspInfoField
     * @param requestID       int
     * @param isLast          boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspUserLogout(FtdcRspEvent event, EventSource source,
                                                  CThostFtdcUserLogoutField field,
                                                  CThostFtdcRspInfoField rspInfoField,
                                                  int requestID, boolean isLast) {
        var userLogout = event.getUserLogout();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        userLogout.Source = source;
        userLogout.ErrorID = rspInfoField.getErrorID();
        userLogout.ErrorMsg = rspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        userLogout.RequestID = requestID;
        userLogout.IsLast = isLast;
        // 经纪公司代码, 用户代码
        userLogout.BrokerID = field.getBrokerID();
        userLogout.UserID = field.getUserID();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.USER_LOGOUT);
    }

    /**
     * @param event       FtdcRspEvent
     * @param source EventSource
     * @param reason      int
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeFrontDisconnected(FtdcRspEvent event,
                                                      EventSource source, int reason) {
        var frontDisconnected = event.getFrontDisconnected();
        // 事件来源
        frontDisconnected.Source = source;
        // 错误原因
        frontDisconnected.Msg = "FrontDisconnected-" + FtdcFrontDisconnectedReason.getPrompt(reason);
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FRONT_DISCONNECTED);
    }

    /**
     * @param event       FtdcRspEvent
     * @param source EventSource
     * @param timeLapse   int
     * @param brokerID    String
     * @param userID      String
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeHeartBeatWarning(FtdcRspEvent event,
                                                     EventSource source, int timeLapse,
                                                     String brokerID, String userID) {
        var heartBeatWarning = event.getHeartBeatWarning();
        // 事件来源
        heartBeatWarning.Source = source;
        // 距离上次接收报文的时间
        heartBeatWarning.TimeLapse = timeLapse;
        // 经纪公司代码
        heartBeatWarning.BrokerID = brokerID;
        // 用户代码
        heartBeatWarning.UserID = userID;
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.HEARTBEAT_WARNING);
    }

    /**
     * @param event        FtdcRspEvent
     * @param source  EventSource
     * @param field CThostFtdcRspInfoField
     * @param requestID    int
     * @param isLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspError(FtdcRspEvent event,
                                             EventSource source,
                                             CThostFtdcRspInfoField field,
                                             int requestID, boolean isLast) {
        var rspError = event.getRspError();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        rspError.Source = source;
        rspError.ErrorID = field.getErrorID();
        rspError.ErrorMsg = field.getErrorMsg();
        // 请求ID, 是否最后一条信息
        rspError.RequestID = requestID;
        rspError.IsLast = isLast;
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.RSP_ERROR);
    }

    /**
     * @param event                 FtdcRspEvent
     * @param field CThostFtdcInstrumentStatusField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInstrumentStatus(FtdcRspEvent event,
                                                     CThostFtdcInstrumentStatusField field) {
        var instrumentStatus = event.getInstrumentStatus();
        // 交易所代码, 合约在交易所的代码, 结算组代码, 合约代码
        instrumentStatus.ExchangeID = field.getExchangeID();
        instrumentStatus.ExchangeInstID = field.getExchangeInstID();
        instrumentStatus.SettlementGroupID = field.getSettlementGroupID();
        instrumentStatus.InstrumentID = field.getInstrumentID();
        // 合约交易状态, 交易阶段编号, 进入本状态时间, 进入本状态原因
        instrumentStatus.InstrumentStatus = field.getInstrumentStatus();
        instrumentStatus.TradingSegmentSN = field.getTradingSegmentSN();
        instrumentStatus.EnterTime = field.getEnterTime();
        instrumentStatus.EnterReason = field.getEnterReason();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INSTRUMENT_STATUS);
    }

    /**
     * @param event           FtdcRspEvent
     * @param field CThostFtdcInputOrderField
     * @param rspInfoField    CThostFtdcRspInfoField
     * @param isLast          boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrder(FtdcRspEvent event,
                                               CThostFtdcInputOrderField field,
                                               CThostFtdcRspInfoField rspInfoField,
                                               boolean isLast) {
        var inputOrder = event.getInputOrder();
        // [FTDC响应信息] - 错误代码, 错误信息
        inputOrder.ErrorID = rspInfoField.getErrorID();
        inputOrder.ErrorMsg = rspInfoField.getErrorMsg();
        inputOrder.IsLast = isLast;
        inputOrder.BrokerID = field.getBrokerID();
        inputOrder.InvestorID = field.getInvestorID();
        inputOrder.InstrumentID = field.getInstrumentID();
        inputOrder.OrderRef = field.getOrderRef();
        inputOrder.UserID = field.getUserID();
        inputOrder.OrderPriceType = field.getOrderPriceType();
        inputOrder.Direction = field.getDirection();
        inputOrder.CombOffsetFlag = field.getCombOffsetFlag();
        inputOrder.CombHedgeFlag = field.getCombHedgeFlag();
        inputOrder.LimitPrice = field.getLimitPrice();
        inputOrder.VolumeTotalOriginal = field.getVolumeTotalOriginal();
        inputOrder.TimeCondition = field.getTimeCondition();
        inputOrder.GTDDate = field.getGTDDate();
        inputOrder.VolumeCondition = field.getVolumeCondition();
        inputOrder.MinVolume = field.getMinVolume();
        inputOrder.ContingentCondition = field.getContingentCondition();
        inputOrder.StopPrice = field.getStopPrice();
        inputOrder.ForceCloseReason = field.getForceCloseReason();
        inputOrder.IsAutoSuspend = field.getIsAutoSuspend();
        inputOrder.BusinessUnit = field.getBusinessUnit();
        inputOrder.RequestID = field.getRequestID();
        inputOrder.UserForceClose = field.getUserForceClose();
        inputOrder.IsSwapOrder = field.getIsSwapOrder();
        inputOrder.ExchangeID = field.getExchangeID();
        inputOrder.InvestUnitID = field.getInvestUnitID();
        inputOrder.AccountID = field.getAccountID();
        inputOrder.CurrencyID = field.getCurrencyID();
        inputOrder.ClientID = field.getClientID();
        inputOrder.IPAddress = field.getIPAddress();
        inputOrder.MacAddress = field.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INPUT_ORDER);
    }

    /**
     * @param event                 FtdcRspEvent
     * @param field CThostFtdcInputOrderActionField
     * @param rspInfoField          CThostFtdcRspInfoField
     * @param isLast                boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrderAction(FtdcRspEvent event,
                                                     CThostFtdcInputOrderActionField field,
                                                     CThostFtdcRspInfoField rspInfoField,
                                                     boolean isLast) {
        var inputOrderAction = event.getInputOrderAction();
        // [FTDC响应信息] - 错误代码, 错误信息
        inputOrderAction.ErrorID = rspInfoField.getErrorID();
        inputOrderAction.ErrorMsg = rspInfoField.getErrorMsg();
        inputOrderAction.IsLast = isLast;
        inputOrderAction.BrokerID = field.getBrokerID();
        inputOrderAction.InvestorID = field.getInvestorID();
        inputOrderAction.OrderActionRef = field.getOrderActionRef();
        inputOrderAction.OrderRef = field.getOrderRef();
        inputOrderAction.RequestID = field.getRequestID();
        inputOrderAction.FrontID = field.getFrontID();
        inputOrderAction.SessionID = field.getSessionID();
        inputOrderAction.ExchangeID = field.getExchangeID();
        inputOrderAction.OrderSysID = field.getOrderSysID();
        inputOrderAction.ActionFlag = field.getActionFlag();
        inputOrderAction.LimitPrice = field.getLimitPrice();
        inputOrderAction.VolumeChange = field.getVolumeChange();
        inputOrderAction.UserID = field.getUserID();
        inputOrderAction.InstrumentID = field.getInstrumentID();
        inputOrderAction.InvestUnitID = field.getInvestUnitID();
        inputOrderAction.IPAddress = field.getIPAddress();
        inputOrderAction.MacAddress = field.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INPUT_ORDER_ACTION);
    }


    /**
     * @param event                 FtdcRspEvent
     * @param field CThostFtdcInvestorPositionField
     * @param rspInfoField          CThostFtdcRspInfoField
     * @param requestID             int
     * @param isLast                boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInvestorPosition(FtdcRspEvent event,
                                                     CThostFtdcInvestorPositionField field,
                                                     CThostFtdcRspInfoField rspInfoField,
                                                     int requestID, boolean isLast) {
        var investorPosition = event.getInvestorPosition();
        // [FTDC响应信息] - 错误代码, 错误信息
        investorPosition.ErrorID = rspInfoField.getErrorID();
        investorPosition.ErrorMsg = rspInfoField.getErrorMsg();
        investorPosition.RequestID = requestID;
        investorPosition.IsLast = isLast;
        // 合约代码
        investorPosition.InstrumentID = field.getInstrumentID();
        // 经纪公司代码
        investorPosition.BrokerID = field.getBrokerID();
        // 投资者代码
        investorPosition.InvestorID = field.getInvestorID();
        // 持仓多空方向
        investorPosition.PosiDirection = field.getPosiDirection();
        // 投机套保标志
        investorPosition.HedgeFlag = field.getHedgeFlag();
        // 持仓日期
        investorPosition.PositionDate = field.getPositionDate();
        // 上日持仓
        investorPosition.YdPosition = field.getYdPosition();
        // 今日持仓
        investorPosition.Position = field.getPosition();
        // 多头冻结
        investorPosition.LongFrozen = field.getLongFrozen();
        // 空头冻结
        investorPosition.ShortFrozen = field.getShortFrozen();
        // 开仓冻结金额
        investorPosition.LongFrozenAmount = field.getLongFrozenAmount();
        // 开仓冻结金额
        investorPosition.ShortFrozenAmount = field.getShortFrozenAmount();
        // 开仓量
        investorPosition.OpenVolume = field.getOpenVolume();
        // 平仓量
        investorPosition.CloseVolume = field.getCloseVolume();
        // 开仓金额
        investorPosition.OpenAmount = field.getOpenAmount();
        // 平仓金额
        investorPosition.CloseAmount = field.getCloseAmount();
        // 持仓成本
        investorPosition.PositionCost = field.getPositionCost();
        // 上次占用的保证金
        investorPosition.PreMargin = field.getPreMargin();
        // 占用的保证金
        investorPosition.UseMargin = field.getUseMargin();
        // 冻结的保证金
        investorPosition.FrozenMargin = field.getFrozenMargin();
        // 冻结的资金
        investorPosition.FrozenCash = field.getFrozenCash();
        // 冻结的手续费
        investorPosition.FrozenCommission = field.getFrozenCommission();
        // 资金差额
        investorPosition.CashIn = field.getCashIn();
        // 手续费
        investorPosition.Commission = field.getCommission();
        // 平仓盈亏
        investorPosition.CloseProfit = field.getCloseProfit();
        // 持仓盈亏
        investorPosition.PositionProfit = field.getPositionProfit();
        // 上次结算价
        investorPosition.PreSettlementPrice = field.getPreSettlementPrice();
        // 本次结算价
        investorPosition.SettlementPrice = field.getSettlementPrice();
        // 交易日
        investorPosition.TradingDay = field.getTradingDay();
        // 结算编号
        investorPosition.SettlementID = field.getSettlementID();
        // 开仓成本
        investorPosition.OpenCost = field.getOpenCost();
        // 交易所保证金
        investorPosition.ExchangeMargin = field.getExchangeMargin();
        // 组合成交形成的持仓
        investorPosition.CombPosition = field.getCombPosition();
        // 组合多头冻结
        investorPosition.CombLongFrozen = field.getCombLongFrozen();
        // 组合空头冻结
        investorPosition.CombShortFrozen = field.getCombShortFrozen();
        // 逐日盯市平仓盈亏
        investorPosition.CloseProfitByDate = field.getCloseProfitByDate();
        // 逐笔对冲平仓盈亏
        investorPosition.CloseProfitByTrade = field.getCloseProfitByTrade();
        // 今日持仓
        investorPosition.TodayPosition = field.getTodayPosition();
        // 保证金率
        investorPosition.MarginRateByMoney = field.getMarginRateByMoney();
        // 保证金率(按手数)
        investorPosition.MarginRateByVolume = field.getMarginRateByVolume();
        // 执行冻结
        investorPosition.StrikeFrozen = field.getStrikeFrozen();
        // 执行冻结金额
        investorPosition.StrikeFrozenAmount = field.getStrikeFrozenAmount();
        // 放弃执行冻结
        investorPosition.AbandonFrozen = field.getAbandonFrozen();
        // 交易所代码
        investorPosition.ExchangeID = field.getExchangeID();
        // 执行冻结的昨仓
        investorPosition.YdStrikeFrozen = field.getYdStrikeFrozen();
        // 投资单元代码
        investorPosition.InvestUnitID = field.getInvestUnitID();
        // 大商所持仓成本差值, 只有大商所使用
        // 6.3.15 版本使用
        investorPosition.PositionCostOffset = field.getPositionCostOffset();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INVESTOR_POSITION);
    }

    /**
     * @param event      FtdcRspEvent
     * @param field CThostFtdcOrderField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrder(FtdcRspEvent event,
                                          CThostFtdcOrderField field) {
        var order = event.getOrder();
        order.RecvEpochMicros = micros();
        order.BrokerID = field.getBrokerID();
        order.InvestorID = field.getInvestorID();
        order.InstrumentID = field.getInstrumentID();
        order.OrderRef = field.getOrderRef();
        order.UserID = field.getUserID();
        order.OrderPriceType = field.getOrderPriceType();
        order.Direction = field.getDirection();
        order.CombOffsetFlag = field.getCombOffsetFlag();
        order.CombHedgeFlag = field.getCombHedgeFlag();
        order.LimitPrice = field.getLimitPrice();
        order.VolumeTotalOriginal = field.getVolumeTotalOriginal();
        order.TimeCondition = field.getTimeCondition();
        order.GTDDate = field.getGTDDate();
        order.VolumeCondition = field.getVolumeCondition();
        order.MinVolume = field.getMinVolume();
        order.ContingentCondition = field.getContingentCondition();
        order.StopPrice = field.getStopPrice();
        order.ForceCloseReason = field.getForceCloseReason();
        order.IsAutoSuspend = field.getIsAutoSuspend();
        order.BusinessUnit = field.getBusinessUnit();
        order.RequestID = field.getRequestID();
        order.OrderLocalID = field.getOrderLocalID();
        order.ExchangeID = field.getExchangeID();
        order.ParticipantID = field.getParticipantID();
        order.ClientID = field.getClientID();
        order.ExchangeInstID = field.getExchangeInstID();
        order.TraderID = field.getTraderID();
        order.InstallID = field.getInstallID();
        order.OrderSubmitStatus = field.getOrderSubmitStatus();
        order.NotifySequence = field.getNotifySequence();
        order.TradingDay = field.getTradingDay();
        order.SettlementID = field.getSettlementID();
        order.OrderSysID = field.getOrderSysID();
        order.OrderSource = field.getOrderSource();
        order.OrderStatus = field.getOrderStatus();
        order.OrderType = field.getOrderType();
        order.VolumeTraded = field.getVolumeTraded();
        order.VolumeTotal = field.getVolumeTotal();
        order.InsertDate = field.getInsertDate();
        order.InsertTime = field.getInsertTime();
        order.ActiveTime = field.getActiveTime();
        order.SuspendTime = field.getSuspendTime();
        order.UpdateTime = field.getUpdateTime();
        order.CancelTime = field.getCancelTime();
        order.ActiveTraderID = field.getActiveTraderID();
        order.ClearingPartID = field.getClearingPartID();
        order.SequenceNo = field.getSequenceNo();
        order.FrontID = field.getFrontID();
        order.SessionID = field.getSessionID();
        order.UserProductInfo = field.getUserProductInfo();
        order.StatusMsg = field.getStatusMsg();
        order.UserForceClose = field.getUserForceClose();
        order.ActiveUserID = field.getActiveUserID();
        order.BrokerOrderSeq = field.getBrokerOrderSeq();
        order.RelativeOrderSysID = field.getRelativeOrderSysID();
        order.ZCETotalTradedVolume = field.getZCETotalTradedVolume();
        order.IsSwapOrder = field.getIsSwapOrder();
        order.BranchID = field.getBranchID();
        order.InvestUnitID = field.getInvestUnitID();
        order.AccountID = field.getAccountID();
        order.CurrencyID = field.getCurrencyID();
        order.IPAddress = field.getIPAddress();
        order.MacAddress = field.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_ORDER);
    }

    /**
     * @param event            FtdcRspEvent
     * @param field CThostFtdcOrderActionField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrderAction(FtdcRspEvent event,
                                                CThostFtdcOrderActionField field) {
        var orderAction = event.getOrderAction();
        // 经纪公司代码
        orderAction.BrokerID = field.getBrokerID();
        // 投资者代码
        orderAction.InvestorID = field.getInvestorID();
        // 报单操作引用
        orderAction.OrderActionRef = field.getOrderActionRef();
        // 报单引用
        orderAction.OrderRef = field.getOrderRef();
        // 请求编号
        orderAction.RequestID = field.getRequestID();
        // 前置编号
        orderAction.FrontID = field.getFrontID();
        // 会话编号
        orderAction.SessionID = field.getSessionID();
        // 交易所代码
        orderAction.ExchangeID = field.getExchangeID();
        // 报单编号
        orderAction.OrderSysID = field.getOrderSysID();
        // 操作标志
        orderAction.ActionFlag = field.getActionFlag();
        // 价格
        orderAction.LimitPrice = field.getLimitPrice();
        // 数量变化
        orderAction.VolumeChange = field.getVolumeChange();
        // 操作日期
        orderAction.ActionDate = field.getActionDate();
        // 操作时间
        orderAction.ActionTime = field.getActionTime();
        // 交易所交易员代码
        orderAction.TraderID = field.getTraderID();
        // 安装编号
        orderAction.InstallID = field.getInstallID();
        // 本地报单编号
        orderAction.OrderLocalID = field.getOrderLocalID();
        // 操作本地编号
        orderAction.ActionLocalID = field.getActionLocalID();
        // 会员代码
        orderAction.ParticipantID = field.getParticipantID();
        // 客户代码
        orderAction.ClientID = field.getClientID();
        // 业务单元
        orderAction.BusinessUnit = field.getBusinessUnit();
        // 报单操作状态
        orderAction.OrderActionStatus = field.getOrderActionStatus();
        // 用户代码
        orderAction.UserID = field.getUserID();
        // 状态信息
        orderAction.StatusMsg = field.getStatusMsg();
        // 合约代码
        orderAction.InstrumentID = field.getInstrumentID();
        // 营业部编号
        orderAction.BranchID = field.getBranchID();
        // 投资单元代码
        orderAction.InvestUnitID = field.getInvestUnitID();
        // IP地址
        orderAction.IPAddress = field.getIPAddress();
        // MAC地址
        orderAction.MacAddress = field.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_ORDER_ACTION);
    }

    /**
     * @param event      FtdcRspEvent
     * @param field CThostFtdcTradeField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTrade(FtdcRspEvent event,
                                          CThostFtdcTradeField field) {
        var trade = event.getTrade();
        trade.RecvEpochMicros = micros();
        // 经纪公司代码
        trade.BrokerID = field.getBrokerID();
        // 投资者代码
        trade.InvestorID = field.getInvestorID();
        // 合约代码
        trade.InstrumentID = field.getInstrumentID();
        // 报单引用
        trade.OrderRef = field.getOrderRef();
        // 用户代码
        trade.UserID = field.getUserID();
        // 交易所代码
        trade.ExchangeID = field.getExchangeID();
        // 成交编号
        trade.TradeID = field.getTradeID();
        // 买卖方向
        trade.Direction = field.getDirection();
        // 报单编号
        trade.OrderSysID = field.getOrderSysID();
        // 会员代码
        trade.ParticipantID = field.getParticipantID();
        // 客户代码
        trade.ClientID = field.getClientID();
        // 交易角色
        trade.TradingRole = field.getTradingRole();
        // 合约在交易所的代码
        trade.ExchangeInstID = field.getExchangeInstID();
        // 开平标志
        trade.OffsetFlag = field.getOffsetFlag();
        // 投机套保标志
        trade.HedgeFlag = field.getHedgeFlag();
        // 价格
        trade.Price = field.getPrice();
        // 数量
        trade.Volume = field.getVolume();
        // 成交日期
        trade.TradeDate = field.getTradeDate();
        // 成交时间
        trade.TradeTime = field.getTradeTime();
        // 成交类型
        trade.TradeType = field.getTradeType();
        // 成交价来源
        trade.PriceSource = field.getPriceSource();
        // 交易所交易员代码
        trade.TraderID = field.getTraderID();
        // 本地报单编号
        trade.OrderLocalID = field.getOrderLocalID();
        // 结算会员编号
        trade.ClearingPartID = field.getClearingPartID();
        // 业务单元
        trade.BusinessUnit = field.getBusinessUnit();
        // 序号
        trade.SequenceNo = field.getSequenceNo();
        // 交易日
        trade.TradingDay = field.getTradingDay();
        // 结算编号
        trade.SettlementID = field.getSettlementID();
        // 经纪公司报单编号
        trade.BrokerOrderSeq = field.getBrokerOrderSeq();
        // 成交来源
        trade.TradeSource = field.getTradeSource();
        // 投资单元代码
        trade.InvestUnitID = field.getInvestUnitID();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_TRADE);
    }

    /**
     * @param event               FtdcRspEvent
     * @param field CThostFtdcTradingAccountField
     * @param rspInfoField        CThostFtdcRspInfoField
     * @param requestID           int
     * @param isLast              boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTradingAccount(FtdcRspEvent event,
                                                   CThostFtdcTradingAccountField field,
                                                   CThostFtdcRspInfoField rspInfoField,
                                                   int requestID, boolean isLast) {
        var tradingAccount = event.getTradingAccount();
        tradingAccount.ErrorID = rspInfoField.getErrorID();
        tradingAccount.ErrorMsg = rspInfoField.getErrorMsg();
        tradingAccount.RequestID = requestID;
        tradingAccount.IsLast = isLast;
        // 经纪公司代码
        tradingAccount.BrokerID = field.getBrokerID();
        // 投资者账号
        tradingAccount.AccountID = field.getAccountID();
        // 上次质押金额
        tradingAccount.PreMortgage = field.getPreMortgage();
        // 上次信用额度
        tradingAccount.PreCredit = field.getPreCredit();
        // 上次存款额
        tradingAccount.PreDeposit = field.getPreDeposit();
        // 上次结算准备金
        tradingAccount.PreBalance = field.getPreBalance();
        // 上次占用的保证金
        tradingAccount.PreMargin = field.getPreMargin();
        // 利息基数
        tradingAccount.InterestBase = field.getInterestBase();
        // 利息收入
        tradingAccount.Interest = field.getInterest();
        // 入金金额
        tradingAccount.Deposit = field.getDeposit();
        // 出金金额
        tradingAccount.Withdraw = field.getWithdraw();
        // 冻结的保证金
        tradingAccount.FrozenMargin = field.getFrozenMargin();
        // 冻结的资金
        tradingAccount.FrozenCash = field.getFrozenCash();
        // 冻结的手续费
        tradingAccount.FrozenCommission = field.getFrozenCommission();
        // 当前保证金总额
        tradingAccount.CurrMargin = field.getCurrMargin();
        // 资金差额
        tradingAccount.CashIn = field.getCashIn();
        // 手续费
        tradingAccount.Commission = field.getCommission();
        // 平仓盈亏
        tradingAccount.CloseProfit = field.getCloseProfit();
        // 持仓盈亏
        tradingAccount.PositionProfit = field.getPositionProfit();
        // 期货结算准备金
        tradingAccount.Balance = field.getBalance();
        // 可用资金
        tradingAccount.Available = field.getAvailable();
        // 可取资金
        tradingAccount.WithdrawQuota = field.getWithdrawQuota();
        // 基本准备金
        tradingAccount.Reserve = field.getReserve();
        // 交易日
        tradingAccount.TradingDay = field.getTradingDay();
        // 结算编号
        tradingAccount.SettlementID = field.getSettlementID();
        // 信用额度
        tradingAccount.Credit = field.getCredit();
        // 质押金额
        tradingAccount.Mortgage = field.getMortgage();
        // 交易所保证金
        tradingAccount.ExchangeMargin = field.getExchangeMargin();
        // 投资者交割保证金
        tradingAccount.DeliveryMargin = field.getDeliveryMargin();
        // 交易所交割保证金
        tradingAccount.ExchangeDeliveryMargin = field.getExchangeDeliveryMargin();
        // 保底期货结算准备金
        tradingAccount.ReserveBalance = field.getReserveBalance();
        // 币种代码
        tradingAccount.CurrencyID = field.getCurrencyID();
        // 上次货币质入金额
        tradingAccount.PreFundMortgageIn = field.getPreFundMortgageIn();
        // 上次货币质出金额
        tradingAccount.PreFundMortgageOut = field.getPreFundMortgageOut();
        // 货币质入金额
        tradingAccount.FundMortgageIn = field.getFundMortgageIn();
        // 货币质出金额
        tradingAccount.FundMortgageOut = field.getFundMortgageOut();
        // 货币质押余额
        tradingAccount.FundMortgageAvailable = field.getFundMortgageAvailable();
        // 可质押货币金额
        tradingAccount.MortgageableFund = field.getMortgageableFund();
        // 特殊产品占用保证金
        tradingAccount.SpecProductMargin = field.getSpecProductMargin();
        // 特殊产品冻结保证金
        tradingAccount.SpecProductFrozenMargin = field.getSpecProductFrozenMargin();
        // 特殊产品手续费
        tradingAccount.SpecProductCommission = field.getSpecProductCommission();
        // 特殊产品冻结手续费
        tradingAccount.SpecProductFrozenCommission = field.getSpecProductFrozenCommission();
        // 特殊产品持仓盈亏
        tradingAccount.SpecProductPositionProfit = field.getSpecProductPositionProfit();
        // 特殊产品平仓盈亏
        tradingAccount.SpecProductCloseProfit = field.getSpecProductCloseProfit();
        // 根据持仓盈亏算法计算的特殊产品持仓盈亏
        tradingAccount.SpecProductPositionProfitByAlg = field.getSpecProductPositionProfitByAlg();
        // 特殊产品交易所保证金
        tradingAccount.SpecProductExchangeMargin = field.getSpecProductExchangeMargin();
        // 业务类型
        tradingAccount.BizType = field.getBizType();
        // 延时换汇冻结金额
        tradingAccount.FrozenSwap = field.getFrozenSwap();
        // 剩余换汇额度
        tradingAccount.RemainSwap = field.getRemainSwap();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_TRADING_ACCOUNT);
    }

    /**
     * @param event                    FtdcRspEvent
     * @param source SpecificInstrumentSource
     * @param field  CThostFtdcSpecificInstrumentField
     * @param rspInfoField             CThostFtdcRspInfoField
     * @param requestID                int
     * @param isLast                   boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeSpecificInstrument(FtdcRspEvent event,
                                                       SpecificInstrumentSource source,
                                                       CThostFtdcSpecificInstrumentField field,
                                                       CThostFtdcRspInfoField rspInfoField,
                                                       int requestID, boolean isLast) {
        var specificInstrument = event.getSpecificInstrument();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        specificInstrument.Source = source;
        specificInstrument.ErrorID = rspInfoField.getErrorID();
        specificInstrument.ErrorMsg = rspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        specificInstrument.RequestID = requestID;
        specificInstrument.IsLast = isLast;
        // 合约代码
        specificInstrument.InstrumentID = field.getInstrumentID();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_SPECIFIC_INSTRUMENT);
    }

}
