package io.cygnuxltb.protocol.http.response.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 指标
 * Account Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class IndicatorDTO {

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