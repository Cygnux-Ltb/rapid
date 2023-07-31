package io.cygnuxltb.adaptor.ctp.consts;

import io.cygnuxltb.jcts.core.ser.enums.TrdAction;
import io.cygnuxltb.jcts.core.ser.enums.OrdStatus;
import io.cygnuxltb.jcts.core.ser.enums.TrdDirection;

import javax.annotation.Nonnull;

import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcDirection.BUY;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcDirection.SELL;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOffsetFlag.CLOSE;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOffsetFlag.CLOSE_TODAY;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOffsetFlag.CLOSE_YESTERDAY;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOffsetFlag.OPEN;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOrderStatus.ALL_TRADED;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOrderStatus.CANCELED;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOrderStatus.NO_TRADE_NOT_QUEUEING;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOrderStatus.NO_TRADE_QUEUEING;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOrderStatus.PART_TRADED_NOT_QUEUEING;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConst.FtdcOrderStatus.PART_TRADED_QUEUEING;

/**
 * @author yellow013
 */
public final class FtdcConstMapper {

    /**
     * 根据<b> [FTDC返回] </b>订单状态, 映射<b> [系统自定义] </b>订单状态
     *
     * @param orderStatus char
     * @return OrdStatus
     */
    @Nonnull
    public static OrdStatus withOrderStatus(char orderStatus) {
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

    /**
     * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
     *
     * @param combOffsetFlag String
     * @return TrdAction
     */
    @Nonnull
    public static TrdAction withOffsetFlag(@Nonnull String combOffsetFlag) {
        return withOffsetFlag(combOffsetFlag.charAt(0));
    }

    /**
     * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
     *
     * @param offsetFlag char
     * @return TrdAction
     */
    @Nonnull
    public static TrdAction withOffsetFlag(char offsetFlag) {
        return  // 开仓
                OPEN == offsetFlag ? TrdAction.OPEN
                        // 平仓
                        : CLOSE == offsetFlag ? TrdAction.CLOSE
                        // 平今
                        : CLOSE_TODAY == offsetFlag ? TrdAction.CLOSE_TODAY
                        // 平昨
                        : CLOSE_YESTERDAY == offsetFlag ? TrdAction.CLOSE_YESTERDAY
                        // 未知
                        : TrdAction.INVALID;
    }

    /**
     * 根据<b>[FTDC返回]</b>买卖方向类型, 映射<b>[系统自定义]</b>买卖方向类型类型
     *
     * @param direction char
     * @return TrdDirection
     */
    public static TrdDirection withDirection(char direction) {
        return  // 买
                BUY == direction ? TrdDirection.LONG
                        // 卖
                        : SELL == direction ? TrdDirection.SHORT
                        // 未知
                        : TrdDirection.INVALID;
    }

}
