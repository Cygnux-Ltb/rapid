package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.core.types.instrument.futures.ChinaFuturesSymbol;
import io.cygnux.rapid.infra.component.ApplicationConfiguration;
import io.cygnux.rapid.infra.dto.resp.InstrumentRsp;
import io.cygnux.rapid.infra.dto.resp.InstrumentSettlementRsp;
import io.cygnux.rapid.infra.dto.resp.LastPriceRsp;
import io.cygnux.rapid.infra.dto.resp.LastPriceRsp.LastPriceObj;
import io.cygnux.rapid.infra.persistence.dao.InstrumentDao;
import io.cygnux.rapid.infra.persistence.dao.InstrumentSettlementDao;
import io.cygnux.rapid.infra.persistence.entity.InstrumentEntity;
import io.cygnux.rapid.infra.persistence.entity.InstrumentSettlementEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import io.mercury.common.collections.MutableMaps;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

import static io.cygnux.rapid.infra.persistence.JpaExecutor.insert;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.insertIfNotExist;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.select;
import static java.util.Arrays.stream;

@Service
public class InstrumentService {

    @Resource
    private InstrumentDao instrumentDao;

    @Resource
    private InstrumentSettlementDao instrumentSettlementDao;

    @Resource
    private ApplicationConfiguration configuration;

    /**
     * LastPrices Cache
     */
    private final ConcurrentMutableMap<String, LastPriceObj> cacheMap = MutableMaps.newConcurrentMap();

    @PostConstruct
    private void init() {
        if (configuration.isInitializeData()) {
            stream(ChinaFuturesSymbol.values())
                    .flatMap(symbol -> symbol.getInstruments().stream())
                    .forEach(instrument ->
                            insertIfNotExist(instrumentDao,
                                    () -> instrumentDao
                                            .queryBy(instrument.getInstrumentCode())
                                            .isEmpty(),
                                    new InstrumentEntity()
                                            .setInstrumentCode(instrument.getInstrumentCode())
                                            .setInstrumentType(instrument.getType().name())
                                            .setExchangeCode(instrument.getExchangeCode())
                                            .setFee(0.001D)
                                            .setTradable(true))
                    );
        }
    }

    private LastPriceObj getInstrumentPrice(String instrumentCode) {
        return cacheMap.putIfAbsent(instrumentCode, new LastPriceObj().setInstrumentCode(instrumentCode).setLastPrice(0.0d));
    }

    /**
     * @return List<InstrumentDTO>
     */
    public List<InstrumentRsp> getAllInstrument() {
        return select(InstrumentEntity.class,
                () -> instrumentDao.findAll())
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }


    /**
     * @param instrumentCode String
     * @return List<InstrumentDTO>
     */
    public List<InstrumentRsp> getInstrument(@Nonnull String instrumentCode) {
        return select(InstrumentEntity.class,
                () -> instrumentDao.queryBy(instrumentCode))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param tradingDay     int
     * @param instrumentCode String
     * @return List<InstrumentSettlementDTO>
     */
    public List<InstrumentSettlementRsp> getInstrumentSettlement(int tradingDay, @Nonnull String instrumentCode) {
        return select(InstrumentSettlementEntity.class,
                () -> instrumentSettlementDao.queryBy(instrumentCode, tradingDay))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param instrumentCodes String[]
     * @return List<InstrumentPrice>
     */
    public LastPriceRsp getLastPrice(String... instrumentCodes) {
        return new LastPriceRsp(stream(instrumentCodes)
                .map(this::getInstrumentPrice)
                .toList());
    }


    /**
     * @param entity MkdInstrumentEntity
     * @return boolean
     */
    public boolean putInstrument(@Nonnull InstrumentEntity entity) {
        return insert(instrumentDao, entity);
    }


    /**
     * @param entities List<MkdInstrumentEntity>
     * @return boolean
     */
    public boolean putInstrument(@Nonnull List<InstrumentEntity> entities) {
        return insert(instrumentDao, entities);
    }

    /**
     * @param entity MkdInstrumentSettlementEntity
     * @return boolean
     */
    public boolean putInstrumentSettlement(@Nonnull InstrumentSettlementEntity entity) {
        return insert(instrumentSettlementDao, entity);
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
