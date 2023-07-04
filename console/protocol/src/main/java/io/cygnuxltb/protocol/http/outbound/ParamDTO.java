package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 配置信息表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ParamDTO {

    /**
     * 参数组
     */
    private String group;

    /**
     * 名称
     */
    private String name;

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
