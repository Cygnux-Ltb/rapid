package io.rapid.adaptor.ctp.consts;

import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_BZTP_Future;
import static ctp.thostapi.thosttraderapiConstants.THOST_FTDC_BZTP_Stock;

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

    char Future = THOST_FTDC_BZTP_Future;

    char Stock = THOST_FTDC_BZTP_Stock;

}
