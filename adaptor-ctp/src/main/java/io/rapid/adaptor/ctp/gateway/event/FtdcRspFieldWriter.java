package io.rapid.adaptor.ctp.gateway.event;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentStatusField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource;
import io.rapid.adaptor.ctp.serializable.avro.shared.EventSource;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

public final class FtdcRspFieldWriter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspFieldWriter.class);

    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcDepthMarketDataField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeDepthMarketData(@Nonnull FtdcRspEvent event,
                                                    @Nonnull CThostFtdcDepthMarketDataField Field) {
        event.getFtdcDepthMarketData()
                // 交易日, 合约ID, 交易所ID, 合约在交易所的代码
                .setTradingDay(Field.getTradingDay())
                .setInstrumentID(Field.getInstrumentID())
                .setExchangeID(Field.getExchangeID())
                .setExchangeInstID(Field.getExchangeInstID())
                // 最新价
                .setLastPrice(Field.getLastPrice())
                // 昨结算价, 昨收盘, 昨持仓量
                .setPreSettlementPrice(Field.getPreSettlementPrice())
                .setPreClosePrice(Field.getPreClosePrice())
                .setPreOpenInterest(Field.getPreOpenInterest())
                // 开盘价, 最高价, 最低价
                .setOpenPrice(Field.getOpenPrice())
                .setHighestPrice(Field.getHighestPrice())
                .setLowestPrice(Field.getLowestPrice())
                // 成交量, 成交金额, 持仓量
                .setVolume(Field.getVolume())
                .setTurnover(Field.getTurnover())
                .setOpenInterest(Field.getOpenInterest())
                // 收盘价, 结算价
                .setClosePrice(Field.getClosePrice())
                .setSettlementPrice(Field.getSettlementPrice())
                // 涨停板价, 跌停板价
                .setUpperLimitPrice(Field.getUpperLimitPrice())
                .setLowerLimitPrice(Field.getLowerLimitPrice())
                // 昨Delta, 今Delta
                .setPreDelta(Field.getPreDelta())
                .setCurrDelta(Field.getCurrDelta())
                // 五档买价卖价及买量卖量 v
                .setBidPrice1(Field.getBidPrice1())
                .setBidVolume1(Field.getBidVolume1())
                .setAskPrice1(Field.getAskPrice1())
                .setAskVolume1(Field.getAskVolume1())
                .setBidPrice2(Field.getBidPrice2())
                .setBidVolume2(Field.getBidVolume2())
                .setAskPrice2(Field.getAskPrice2())
                .setAskVolume2(Field.getAskVolume2())
                .setBidPrice3(Field.getBidPrice3())
                .setBidVolume3(Field.getBidVolume3())
                .setAskPrice3(Field.getAskPrice3())
                .setAskVolume3(Field.getAskVolume3())
                .setBidPrice4(Field.getBidPrice4())
                .setBidVolume4(Field.getBidVolume4())
                .setAskPrice4(Field.getAskPrice4())
                .setAskVolume4(Field.getAskVolume4())
                .setBidPrice5(Field.getBidPrice5())
                .setBidVolume5(Field.getBidVolume5())
                .setAskPrice5(Field.getAskPrice5())
                .setAskVolume5(Field.getAskVolume5())
                // 五档买价卖价及买量卖量 ^
                // 平均价格
                .setAveragePrice(Field.getAveragePrice())
                // 更新时间, 更新毫秒数, 业务日期
                .setUpdateTime(Field.getUpdateTime())
                .setUpdateMillisec(Field.getUpdateMillisec())
                .setActionDay(Field.getActionDay());
        return event;
    }


    public static FtdcRspEvent writeRspUserLogin(FtdcRspEvent event, EventSource source,
                                                 CThostFtdcRspUserLoginField Field,
                                                 CThostFtdcRspInfoField RspInfo,
                                                 int RequestID, boolean IsLast) {
        event.getRspUserLogin()
                // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
                .setSource(source)
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                // 请求ID, 是否最后一条信息
                .setRequestID(RequestID)
                .setIsLast(IsLast)
                // 交易日
                .setTradingDay(Field.getTradingDay())
                // 登录成功时间
                .setLoginTime(Field.getLoginTime())
                // 经纪公司代码
                .setBrokerID(Field.getBrokerID())
                // 用户代码
                .setUserID(Field.getUserID())
                // 交易系统名称
                .setSystemName(Field.getSystemName())
                // 前置编号
                .setFrontID(Field.getFrontID())
                // 会话编号
                .setSessionID(Field.getSessionID())
                // 最大报单引用
                .setMaxOrderRef(Field.getMaxOrderRef())
                // 上期所时间
                .setSHFETime(Field.getSHFETime())
                // 大商所时间
                .setDCETime(Field.getDCETime())
                // 郑商所时间
                .setCZCETime(Field.getCZCETime())
                // 中金所时间
                .setFFEXTime(Field.getFFEXTime())
                // 能源中心时间
                .setINETime(Field.getINETime());
        return event;
    }


    public static FtdcRspEvent writeRspUserLogout(FtdcRspEvent event, EventSource source,
                                                  CThostFtdcUserLogoutField Field,
                                                  CThostFtdcRspInfoField RspInfo,
                                                  int RequestID, boolean IsLast) {
        event.getUserLogout()
                // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
                .setSource(source)
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                // 请求ID, 是否最后一条信息
                .setRequestID(RequestID)
                .setIsLast(IsLast)
                // 经纪公司代码, 用户代码
                .setBrokerID(Field.getBrokerID())
                .setUserID(Field.getUserID());
        return event;
    }


    public static FtdcRspEvent writeFrontDisconnected(FtdcRspEvent event, EventSource source,
                                                      int Reason, String BrokerID, String UserID) {
        event.getFrontDisconnected()
                // 事件来源
                .setSource(source)
                // 错误原因
                .setReason(Reason)
                // 经纪公司代码
                .setBrokerID(BrokerID)
                // 用户代码
                .setUserID(UserID);
        return event;
    }

    public static FtdcRspEvent writeHeartBeatWarning(FtdcRspEvent event, EventSource source,
                                                     int TimeLapse, String BrokerID, String UserID) {
        event.getHeartBeatWarning()
                // 事件来源
                .setSource(source)
                // 距离上次接收报文的时间
                .setTimeLapse(TimeLapse)
                // 经纪公司代码
                .setBrokerID(BrokerID)
                // 用户代码
                .setUserID(UserID);
        return event;
    }

    public static FtdcRspEvent writeRspError(FtdcRspEvent event, EventSource source,
                                             CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        event.getRspError()
                // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
                .setSource(source)
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                // 请求ID, 是否最后一条信息
                .setRequestID(RequestID)
                .setIsLast(IsLast);
        return event;
    }

    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcInstrumentStatusField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInstrumentStatus(FtdcRspEvent event,
                                                     CThostFtdcInstrumentStatusField Field) {
        event.getFtdcInstrumentStatus()
                // 交易所代码, 合约在交易所的代码, 结算组代码, 合约代码
                .setExchangeID(Field.getExchangeID())
                .setExchangeInstID(Field.getExchangeInstID())
                .setSettlementGroupID(Field.getSettlementGroupID())
                .setInstrumentID(Field.getInstrumentID())
                // 合约交易状态, 交易阶段编号, 进入本状态时间, 进入本状态原因
                .setInstrumentStatus(Field.getInstrumentStatus())
                .setTradingSegmentSN(Field.getTradingSegmentSN())
                .setEnterTime(Field.getEnterTime())
                .setEnterReason(Field.getEnterReason());
        return event;
    }

    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcInputOrderField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrder(FtdcRspEvent event,
                                               CThostFtdcInputOrderField Field,
                                               CThostFtdcRspInfoField RspInfo, boolean IsLast) {
        event.getFtdcInputOrder()
                // [FTDC响应信息] - 错误代码, 错误信息
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                .setIsLast(IsLast)
                .setBrokerID(Field.getBrokerID())
                .setInvestorID(Field.getInvestorID())
                .setInstrumentID(Field.getInstrumentID())
                .setOrderRef(Field.getOrderRef())
                .setUserID(Field.getUserID())
                .setOrderPriceType(Field.getOrderPriceType())
                .setDirection(Field.getDirection())
                .setCombOffsetFlag(Field.getCombOffsetFlag())
                .setCombHedgeFlag(Field.getCombHedgeFlag())
                .setLimitPrice(Field.getLimitPrice())
                .setVolumeTotalOriginal(Field.getVolumeTotalOriginal())
                .setTimeCondition(Field.getTimeCondition())
                .setGTDDate(Field.getGTDDate())
                .setVolumeCondition(Field.getVolumeCondition())
                .setMinVolume(Field.getMinVolume())
                .setContingentCondition(Field.getContingentCondition())
                .setStopPrice(Field.getStopPrice())
                .setForceCloseReason(Field.getForceCloseReason())
                .setIsAutoSuspend(Field.getIsAutoSuspend())
                .setBusinessUnit(Field.getBusinessUnit())
                .setRequestID(Field.getRequestID())
                .setUserForceClose(Field.getUserForceClose())
                .setIsSwapOrder(Field.getIsSwapOrder())
                .setExchangeID(Field.getExchangeID())
                .setInvestUnitID(Field.getInvestUnitID())
                .setAccountID(Field.getAccountID())
                .setCurrencyID(Field.getCurrencyID())
                .setClientID(Field.getClientID())
                .setIPAddress(Field.getIPAddress())
                .setMacAddress(Field.getMacAddress());
        return event;
    }

    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcInputOrderActionField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInputOrderAction(@Nonnull FtdcRspEvent event,
                                                     @Nonnull CThostFtdcInputOrderActionField Field,
                                                     CThostFtdcRspInfoField RspInfo, boolean IsLast) {
        event.getFtdcInputOrderAction()
                // [FTDC响应信息] - 错误代码, 错误信息
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                .setIsLast(IsLast)
                .setBrokerID(Field.getBrokerID())
                .setInvestorID(Field.getInvestorID())
                .setOrderActionRef(Field.getOrderActionRef())
                .setOrderRef(Field.getOrderRef())
                .setRequestID(Field.getRequestID())
                .setFrontID(Field.getFrontID())
                .setSessionID(Field.getSessionID())
                .setExchangeID(Field.getExchangeID())
                .setOrderSysID(Field.getOrderSysID())
                .setActionFlag(Field.getActionFlag())
                .setLimitPrice(Field.getLimitPrice())
                .setVolumeChange(Field.getVolumeChange())
                .setUserID(Field.getUserID())
                .setInstrumentID(Field.getInstrumentID())
                .setInvestUnitID(Field.getInvestUnitID())
                .setIPAddress(Field.getIPAddress())
                .setMacAddress(Field.getMacAddress());
        return event;
    }


    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcInvestorPositionField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeInvestorPosition(@Nonnull FtdcRspEvent event,
                                                     @Nonnull CThostFtdcInvestorPositionField Field,
                                                     CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        event.getFtdcInvestorPosition()
                // [FTDC响应信息] - 错误代码, 错误信息
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                .setRequestID(RequestID)
                .setIsLast(IsLast)
                /// 合约代码
                .setInstrumentID(Field.getInstrumentID())
                /// 经纪公司代码
                .setBrokerID(Field.getBrokerID())
                /// 投资者代码
                .setInvestorID(Field.getInvestorID())
                /// 持仓多空方向
                .setPosiDirection(Field.getPosiDirection())
                /// 投机套保标志
                .setHedgeFlag(Field.getHedgeFlag())
                /// 持仓日期
                .setPositionDate(Field.getPositionDate())
                /// 上日持仓
                .setYdPosition(Field.getYdPosition())
                /// 今日持仓
                .setPosition(Field.getPosition())
                /// 多头冻结
                .setLongFrozen(Field.getLongFrozen())
                /// 空头冻结
                .setShortFrozen(Field.getShortFrozen())
                /// 开仓冻结金额
                .setLongFrozenAmount(Field.getLongFrozenAmount())
                /// 开仓冻结金额
                .setShortFrozenAmount(Field.getShortFrozenAmount())
                /// 开仓量
                .setOpenVolume(Field.getOpenVolume())
                /// 平仓量
                .setCloseVolume(Field.getCloseVolume())
                /// 开仓金额
                .setOpenAmount(Field.getOpenAmount())
                /// 平仓金额
                .setCloseAmount(Field.getCloseAmount())
                /// 持仓成本
                .setPositionCost(Field.getPositionCost())
                /// 上次占用的保证金
                .setPreMargin(Field.getPreMargin())
                /// 占用的保证金
                .setUseMargin(Field.getUseMargin())
                /// 冻结的保证金
                .setFrozenMargin(Field.getFrozenMargin())
                /// 冻结的资金
                .setFrozenCash(Field.getFrozenCash())
                /// 冻结的手续费
                .setFrozenCommission(Field.getFrozenCommission())
                /// 资金差额
                .setCashIn(Field.getCashIn())
                /// 手续费
                .setCommission(Field.getCommission())
                /// 平仓盈亏
                .setCloseProfit(Field.getCloseProfit())
                /// 持仓盈亏
                .setPositionProfit(Field.getPositionProfit())
                /// 上次结算价
                .setPreSettlementPrice(Field.getPreSettlementPrice())
                /// 本次结算价
                .setSettlementPrice(Field.getSettlementPrice())
                /// 交易日
                .setTradingDay(Field.getTradingDay())
                /// 结算编号
                .setSettlementID(Field.getSettlementID())
                /// 开仓成本
                .setOpenCost(Field.getOpenCost())
                /// 交易所保证金
                .setExchangeMargin(Field.getExchangeMargin())
                /// 组合成交形成的持仓
                .setCombPosition(Field.getCombPosition())
                /// 组合多头冻结
                .setCombLongFrozen(Field.getCombLongFrozen())
                /// 组合空头冻结
                .setCombShortFrozen(Field.getCombShortFrozen())
                /// 逐日盯市平仓盈亏
                .setCloseProfitByDate(Field.getCloseProfitByDate())
                /// 逐笔对冲平仓盈亏
                .setCloseProfitByTrade(Field.getCloseProfitByTrade())
                /// 今日持仓
                .setTodayPosition(Field.getTodayPosition())
                /// 保证金率
                .setMarginRateByMoney(Field.getMarginRateByMoney())
                /// 保证金率(按手数)
                .setMarginRateByVolume(Field.getMarginRateByVolume())
                /// 执行冻结
                .setStrikeFrozen(Field.getStrikeFrozen())
                /// 执行冻结金额
                .setStrikeFrozenAmount(Field.getStrikeFrozenAmount())
                /// 放弃执行冻结
                .setAbandonFrozen(Field.getAbandonFrozen())
                /// 交易所代码
                .setExchangeID(Field.getExchangeID())
                /// 执行冻结的昨仓
                .setYdStrikeFrozen(Field.getYdStrikeFrozen())
                /// 投资单元代码
                .setInvestUnitID(Field.getInvestUnitID())
        /// 大商所持仓成本差值, 只有大商所使用
        // 6.3.15 版本使用
        // .setPositionCostOffset(Field.getPositionCostOffset())
        ;
        return event;
    }


    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcOrderField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrder(@Nonnull FtdcRspEvent event,
                                          @Nonnull CThostFtdcOrderField Field) {
        event.getFtdcOrder()
                .setBrokerID(Field.getBrokerID())
                .setInvestorID(Field.getInvestorID())
                .setInstrumentID(Field.getInstrumentID())
                .setOrderRef(Field.getOrderRef())
                .setUserID(Field.getUserID())
                .setOrderPriceType(Field.getOrderPriceType())
                .setDirection(Field.getDirection())
                .setCombOffsetFlag(Field.getCombOffsetFlag())
                .setCombHedgeFlag(Field.getCombHedgeFlag())
                .setLimitPrice(Field.getLimitPrice())
                .setVolumeTotalOriginal(Field.getVolumeTotalOriginal())
                .setTimeCondition(Field.getTimeCondition())
                .setGTDDate(Field.getGTDDate())
                .setVolumeCondition(Field.getVolumeCondition())
                .setMinVolume(Field.getMinVolume())
                .setContingentCondition(Field.getContingentCondition())
                .setStopPrice(Field.getStopPrice())
                .setForceCloseReason(Field.getForceCloseReason())
                .setIsAutoSuspend(Field.getIsAutoSuspend())
                .setBusinessUnit(Field.getBusinessUnit())
                .setRequestID(Field.getRequestID())
                .setOrderLocalID(Field.getOrderLocalID())
                .setExchangeID(Field.getExchangeID())
                .setParticipantID(Field.getParticipantID())
                .setClientID(Field.getClientID())
                .setExchangeInstID(Field.getExchangeInstID())
                .setTraderID(Field.getTraderID())
                .setInstallID(Field.getInstallID())
                .setOrderSubmitStatus(Field.getOrderSubmitStatus())
                .setNotifySequence(Field.getNotifySequence())
                .setTradingDay(Field.getTradingDay())
                .setSettlementID(Field.getSettlementID())
                .setOrderSysID(Field.getOrderSysID())
                .setOrderSource(Field.getOrderSource())
                .setOrderStatus(Field.getOrderStatus())
                .setOrderType(Field.getOrderType())
                .setVolumeTraded(Field.getVolumeTraded())
                .setVolumeTotal(Field.getVolumeTotal())
                .setInsertDate(Field.getInsertDate())
                .setInsertTime(Field.getInsertTime())
                .setActiveTime(Field.getActiveTime())
                .setSuspendTime(Field.getSuspendTime())
                .setUpdateTime(Field.getUpdateTime())
                .setCancelTime(Field.getCancelTime())
                .setActiveTraderID(Field.getActiveTraderID())
                .setClearingPartID(Field.getClearingPartID())
                .setSequenceNo(Field.getSequenceNo())
                .setFrontID(Field.getFrontID())
                .setSessionID(Field.getSessionID())
                .setUserProductInfo(Field.getUserProductInfo())
                .setStatusMsg(Field.getStatusMsg())
                .setUserForceClose(Field.getUserForceClose())
                .setActiveUserID(Field.getActiveUserID())
                .setBrokerOrderSeq(Field.getBrokerOrderSeq())
                .setRelativeOrderSysID(Field.getRelativeOrderSysID())
                .setZCETotalTradedVolume(Field.getZCETotalTradedVolume())
                .setIsSwapOrder(Field.getIsSwapOrder())
                .setBranchID(Field.getBranchID())
                .setInvestUnitID(Field.getInvestUnitID())
                .setAccountID(Field.getAccountID())
                .setCurrencyID(Field.getCurrencyID())
                .setIPAddress(Field.getIPAddress())
                .setMacAddress(Field.getMacAddress());
        return event;
    }


    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcOrderActionField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeOrderAction(@Nonnull FtdcRspEvent event,
                                                @Nonnull CThostFtdcOrderActionField Field) {
        event.getFtdcOrderAction()
                // 经纪公司代码
                .setBrokerID(Field.getBrokerID())
                // 投资者代码
                .setInvestorID(Field.getInvestorID())
                // 报单操作引用
                .setOrderActionRef(Field.getOrderActionRef())
                // 报单引用
                .setOrderRef(Field.getOrderRef())
                // 请求编号
                .setRequestID(Field.getRequestID())
                // 前置编号
                .setFrontID(Field.getFrontID())
                // 会话编号
                .setSessionID(Field.getSessionID())
                // 交易所代码
                .setExchangeID(Field.getExchangeID())
                // 报单编号
                .setOrderSysID(Field.getOrderSysID())
                // 操作标志
                .setActionFlag(Field.getActionFlag())
                // 价格
                .setLimitPrice(Field.getLimitPrice())
                // 数量变化
                .setVolumeChange(Field.getVolumeChange())
                // 操作日期
                .setActionDate(Field.getActionDate())
                // 操作时间
                .setActionTime(Field.getActionTime())
                // 交易所交易员代码
                .setTraderID(Field.getTraderID())
                // 安装编号
                .setInstallID(Field.getInstallID())
                // 本地报单编号
                .setOrderLocalID(Field.getOrderLocalID())
                // 操作本地编号
                .setActionLocalID(Field.getActionLocalID())
                // 会员代码
                .setParticipantID(Field.getParticipantID())
                // 客户代码
                .setClientID(Field.getClientID())
                // 业务单元
                .setBusinessUnit(Field.getBusinessUnit())
                // 报单操作状态
                .setOrderActionStatus(Field.getOrderActionStatus())
                // 用户代码
                .setUserID(Field.getUserID())
                // 状态信息
                .setStatusMsg(Field.getStatusMsg())
                // 合约代码
                .setInstrumentID(Field.getInstrumentID())
                // 营业部编号
                .setBranchID(Field.getBranchID())
                // 投资单元代码
                .setInvestUnitID(Field.getInvestUnitID())
                // IP地址
                .setIPAddress(Field.getIPAddress())
                // MAC地址
                .setMacAddress(Field.getMacAddress());
        return event;
    }

    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcTradeField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTrade(@Nonnull FtdcRspEvent event,
                                          @Nonnull CThostFtdcTradeField Field) {
        event.getFtdcTrade()
                /// 经纪公司代码
                .setBrokerID(Field.getBrokerID())
                /// 投资者代码
                .setInvestorID(Field.getInvestorID())
                /// 合约代码
                .setInstrumentID(Field.getInstrumentID())
                /// 报单引用
                .setOrderRef(Field.getOrderRef())
                /// 用户代码
                .setUserID(Field.getUserID())
                /// 交易所代码
                .setExchangeID(Field.getExchangeID())
                /// 成交编号
                .setTradeID(Field.getTradeID())
                /// 买卖方向
                .setDirection(Field.getDirection())
                /// 报单编号
                .setOrderSysID(Field.getOrderSysID())
                /// 会员代码
                .setParticipantID(Field.getParticipantID())
                /// 客户代码
                .setClientID(Field.getClientID())
                /// 交易角色
                .setTradingRole(Field.getTradingRole())
                /// 合约在交易所的代码
                .setExchangeInstID(Field.getExchangeInstID())
                /// 开平标志
                .setOffsetFlag(Field.getOffsetFlag())
                /// 投机套保标志
                .setHedgeFlag(Field.getHedgeFlag())
                /// 价格
                .setPrice(Field.getPrice())
                /// 数量
                .setVolume(Field.getVolume())
                /// 成交日期
                .setTradeDate(Field.getTradeDate())
                /// 成交时间
                .setTradeTime(Field.getTradeTime())
                /// 成交类型
                .setTradeType(Field.getTradeType())
                /// 成交价来源
                .setPriceSource(Field.getPriceSource())
                /// 交易所交易员代码
                .setTraderID(Field.getTraderID())
                /// 本地报单编号
                .setOrderLocalID(Field.getOrderLocalID())
                /// 结算会员编号
                .setClearingPartID(Field.getClearingPartID())
                /// 业务单元
                .setBusinessUnit(Field.getBusinessUnit())
                /// 序号
                .setSequenceNo(Field.getSequenceNo())
                /// 交易日
                .setTradingDay(Field.getTradingDay())
                /// 结算编号
                .setSettlementID(Field.getSettlementID())
                /// 经纪公司报单编号
                .setBrokerOrderSeq(Field.getBrokerOrderSeq())
                /// 成交来源
                .setTradeSource(Field.getTradeSource())
                /// 投资单元代码
                .setInvestUnitID(Field.getInvestUnitID());
        return event;
    }


    /**
     * @param event FtdcRspEvent
     * @param Field CThostFtdcTradingAccountField
     * @return FtdcRspEvent
     */
    public static FtdcRspEvent writeTradingAccount(@Nonnull FtdcRspEvent event,
                                                   @Nonnull CThostFtdcTradingAccountField Field,
                                                   CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        event.getFtdcTradingAccount()
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                .setRequestID(RequestID)
                .setIsLast(IsLast)
                /// 经纪公司代码
                .setBrokerID(Field.getBrokerID())
                /// 投资者账号
                .setAccountID(Field.getAccountID())
                /// 上次质押金额
                .setPreMortgage(Field.getPreMortgage())
                /// 上次信用额度
                .setPreCredit(Field.getPreCredit())
                /// 上次存款额
                .setPreDeposit(Field.getPreDeposit())
                /// 上次结算准备金
                .setPreBalance(Field.getPreBalance())
                /// 上次占用的保证金
                .setPreMargin(Field.getPreMargin())
                /// 利息基数
                .setInterestBase(Field.getInterestBase())
                /// 利息收入
                .setInterest(Field.getInterest())
                /// 入金金额
                .setDeposit(Field.getDeposit())
                /// 出金金额
                .setWithdraw(Field.getWithdraw())
                /// 冻结的保证金
                .setFrozenMargin(Field.getFrozenMargin())
                /// 冻结的资金
                .setFrozenCash(Field.getFrozenCash())
                /// 冻结的手续费
                .setFrozenCommission(Field.getFrozenCommission())
                /// 当前保证金总额
                .setCurrMargin(Field.getCurrMargin())
                /// 资金差额
                .setCashIn(Field.getCashIn())
                /// 手续费
                .setCommission(Field.getCommission())
                /// 平仓盈亏
                .setCloseProfit(Field.getCloseProfit())
                /// 持仓盈亏
                .setPositionProfit(Field.getPositionProfit())
                /// 期货结算准备金
                .setBalance(Field.getBalance())
                /// 可用资金
                .setAvailable(Field.getAvailable())
                /// 可取资金
                .setWithdrawQuota(Field.getWithdrawQuota())
                /// 基本准备金
                .setReserve(Field.getReserve())
                /// 交易日
                .setTradingDay(Field.getTradingDay())
                /// 结算编号
                .setSettlementID(Field.getSettlementID())
                /// 信用额度
                .setCredit(Field.getCredit())
                /// 质押金额
                .setMortgage(Field.getMortgage())
                /// 交易所保证金
                .setExchangeMargin(Field.getExchangeMargin())
                /// 投资者交割保证金
                .setDeliveryMargin(Field.getDeliveryMargin())
                /// 交易所交割保证金
                .setExchangeDeliveryMargin(Field.getExchangeDeliveryMargin())
                /// 保底期货结算准备金
                .setReserveBalance(Field.getReserveBalance())
                /// 币种代码
                .setCurrencyID(Field.getCurrencyID())
                /// 上次货币质入金额
                .setPreFundMortgageIn(Field.getPreFundMortgageIn())
                /// 上次货币质出金额
                .setPreFundMortgageOut(Field.getPreFundMortgageOut())
                /// 货币质入金额
                .setFundMortgageIn(Field.getFundMortgageIn())
                /// 货币质出金额
                .setFundMortgageOut(Field.getFundMortgageOut())
                /// 货币质押余额
                .setFundMortgageAvailable(Field.getFundMortgageAvailable())
                /// 可质押货币金额
                .setMortgageableFund(Field.getMortgageableFund())
                /// 特殊产品占用保证金
                .setSpecProductMargin(Field.getSpecProductMargin())
                /// 特殊产品冻结保证金
                .setSpecProductFrozenMargin(Field.getSpecProductFrozenMargin())
                /// 特殊产品手续费
                .setSpecProductCommission(Field.getSpecProductCommission())
                /// 特殊产品冻结手续费
                .setSpecProductFrozenCommission(Field.getSpecProductFrozenCommission())
                /// 特殊产品持仓盈亏
                .setSpecProductPositionProfit(Field.getSpecProductPositionProfit())
                /// 特殊产品平仓盈亏
                .setSpecProductCloseProfit(Field.getSpecProductCloseProfit())
                /// 根据持仓盈亏算法计算的特殊产品持仓盈亏
                .setSpecProductPositionProfitByAlg(Field.getSpecProductPositionProfitByAlg())
                /// 特殊产品交易所保证金
                .setSpecProductExchangeMargin(Field.getSpecProductExchangeMargin())
                /// 业务类型
                .setBizType(Field.getBizType())
                /// 延时换汇冻结金额
                .setFrozenSwap(Field.getFrozenSwap())
                /// 剩余换汇额度
                .setRemainSwap(Field.getRemainSwap());
        return event;
    }

    public static FtdcRspEvent writeSpecificInstrument(FtdcRspEvent event,
                                                       SpecificInstrumentSource source,
                                                       CThostFtdcSpecificInstrumentField Field,
                                                       CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        event.getFtdcSpecificInstrument()
                // 事件来源, [FTDC响应信息] - 错误代码, 错误信息
                .setSource(source)
                .setErrorID(RspInfo.getErrorID())
                .setErrorMsg(RspInfo.getErrorMsg())
                // 请求ID, 是否最后一条信息
                .setRequestID(RequestID)
                .setIsLast(IsLast)
                // 合约代码
                .setInstrumentID(Field.getInstrumentID());
        return event;
    }


}
