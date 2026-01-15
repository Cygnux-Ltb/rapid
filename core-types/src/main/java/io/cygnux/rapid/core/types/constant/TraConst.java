package io.cygnux.rapid.core.types.constant;

public enum TraConst {

    ;

    /**
     * 无效
     */
    public static final char INVALID = 'I';

    /* ********************* TRADE ACTION ********************* */
    /**
     * 开仓
     */
    public static final char ACTION_OPEN = 'O';
    /**
     * 平仓
     */
    public static final char ACTION_CLOSE = 'C';
    /**
     * 平今仓
     */
    public static final char ACTION_CLOSE_TODAY = 'T';
    /**
     * 平昨仓
     */
    public static final char ACTION_CLOSE_YESTERDAY = 'Y';

    /* ********************* TRADE DIRECTION ********************* */
    /**
     * 多
     */
    public static final char DIRECTION_LONG = 'L';
    /**
     * 空
     */
    public static final char DIRECTION_SHORT = 'S';

}