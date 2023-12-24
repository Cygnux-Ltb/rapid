package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.console.persistence.dao.InstrumentDao;
import io.cygnuxltb.console.persistence.dao.InstrumentSettlementDao;
import io.cygnuxltb.console.persistence.entity.MkdInstrumentEntity;
import io.cygnuxltb.console.persistence.entity.MkdInstrumentSettlementEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.request.InstrumentPrice;
import io.cygnuxltb.protocol.http.response.InstrumentDTO;
import io.cygnuxltb.protocol.http.response.InstrumentSettlementDTO;
import io.mercury.common.collections.MutableMaps;
import io.rapid.core.instrument.futures.ChinaFutures.ChinaFuturesSymbol;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.cygnuxltb.console.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.JpaExecutor.select;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
public final class InstrumentService {

    @Resource
    private InstrumentDao dao;

    @Resource
    private InstrumentSettlementDao settlementDao;

    private final boolean isMock = true;

    /**
     * LastPrices Cache
     */
    private final ConcurrentMutableMap<String, InstrumentPrice> cacheMap = MutableMaps.newConcurrentHashMap();

    @PostConstruct
    private void init() {
        Stream.of(ChinaFuturesSymbol.values())
                .flatMap(symbol -> symbol.getInstruments().stream())
                .map(instrument -> new MkdInstrumentEntity()
                        .setInstrumentCode(instrument.getInstrumentCode())
                        .setInstrumentType(instrument.getType().name())
                        .setExchangeCode(instrument.getExchangeCode())
                        .setFee(0.001D).setTradable(true))
                .forEach(this::putInstrument);
    }

    private InstrumentPrice getInstrumentPrice(String instrumentCode) {
        return cacheMap.putIfAbsent(instrumentCode, new InstrumentPrice(instrumentCode));
    }

    public List<InstrumentDTO> getAllInstrument() {
        return select(MkdInstrumentEntity.class,
                () -> dao.findAll())
                .stream()
                .map(DtoUtil::toDto)
                .collect(toList());
    }


    /**
     * @param instrumentCode String
     * @return List<InstrumentEntity>
     */
    public List<InstrumentDTO> getInstrument(@Nonnull String instrumentCode) {
        if (isMock) {
            var list = new ArrayList<InstrumentDTO>();
            list.add(JMockData.mock(InstrumentDTO.class));
            list.add(JMockData.mock(InstrumentDTO.class));
            list.add(JMockData.mock(InstrumentDTO.class));
            list.add(JMockData.mock(InstrumentDTO.class));
            return list;
        }
        return select(MkdInstrumentEntity.class,
                () -> dao.queryBy(instrumentCode))
                .stream()
                .map(DtoUtil::toDto)
                .collect(toList());
    }

    /**
     * @param tradingDay     int
     * @param instrumentCode String
     * @return List<InstrumentSettlementEntity>
     */
    public List<InstrumentSettlementDTO> getInstrumentSettlement(
            int tradingDay, @Nonnull String instrumentCode) {
        if (isMock) {
            var list = new ArrayList<InstrumentSettlementDTO>();
            list.add(JMockData.mock(InstrumentSettlementDTO.class));
            list.add(JMockData.mock(InstrumentSettlementDTO.class));
            list.add(JMockData.mock(InstrumentSettlementDTO.class));
            list.add(JMockData.mock(InstrumentSettlementDTO.class));
            return list;
        }
        return select(MkdInstrumentSettlementEntity.class,
                () -> settlementDao.queryBy(instrumentCode, tradingDay))
                .stream()
                .map(DtoUtil::toDto)
                .collect(toList());
    }

    /**
     * @param instrumentCodes String[]
     * @return List<InstrumentPrice>
     */
    public List<InstrumentPrice> getLastPrice(String... instrumentCodes) {
        return stream(instrumentCodes)
                .map(this::getInstrumentPrice)
                .collect(toList());
    }


    /**
     * @param entity InstrumentEntity
     * @return boolean
     */
    public boolean putInstrument(@Nonnull MkdInstrumentEntity entity) {
        return insertOrUpdate(dao, entity);
    }


    /**
     * @param entities List<TblMkdInstrument>
     * @return boolean
     */
    public boolean putInstrument(@Nonnull List<MkdInstrumentEntity> entities) {
        return insertOrUpdate(dao, entities);
    }

    /**
     * @param entity InstrumentSettlementEntity
     * @return boolean
     */
    public boolean putInstrumentSettlement(@Nonnull MkdInstrumentSettlementEntity entity) {
        return insertOrUpdate(settlementDao, entity);
    }

    /**
     * @param instrumentCode String
     * @param price          double
     * @return boolean
     */
    public boolean putLastPrice(@Nonnull String instrumentCode, double price) {
        try {
            getInstrumentPrice(instrumentCode).setLastPrice(price);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
