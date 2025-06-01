package io.cygnux.rapid.adaptor.ctp.consts;

/**
 * ///TFtdcActionFlagType是一个操作标志类型<br>
 * <br>
 * ///删除<br>
 * #define THOST_FTDC_AF_Delete '0'<br>
 * <br>
 * ///修改<br>
 * #define THOST_FTDC_AF_Modify '3'<br>
 */
public final class FtdcActionFlag {

    /**
     * 删除
     */
    public static final char DELETE = '0';

    /**
     * 修改
     */
    public static final char MODIFY = '3';

    private FtdcActionFlag() {
    }

}
