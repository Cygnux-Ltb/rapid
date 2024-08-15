package io.rapid.adaptor.ctp.consts;


import io.rapid.core.serializable.avro.enums.TrdDirection;

import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_D_Buy;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_D_Sell;

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
    char BUY = THOST_FTDC_D_Buy;

    /**
     * 卖
     */
    char SELL = THOST_FTDC_D_Sell;

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

