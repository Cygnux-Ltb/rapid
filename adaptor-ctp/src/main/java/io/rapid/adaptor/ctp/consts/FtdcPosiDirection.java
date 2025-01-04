package io.rapid.adaptor.ctp.consts;

import io.rapid.core.event.enums.TrdDirection;

/**
 * TFtdcPosiDirectionType是一个持仓多空方向类型 <br>
 * ///净 <br>
 * #define THOST_FTDC_PD_Net '1' <br>
 * ///多头 <br>
 * #define THOST_FTDC_PD_Long '2' <br>
 * ///空头 <br>
 * #define THOST_FTDC_PD_Short '3' <br>
 */
public interface FtdcPosiDirection {

    /**
     * 净
     */
    char NET = '0';

    /**
     * 多头
     */
    char LONG = '2';

    /**
     * 空头
     */
    char SHORT = '3';

    /**
     * @param ftdcPosiDirection int
     * @return TrdDirection
     */
    static TrdDirection getTrdDirection(int ftdcPosiDirection) {
        return switch (ftdcPosiDirection) {
            // 多头
            case LONG -> TrdDirection.LONG;
            // 空头
            case SHORT -> TrdDirection.SHORT;
            // 未知
            default -> TrdDirection.INVALID;
        };
    }

}
