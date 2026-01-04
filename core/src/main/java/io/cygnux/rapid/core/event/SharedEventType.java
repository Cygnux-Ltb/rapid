package io.cygnux.rapid.core.event;

public enum SharedEventType {

    INVALID,

    CONTROL_COMMAND,

    FAST_MARKET_DATA,

    DEPTH_MARKET_DATA,

    INSTRUMENT_STATUS_REPORT,

    ADAPTER_STATUS_REPORT,

    ORDER_REPORT,

    POSITIONS_REPORT,

    BALANCE_REPORT,

    STRATEGY_SIGNALS,

    SKIP,

}
