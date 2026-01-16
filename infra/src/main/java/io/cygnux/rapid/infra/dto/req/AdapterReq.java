package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class AdapterReq {

    /**
     * Adaptor ID
     */
    private int adapterId;

    /**
     * Adaptor Name
     */
    private String adapterName;

    /**
     * Adaptor Type
     */
    private String adapterType;

    /**
     * Remark
     */
    private String remark;

}
