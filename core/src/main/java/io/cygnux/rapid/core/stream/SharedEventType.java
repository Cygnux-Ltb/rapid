package io.cygnux.rapid.core.stream;

public enum SharedEventType {

    INVALID,

    FAST_MARKET_DATA,

    DEPTH_MARKET_DATA,

    INSTRUMENT_STATUS_REPORT,

    ADAPTOR_STATUS_REPORT,

    ORDER_REPORT,

    POSITIONS_REPORT,

    BALANCE_REPORT,

    STRATEGY_SIGNAL,

    SKIP,

}
