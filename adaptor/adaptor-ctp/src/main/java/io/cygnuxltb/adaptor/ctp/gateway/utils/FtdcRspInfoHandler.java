package io.cygnuxltb.adaptor.ctp.gateway.utils;

import ctp.thostapi.CThostFtdcRspInfoField;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

public final class FtdcRspInfoHandler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspInfoHandler.class);

    public static boolean nonError(String funcName, CThostFtdcRspInfoField field) {
        if (field != null && field.getErrorID() != 0) {
            log.error("Error func -> {}, ErrorID == [{}], ErrorMsg == [{}]",
                    funcName, field.getErrorID(), field.getErrorMsg());
            return false;
        } else
            return true;
    }

}