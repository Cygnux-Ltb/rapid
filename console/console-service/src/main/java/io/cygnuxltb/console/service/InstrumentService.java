package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.InstrumentDao;
import io.cygnuxltb.console.persistence.dao.InstrumentSettlementDao;
import io.cygnuxltb.console.persistence.entity.TblMkdInstrument;
import io.cygnuxltb.console.persistence.entity.TblMkdInstrumentSettlement;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.request.InstrumentPrice;
import io.cygnuxltb.protocol.http.response.InstrumentDTO;
import io.cygnuxltb.protocol.http.response.InstrumentSettlementDTO;
import io.mercury.common.collections.MutableMaps;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.JpaExecutor.select;
import static java.util.Arrays.stream;

@Service
public final class InstrumentService {

    @Resource
    private InstrumentDao dao;

    @Resource
    private InstrumentSettlementDao settlementDao;

    // LastPrices Cache
    private final ConcurrentMutableMap<String, InstrumentPrice> cacheMap = MutableMaps.newConcurrentHashMap();

    private InstrumentPrice getInstrumentPrice(String instrumentCode) {
        return cacheMap.putIfAbsent(instrumentCode, new InstrumentPrice(instrumentCode));
    }

    /**
     * @param instrumentCode String
     * @return List<InstrumentEntity>
     */
    public List<InstrumentDTO> getInstrument(@Nonnull String instrumentCode) {
        return select(TblMkdInstrument.class,
                () -> dao.queryBy(instrumentCode))
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param tradingDay     int
     * @param instrumentCode String
     * @return List<InstrumentSettlementEntity>
     */
    public List<InstrumentSettlementDTO> getInstrumentSettlement(
            int tradingDay, @Nonnull String instrumentCode) {
        return select(TblMkdInstrumentSettlement.class,
                () -> settlementDao
                        .queryBy(instrumentCode, tradingDay))
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param instrumentCodes String[]
     * @return List<InstrumentPrice>
     */
    public List<InstrumentPrice> getLastPrice(String... instrumentCodes) {
        return stream(instrumentCodes)
                .map(this::getInstrumentPrice)
                .collect(Collectors.toList());
    }


    /**
     * @param entity InstrumentEntity
     * @return boolean
     */
    public boolean putInstrument(@Nonnull TblMkdInstrument entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity InstrumentSettlementEntity
     * @return boolean
     */
    public boolean putInstrumentSettlement(@Nonnull TblMkdInstrumentSettlement entity) {
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
