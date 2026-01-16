package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class StrategyParamRsp {

    /**
     * 用户ID
     */
    private int userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 策略名
     */
    private String strategyDisplayName;

    /**
     * 算法ID
     */
    private int algoId;

    /**
     * 算法名
     */
    private String algoDisplayName;

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

    /**
     * 备注
     */
    private String remark;


}