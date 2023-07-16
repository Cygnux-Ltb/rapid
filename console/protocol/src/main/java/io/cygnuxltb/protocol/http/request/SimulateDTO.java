package io.cygnuxltb.protocol.http.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SimulateDTO {

    /**
     *
     */
    public int userId;

    /**
     *
     */
    public int strategyId;

    /**
     *
     */
    public int tradingDay;

}
