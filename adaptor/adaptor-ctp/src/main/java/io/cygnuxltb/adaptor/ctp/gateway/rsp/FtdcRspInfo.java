package io.cygnuxltb.adaptor.ctp.gateway.rsp;

import ctp.thostapi.CThostFtdcRspInfoField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FtdcRspInfo {

    // 错误代码
    private int ErrorID;

    // 错误信息
    private String ErrorMsg;

    // 请求码
    private int RequestID;

    public FtdcRspInfo copy(CThostFtdcRspInfoField field, int RequestID) {
        return this
                .setErrorID(field.getErrorID())
                .setErrorMsg(field.getErrorMsg())
                .setRequestID(RequestID);
    }
}
