package io.rapid.adaptor.ctp.consts;


import io.rapid.core.event.enums.TrdDirection;

/**
 * TFtdcDirectionType是一个买卖方向类型 <br>
 * <br>
 * ///买<br>
 * #define THOST_FTDC_D_Buy '0'<br>
 * <br>
 * ///卖<br>
 * #define THOST_FTDC_D_Sell '1'<br>
 */
public interface FtdcDirection {

    /**
     * 买
     */
    char BUY = '0';

    /**
     * 卖
     */
    char SELL = '1';

    /**
     * 根据<b>[FTDC返回]</b>买卖方向类型, 映射<b>[系统自定义]</b>买卖方向类型类型
     *
     * @param ftdcDirection char
     * @return TrdDirection
     */
    static TrdDirection getTrdDirection(int ftdcDirection) {
        return switch (ftdcDirection) {
            // 买
            case BUY -> TrdDirection.LONG;
            // 卖
            case SELL -> TrdDirection.SHORT;
            // 未知
            default -> TrdDirection.INVALID;
        };
    }

}

