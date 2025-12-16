package io.cygnux.rapid.core.adapter;

public enum AdapterRunningMode {

    FULL,

    MARKET_DATA,

    TRADE,

    ;

    public static AdapterRunningMode valueOfName(String name) {
        for (AdapterRunningMode mode : values())
            if (mode.name().equalsIgnoreCase(name))
                return mode;
        throw new IllegalArgumentException("Unknown AdaptorRunningMode: " + name);
    }

}
