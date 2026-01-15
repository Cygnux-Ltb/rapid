package io.cygnux.rapid.core.types.instrument;

import io.cygnux.rapid.core.types.adapter.enums.SubscribeStatus;
import io.cygnux.rapid.core.types.trade.enums.TradingStatus;
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
