package io.cygnuxltb.adaptor.ctp.gateway.rsp.state;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FtdcTraderConnect {
    
    // 可用状态
    private boolean available;
    // 前置机ID
    private int frontId;
    // 会话ID
    private int sessionId;

}
