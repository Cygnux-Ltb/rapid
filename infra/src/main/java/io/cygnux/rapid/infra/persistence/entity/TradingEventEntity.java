package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ADAPTOR_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_SIMULATION_DATE;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_STRATEGY_ID;
import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_TRADING_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ADAPTER_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.STRATEGY_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.TRADING_ID;

/**
 * 交易表
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_trading_event")
public final class TradingEventEntity {

    /**
     * 交易 ID
     */
    @Id
    @Min(MIN_TRADING_ID)
    @Column(name = TRADING_ID)
    private long tradingId;

    /**
     * Strategy ID
     */
    @Min(MIN_STRATEGY_ID)
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * Adaptor ID
     */
    @Min(MIN_ADAPTOR_ID)
    @Column(name = ADAPTER_ID)
    private int adaptorId;

    /**
     * 开始时间 [*]
     */
    @Min(MIN_SIMULATION_DATE)
    @Column(name = "START_TIME")
    private int startTime;

    /**
     * 结束时间 [*]
     */
    @Min(MIN_SIMULATION_DATE)
    @Column(name = "END_TIME")
    private int endTime;

    /**
     * 当前状态
     */
    @Column(name = "STATUS")
    private int status;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
