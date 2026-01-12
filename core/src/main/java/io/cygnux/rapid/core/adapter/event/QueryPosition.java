package io.cygnux.rapid.core.adapter.event;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 查询账户持仓 (查询范围逐步提升)
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryPosition extends JsonBean implements Copyable<QueryPosition> {

    /**
     * Epoch Millisecond Unit
     */
    private long generateTime;
    /**
     * Epoch Millisecond Unit
     */
    private long sendTime;
    /**
     * 1.交易标的代码
     */
    private String instrumentCode;
    /**
     * 2.交易所代码
     */
    private String exchangeCode;
    /**
     * 3.账户ID
     */
    private int accountId;
    /**
     * 4.经纪商ID
     */
    private String brokerId;
    /**
     * 5.子账户ID
     */
    private int subAccountId;
    /**
     * 6.策略ID
     */
    private int strategyId;
    /**
     * 7.交易日
     */
    private int tradingDay;
    /**
     * 操作原因
     */
    private String reason;
    /**
     * 操作来源
     */
    private String source;

    @Override
    public void copyOf(QueryPosition source) {
        // 1. 复制生成时间 (Epoch Millisecond Unit)
        this.generateTime = source.generateTime;
        // 2. 复制发送时间 (Epoch Millisecond Unit)
        this.sendTime = source.sendTime;
        // 3. 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 4. 复制交易所代码
        this.exchangeCode = source.exchangeCode;
        // 5. 复制账户ID
        this.accountId = source.accountId;
        // 6. 复制经纪商ID
        this.brokerId = source.brokerId;
        // 7. 复制子账户ID
        this.subAccountId = source.subAccountId;
        // 8. 复制策略ID
        this.strategyId = source.strategyId;
        // 9. 复制交易日
        this.tradingDay = source.tradingDay;
        // 10. 复制操作原因
        this.reason = source.reason;
        // 11. 复制操作来源
        this.source = source.source;
    }

}