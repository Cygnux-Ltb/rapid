package io.cygnux.rapid.adaptor.ctp.consts;

import io.cygnux.rapid.core.stream.enums.TrdAction;

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
public final class FtdcOffsetFlag {

    /**
     * 开仓 [char]
     */
    public static final char OPEN = '0';

    /**
     * 开仓 [String]
     */
    public static final String OPEN_STR = String.valueOf(OPEN);

    /**
     * 平仓 [char]
     */
    public static final char CLOSE = '1';
    /**
     * 平仓 [String]
     */
    public static final String CLOSE_STR = String.valueOf(CLOSE);

    /**
     * 强平 [char]
     */
    public static final char FORCE_CLOSE = '2';
    /**
     * 强平 [String]
     */
    public static final String FORCE_CLOSE_STR = String.valueOf(FORCE_CLOSE);

    /**
     * 平今 [char]
     */
    public static final char CLOSE_TODAY = '3';
    /**
     * 平今 [String]
     */
    public static final String CLOSE_TODAY_STR = String.valueOf(CLOSE_TODAY);

    /**
     * 平昨 [char]
     */
    public static final char CLOSE_YESTERDAY = '4';
    /**
     * 平昨 [String]
     */
    public static final String CLOSE_YESTERDAY_STR = String.valueOf(CLOSE_YESTERDAY);

    /**
     * 强减 [char]
     */
    public static final char FORCE_OFF = '5';
    /**
     * 强减 [String]
     */
    public static final String FORCE_OFF_STR = String.valueOf(FORCE_OFF);

    /**
     * 本地强平 [char]
     */
    public static final char LOCAL_FORCE_CLOSE = '6';
    /**
     * 本地强平 [String]
     */
    public static final String LOCAL_FORCE_CLOSE_STR = String.valueOf(LOCAL_FORCE_CLOSE);

    /**
     * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
     *
     * @param combOffsetFlag String
     * @return TrdAction
     */
    @Nonnull
    public static TrdAction getTrdAction(@Nonnull String combOffsetFlag) {
        return getTrdAction(combOffsetFlag.charAt(0));
    }

    /**
     * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
     *
     * @param offsetFlag char
     * @return TrdAction
     */
    @Nonnull
    public static TrdAction getTrdAction(int offsetFlag) {
        return switch (offsetFlag) {
            // 开仓
            case OPEN -> TrdAction.OPEN;
            // 平仓
            case CLOSE -> TrdAction.CLOSE;
            // 平今
            case CLOSE_TODAY -> TrdAction.CLOSE_TODAY;
            // 平昨
            case CLOSE_YESTERDAY -> TrdAction.CLOSE_YESTERDAY;
            // 未知
            default -> TrdAction.INVALID;
        };
    }

    private FtdcOffsetFlag() {
    }
    
}
