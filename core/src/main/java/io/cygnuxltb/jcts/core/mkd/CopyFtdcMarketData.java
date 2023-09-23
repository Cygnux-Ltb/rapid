package io.cygnuxltb.jcts.core.mkd;

import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;

@Getter
@Setter
@Accessors(chain = true)
public final class CopyFtdcMarketData implements JsonSerializable {

    // 交易日
    private String TradingDay;
    // InstrumentID
    private String InstrumentID;
    // 交易所ID
    private String ExchangeID;
    // 最新价
    private long LastPrice;
    // 上次结算价
    private double PreSettlementPrice;
    // 昨收盘
    private double PreClosePrice;
    // 昨持仓量
    private double PreOpenInterest;
    // 开盘价
    private double OpenPrice;
    // 最高价
    private double HighestPrice;
    // 最低价
    private double LowestPrice;
    // 成交量
    private int Volume;
    // 成交金额
    private long Turnover;
    // 持仓量
    private double OpenInterest;
    // 涨停板价
    private double UpperLimitPrice;
    // 跌停板价
    private double LowerLimitPrice;

    /* 五档买价卖价及买量卖量 v */
    private long BidPrice1;
    private int BidVolume1;
    private long AskPrice1;
    private int AskVolume1;
    private long BidPrice2;
    private int BidVolume2;
    private long AskPrice2;
    private int AskVolume2;
    private long BidPrice3;
    private int BidVolume3;
    private long AskPrice3;
    private int AskVolume3;
    private long BidPrice4;
    private int BidVolume4;
    private long AskPrice4;
    private int AskVolume4;
    private long BidPrice5;
    private int BidVolume5;
    private long AskPrice5;
    private int AskVolume5;
    /* 五档买价卖价及买量卖量 ^ */

    // 平均价格
    private long AveragePrice;
    // 更新时间
    private String UpdateTime;
    // 更新毫秒数
    private int UpdateMillisec;


    @Override
    public String toString() {
        return JsonWrapper.toJsonHasNulls(this);
    }

    @Override
    @Nonnull
    public String toJson() {
        return this.toString();
    }

}
