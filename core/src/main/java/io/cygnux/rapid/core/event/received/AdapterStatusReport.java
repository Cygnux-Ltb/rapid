package io.cygnux.rapid.core.event.received;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.cygnux.rapid.core.event.enums.AdapterType;
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
public class AdapterStatusReport extends JsonBean implements Copyable<AdapterStatusReport> {

    private long epochMillis;
    private int accountId;
    private String adaptorId;
    private AdapterType adapterType;
    private boolean available;
    private String msg;

    @Override
    public void copyOf(AdapterStatusReport source) {
        // 复制事件发生的时间 (Epoch Millisecond Unit)
        this.epochMillis = source.epochMillis;
        // 复制账户ID
        this.accountId = source.accountId;
        // 复制适配器ID
        this.adaptorId = source.adaptorId;
        // 复制通道类型
        this.adapterType = source.adapterType;
        // 复制可用状态
        this.available = source.available;
        // 复制消息
        this.msg = source.msg;
    }

}