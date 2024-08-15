package io.rapid.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcRspInfoField;
import org.slf4j.Logger;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public final class FtdcRspInfoHandler {

    private FtdcRspInfoHandler() {
    }

    private static final Logger log = getLogger(FtdcRspInfoHandler.class);

    public static boolean nonError(CThostFtdcRspInfoField RspInfoField) {
        if (RspInfoField != null && RspInfoField.getErrorID() != 0) {
            log.error("RspInfoField Has Error, ErrorID == [{}], ErrorMsg == [{}]",
                    RspInfoField.getErrorID(), RspInfoField.getErrorMsg());
            return false;
        } else
            return true;
    }

}