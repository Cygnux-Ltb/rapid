package io.rapid.adaptor.ctp.gateway.util;

import ctp.thostapi.CThostFtdcRspInfoField;
import org.slf4j.Logger;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public final class FtdcRspInfoHandler {

    private static final Logger log = getLogger(FtdcRspInfoHandler.class);

    public static boolean nonError(CThostFtdcRspInfoField RspInfo) {
        if (RspInfo != null && RspInfo.getErrorID() != 0) {
            log.error("RspInfo Has Error, ErrorID == [{}], ErrorMsg == [{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
            return false;
        } else
            return true;
    }

}