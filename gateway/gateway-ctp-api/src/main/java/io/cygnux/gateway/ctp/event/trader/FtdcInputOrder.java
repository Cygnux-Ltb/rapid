package io.cygnux.gateway.ctp.event.trader;

import io.mercury.serialization.json.JsonWriter;

/**
 * FtdcInputOrder<br>
 * <p>
 * [数据结构原型]
 * <pre>
 * ///输入报单
 * struct CThostFtdcInputOrderField
 * {
 * ///经纪公司代码
 * TThostFtdcBrokerIDType	BrokerID;
 * ///投资者代码
 * TThostFtdcInvestorIDType	InvestorID;
 * ///保留的无效字段
 * TThostFtdcOldInstrumentIDType	reserve1;
 * ///报单引用
 * TThostFtdcOrderRefType	OrderRef;
 * ///用户代码
 * TThostFtdcUserIDType	UserID;
 * ///报单价格条件
 * TThostFtdcOrderPriceTypeType	OrderPriceType;
 * ///买卖方向
 * TThostFtdcDirectionType	Direction;
 * ///组合开平标志
 * TThostFtdcCombOffsetFlagType	CombOffsetFlag;
 * ///组合投机套保标志
 * TThostFtdcCombHedgeFlagType	CombHedgeFlag;
 * ///价格
 * TThostFtdcPriceType	LimitPrice;
 * ///数量
 * TThostFtdcVolumeType	VolumeTotalOriginal;
 * ///有效期类型
 * TThostFtdcTimeConditionType	TimeCondition;
 * ///GTD日期
 * TThostFtdcDateType	GTDDate;
 * ///成交量类型
 * TThostFtdcVolumeConditionType	VolumeCondition;
 * ///最小成交量
 * TThostFtdcVolumeType	MinVolume;
 * ///触发条件
 * TThostFtdcContingentConditionType	ContingentCondition;
 * ///止损价
 * TThostFtdcPriceType	StopPrice;
 * ///强平原因
 * TThostFtdcForceCloseReasonType	ForceCloseReason;
 * ///自动挂起标志
 * TThostFtdcBoolType	IsAutoSuspend;
 * ///业务单元
 * TThostFtdcBusinessUnitType	BusinessUnit;
 * ///请求编号
 * TThostFtdcRequestIDType	RequestID;
 * ///用户强评标志
 * TThostFtdcBoolType	UserForceClose;
 * ///互换单标志
 * TThostFtdcBoolType	IsSwapOrder;
 * ///交易所代码
 * TThostFtdcExchangeIDType	ExchangeID;
 * ///投资单元代码
 * TThostFtdcInvestUnitIDType	InvestUnitID;
 * ///资金账号
 * TThostFtdcAccountIDType	AccountID;
 * ///币种代码
 * TThostFtdcCurrencyIDType	CurrencyID;
 * ///交易编码
 * TThostFtdcClientIDType	ClientID;
 * ///保留的无效字段
 * TThostFtdcOldIPAddressType	reserve2;
 * ///Mac地址
 * TThostFtdcMacAddressType	MacAddress;
 * ///合约代码
 * TThostFtdcInstrumentIDType	InstrumentID;
 * ///IP地址
 * TThostFtdcIPAddressType	IPAddress;
 * };
 * </pre>
 */
public final class FtdcInputOrder {

    /**
     * FTDC响应信息 - 错误代码
     */
    public int ErrorID;
    /**
     * FTDC响应信息 - 错误信息
     */
    public String ErrorMsg;
    /**
     * 是否最后一条信息
     */
    public boolean IsLast;
    /**
     * 经纪公司代码
     */
    public String BrokerID;
    /**
     * 投资者代码
     */
    public String InvestorID;
    /**
     * 合约代码
     */
    public String InstrumentID;
    /**
     * 报单引用
     */
    public String OrderRef;
    /**
     * 用户代码
     */
    public String UserID;
    /**
     * 报单价格条件
     */
    public int OrderPriceType;
    /**
     * 买卖方向
     */
    public int Direction;
    /**
     * 组合开平标志
     */
    public String CombOffsetFlag;
    /**
     * 组合投机套保标志
     */
    public String CombHedgeFlag;
    /**
     * 价格
     */
    public double LimitPrice;
    /**
     * 数量
     */
    public int VolumeTotalOriginal;
    /**
     * 有效期类型
     */
    public int TimeCondition;
    /**
     * GTD日期
     */
    public String GTDDate;
    /**
     * 成交量类型
     */
    public int VolumeCondition;
    /**
     * 最小成交量
     */
    public int MinVolume;
    /**
     * 触发条件
     */
    public int ContingentCondition;
    /**
     * 止损价
     */
    public double StopPrice;
    /**
     * 强平原因
     */
    public int ForceCloseReason;
    /**
     * 自动挂起标志
     */
    public int IsAutoSuspend;
    /**
     * 业务单元
     */
    public String BusinessUnit;
    /**
     * 请求编号
     */
    public int RequestID;
    /**
     * 用户强评标志
     */
    public int UserForceClose;
    /**
     * 互换单标志
     */
    public int IsSwapOrder;
    /**
     * 交易所代码
     */
    public String ExchangeID;
    /**
     * 投资单元代码
     */
    public String InvestUnitID;
    /**
     * 资金账号
     */
    public String AccountID;
    /**
     * 币种代码
     */
    public String CurrencyID;
    /**
     * 交易编码
     */
    public String ClientID;
    /**
     * IP地址
     */
    public String IPAddress;
    /**
     * MAC地址
     */
    public String MacAddress;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










