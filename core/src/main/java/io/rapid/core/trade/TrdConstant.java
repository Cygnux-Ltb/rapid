package io.rapid.core.order;

public interface TrdConstant {

    /**
     * 无效
     */
    char INVALID = 'I';

    /* ********************* TRADE ACTION ********************* */
    /**
     * 开仓
     */
    char ACTION_OPEN = 'O';
    /**
     * 平仓
     */
    char ACTION_CLOSE = 'C';
    /**
     * 平今仓
     */
    char ACTION_CLOSE_TODAY = 'T';
    /**
     * 平昨仓
     */
    char ACTION_CLOSE_YESTERDAY = 'Y';

    /* ********************* TRADE DIRECTION ********************* */
    /**
     * 多
     */
    char DIRECTION_LONG = 'L';
    /**
     * 空
     */
    char DIRECTION_SHORT = 'S';

}