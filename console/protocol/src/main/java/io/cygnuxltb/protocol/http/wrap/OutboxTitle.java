package io.cygnuxltb.protocol.http.pack;

import io.mercury.common.codec.Envelope;

import javax.annotation.Nonnull;
import java.util.Map;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

public enum OutboxTitle implements Envelope {

    Heartbeat,

    TimeBars,

    SysInfo,

    SysStrategy,

    Strategy,

    StrategyParam,

    StrategySymbol,

    StrategyInstrumentPNLDaily,

    SymbolInfo,

    SymbolTradingFee,

    SymbolTradingPeriod,

    TradableInstrument,

    InitFinish,

    StrategySwitch,

    UpdateStrategyParams,

    EndTimeBar,

    UpdateStrategySignals,

    StrategySignal,

    Signal,

    SignalParam,

    SignalSymbol,

    CygInitConfig,

    ;

    private static final Map<String, OutboxTitle> Map = newUnifiedMap();

    static {
        for (OutboxTitle value : OutboxTitle.values())
            Map.put(value.name(), value);
    }

    public static OutboxTitle checkout(@Nonnull String name) {
        OutboxTitle value;
        if ((value = Map.get(name)) != null)
            return value;
        throw new IllegalArgumentException("checkout with [" + name + "] is null");
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public int getVersion() {
        return 1;
    }

}
