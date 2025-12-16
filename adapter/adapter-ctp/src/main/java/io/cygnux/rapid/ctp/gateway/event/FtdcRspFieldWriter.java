package io.cygnux.rapid.ctp.gateway.event;

import io.cygnux.rapid.ctp.gateway.consts.FtdcFrontDisconnectedReason;
import io.cygnux.rapid.ctp.gateway.event.source.EventSource;
import io.cygnux.rapid.ctp.gateway.event.source.SpecificInstrumentSource;
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
     * @param depthMarketDataField CThostFtdcDepthMarketDataField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeDepthMarketData(FtdcRspEvent event,
                                                    CThostFtdcDepthMarketDataField depthMarketDataField) {
        var depthMarketData = event.getDepthMarketData();
        depthMarketData.RecvEpochMicros = micros();
        // 交易日, 合约ID, 交易所ID, 合约在交易所的代码
        depthMarketData.TradingDay = depthMarketDataField.getTradingDay();
        depthMarketData.InstrumentID = depthMarketDataField.getInstrumentID();
//        depthMarketData.ExchangeID = depthMarketDataField.getExchangeID();
//        depthMarketData.ExchangeInstID = depthMarketDataField.getExchangeInstID();
        // 最新价
        depthMarketData.LastPrice = depthMarketDataField.getLastPrice();
        // 昨结算价, 昨收盘, 昨持仓量
        depthMarketData.PreSettlementPrice = depthMarketDataField.getPreSettlementPrice();
        depthMarketData.PreClosePrice = depthMarketDataField.getPreClosePrice();
        depthMarketData.PreOpenInterest = depthMarketDataField.getPreOpenInterest();
        // 开盘价, 最高价, 最低价
        depthMarketData.OpenPrice = depthMarketDataField.getOpenPrice();
        depthMarketData.HighestPrice = depthMarketDataField.getHighestPrice();
        depthMarketData.LowestPrice = depthMarketDataField.getLowestPrice();
        // 成交量, 成交金额, 持仓量
        depthMarketData.Volume = depthMarketDataField.getVolume();
        depthMarketData.Turnover = depthMarketDataField.getTurnover();
        depthMarketData.OpenInterest = depthMarketDataField.getOpenInterest();
        // 收盘价, 结算价
//        depthMarketData.ClosePrice = depthMarketDataField.getClosePrice();
//        depthMarketData.SettlementPrice = depthMarketDataField.getSettlementPrice();
        // 涨停板价, 跌停板价
        depthMarketData.UpperLimitPrice = depthMarketDataField.getUpperLimitPrice();
        depthMarketData.LowerLimitPrice = depthMarketDataField.getLowerLimitPrice();
        // 昨Delta, 今Delta
//        depthMarketData.PreDelta = depthMarketDataField.getPreDelta();
//        depthMarketData.CurrDelta = depthMarketDataField.getCurrDelta();
        // 五档买价卖价及买量卖量 v
        depthMarketData.BidPrice1 = depthMarketDataField.getBidPrice1();
        depthMarketData.BidVolume1 = depthMarketDataField.getBidVolume1();
        depthMarketData.AskPrice1 = depthMarketDataField.getAskPrice1();
        depthMarketData.AskVolume1 = depthMarketDataField.getAskVolume1();
//        depthMarketData.BidPrice2 = depthMarketDataField.getBidPrice2();
//        depthMarketData.BidVolume2 = depthMarketDataField.getBidVolume2();
//        depthMarketData.AskPrice2 = depthMarketDataField.getAskPrice2();
//        depthMarketData.AskVolume2 = depthMarketDataField.getAskVolume2();
//        depthMarketData.BidPrice3 = depthMarketDataField.getBidPrice3();
//        depthMarketData.BidVolume3 = depthMarketDataField.getBidVolume3();
//        depthMarketData.AskPrice3 = depthMarketDataField.getAskPrice3();
//        depthMarketData.AskVolume3 = depthMarketDataField.getAskVolume3();
//        depthMarketData.BidPrice4 = depthMarketDataField.getBidPrice4();
//        depthMarketData.BidVolume4 = depthMarketDataField.getBidVolume4();
//        depthMarketData.AskPrice4 = depthMarketDataField.getAskPrice4();
//        depthMarketData.AskVolume4 = depthMarketDataField.getAskVolume4();
//        depthMarketData.BidPrice5 = depthMarketDataField.getBidPrice5();
//        depthMarketData.BidVolume5 = depthMarketDataField.getBidVolume5();
//        depthMarketData.AskPrice5 = depthMarketDataField.getAskPrice5();
//        depthMarketData.AskVolume5 = depthMarketDataField.getAskVolume5();
        // 五档买价卖价及买量卖量 ^
        // 平均价格
        depthMarketData.AveragePrice = depthMarketDataField.getAveragePrice();
        // 更新时间, 更新毫秒数, 业务日期
        depthMarketData.UpdateTime = depthMarketDataField.getUpdateTime();
        depthMarketData.UpdateMillisec = depthMarketDataField.getUpdateMillisec();
        depthMarketData.ActionDay = depthMarketDataField.getActionDay();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_DEPTH_MARKET_DATA);
    }

    /**
     * @param event             FtdcRspEvent
     * @param eventSource       EventSource
     * @param rspUserLoginField CThostFtdcRspUserLoginField
     * @param rspInfoField      CThostFtdcRspInfoField
     * @param requestID         int
     * @param isLast            boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspUserLogin(FtdcRspEvent event, EventSource eventSource,
                                                 CThostFtdcRspUserLoginField rspUserLoginField,
                                                 CThostFtdcRspInfoField rspInfoField,
                                                 int requestID, boolean isLast) {
        var rspUserLogin = event.getRspUserLogin();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        rspUserLogin.Source = eventSource;
        rspUserLogin.ErrorID = rspInfoField.getErrorID();
        rspUserLogin.ErrorMsg = rspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        rspUserLogin.RequestID = requestID;
        rspUserLogin.IsLast = isLast;
        // 交易日
        rspUserLogin.TradingDay = rspUserLoginField.getTradingDay();
        // 登录成功时间
        rspUserLogin.LoginTime = rspUserLoginField.getLoginTime();
        // 经纪公司代码
        rspUserLogin.BrokerID = rspUserLoginField.getBrokerID();
        // 用户代码
        rspUserLogin.UserID = rspUserLoginField.getUserID();
        // 交易系统名称
        rspUserLogin.SystemName = rspUserLoginField.getSystemName();
        // 前置编号
        rspUserLogin.FrontID = rspUserLoginField.getFrontID();
        // 会话编号
        rspUserLogin.SessionID = rspUserLoginField.getSessionID();
        // 最大报单引用
        rspUserLogin.MaxOrderRef = rspUserLoginField.getMaxOrderRef();
        // 上期所时间
        rspUserLogin.SHFETime = rspUserLoginField.getSHFETime();
        // 大商所时间
        rspUserLogin.DCETime = rspUserLoginField.getDCETime();
        // 郑商所时间
        rspUserLogin.CZCETime = rspUserLoginField.getCZCETime();
        // 中金所时间
        rspUserLogin.FFEXTime = rspUserLoginField.getFFEXTime();
        // 能源中心时间
        rspUserLogin.INETime = rspUserLoginField.getINETime();
        // 广期所时间
        rspUserLogin.GFEXTime = rspUserLoginField.getGFEXTime();
        // 后台版本信息
        rspUserLogin.SysVersion = rspUserLoginField.getSysVersion();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.RSP_USER_LOGIN);
    }

    /**
     * @param event           FtdcRspEvent
     * @param eventSource     EventSource
     * @param userLogoutField CThostFtdcUserLogoutField
     * @param rspInfoField    CThostFtdcRspInfoField
     * @param requestID       int
     * @param isLast          boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspUserLogout(FtdcRspEvent event, EventSource eventSource,
                                                  CThostFtdcUserLogoutField userLogoutField,
                                                  CThostFtdcRspInfoField rspInfoField,
                                                  int requestID, boolean isLast) {
        var userLogout = event.getUserLogout();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        userLogout.Source = eventSource;
        userLogout.ErrorID = rspInfoField.getErrorID();
        userLogout.ErrorMsg = rspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        userLogout.RequestID = requestID;
        userLogout.IsLast = isLast;
        // 经纪公司代码, 用户代码
        userLogout.BrokerID = userLogoutField.getBrokerID();
        userLogout.UserID = userLogoutField.getUserID();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.USER_LOGOUT);
    }

    /**
     * @param event       FtdcRspEvent
     * @param eventSource EventSource
     * @param reason      int
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeFrontDisconnected(FtdcRspEvent event,
                                                      EventSource eventSource, int reason) {
        var frontDisconnected = event.getFrontDisconnected();
        // 事件来源
        frontDisconnected.Source = eventSource;
        // 错误原因
        frontDisconnected.Msg = "FrontDisconnected-" + FtdcFrontDisconnectedReason.getPrompt(reason);
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FRONT_DISCONNECTED);
    }

    /**
     * @param event       FtdcRspEvent
     * @param eventSource EventSource
     * @param timeLapse   int
     * @param brokerID    String
     * @param userID      String
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeHeartBeatWarning(FtdcRspEvent event,
                                                     EventSource eventSource, int timeLapse,
                                                     String brokerID, String userID) {
        var heartBeatWarning = event.getHeartBeatWarning();
        // 事件来源
        heartBeatWarning.Source = eventSource;
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
     * @param eventSource  EventSource
     * @param rspInfoField CThostFtdcRspInfoField
     * @param requestID    int
     * @param isLast       boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeRspError(FtdcRspEvent event,
                                             EventSource eventSource,
                                             CThostFtdcRspInfoField rspInfoField,
                                             int requestID, boolean isLast) {
        var rspError = event.getRspError();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        rspError.Source = eventSource;
        rspError.ErrorID = rspInfoField.getErrorID();
        rspError.ErrorMsg = rspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        rspError.RequestID = requestID;
        rspError.IsLast = isLast;
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.RSP_ERROR);
    }

    /**
     * @param event                 FtdcRspEvent
     * @param instrumentStatusField CThostFtdcInstrumentStatusField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInstrumentStatus(FtdcRspEvent event,
                                                     CThostFtdcInstrumentStatusField instrumentStatusField) {
        var instrumentStatus = event.getInstrumentStatus();
        // 交易所代码, 合约在交易所的代码, 结算组代码, 合约代码
        instrumentStatus.ExchangeID = instrumentStatusField.getExchangeID();
        instrumentStatus.ExchangeInstID = instrumentStatusField.getExchangeInstID();
        instrumentStatus.SettlementGroupID = instrumentStatusField.getSettlementGroupID();
        instrumentStatus.InstrumentID = instrumentStatusField.getInstrumentID();
        // 合约交易状态, 交易阶段编号, 进入本状态时间, 进入本状态原因
        instrumentStatus.InstrumentStatus = instrumentStatusField.getInstrumentStatus();
        instrumentStatus.TradingSegmentSN = instrumentStatusField.getTradingSegmentSN();
        instrumentStatus.EnterTime = instrumentStatusField.getEnterTime();
        instrumentStatus.EnterReason = instrumentStatusField.getEnterReason();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INSTRUMENT_STATUS);
    }

    /**
     * @param event           FtdcRspEvent
     * @param inputOrderField CThostFtdcInputOrderField
     * @param rspInfoField    CThostFtdcRspInfoField
     * @param isLast          boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrder(FtdcRspEvent event,
                                               CThostFtdcInputOrderField inputOrderField,
                                               CThostFtdcRspInfoField rspInfoField,
                                               boolean isLast) {
        var inputOrder = event.getInputOrder();
        // [FTDC响应信息] - 错误代码, 错误信息
        inputOrder.ErrorID = rspInfoField.getErrorID();
        inputOrder.ErrorMsg = rspInfoField.getErrorMsg();
        inputOrder.IsLast = isLast;
        inputOrder.BrokerID = inputOrderField.getBrokerID();
        inputOrder.InvestorID = inputOrderField.getInvestorID();
        inputOrder.InstrumentID = inputOrderField.getInstrumentID();
        inputOrder.OrderRef = inputOrderField.getOrderRef();
        inputOrder.UserID = inputOrderField.getUserID();
        inputOrder.OrderPriceType = inputOrderField.getOrderPriceType();
        inputOrder.Direction = inputOrderField.getDirection();
        inputOrder.CombOffsetFlag = inputOrderField.getCombOffsetFlag();
        inputOrder.CombHedgeFlag = inputOrderField.getCombHedgeFlag();
        inputOrder.LimitPrice = inputOrderField.getLimitPrice();
        inputOrder.VolumeTotalOriginal = inputOrderField.getVolumeTotalOriginal();
        inputOrder.TimeCondition = inputOrderField.getTimeCondition();
        inputOrder.GTDDate = inputOrderField.getGTDDate();
        inputOrder.VolumeCondition = inputOrderField.getVolumeCondition();
        inputOrder.MinVolume = inputOrderField.getMinVolume();
        inputOrder.ContingentCondition = inputOrderField.getContingentCondition();
        inputOrder.StopPrice = inputOrderField.getStopPrice();
        inputOrder.ForceCloseReason = inputOrderField.getForceCloseReason();
        inputOrder.IsAutoSuspend = inputOrderField.getIsAutoSuspend();
        inputOrder.BusinessUnit = inputOrderField.getBusinessUnit();
        inputOrder.RequestID = inputOrderField.getRequestID();
        inputOrder.UserForceClose = inputOrderField.getUserForceClose();
        inputOrder.IsSwapOrder = inputOrderField.getIsSwapOrder();
        inputOrder.ExchangeID = inputOrderField.getExchangeID();
        inputOrder.InvestUnitID = inputOrderField.getInvestUnitID();
        inputOrder.AccountID = inputOrderField.getAccountID();
        inputOrder.CurrencyID = inputOrderField.getCurrencyID();
        inputOrder.ClientID = inputOrderField.getClientID();
        inputOrder.IPAddress = inputOrderField.getIPAddress();
        inputOrder.MacAddress = inputOrderField.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INPUT_ORDER);
    }

    /**
     * @param event                 FtdcRspEvent
     * @param inputOrderActionField CThostFtdcInputOrderActionField
     * @param rspInfoField          CThostFtdcRspInfoField
     * @param isLast                boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrderAction(FtdcRspEvent event,
                                                     CThostFtdcInputOrderActionField inputOrderActionField,
                                                     CThostFtdcRspInfoField rspInfoField,
                                                     boolean isLast) {
        var inputOrderAction = event.getInputOrderAction();
        // [FTDC响应信息] - 错误代码, 错误信息
        inputOrderAction.ErrorID = rspInfoField.getErrorID();
        inputOrderAction.ErrorMsg = rspInfoField.getErrorMsg();
        inputOrderAction.IsLast = isLast;
        inputOrderAction.BrokerID = inputOrderActionField.getBrokerID();
        inputOrderAction.InvestorID = inputOrderActionField.getInvestorID();
        inputOrderAction.OrderActionRef = inputOrderActionField.getOrderActionRef();
        inputOrderAction.OrderRef = inputOrderActionField.getOrderRef();
        inputOrderAction.RequestID = inputOrderActionField.getRequestID();
        inputOrderAction.FrontID = inputOrderActionField.getFrontID();
        inputOrderAction.SessionID = inputOrderActionField.getSessionID();
        inputOrderAction.ExchangeID = inputOrderActionField.getExchangeID();
        inputOrderAction.OrderSysID = inputOrderActionField.getOrderSysID();
        inputOrderAction.ActionFlag = inputOrderActionField.getActionFlag();
        inputOrderAction.LimitPrice = inputOrderActionField.getLimitPrice();
        inputOrderAction.VolumeChange = inputOrderActionField.getVolumeChange();
        inputOrderAction.UserID = inputOrderActionField.getUserID();
        inputOrderAction.InstrumentID = inputOrderActionField.getInstrumentID();
        inputOrderAction.InvestUnitID = inputOrderActionField.getInvestUnitID();
        inputOrderAction.IPAddress = inputOrderActionField.getIPAddress();
        inputOrderAction.MacAddress = inputOrderActionField.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INPUT_ORDER_ACTION);
    }


    /**
     * @param event                 FtdcRspEvent
     * @param investorPositionField CThostFtdcInvestorPositionField
     * @param rspInfoField          CThostFtdcRspInfoField
     * @param requestID             int
     * @param isLast                boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInvestorPosition(FtdcRspEvent event,
                                                     CThostFtdcInvestorPositionField investorPositionField,
                                                     CThostFtdcRspInfoField rspInfoField,
                                                     int requestID, boolean isLast) {
        var investorPosition = event.getInvestorPosition();
        // [FTDC响应信息] - 错误代码, 错误信息
        investorPosition.ErrorID = rspInfoField.getErrorID();
        investorPosition.ErrorMsg = rspInfoField.getErrorMsg();
        investorPosition.RequestID = requestID;
        investorPosition.IsLast = isLast;
        // 合约代码
        investorPosition.InstrumentID = investorPositionField.getInstrumentID();
        // 经纪公司代码
        investorPosition.BrokerID = investorPositionField.getBrokerID();
        // 投资者代码
        investorPosition.InvestorID = investorPositionField.getInvestorID();
        // 持仓多空方向
        investorPosition.PosiDirection = investorPositionField.getPosiDirection();
        // 投机套保标志
        investorPosition.HedgeFlag = investorPositionField.getHedgeFlag();
        // 持仓日期
        investorPosition.PositionDate = investorPositionField.getPositionDate();
        // 上日持仓
        investorPosition.YdPosition = investorPositionField.getYdPosition();
        // 今日持仓
        investorPosition.Position = investorPositionField.getPosition();
        // 多头冻结
        investorPosition.LongFrozen = investorPositionField.getLongFrozen();
        // 空头冻结
        investorPosition.ShortFrozen = investorPositionField.getShortFrozen();
        // 开仓冻结金额
        investorPosition.LongFrozenAmount = investorPositionField.getLongFrozenAmount();
        // 开仓冻结金额
        investorPosition.ShortFrozenAmount = investorPositionField.getShortFrozenAmount();
        // 开仓量
        investorPosition.OpenVolume = investorPositionField.getOpenVolume();
        // 平仓量
        investorPosition.CloseVolume = investorPositionField.getCloseVolume();
        // 开仓金额
        investorPosition.OpenAmount = investorPositionField.getOpenAmount();
        // 平仓金额
        investorPosition.CloseAmount = investorPositionField.getCloseAmount();
        // 持仓成本
        investorPosition.PositionCost = investorPositionField.getPositionCost();
        // 上次占用的保证金
        investorPosition.PreMargin = investorPositionField.getPreMargin();
        // 占用的保证金
        investorPosition.UseMargin = investorPositionField.getUseMargin();
        // 冻结的保证金
        investorPosition.FrozenMargin = investorPositionField.getFrozenMargin();
        // 冻结的资金
        investorPosition.FrozenCash = investorPositionField.getFrozenCash();
        // 冻结的手续费
        investorPosition.FrozenCommission = investorPositionField.getFrozenCommission();
        // 资金差额
        investorPosition.CashIn = investorPositionField.getCashIn();
        // 手续费
        investorPosition.Commission = investorPositionField.getCommission();
        // 平仓盈亏
        investorPosition.CloseProfit = investorPositionField.getCloseProfit();
        // 持仓盈亏
        investorPosition.PositionProfit = investorPositionField.getPositionProfit();
        // 上次结算价
        investorPosition.PreSettlementPrice = investorPositionField.getPreSettlementPrice();
        // 本次结算价
        investorPosition.SettlementPrice = investorPositionField.getSettlementPrice();
        // 交易日
        investorPosition.TradingDay = investorPositionField.getTradingDay();
        // 结算编号
        investorPosition.SettlementID = investorPositionField.getSettlementID();
        // 开仓成本
        investorPosition.OpenCost = investorPositionField.getOpenCost();
        // 交易所保证金
        investorPosition.ExchangeMargin = investorPositionField.getExchangeMargin();
        // 组合成交形成的持仓
        investorPosition.CombPosition = investorPositionField.getCombPosition();
        // 组合多头冻结
        investorPosition.CombLongFrozen = investorPositionField.getCombLongFrozen();
        // 组合空头冻结
        investorPosition.CombShortFrozen = investorPositionField.getCombShortFrozen();
        // 逐日盯市平仓盈亏
        investorPosition.CloseProfitByDate = investorPositionField.getCloseProfitByDate();
        // 逐笔对冲平仓盈亏
        investorPosition.CloseProfitByTrade = investorPositionField.getCloseProfitByTrade();
        // 今日持仓
        investorPosition.TodayPosition = investorPositionField.getTodayPosition();
        // 保证金率
        investorPosition.MarginRateByMoney = investorPositionField.getMarginRateByMoney();
        // 保证金率(按手数)
        investorPosition.MarginRateByVolume = investorPositionField.getMarginRateByVolume();
        // 执行冻结
        investorPosition.StrikeFrozen = investorPositionField.getStrikeFrozen();
        // 执行冻结金额
        investorPosition.StrikeFrozenAmount = investorPositionField.getStrikeFrozenAmount();
        // 放弃执行冻结
        investorPosition.AbandonFrozen = investorPositionField.getAbandonFrozen();
        // 交易所代码
        investorPosition.ExchangeID = investorPositionField.getExchangeID();
        // 执行冻结的昨仓
        investorPosition.YdStrikeFrozen = investorPositionField.getYdStrikeFrozen();
        // 投资单元代码
        investorPosition.InvestUnitID = investorPositionField.getInvestUnitID();
        // 大商所持仓成本差值, 只有大商所使用
        // 6.3.15 版本使用
        investorPosition.PositionCostOffset = investorPositionField.getPositionCostOffset();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_INVESTOR_POSITION);
    }

    /**
     * @param event      FtdcRspEvent
     * @param orderField CThostFtdcOrderField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrder(FtdcRspEvent event,
                                          CThostFtdcOrderField orderField) {
        var order = event.getOrder();
        order.RecvEpochMicros = micros();
        order.BrokerID = orderField.getBrokerID();
        order.InvestorID = orderField.getInvestorID();
        order.InstrumentID = orderField.getInstrumentID();
        order.OrderRef = orderField.getOrderRef();
        order.UserID = orderField.getUserID();
        order.OrderPriceType = orderField.getOrderPriceType();
        order.Direction = orderField.getDirection();
        order.CombOffsetFlag = orderField.getCombOffsetFlag();
        order.CombHedgeFlag = orderField.getCombHedgeFlag();
        order.LimitPrice = orderField.getLimitPrice();
        order.VolumeTotalOriginal = orderField.getVolumeTotalOriginal();
        order.TimeCondition = orderField.getTimeCondition();
        order.GTDDate = orderField.getGTDDate();
        order.VolumeCondition = orderField.getVolumeCondition();
        order.MinVolume = orderField.getMinVolume();
        order.ContingentCondition = orderField.getContingentCondition();
        order.StopPrice = orderField.getStopPrice();
        order.ForceCloseReason = orderField.getForceCloseReason();
        order.IsAutoSuspend = orderField.getIsAutoSuspend();
        order.BusinessUnit = orderField.getBusinessUnit();
        order.RequestID = orderField.getRequestID();
        order.OrderLocalID = orderField.getOrderLocalID();
        order.ExchangeID = orderField.getExchangeID();
        order.ParticipantID = orderField.getParticipantID();
        order.ClientID = orderField.getClientID();
        order.ExchangeInstID = orderField.getExchangeInstID();
        order.TraderID = orderField.getTraderID();
        order.InstallID = orderField.getInstallID();
        order.OrderSubmitStatus = orderField.getOrderSubmitStatus();
        order.NotifySequence = orderField.getNotifySequence();
        order.TradingDay = orderField.getTradingDay();
        order.SettlementID = orderField.getSettlementID();
        order.OrderSysID = orderField.getOrderSysID();
        order.OrderSource = orderField.getOrderSource();
        order.OrderStatus = orderField.getOrderStatus();
        order.OrderType = orderField.getOrderType();
        order.VolumeTraded = orderField.getVolumeTraded();
        order.VolumeTotal = orderField.getVolumeTotal();
        order.InsertDate = orderField.getInsertDate();
        order.InsertTime = orderField.getInsertTime();
        order.ActiveTime = orderField.getActiveTime();
        order.SuspendTime = orderField.getSuspendTime();
        order.UpdateTime = orderField.getUpdateTime();
        order.CancelTime = orderField.getCancelTime();
        order.ActiveTraderID = orderField.getActiveTraderID();
        order.ClearingPartID = orderField.getClearingPartID();
        order.SequenceNo = orderField.getSequenceNo();
        order.FrontID = orderField.getFrontID();
        order.SessionID = orderField.getSessionID();
        order.UserProductInfo = orderField.getUserProductInfo();
        order.StatusMsg = orderField.getStatusMsg();
        order.UserForceClose = orderField.getUserForceClose();
        order.ActiveUserID = orderField.getActiveUserID();
        order.BrokerOrderSeq = orderField.getBrokerOrderSeq();
        order.RelativeOrderSysID = orderField.getRelativeOrderSysID();
        order.ZCETotalTradedVolume = orderField.getZCETotalTradedVolume();
        order.IsSwapOrder = orderField.getIsSwapOrder();
        order.BranchID = orderField.getBranchID();
        order.InvestUnitID = orderField.getInvestUnitID();
        order.AccountID = orderField.getAccountID();
        order.CurrencyID = orderField.getCurrencyID();
        order.IPAddress = orderField.getIPAddress();
        order.MacAddress = orderField.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_ORDER);
    }

    /**
     * @param event            FtdcRspEvent
     * @param orderActionField CThostFtdcOrderActionField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrderAction(FtdcRspEvent event,
                                                CThostFtdcOrderActionField orderActionField) {
        var orderAction = event.getOrderAction();
        // 经纪公司代码
        orderAction.BrokerID = orderActionField.getBrokerID();
        // 投资者代码
        orderAction.InvestorID = orderActionField.getInvestorID();
        // 报单操作引用
        orderAction.OrderActionRef = orderActionField.getOrderActionRef();
        // 报单引用
        orderAction.OrderRef = orderActionField.getOrderRef();
        // 请求编号
        orderAction.RequestID = orderActionField.getRequestID();
        // 前置编号
        orderAction.FrontID = orderActionField.getFrontID();
        // 会话编号
        orderAction.SessionID = orderActionField.getSessionID();
        // 交易所代码
        orderAction.ExchangeID = orderActionField.getExchangeID();
        // 报单编号
        orderAction.OrderSysID = orderActionField.getOrderSysID();
        // 操作标志
        orderAction.ActionFlag = orderActionField.getActionFlag();
        // 价格
        orderAction.LimitPrice = orderActionField.getLimitPrice();
        // 数量变化
        orderAction.VolumeChange = orderActionField.getVolumeChange();
        // 操作日期
        orderAction.ActionDate = orderActionField.getActionDate();
        // 操作时间
        orderAction.ActionTime = orderActionField.getActionTime();
        // 交易所交易员代码
        orderAction.TraderID = orderActionField.getTraderID();
        // 安装编号
        orderAction.InstallID = orderActionField.getInstallID();
        // 本地报单编号
        orderAction.OrderLocalID = orderActionField.getOrderLocalID();
        // 操作本地编号
        orderAction.ActionLocalID = orderActionField.getActionLocalID();
        // 会员代码
        orderAction.ParticipantID = orderActionField.getParticipantID();
        // 客户代码
        orderAction.ClientID = orderActionField.getClientID();
        // 业务单元
        orderAction.BusinessUnit = orderActionField.getBusinessUnit();
        // 报单操作状态
        orderAction.OrderActionStatus = orderActionField.getOrderActionStatus();
        // 用户代码
        orderAction.UserID = orderActionField.getUserID();
        // 状态信息
        orderAction.StatusMsg = orderActionField.getStatusMsg();
        // 合约代码
        orderAction.InstrumentID = orderActionField.getInstrumentID();
        // 营业部编号
        orderAction.BranchID = orderActionField.getBranchID();
        // 投资单元代码
        orderAction.InvestUnitID = orderActionField.getInvestUnitID();
        // IP地址
        orderAction.IPAddress = orderActionField.getIPAddress();
        // MAC地址
        orderAction.MacAddress = orderActionField.getMacAddress();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_ORDER_ACTION);
    }

    /**
     * @param event      FtdcRspEvent
     * @param tradeField CThostFtdcTradeField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTrade(FtdcRspEvent event,
                                          CThostFtdcTradeField tradeField) {
        var trade = event.getTrade();
        trade.RecvEpochMicros = micros();
        // 经纪公司代码
        trade.BrokerID = tradeField.getBrokerID();
        // 投资者代码
        trade.InvestorID = tradeField.getInvestorID();
        // 合约代码
        trade.InstrumentID = tradeField.getInstrumentID();
        // 报单引用
        trade.OrderRef = tradeField.getOrderRef();
        // 用户代码
        trade.UserID = tradeField.getUserID();
        // 交易所代码
        trade.ExchangeID = tradeField.getExchangeID();
        // 成交编号
        trade.TradeID = tradeField.getTradeID();
        // 买卖方向
        trade.Direction = tradeField.getDirection();
        // 报单编号
        trade.OrderSysID = tradeField.getOrderSysID();
        // 会员代码
        trade.ParticipantID = tradeField.getParticipantID();
        // 客户代码
        trade.ClientID = tradeField.getClientID();
        // 交易角色
        trade.TradingRole = tradeField.getTradingRole();
        // 合约在交易所的代码
        trade.ExchangeInstID = tradeField.getExchangeInstID();
        // 开平标志
        trade.OffsetFlag = tradeField.getOffsetFlag();
        // 投机套保标志
        trade.HedgeFlag = tradeField.getHedgeFlag();
        // 价格
        trade.Price = tradeField.getPrice();
        // 数量
        trade.Volume = tradeField.getVolume();
        // 成交日期
        trade.TradeDate = tradeField.getTradeDate();
        // 成交时间
        trade.TradeTime = tradeField.getTradeTime();
        // 成交类型
        trade.TradeType = tradeField.getTradeType();
        // 成交价来源
        trade.PriceSource = tradeField.getPriceSource();
        // 交易所交易员代码
        trade.TraderID = tradeField.getTraderID();
        // 本地报单编号
        trade.OrderLocalID = tradeField.getOrderLocalID();
        // 结算会员编号
        trade.ClearingPartID = tradeField.getClearingPartID();
        // 业务单元
        trade.BusinessUnit = tradeField.getBusinessUnit();
        // 序号
        trade.SequenceNo = tradeField.getSequenceNo();
        // 交易日
        trade.TradingDay = tradeField.getTradingDay();
        // 结算编号
        trade.SettlementID = tradeField.getSettlementID();
        // 经纪公司报单编号
        trade.BrokerOrderSeq = tradeField.getBrokerOrderSeq();
        // 成交来源
        trade.TradeSource = tradeField.getTradeSource();
        // 投资单元代码
        trade.InvestUnitID = tradeField.getInvestUnitID();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_TRADE);
    }

    /**
     * @param event               FtdcRspEvent
     * @param tradingAccountField CThostFtdcTradingAccountField
     * @param rspInfoField        CThostFtdcRspInfoField
     * @param requestID           int
     * @param isLast              boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTradingAccount(FtdcRspEvent event,
                                                   CThostFtdcTradingAccountField tradingAccountField,
                                                   CThostFtdcRspInfoField rspInfoField,
                                                   int requestID, boolean isLast) {
        var tradingAccount = event.getTradingAccount();
        tradingAccount.ErrorID = rspInfoField.getErrorID();
        tradingAccount.ErrorMsg = rspInfoField.getErrorMsg();
        tradingAccount.RequestID = requestID;
        tradingAccount.IsLast = isLast;
        // 经纪公司代码
        tradingAccount.BrokerID = tradingAccountField.getBrokerID();
        // 投资者账号
        tradingAccount.AccountID = tradingAccountField.getAccountID();
        // 上次质押金额
        tradingAccount.PreMortgage = tradingAccountField.getPreMortgage();
        // 上次信用额度
        tradingAccount.PreCredit = tradingAccountField.getPreCredit();
        // 上次存款额
        tradingAccount.PreDeposit = tradingAccountField.getPreDeposit();
        // 上次结算准备金
        tradingAccount.PreBalance = tradingAccountField.getPreBalance();
        // 上次占用的保证金
        tradingAccount.PreMargin = tradingAccountField.getPreMargin();
        // 利息基数
        tradingAccount.InterestBase = tradingAccountField.getInterestBase();
        // 利息收入
        tradingAccount.Interest = tradingAccountField.getInterest();
        // 入金金额
        tradingAccount.Deposit = tradingAccountField.getDeposit();
        // 出金金额
        tradingAccount.Withdraw = tradingAccountField.getWithdraw();
        // 冻结的保证金
        tradingAccount.FrozenMargin = tradingAccountField.getFrozenMargin();
        // 冻结的资金
        tradingAccount.FrozenCash = tradingAccountField.getFrozenCash();
        // 冻结的手续费
        tradingAccount.FrozenCommission = tradingAccountField.getFrozenCommission();
        // 当前保证金总额
        tradingAccount.CurrMargin = tradingAccountField.getCurrMargin();
        // 资金差额
        tradingAccount.CashIn = tradingAccountField.getCashIn();
        // 手续费
        tradingAccount.Commission = tradingAccountField.getCommission();
        // 平仓盈亏
        tradingAccount.CloseProfit = tradingAccountField.getCloseProfit();
        // 持仓盈亏
        tradingAccount.PositionProfit = tradingAccountField.getPositionProfit();
        // 期货结算准备金
        tradingAccount.Balance = tradingAccountField.getBalance();
        // 可用资金
        tradingAccount.Available = tradingAccountField.getAvailable();
        // 可取资金
        tradingAccount.WithdrawQuota = tradingAccountField.getWithdrawQuota();
        // 基本准备金
        tradingAccount.Reserve = tradingAccountField.getReserve();
        // 交易日
        tradingAccount.TradingDay = tradingAccountField.getTradingDay();
        // 结算编号
        tradingAccount.SettlementID = tradingAccountField.getSettlementID();
        // 信用额度
        tradingAccount.Credit = tradingAccountField.getCredit();
        // 质押金额
        tradingAccount.Mortgage = tradingAccountField.getMortgage();
        // 交易所保证金
        tradingAccount.ExchangeMargin = tradingAccountField.getExchangeMargin();
        // 投资者交割保证金
        tradingAccount.DeliveryMargin = tradingAccountField.getDeliveryMargin();
        // 交易所交割保证金
        tradingAccount.ExchangeDeliveryMargin = tradingAccountField.getExchangeDeliveryMargin();
        // 保底期货结算准备金
        tradingAccount.ReserveBalance = tradingAccountField.getReserveBalance();
        // 币种代码
        tradingAccount.CurrencyID = tradingAccountField.getCurrencyID();
        // 上次货币质入金额
        tradingAccount.PreFundMortgageIn = tradingAccountField.getPreFundMortgageIn();
        // 上次货币质出金额
        tradingAccount.PreFundMortgageOut = tradingAccountField.getPreFundMortgageOut();
        // 货币质入金额
        tradingAccount.FundMortgageIn = tradingAccountField.getFundMortgageIn();
        // 货币质出金额
        tradingAccount.FundMortgageOut = tradingAccountField.getFundMortgageOut();
        // 货币质押余额
        tradingAccount.FundMortgageAvailable = tradingAccountField.getFundMortgageAvailable();
        // 可质押货币金额
        tradingAccount.MortgageableFund = tradingAccountField.getMortgageableFund();
        // 特殊产品占用保证金
        tradingAccount.SpecProductMargin = tradingAccountField.getSpecProductMargin();
        // 特殊产品冻结保证金
        tradingAccount.SpecProductFrozenMargin = tradingAccountField.getSpecProductFrozenMargin();
        // 特殊产品手续费
        tradingAccount.SpecProductCommission = tradingAccountField.getSpecProductCommission();
        // 特殊产品冻结手续费
        tradingAccount.SpecProductFrozenCommission = tradingAccountField.getSpecProductFrozenCommission();
        // 特殊产品持仓盈亏
        tradingAccount.SpecProductPositionProfit = tradingAccountField.getSpecProductPositionProfit();
        // 特殊产品平仓盈亏
        tradingAccount.SpecProductCloseProfit = tradingAccountField.getSpecProductCloseProfit();
        // 根据持仓盈亏算法计算的特殊产品持仓盈亏
        tradingAccount.SpecProductPositionProfitByAlg = tradingAccountField.getSpecProductPositionProfitByAlg();
        // 特殊产品交易所保证金
        tradingAccount.SpecProductExchangeMargin = tradingAccountField.getSpecProductExchangeMargin();
        // 业务类型
        tradingAccount.BizType = tradingAccountField.getBizType();
        // 延时换汇冻结金额
        tradingAccount.FrozenSwap = tradingAccountField.getFrozenSwap();
        // 剩余换汇额度
        tradingAccount.RemainSwap = tradingAccountField.getRemainSwap();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_TRADING_ACCOUNT);
    }

    /**
     * @param event                    FtdcRspEvent
     * @param specificInstrumentSource SpecificInstrumentSource
     * @param specificInstrumentField  CThostFtdcSpecificInstrumentField
     * @param rspInfoField             CThostFtdcRspInfoField
     * @param requestID                int
     * @param isLast                   boolean
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeSpecificInstrument(FtdcRspEvent event,
                                                       SpecificInstrumentSource specificInstrumentSource,
                                                       CThostFtdcSpecificInstrumentField specificInstrumentField,
                                                       CThostFtdcRspInfoField rspInfoField,
                                                       int requestID, boolean isLast) {
        var specificInstrument = event.getSpecificInstrument();
        // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
        specificInstrument.Source = specificInstrumentSource;
        specificInstrument.ErrorID = rspInfoField.getErrorID();
        specificInstrument.ErrorMsg = rspInfoField.getErrorMsg();
        // 请求ID, 是否最后一条信息
        specificInstrument.RequestID = requestID;
        specificInstrument.IsLast = isLast;
        // 合约代码
        specificInstrument.InstrumentID = specificInstrumentField.getInstrumentID();
        return event.setEpochMicros(micros())
                .setType(FtdcRspType.FTDC_SPECIFIC_INSTRUMENT);
    }

}
