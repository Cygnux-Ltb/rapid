package io.cygnux.gateway.ctp.consts;


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
public final class FtdcContingentCondition {

    /**
     * 立即
     */
    public static final char IMMEDIATELY = '1';

    /**
     * 止损
     */
    public static final char TOUCH = '2';

    /**
     * 止赢
     */
    public static final char TOUCH_PROFIT = '3';

    /**
     * 预埋单
     */
    public static final char PARKED_ORDER = '4';

    /**
     * 最新价大于条件价
     */
    public static final char LAST_PRICE_GREATER_THAN_STOP_PRICE = '5';

    /**
     * 最新价大于等于条件价
     */
    public static final char LAST_PRICE_GREATER_EQUAL_STOP_PRICE = '6';

    /**
     * 最新价小于条件价
     */
    public static final char LAST_PRICE_LESSER_THAN_STOP_PRICE = '7';

    /**
     * 最新价小于等于条件价
     */
    public static final char LAST_PRICE_LESSER_EQUAL_STOP_PRICE = '8';

    /**
     * 卖一价大于条件价
     */
    public static final char ASK_PRICE_GREATER_THAN_STOP_PRICE = '9';

    /**
     * 卖一价大于等于条件价
     */
    public static final char ASK_PRICE_GREATER_EQUAL_STOP_PRICE = 'A';

    /**
     * 卖一价小于条件价
     */
    public static final char ASK_PRICE_LESSER_THAN_STOP_PRICE = 'B';

    /**
     * 卖一价小于等于条件价
     */
    public static final char ASK_PRICE_LESSER_EQUAL_STOP_PRICE = 'C';

    /**
     * 买一价大于条件价
     */
    public static final char BID_PRICE_GREATER_THAN_STOP_PRICE = 'D';

    /**
     * 买一价大于等于条件价
     */
    public static final char BID_PRICE_GREATER_EQUAL_STOP_PRICE = 'E';

    /**
     * 买一价小于条件价
     */
    public static final char BID_PRICE_LESSER_THAN_STOP_PRICE = 'F';

    /**
     * 买一价小于等于条件价
     */
    public static final char BID_PRICE_LESSER_EQUAL_STOP_PRICE = 'H';

    private FtdcContingentCondition() {
    }

}
