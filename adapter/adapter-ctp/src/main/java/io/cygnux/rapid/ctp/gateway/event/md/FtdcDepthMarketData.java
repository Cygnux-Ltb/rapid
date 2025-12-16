package io.cygnux.rapid.ctp.gateway.event.md;

import io.mercury.serialization.json.JsonWriter;

/**
 * 深度行情数据结构<br>
 * <p>
 * [数据结构原型]
 * <pre>
 * ///深度行情
 * struct CThostFtdcDepthMarketDataField
 * {
 * ///交易日
 * TThostFtdcDateType	TradingDay;
 * ///保留的无效字段
 * TThostFtdcOldInstrumentIDType	reserve1;
 * ///交易所代码
 * TThostFtdcExchangeIDType	ExchangeID;
 * ///保留的无效字段
 * TThostFtdcOldExchangeInstIDType	reserve2;
 * ///最新价
 * TThostFtdcPriceType	LastPrice;
 * ///上次结算价
 * TThostFtdcPriceType	PreSettlementPrice;
 * ///昨收盘
 * TThostFtdcPriceType	PreClosePrice;
 * ///昨持仓量
 * TThostFtdcLargeVolumeType	PreOpenInterest;
 * ///今开盘
 * TThostFtdcPriceType	OpenPrice;
 * ///最高价
 * TThostFtdcPriceType	HighestPrice;
 * ///最低价
 * TThostFtdcPriceType	LowestPrice;
 * ///数量
 * TThostFtdcVolumeType	Volume;
 * ///成交金额
 * TThostFtdcMoneyType	Turnover;
 * ///持仓量
 * TThostFtdcLargeVolumeType	OpenInterest;
 * ///今收盘
 * TThostFtdcPriceType	ClosePrice;
 * ///本次结算价
 * TThostFtdcPriceType	SettlementPrice;
 * ///涨停板价
 * TThostFtdcPriceType	UpperLimitPrice;
 * ///跌停板价
 * TThostFtdcPriceType	LowerLimitPrice;
 * ///昨虚实度
 * TThostFtdcRatioType	PreDelta;
 * ///今虚实度
 * TThostFtdcRatioType	CurrDelta;
 * ///最后修改时间
 * TThostFtdcTimeType	UpdateTime;
 * ///最后修改毫秒
 * TThostFtdcMillisecType	UpdateMillisec;
 * ///申买价一
 * TThostFtdcPriceType	BidPrice1;
 * ///申买量一
 * TThostFtdcVolumeType	BidVolume1;
 * ///申卖价一
 * TThostFtdcPriceType	AskPrice1;
 * ///申卖量一
 * TThostFtdcVolumeType	AskVolume1;
 * ///申买价二
 * TThostFtdcPriceType	BidPrice2;
 * ///申买量二
 * TThostFtdcVolumeType	BidVolume2;
 * ///申卖价二
 * TThostFtdcPriceType	AskPrice2;
 * ///申卖量二
 * TThostFtdcVolumeType	AskVolume2;
 * ///申买价三
 * TThostFtdcPriceType	BidPrice3;
 * ///申买量三
 * TThostFtdcVolumeType	BidVolume3;
 * ///申卖价三
 * TThostFtdcPriceType	AskPrice3;
 * ///申卖量三
 * TThostFtdcVolumeType	AskVolume3;
 * ///申买价四
 * TThostFtdcPriceType	BidPrice4;
 * ///申买量四
 * TThostFtdcVolumeType	BidVolume4;
 * ///申卖价四
 * TThostFtdcPriceType	AskPrice4;
 * ///申卖量四
 * TThostFtdcVolumeType	AskVolume4;
 * ///申买价五
 * TThostFtdcPriceType	BidPrice5;
 * ///申买量五
 * TThostFtdcVolumeType	BidVolume5;
 * ///申卖价五
 * TThostFtdcPriceType	AskPrice5;
 * ///申卖量五
 * TThostFtdcVolumeType	AskVolume5;
 * ///当日均价
 * TThostFtdcPriceType	AveragePrice;
 * ///业务日期
 * TThostFtdcDateType	ActionDay;
 * ///合约代码
 * TThostFtdcInstrumentIDType	InstrumentID;
 * ///合约在交易所的代码
 * TThostFtdcExchangeInstIDType	ExchangeInstID;
 * ///上带价
 * TThostFtdcPriceType	BandingUpperPrice;
 * ///下带价
 * TThostFtdcPriceType	BandingLowerPrice;
 * };
 * </pre>
 */
public class FtdcDepthMarketData {

    /**
     * (自定义增加字段) 接收时[Epoch微秒]
     */
    public long RecvEpochMicros;
    /**
     * 合约ID
     */
    public String InstrumentID;
    /**
     * 最新价
     */
    public double LastPrice;
    /**
     * 昨结算价
     */
    public double PreSettlementPrice;
    /**
     * 昨收盘
     */
    public double PreClosePrice;
    /**
     * 昨持仓量
     */
    public double PreOpenInterest;
    /**
     * 开盘价
     */
    public double OpenPrice;
    /**
     * 最高价
     */
    public double HighestPrice;
    /**
     * 最低价
     */
    public double LowestPrice;
    /**
     * 成交量
     */
    public int Volume;
    /**
     * 成交金额
     */
    public double Turnover;
    /**
     * 持仓量
     */
    public double OpenInterest;
    /**
     * 收盘价
     */
    public double ClosePrice;
    /**
     * 结算价
     */
    public double SettlementPrice;
    /**
     * 涨停板价
     */
    public double UpperLimitPrice;
    /**
     * 跌停板价
     */
    public double LowerLimitPrice;
    /**
     * 昨Delta
     */
    public double PreDelta;
    /**
     * 今Delta
     */
    public double CurrDelta;
    /**
     * 第一档买价
     */
    public double BidPrice1;
    /**
     * 第一档买量
     */
    public int BidVolume1;
    /**
     * 第一档卖价
     */
    public double AskPrice1;
    /**
     * 第一档卖量
     */
    public int AskVolume1;
    /**
     * 平均价格
     */
    public double AveragePrice;
    /**
     * 上带价
     */
    public double BandingUpperPrice;
    /**
     * 下带价
     */
    public double BandingLowerPrice;
    /**
     * 更新时间
     */
    public String UpdateTime;
    /**
     * 更新毫秒数
     */
    public int UpdateMillisec;
    /**
     * 交易日
     */
    public String TradingDay;
    /**
     * 业务日期
     */
    public String ActionDay;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










