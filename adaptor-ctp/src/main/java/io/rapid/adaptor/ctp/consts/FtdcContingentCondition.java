package io.rapid.adaptor.ctp.consts;


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
public interface FtdcContingentCondition {

    /**
     * 立即
     */
    char Immediately = '1';

    /**
     * 止损
     */
    char Touch = '2';

    /**
     * 止赢
     */
    char TouchProfit = '3';

    /**
     * 预埋单
     */
    char ParkedOrder = '4';

    /**
     * 最新价大于条件价
     */
    char LastPriceGreaterThanStopPrice = '5';

    /**
     * 最新价大于等于条件价
     */
    char LastPriceGreaterEqualStopPrice = '6';

    /**
     * 最新价小于条件价
     */
    char LastPriceLesserThanStopPrice = '7';

    /**
     * 最新价小于等于条件价
     */
    char LastPriceLesserEqualStopPrice = '8';

    /**
     * 卖一价大于条件价
     */
    char AskPriceGreaterThanStopPrice = '9';

    /**
     * 卖一价大于等于条件价
     */
    char AskPriceGreaterEqualStopPrice = 'A';

    /**
     * 卖一价小于条件价
     */
    char AskPriceLesserThanStopPrice = 'B';

    /**
     * 卖一价小于等于条件价
     */
    char AskPriceLesserEqualStopPrice = 'C';

    /**
     * 买一价大于条件价
     */
    char BidPriceGreaterThanStopPrice = 'D';

    /**
     * 买一价大于等于条件价
     */
    char BidPriceGreaterEqualStopPrice = 'E';

    /**
     * 买一价小于条件价
     */
    char BidPriceLesserThanStopPrice = 'F';

    /**
     * 买一价小于等于条件价
     */
    char BidPriceLesserEqualStopPrice = 'H';

}