package io.cygnuxltb.protocol.http.response.status;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StrategyStatus {

    private int strategyId;

    private int status;

}
