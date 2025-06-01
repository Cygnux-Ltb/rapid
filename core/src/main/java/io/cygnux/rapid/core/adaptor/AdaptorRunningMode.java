package io.cygnux.rapid.core.adaptor;

public enum AdaptorRunningMode {

    FULL,

    MARKET_DATA,

    TRADE,

    ;

    public static AdaptorRunningMode valueOfName(String name) {
        for (AdaptorRunningMode mode : values())
            if (mode.name().equalsIgnoreCase(name))
                return mode;
        throw new IllegalArgumentException("Unknown AdaptorRunningMode: " + name);
    }

}
