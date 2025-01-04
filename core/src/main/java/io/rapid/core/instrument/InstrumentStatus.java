package io.rapid.core.instrument;

import io.rapid.core.event.enums.SubscribeStatus;
import io.rapid.core.event.enums.TradingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class InstrumentStatus {

    private SubscribeStatus subscribeStatus;
    private TradingStatus tradingStatus;

    InstrumentStatus() {
    }

}
