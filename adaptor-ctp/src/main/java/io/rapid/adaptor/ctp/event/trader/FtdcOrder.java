package io.rapid.adaptor.ctp.event.trader;

import io.mercury.serialization.json.JsonWriter;

/**
 * FtdcOrder<br>
 * <p>
 * [数据结构原型]
 * <pre>
 * ///报单
 * struct CThostFtdcOrderField
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
 * ///本地报单编号
 * TThostFtdcOrderLocalIDType	OrderLocalID;
 * ///交易所代码
 * TThostFtdcExchangeIDType	ExchangeID;
 * ///会员代码
 * TThostFtdcParticipantIDType	ParticipantID;
 * ///客户代码
 * TThostFtdcClientIDType	ClientID;
 * ///保留的无效字段
 * TThostFtdcOldExchangeInstIDType	reserve2;
 * ///交易所交易员代码
 * TThostFtdcTraderIDType	TraderID;
 * ///安装编号
 * TThostFtdcInstallIDType	InstallID;
 * ///报单提交状态
 * TThostFtdcOrderSubmitStatusType	OrderSubmitStatus;
 * ///报单提示序号
 * TThostFtdcSequenceNoType	NotifySequence;
 * ///交易日
 * TThostFtdcDateType	TradingDay;
 * ///结算编号
 * TThostFtdcSettlementIDType	SettlementID;
 * ///报单编号
 * TThostFtdcOrderSysIDType	OrderSysID;
 * ///报单来源
 * TThostFtdcOrderSourceType	OrderSource;
 * ///报单状态
 * TThostFtdcOrderStatusType	OrderStatus;
 * ///报单类型
 * TThostFtdcOrderTypeType	OrderType;
 * ///今成交数量
 * TThostFtdcVolumeType	VolumeTraded;
 * ///剩余数量
 * TThostFtdcVolumeType	VolumeTotal;
 * ///报单日期
 * TThostFtdcDateType	InsertDate;
 * ///委托时间
 * TThostFtdcTimeType	InsertTime;
 * ///激活时间
 * TThostFtdcTimeType	ActiveTime;
 * ///挂起时间
 * TThostFtdcTimeType	SuspendTime;
 * ///最后修改时间
 * TThostFtdcTimeType	UpdateTime;
 * ///撤销时间
 * TThostFtdcTimeType	CancelTime;
 * ///最后修改交易所交易员代码
 * TThostFtdcTraderIDType	ActiveTraderID;
 * ///结算会员编号
 * TThostFtdcParticipantIDType	ClearingPartID;
 * ///序号
 * TThostFtdcSequenceNoType	SequenceNo;
 * ///前置编号
 * TThostFtdcFrontIDType	FrontID;
 * ///会话编号
 * TThostFtdcSessionIDType	SessionID;
 * ///用户端产品信息
 * TThostFtdcProductInfoType	UserProductInfo;
 * ///状态信息
 * TThostFtdcErrorMsgType	StatusMsg;
 * ///用户强评标志
 * TThostFtdcBoolType	UserForceClose;
 * ///操作用户代码
 * TThostFtdcUserIDType	ActiveUserID;
 * ///经纪公司报单编号
 * TThostFtdcSequenceNoType	BrokerOrderSeq;
 * ///相关报单
 * TThostFtdcOrderSysIDType	RelativeOrderSysID;
 * ///郑商所成交数量
 * TThostFtdcVolumeType	ZCETotalTradedVolume;
 * ///互换单标志
 * TThostFtdcBoolType	IsSwapOrder;
 * ///营业部编号
 * TThostFtdcBranchIDType	BranchID;
 * ///投资单元代码
 * TThostFtdcInvestUnitIDType	InvestUnitID;
 * ///资金账号
 * TThostFtdcAccountIDType	AccountID;
 * ///币种代码
 * TThostFtdcCurrencyIDType	CurrencyID;
 * ///保留的无效字段
 * TThostFtdcOldIPAddressType	reserve3;
 * ///Mac地址
 * TThostFtdcMacAddressType	MacAddress;
 * ///合约代码
 * TThostFtdcInstrumentIDType	InstrumentID;
 * ///合约在交易所的代码
 * TThostFtdcExchangeInstIDType	ExchangeInstID;
 * ///IP地址
 * TThostFtdcIPAddressType	IPAddress;
 * };
 * </pre>
 */
public final class FtdcOrder {

    /**
     * (自定义增加字段) 接收时[Epoch微秒]
     */
    public long RecvEpochMicros;
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
     * 本地报单编号
     */
    public String OrderLocalID;
    /**
     * 交易所代码
     */
    public String ExchangeID;
    /**
     * 会员代码
     */
    public String ParticipantID;
    /**
     * 客户代码
     */
    public String ClientID;
    /**
     * 合约在交易所的代码
     */
    public String ExchangeInstID;
    /**
     * 交易所交易员代码
     */
    public String TraderID;
    /**
     * 安装编号
     */
    public int InstallID;
    /**
     * 报单提交状态
     */
    public int OrderSubmitStatus;
    /**
     * 报单提示序号
     */
    public int NotifySequence;
    /**
     * 交易日
     */
    public String TradingDay;
    /**
     * 结算编号
     */
    public int SettlementID;
    /**
     * 报单编号
     */
    public String OrderSysID;
    /**
     * 报单来源
     */
    public int OrderSource;
    /**
     * 报单状态
     */
    public int OrderStatus;
    /**
     * 报单类型
     */
    public int OrderType;
    /**
     * 今成交数量
     */
    public int VolumeTraded;
    /**
     * 剩余数量
     */
    public int VolumeTotal;
    /**
     * 报单日期
     */
    public String InsertDate;
    /**
     * 委托时间
     */
    public String InsertTime;
    /**
     * 激活时间
     */
    public String ActiveTime;
    /**
     * 挂起时间
     */
    public String SuspendTime;
    /**
     * 最后修改时间
     */
    public String UpdateTime;
    /**
     * 撤销时间
     */
    public String CancelTime;
    /**
     * 最后修改交易所交易员代码
     */
    public String ActiveTraderID;
    /**
     * 结算会员编号
     */
    public String ClearingPartID;
    /**
     * 序号
     */
    public int SequenceNo;
    /**
     * 前置编号
     */
    public int FrontID;
    /**
     * 会话编号
     */
    public int SessionID;
    /**
     * 用户端产品信息
     */
    public String UserProductInfo;
    /**
     * 状态信息
     */
    public String StatusMsg;
    /**
     * 用户强评标志
     */
    public int UserForceClose;
    /**
     * 操作用户代码
     */
    public String ActiveUserID;
    /**
     * 经纪公司报单编号
     */
    public int BrokerOrderSeq;
    /**
     * 相关报单
     */
    public String RelativeOrderSysID;
    /**
     * 郑商所成交数量
     */
    public int ZCETotalTradedVolume;
    /**
     * 互换单标志
     */
    public int IsSwapOrder;
    /**
     * 营业部编号
     */
    public String BranchID;
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










