package io.rapid.adaptor.ctp.gateway;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.consts.ErrorDictionary;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.slf4j.Logger;

public final class FtdcFieldValidator {

    private FtdcFieldValidator() {
    }

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcFieldValidator.class);

    static boolean nonError(CThostFtdcRspInfoField Field, String SpiFuncName) {
        if (Field == null || Field.getErrorID() == 0)
            return true;
        log.error("SPI Function >>>> [{}], ErrorID==[{}], ErrorMsg==[{}], Prompt==[{}]",
                SpiFuncName, Field.getErrorID(), Field.getErrorMsg(), ErrorDictionary.getPrompt(Field.getErrorID()));
        return false;
    }

    static boolean nonError(CThostFtdcRspInfoField Field, String SpiFuncName, int RequestID, boolean IsLast) {
        if (Field == null || Field.getErrorID() == 0)
            return true;
        log.error("SPI Function >>>> [{}], ErrorID==[{}], ErrorMsg==[{}], Prompt==[{}], RequestID==[{}], IsLast==[{}]",
                SpiFuncName, Field.getErrorID(), Field.getErrorMsg(), ErrorDictionary.getPrompt(Field.getErrorID()),
                RequestID, IsLast);
        return false;
    }


    static boolean nonnull(Object FtdcField, String SpiFuncName) {
        if (FtdcField != null)
            return true;
        log.error("SPI Function >>>> [{}] return null ftdc fields", SpiFuncName);
        return false;
    }

    static boolean nonnull(Object FtdcField, String SpiFuncName, int RequestID, boolean IsLast) {
        if (FtdcField != null)
            return true;
        log.error("SPI Function >>>> [{}] return null ftdc fields, RequestID==[{}], IsLast==[{}]",
                SpiFuncName, RequestID, IsLast);
        return false;
    }

}