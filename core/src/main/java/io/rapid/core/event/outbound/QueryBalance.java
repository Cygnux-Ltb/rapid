package io.rapid.core.event.outbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 查询账户余额 (查询范围逐步提升)
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryBalance extends JsonBean implements Copyable<QueryBalance> {

    /**
     * Epoch Millisecond Unit
     */
    private long generateTime;
    /**
     * Epoch Millisecond Unit
     */
    private long sendTime;
    /**
     * 1.账户ID
     */
    private int accountId;
    /**
     * 2.经纪商ID
     */
    private String brokerId;
    /**
     * 3.子账户ID
     */
    private int subAccountId;
    /**
     * 操作原因
     */
    private String reason;
    /**
     * 操作来源
     */
    private String source;

    @Override
    public void copyOf(QueryBalance source) {
        // 1. 复制生成时间 (Epoch Millisecond Unit)
        this.generateTime = source.generateTime;
        // 2. 复制发送时间 (Epoch Millisecond Unit)
        this.sendTime = source.sendTime;
        // 3. 复制账户ID
        this.accountId = source.accountId;
        // 4. 复制经纪商ID
        this.brokerId = source.brokerId;
        // 5. 复制子账户ID
        this.subAccountId = source.subAccountId;
        // 6. 复制操作原因
        this.reason = source.reason;
        // 7. 复制操作来源
        this.source = source.source;
    }

}