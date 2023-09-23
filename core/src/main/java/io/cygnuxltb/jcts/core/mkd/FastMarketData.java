package io.cygnuxltb.jcts.core.mkd;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 统一行情处理数据结构
 *
 * @author yellow013
 * @creation 2019年5月24日
 * @description 价格转换使用对应Instrument的价格乘数
 */
@Getter
@Setter
@Accessors(chain = true)
public class FastMarketData {

    // 标的代码 [Required]
    protected String instrumentCode;

    // Epoch [Required]
    protected long epochMillis;

    // 行情深度 [Required]
    protected int depth;

    // 最新价
    protected double lastPrice;

    // 成交量
    protected int volume;

    // 成交金额
    protected double turnover;

    // 交易日
    protected String tradingDay;

    // 上次结算价
    protected double preSettlementPrice;

    // 昨收盘
    protected double preClosePrice;

    // 昨持仓量
    protected double preOpenInterest;

    // 开盘价
    protected double openPrice;

    // 最高价
    protected double highestPrice;

    // 最低价
    protected double lowestPrice;

    // 持仓量
    protected double openInterest;

    // 收盘价
    protected double closePrice;

    // 结算价
    protected double settlementPrice;

    // 涨停板价
    protected double upperLimitPrice;

    // 跌停板价
    protected double lowerLimitPrice;

    // 昨Delta
    protected double preDelta;

    // 今Delta
    protected double currDelta;

    /* 五档买价卖价及买量卖量 v */
    protected double bidPrice1;
    protected int bidVolume1;
    protected double askPrice1;
    protected int askVolume1;
    protected double bidPrice2;
    protected int bidVolume2;
    protected double askPrice2;
    protected int askVolume2;
    protected double bidPrice3;
    protected int bidVolume3;
    protected double askPrice3;
    protected int askVolume3;
    protected double bidPrice4;
    protected int bidVolume4;
    protected double askPrice4;
    protected int askVolume4;
    protected double bidPrice5;
    protected int bidVolume5;
    protected double askPrice5;
    protected int askVolume5;
    /* 五档买价卖价及买量卖量 ^ */

    // 平均价格
    protected double averagePrice;

    // 更新时间
    protected String updateTime;

    // 更新毫秒数
    protected int updateMillisec;

    // 业务日期
    protected String actionDay;

}
