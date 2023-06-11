package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TbtPnl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlDao extends JpaRepository<TbtPnl, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlEntity>
     */
    List<TbtPnl> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
