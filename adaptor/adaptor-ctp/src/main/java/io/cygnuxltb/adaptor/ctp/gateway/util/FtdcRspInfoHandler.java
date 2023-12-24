package io.cygnuxltb.adaptor.ctp.gateway.util;

import ctp.thostapi.CThostFtdcRspInfoField;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

public final class FtdcRspInfoHandler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspInfoHandler.class);

    public static boolean nonError(CThostFtdcRspInfoField RspInfo) {
        if (RspInfo != null && RspInfo.getErrorID() != 0) {
            log.error("RspInfo Has Error, ErrorID == [{}], ErrorMsg == [{}]",
                    RspInfo.getErrorID(), RspInfo.getErrorMsg());
            return false;
        } else
            return true;
    }

}