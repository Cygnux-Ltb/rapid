package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class StrategyParamReq {

    /**
     *
     */
    private int userid;

    /**
     *
     */
    private int strategyId;

    /**
     *
     */
    private int algoId;

    /**
     *
     */
    private String paramName;

    /**
     *
     */
    private String paramType;

    /**
     *
     */
    private String paramValue;

    /**
     *
     */
    private String remark;

}
