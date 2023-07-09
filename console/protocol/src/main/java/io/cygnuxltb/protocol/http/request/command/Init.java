package io.cygnuxltb.protocol.http.request.command;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Init {

    private int sysId;

    private int tradingDay;

}
