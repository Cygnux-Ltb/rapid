package io.rapid.core.instrument;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWriter;
import io.rapid.core.instrument.futures.ChinaFuturesSymbol;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * 管理全局Instrument状态
 *
 * @author yellow013
 */
@ThreadSafe
public final class InstrumentKeeper {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentKeeper.class);

    /**
     * 存储[Instrument], 以[instrumentId]索引
     */
    private static final ImmutableIntObjectMap<Instrument> MAP_BY_ID;

    /**
     * 存储[Instrument], 以[instrumentCode]索引
     */
    private static final ImmutableMap<String, Instrument> MAP_BY_CODE;

    /**
     * [Instrument]集合
     */
    private static final ImmutableList<Instrument> INSTRUMENTS;

    // 初始化内部存储结构
    static {
        try {
            var mapById = MutableMaps.<Instrument>newIntObjectMap();
            var mapByCode = MutableMaps.<String, Instrument>newUnifiedMap();
            Stream.of(ChinaFuturesSymbol.values())
                    .flatMap(symbol -> symbol.getInstruments().stream())
                    .forEach(instrument -> {
                        log.debug("PUT instrument, instrumentId==[{}], instrumentCode==[{}], instrument -> {}",
                                instrument.getInstrumentId(), instrument.getInstrumentCode(), instrument);
                        mapById.put(instrument.getInstrumentId(), instrument);
                        mapByCode.put(instrument.getInstrumentCode().toLowerCase(), instrument);
                        mapByCode.put(instrument.getInstrumentCode().toUpperCase(), instrument);
                        instrument.enable();
                    });
            MAP_BY_ID = mapById.toImmutable();
            MAP_BY_CODE = mapByCode.toImmutable();
            INSTRUMENTS = MAP_BY_ID.toList().toImmutable();
            log.info("[InstrumentManager] is initialized");
        } catch (Exception e) {
            var exception = new RuntimeException("[InstrumentManager] initialization failed", e);
            log.error("[InstrumentManager] initialization failed", exception);
            throw exception;
        }
    }

    /**
     * @return ImmutableList
     */
    public static ImmutableList<Instrument> getInstruments() {
        return INSTRUMENTS;
    }

    /**
     * @return Stream<Instrument>
     */
    public static Stream<Instrument> stream() {
        return INSTRUMENTS.stream();
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
     * @param instrumentCode String
     */
    public static Instrument setTradable(String instrumentCode) {
        var instrument = getInstrument(instrumentCode);
        instrument.enable();
        log.info("Instrument enable, instrumentCode==[{}], instrument -> {}", instrumentCode, instrument);
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
     * @param instrumentCode String
     * @return Instrument
     */
    public static Instrument setNotTradable(String instrumentCode) {
        var instrument = getInstrument(instrumentCode);
        instrument.disable();
        log.info("Instrument disable, instrumentCode==[{}], instrument -> {}", instrumentCode, instrument);
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
     * @param instrumentCode String
     * @return boolean
     */
    public static boolean isTradable(String instrumentCode) {
        return getInstrument(instrumentCode).isEnabled();
    }

    /**
     * @param instrumentId int
     * @return Instrument
     */
    public static Instrument getInstrument(int instrumentId)
            throws NullPointerException {
        var instrument = MAP_BY_ID.get(instrumentId);
        if (instrument != null)
            return instrument;
        log.error("Instrument is not find, instrumentId==[{}]", instrumentId);
        throw new NullPointerException("Instrument is not find, by [instrumentId] : " + instrumentId);
    }

    /**
     * @param instrumentCode String
     * @return Instrument
     * @throws NullPointerException npe
     */
    public static Instrument getInstrument(@Nonnull String instrumentCode)
            throws NullPointerException {
        Asserter.nonEmpty(instrumentCode, "instrumentCode");
        var instrument = MAP_BY_CODE.get(instrumentCode.toLowerCase());
        if (instrument != null)
            return instrument;
        instrument = MAP_BY_CODE.get(instrumentCode.toUpperCase());
        if (instrument != null)
            return instrument;
        log.error("Instrument is not find, instrumentCode==[{}]", instrumentCode);
        throw new NullPointerException("Instrument is not find, by [instrumentCode] : " + instrumentCode);
    }

    /**
     * @param instrumentCodes String[]
     * @return MutableList<Instrument>
     * @throws NullPointerException     npe
     * @throws IllegalArgumentException iae
     */
    public static MutableList<Instrument> getInstrument(String... instrumentCodes)
            throws NullPointerException, IllegalArgumentException {
        Asserter.requiredLength(instrumentCodes, 1, "instrumentCodes");
        return MutableLists.newFastList(Stream.of(instrumentCodes)
                .map(InstrumentKeeper::getInstrument)
                .peek(instrument -> log.info("Found instrument -> {}", instrument))
                .toList());
    }

//    /**
//     * 将[Instrument]数组转换为[ImmutableMap], 以[instrumentId]作为索引
//     *
//     * @param instruments Instrument[]
//     * @return ImmutableIntObjectMap<Instrument>
//     */
//    public static ImmutableIntObjectMap<Instrument> toImmutableMapWithId(Instrument... instruments) {
//        return ImmutableMaps.newImmutableIntMap(ImmutableSets.from(instruments),
//                Instrument::getInstrumentId);
//    }

    /**
     * @return Pretty Json String
     */
    private static String showStatus() {
        var map = new HashMap<>();
        map.put("instruments", getInstruments());
        return JsonWriter.toPrettyJson(map);
    }

    public static void main(String[] args) {
        System.out.println(InstrumentKeeper.showStatus());
    }

}
