package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Adapter参数
 */
@Getter
@Setter
@Accessors(chain = true)
public final class AdapterParamRsp {

    /**
     * Adapter类型
     */
    private int adapterId;

    /**
     * Adapter显示名
     */
    private String adapterDisplayName;

    /**
     * 参数组
     */
    private String paramGroup;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 备注
     */
    private String remark;


}