package io.cygnuxltb.protocol.http.inbound.command;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SysInit {
    private int sysId;
    private int tradingDay;
}
