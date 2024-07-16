package io.rapid.adaptor.ctp.gateway.event.received;

import ctp.thostapi.CThostFtdcRspInfoField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FtdcRspInfo {

    /// 错误代码
    private int ErrorID;

    /// 错误信息
    private String ErrorMsg;

    /**
     * @param RspInfoField CThostFtdcRspInfoField
     * @return FtdcRspInfo
     */
    public FtdcRspInfo copy(CThostFtdcRspInfoField RspInfoField) {
        return this
                .setErrorID(RspInfoField.getErrorID())
                .setErrorMsg(RspInfoField.getErrorMsg());
    }
    
}
