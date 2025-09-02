package io.cygnux.rapid.core.strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StrategyParam {

    /**
     * 算法ID
     */
    private int algoId;

    /**
     * 算法名
     */
    private String algoName;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数值
     */
    private String paramValue;

}
