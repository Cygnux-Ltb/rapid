package io.cygnuxltb.adaptor.ctp.gateway.rsp.state;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FtdcMdConnect {

    // 可用状态
    private boolean available;

}
