package io.cygnux.rapid.adaptor.ctp.event.trader;

import io.mercury.serialization.json.JsonWriter;

/**
 * FtdcTradingAccount
 */
public final class FtdcTradingAccount {

    /**
     * FTDC响应信息 - 错误代码
     */
    public int ErrorID;
    /**
     * FTDC响应信息 - 错误信息
     */
    public String ErrorMsg;
    /**
     * 请求ID
     */
    public int RequestID;
    /**
     * 是否最后一条信息
     */
    public boolean IsLast;
    /**
     * 经纪公司代码
     */
    public String BrokerID;
    /**
     * 投资者账号
     */
    public String AccountID;
    /**
     * 上次质押金额
     */
    public double PreMortgage;
    /**
     * 上次信用额度
     */
    public double PreCredit;
    /**
     * 上次存款额
     */
    public double PreDeposit;
    /**
     * 上次结算准备金
     */
    public double PreBalance;
    /**
     * 上次占用的保证金
     */
    public double PreMargin;
    /**
     * 利息基数
     */
    public double InterestBase;
    /**
     * 利息收入
     */
    public double Interest;
    /**
     * 入金金额
     */
    public double Deposit;
    /**
     * 出金金额
     */
    public double Withdraw;
    /**
     * 冻结的保证金
     */
    public double FrozenMargin;
    /**
     * 冻结的资金
     */
    public double FrozenCash;
    /**
     * 冻结的手续费
     */
    public double FrozenCommission;
    /**
     * 当前保证金总额
     */
    public double CurrMargin;
    /**
     * 资金差额
     */
    public double CashIn;
    /**
     * 手续费
     */
    public double Commission;
    /**
     * 平仓盈亏
     */
    public double CloseProfit;
    /**
     * 持仓盈亏
     */
    public double PositionProfit;
    /**
     * 期货结算准备金
     */
    public double Balance;
    /**
     * 可用资金
     */
    public double Available;
    /**
     * 可取资金
     */
    public double WithdrawQuota;
    /**
     * 基本准备金
     */
    public double Reserve;
    /**
     * 交易日
     */
    public String TradingDay;
    /**
     * 结算编号
     */
    public int SettlementID;
    /**
     * 信用额度
     */
    public double Credit;
    /**
     * 质押金额
     */
    public double Mortgage;
    /**
     * 交易所保证金
     */
    public double ExchangeMargin;
    /**
     * 投资者交割保证金
     */
    public double DeliveryMargin;
    /**
     * 交易所交割保证金
     */
    public double ExchangeDeliveryMargin;
    /**
     * 保底期货结算准备金
     */
    public double ReserveBalance;
    /**
     * 币种代码
     */
    public String CurrencyID;
    /**
     * 上次货币质入金额
     */
    public double PreFundMortgageIn;
    /**
     * 上次货币质出金额
     */
    public double PreFundMortgageOut;
    /**
     * 货币质入金额
     */
    public double FundMortgageIn;
    /**
     * 货币质出金额
     */
    public double FundMortgageOut;
    /**
     * 货币质押余额
     */
    public double FundMortgageAvailable;
    /**
     * 可质押货币金额
     */
    public double MortgageableFund;
    /**
     * 特殊产品占用保证金
     */
    public double SpecProductMargin;
    /**
     * 特殊产品冻结保证金
     */
    public double SpecProductFrozenMargin;
    /**
     * 特殊产品手续费
     */
    public double SpecProductCommission;
    /**
     * 特殊产品冻结手续费
     */
    public double SpecProductFrozenCommission;
    /**
     * 特殊产品持仓盈亏
     */
    public double SpecProductPositionProfit;
    /**
     * 特殊产品平仓盈亏
     */
    public double SpecProductCloseProfit;
    /**
     * 根据持仓盈亏算法计算的特殊产品持仓盈亏
     */
    public double SpecProductPositionProfitByAlg;
    /**
     * 特殊产品交易所保证金
     */
    public double SpecProductExchangeMargin;
    /**
     * 业务类型
     */
    public int BizType;
    /**
     * 延时换汇冻结金额
     */
    public double FrozenSwap;
    /**
     * 剩余换汇额度
     */
    public double RemainSwap;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










