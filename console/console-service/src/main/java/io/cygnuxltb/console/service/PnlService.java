package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.PnlDao;
import io.cygnuxltb.console.persistence.dao.PnlSettlementDao;
import io.cygnuxltb.console.persistence.entity.TbtPnl;
import io.cygnuxltb.console.persistence.entity.TbtPnlSettlement;
import io.cygnuxltb.console.service.util.DtoConverter;
import io.cygnuxltb.protocol.http.outbound.PnlDTO;
import io.cygnuxltb.protocol.http.outbound.PnlSettlementDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public final class PnlService {

    @Resource
    private PnlDao dao;

    @Resource
    private PnlSettlementDao settlementDao;

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlEntity>
     */
    public List<PnlDTO> getPnl(int strategyId, int tradingDay) {
        return select(TbtPnl.class,
                () -> dao.queryBy(strategyId, tradingDay))
                .stream()
                .map(DtoConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    public List<PnlSettlementDTO> getPnlSettlement(int strategyId, int tradingDay) {
        return select(TbtPnlSettlement.class,
                () -> settlementDao.queryBy(strategyId, tradingDay))
                .stream()
                .map(DtoConverter::toDTO)
                .collect(Collectors.toList())
                ;
    }

    /**
     * @param entity PnlEntity
     * @return boolean
     */
    public boolean putPnl(TbtPnl entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity PnlSettlementEntity
     * @return boolean
     */
    public boolean putPnlSettlement(TbtPnlSettlement entity) {
        return insertOrUpdate(settlementDao, entity);
    }

}
