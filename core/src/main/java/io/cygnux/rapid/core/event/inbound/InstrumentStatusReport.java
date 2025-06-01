package io.cygnux.rapid.core.event.inbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.cygnux.rapid.core.event.enums.SubscribeStatus;
import io.cygnux.rapid.core.event.enums.TradingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易标的状态回报
 */
@Getter
@Setter
@Accessors(chain = true)
public class InstrumentStatusReport extends JsonBean implements Copyable<InstrumentStatusReport> {

    private long epochMillis;
    private SubscribeStatus subscribeStatus = SubscribeStatus.NOT_PROVIDED;
    private TradingStatus tradingStatus = TradingStatus.NOT_PROVIDED;
    private String symbolCode;
    private String instrumentCode;
    private String actualDate;
    private String updateTime;
    private String msg;

    @Override
    public void copyOf(InstrumentStatusReport source) {
        // 复制行情订阅状态
        this.subscribeStatus = source.subscribeStatus;
        // 复制交易状态
        this.tradingStatus = source.tradingStatus;
        // 复制交易产品代码
        this.symbolCode = source.symbolCode;
        // 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 复制实际日期
        this.actualDate = source.actualDate;
        // 复制更新时间
        this.updateTime = source.updateTime;
        // 复制消息
        this.msg = source.msg;
    }

}