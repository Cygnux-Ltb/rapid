package io.rapid.adaptor.ctp.consts;

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
public final class FtdcVolumeCondition {

    /**
     * 任何数量
     */
    public static final char AV = '1';

    /**
     * 最小数量
     */
    public static final char MV = '2';

    /**
     * 全部数量
     */
    public static final char CV = '3';

    private FtdcVolumeCondition() {
    }

}
