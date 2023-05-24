package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.PnlSettlementEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlSettlementDaily DAO
 *
 * @author yellow013
 */
@Repository
public interface PnlSettlementRepository extends BaseJpaRepository<PnlSettlementEntity> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    List<PnlSettlementEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
