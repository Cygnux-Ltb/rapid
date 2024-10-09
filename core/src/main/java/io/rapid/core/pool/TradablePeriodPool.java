package io.rapid.core.pool;

import io.mercury.common.collections.MutableMaps;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.Symbol;
import io.rapid.core.instrument.TradingPeriod;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import javax.annotation.concurrent.ThreadSafe;
import java.time.LocalTime;

import static io.mercury.common.lang.Asserter.requiredLength;

@ThreadSafe
public final class TradablePeriodPool {

    private TradablePeriodPool() {
    }

    // Map<symbolId, ImmutableList<TradablePeriod>>
    private static ImmutableIntObjectMap<ImmutableList<TradingPeriod>> Pool;

    public synchronized static void register(Symbol[] symbols) {
        requiredLength(symbols, 1, "symbols");
        var map = MutableMaps.<ImmutableList<TradingPeriod>>newIntObjectMap();
        if (Pool != null)
            Pool.forEachKeyValue(map::put);
        for (var symbol : symbols) {
            if (!map.containsKey(symbol.getSymbolId()))
                map.put(symbol.getSymbolId(), symbol.getTradablePeriods());
        }
        Pool = map.toImmutable();
    }

    /**
     * 获取指定Instrument的交易周期
     *
     * @param instrument Instrument
     * @return ImmutableList<TradablePeriod>
     */
    public static synchronized ImmutableList<TradingPeriod> getTradingPeriods(Instrument instrument) {
        return Pool.get(instrument.getSymbol().getSymbolId());
    }

    /**
     * 获取指定Symbol的交易周期
     *
     * @param symbol Symbol
     * @return ImmutableList<TradablePeriod>
     */
    public static synchronized ImmutableList<TradingPeriod> getTradingPeriods(Symbol symbol) {
        return Pool.get(symbol.getSymbolId());
    }

    /**
     * @param instrument Instrument
     * @param time       LocalTime
     * @return TradablePeriod
     */
    public static synchronized TradingPeriod nextTradingPeriod(Instrument instrument, LocalTime time) {
        return nextTradingPeriod(instrument.getSymbol(), time);
    }

    /**
     * 获取下一个交易时段
     *
     * @param symbol Symbol
     * @param time   LocalTime
     * @return TradablePeriod
     */
    public static synchronized TradingPeriod nextTradingPeriod(Symbol symbol, LocalTime time) {
        var tradingPeriods = getTradingPeriods(symbol);
        TradingPeriod result = null;
        int baseTime = time.toSecondOfDay();
        int baseDiff = Integer.MAX_VALUE;
        for (var period : tradingPeriods) {
            int start = period.getStart().toSecondOfDay();
            int diff = Math.abs(start - baseTime);
            if (diff < baseDiff) {
                baseDiff = diff;
                result = period;
            }
        }
        return result;
    }

}
