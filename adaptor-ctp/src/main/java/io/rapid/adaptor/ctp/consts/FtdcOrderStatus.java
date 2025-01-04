package io.rapid.adaptor.ctp.consts;


import io.rapid.core.event.enums.OrdStatus;

import javax.annotation.Nonnull;

/**
 * ///TFtdcOrderStatusType是一个报单状态类型<br>
 * <br>
 * ///全部成交<br>
 * #define THOST_FTDC_OST_AllTraded '0'<br>
 * <br>
 * ///部分成交还在队列中<br>
 * #define THOST_FTDC_OST_PartTradedQueueing '1'<br>
 * <br>
 * ///部分成交不在队列中<br>
 * #define THOST_FTDC_OST_PartTradedNotQueueing '2'<br>
 * <br>
 * ///未成交还在队列中<br>
 * #define THOST_FTDC_OST_NoTradeQueueing '3'<br>
 * <br>
 * ///未成交不在队列中<br>
 * #define THOST_FTDC_OST_NoTradeNotQueueing '4'<br>
 * <br>
 * ///撤单<br>
 * #define THOST_FTDC_OST_Canceled '5'<br>
 * <br>
 * ///未知<br>
 * #define THOST_FTDC_OST_Unknown 'a'<br>
 * <br>
 * ///尚未触发<br>
 * #define THOST_FTDC_OST_NotTouched 'b'<br>
 * <br>
 * ///已触发<br>
 * #define THOST_FTDC_OST_Touched 'c'<br>
 *
 * @author yellow013
 */
public interface FtdcOrderStatus {

    /**
     * 全部成交
     */
    char ALL_TRADED = '0';

    /**
     * 部分成交还在队列中
     */
    char PART_TRADED_QUEUEING = '1';


    /**
     * 部分成交不在队列中
     */
    char PART_TRADED_NOT_QUEUEING = '2';

    /**
     * 未成交还在队列中
     */
    char NO_TRADE_QUEUEING = '3';

    /**
     * 未成交不在队列中
     */
    char NO_TRADE_NOT_QUEUEING = '4';

    /**
     * 撤单
     */
    char CANCELED = '5';

    /**
     * 未知
     */
    char UNKNOWN = 'a';

    /**
     * 尚未触发
     */
    char NOT_TOUCHED = 'b';

    /**
     * 已触发
     */
    char TOUCHED = 'c';

    /**
     * 根据<b> [FTDC返回] </b>订单状态, 映射<b> [系统自定义] </b>订单状态
     *
     * @param ftdcOrderStatus char
     * @return OrdStatus
     */
    @Nonnull
    static OrdStatus getOrdStatus(int ftdcOrderStatus) {
        return switch (ftdcOrderStatus) {
            // 未成交不在队列中 or 未成交还在队列中 return [OrdStatus.NEW]
            case NO_TRADE_NOT_QUEUEING -> OrdStatus.NEW;
            // 部分成交不在队列中 or 部分成交还在队列中 return [OrdStatus.PARTIALLY_FILLED]
            case PART_TRADED_NOT_QUEUEING, PART_TRADED_QUEUEING -> OrdStatus.PARTIALLY_FILLED;
            // 全部成交 return [OrdStatus.FILLED]
            case ALL_TRADED -> OrdStatus.FILLED;
            // 撤单 return [OrdStatus.CANCELED]
            case CANCELED -> OrdStatus.CANCELED;
            // return [OrdStatus.UNKNOWN]
            default -> OrdStatus.UNKNOWN;
        };
    }

    static String getPrompt(int ftdcOrderStatus) {
        return switch (ftdcOrderStatus) {
            case ALL_TRADED -> "全部成交";
            case PART_TRADED_QUEUEING -> "部分成交还在队列中";
            case PART_TRADED_NOT_QUEUEING -> "部分成交不在队列中";
            case NO_TRADE_QUEUEING -> "未成交还在队列中";
            case NO_TRADE_NOT_QUEUEING -> "未成交不在队列中";
            case CANCELED -> "撤单";
            case UNKNOWN -> "未知";
            case NOT_TOUCHED -> "尚未触发";
            case TOUCHED -> "已触发";
            default -> "NONE";
        };
    }

}
