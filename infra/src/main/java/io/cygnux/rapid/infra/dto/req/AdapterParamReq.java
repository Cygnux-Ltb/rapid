package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class AdapterParamReq {

    /**
     * AdaptorId
     */
    private int adapterId;

    /**
     * ParamGroup
     */
    private String paramGroup;

    /**
     * ParamName
     */
    private String paramName;

    /**
     * ParamValue
     */
    private String paramValue;

    /**
     * Remark
     */
    private String remark;

}
