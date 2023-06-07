package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.trade.PnlSettlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlSettlementDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlSettlementRepository extends JpaRepository<PnlSettlementEntity, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    List<PnlSettlementEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
