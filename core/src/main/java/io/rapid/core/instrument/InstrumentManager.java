package io.rapid.core.instrument;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWriter;
import io.rapid.core.instrument.futures.ChinaFuturesSymbol;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * 管理全局Instrument状态
 *
 * @author yellow013
 */
@ThreadSafe
public final class InstrumentManager {

    // Logger
    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentManager.class);

    /**
     * 存储[Instrument], 以[instrumentId]索引
     */
    private static final MutableIntObjectMap<Instrument> INSTRUMENT_MAP_BY_ID = MutableMaps.newIntObjectMap();

    /**
     * 存储[Instrument], 以[instrumentCode]索引
     */
    private static final MutableMap<String, Instrument> INSTRUMENT_MAP_BY_CODE = MutableMaps.newUnifiedMap();

    /**
     * 初始化标识
     */
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);

    private static ImmutableList<Instrument> instruments;

    private InstrumentManager() {
    }

    private static void initialize() {
        if (isInitialized.compareAndSet(false, true)) {
            try {
                Stream.of(ChinaFuturesSymbol.values()).forEach(InstrumentManager::saveInstrument);
                InstrumentManager.instruments = INSTRUMENT_MAP_BY_ID.toList().toImmutable();
                log.info("InstrumentKeeper is initialized");
            } catch (Exception e) {
                var re = new RuntimeException("InstrumentManager initialization failed", e);
                log.error("InstrumentManager initialization failed", re);
                throw re;
            }
        } else {
            log.error("InstrumentManager already initialized");
        }
    }

    private static void saveInstrument(Symbol symbol) {
        symbol.getInstruments().each(instrument -> {
            log.info("Put instrument, instrumentId==[{}], instrumentCode==[{}], instrument -> {}",
                    instrument.getInstrumentId(), instrument.getInstrumentCode(), instrument);
            INSTRUMENT_MAP_BY_ID.put(instrument.getInstrumentId(), instrument);
            INSTRUMENT_MAP_BY_CODE.put(instrument.getInstrumentCode().toLowerCase(), instrument);
            INSTRUMENT_MAP_BY_CODE.put(instrument.getInstrumentCode().toUpperCase(), instrument);
            setTradable(instrument);
        });
    }

    public static boolean isInitialized() {
        return isInitialized.get();
    }

    /**
     * @param instrument Instrument
     */
    public static Instrument setTradable(@Nonnull Instrument instrument) {
        return setTradable(instrument.getInstrumentId());
    }

    /**
     * @param instrumentId int
     */
    public static Instrument setTradable(int instrumentId) {
        var instrument = getInstrument(instrumentId);
        instrument.enable();
        log.info("Instrument enable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
        return instrument;
    }

    /**
     * @param instrument Instrument
     * @return Instrument
     */
    public static Instrument setNotTradable(Instrument instrument) {
        return setNotTradable(instrument.getInstrumentId());
    }

    /**
     * @param instrumentId int
     * @return Instrument
     */
    public static Instrument setNotTradable(int instrumentId) {
        var instrument = getInstrument(instrumentId);
        instrument.disable();
        log.info("Instrument disable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
        return instrument;
    }

    /**
     * @param instrument Instrument
     * @return boolean
     */
    public static boolean isTradable(Instrument instrument) {
        return isTradable(instrument.getInstrumentId());
    }

    /**
     * @param instrumentId int
     * @return boolean
     */
    public static boolean isTradable(int instrumentId) {
        return getInstrument(instrumentId).isEnabled();
    }

    /**
     * @return ImmutableList
     */
    public static ImmutableList<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * @param instrumentId int
     * @return Instrument
     */
    public static Instrument getInstrument(int instrumentId) {
        var instrument = INSTRUMENT_MAP_BY_ID.get(instrumentId);
        if (instrument == null)
            throw new IllegalArgumentException("Instrument is not find, by instrumentId : " + instrumentId);
        return instrument;
    }

    /**
     * @param instrumentCode String
     * @return Instrument
     */
    public static Instrument getInstrument(@Nonnull String instrumentCode)
            throws IllegalArgumentException {
        var instrument = INSTRUMENT_MAP_BY_CODE.get(instrumentCode.toLowerCase());
        if (instrument != null)
            return instrument;
        instrument = INSTRUMENT_MAP_BY_CODE.get(instrumentCode.toUpperCase());
        if (instrument != null)
            return instrument;
        log.error("Instrument is not find, by instrument code : {}", instrumentCode);
        throw new IllegalArgumentException("Instrument is not find, by instrument code : " + instrumentCode);
    }

    public static MutableList<Instrument> getInstrument(String... instrumentCodes)
            throws NullPointerException, IllegalArgumentException {
        Asserter.requiredLength(instrumentCodes, 1, "instrumentCodes");
        if (isInitialized()) {
            return MutableLists.newFastList(Stream.of(instrumentCodes)
                    .map(InstrumentManager::getInstrument)
                    .peek(instrument -> log.info("Found instrument -> {}", instrument))
                    .toList());
        } else {
            initialize();
            return getInstrument(instrumentCodes);
        }
    }

    /**
     * @return Pretty Json String
     */
    public static String showStatus() {
        var map = new HashMap<>();
        map.put("isInitialized", isInitialized);
        map.put("instruments", getInstruments());
        return JsonWriter.toPrettyJson(map);
    }

}
