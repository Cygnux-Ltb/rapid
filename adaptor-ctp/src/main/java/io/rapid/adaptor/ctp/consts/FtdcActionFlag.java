package io.rapid.adaptor.ctp.consts;

/**
 * ///TFtdcActionFlagType是一个操作标志类型<br>
 * <br>
 * ///删除<br>
 * #define THOST_FTDC_AF_Delete '0'<br>
 * <br>
 * ///修改<br>
 * #define THOST_FTDC_AF_Modify '3'<br>
 */
public interface FtdcActionFlag {

    /**
     * 删除
     */
    char DELETE = '0';

    /**
     * 修改
     */
    char MODIFY = '3';

}