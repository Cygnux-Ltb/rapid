package io.cygnux.rapid.core.types.adapter.event;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 撤单
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrder extends JsonBean implements Copyable<CancelOrder> {

    /**
     * Epoch Microsecond Unit
     */
    private long generateTime;
    /**
     * Epoch Microsecond Unit
     */
    private long sendTime;
    /**
     * 订单ID
     */
    private long ordSysId;
    /**
     * 交易标的ID
     */
    private int instrumentId;
    /**
     * 交易标的代码
     */
    private String instrumentCode;
    /**
     * 交易所代码
     */
    private String exchangeCode;
    /**
     * 账户ID
     */
    private int accountId;
    /**
     * 经纪商ID
     */
    private String brokerId;
    /**
     * 子账户ID
     */
    private int subAccountId;
    /**
     * 策略ID
     */
    private int strategyId;
    /**
     * 延迟时间
     */
    private long delayMillis;
    /**
     * 操作ID
     */
    private String operatorId;
    /**
     * 备注
     */
    private String remark;

    @Override
    public void copyOf(CancelOrder source) {
        // 1. 复制生成时间 (Epoch Microsecond Unit)
        this.generateTime = source.generateTime;
        // 2. 复制发送时间 (Epoch Microsecond Unit)
        this.sendTime = source.sendTime;
        // 3. 复制订单ID
        this.ordSysId = source.ordSysId;
        // 4. 复制交易标的ID
        this.instrumentId = source.instrumentId;
        // 5. 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 6. 复制交易所代码
        this.exchangeCode = source.exchangeCode;
        // 7. 复制账户ID
        this.accountId = source.accountId;
        // 8. 复制经纪商ID
        this.brokerId = source.brokerId;
        // 9. 复制子账户ID
        this.subAccountId = source.subAccountId;
        // 10. 复制策略ID
        this.strategyId = source.strategyId;
        // 11. 复制延迟时间 (毫秒)
        this.delayMillis = source.delayMillis;
        // 12. 复制操作ID
        this.operatorId = source.operatorId;
        // 13. 复制备注
        this.remark = source.remark;
    }

}