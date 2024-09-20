package io.rapid.adaptor.ctp.consts;

import io.rapid.core.event.enums.TrdAction;

import javax.annotation.Nonnull;


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
public interface FtdcOffsetFlag {

    /**
     * 开仓 [char]
     */
    char Open = '0';

    /**
     * 开仓 [String]
     */
    String OpenStr = "0";

    /**
     * 平仓 [char]
     */
    char Close = '1';
    /**
     * 平仓 [String]
     */
    String CloseStr = "1";

    /**
     * 强平 [char]
     */
    char ForceClose = '2';
    /**
     * 强平 [String]
     */
    String ForceCloseStr = "2";

    /**
     * 平今 [char]
     */
    char CloseToday = '3';
    /**
     * 平今 [String]
     */
    String CloseTodayStr = "3";

    /**
     * 平昨 [char]
     */
    char CloseYesterday = '4';
    /**
     * 平昨 [String]
     */
    String CloseYesterdayStr = "4";

    /**
     * 强减 [char]
     */
    char ForceOff = '5';
    /**
     * 强减 [String]
     */
    String ForceOffStr = "5";

    /**
     * 本地强平 [char]
     */
    char LocalForceClose = '6';
    /**
     * 本地强平 [String]
     */
    String LocalForceCloseStr = "6";


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
        return switch (offsetFlag) {
            // 开仓
            case Open -> TrdAction.OPEN;
            // 平仓
            case Close -> TrdAction.CLOSE;
            // 平今
            case CloseToday -> TrdAction.CLOSE_TODAY;
            // 平昨
            case CloseYesterday -> TrdAction.CLOSE_YESTERDAY;
            // 未知
            default -> TrdAction.INVALID;
        };
    }

}
