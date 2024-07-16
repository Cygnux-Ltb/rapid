package io.rapid.adaptor.ctp.gateway.event.recv.trader;

import ctp.thostapi.CThostFtdcTradingAccountField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class FtdcTradingAccount {

    /// 经纪公司代码
    private String BrokerID;

    /// 投资者帐号
    private String AccountID;

    /// 上次质押金额
    private double PreMortgage;

    /// 上次信用额度
    private double PreCredit;

    /// 上次存款额
    private double PreDeposit;

    /// 上次结算准备金
    private double PreBalance;

    /// 上次占用的保证金
    private double PreMargin;

    /// 利息基数
    private double InterestBase;

    /// 利息收入
    private double Interest;

    /// 入金金额
    private double Deposit;

    /// 出金金额
    private double Withdraw;

    /// 冻结的保证金
    private double FrozenMargin;

    /// 冻结的资金
    private double FrozenCash;

    /// 冻结的手续费
    private double FrozenCommission;

    /// 当前保证金总额
    private double CurrMargin;

    /// 资金差额
    private double CashIn;

    /// 手续费
    private double Commission;

    /// 平仓盈亏
    private double CloseProfit;

    /// 持仓盈亏
    private double PositionProfit;

    /// 期货结算准备金
    private double Balance;

    /// 可用资金
    private double Available;

    /// 可取资金
    private double WithdrawQuota;

    /// 基本准备金
    private double Reserve;

    /// 交易日
    private String TradingDay;

    /// 结算编号
    private int SettlementID;

    /// 信用额度
    private double Credit;

    /// 质押金额
    private double Mortgage;

    /// 交易所保证金
    private double ExchangeMargin;

    /// 投资者交割保证金
    private double DeliveryMargin;

    /// 交易所交割保证金
    private double ExchangeDeliveryMargin;

    /// 保底期货结算准备金
    private double ReserveBalance;

    /// 币种代码
    private String CurrencyID;

    /// 上次货币质入金额
    private double PreFundMortgageIn;

    /// 上次货币质出金额
    private double PreFundMortgageOut;

    /// 货币质入金额
    private double FundMortgageIn;

    /// 货币质出金额
    private double FundMortgageOut;

    /// 货币质押余额
    private double FundMortgageAvailable;

    /// 可质押货币金额
    private double MortgageableFund;

    /// 特殊产品占用保证金
    private double SpecProductMargin;

    /// 特殊产品冻结保证金
    private double SpecProductFrozenMargin;

    /// 特殊产品手续费
    private double SpecProductCommission;

    /// 特殊产品冻结手续费
    private double SpecProductFrozenCommission;

    /// 特殊产品持仓盈亏
    private double SpecProductPositionProfit;

    /// 特殊产品平仓盈亏
    private double SpecProductCloseProfit;

    /// 根据持仓盈亏算法计算的特殊产品持仓盈亏
    private double SpecProductPositionProfitByAlg;

    /// 特殊产品交易所保证金
    private double SpecProductExchangeMargin;

    /// 业务类型
    private char BizType;

    /// 延时换汇冻结金额
    private double FrozenSwap;

    /// 剩余换汇额度
    private double RemainSwap;

    /**
     * @param TradingAccountField CThostFtdcTradingAccountField
     * @return FtdcTradingAccount
     */
    public FtdcTradingAccount copy(CThostFtdcTradingAccountField TradingAccountField) {
        return this
                .setBrokerID(TradingAccountField.getBrokerID())
                .setAccountID(TradingAccountField.getAccountID())
                .setPreMortgage(TradingAccountField.getPreMortgage())
                .setPreCredit(TradingAccountField.getPreCredit())
                .setPreDeposit(TradingAccountField.getPreDeposit())
                .setPreBalance(TradingAccountField.getPreBalance())
                .setPreMargin(TradingAccountField.getPreMargin())
                .setInterestBase(TradingAccountField.getInterestBase())
                .setInterest(TradingAccountField.getInterest())
                .setDeposit(TradingAccountField.getDeposit())
                .setWithdraw(TradingAccountField.getWithdraw())
                .setFrozenMargin(TradingAccountField.getFrozenMargin())
                .setFrozenCash(TradingAccountField.getFrozenCash())
                .setFrozenCommission(TradingAccountField.getFrozenCommission())
                .setCurrMargin(TradingAccountField.getCurrMargin())
                .setCashIn(TradingAccountField.getCashIn())
                .setCommission(TradingAccountField.getCommission())
                .setCloseProfit(TradingAccountField.getCloseProfit())
                .setPositionProfit(TradingAccountField.getPositionProfit())
                .setBalance(TradingAccountField.getBalance())
                .setAvailable(TradingAccountField.getAvailable())
                .setWithdrawQuota(TradingAccountField.getWithdrawQuota())
                .setReserve(TradingAccountField.getReserve())
                .setTradingDay(TradingAccountField.getTradingDay())
                .setSettlementID(TradingAccountField.getSettlementID())
                .setCredit(TradingAccountField.getCredit())
                .setMortgage(TradingAccountField.getMortgage())
                .setExchangeMargin(TradingAccountField.getExchangeMargin())
                .setDeliveryMargin(TradingAccountField.getDeliveryMargin())
                .setExchangeDeliveryMargin(TradingAccountField.getExchangeDeliveryMargin())
                .setReserveBalance(TradingAccountField.getReserveBalance())
                .setCurrencyID(TradingAccountField.getCurrencyID())
                .setPreFundMortgageIn(TradingAccountField.getPreFundMortgageIn())
                .setPreFundMortgageOut(TradingAccountField.getPreFundMortgageOut())
                .setFundMortgageIn(TradingAccountField.getFundMortgageIn())
                .setFundMortgageOut(TradingAccountField.getFundMortgageOut())
                .setFundMortgageAvailable(TradingAccountField.getFundMortgageAvailable())
                .setMortgageableFund(TradingAccountField.getMortgageableFund())
                .setSpecProductMargin(TradingAccountField.getSpecProductMargin())
                .setSpecProductFrozenMargin(TradingAccountField.getSpecProductFrozenMargin())
                .setSpecProductCommission(TradingAccountField.getSpecProductCommission())
                .setSpecProductFrozenCommission(TradingAccountField.getSpecProductFrozenCommission())
                .setSpecProductPositionProfit(TradingAccountField.getSpecProductPositionProfit())
                .setSpecProductCloseProfit(TradingAccountField.getSpecProductCloseProfit())
                .setSpecProductPositionProfitByAlg(TradingAccountField.getSpecProductPositionProfitByAlg())
                .setSpecProductExchangeMargin(TradingAccountField.getSpecProductExchangeMargin())
                .setBizType(TradingAccountField.getBizType())
                .setFrozenSwap(TradingAccountField.getFrozenSwap())
                .setRemainSwap(TradingAccountField.getRemainSwap());
    }

}