package io.rapid.adaptor.ctp.gateway.event.received.trader;

import ctp.thostapi.CThostFtdcInvestorPositionField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class FtdcInvestorPosition {

    /// 合约代码
    private String InstrumentID;

    /// 经纪公司代码
    private String BrokerID;

    /// 投资者代码
    private String InvestorID;

    /// 持仓多空方向
    private char PosiDirection;

    /// 投机套保标志
    private char HedgeFlag;

    /// 持仓日期
    private char PositionDate;

    /// 上日持仓
    private int YdPosition;

    /// 今日持仓
    private int Position;

    /// 多头冻结
    private int LongFrozen;

    /// 空头冻结
    private int ShortFrozen;

    /// 开仓冻结金额
    private double LongFrozenAmount;

    /// 开仓冻结金额
    private double ShortFrozenAmount;

    /// 开仓量
    private int OpenVolume;

    /// 平仓量
    private int CloseVolume;

    /// 开仓金额
    private double OpenAmount;

    /// 平仓金额
    private double CloseAmount;

    /// 持仓成本
    private double PositionCost;

    /// 上次占用的保证金
    private double PreMargin;

    /// 占用的保证金
    private double UseMargin;

    /// 冻结的保证金
    private double FrozenMargin;

    /// 冻结的资金
    private double FrozenCash;

    /// 冻结的手续费
    private double FrozenCommission;

    /// 资金差额
    private double CashIn;

    /// 手续费
    private double Commission;

    /// 平仓盈亏
    private double CloseProfit;

    /// 持仓盈亏
    private double PositionProfit;

    /// 上次结算价
    private double PreSettlementPrice;

    /// 本次结算价
    private double SettlementPrice;

    /// 交易日
    private String TradingDay;

    /// 结算编号
    private int SettlementID;

    /// 开仓成本
    private double OpenCost;

    /// 交易所保证金
    private double ExchangeMargin;

    /// 组合成交形成的持仓
    private int CombPosition;

    /// 组合多头冻结
    private int CombLongFrozen;

    /// 组合空头冻结
    private int CombShortFrozen;

    /// 逐日盯市平仓盈亏
    private double CloseProfitByDate;

    /// 逐笔对冲平仓盈亏
    private double CloseProfitByTrade;

    /// 今日持仓
    private int TodayPosition;

    /// 保证金率
    private double MarginRateByMoney;

    /// 保证金率(按手数)
    private double MarginRateByVolume;

    /// 执行冻结
    private int StrikeFrozen;

    /// 执行冻结金额
    private double StrikeFrozenAmount;

    /// 放弃执行冻结
    private int AbandonFrozen;

    /// 交易所代码
    private String ExchangeID;

    /// 执行冻结的昨仓
    private int YdStrikeFrozen;

    /// 投资单元代码
    private String InvestUnitID;

    /// 大商所持仓成本差值，只有大商所使用
    private double PositionCostOffset;

    /**
     * @param InvestorPositionField CThostFtdcInvestorPositionField
     * @return FtdcInvestorPosition
     */
    public FtdcInvestorPosition copy(CThostFtdcInvestorPositionField InvestorPositionField) {
        return this
                /// 合约代码
                .setInstrumentID(InvestorPositionField.getInstrumentID())
                /// 经纪公司代码
                .setBrokerID(InvestorPositionField.getBrokerID())
                /// 投资者代码
                .setInvestorID(InvestorPositionField.getInvestorID())
                /// 持仓多空方向
                .setPosiDirection(InvestorPositionField.getPosiDirection())
                /// 投机套保标志
                .setHedgeFlag(InvestorPositionField.getHedgeFlag())
                /// 持仓日期
                .setPositionDate(InvestorPositionField.getPositionDate())
                /// 上日持仓
                .setYdPosition(InvestorPositionField.getYdPosition())
                /// 今日持仓
                .setPosition(InvestorPositionField.getPosition())
                /// 多头冻结
                .setLongFrozen(InvestorPositionField.getLongFrozen())
                /// 空头冻结
                .setShortFrozen(InvestorPositionField.getShortFrozen())
                /// 开仓冻结金额
                .setLongFrozenAmount(InvestorPositionField.getLongFrozenAmount())
                /// 开仓冻结金额
                .setShortFrozenAmount(InvestorPositionField.getShortFrozenAmount())
                /// 开仓量
                .setOpenVolume(InvestorPositionField.getOpenVolume())
                /// 平仓量
                .setCloseVolume(InvestorPositionField.getCloseVolume())
                /// 开仓金额
                .setOpenAmount(InvestorPositionField.getOpenAmount())
                /// 平仓金额
                .setCloseAmount(InvestorPositionField.getCloseAmount())
                /// 持仓成本
                .setPositionCost(InvestorPositionField.getPositionCost())
                /// 上次占用的保证金
                .setPreMargin(InvestorPositionField.getPreMargin())
                /// 占用的保证金
                .setUseMargin(InvestorPositionField.getUseMargin())
                /// 冻结的保证金
                .setFrozenMargin(InvestorPositionField.getFrozenMargin())
                /// 冻结的资金
                .setFrozenCash(InvestorPositionField.getFrozenCash())
                /// 冻结的手续费
                .setFrozenCommission(InvestorPositionField.getFrozenCommission())
                /// 资金差额
                .setCashIn(InvestorPositionField.getCashIn())
                /// 手续费
                .setCommission(InvestorPositionField.getCommission())
                /// 平仓盈亏
                .setCloseProfit(InvestorPositionField.getCloseProfit())
                /// 持仓盈亏
                .setPositionProfit(InvestorPositionField.getPositionProfit())
                /// 上次结算价
                .setPreSettlementPrice(InvestorPositionField.getPreSettlementPrice())
                /// 本次结算价
                .setSettlementPrice(InvestorPositionField.getSettlementPrice())
                /// 交易日
                .setTradingDay(InvestorPositionField.getTradingDay())
                /// 结算编号
                .setSettlementID(InvestorPositionField.getSettlementID())
                /// 开仓成本
                .setOpenCost(InvestorPositionField.getOpenCost())
                /// 交易所保证金
                .setExchangeMargin(InvestorPositionField.getExchangeMargin())
                /// 组合成交形成的持仓
                .setCombPosition(InvestorPositionField.getCombPosition())
                /// 组合多头冻结
                .setCombLongFrozen(InvestorPositionField.getCombLongFrozen())
                /// 组合空头冻结
                .setCombShortFrozen(InvestorPositionField.getCombShortFrozen())
                /// 逐日盯市平仓盈亏
                .setCloseProfitByDate(InvestorPositionField.getCloseProfitByDate())
                /// 逐笔对冲平仓盈亏
                .setCloseProfitByTrade(InvestorPositionField.getCloseProfitByTrade())
                /// 今日持仓
                .setTodayPosition(InvestorPositionField.getTodayPosition())
                /// 保证金率
                .setMarginRateByMoney(InvestorPositionField.getMarginRateByMoney())
                /// 保证金率(按手数)
                .setMarginRateByVolume(InvestorPositionField.getMarginRateByVolume())
                /// 执行冻结
                .setStrikeFrozen(InvestorPositionField.getStrikeFrozen())
                /// 执行冻结金额
                .setStrikeFrozenAmount(InvestorPositionField.getStrikeFrozenAmount())
                /// 放弃执行冻结
                .setAbandonFrozen(InvestorPositionField.getAbandonFrozen())
                /// 交易所代码
                .setExchangeID(InvestorPositionField.getExchangeID())
                /// 执行冻结的昨仓
                .setYdStrikeFrozen(InvestorPositionField.getYdStrikeFrozen())
                /// 投资单元代码
                .setInvestUnitID(InvestorPositionField.getInvestUnitID())
                /// 大商所持仓成本差值, 只有大商所使用
                // 6.3.15 版本使用
                // .setPositionCostOffset(InvestorPositionField.getPositionCostOffset())
                ;
    }

}
