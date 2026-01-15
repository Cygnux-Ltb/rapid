package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 盈亏结算
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class PnlSettlementRsp {

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 交易标的代码 [*]
     */
    private String instrumentCode;

    /**
     * 交易日 [*]
     */
    private int tradingDay;

    /**
     * 仓位
     */
    private int position;

    /**
     * 盈亏
     */
    private double totalPnl;

    /**
     * 净盈亏
     */
    private double netPnl;

    /**
     * 交易成本
     */
    private double tradingCost;

    /**
     * 风险暴露
     */
    private double exposure;

    /**
     * 认证状态
     */
    private int approved;


}
