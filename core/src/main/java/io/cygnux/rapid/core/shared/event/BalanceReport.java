package io.cygnux.rapid.core.shared.event;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 账户可用余额回报
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceReport extends JsonBean implements Copyable<BalanceReport> {

    private long epochMillis;
    private String brokerId;
    private String investorId;
    private long available;
    private String currencyId;
    private String msg;

    @Override
    public void copyOf(BalanceReport source) {
        // 复制事件发生的时间 (Epoch Millisecond Unit)
        this.epochMillis = source.epochMillis;
        // 复制经纪商ID
        this.brokerId = source.brokerId;
        // 复制投资者ID
        this.investorId = source.investorId;
        // 复制可用余额
        this.available = source.available;
        // 复制货币ID
        this.currencyId = source.currencyId;
        // 复制消息
        this.msg = source.msg;
    }

}