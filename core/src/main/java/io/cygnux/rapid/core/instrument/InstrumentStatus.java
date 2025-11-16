package io.cygnux.rapid.core.instrument;

import io.cygnux.rapid.core.shared.enums.SubscribeStatus;
import io.cygnux.rapid.core.shared.enums.TradingStatus;
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
