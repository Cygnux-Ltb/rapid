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
public final class AdapterRsp {

    private int adapterId;

    /**
     * Adapter Name
     */
    private String adapterName;

    /**
     * Adapter Type
     */
    private String adapterType;

    /**
     * Remark
     */
    private String remark;

    /**
     * Enabled
     */
    private boolean enabled;

}