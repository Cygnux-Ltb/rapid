package io.cygnux.rapid.core.instrument;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.core.shared.event.InstrumentStatusReport;
import io.cygnux.rapid.core.instrument.futures.ChinaFuturesSymbol;
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
 * @author yellow012
 */
@ThreadSafe
public final class InstrumentKeeper {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentKeeper.class);

    /**
     * 存储[Instrument], 以[instrumentId]索引
     */
    private static final ImmutableIntObjectMap<Instrument> MAP_BY_INSTRUMENT_ID;

    /**
     * 存储[Instrument], 以[instrumentCode]索引
     */
    private static final ImmutableMap<String, Instrument> MAP_BY_INSTRUMENT_CODE;

    /**
     * 存储[Instrument List], 以[symbolCode]索引
     */
    private static final ImmutableMap<String, ImmutableList<Instrument>> MAP_BY_SYMBOL_CODE;

    /**
     * [Instrument]集合
     */
    private static final ImmutableList<Instrument> INSTRUMENTS;

    // 初始化内部存储结构
    static {
        try {
            var mapByInstrumentId = MutableMaps.<Instrument>newIntObjectMap();
            var mapByInstrumentCode = MutableMaps.<String, Instrument>newUnifiedMap();
            // 填充[Instrument]索引MAP
            Stream.of(ChinaFuturesSymbol.values())
                    .flatMap(symbol -> symbol.getInstruments().stream())
                    .forEach(instrument -> {
                        log.debug("PUT instrument, instrumentId==[{}], instrumentCode==[{}], instrument -> {}",
                                instrument.getInstrumentId(), instrument.getInstrumentCode(), instrument);
                        mapByInstrumentId.put(instrument.getInstrumentId(), instrument);
                        mapByInstrumentCode.put(instrument.getInstrumentCode().toLowerCase(), instrument);
                        mapByInstrumentCode.put(instrument.getInstrumentCode().toUpperCase(), instrument);
                        instrument.disable();
                    });
            MAP_BY_INSTRUMENT_ID = mapByInstrumentId.toImmutable();
            MAP_BY_INSTRUMENT_CODE = mapByInstrumentCode.toImmutable();
            INSTRUMENTS = MAP_BY_INSTRUMENT_ID.toList().toImmutable();
            // 填充[Symbol]索引MAP
            var mapBySymbolCode = MutableMaps.<String, ImmutableList<Instrument>>newUnifiedMap();
            Stream.of(ChinaFuturesSymbol.values()).forEach(symbol -> {
                mapBySymbolCode.put(symbol.getSymbolCode().toLowerCase(), symbol.getInstruments());
                mapBySymbolCode.put(symbol.getSymbolCode().toUpperCase(), symbol.getInstruments());
            });
            MAP_BY_SYMBOL_CODE = mapBySymbolCode.toImmutable();
            log.info("[InstrumentKeeper] Initialized");
        } catch (Exception e) {
            var exception = new IllegalStateException("[InstrumentKeeper] initialization failed, cause -> " + e.getMessage(), e);
            log.error("[InstrumentKeeper] initialization failed", exception);
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
     * @param instrumentId int
     */
    public static Instrument setTradable(int instrumentId) {
        var instrument = getInstrumentById(instrumentId);
        instrument.enable();
        log.info("Instrument enable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
        return instrument;
    }

    /**
     * @param instrumentCode String
     */
    public static Instrument setTradable(String instrumentCode) {
        var instrument = getInstrumentByCode(instrumentCode);
        instrument.enable();
        log.info("Instrument enable, instrumentCode==[{}], instrument -> {}", instrumentCode, instrument);
        return instrument;
    }

    /**
     * @param instrumentId int
     * @return Instrument
     */
    public static Instrument setNotTradable(int instrumentId) {
        var instrument = getInstrumentById(instrumentId);
        instrument.disable();
        log.info("Instrument disable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
        return instrument;
    }

    /**
     * @param instrumentCode String
     * @return Instrument
     */
    public static Instrument setNotTradable(String instrumentCode) {
        var instrument = getInstrumentByCode(instrumentCode);
        instrument.disable();
        log.info("Instrument disable, instrumentCode==[{}], instrument -> {}", instrumentCode, instrument);
        return instrument;
    }

    /**
     * @param instrumentId int
     * @return boolean
     */
    public static boolean isTradable(int instrumentId) {
        return getInstrumentById(instrumentId).isTradable();
    }

    /**
     * @param instrumentCode String
     * @return boolean
     */
    public static boolean isTradable(String instrumentCode) {
        return getInstrumentByCode(instrumentCode).isTradable();
    }

    /**
     * @param instrumentId int
     * @return Instrument
     */
    public static Instrument getInstrumentById(int instrumentId)
            throws NullPointerException {
        var instrument = MAP_BY_INSTRUMENT_ID.get(instrumentId);
        if (instrument != null)
            return instrument;
        log.error("Instrument is not find, instrumentId==[{}]", instrumentId);
        throw new NullPointerException("Instrument is not find, by instrumentId==" + instrumentId);
    }

    /**
     * @param instrumentCode String
     * @return Instrument
     * @throws NullPointerException npe
     */
    public static Instrument getInstrumentByCode(@Nonnull String instrumentCode)
            throws NullPointerException, IllegalArgumentException {
        Asserter.nonEmpty(instrumentCode, "instrumentCode");
        var instrument = MAP_BY_INSTRUMENT_CODE.get(instrumentCode);
        if (instrument != null)
            return instrument;
        log.error("Instrument is not find, instrumentCode==[{}]", instrumentCode);
        throw new NullPointerException("Instrument is not find, by instrumentCode==" + instrumentCode);
    }

    /**
     * @param instrumentCodes String[]
     * @return MutableList<Instrument>
     * @throws NullPointerException     npe
     * @throws IllegalArgumentException iae
     */
    public static MutableList<Instrument> getInstrumentByCode(String... instrumentCodes)
            throws NullPointerException, IllegalArgumentException {
        Asserter.requiredLength(instrumentCodes, 1, "instrumentCodes");
        return MutableLists.newFastList(Stream.of(instrumentCodes)
                .map(InstrumentKeeper::getInstrumentByCode)
                .toList());
    }

    /**
     * @param symbol Symbol
     * @return ImmutableList<Instrument>
     * @throws NullPointerException     npe
     * @throws IllegalArgumentException iae
     */
    public static ImmutableList<Instrument> getInstrumentBySymbol(Symbol symbol)
            throws NullPointerException, IllegalArgumentException {
        return getInstrumentBySymbolCode(symbol.getSymbolCode());
    }


    /**
     * @param symbolCodes String
     * @return ImmutableList<Instrument>
     * @throws NullPointerException     npe
     * @throws IllegalArgumentException iae
     */
    public static ImmutableList<Instrument> getInstrumentBySymbolCode(String symbolCodes)
            throws NullPointerException, IllegalArgumentException {
        Asserter.nonEmpty(symbolCodes, "symbolCodes");
        var instruments = MAP_BY_SYMBOL_CODE.get(symbolCodes);
        if (instruments != null)
            return instruments;
        log.error("Instruments is not find, symbolCodes==[{}]", symbolCodes);
        throw new NullPointerException("Instruments is not find, by symbolCodes==" + symbolCodes);
    }

    /**
     * 更新[Instrument]状态
     *
     * @param report InstrumentStatusReport
     */
    public static void updateWith(InstrumentStatusReport report) {
        if (StringSupport.nonEmpty(report.getSymbolCode()))
            getInstrumentBySymbolCode(report.getSymbolCode())
                    .each(instrument ->
                            updateStatus(instrument, report));
        if (StringSupport.nonEmpty(report.getInstrumentCode()))
            updateStatus(getInstrumentByCode(report.getInstrumentCode()), report);
    }

    private static void updateStatus(Instrument instrument,
                                     InstrumentStatusReport report) {
        instrument.getStatus()
                .setSubscribeStatus(report.getSubscribeStatus())
                .setTradingStatus(report.getTradingStatus());
    }

    /**
     * @return Pretty Json String
     */
    private static String showStatus() {
        var map = new HashMap<>();
        map.put("instruments", getInstruments());
        return JsonWriter.toPrettyJson(map);
    }

    public static void main(String[] args) {
        InstrumentKeeper.setTradable("CF509");
        System.out.println(InstrumentKeeper.showStatus());
    }

}
