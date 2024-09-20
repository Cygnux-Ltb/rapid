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
    char AllTraded = '0';

    /**
     * 部分成交还在队列中
     */
    char PartTradedQueueing = '1';


    /**
     * 部分成交不在队列中
     */
    char PartTradedNotQueueing = '2';

    /**
     * 未成交还在队列中
     */
    char NoTradeQueueing = '3';

    /**
     * 未成交不在队列中
     */
    char NoTradeNotQueueing = '4';

    /**
     * 撤单
     */
    char Canceled = '5';

    /**
     * 未知
     */
    char Unknown = 'a';

    /**
     * 尚未触发
     */
    char NotTouched = 'b';

    /**
     * 已触发
     */
    char Touched = 'c';

    /**
     * 根据<b> [FTDC返回] </b>订单状态, 映射<b> [系统自定义] </b>订单状态
     *
     * @param orderStatus char
     * @return OrdStatus
     */
    @Nonnull
    static OrdStatus withOrderStatus(char orderStatus) {
        return switch (orderStatus) {
            // 未成交不在队列中 or 未成交还在队列中 return [OrdStatus.New]
            case NoTradeNotQueueing, NoTradeQueueing -> OrdStatus.NEW;
            // 部分成交不在队列中 or 部分成交还在队列中 return [OrdStatus.PartiallyFilled]
            case PartTradedNotQueueing, PartTradedQueueing -> OrdStatus.PARTIALLY_FILLED;
            // 全部成交 return [OrdStatus.Filled]
            case AllTraded -> OrdStatus.FILLED;
            // 撤单 return [OrdStatus.Canceled]
            case Canceled -> OrdStatus.CANCELED;
            // return [OrdStatus.Invalid]
            default -> OrdStatus.UNKNOWN;
        };
    }

}
