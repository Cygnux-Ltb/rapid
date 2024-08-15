package io.rapid.adaptor.ctp.consts;


import io.rapid.core.serializable.avro.enums.OrdStatus;

import javax.annotation.Nonnull;

import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_AllTraded;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_Canceled;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_NoTradeNotQueueing;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_NoTradeQueueing;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_NotTouched;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_PartTradedNotQueueing;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_PartTradedQueueing;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_Touched;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_OST_Unknown;

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
    char ALL_TRADED = THOST_FTDC_OST_AllTraded;

    /**
     * 部分成交还在队列中
     */
    char PART_TRADED_QUEUEING = THOST_FTDC_OST_PartTradedQueueing;

    /**
     * 部分成交不在队列中
     */
    char PART_TRADED_NOT_QUEUEING = THOST_FTDC_OST_PartTradedNotQueueing;

    /**
     * 未成交还在队列中
     */
    char NO_TRADE_QUEUEING = THOST_FTDC_OST_NoTradeQueueing;

    /**
     * 未成交不在队列中
     */
    char NO_TRADE_NOT_QUEUEING = THOST_FTDC_OST_NoTradeNotQueueing;

    /**
     * 撤单
     */
    char CANCELED = THOST_FTDC_OST_Canceled;

    /**
     * 未知
     */
    char UNKNOWN = THOST_FTDC_OST_Unknown;

    /**
     * 尚未触发
     */
    char NOT_TOUCHED = THOST_FTDC_OST_NotTouched;

    /**
     * 已触发
     */
    char TOUCHED = THOST_FTDC_OST_Touched;


    /**
     * 根据<b> [FTDC返回] </b>订单状态, 映射<b> [系统自定义] </b>订单状态
     *
     * @param orderStatus char
     * @return OrdStatus
     */
    @Nonnull
    static OrdStatus withOrderStatus(char orderStatus) {
        return  // 未成交不在队列中 or 未成交还在队列中 return [OrdStatus.New]
                NO_TRADE_NOT_QUEUEING == orderStatus || NO_TRADE_QUEUEING == orderStatus
                        ? OrdStatus.NEW
                        // 部分成交不在队列中 or 部分成交还在队列中 return [OrdStatus.PartiallyFilled]
                        : PART_TRADED_NOT_QUEUEING == orderStatus || PART_TRADED_QUEUEING == orderStatus
                        ? OrdStatus.PARTIALLY_FILLED
                        // 全部成交 return [OrdStatus.Filled]
                        : ALL_TRADED == orderStatus
                        ? OrdStatus.FILLED
                        // 撤单 return [OrdStatus.Canceled]
                        : CANCELED == orderStatus
                        ? OrdStatus.CANCELED
                        // return [OrdStatus.Invalid]
                        : OrdStatus.INVALID;
    }


}
