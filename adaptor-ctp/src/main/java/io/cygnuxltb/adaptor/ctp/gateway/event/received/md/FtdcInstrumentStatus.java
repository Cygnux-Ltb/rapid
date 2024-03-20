package io.cygnuxltb.adaptor.ctp.gateway.event.received.md;

import ctp.thostapi.CThostFtdcInstrumentStatusField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FtdcInstrumentStatus {

    ///交易所代码
    private String ExchangeID;

    ///合约在交易所的代码
    private String ExchangeInstID;

    ///结算组代码
    private String SettlementGroupID;

    ///合约代码
    private String InstrumentID;

    ///合约交易状态
    /**
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
    private char InstrumentStatus;

    ///交易阶段编号
    private int TradingSegmentSN;

    ///进入本状态时间
    private String EnterTime;

    ///进入本状态原因
    /**
     * ///自动切换
     * #define THOST_FTDC_IER_Automatic '1'
     * ///手动切换
     * #define THOST_FTDC_IER_Manual '2'
     * ///熔断
     * #define THOST_FTDC_IER_Fuse '3'
     */
    private char EnterReason;

    public FtdcInstrumentStatus read(CThostFtdcInstrumentStatusField field) {
        return this
                // 交易所代码, 合约在交易所的代码, 结算组代码, 合约代码
                .setExchangeID(field.getExchangeID())
                .setExchangeInstID(field.getExchangeInstID())
                .setSettlementGroupID(field.getSettlementGroupID())
                .setInstrumentID(field.getInstrumentID())
                // 合约交易状态, 交易阶段编号, 进入本状态时间, 进入本状态原因
                .setInstrumentStatus(field.getInstrumentStatus())
                .setTradingSegmentSN(field.getTradingSegmentSN())
                .setEnterTime(field.getEnterTime())
                .setEnterReason(field.getEnterReason());
    }

}
