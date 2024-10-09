package io.rapid.core.event.inbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.rapid.core.event.enums.AdaptorStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 适配器回报
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdaptorReport extends JsonBean implements Copyable<AdaptorReport> {

    private long epochMillis;
    private int accountId;
    private String adaptorId;
    private AdaptorStatus status;

    @Override
    public void copyFrom(AdaptorReport source) {
        // 复制事件发生的时间 (Epoch Millisecond Unit)
        this.epochMillis = source.getEpochMillis();
        // 复制账户ID
        this.accountId = source.getAccountId();
        // 复制适配器ID
        this.adaptorId = source.getAdaptorId();
        // 复制适配器状态
        this.status = source.getStatus();
    }

}