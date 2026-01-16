package io.cygnux.rapid.infra.dto.wrap;

import io.mercury.common.codec.Envelope;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.collector.Collectors2;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public enum OutboxTitle implements Envelope {

    HEARTBEAT(1,1),

    INIT_CONFIG(2,1),

    BARS(0,1),

    SYS_INFO(0,1),

    SYS_STRATEGY(0,1),

    STRATEGY(0,1),

    STRATEGY_PARAM(0,1),

    STRATEGY_SYMBOL(0,1),

    STRATEGY_INSTRUMENT_PNL_DAILY(0,1),

    SYMBOL_INFO(0,1),

    SYMBOL_TRADING_FEE(0,1),

    SYMBOL_TRADING_PERIOD(0,1),

    TRADABLE_INSTRUMENT(0,1),

    INIT_FINISH(0,1),

    STRATEGY_SWITCH(0,1),

    UPDATE_STRATEGY_PARAMS(0,1),

    END_BARS(0,1),

    UPDATE_STRATEGY_SIGNALS(0,1),

    STRATEGY_SIGNAL(0,1),

    SIGNAL(0,1),

    SIGNAL_PARAM(0,1),

    SIGNAL_SYMBOL(0,1),

    ;

    private final int code;
    private final int version;

    private static final MutableMap<String, OutboxTitle> MAP =
            Stream.of(OutboxTitle.values())
                    .collect(Collectors2.toMap(OutboxTitle::name, value -> value));

    OutboxTitle(int code, int version) {
        this.code = code;
        this.version = version;
    }

    public static OutboxTitle checkout(@Nonnull String name) {
        OutboxTitle value;
        if ((value = MAP.get(name)) != null)
            return value;
        throw new IllegalArgumentException("checkout with [" + name + "] is null");
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getVersion() {
        return version;
    }

}
