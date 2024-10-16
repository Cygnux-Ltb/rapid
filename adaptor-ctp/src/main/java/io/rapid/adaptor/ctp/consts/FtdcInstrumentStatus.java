package io.rapid.adaptor.ctp.consts;

/**
 * ///TFtdcInstrumentStatusType是一个合约交易状态类型<br>
 * <br>
 * ///开盘前<br>
 * #define THOST_FTDC_IS_BeforeTrading '0'<br>
 * <br>
 * ///非交易<br>
 * #define THOST_FTDC_IS_NoTrading '1'<br>
 * <br>
 * ///连续交易<br>
 * #define THOST_FTDC_IS_Continous '2'<br>
 * <br>
 * ///集合竞价报单<br>
 * #define THOST_FTDC_IS_AuctionOrdering '3'<br>
 * <br>
 * ///集合竞价价格平衡<br>
 * #define THOST_FTDC_IS_AuctionBalance '4'<br>
 * <br>
 * ///集合竞价撮合<br>
 * #define THOST_FTDC_IS_AuctionMatch '5'<br>
 * <br>
 * ///收盘<br>
 * #define THOST_FTDC_IS_Closed '6'<br>
 */
public interface FtdcInstrumentStatus {

    /**
     * 开盘前
     */
    char BEFORE_TRADING = '0';

    /**
     * 非交易
     */
    char NO_TRADING = '1';

    /**
     * 连续交易
     */
    char CONTINUOUS = '2';

    /**
     * 集合竞价报单
     */
    char AUCTION_ORDERING = '3';

    /**
     * 集合竞价价格平衡
     */
    char AUCTION_BALANCE = '4';

    /**
     * 集合竞价撮合
     */
    char AUCTION_MATCH = '5';

    /**
     * 收盘
     */
    char CLOSED = '6';

}
