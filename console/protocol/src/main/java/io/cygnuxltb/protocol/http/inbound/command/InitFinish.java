package io.cygnuxltb.protocol.http.inbound.command;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class InitFinish {
    private int sysId;
}
