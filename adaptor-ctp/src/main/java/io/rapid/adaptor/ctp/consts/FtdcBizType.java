package io.rapid.adaptor.ctp.consts;

/**
 * ///TFtdcBizTypeType是一个业务类型类型
 * <br>
 * ///期货<br>
 * #define THOST_FTDC_BZTP_Future '1'<br>
 * <br>
 * ///证券<br>
 * #define THOST_FTDC_BZTP_Stock '2'<br>
 */
public interface FtdcBizType {

    /**
     * 期货
     */
    char Future = '1';

    /**
     * 证券
     */
    char Stock = '2';

}
