package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TbtPnlSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlSettlementDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlSettlementDao extends JpaRepository<TbtPnlSettlement, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    List<TbtPnlSettlement> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
