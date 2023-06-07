package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.entity.trade.PnlEntity;
import io.cygnuxltb.console.persistence.entity.trade.PnlSettlementEntity;
import io.cygnuxltb.console.persistence.repository.PnlRepository;
import io.cygnuxltb.console.persistence.repository.PnlSettlementRepository;
import io.cygnuxltb.console.service.util.DtoUtil;
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
    private PnlRepository repo;

    @Resource
    private PnlSettlementRepository settlementRepo;

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlEntity>
     */
    public List<PnlDTO> getPnl(int strategyId, int tradingDay) {
        return select(PnlEntity.class,
                () -> repo.queryByStrategyIdAndTradingDay(strategyId, tradingDay))
                .stream()
                .map(DtoUtil::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    public List<PnlSettlementDTO> getPnlSettlement(int strategyId, int tradingDay) {
        return select(PnlSettlementEntity.class,
                () -> settlementRepo.queryByStrategyIdAndTradingDay(strategyId, tradingDay))
                .stream()
                .map(DtoUtil::convertToDTO)
                .collect(Collectors.toList())
                ;
    }

    /**
     * @param entity PnlEntity
     * @return boolean
     */
    public boolean putPnl(PnlEntity entity) {
        return insertOrUpdate(repo, entity);
    }

    /**
     * @param entity PnlSettlementEntity
     * @return boolean
     */
    public boolean putPnlSettlement(PnlSettlementEntity entity) {
        return insertOrUpdate(settlementRepo, entity);
    }

}
