package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.PnlDao;
import io.cygnuxltb.console.persistence.dao.PnlSettlementDao;
import io.cygnuxltb.console.persistence.entity.TblPnl;
import io.cygnuxltb.console.persistence.entity.TblPnlSettlement;
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
        return select(TblPnl.class,
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
        return select(TblPnlSettlement.class,
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
    public boolean putPnl(TblPnl entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity PnlSettlementEntity
     * @return boolean
     */
    public boolean putPnlSettlement(TblPnlSettlement entity) {
        return insertOrUpdate(settlementDao, entity);
    }

}
