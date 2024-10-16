package io.rapid.core.event.inbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.rapid.core.event.enums.SubscribeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 行情订阅回报
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataSubscribeReport extends JsonBean implements Copyable<MarketDataSubscribeReport> {

    private SubscribeStatus status;
    private String instrumentCode;
    private String msg;

    @Override
    public void copyFrom(MarketDataSubscribeReport source) {
        // 复制订阅状态
        this.status = source.status;
        // 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 复制消息
        this.msg = source.getMsg();
    }

}