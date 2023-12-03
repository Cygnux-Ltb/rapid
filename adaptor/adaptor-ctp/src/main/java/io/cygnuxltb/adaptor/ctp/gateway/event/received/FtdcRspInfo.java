package io.cygnuxltb.adaptor.ctp.gateway.event.received;

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

    public FtdcRspInfo load(CThostFtdcRspInfoField field) {
        return this
                .setErrorID(field.getErrorID())
                .setErrorMsg(field.getErrorMsg());
    }
}
