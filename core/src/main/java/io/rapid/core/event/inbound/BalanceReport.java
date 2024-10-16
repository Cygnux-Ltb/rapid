package io.rapid.core.event.inbound;

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

    @Override
    public void copyFrom(BalanceReport source) {
        // 复制事件发生的时间 (Epoch Millisecond Unit)
        this.epochMillis = source.getEpochMillis();
        // 复制经纪商ID
        this.brokerId = source.getBrokerId();
        // 复制投资者ID
        this.investorId = source.getInvestorId();
        // 复制可用余额
        this.available = source.getAvailable();
        // 复制货币ID
        this.currencyId = source.getCurrencyId();
    }

}