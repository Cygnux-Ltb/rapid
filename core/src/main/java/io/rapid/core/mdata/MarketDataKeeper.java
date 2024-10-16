package io.rapid.core.mdata;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.InstrumentKeeper;
import org.eclipse.collections.api.map.ImmutableMap;
import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serial;
import java.io.Serializable;

import static java.util.Arrays.stream;


/**
 * 管理当前最新行情<br>
 * <p>
 * 仅在初始化时使用InstrumentKeeper加载一次Instrument<br>
 * <p>
 * 无论修改最新行情或查询最新行情都使用GetLast方法获取对象<br>
 * 对象使用原子类型保证
 *
 * @author yellow013
 * @creation 2019年4月16日
 */
@ThreadSafe
public final class MarketDataKeeper implements Serializable {

    @Serial
    private static final long serialVersionUID = 2145644316828652275L;

    private static final Logger log = Log4j2LoggerFactory.getLogger(MarketDataKeeper.class);

    /**
     * MarketDataBucket Map
     */
    private final ImmutableMap<String, MarketDataBucket> saved;

    public MarketDataKeeper() {
        this("");
    }

    public MarketDataKeeper(String... instrumentCodes) {
        Asserter.requiredLength(instrumentCodes, 1, "instrumentCodes");
        var map = MutableMaps.<String, MarketDataBucket>newUnifiedMap();
        var instruments = instrumentCodes.length == 1 && instrumentCodes[0].isEmpty()
                ? InstrumentKeeper.getInstruments()
                : ImmutableLists.newImmutableList(
                stream(instrumentCodes)
                        .map(InstrumentKeeper::getInstrument)
                        .toList());
        if (instruments.isEmpty())
            throw new IllegalStateException("No instrument provided, with -> " + StringSupport.toString(instrumentCodes));
        instruments.each(instrument -> {
            map.put(instrument.getInstrumentCode(), new MarketDataBucket(instrument));
            log.info("Add [MarketDataBucket] instance, instrumentCode==[{}], instrument -> {}",
                    instrument.getInstrumentCode(), instrument);
        });
        this.saved = map.toImmutable();
    }

    /**
     * @param instrument Instrument
     * @return MarketDataBucket
     */
    public MarketDataBucket get(Instrument instrument) {
        return get(instrument.getInstrumentCode());
    }

    /**
     * @param instrumentCode String
     * @return MarketDataBucket
     */
    public MarketDataBucket get(String instrumentCode) {
        return saved.get(instrumentCode);
    }

}
