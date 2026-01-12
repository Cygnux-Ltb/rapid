package io.cygnux.rapid.gateway.ctp;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.slf4j.Logger;

import static io.cygnux.rapid.gateway.ctp.consts.ErrorDictionary.getPrompt;

public final class FtdcFieldValidator {

    private FtdcFieldValidator() {
    }

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcFieldValidator.class);

    static boolean nonError(CThostFtdcRspInfoField Field, String Location) {
        if (Field == null || Field.getErrorID() == 0)
            return true;
        log.error("NOTE >>>> [{}], ErrorID==[{}], ErrorMsg==[{}], Prompt==[{}]",
                Location, Field.getErrorID(), Field.getErrorMsg(), getPrompt(Field.getErrorID()));
        return false;
    }

    static boolean nonError(CThostFtdcRspInfoField Field, String Location, int RequestID, boolean IsLast) {
        if (Field == null || Field.getErrorID() == 0)
            return true;
        log.error("NOTE >>>> [{}], ErrorID==[{}], ErrorMsg==[{}], Prompt==[{}], RequestID==[{}], IsLast==[{}]",
                Location, Field.getErrorID(), Field.getErrorMsg(), getPrompt(Field.getErrorID()),
                RequestID, IsLast);
        return false;
    }


    static boolean nonnull(Object FtdcField, String Location) {
        if (FtdcField != null)
            return true;
        log.error("NOTE >>>> [{}] return null field", Location);
        return false;
    }

    static boolean nonnull(Object FtdcField, String Location, int RequestID, boolean IsLast) {
        if (FtdcField != null)
            return true;
        log.error("NOTE >>>> [{}] return null field, RequestID==[{}], IsLast==[{}]",
                Location, RequestID, IsLast);
        return false;
    }

}