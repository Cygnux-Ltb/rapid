package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 配置参数信息
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ParamRsp {

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
