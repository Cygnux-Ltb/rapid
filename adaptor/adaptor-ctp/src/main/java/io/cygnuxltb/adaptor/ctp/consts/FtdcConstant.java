package io.cygnuxltb.adaptor.ctp.consts;


import io.cygnuxltb.jcts.core.ser.enums.OrdStatus;
import io.cygnuxltb.jcts.core.ser.enums.TrdAction;
import io.cygnuxltb.jcts.core.ser.enums.TrdDirection;

import javax.annotation.Nonnull;

import static ctp.thostapi.thosttraderapiConstants.*;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcDirection.BUY;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcDirection.SELL;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOffsetFlag.CLOSE;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOffsetFlag.CLOSE_TODAY;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOffsetFlag.CLOSE_YESTERDAY;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOffsetFlag.OPEN;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOrderStatus.ALL_TRADED;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOrderStatus.CANCELED;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOrderStatus.NO_TRADE_NOT_QUEUEING;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOrderStatus.NO_TRADE_QUEUEING;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOrderStatus.PART_TRADED_NOT_QUEUEING;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstant.FtdcOrderStatus.PART_TRADED_QUEUEING;

public interface FtdcConstant {


    /**
     * ///TFtdcActionFlagType是一个操作标志类型<br>
     * <br>
     * ///删除<br>
     * #define THOST_FTDC_AF_Delete '0'<br>
     * <br>
     * ///修改<br>
     * #define THOST_FTDC_AF_Modify '3'<br>
     */
    interface FtdcActionFlag {
        /**
         * 删除
         */
        char DELETE = THOST_FTDC_AF_Delete;
        /**
         * 修改
         */
        char MODIFY = THOST_FTDC_AF_Modify;
    }


    /**
     * ///TFtdcContingentConditionType是一个触发条件类型<br>
     * <br>
     * ///立即<br>
     * #define THOST_FTDC_CC_Immediately '1'<br>
     * <br>
     * ///止损<br>
     * #define THOST_FTDC_CC_Touch '2'<br>
     * <br>
     * ///止赢<br>
     * #define THOST_FTDC_CC_TouchProfit '3'<br>
     * <br>
     * ///预埋单<br>
     * #define THOST_FTDC_CC_ParkedOrder '4'<br>
     * <br>
     * ///最新价大于条件价<br>
     * #define THOST_FTDC_CC_LastPriceGreaterThanStopPrice '5'<br>
     * <br>
     * ///最新价大于等于条件价<br>
     * #define THOST_FTDC_CC_LastPriceGreaterEqualStopPrice '6'<br>
     * <br>
     * ///最新价小于条件价<br>
     * #define THOST_FTDC_CC_LastPriceLesserThanStopPrice '7'<br>
     * <br>
     * ///最新价小于等于条件价<br>
     * #define THOST_FTDC_CC_LastPriceLesserEqualStopPrice '8'<br>
     * <br>
     * ///卖一价大于条件价<br>
     * #define THOST_FTDC_CC_AskPriceGreaterThanStopPrice '9'<br>
     * <br>
     * ///卖一价大于等于条件价<br>
     * #define THOST_FTDC_CC_AskPriceGreaterEqualStopPrice 'A'<br>
     * <br>
     * ///卖一价小于条件价<br>
     * #define THOST_FTDC_CC_AskPriceLesserThanStopPrice 'B'<br>
     * <br>
     * ///卖一价小于等于条件价<br>
     * #define THOST_FTDC_CC_AskPriceLesserEqualStopPrice 'C'<br>
     * <br>
     * ///买一价大于条件价<br>
     * #define THOST_FTDC_CC_BidPriceGreaterThanStopPrice 'D'<br>
     * <br>
     * ///买一价大于等于条件价<br>
     * #define THOST_FTDC_CC_BidPriceGreaterEqualStopPrice 'E'<br>
     * <br>
     * ///买一价小于条件价<br>
     * #define THOST_FTDC_CC_BidPriceLesserThanStopPrice 'F'<br>
     * <br>
     * ///买一价小于等于条件价<br>
     * #define THOST_FTDC_CC_BidPriceLesserEqualStopPrice 'H'<br>
     */
    interface FtdcContingentCondition {
        /**
         * 立即
         */
        char IMMEDIATELY = THOST_FTDC_CC_Immediately;
        /**
         * 止损
         */
        char TOUCH = THOST_FTDC_CC_Touch;
        /**
         * 止赢
         */
        char TOUCH_PROFIT = THOST_FTDC_CC_TouchProfit;
        /**
         * 预埋单
         */
        char PARKED_ORDER = THOST_FTDC_CC_ParkedOrder;
    }


    /**
     * TFtdcDirectionType是一个买卖方向类型 <br>
     * <br>
     * ///买<br>
     * #define THOST_FTDC_D_Buy '0'<br>
     * <br>
     * ///卖<br>
     * #define THOST_FTDC_D_Sell '1'<br>
     */
    interface FtdcDirection {
        /**
         * 买
         */
        char BUY = THOST_FTDC_D_Buy;
        /**
         * 卖
         */
        char SELL = THOST_FTDC_D_Sell;
    }


    /**
     * ///TFtdcForceCloseReasonType是一个强平原因类型<br>
     * <br>
     * ///非强平<br>
     * #define THOST_FTDC_FCC_NotForceClose '0'<br>
     * <br>
     * ///资金不足<br>
     * #define THOST_FTDC_FCC_LackDeposit '1'<br>
     * <br>
     * ///客户超仓<br>
     * #define THOST_FTDC_FCC_ClientOverPositionLimit '2'<br>
     * <br>
     * ///会员超仓<br>
     * #define THOST_FTDC_FCC_MemberOverPositionLimit '3'<br>
     * <br>
     * ///持仓非整数倍<br>
     * #define THOST_FTDC_FCC_NotMultiple '4'<br>
     * <br>
     * ///违规<br>
     * #define THOST_FTDC_FCC_Violation '5'<br>
     * <br>
     * ///其它<br>
     * #define THOST_FTDC_FCC_Other '6'<br>
     * <br>
     * ///自然人临近交割<br>
     * #define THOST_FTDC_FCC_PersonDeliv '7'<br>
     */
    interface FtdcForceCloseReason {
        /**
         * 非强平
         */
        char NOT_FORCE_CLOSE = THOST_FTDC_FCC_NotForceClose;
    }


    /**
     * ///TFtdcHedgeFlagType是一个投机套保标志类型<br>
     * <br>
     * ///投机<br>
     * #define THOST_FTDC_HF_Speculation '1'<br>
     * <br>
     * ///套利<br>
     * #define THOST_FTDC_HF_Arbitrage '2'<br>
     * <br>
     * ///套保<br>
     * #define THOST_FTDC_HF_Hedge '3'<br>
     * <br>
     * ///做市商<br>
     * #define THOST_FTDC_HF_MarketMaker '5'<br>
     * <br>
     * ///第一腿投机第二腿套保 大商所专用<br>
     * #define THOST_FTDC_HF_SpecHedge '6'<br>
     * <br>
     * ///第一腿套保第二腿投机 大商所专用<br>
     * #define THOST_FTDC_HF_HedgeSpec '7'<br>
     */
    interface FtdcHedgeFlag {
        /**
         * 组合投机套保标识, 投机, [char]
         */
        char SPECULATION = THOST_FTDC_HF_Speculation;
        /**
         * 组合投机套保标识, 投机, [String]
         */
        String SPECULATION_STR = String.valueOf(THOST_FTDC_HF_Speculation);
    }


    /**
     * ///TFtdcOffsetFlagType是一个开平标志类型<br>
     * <br>
     * ///开仓<br>
     * #define THOST_FTDC_OF_Open '0'<br>
     * <br>
     * ///平仓<br>
     * #define THOST_FTDC_OF_Close '1'<br>
     * <br>
     * ///强平<br>
     * #define THOST_FTDC_OF_ForceClose '2'<br>
     * <br>
     * ///平今<br>
     * #define THOST_FTDC_OF_CloseToday '3'<br>
     * <br>
     * ///平昨<br>
     * #define THOST_FTDC_OF_CloseYesterday '4'<br>
     * <br>
     * ///强减<br>
     * #define THOST_FTDC_OF_ForceOff '5'<br>
     * <br>
     * ///本地强平<br>
     * #define THOST_FTDC_OF_LocalForceClose '6'<br>
     */
    interface FtdcOffsetFlag {
        /**
         * 组合开平标识, 开仓, [char]
         */
        char OPEN = THOST_FTDC_OF_Open;
        /**
         * 组合开平标识, 开仓, [String]
         */
        String OPEN_STR = String.valueOf(THOST_FTDC_OF_Open);
        /**
         * 组合开平标识, 平仓, [char]
         */
        char CLOSE = THOST_FTDC_OF_Close;
        /**
         * 组合开平标识, 平仓, [String]
         */
        String CLOSE_STR = String.valueOf(THOST_FTDC_OF_Close);
        /**
         * 组合开平标识, 平今, [char]
         */
        char CLOSE_TODAY = THOST_FTDC_OF_CloseToday;
        /**
         * 组合开平标识, 平今, [String]
         */
        String CLOSE_TODAY_STR = String.valueOf(THOST_FTDC_OF_CloseToday);
        /**
         * 组合开平标识, 平昨, [char]
         */
        char CLOSE_YESTERDAY = THOST_FTDC_OF_CloseYesterday;
        /**
         * 组合开平标识, 平昨, [String]
         */
        String CLOSE_YESTERDAY_STR = String.valueOf(THOST_FTDC_OF_CloseYesterday);
    }


    /**
     * ///TFtdcOrderPriceTypeType是一个报单价格条件类型 <br>
     * <br>
     * ///任意价<br>
     * #define THOST_FTDC_OPT_AnyPrice '1'<br>
     * <br>
     * ///限价<br>
     * #define THOST_FTDC_OPT_LimitPrice '2'<br>
     * <br>
     * ///最优价<br>
     * #define THOST_FTDC_OPT_BestPrice '3'<br>
     * <br>
     * ///最新价<br>
     * #define THOST_FTDC_OPT_LastPrice '4'<br>
     * <br>
     * ///最新价浮动上浮1个ticks<br>
     * #define THOST_FTDC_OPT_LastPricePlusOneTicks '5'<br>
     * <br>
     * ///最新价浮动上浮2个ticks<br>
     * #define THOST_FTDC_OPT_LastPricePlusTwoTicks '6'<br>
     * <br>
     * ///最新价浮动上浮3个ticks<br>
     * #define THOST_FTDC_OPT_LastPricePlusThreeTicks '7'<br>
     * <br>
     * ///卖一价<br>
     * #define THOST_FTDC_OPT_AskPrice1 '8'<br>
     * <br>
     * ///卖一价浮动上浮1个ticks<br>
     * #define THOST_FTDC_OPT_AskPrice1PlusOneTicks '9'<br>
     * <br>
     * ///卖一价浮动上浮2个ticks<br>
     * #define THOST_FTDC_OPT_AskPrice1PlusTwoTicks 'A'<br>
     * <br>
     * ///卖一价浮动上浮3个ticks<br>
     * #define THOST_FTDC_OPT_AskPrice1PlusThreeTicks 'B'<br>
     * <br>
     * ///买一价<br>
     * #define THOST_FTDC_OPT_BidPrice1 'C'<br>
     * <br>
     * ///买一价浮动上浮1个ticks<br>
     * #define THOST_FTDC_OPT_BidPrice1PlusOneTicks 'D'<br>
     * <br>
     * ///买一价浮动上浮2个ticks<br>
     * #define THOST_FTDC_OPT_BidPrice1PlusTwoTicks 'E'<br>
     * <br>
     * ///买一价浮动上浮3个ticks<br>
     * #define THOST_FTDC_OPT_BidPrice1PlusThreeTicks 'F'<br>
     * <br>
     * ///五档价<br>
     * #define THOST_FTDC_OPT_FiveLevelPrice 'G'<br>
     */
    interface FtdcOrderPrice {
        /**
         * 任意价
         */
        char ANY_PRICE = THOST_FTDC_OPT_AnyPrice;
        /**
         * 限价
         */
        char LIMIT_PRICE = THOST_FTDC_OPT_LimitPrice;
        /**
         * 最优价
         */
        char BEST_PRICE = THOST_FTDC_OPT_BestPrice;
        /**
         * 最新价
         */
        char LAST_PRICE = THOST_FTDC_OPT_LastPrice;
        /**
         * 卖一价
         */
        char ASK_PRICE1 = THOST_FTDC_OPT_AskPrice1;
        /**
         * 买一价
         */
        char BID_PRICE1 = THOST_FTDC_OPT_BidPrice1;
    }


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
    interface FtdcOrderStatus {
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
    }


    /**
     * ///TFtdcTimeConditionType是一个有效期类型类型<br>
     * <br>
     * ///立即完成，否则撤销<br>
     * #define THOST_FTDC_TC_IOC '1'<br>
     * <br>
     * ///本节有效<br>
     * #define THOST_FTDC_TC_GFS '2'<br>
     * <br>
     * ///当日有效<br>
     * #define THOST_FTDC_TC_GFD '3'<br>
     * <br>
     * ///指定日期前有效<br>
     * #define THOST_FTDC_TC_GTD '4'<br>
     * <br>
     * ///撤销前有效<br>
     * #define THOST_FTDC_TC_GTC '5'<br>
     * <br>
     * ///集合竞价有效<br>
     * #define THOST_FTDC_TC_GFA '6'<br>
     */
    interface FtdcTimeCondition {
        /**
         * 立即完成, 否则撤销
         */
        char IOC = THOST_FTDC_TC_IOC;
        /**
         * 当日有效
         */
        char GFD = THOST_FTDC_TC_GFD;
    }


    /**
     * ///TFtdcVolumeConditionType是一个成交量类型类型<br>
     * <br>
     * ///任何数量<br>
     * #define THOST_FTDC_VC_AV '1'<br>
     * <br>
     * ///最小数量<br>
     * #define THOST_FTDC_VC_MV '2'<br>
     * <br>
     * ///全部数量<br>
     * #define THOST_FTDC_VC_CV '3'<br>
     */
    interface FtdcVolumeCondition {
        /**
         * 任何数量
         */
        char AV = THOST_FTDC_VC_AV;
        /**
         * 最小数量
         */
        char MV = THOST_FTDC_VC_MV;
        /**
         * 全部数量
         */
        char CV = THOST_FTDC_VC_CV;
    }


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

    /**
     * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
     *
     * @param combOffsetFlag String
     * @return TrdAction
     */
    @Nonnull
    static TrdAction withOffsetFlag(@Nonnull String combOffsetFlag) {
        return withOffsetFlag(combOffsetFlag.charAt(0));
    }

    /**
     * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
     *
     * @param offsetFlag char
     * @return TrdAction
     */
    @Nonnull
    static TrdAction withOffsetFlag(char offsetFlag) {
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
    static TrdDirection withDirection(char direction) {
        return  // 买
                BUY == direction ? TrdDirection.LONG
                        // 卖
                        : SELL == direction ? TrdDirection.SHORT
                        // 未知
                        : TrdDirection.INVALID;
    }


}
