package io.cygnux.rapid.core.event.inbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.cygnux.rapid.core.event.enums.AdaptorType;
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
    private AdaptorType adaptorType;
    private boolean isAvailable;
    private String msg;

    @Override
    public void copyOf(AdaptorReport source) {
        // 复制事件发生的时间 (Epoch Millisecond Unit)
        this.epochMillis = source.epochMillis;
        // 复制账户ID
        this.accountId = source.accountId;
        // 复制适配器ID
        this.adaptorId = source.adaptorId;
        // 复制通道类型
        this.adaptorType = source.adaptorType;
        // 复制可用状态
        this.isAvailable = source.isAvailable;
        // 复制消息
        this.msg = source.msg;
    }

}