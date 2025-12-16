package io.cygnux.rapid.ctp.gateway.event.trader;

import io.mercury.serialization.json.JsonWriter;

/**
 * 合约状态
 */
public final class FtdcInstrumentStatus {

    /**
     * 交易所代码
     */
    public String ExchangeID;
    /**
     * 合约在交易所的代码
     */
    public String ExchangeInstID;
    /**
     * 结算组代码
     */
    public String SettlementGroupID;
    /**
     * 合约代码
     */
    public String InstrumentID;
    /**
     * 合约交易状态
     * ///开盘前
     * #define THOST_FTDC_IS_BeforeTrading '0'
     * ///非交易
     * #define THOST_FTDC_IS_NoTrading '1'
     * ///连续交易
     * #define THOST_FTDC_IS_Continous '2'
     * ///集合竞价报单
     * #define THOST_FTDC_IS_AuctionOrdering '3'
     * ///集合竞价价格平衡
     * #define THOST_FTDC_IS_AuctionBalance '4'
     * ///集合竞价撮合
     * #define THOST_FTDC_IS_AuctionMatch '5'
     * ///收盘
     * #define THOST_FTDC_IS_Closed '6'
     */
    public int InstrumentStatus;
    /**
     *
     */
    public int InstrumentStatusMsg;
    /**
     * 交易阶段编号
     */
    public int TradingSegmentSN;
    /**
     * 进入本状态时间
     */
    public String EnterTime;
    /**
     * 进入本状态原因
     * ///自动切换
     * #define THOST_FTDC_IER_Automatic '1'
     * ///手动切换
     * #define THOST_FTDC_IER_Manual '2'
     * ///熔断
     * #define THOST_FTDC_IER_Fuse '3'
     */
    public int EnterReason;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










