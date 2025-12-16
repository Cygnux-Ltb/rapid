package io.cygnux.gateway.ctp.consts;

import io.cygnux.rapid.core.shared.enums.SubscribeStatus;
import io.cygnux.rapid.core.shared.enums.TradingStatus;

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
public final class FtdcInstrumentStatus {

    /**
     * 开盘前
     */
    public static final char BEFORE_TRADING = '0';

    /**
     * 非交易
     */
    public static final char NO_TRADING = '1';

    /**
     * 连续交易
     */
    public static final char CONTINUOUS = '2';

    /**
     * 集合竞价报单
     */
    public static final char AUCTION_ORDERING = '3';

    /**
     * 集合竞价价格平衡
     */
    public static final char AUCTION_BALANCE = '4';

    /**
     * 集合竞价撮合
     */
    public static final char AUCTION_MATCH = '5';

    /**
     * 收盘
     */
    public static final char CLOSED = '6';

    public static SubscribeStatus getSubscribeStatus(int instrumentStatus) {
        return switch (instrumentStatus) {
            case NO_TRADING, CLOSED -> SubscribeStatus.SUBSCRIPTION_UNAVAILABLE;
            case BEFORE_TRADING,
                 CONTINUOUS,
                 AUCTION_ORDERING,
                 AUCTION_BALANCE,
                 AUCTION_MATCH -> SubscribeStatus.SUBSCRIPTION_AVAILABLE;
            default -> SubscribeStatus.NOT_PROVIDED;
        };
    }

    public static TradingStatus getTradingStatus(int instrumentStatus) {
        return switch (instrumentStatus) {
            case BEFORE_TRADING,
                 NO_TRADING -> TradingStatus.NO_TRADING;
            case CONTINUOUS,
                 AUCTION_ORDERING,
                 AUCTION_BALANCE,
                 AUCTION_MATCH -> TradingStatus.TRADING;
            case CLOSED -> TradingStatus.CLOSED_UNSETTLED;
            default -> TradingStatus.NOT_PROVIDED;
        };
    }

    public static String getPrompt(int instrumentStatus) {
        return switch (instrumentStatus) {
            case BEFORE_TRADING -> "开盘前";
            case NO_TRADING -> "非交易";
            case CONTINUOUS -> "连续交易";
            case AUCTION_ORDERING -> "集合竞价报单";
            case AUCTION_BALANCE -> "集合竞价价格平衡";
            case AUCTION_MATCH -> "集合竞价撮合";
            case CLOSED -> "收盘";
            default -> "NONE";
        };
    }

    private FtdcInstrumentStatus() {
    }

}
