package io.cygnux.rapid.core.adapter;

import io.mercury.serialization.json.JsonWriter;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Adaptor状态
 */
@ThreadSafe
public final class AdapterStatus {

    private final AtomicBoolean isMarketDataEnabled = new AtomicBoolean(false);

    private final AtomicBoolean isTraderEnabled = new AtomicBoolean(false);

    public AdapterStatus setMarketDataEnabled(boolean enabled) {
        isMarketDataEnabled.set(enabled);
        return this;
    }

    public AdapterStatus setTraderEnabled(boolean enabled) {
        isTraderEnabled.set(enabled);
        return this;
    }

    public boolean isMarketDataEnabled() {
        return isMarketDataEnabled.get();
    }

    public boolean isTraderEnabled() {
        return isTraderEnabled.get();
    }

    @Override
    public String toString() {
        var map = new HashMap<String, Boolean>();
        map.put("isMarketDataEnabled", isMarketDataEnabled.get());
        map.put("isTraderEnabled", isTraderEnabled.get());
        return JsonWriter.toJson(map);
    }

    public static void main(String[] args) {
        System.out.println(new AdapterStatus().setMarketDataEnabled(true).setTraderEnabled(true));
    }

}
