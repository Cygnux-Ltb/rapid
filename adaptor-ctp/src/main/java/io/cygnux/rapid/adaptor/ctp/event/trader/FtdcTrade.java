package io.cygnux.rapid.adaptor.ctp.event.trader;

import io.mercury.serialization.json.JsonWriter;

/**
 * FtdcTrade<br>
 * <p>
 * [数据结构原型]
 * <pre>
 * ///成交
 * struct CThostFtdcTradeField
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
 * ///交易所代码
 * TThostFtdcExchangeIDType	ExchangeID;
 * ///成交编号
 * TThostFtdcTradeIDType	TradeID;
 * ///买卖方向
 * TThostFtdcDirectionType	Direction;
 * ///报单编号
 * TThostFtdcOrderSysIDType	OrderSysID;
 * ///会员代码
 * TThostFtdcParticipantIDType	ParticipantID;
 * ///客户代码
 * TThostFtdcClientIDType	ClientID;
 * ///交易角色
 * TThostFtdcTradingRoleType	TradingRole;
 * ///保留的无效字段
 * TThostFtdcOldExchangeInstIDType	reserve2;
 * ///开平标志
 * TThostFtdcOffsetFlagType	OffsetFlag;
 * ///投机套保标志
 * TThostFtdcHedgeFlagType	HedgeFlag;
 * ///价格
 * TThostFtdcPriceType	Price;
 * ///数量
 * TThostFtdcVolumeType	Volume;
 * ///成交时期
 * TThostFtdcDateType	TradeDate;
 * ///成交时间
 * TThostFtdcTimeType	TradeTime;
 * ///成交类型
 * TThostFtdcTradeTypeType	TradeType;
 * ///成交价来源
 * TThostFtdcPriceSourceType	PriceSource;
 * ///交易所交易员代码
 * TThostFtdcTraderIDType	TraderID;
 * ///本地报单编号
 * TThostFtdcOrderLocalIDType	OrderLocalID;
 * ///结算会员编号
 * TThostFtdcParticipantIDType	ClearingPartID;
 * ///业务单元
 * TThostFtdcBusinessUnitType	BusinessUnit;
 * ///序号
 * TThostFtdcSequenceNoType	SequenceNo;
 * ///交易日
 * TThostFtdcDateType	TradingDay;
 * ///结算编号
 * TThostFtdcSettlementIDType	SettlementID;
 * ///经纪公司报单编号
 * TThostFtdcSequenceNoType	BrokerOrderSeq;
 * ///成交来源
 * TThostFtdcTradeSourceType	TradeSource;
 * ///投资单元代码
 * TThostFtdcInvestUnitIDType	InvestUnitID;
 * ///合约代码
 * TThostFtdcInstrumentIDType	InstrumentID;
 * ///合约在交易所的代码
 * TThostFtdcExchangeInstIDType	ExchangeInstID;
 * };
 * </pre>
 */
public final class FtdcTrade {

    /**
     * (自定义增加字段) 接收时[Epoch微秒]
     */
    public long RecvEpochMicros;
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
     * 交易所代码
     */
    public String ExchangeID;
    /**
     * 成交编号
     */
    public String TradeID;
    /**
     * 买卖方向
     */
    public int Direction;
    /**
     * 报单编号
     */
    public String OrderSysID;
    /**
     * 会员代码
     */
    public String ParticipantID;
    /**
     * 客户代码
     */
    public String ClientID;
    /**
     * 交易角色
     */
    public int TradingRole;
    /**
     * 合约在交易所的代码
     */
    public String ExchangeInstID;
    /**
     * 开平标志
     */
    public int OffsetFlag;
    /**
     * 投机套保标志
     */
    public int HedgeFlag;
    /**
     * 价格
     */
    public double Price;
    /**
     * 数量
     */
    public int Volume;
    /**
     * 成交日期
     */
    public String TradeDate;
    /**
     * 成交时间
     */
    public String TradeTime;
    /**
     * 成交类型
     */
    public int TradeType;
    /**
     * 成交价来源
     */
    public int PriceSource;
    /**
     * 交易所交易员代码
     */
    public String TraderID;
    /**
     * 本地报单编号
     */
    public String OrderLocalID;
    /**
     * 结算会员编号
     */
    public String ClearingPartID;
    /**
     * 业务单元
     */
    public String BusinessUnit;
    /**
     * 序号
     */
    public int SequenceNo;
    /**
     * 交易日
     */
    public String TradingDay;
    /**
     * 结算编号
     */
    public int SettlementID;
    /**
     * 经纪公司报单编号
     */
    public int BrokerOrderSeq;
    /**
     * 成交来源
     */
    public int TradeSource;
    /**
     * 投资单元代码
     */
    public String InvestUnitID;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










