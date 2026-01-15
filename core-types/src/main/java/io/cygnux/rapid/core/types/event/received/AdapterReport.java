package io.cygnux.rapid.core.types.event.received;

import io.cygnux.rapid.core.types.adapter.enums.AdapterType;
import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
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
public class AdapterReport extends JsonBean implements Copyable<AdapterReport> {

    private long epochMillis;
    private int accountId;
    private String adapterId;
    private AdapterType adapterType = AdapterType.INVALID;
    private boolean available;
    private String msg;

    @Override
    public void copyOf(AdapterReport source) {
        // 复制事件发生的时间 (Epoch Millisecond Unit)
        this.epochMillis = source.epochMillis;
        // 复制账户ID
        this.accountId = source.accountId;
        // 复制适配器ID
        this.adapterId = source.adapterId;
        // 复制通道类型
        this.adapterType = source.adapterType;
        // 复制可用状态
        this.available = source.available;
        // 复制消息
        this.msg = source.msg;
    }

}