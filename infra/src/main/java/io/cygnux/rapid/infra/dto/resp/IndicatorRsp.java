package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 技术指标
 * Indicator Rsp
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class IndicatorRsp {

    /**
     * 指标ID
     */
    private int indicatorId;

    /**
     * 指标名称
     */
    private String indicatorName;

    /**
     * 指标类型
     */
    private String indicatorType;

    /**
     * 是否可用
     */
    private boolean available;

}