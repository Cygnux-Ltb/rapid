package io.cygnuxltb.adaptor.ctp.gateway.rsp;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class FtdcDepthMarketData {

    // 交易日
    private String TradingDay;

    // 合约ID
    private String InstrumentID;

    // 交易所ID
    private String ExchangeID;

    // 合约在交易所的代码
    private String ExchangeInstID;

    // 最新价
    private double LastPrice;

    // 昨结算价
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
    private double Turnover;

    // 持仓量
    private double OpenInterest;

    // 收盘价
    private double ClosePrice;

    // 结算价
    private double SettlementPrice;

    // 涨停板价
    private double UpperLimitPrice;

    // 跌停板价
    private double LowerLimitPrice;

    // 昨Delta
    private double PreDelta;

    // 今Delta
    private double CurrDelta;

    /* 五档买价卖价及买量卖量 v */
    private double BidPrice1;
    private int BidVolume1;
    private double AskPrice1;
    private int AskVolume1;
    private double BidPrice2;
    private int BidVolume2;
    private double AskPrice2;
    private int AskVolume2;
    private double BidPrice3;
    private int BidVolume3;
    private double AskPrice3;
    private int AskVolume3;
    private double BidPrice4;
    private int BidVolume4;
    private double AskPrice4;
    private int AskVolume4;
    private double BidPrice5;
    private int BidVolume5;
    private double AskPrice5;
    private int AskVolume5;
    /* 五档买价卖价及买量卖量 ^ */

    // 平均价格
    private double AveragePrice;

    // 更新时间
    private String UpdateTime;

    // 更新毫秒数
    private int UpdateMillisec;

    // 业务日期
    private String ActionDay;

    public FtdcDepthMarketData load(CThostFtdcDepthMarketDataField field) {
        return this
                // 交易日, 合约ID, 交易所ID, 合约在交易所的代码
                .setTradingDay(field.getTradingDay())
                .setInstrumentID(field.getInstrumentID())
                .setExchangeID(field.getExchangeID())
                .setExchangeInstID(field.getExchangeInstID())
                // 最新价
                .setLastPrice(field.getLastPrice())
                // 昨结算价, 昨收盘, 昨持仓量
                .setPreSettlementPrice(field.getPreSettlementPrice())
                .setPreClosePrice(field.getPreClosePrice())
                .setPreOpenInterest(field.getPreOpenInterest())
                // 开盘价, 最高价, 最低价
                .setOpenPrice(field.getOpenPrice())
                .setHighestPrice(field.getHighestPrice())
                .setLowestPrice(field.getLowestPrice())
                // 成交量, 成交金额, 持仓量
                .setVolume(field.getVolume())
                .setTurnover(field.getTurnover())
                .setOpenInterest(field.getOpenInterest())
                // 收盘价, 结算价
                .setClosePrice(field.getClosePrice())
                .setSettlementPrice(field.getSettlementPrice())
                // 涨停板价, 跌停板价
                .setUpperLimitPrice(field.getUpperLimitPrice())
                .setLowerLimitPrice(field.getLowerLimitPrice())
                // 昨Delta, 今Delta
                .setPreDelta(field.getPreDelta())
                .setCurrDelta(field.getCurrDelta())
                // 五档买价卖价及买量卖量 v
                .setBidPrice1(field.getBidPrice1())
                .setBidVolume1(field.getBidVolume1())
                .setAskPrice1(field.getAskPrice1())
                .setAskVolume1(field.getAskVolume1())
                .setBidPrice2(field.getBidPrice2())
                .setBidVolume2(field.getBidVolume2())
                .setAskPrice2(field.getAskPrice2())
                .setAskVolume2(field.getAskVolume2())
                .setBidPrice3(field.getBidPrice3())
                .setBidVolume3(field.getBidVolume3())
                .setAskPrice3(field.getAskPrice3())
                .setAskVolume3(field.getAskVolume3())
                .setBidPrice4(field.getBidPrice4())
                .setBidVolume4(field.getBidVolume4())
                .setAskPrice4(field.getAskPrice4())
                .setAskVolume4(field.getAskVolume4())
                .setBidPrice5(field.getBidPrice5())
                .setBidVolume5(field.getBidVolume5())
                .setAskPrice5(field.getAskPrice5())
                .setAskVolume5(field.getAskVolume5())
                // 五档买价卖价及买量卖量 ^
                // 平均价格
                .setAveragePrice(field.getAveragePrice())
                // 更新时间, 更新毫秒数, 业务日期
                .setUpdateTime(field.getUpdateTime())
                .setUpdateMillisec(field.getUpdateMillisec())
                .setActionDay(field.getActionDay())
                ;
    }

}
