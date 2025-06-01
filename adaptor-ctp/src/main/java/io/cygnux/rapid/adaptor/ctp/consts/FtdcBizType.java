package io.cygnux.rapid.adaptor.ctp.consts;

/**
 * ///TFtdcBizTypeType是一个业务类型类型
 * <br>
 * ///期货<br>
 * #define THOST_FTDC_BZTP_Future '1'<br>
 * <br>
 * ///证券<br>
 * #define THOST_FTDC_BZTP_Stock '2'<br>
 */
public final class FtdcBizType {

    /**
     * 期货
     */
    public static final char FUTURE = '1';

    /**
     * 证券
     */
    public static final char STOCK = '2';

    private FtdcBizType() {
    }

}

