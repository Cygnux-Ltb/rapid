package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 策略表
 * Strategy Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class StrategyRsp {

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 策略名称
     */
    private String strategyName;

    /**
     * 策略类型
     */
    private String strategyType;

    /**
     * 策略相关信息
     */
    private String remark;

    /**
     * 可交易标识
     */
    private boolean enabled;


}
